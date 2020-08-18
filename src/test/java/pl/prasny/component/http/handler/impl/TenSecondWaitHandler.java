package pl.prasny.component.http.handler.impl;

import pl.prasny.api.http.IHttpPortHandler;
import pl.prasny.api.http.IRequest;
import pl.prasny.api.http.IResponse;
import pl.prasny.api.http.response.Response;

import static java.lang.System.currentTimeMillis;

public class TenSecondWaitHandler implements IHttpPortHandler {
    public IResponse invoke(IRequest request) {
        long startTime = currentTimeMillis();
        long timeToWait = 10000;

        while(currentTimeMillis() - startTime < timeToWait){
            String waitingString = "";
        }
        System.out.println("invoking ten seconds wait handler");

        return new Response("TenSecondWaitHandler");
    }
}
