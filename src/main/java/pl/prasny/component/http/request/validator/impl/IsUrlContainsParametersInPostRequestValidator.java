package pl.prasny.component.http.request.validator.impl;

import pl.prasny.api.http.IRequest;
import pl.prasny.component.http.request.validator.AbstractValidator;
import pl.prasny.component.http.request.validator.IValidator;
import pl.prasny.component.http.type.ValidatorMessageEnum;

/**
 * walidator sprawdzający czy w url request są parametry
 */
public class IsUrlContainsParametersInPostRequestValidator extends AbstractValidator implements IValidator {

    public IsUrlContainsParametersInPostRequestValidator(IRequest request) {
        super(request);
    }

    @Override
    public ValidatorMessageEnum validate() {
        boolean urlParamsExists = !getRequest().getUrlParams().isEmpty();
        if (urlParamsExists){
            return ValidatorMessageEnum.URL_CONTAINS_PARAMETERS_IN_POST_METHOD;
        } else {
            return null;
        }
    }
}
