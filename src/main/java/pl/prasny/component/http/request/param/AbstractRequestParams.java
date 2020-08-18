package pl.prasny.component.http.request.param;

import pl.prasny.api.http.IRequestParams;

import java.util.Map;
import java.util.Set;

/**
 * Klasa rodzic dla każdego obiektu typu Params w obiektcie Request
 */
public abstract class AbstractRequestParams implements IRequestParams {

    private Map<String, String> map;

    public AbstractRequestParams(Map<String, String> map) {
        this.map = map;
    }

    public String getValue(String key) {
        return map.get(key);
    }

    public Set<String> getKeys() {
        return map.keySet();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }
}
