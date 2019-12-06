package fixtures.head;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HeadOperationsTests {
    private static AutoRestHeadTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestHeadTestServiceBuilder().build();
    }

    @Test
    public void head200() {
        Assert.assertEquals(200, client.httpSuccess().head200WithResponseAsync().block().getStatusCode());
    }

    @Test
    public void head204() {
        Assert.assertEquals(204, client.httpSuccess().head204WithResponseAsync().block().getStatusCode());
    }

    @Test
    public void head404() {
        Assert.assertEquals(404, client.httpSuccess().head404WithResponseAsync().block().getStatusCode());  // both status code 204 and 404 is not error
    }
}
