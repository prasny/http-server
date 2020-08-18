package pl.prasny.component.http.utils.paramsHelper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConvertEveryFirstWordsWithDashSeparatorLetterToUpperCaseTest {

    private String string;

    @Test
    public void shouldReturnEveryWordUpperCaseString() {
        string = "this-is_an-example-String";

        assertEquals("This-Is_an-Example-String", pl.prasny.component.http.utils.ParamsHelper.convertEveryFirstWordsLetterWithDashSeparatorToUpperCase(string));
    }

    @Test
    public void shouldReturnEmptyStringWhenInputStringIsEmpty(){
        string = "";

        assertEquals("", pl.prasny.component.http.utils.ParamsHelper.convertEveryFirstWordsLetterWithDashSeparatorToUpperCase(string));
    }
}
