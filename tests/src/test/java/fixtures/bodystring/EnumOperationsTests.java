package fixtures.bodystring;

import fixtures.bodystring.models.Colors;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class EnumOperationsTests {
    private static AutoRestSwaggerBATService client;
    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATServiceBuilder().build();
    }

    @Test
    public void getNotExpandable() throws Exception {
        Colors result = client.enums().getNotExpandableWithResponseAsync().block().getValue();
        Assert.assertEquals(Colors.RED_COLOR, result);
    }

    @Test
    public void putNotExpandable() throws Exception {
        client.enums().putNotExpandableWithResponseAsync(Colors.RED_COLOR).block();
    }
}
