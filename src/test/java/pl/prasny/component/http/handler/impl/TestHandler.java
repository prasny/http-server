package pl.prasny.component.http.handler.impl;

import pl.prasny.api.http.IHttpPortHandler;
import pl.prasny.api.http.IRequest;
import pl.prasny.api.http.IRequestParams;
import pl.prasny.api.http.IResponse;
import pl.prasny.api.http.response.Response;

import java.util.Set;

public class TestHandler implements IHttpPortHandler {
    public IResponse invoke(IRequest request) {
        String responseBody = "";

        responseBody = responseBody + "UrlParams: " + returnStringOfKeysAndValues(request.getUrlParams());
        responseBody = responseBody + "BodyParams: " + returnStringOfKeysAndValues(request.getBodyParams());
        //responseBody = responseBody + "HeaderParams: " + returnStringOfKeysAndValues(request.getHeaderParams());
        responseBody = responseBody + "Body: " + "\n" + request.getBody();

        return new Response(responseBody);
    }

    private String returnStringOfKeysAndValues(IRequestParams params){
        String returnString = "\n";

        Set<String> keys = params.getKeys();

        for (String key: keys) {
            returnString = " " + returnString + " " + key + "=" + params.getValue(key) + "\n";
        }

        return returnString + "\n";
    }
}
