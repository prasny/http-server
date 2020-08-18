package pl.prasny.api.http.exception.server;

/**
 * wyrzucany przez server.setExecutor(), gdy
 * serwer już wystartował
 */
public class HttpServerIllegalStateException extends Exception {
    public HttpServerIllegalStateException(String message) {
        super(message);
    }
}
