package pl.prasny.api.http;

/**
 * Interfejs implementowany przez każdy handler wywoływany na danym endpoincie
 */
public interface IHttpPortHandler {
    /**
     * Metoda zawiera logikę wykonywaną po wywołaniu endpointa określonego w IRoute w którym handler ten jest zawarty
     *
     * @param request obiekt implementujący interfejs IRequest utworzony przez implementację serwera
     *               po otrzymaniu żądania (często również walidowany)
     * @return obiekt odpowiedzi metody invoke implementujący interfejs IResponse
     */
    IResponse invoke(IRequest request);
}
