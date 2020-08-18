package pl.prasny.component.http.request.param;

import pl.prasny.api.http.IRequestParams;

import java.util.Map;

public class BodyParams extends AbstractRequestParams implements IRequestParams {

    public BodyParams(Map<String, String> map) {
        super(map);
    }
}
