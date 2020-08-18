package pl.prasny.component.http.request.validator.impl;

import pl.prasny.api.http.IRequest;
import pl.prasny.api.http.exception.validator.BodyNotPresentInPostRequestValidationException;
import pl.prasny.component.http.request.validator.AbstractValidator;
import pl.prasny.component.http.request.validator.IValidator;
import pl.prasny.component.http.type.ValidatorMessageEnum;

/**
 * walidator sprawdzający czy w requeście POST znajduje się jakiekolwiek body (bodyParams czy czyste body)
 */
public class IsBodyPresentInPostRequestValidator extends AbstractValidator implements IValidator {
    public IsBodyPresentInPostRequestValidator(IRequest request) {
        super(request);
    }

    @Override
    public ValidatorMessageEnum validate() {
        boolean isBodyEmpty = getRequest().getBody().isEmpty();
        boolean isBodyParamsEmpty = getRequest().getBodyParams().isEmpty();

        if (isBodyEmpty && isBodyParamsEmpty){
            throw new BodyNotPresentInPostRequestValidationException();
        }
        return null;
    }
}
