package pl.prasny.api.http.exception.router;

import pl.prasny.api.http.type.HttpStatus;

/**
 * Nad-klasa wszystkich wyjątków powiązanych z komponentem routera HTTP
 */
public abstract class RouterException extends RuntimeException {

    public RouterException(String message) {
        super(message);
    }

    private HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
