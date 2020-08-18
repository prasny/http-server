package pl.prasny.component.http.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.prasny.api.http.IHttpPort;
import pl.prasny.api.http.IHttpPortHandler;
import pl.prasny.api.http.type.HeaderContentTypeValuesEnum;
import pl.prasny.api.http.type.HttpStatus;
import pl.prasny.component.http.handler.impl.EmptyResponseHandler;
import pl.prasny.component.http.handler.impl.TestHandler;
import pl.prasny.component.http.route.Route;
import pl.prasny.component.http.server.PlainJavaHttp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertEquals;

public class ExceptionsIntegrationTest {

    private IHttpPort server;
    private HttpClient httpClient;

    @Before
    public void setUp() throws Throwable {
        //ENDPOINTS
        String endpoint1 = "/test";
        String endpoint2 = "/emptyResponseHandler";
        //HANDLERS
        IHttpPortHandler testHandler = new TestHandler();
        IHttpPortHandler emptyResponseHandler = new EmptyResponseHandler();

        //SERVER
        server = new PlainJavaHttp("8888");
        server.startServer();
        //ROUTES
        server.addRoute(new Route(endpoint1, testHandler));
        server.addRoute(new Route(endpoint2, emptyResponseHandler));
        //CLIENT
        httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    }

    @Test
    public void shouldReturnExceptionForPostRequestWithoutContentTypeHeaderTest() throws IOException, InterruptedException {
        //variables
        String requestBody = "this is plain text with some characters!$^#%&%$^@$!%#$!$#%#$^$@&%$";
        String expectedResponseBodyFromTestHandler =
                "pl.prasny.component.api.http.exception.validator.content_type_not_present";

        //set http client and send request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8888/test"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        //test condition
        assertEquals(expectedResponseBodyFromTestHandler, response.body());
        assertEquals(response.statusCode(), HttpStatus.BAD_REQUEST.getValue());
    }

    @Test
    public void shouldReturnExceptionForPostRequestWithoutBodyTest() throws IOException, InterruptedException {
        //variables
        String requestBody = "";
        String expectedResponseBodyFromTestHandler =
                "pl.prasny.component.api.http.exception.validator.body_not_present_in_post_request";

        //set http client and send request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8888/test"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-Type", HeaderContentTypeValuesEnum.TEXT_PLAIN.getValue())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        //test condition
        assertEquals(expectedResponseBodyFromTestHandler, response.body());
        assertEquals(response.statusCode(), HttpStatus.BAD_REQUEST.getValue());
    }

    @Test
    public void shouldReturnExceptionWhenRequestHaveUnsupportedMethodTest() throws IOException, InterruptedException {
        //variables
        String expectedResponseBodyFromTestHandler =
                "pl.prasny.component.api.http.exception.validator.not_supported_method";

        //set http client and send request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8888/test"))
                .DELETE()
                .header("Content-Type", HeaderContentTypeValuesEnum.TEXT_PLAIN.getValue())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        //test condition
        assertEquals(expectedResponseBodyFromTestHandler, response.body());
        assertEquals(response.statusCode(), HttpStatus.METHOD_NOT_ALLOWED.getValue());
    }

    @Test
    public void shouldReturnExceptionWhenHandlersInvokeReturnNullResponseTest() throws IOException, InterruptedException {
        //set http client and send request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8888/emptyResponseHandler"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        //test condition
        assertEquals("pl.prasny.component.api.http.exception.router.response.empty_response", response.body());
        assertEquals(response.statusCode(), HttpStatus.INTERNAL_SERVER_ERROR.getValue());
    }

    @After
    public void closeServer() {
        server.stopServer();
    }
}
