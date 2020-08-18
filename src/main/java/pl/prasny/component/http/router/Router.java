package pl.prasny.component.http.router;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.prasny.api.http.IRequest;
import pl.prasny.api.http.IRequestParams;
import pl.prasny.api.http.IResponse;
import pl.prasny.api.http.IRoute;
import pl.prasny.api.http.exception.router.RouterException;
import pl.prasny.api.http.exception.router.handler.HandlerInvokeMethodLogicException;
import pl.prasny.api.http.exception.router.handler.HandlerNotFoundException;
import pl.prasny.api.http.exception.router.request.RequestException;
import pl.prasny.api.http.exception.router.response.EmptyResponseContentTypeException;
import pl.prasny.api.http.exception.router.response.EmptyResponseException;
import pl.prasny.api.http.exception.validator.ValidationException;
import pl.prasny.api.http.response.Response;
import pl.prasny.api.http.type.HeaderContentTypeValuesEnum;
import pl.prasny.api.http.type.HttpRequestMethodEnum;
import pl.prasny.api.http.type.HttpStatus;
import pl.prasny.component.http.component.RequestValidatorComponent;
import pl.prasny.component.http.request.Request;
import pl.prasny.component.http.request.param.BodyParams;
import pl.prasny.component.http.request.param.UrlParams;
import pl.prasny.component.http.type.HeaderTypesValuesEnum;
import pl.prasny.component.http.type.ValidatorMessageEnum;
import pl.prasny.component.http.utils.ParamsHelper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Set;

import static pl.prasny.component.http.utils.ParamsHelper.convertHeadersToHeaderParams;
import static pl.prasny.component.http.utils.ParamsHelper.convertStringQueryToMap;
import static pl.prasny.component.http.utils.StreamHelper.inputStreamToString;

/**
 * Router to globalny handler wbudowanego serwera Javy (java.base.net).
 * Do Routera kierowane są wszystkie requesty, ze wszystkich endpointów.
 * Jego zadaniem jest:
 * - odebranie żądanie poprzez metodę handle (z interfejsu HttpHandler)
 * - przerobienie żądanie na obiekt Request (metoda: prepareRequestObject)
 * - walidacja (klasa RequestValidatorComponene i metoda run w jej implementacji)
 * - poprawienie obiekt request po walidacji według przyjętych standardów (np. ignorowanie body przy metodzie GET)
 * bazując na validatorWarnings utworzonych podczas walidacji
 * - odnalezienie odpowiedni IHttpPortHandler ze zbioru routes i uruchomić jego metodę invoke w osobnym wątku
 * <p>
 * Metoda handle przekazuje do routera obiekt HttpExchange w którym zawarte są wszystkie informacje o żądaniu.
 * Dzięki temu obiektowi również możliwe jest wysyłanie odpowiedzi do nadawcy żądania (metoda sendResponse).
 * </p>
 */
public class Router implements HttpHandler {
    private URI requestUri;
    private Set<IRoute> routes;
    private RequestValidatorComponent validator;
    private Set<ValidatorMessageEnum> validationWarnings;
    final private Logger LOGGER = LoggerFactory.getLogger(Router.class);

    public Router(Set<IRoute> routes) {
        this.routes = routes;
    }

    /**
     * Metoda wywoływana przy otrzymaniu przez serwer żądania.
     * Metoda zawiera procedurę postępowania z każdym żądaniem od czasu jego otrzymania do wysłania odpowiedzi.
     *
     * @param exchange obiekt HttpExchange zawierający wszystkie informacje o żądaniu, jak również umożliwiający wysłanie odpowiedzi
     * @throws IOException błąd wyrzucany przez sendResponse
     */
    public void handle(HttpExchange exchange) throws IOException {
        requestUri = exchange.getRequestURI();
        try {
            //prepare request
            IRequest request = prepareRequestObject(exchange);
            //validate request
            validator = new RequestValidatorComponent(request);
            validationWarnings = validator.run();
            //request correction after validation
            request = correctRequestAfterValidation(request, validationWarnings);
            //dispatch request
            dispatchRequestAndSendResponse(request, exchange);
            //TODO: instrukcje catch poniżej mogłyby być uproszczone bo wszędzie jest to samo
        } catch (RouterException e) { //catch router exception
            String message = e.getMessage();
            sendResponse(exchange, new Response(message, e.getHttpStatus()));
            LOGGER.warn(message);
        } catch (ValidationException e) { //catch validation exception
            String message = e.getMessage();
            sendResponse(exchange, new Response(message, HttpStatus.BAD_REQUEST));
            LOGGER.warn(message);
        } catch (Throwable e) { //catch everything else and print it
            String message = e.getMessage();
            sendResponse(exchange, new Response(message, HttpStatus.INTERNAL_SERVER_ERROR));
            LOGGER.warn(message);
        }
    }

    /**
     * Zbiór reguł korkty requesta według założonych standardów.
     *
     * @param request            request który będzie korygowany
     * @param validationWarnings zbiór ostrzeżeń zebranych podczas procesu walidacji
     * @return poprawiony request
     */
    private IRequest correctRequestAfterValidation(IRequest request, Set<ValidatorMessageEnum> validationWarnings) {
        if (!validationWarnings.isEmpty()) {
            //clear body if present in get request
            if (validationWarnings.contains(ValidatorMessageEnum.BODY_PRESENT_IN_GET_METHOD)) {
                request.setBody("");
                request.setBodyParams(new BodyParams(new HashMap<>()));
                LOGGER.info("Body present in GET request method. Request passed without body.");
            }
            //clear urlparams if present in post request
            if (validationWarnings.contains(ValidatorMessageEnum.URL_CONTAINS_PARAMETERS_IN_POST_METHOD)) {
                request.setUrlParams(new UrlParams(new HashMap<>()));
                LOGGER.info("Request URL contains parameters. Request passed without URL parameters.");
            }
        }
        return request;
    }

