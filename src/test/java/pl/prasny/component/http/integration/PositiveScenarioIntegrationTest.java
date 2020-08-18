package pl.prasny.component.http.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.prasny.api.http.IHttpPort;
import pl.prasny.api.http.IHttpPortHandler;
import pl.prasny.api.http.type.HeaderContentTypeValuesEnum;
import pl.prasny.api.http.type.HttpStatus;
import pl.prasny.component.http.handler.impl.TestHandler;
import pl.prasny.component.http.route.Route;
import pl.prasny.component.http.server.PlainJavaHttp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertEquals;

public class PositiveScenarioIntegrationTest {

    private IHttpPort server;
    private HttpClient httpClient;

    @Before
    public void setUp() throws Throwable {
        //ENDPOINTS
        String endpoint = "/test";
        //HANDLERS
        IHttpPortHandler testHandler = new TestHandler();

        //SERVER
        server = new PlainJavaHttp("8888");
        server.startServer();
        //ROUTES
        server.addRoute(new Route(endpoint, testHandler));
        //CLIENT
        httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    }

    @Test
    public void positiveScenarioForGetRequestWithoutBodyTest() throws IOException, InterruptedException {
        //variables
        String expectedResponseBodyFromTestHandler =
                "UrlParams: \n" +
                "\n" +
                "BodyParams: \n" +
                "\n" +
                "Body: \n";

        //set http client and send request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8888/test"))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        //test condition
        assertEquals(expectedResponseBodyFromTestHandler, response.body());
        assertEquals(response.statusCode(), HttpStatus.OK.getValue());
    }

    @Test
    public void positiveScenarioForGetRequestWithoutBodyWithUrlParamsTest() throws IOException, InterruptedException {
        //variables
        String expectedResponseBodyFromTestHandler =
                "UrlParams:    \n" +
                " key1=val1\n" +
                " key2=val2\n" +
                " something=nothing\n" +
                "\n" +
                "BodyParams: \n" +
                "\n" +
                "Body: \n";

        //set http client and send request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8888/test?key1=val1&key2=val2&something=nothing"))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        //test condition
        assertEquals(expectedResponseBodyFromTestHandler, response.body());
        assertEquals(response.statusCode(), HttpStatus.OK.getValue());
    }

    @Test
    public void positiveScenarioForPostRequestWithXWebFormUrlEncodedTest() throws IOException, InterruptedException {
        //variables
        String requestBody = "key1=val1&key2=val2";
        String expectedResponseBodyFromTestHandler =
                "UrlParams: \n" +
                "\n" +
                "BodyParams:   \n" +
                " key1=val1\n" +
                " key2=val2\n" +
                "\n" +
                "Body: \n" +
                "key1=val1&key2=val2";

        //set http client and send request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8888/test"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-Type", HeaderContentTypeValuesEnum.X_WEB_FORM_URLENCODED.getValue())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        //test condition
        assertEquals(expectedResponseBodyFromTestHandler, response.body());
        assertEquals(response.statusCode(), HttpStatus.OK.getValue());
    }

    @Test
    public void positiveScenarioForPostRequestWithPlainTextAsBodyTest() throws IOException, InterruptedException {
        //variables
        String requestBody = "this is plain text with some characters!$^#%&%$^@$!%#$!$#%#$^$@&%$";
        String expectedResponseBodyFromTestHandler =
                "UrlParams: \n" +
                "\n" +
                "BodyParams: \n" +
                "\n" +
                "Body: \n" +
                "this is plain text with some characters!$^#%&%$^@$!%#$!$#%#$^$@&%$";

        //set http client and send request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8888/test"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-Type", HeaderContentTypeValuesEnum.TEXT_PLAIN.getValue())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        //test condition
        assertEquals(expectedResponseBodyFromTestHandler, response.body());
        assertEquals(response.statusCode(), HttpStatus.OK.getValue());
    }

    @After
    public void closeServer() {
        server.stopServer();
    }
}
