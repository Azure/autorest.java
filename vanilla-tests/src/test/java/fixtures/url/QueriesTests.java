package fixtures.url;

import fixtures.url.models.ErrorException;
import fixtures.url.models.UriColor;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QueriesTests {
    private static AutoRestUrlTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestUrlTestServiceBuilder().buildClient();
    }

    @Test
    public void getBooleanTrue() throws Exception {
        client.getQueries().getBooleanTrue();
    }

    @Test
    public void getBooleanFalse() throws Exception {
        client.getQueries().getBooleanFalse();
    }

    @Test
    public void getBooleanNull() throws Exception {
        client.getQueries().getBooleanNull(null);
    }

    @Test
    public void getIntOneMillion() throws Exception {
        client.getQueries().getIntOneMillion();
    }

    @Test
    public void getIntNegativeOneMillion() throws Exception {
        client.getQueries().getIntNegativeOneMillion();
    }

    @Test
    public void getIntNull() throws Exception {
        client.getQueries().getIntNull(null);
    }

    @Test
    public void getTenBillion() throws Exception {
        client.getQueries().getTenBillion();
    }

    @Test
    public void getNegativeTenBillion() throws Exception {
        client.getQueries().getNegativeTenBillion();
    }

    @Test
    public void getLongNull() throws Exception {
        client.getQueries().getLongNull(null);
    }

    @Test
    public void floatScientificPositive() throws Exception {
        client.getQueries().floatScientificPositive();
    }

    @Test
    public void floatScientificNegative() throws Exception {
        client.getQueries().floatScientificNegative();
    }

    @Test
    public void floatNull() throws Exception {
        client.getQueries().floatNull(null);
    }

    @Test
    public void doubleDecimalPositive() throws Exception {
        client.getQueries().doubleDecimalPositive();
    }

    @Test
    public void doubleDecimalNegative() throws Exception {
        client.getQueries().doubleDecimalNegative();
    }

    @Test
    public void doubleNull() throws Exception {
        client.getQueries().doubleNull(null);
    }

    @Test
    public void stringUnicode() throws Exception {
        client.getQueries().stringUnicode();
    }

    @Test
    public void stringUrlEncoded() throws Exception {
        client.getQueries().stringUrlEncoded();
    }

    @Test
    public void stringEmpty() throws Exception {
        client.getQueries().stringEmpty();
    }

    @Test
    public void stringNull() throws Exception {
        try {
            client.getQueries().stringNull(null);
        } catch (ErrorException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter stringPath is required"));
        }
    }

    @Test
    public void enumValid() throws Exception {
        client.getQueries().enumValid(UriColor.GREEN_COLOR);
    }

    @Test
    public void enumNull() throws Exception {
        try {
            client.getQueries().enumNull(null);
        } catch (ErrorException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter enumPath is required"));
        }
    }

    @Test
    public void byteMultiByte() throws Exception {
        client.getQueries().byteMultiByte("啊齄丂狛狜隣郎隣兀﨩".getBytes("UTF-8"));
    }

    @Test
    public void byteEmpty() throws Exception {
        client.getQueries().byteEmpty();
    }

    @Test
    public void byteNull() throws Exception {
        try {
            client.getQueries().byteNull(null);
        } catch (ErrorException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter bytePath is required"));
        }
    }

    @Test
    public void dateValid() throws Exception {
        client.getQueries().dateValid();
    }

    @Test
    public void dateNull() throws Exception {
        try {
            client.getQueries().dateNull(null);
        } catch (ErrorException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter datePath is required"));
        }
    }

    @Test
    public void dateTimeValid() throws Exception {
        client.getQueries().dateTimeValid();
    }

    @Test
    public void dateTimeNull() throws Exception {
        try {
            client.getQueries().dateTimeNull(null);
        } catch (ErrorException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter dateTimePath is required"));
        }
    }

    @Test
    public void arrayStringCsvValid() throws Exception {
        List<String> query = new ArrayList<>();
        query.add("ArrayQuery1");
        query.add("begin!*'();:@ &=+$,/?#[]end");
        query.add(null);
        query.add("");
        client.getQueries().arrayStringCsvValid(query);
    }

    @Test
    public void arrayStringCsvNull() throws Exception {
        client.getQueries().arrayStringCsvNull(null);
    }

    @Test
    public void arrayStringCsvEmpty() throws Exception {
        client.getQueries().arrayStringCsvEmpty(new ArrayList<String>());
    }

    @Test
    public void arrayStringSsvValid() throws Exception {
        List<String> query = new ArrayList<>();
        query.add("ArrayQuery1");
        query.add("begin!*'();:@ &=+$,/?#[]end");
        query.add(null);
        query.add("");
        client.getQueries().arrayStringSsvValid(query);
    }

    @Test
    public void arrayStringTsvValid() throws Exception {
        List<String> query = new ArrayList<>();
        query.add("ArrayQuery1");
        query.add("begin!*'();:@ &=+$,/?#[]end");
        query.add(null);
        query.add("");
        client.getQueries().arrayStringTsvValid(query);
    }

    @Test
    public void arrayStringPipesValid() throws Exception {
        List<String> query = new ArrayList<>();
        query.add("ArrayQuery1");
        query.add("begin!*'();:@ &=+$,/?#[]end");
        query.add(null);
        query.add("");
        client.getQueries().arrayStringPipesValid(query);
    }
}
