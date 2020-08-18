package pl.prasny.api.http.exception.router.request;

import pl.prasny.api.http.exception.router.RouterException;
import pl.prasny.api.http.type.HttpStatus;

/**
 * Klasa wyjątków wyrzucanych w trakcie walidacji składowych żądania
 */
@SuppressWarnings("unused")
public class RequestException extends RouterException {

    public RequestException(String message) {
        super(message);
        setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
