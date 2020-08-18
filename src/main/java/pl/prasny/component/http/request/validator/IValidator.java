package pl.prasny.component.http.request.validator;

import pl.prasny.component.http.type.ValidatorMessageEnum;

/**
 * Interfejs który implementują walidatory implementacji PlainJavaHttp
 */
public interface IValidator {
    /**
     * Metoda zawierająca logikę walidacji
     * @return wiadomość typu enum ValidatorMessageEnum jeśli metoda validate chce przekazać warning,
     *          jeśli metoda validate nie przekazuje warningu zwraca null;
     */
    ValidatorMessageEnum validate();
}
