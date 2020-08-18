package pl.prasny.component.http.handler.impl;

import pl.prasny.api.http.IHttpPortHandler;
import pl.prasny.api.http.IRequest;
import pl.prasny.api.http.IResponse;

public class EmptyResponseHandler implements IHttpPortHandler {
    public IResponse invoke(IRequest request) {
        return null;
    }
}
