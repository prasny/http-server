package pl.prasny.api.http.exception.router.handler;

import pl.prasny.api.http.exception.router.RouterException;
import pl.prasny.api.http.type.HttpStatus;

/**
 * Wyjątek wyrzucany w klasie router gdy w liście routów nie będzie żadnego routa z odpowiadającym żądaniu http endpoincie
 */
@SuppressWarnings("unused")
public class HandlerNotFoundException extends RouterException {

    public HandlerNotFoundException() {
        super("pl.prasny.component.api.http.exception.router.handler.handler_not_found");
        setHttpStatus(HttpStatus.NOT_FOUND);
    }

}
