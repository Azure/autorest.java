package fixtures.headexceptions;

import com.azure.core.exception.HttpResponseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HeadExceptionsOperationsTests {
    private static AutoRestHeadExceptionTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestHeadExceptionTestServiceBuilder().buildClient();
    }

    @Test
    public void head200() {
        Assert.assertEquals(200, client.getHeadExceptions().head200WithResponseAsync().block().getStatusCode());
    }

    @Test
    public void head204() {
        Assert.assertEquals(204, client.getHeadExceptions().head204WithResponseAsync().block().getStatusCode());
    }

    @Test(expected = HttpResponseException.class)
    public void head404() {
        client.getHeadExceptions().head404();  // status code other than 204 is error
        Assert.fail();
    }
}
