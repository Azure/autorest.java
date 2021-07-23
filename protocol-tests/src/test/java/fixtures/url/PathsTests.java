package fixtures.url;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PathsTests {
    private static PathsAsyncClient asyncClient;

    private static PathsClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new AutoRestUrlTestServiceBuilder().buildPathsAsyncClient();
        client = new AutoRestUrlTestServiceBuilder().buildPathsClient();
    }

    @Test
    public void getBooleanTrue() {
        client.getBooleanTrue(null);
    }

    @Test
    public void getBooleanFalse() {
        client.getBooleanTrue(null);
    }

    @Test
    public void getIntOneMillion() {
        client.getIntOneMillion(null);
    }

    @Test
    public void getIntNegativeOneMillion() {
        client.getIntNegativeOneMillion(null);
    }

    @Test
    public void getTenBillion() {
        client.getTenBillion(null);
    }

    @Test
    public void getNegativeTenBillion() {
        client.getNegativeTenBillion(null);
    }

    @Test
    public void floatScientificPositive() {
        client.floatScientificPositive(null);
    }

    @Test
    public void floatScientificNegative() {
        client.floatScientificNegative(null);
    }

    @Test
    public void doubleDecimalPositive() {
        client.doubleDecimalPositive(null);
    }

    @Test
    public void doubleDecimalNegative() {
        client.doubleDecimalNegative(null);
    }

    @Test
    public void stringUnicode() {
        client.stringUnicode(null);
    }

    @Test
    public void stringUrlEncoded() {
        client.stringUrlEncoded(null);
    }

    @Test
    public void stringUrlNonEncoded() {
        client.stringUrlNonEncoded(null);
    }

    @Test
    public void stringEmpty() {
        client.stringEmpty(null);
    }


}
