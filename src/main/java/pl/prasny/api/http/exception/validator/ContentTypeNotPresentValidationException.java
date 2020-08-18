package pl.prasny.api.http.exception.validator;


/**
 * wyjątek wyrzucany podczas walidacji gdy brakuje nagłówka "Content-Type" a body jest wymagane (nie dotyczy np. GET)
 */
@SuppressWarnings("unused")
public class ContentTypeNotPresentValidationException extends ValidationException {

    public ContentTypeNotPresentValidationException() {
        super("pl.prasny.component.api.http.exception.validator.content_type_not_present");
    }

}
