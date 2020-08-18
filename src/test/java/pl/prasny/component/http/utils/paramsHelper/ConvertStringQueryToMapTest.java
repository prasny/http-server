package pl.prasny.component.http.utils.paramsHelper;

import org.junit.Test;
import pl.prasny.component.http.utils.ParamsHelper;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ConvertStringQueryToMapTest {

    private String query;
    private Map<String, String> map = new HashMap<>();

    @Test
    public void shouldReturnCorrectParametersMapFromCorrectString() throws UnsupportedEncodingException {
        map.put("param1", "value1");
        map.put("param2", "value2");
        query = "param1=value1&param2=value2";

        assertEquals(map, ParamsHelper.convertStringQueryToMap(query));
    }

    @Test
    public void shouldReturnEmptyMapFromEmptyParametersString() throws  UnsupportedEncodingException {
        query = "";

        assertEquals(map, ParamsHelper.convertStringQueryToMap(query));
    }

    @Test
    public void shouldReturnEmptyMapFromStringWithoutParameters() throws UnsupportedEncodingException {
        query = "some_sample_stringWithoutParameters!!!!!";

        assertEquals(map, ParamsHelper.convertStringQueryToMap(query));
    }
}
