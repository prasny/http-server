package pl.prasny.api.http.exception.router.handler;

import pl.prasny.api.http.exception.router.RouterException;
import pl.prasny.api.http.type.HttpStatus;

/**
 * Wyjątek wyrzucany podczas przechwycenia jakiegokolwiek wyjądku pochodzącego z wywołanej w routerze metody invoke() handlera
 */
@SuppressWarnings("unused")
public class HandlerInvokeMethodLogicException extends RouterException {

    public HandlerInvokeMethodLogicException() {
        super("pl.prasny.component.api.http.exception.router.handler.handler_invoke_method_logic");
        setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
