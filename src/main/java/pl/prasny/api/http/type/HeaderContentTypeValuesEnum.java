package pl.prasny.api.http.type;

/**
 * zbiór wartości kontent type dla body requesta wypisany zgodnie ze standardem (małe litery)
 */
public enum HeaderContentTypeValuesEnum {
    JSON("application/json"),
    X_WEB_FORM_URLENCODED("application/x-www-form-urlencoded"),
    TEXT_PLAIN("text/plain");

    private String value;

    HeaderContentTypeValuesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
