package pl.prasny.api.http;

import pl.prasny.api.http.type.HttpRequestMethodEnum;

/**
 * Interfejs który implementują obiekty typu Request na które przerabiane są żadania HTTP
 */
public interface IRequest {
    /**
     * Zwraca BodyParam obiektu Request
     * @return obiekt BodyParams implementujący interfejs IRequestParams
     */
    IRequestParams getBodyParams();

    /**
     * Zwraca UrlParams obiektu Request
     * @return obiekt UrlParams implementujący interfejs IRequestParams
     */
    IRequestParams getUrlParams();

    /**
     * Zwraca HeaderParams obiektu Request
     * @return obiekt HeaderParams implementujący interfejs IRequestParams
     */
    IRequestParams getHeaderParams();

    /**
     * Zwraca Body obiektu Request
     * @return ciało żądania
     */
    String getBody();

    /**
     * Zwraca wartość enum HttpRequestMethodEnum określający metodę żądania obiektu Request
     * @return metoda żądania określona w klasie enum HttpRequestMethodEnum
     */
    HttpRequestMethodEnum getMethod();

    /**
     * Przypisuje @param do zmiennej BodyParam obiektu Request
     * @param bodyParams obiekt BodyParams implementujący interfejs IRequestParams
     */
    void setBodyParams(IRequestParams bodyParams);

    /**
     * Przypisuje @param do zmiennej UrlParams obiektu Request
     * @param urlParams obiekt UrlParams implementujący interfejs IRequestParams
     */
    void setUrlParams(IRequestParams urlParams);

    /**
     * Przypisuje @param do zmiennej HeadersParam obiektu Request
     * @param headerParams obiekt HeaderParams implementujący interfejs IRequestParams
     */
    void setHeaderParams(IRequestParams headerParams);

    /**
     * Przypisuje @param do zmiennej body obiektu Request
     * @param body ciało  żądania
     */
    void setBody(String body);
}
