package pl.prasny.component.http.utils;

import com.sun.net.httpserver.Headers;
import org.apache.commons.lang3.StringUtils;
import pl.prasny.component.http.request.param.HeaderParams;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

//TODO sprawdzić możliwość zastosowania biblioteki apache StrinUtils do konwersji Stringów

public class ParamsHelper {
    public static Map<String, String> convertUriQueryToMap(URI uri) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new HashMap<>();
        if (uri.getQuery() != null) {
            String query = uri.getQuery();
            query_pairs = convertStringQueryToMap(query);
        }
        return query_pairs;
    }

    public static Map<String, String> convertStringQueryToMap(String query) {
        Map<String, String> query_pairs = new HashMap<>();
        if (!"".equals(query) && query.contains("=")) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8), URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8));
            }
        }
        return query_pairs;
    }

    public static HeaderParams convertHeadersToHeaderParams(Headers headers) {
        //convert Headers to Map<String,String>
        Set<String> keys = headers.keySet();
        Map<String, String> convertedMap = new HashMap<>();
        for (String key : keys) {
            convertedMap.put(convertEveryFirstWordsLetterWithDashSeparatorToUpperCase(key), headers.getFirst(key));
        }

        return new HeaderParams(convertedMap);
    }

    public static String convertEveryFirstWordsLetterWithDashSeparatorToUpperCase(String input) {
        String outputString = "";
        //safety
        if (StringUtils.isNotEmpty(input)) {
            //split input into separate words
            String[] arrayOfWords = input.split("-");
            //set first letter of every word uppercase
            for (String word : arrayOfWords) {
                String firstUpperCaseWord = word.substring(0, 1).toUpperCase() + word.substring(1);
                if ("".equals(outputString)) {
                    outputString = firstUpperCaseWord;
                } else {
                    outputString = outputString + "-" + firstUpperCaseWord;
                }
            }
        }
        return outputString;
    }
}
