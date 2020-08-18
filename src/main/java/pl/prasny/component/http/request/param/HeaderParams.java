package pl.prasny.component.http.request.param;

import pl.prasny.api.http.IRequestParams;

import java.util.Map;

public class HeaderParams extends AbstractRequestParams implements IRequestParams {

    public HeaderParams(Map<String, String> map) {
        super(map);
    }
}
