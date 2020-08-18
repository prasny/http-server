package pl.prasny.api.http.exception.server;
/**
 * wyjątek wyrzucany w PlainJavaHttp w metodzie removeRoute, gdy:
 * - w liście routów nie istnieje route z takim endpointem
 * - wystąpi inny błąd przy dodawaniu obiektu do zbioru:
 *     + NullPointerException
 *     + ClassCastException
 *     + UnsupportedOperationException
 */
public class HttpServerRouteRemoveException extends RuntimeException {

    public HttpServerRouteRemoveException(String message) {
        super(message);
    }
}
