package pl.prasny.api.http;

/**
 * Interfejs który implementują obiekty wchodządze w skład reguł routingu całego serwera.
 * Obiekt implementujący zawiera endpoint i handler implementujący interfejs IHttpPortHandler
 */
public interface IRoute {
    /**
     * Zwraca string endpointa którego wywłowanie uruchomi metodę invoke w IHttpPortHandler
     * @return endpoint danego routa
     */
    String getEndpoint();

    /**
     * Zwraca obiekt implementujący IHttpPortHandler zawierający w sobie metodę invoke wykonywaną
     * po wywołaniu endpointa przypisanego do handlera w obiekcie IRoute
     * @return obiekt implementujący IHttpPortHandler
     */
    IHttpPortHandler getHandler();
}