    /**
     * Metoda wyciągająca informację z obiektu typu HttpExchange i przerabiająca go na obiekt Request
     *
     * @param exchange obiekt HttpExchange żądania
     * @return obiekt Request
     * @throws RequestException w przypadku jakichkolwiek problemów z utworzeniem obiektu Request
     */
    private IRequest prepareRequestObject(HttpExchange exchange) throws RequestException {
        try {
            //obtain request parts
            Headers requestHeaders = exchange.getRequestHeaders();
            String requestBody = inputStreamToString(exchange.getRequestBody());

            //achieve headers params
            IRequestParams requestHeaderParams = convertHeadersToHeaderParams(requestHeaders);

            //achieve url parameters
            IRequestParams requestUrlParams = new UrlParams(ParamsHelper.convertUriQueryToMap(requestUri));

            //check and handle with type of body
            String typeOfBody = requestHeaderParams.getValue(HeaderTypesValuesEnum.CONTENT_TYPE.getValue());
            IRequestParams requestBodyParams;

            if (HeaderContentTypeValuesEnum.JSON.getValue().equals(typeOfBody)) {
                requestBodyParams = new BodyParams(new HashMap<>());
            } else if (HeaderContentTypeValuesEnum.X_WEB_FORM_URLENCODED.getValue().equals(typeOfBody)) {
                requestBodyParams = new BodyParams(convertStringQueryToMap(requestBody));
            } else if (HeaderContentTypeValuesEnum.TEXT_PLAIN.getValue().equals(typeOfBody)) {
                requestBodyParams = new BodyParams(new HashMap<>());
            } else {
                requestBodyParams = new BodyParams(new HashMap<>());
            }

            //prepare method
            HttpRequestMethodEnum method = HttpRequestMethodEnum.valueOf(exchange.getRequestMethod().toUpperCase());

            //complete request
            return new Request(requestBodyParams, requestUrlParams, requestHeaderParams, requestBody, method);

        } catch (Throwable e) {
            throw new RequestException(e.getClass().getName() + " " + e.getMessage());
        }
    }

    /**
     * Metoda przeszukuje zbiór routes w poszukiwaniu endpointa zgodnego z endpointem żądania.
     * W przypadku znalezienia odpowiedniego endpointa uruchamia metodę invokę zawartą w HttpPortHandler
     * z Route zawierającego dany endpoint. Metoda invoke uruchamiana jest w osobnym wątku.
     * <p>
     * Błędy wyrzucane przez logikę handlera zostają przechwytywane
     * i wyrzucany zostaje wyjątek HandlerInvokeMethodLogicException.
     * </p>
     * <p>
     * W przypadku nieznalezienia odpowiedniego endpointa w zbiorze routes
     * wyrzucany zostaje wyjątek HandlerNotFoundException.
     * </p>
     *
     * @param request  obiekt request przekazywany do metody invoke handlera
     * @param exchange obiekt HttpExchange w tym wypadku umożliwiający wysłanie odpowiedzi
     */
    private void dispatchRequestAndSendResponse(IRequest request, HttpExchange exchange) {
        //compare routes endpoint with request endpoint and call invoke method of router(s?)
        String requestEndpoint = requestUri.getPath();
        boolean handlerFound = false;
        for (IRoute route : routes) {
            //"if" condition contains every endpoint "/" character scenario (absent or present at the end of endpoint)
            //so end backslash of endpoint do not matter ( /abc/ == /abc )
            if (route.getEndpoint().equals(requestEndpoint) ||
                    route.getEndpoint().equals(requestEndpoint + "/") ||
                    requestEndpoint.equals(route.getEndpoint() + "/")) {
                try {
                    //run handler's invoke method and send response in separate thread
                    new Thread(new Runnable() {
                        IResponse response;

                        public void run() {
                            response = route.getHandler().invoke(request);

                            try {
                                sendResponse(exchange, response);
                            } catch (RouterException e) {
                                try {
                                    sendResponse(exchange, new Response(e.getMessage(), e.getHttpStatus()));
                                } catch (IOException eInternal) {
                                    eInternal.printStackTrace();
                                }
                            } catch (Throwable e) {
                                throw new HandlerInvokeMethodLogicException();
                            }
                        }
                    }).start();
                } catch (Throwable e) {
                    throw new HandlerInvokeMethodLogicException();
                }
                handlerFound = true;
            }
        }
        if (!handlerFound) {
            throw new HandlerNotFoundException();
        }
    }

    /**
     * Metoda wysyłająca odpowiedź na żądanie
     *
     * @param exchange obiekt HttpExchange w tym wypadku umożliwiający wysłanie odpowiedzi
     * @param response obiekt Response zawierający ciało odpowiedzi i informację o zawartości odpowiedzi
     */
    private void sendResponse(HttpExchange exchange, IResponse response) throws IOException {
        String responseBody = "";
        String responseContentType;

        if (response == null) {
            throw new EmptyResponseException();
        } else if (response.getContentType() == null) {
            throw new EmptyResponseContentTypeException();
        } else {
            if (response.getBody() != null) {
                responseBody = response.getBody();
            }
            responseContentType = response.getContentType().getValue();
            exchange.getResponseHeaders().add("Content-Type", responseContentType);
        }

        exchange.sendResponseHeaders(response.status().getValue(), responseBody.length());
        OutputStream os = exchange.getResponseBody();
        os.write(responseBody.getBytes());
        os.close();
    }
}
