package pl.prasny.component.http.request.validator;

import pl.prasny.api.http.IRequest;
import pl.prasny.api.http.exception.validator.NotSupportedMethodValidationException;
import pl.prasny.component.http.type.ValidatorMessageEnum;

/**
 * Klasa abstrakcyjna rozszerzana przez każdy walidator
 */
public abstract class AbstractValidator implements IValidator {

    private IRequest request;

    public AbstractValidator(IRequest request) {
        this.request = request;
    }

    public IRequest getRequest() {
        return request;
    }

    public ValidatorMessageEnum validate() throws RuntimeException {
        throw new NotSupportedMethodValidationException();
    }

}
