package pl.prasny.api.http.exception.server;

/**
 * Wyjątek wyrzucany przez server.createContext() gdy ścieżka endpointa jest nieprawidłowa lub w użyciu
 */
public class HttpServerIllegalArgumentException extends Throwable {
    public HttpServerIllegalArgumentException(String message) {
        super(message);
    }
}
