package pl.prasny.api.http.exception.router.response;

import pl.prasny.api.http.exception.router.RouterException;
import pl.prasny.api.http.type.HttpStatus;

/**
 * Wyjątek wyrzucany gdy obiekt Response będący obiektem zwracanym przez metodę invoke handler'a jest nullem
 */
@SuppressWarnings("unused")
public class EmptyResponseException extends RouterException {

    public EmptyResponseException() {
        super("pl.prasny.component.api.http.exception.router.response.empty_response");
        setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
