package pl.prasny.api.http;

/**
 * interfejs umożliwiający dostęp do implementacji serwera HTTP
 */
public interface IHttpPort {
    /**
     * Dodanie obiekt implementującego interfejs IRoute do zbioru routów zawierających endpoind i handlery
     * @param route obiekt routingu dziedziczy z interfejsu IRoute
     */
    void addRoute(IRoute route);

    /**
     * Usunięcie obiektu implementującego interfejs IRoute ze zbioru routów zawierających endpoind i handlery
     * @param route obiekt routingu dziedziczy z interfejsu IRoute
     */
    void removeRoute(IRoute route);

    /**
     * Uruchom serwer
     */
    void startServer();

    /**
     * Zatrzymaj działanie serwera
     */
    void stopServer();
}
