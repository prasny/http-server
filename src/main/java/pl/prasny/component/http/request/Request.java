package pl.prasny.component.http.request;

import pl.prasny.api.http.IRequest;
import pl.prasny.api.http.IRequestParams;
import pl.prasny.api.http.type.HttpRequestMethodEnum;

/**
 * Obiekt w implementacji PlainJavaHttp reprezentujący żądanie http.
 * - walidacja odbywa się na podstawie tego obiektu
 * - przekazywany jest do metody invoke handlera
 */
public class Request implements IRequest {
    private IRequestParams bodyParams;
    private IRequestParams urlParams;
    private IRequestParams headerParams;
    private String body;
    private HttpRequestMethodEnum method;

    public Request(IRequestParams bodyParams, IRequestParams urlParams, IRequestParams headerParams, String body, HttpRequestMethodEnum method) {
        this.bodyParams = bodyParams;
        this.urlParams = urlParams;
        this.headerParams = headerParams;
        this.body = body;
        this.method = method;
    }

    @Override
    public IRequestParams getBodyParams() {
        return bodyParams;
    }

    @Override
    public IRequestParams getUrlParams() {
        return urlParams;
    }

    @Override
    public IRequestParams getHeaderParams() {
        return headerParams;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public void setBodyParams(IRequestParams bodyParams) {
        this.bodyParams = bodyParams;
    }

    @Override
    public void setUrlParams(IRequestParams urlParams) {
        this.urlParams = urlParams;
    }

    @Override
    public void setHeaderParams(IRequestParams headerParams) {
        this.headerParams = headerParams;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public HttpRequestMethodEnum getMethod() {
        return method;
    }
}
