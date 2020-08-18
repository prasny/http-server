package pl.prasny.component.http.type;

/**
 * Zbiór nazw header'ów wykorzystywanych w implementacji
 *
 * Poniższa lista zawiera nazwy header'ów zgodnie ze standardem (pierwsze duże litery oddzielone myślnikiem)
 */
public enum HeaderTypesValuesEnum {
    CONTENT_TYPE("Content-Type");

    private String value;

    HeaderTypesValuesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
