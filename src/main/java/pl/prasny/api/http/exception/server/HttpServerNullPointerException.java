package pl.prasny.api.http.exception.server;

/**
 * Wyrzucany przez server.createContext(), gdy:
 * ścieżka globalnej klasy Router jest nieprawidłowa (w przypadku PlainJavaHttp musi być globalna czyli "/") lub
 * gdy przy wtorzeniu kontekstu utworzymy klasę Router, który jest nullem
 * //TODO jest sens to obsługiwać, skoro w implementacji jest z góry określony global endpoint i nowa klasa Router z pustymi routami (warunek zawsze spełniony)?
 */
public class HttpServerNullPointerException extends Throwable {
    public HttpServerNullPointerException(String message) {
        super(message);
    }
}
