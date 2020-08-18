package pl.prasny.api.http;

import pl.prasny.api.http.response.Response;
import pl.prasny.api.http.type.HeaderContentTypeValuesEnum;
import pl.prasny.api.http.type.HttpStatus;

/**
 * Interfejs klas odpowiedzi na żądanie HTTP
 *
 * Interfejs występuje w handler'ach {@link IHttpPortHandler#invoke(IRequest)}.
 * Podstawową implementacją interfejsu jest klasa {@link Response}.
 */
@SuppressWarnings("unused")
public interface IResponse {
    /**
     * Zwraca typ zawartości obiektu Response określony w enum'ie HeaderContentTypeValuesEnum
     * @return wartość enum z klasy HeaderContentTypeValueEnum
     */
    HeaderContentTypeValuesEnum getContentType();

    /**
     * Zwraca ciało odpowiedzi HTTP
     * @return ciało odpowiedzi HTTP
     */
    String getBody();

    /**
     * Status odpowiedzi HTTP
     * @return status odpowiedzi HTTP wg predefiniowanej listy
     */
    HttpStatus status();
}
