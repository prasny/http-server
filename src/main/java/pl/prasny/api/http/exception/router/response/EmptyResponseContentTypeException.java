package pl.prasny.api.http.exception.router.response;

import pl.prasny.api.http.exception.router.RouterException;
import pl.prasny.api.http.type.HttpStatus;

/**
 * Wyjątek wyrzucany gdy content type obiektu Response będący obiektem zwracanym przez metodę invoke handlera jest nullem
 */
@SuppressWarnings("unused")
public class EmptyResponseContentTypeException extends RouterException {

    public EmptyResponseContentTypeException() {
        super("pl.prasny.component.api.http.exception.router.response.empty_response_content_type");
        setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
