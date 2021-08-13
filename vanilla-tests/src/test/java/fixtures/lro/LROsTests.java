package fixtures.lro;

import com.azure.core.util.polling.SyncPoller;
import fixtures.lro.models.Product;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LROsTests {
    private static AutoRestLongRunningOperationTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestLongRunningOperationTestServiceBuilder().buildClient();
    }

    @Test
    public void beginPut200Succeeded() throws Exception {
        Product product = new Product();
        product.setLocation("West US");

        SyncPoller<Product, Product> poller = client.getLROs().beginPut200Succeeded(product, Duration.ofSeconds(1));
        assertEquals("Succeeded", poller.getFinalResult().getProvisioningState());
    }
}
