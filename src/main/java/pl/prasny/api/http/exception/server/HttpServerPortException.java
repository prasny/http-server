package pl.prasny.api.http.exception.server;

/**
 * Wyjątek wyrzucany w konstruktorze AbstractHttpPort, gdy wartość "port":
 * - zawiera ciąg znaków którego nie da się sprarsować do int
 * - zawiera liczbę wykraczającą poza całkowity zbiór <0,65535>
 */
public class HttpServerPortException extends IllegalArgumentException {
    public HttpServerPortException(String message) {
        super(message);
    }
}
