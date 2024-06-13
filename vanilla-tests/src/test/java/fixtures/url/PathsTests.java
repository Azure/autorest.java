package fixtures.url;

import fixtures.url.models.UriColor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathsTests {
    private static AutoRestUrlTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestUrlTestServiceBuilder().globalStringPath("global").buildClient();
    }

    @Test
    public void getBooleanTrue() {
        client.getPaths().getBooleanTrue();
    }

    @Test
    public void getBooleanFalse() {
        client.getPaths().getBooleanFalse();
    }

    @Test
    public void getIntOneMillion() {
        client.getPaths().getIntOneMillion();
    }

    @Test
    public void getIntNegativeOneMillion() {
        client.getPaths().getIntNegativeOneMillion();
    }

    @Test
    public void getTenBillion() {
        client.getPaths().getTenBillion();
    }

    @Test
    public void getNegativeTenBillion() {
        client.getPaths().getNegativeTenBillion();
    }

    @Test
    public void floatScientificPositive() {
        client.getPaths().floatScientificPositive();
    }

    @Test
    public void floatScientificNegative() {
        client.getPaths().floatScientificNegative();
    }

    @Test
    public void doubleDecimalPositive() {
        client.getPaths().doubleDecimalPositive();
    }

    @Test
    public void doubleDecimalNegative() {
        client.getPaths().doubleDecimalNegative();
    }

    @Test
    public void stringUnicode() {
        client.getPaths().stringUnicode();
    }

    @Test
    public void stringUrlEncoded() {
        client.getPaths().stringUrlEncoded();
    }

    @Test
    public void stringUrlNonEncoded() {
        client.getPaths().stringUrlNonEncoded();
    }

    @Test
    public void stringEmpty() {
        client.getPaths().stringEmpty();
    }

    @Disabled("Client side validation")
    @Test
    public void stringNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> client.getPaths().stringNull(null));
        assertTrue(ex.getMessage().contains("Parameter stringPath is required"));
    }

    @Test
    public void enumValid() {
        client.getPaths().enumValid(UriColor.GREEN_COLOR);
    }

    @Test
    public void enumNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> client.getPaths().enumNull(null));
        assertTrue(ex.getMessage().contains("Parameter enumPath is required"));
    }

    @Test
    public void byteMultiByte() {
        client.getPaths().byteMultiByte("啊齄丂狛狜隣郎隣兀﨩".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void byteEmpty() {
        client.getPaths().byteEmpty();
    }

    @Test
    public void byteNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> client.getPaths().byteNull(null));
        assertTrue(ex.getMessage().contains("Parameter bytePath is required"));
    }

    @Test
    public void dateValid() {
        client.getPaths().dateValid();
    }

    @Test
    public void dateNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> client.getPaths().dateNull(null));
        assertTrue(ex.getMessage().contains("Parameter datePath is required"));
    }

    @Test
    public void dateTimeValid() {
        client.getPaths().dateTimeValid();
    }

    @Test
    public void dateTimeNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> client.getPaths().dateTimeNull(null));
        assertTrue(ex.getMessage().contains("Parameter dateTimePath is required"));
    }

    @Test
    public void base64Url() {
        client.getPaths().base64Url("lorem".getBytes());
    }

    @Test
    public void arrayCsvInPath() {
        List<String> arrayPath = new ArrayList<>();
        arrayPath.add("ArrayPath1");
        arrayPath.add("begin!*'();:@ &=+$,/?#[]end");
        arrayPath.add(null);
        arrayPath.add("");
        client.getPaths().arrayCsvInPath(arrayPath);
    }

    @Test
    public void unixTimeUrl() {
        client.getPaths().unixTimeUrl(OffsetDateTime.parse("2016-04-13T00:00:00Z"));
    }
}
