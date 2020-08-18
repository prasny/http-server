package pl.prasny.component.http.handler.impl;

import pl.prasny.api.http.IHttpPortHandler;
import pl.prasny.api.http.IRequest;
import pl.prasny.api.http.IResponse;
import pl.prasny.api.http.response.Response;

public class WarningHandler implements IHttpPortHandler {
    public IResponse invoke(IRequest request) {
        System.out.println("invoking warning router ");
        return new Response("WarningHandler");
    }
}
