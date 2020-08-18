package pl.prasny.api.http.exception.validator;

/**
 * wyjątek wyrzucany podczas walidacji gdy wykryty zostanie brak body w żądaniu gdzie body jest wymagane (np. POST)
 */
@SuppressWarnings("unused")
public class BodyNotPresentInPostRequestValidationException extends ValidationException {

    public BodyNotPresentInPostRequestValidationException() {
        super("pl.prasny.component.api.http.exception.validator.body_not_present_in_post_request");
    }

}
