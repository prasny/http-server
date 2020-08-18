package pl.prasny.component.http.utils.paramsHelper;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URI;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ConvertUriQueryToMapTest {

    private URI uri;
    private Map<String, String> map = new HashMap<>();

    @Test
    public void shouldReturnCorrectParametersMapFromCorrectUri() throws UnsupportedEncodingException, URISyntaxException {
        map.put("param1", "value1");
        map.put("param2", "value2");
        uri = new URI("http://localhost/folder1/site1?param1=value1&param2=value2");

        assertEquals(map, pl.prasny.component.http.utils.ParamsHelper.convertUriQueryToMap(uri));
    }

    @Test
    public void shouldReturnEmptyMapFromEmptyParametersUri () throws URISyntaxException, UnsupportedEncodingException {
        uri = new URI("http://localhost/folder1/site1");

        assertEquals(map, pl.prasny.component.http.utils.ParamsHelper.convertUriQueryToMap(uri));
    }

    @Test
    public void shouldReturnEmptyMapFromEmptyUri () throws URISyntaxException, UnsupportedEncodingException {
        uri = new URI("");

        assertEquals(map, pl.prasny.component.http.utils.ParamsHelper.convertUriQueryToMap(uri));
    }
}
