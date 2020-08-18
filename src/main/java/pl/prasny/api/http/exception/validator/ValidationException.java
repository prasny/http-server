package pl.prasny.api.http.exception.validator;

/**
 * Nad-klasa wszystkich wyjątków walidacji
 */
@SuppressWarnings("WeakerAccess")
public abstract class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

}
