package pl.prasny.component.http.request.validator.impl;

import pl.prasny.api.http.IRequest;
import pl.prasny.api.http.exception.validator.ContentTypeNotPresentValidationException;
import pl.prasny.component.http.request.validator.AbstractValidator;
import pl.prasny.component.http.request.validator.IValidator;
import pl.prasny.component.http.type.HeaderTypesValuesEnum;
import pl.prasny.component.http.type.ValidatorMessageEnum;

/**
 * walidator sprawdzający czy w request istenieje header Content-Type
 */
public class IsContentTypeHeaderPresent extends AbstractValidator implements IValidator {
    public IsContentTypeHeaderPresent(IRequest request) {
        super(request);
    }

    @Override
    public ValidatorMessageEnum validate() {
        String contentTypeName = HeaderTypesValuesEnum.CONTENT_TYPE.getValue();
        boolean isContentTypeHeaderPresent = getRequest().getHeaderParams().getKeys().contains(contentTypeName);

        if(!isContentTypeHeaderPresent){
            throw new ContentTypeNotPresentValidationException();
        } else {
            return null;
        }
    }
}
