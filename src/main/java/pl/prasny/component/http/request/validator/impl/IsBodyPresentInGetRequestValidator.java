package pl.prasny.component.http.request.validator.impl;

import pl.prasny.api.http.IRequest;
import pl.prasny.component.http.request.validator.AbstractValidator;
import pl.prasny.component.http.request.validator.IValidator;
import pl.prasny.component.http.type.ValidatorMessageEnum;

/**
 * walidator sprawdzający czy w requeście GET znajduje się jakiekolwiek body (bodyParams czy czyste body)
 */
public class IsBodyPresentInGetRequestValidator extends AbstractValidator implements IValidator {
    public IsBodyPresentInGetRequestValidator(IRequest request) {
        super(request);
    }

    @Override
    public ValidatorMessageEnum validate() {
        boolean isBodyEmpty = getRequest().getBody().isEmpty();
        boolean isBodyParamsEmpty = getRequest().getBodyParams().isEmpty();

        if (!isBodyEmpty ||!isBodyParamsEmpty){
            return ValidatorMessageEnum.BODY_PRESENT_IN_GET_METHOD;
        } else {
            return null;
        }
    }
}
