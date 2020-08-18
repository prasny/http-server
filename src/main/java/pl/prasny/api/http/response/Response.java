package pl.prasny.api.http.response;

import pl.prasny.api.http.IHttpPortHandler;
import pl.prasny.api.http.IRequest;
import pl.prasny.api.http.IResponse;
import pl.prasny.api.http.type.HeaderContentTypeValuesEnum;
import pl.prasny.api.http.type.HttpStatus;

/**
 * Domyślna implementacja odpowiedzi zwracany przez {@link IHttpPortHandler#invoke(IRequest)}
 */
public class Response implements IResponse {

    private String body;
    private HeaderContentTypeValuesEnum contentType;
    private HttpStatus status;

    public Response(String body) {
        this(body, HeaderContentTypeValuesEnum.TEXT_PLAIN, HttpStatus.OK);
    }

    public Response(String body, HttpStatus status) {
        this(body, HeaderContentTypeValuesEnum.TEXT_PLAIN, status);
    }

    public Response(String body, HeaderContentTypeValuesEnum contentType, HttpStatus status) {
        this.body = body;
        this.contentType = contentType;
        this.status = status;
    }

    @Override
    public HeaderContentTypeValuesEnum getContentType() {
        return contentType;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public HttpStatus status() {
        return status;
    }

}
