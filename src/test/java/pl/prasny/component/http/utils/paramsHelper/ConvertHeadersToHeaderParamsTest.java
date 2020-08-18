package pl.prasny.component.http.utils.paramsHelper;

import com.sun.net.httpserver.Headers;
import org.junit.Test;
import pl.prasny.component.http.request.param.HeaderParams;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static pl.prasny.component.http.utils.ParamsHelper.convertHeadersToHeaderParams;

public class ConvertHeadersToHeaderParamsTest {

    private Headers headers = new Headers();
    private Map<String,String> headerParamsMap = new HashMap<>();
    private HeaderParams headerParams;

    @Test
    public void shouldReturnProperHeaderParams(){
        headers.add("Content-Type", "application/json");
        headers.add("Host", "www.w3.org");
        headers.add("Content-Length", "1");
        headers.add("Server", "Apache/2.0.50");

        headerParamsMap.put("Content-Type", "application/json");
        headerParamsMap.put("Host", "www.w3.org");
        headerParamsMap.put("Content-Length", "1");
        headerParamsMap.put("Server", "Apache/2.0.50");

        headerParams = new HeaderParams(headerParamsMap);

        HeaderParams headerParamsFromFunction = convertHeadersToHeaderParams(headers);

        //check if maps are equal
        assertEquals(headerParamsFromFunction.getKeys(), headerParams.getKeys());
        assertEquals(headerParamsFromFunction.getValue("Content-Type"), headerParams.getValue("Content-Type"));
        assertEquals(headerParamsFromFunction.getValue("Host"), headerParams.getValue("Host"));
        assertEquals(headerParamsFromFunction.getValue("Content-Length"), headerParams.getValue("Content-Length"));
        assertEquals(headerParamsFromFunction.getValue("Server"), headerParams.getValue("Server"));
    }
}
