package pl.prasny.component.http.request.param;

import pl.prasny.api.http.IRequestParams;

import java.util.Map;

public class UrlParams extends AbstractRequestParams implements IRequestParams {

    public UrlParams(Map<String, String> map) {
        super(map);
    }
}
