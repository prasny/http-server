package pl.prasny.api.http.exception.validator;

import pl.prasny.api.http.exception.router.RouterException;
import pl.prasny.api.http.type.HttpStatus;

/**
 * wyjątek wyrzucany podczas walidacji gdy wykryty zostanie nieobsługiwana metoda żądania
 */
@SuppressWarnings("unused")
public class NotSupportedMethodValidationException extends RouterException {

    public NotSupportedMethodValidationException() {
        super("pl.prasny.component.http.exception.validator.not_supported_method");
        setHttpStatus(HttpStatus.METHOD_NOT_ALLOWED);
    }

}
