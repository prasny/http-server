package pl.prasny.api.http.exception.server;
/**
 * Wyjątek wyrzucany w PlainJavaHttp w metodzie addRoute, gdy:
 * - w liście routów istnieje już route z takim endpointem
 * - route, handler lub endpoint są nullem
 * - wystąpi inny błąd przy dodawaniu obiektu do zbioru:
 *     + IllegalArgumentException
 *     + NullPointerException
 *     + ClassCastException
 *     + UnsupportedOperationException
 */
public class HttpServerRouteAddException extends RuntimeException {
    public HttpServerRouteAddException(String message) {
        super(message);
    }
}
