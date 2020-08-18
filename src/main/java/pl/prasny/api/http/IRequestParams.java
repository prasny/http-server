package pl.prasny.api.http;

import java.util.Set;

/**
 * Interfejs który implementują wszystkie obiekty typu "Param" obiektu Request (implementującego IRequest)
 */
public interface IRequestParams {
    /**
     * Zwraca wartość typu String parametru  dla podanego @param klucza
     * @param key klucz żądanej wartości
     * @return wartość parametru dla podanego klucza
     */
    String getValue(String key);

    /**
     * Zwraca zbiór wszustkich kluczy parametrów
     * @return zbiór kluczy
     */
    Set<String> getKeys();

    /**
     * Zwraca true jeśli zbiór parametrów jest pusty, false jeśli zawiera przynajmniej jeden element
     * @return wartość boolean
     */
    boolean isEmpty();
}
