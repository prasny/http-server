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

public class ValidationWarningsIntegrationTest {

    String endpoint;

    IHttpPortHandler testHandler;

    IHttpPort server;

    HttpClient httpClient;

    @Before
    public void setUp() throws Throwable {
        //ENDPOINTS
        endpoint = "/test";
        //HANDLERS
        testHandler = new TestHandler();

        //SERVER
        server = new PlainJavaHttp("8888");
        server.startServer();
        //ROUTES
        server.addRoute(new Route(endpoint, testHandler));
        //CLIENT
        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    @Test
    public void shouldMakeRequestObjectWithoutUrlParamsForPostRequestWithUrlParamsTest() throws IOException, InterruptedException {
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
                .uri(URI.create("http://localhost:8888/test?key1=val1&key2=val2"))
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
