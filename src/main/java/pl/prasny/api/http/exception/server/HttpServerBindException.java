package pl.prasny.api.http.exception.server;

/**
 * Wyrzucany przez HttpServer.create(), gdy wyjatek wyrzucany gdy serwer nie może wystartować na podanym porcie
 * (najczęściej port jest użyciu)
 */
public class HttpServerBindException extends Exception {
    public HttpServerBindException(String message) {
        super(message);
    }
}
