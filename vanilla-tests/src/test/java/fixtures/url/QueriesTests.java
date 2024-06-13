package fixtures.url;

import fixtures.url.models.ErrorException;
import fixtures.url.models.UriColor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueriesTests {
    private static AutoRestUrlTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestUrlTestServiceBuilder().globalStringPath("global").buildClient();
    }

    @Test
    public void getBooleanTrue() {
        client.getQueries().getBooleanTrue();
    }

    @Test
    public void getBooleanFalse() {
        client.getQueries().getBooleanFalse();
    }

    @Test
    public void getBooleanNull() {
        client.getQueries().getBooleanNull(null);
    }

    @Test
    public void getIntOneMillion() {
        client.getQueries().getIntOneMillion();
    }

    @Test
    public void getIntNegativeOneMillion() {
        client.getQueries().getIntNegativeOneMillion();
    }

    @Test
    public void getIntNull() {
        client.getQueries().getIntNull(null);
    }

    @Test
    public void getTenBillion() {
        client.getQueries().getTenBillion();
    }

    @Test
    public void getNegativeTenBillion() {
        client.getQueries().getNegativeTenBillion();
    }

    @Test
    public void getLongNull() {
        client.getQueries().getLongNull(null);
    }

    @Test
    public void floatScientificPositive() {
        client.getQueries().floatScientificPositive();
    }

    @Test
    public void floatScientificNegative() {
        client.getQueries().floatScientificNegative();
    }

    @Test
    public void floatNull() {
        client.getQueries().floatNull(null);
    }

    @Test
    public void doubleDecimalPositive() {
        client.getQueries().doubleDecimalPositive();
    }

    @Test
    public void doubleDecimalNegative() {
        client.getQueries().doubleDecimalNegative();
    }

    @Test
    public void doubleNull() {
        client.getQueries().doubleNull(null);
    }

    @Test
    public void stringUnicode() {
        client.getQueries().stringUnicode();
    }

    @Test
    public void stringUrlEncoded() {
        client.getQueries().stringUrlEncoded();
    }

    @Test
    public void stringEmpty() {
        client.getQueries().stringEmpty();
    }

    @Disabled("Tests for required parameter but SwaggerMethodParser doesn't have that concept")
    @Test
    public void stringNull() {
        ErrorException exception = assertThrows(ErrorException.class, () -> client.getQueries().stringNull(null));
        assertTrue(exception.getMessage().contains("Parameter stringPath is required"));
    }

    @Test
    public void enumValid() {
        client.getQueries().enumValid(UriColor.GREEN_COLOR);
    }

    @Disabled("Tests for required parameter but SwaggerMethodParser doesn't have that concept")
    @Test
    public void enumNull() {
        ErrorException exception = assertThrows(ErrorException.class, () -> client.getQueries().enumNull(null));
        assertTrue(exception.getMessage().contains("Parameter enumPath is required"));
    }

    @Test
    public void byteMultiByte() {
        client.getQueries().byteMultiByte("啊齄丂狛狜隣郎隣兀﨩".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void byteEmpty() {
        client.getQueries().byteEmpty();
    }

    @Disabled("Tests for required parameter but SwaggerMethodParser doesn't have that concept")
    @Test
    public void byteNull() {
        ErrorException exception = assertThrows(ErrorException.class, () -> client.getQueries().byteNull(null));
        assertTrue(exception.getMessage().contains("Parameter bytePath is required"));
    }

    @Test
    public void dateValid() {
        client.getQueries().dateValid();
    }

    @Disabled("Tests for required parameter but SwaggerMethodParser doesn't have that concept")
    @Test
    public void dateNull() {
        ErrorException exception = assertThrows(ErrorException.class, () -> client.getQueries().dateNull(null));
        assertTrue(exception.getMessage().contains("Parameter datePath is required"));
    }

    @Test
    public void dateTimeValid() {
        client.getQueries().dateTimeValid();
    }

    @Disabled("Tests for required parameter but SwaggerMethodParser doesn't have that concept")
    @Test
    public void dateTimeNull() {
        ErrorException exception = assertThrows(ErrorException.class, () -> client.getQueries().dateTimeNull(null));
        assertTrue(exception.getMessage().contains("Parameter dateTimePath is required"));
    }

    @Test
    public void arrayStringCsvValid() {
        List<String> query = new ArrayList<>();
        query.add("ArrayQuery1");
        query.add("begin!*'();:@ &=+$,/?#[]end");
        query.add(null);
        query.add("");
        client.getQueries().arrayStringCsvValid(query);
    }

    @Test
    public void arrayStringCsvNull() {
        client.getQueries().arrayStringCsvNull(null);
    }

    @Test
    public void arrayStringCsvEmpty() {
        client.getQueries().arrayStringCsvEmpty(new ArrayList<>());
    }

    @Test
    public void arrayStringSsvValid() {
        List<String> query = new ArrayList<>();
        query.add("ArrayQuery1");
        query.add("begin!*'();:@ &=+$,/?#[]end");
        query.add(null);
        query.add("");
        client.getQueries().arrayStringSsvValid(query);
    }

    @Test
    public void arrayStringTsvValid() {
        List<String> query = new ArrayList<>();
        query.add("ArrayQuery1");
        query.add("begin!*'();:@ &=+$,/?#[]end");
        query.add(null);
        query.add("");
        client.getQueries().arrayStringTsvValid(query);
    }

    @Test
    public void arrayStringPipesValid() {
        List<String> query = new ArrayList<>();
        query.add("ArrayQuery1");
        query.add("begin!*'();:@ &=+$,/?#[]end");
        query.add(null);
        query.add("");
        client.getQueries().arrayStringPipesValid(query);
    }
}
