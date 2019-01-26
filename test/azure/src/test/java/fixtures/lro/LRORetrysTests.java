package fixtures.lro;

import com.microsoft.azure.v3.AzureProxy;
import com.microsoft.rest.v3.credentials.BasicAuthenticationCredentials;
import fixtures.lro.implementation.AutoRestLongRunningOperationTestServiceImpl;
import fixtures.lro.models.Product;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LRORetrysTests {
    private static AutoRestLongRunningOperationTestServiceImpl client;

    @BeforeClass
    public static void setup() {
        AzureProxy.setDefaultPollingDelayInMilliseconds(0);
        client = new AutoRestLongRunningOperationTestServiceImpl(
            new BasicAuthenticationCredentials(null, null))
            .withLongRunningOperationRetryTimeout(0);
    }

    @Test
    public void put201CreatingSucceeded200() {
        Product product = new Product();
        product.withLocation("West US");
        Product response = client.lRORetrys().put201CreatingSucceeded200(product);
        Assert.assertEquals("Succeeded", response.provisioningState());
    }

    @Test
    public void putAsyncRelativeRetrySucceeded() {
        Product product = new Product();
        product.withLocation("West US");
        Product response = client.lRORetrys().putAsyncRelativeRetrySucceeded(product);
        Assert.assertEquals("Succeeded", response.provisioningState());
    }

    @Test
    public void deleteProvisioning202Accepted200Succeeded() {
        Product response = client.lRORetrys().deleteProvisioning202Accepted200Succeeded();
        Assert.assertEquals("Succeeded", response.provisioningState());
    }

    @Test
    public void delete202Retry200() {
        client.lRORetrys().delete202Retry200();
    }

    @Test
    public void deleteAsyncRelativeRetrySucceeded() {
        client.lRORetrys().deleteAsyncRelativeRetrySucceeded();
    }

    @Test
    public void post202Retry200() {
        Product product = new Product();
        product.withLocation("West US");
        client.lRORetrys().post202Retry200(product);
    }

    @Test
    public void postAsyncRelativeRetrySucceeded() {
        Product product = new Product();
        product.withLocation("West US");
        client.lRORetrys().postAsyncRelativeRetrySucceeded(product);
    }
}
