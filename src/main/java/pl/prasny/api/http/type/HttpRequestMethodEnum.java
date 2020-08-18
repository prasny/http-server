package pl.prasny.api.http.type;

/**
 * Lista typów żądań HTTP utworzona celem unifikacji nazw (duże litery)
 */
@SuppressWarnings("unused")
public enum HttpRequestMethodEnum {

    POST("POST"),
    GET("GET"),
    PUT("PUT"),
    HEAD("HEAD"),
    CONNECT("CONNECT"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE"),
    PATH("PATH"),
    DELETE("DELETE");

    private String value;

    HttpRequestMethodEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
