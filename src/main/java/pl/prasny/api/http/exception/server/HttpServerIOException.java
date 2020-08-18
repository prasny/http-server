package pl.prasny.api.http.exception.server;

/**
 * Wyrzucany przez HttpServer.create() (oraz inne klasy, których wyjątków nie sprecyzowano)
 * przekazuje standardowy wyjątek IOException
 */
public class HttpServerIOException extends Throwable {
    public HttpServerIOException(String message) {
        super(message);
    }
}
