package fixtures.url;

import fixtures.url.models.UriColor;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.time.OffsetDateTime;

public class PathsTests {
    private static AutoRestUrlTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestUrlTestServiceBuilder().buildClient();
    }

    @Test
    public void getBooleanTrue() throws Exception {
        client.getPaths().getBooleanTrue();
    }

    @Test
    public void getBooleanFalse() throws Exception {
        client.getPaths().getBooleanFalse();
    }

    @Test
    public void getIntOneMillion() throws Exception {
        client.getPaths().getIntOneMillion();
    }

    @Test
    public void getIntNegativeOneMillion() throws Exception {
        client.getPaths().getIntNegativeOneMillion();
    }

    @Test
    public void getTenBillion() throws Exception {
        client.getPaths().getTenBillion();
    }

    @Test
    public void getNegativeTenBillion() throws Exception {
        client.getPaths().getNegativeTenBillion();
    }

    @Test
    public void floatScientificPositive() throws Exception {
        client.getPaths().floatScientificPositive();
    }

    @Test
    public void floatScientificNegative() throws Exception {
        client.getPaths().floatScientificNegative();
    }

    @Test
    public void doubleDecimalPositive() throws Exception {
        client.getPaths().doubleDecimalPositive();
    }

    @Test
    public void doubleDecimalNegative() throws Exception {
        client.getPaths().doubleDecimalNegative();
    }

    @Test
    public void stringUrlEncoded() throws Exception {
        client.getPaths().stringUrlEncoded();
    }

    @Test
    public void stringEmpty() throws Exception {
        client.getPaths().stringEmpty();
    }

    @Ignore("Client side validation")
    public void stringNull() throws Exception {
        try {
            client.getPaths().stringNull(null);
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter stringPath is required"));
        }
    }

    @Test
    public void enumValid() throws Exception {
        client.getPaths().enumValid(UriColor.GREEN_COLOR);
    }

    @Test
    public void enumNull() throws Exception {
        try {
            client.getPaths().enumNull(null);
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter enumPath is required"));
        }
    }

    @Test
    public void byteMultiByte() throws Exception {
        client.getPaths().byteMultiByte("啊齄丂狛狜隣郎隣兀﨩".getBytes("UTF-8"));
    }

    @Test
    public void byteEmpty() throws Exception {
        client.getPaths().byteEmpty();
    }

    @Test
    public void byteNull() throws Exception {
        try {
            client.getPaths().byteNull(null);
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter bytePath is required"));
        }
    }

    @Test
    public void dateValid() throws Exception {
        client.getPaths().dateValid();
    }

    @Test
    public void dateNull() throws Exception {
        try {
            client.getPaths().dateNull(null);
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter datePath is required"));
        }
    }

    @Test
    public void dateTimeValid() throws Exception {
        client.getPaths().dateTimeValid();
    }

    @Test
    public void dateTimeNull() throws Exception {
        try {
            client.getPaths().dateTimeNull(null);
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter dateTimePath is required"));
        }
    }

    @Test
    public void base64Url() throws Exception {
        client.getPaths().base64Url("lorem".getBytes());
    }

    /*
    @Test
    public void arrayCsvInPath() throws Exception {
        List<String> arrayPath = new ArrayList<>();
        arrayPath.add("ArrayPath1");
        arrayPath.add("begin!*'();:@ &=+$,/?#[]end");
        arrayPath.add(null);
        arrayPath.add("");
        client.getPathsOperations().arrayCsvInPath(arrayPath);
    }
    */

    @Test
    public void unixTimeUrl() throws Exception {
        client.getPaths().unixTimeUrl(OffsetDateTime.parse("2016-04-13T00:00:00Z"));
    }
}
