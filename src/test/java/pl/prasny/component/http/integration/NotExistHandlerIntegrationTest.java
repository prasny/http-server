package pl.prasny.component.http.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.prasny.api.http.IHttpPort;
import pl.prasny.api.http.type.HttpStatus;
import pl.prasny.component.http.server.PlainJavaHttp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertEquals;

/**
 * Test sprawdzający zwrotną odpowiedź HTTP w sytuacji gdy dany endpoint i handler nie istnieje
 */
public class NotExistHandlerIntegrationTest {

    private IHttpPort server;
    private HttpClient httpClient;

    @Before
    public void setUp() throws Throwable {
        //SERVER
        server = new PlainJavaHttp("8888");
        server.startServer();
        //CLIENT
        httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    }

    @Test
    public void notExistHandlerTest() throws IOException, InterruptedException {
        // given

        //when
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8888/not_existed_endpoint")).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // then
        assertEquals("pl.prasny.component.api.http.exception.router.handler.handler_not_found", response.body());
        assertEquals(response.statusCode(), HttpStatus.NOT_FOUND.getValue());
    }

    @After
    public void closeServer() {
        server.stopServer();
    }
}
