package fixtures.head;

import com.azure.core.http.rest.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HeadOperationsTests {
    private static AutoRestHeadTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestHeadTestServiceBuilder().buildClient();
    }

    @Test
    public void head200() {
        Assert.assertEquals(200, client.httpSuccess().head200WithResponseAsync().block().getStatusCode());
    }

    @Test
    public void head204() {
        Response<Boolean> response =  client.httpSuccess().head204WithResponseAsync().block();
        Assert.assertEquals(204, response.getStatusCode());
        Assert.assertTrue(response.getValue()); // 204 means true
    }

    @Test
    public void head404() {
        Response<Boolean> response =  client.httpSuccess().head404WithResponseAsync().block();
        // both status code 204 and 404 is not error, 404 means false
        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertFalse(response.getValue());
    }
}
