package fixtures.lro;

import com.microsoft.azure.v2.AzureProxy;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CookiePolicyFactory;
import com.microsoft.rest.v2.policy.DecodingPolicyFactory;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import com.microsoft.rest.v2.policy.ProtocolPolicyFactory;
import com.microsoft.rest.v2.policy.RetryPolicyFactory;
import fixtures.lro.implementation.AutoRestLongRunningOperationTestServiceImpl;
import fixtures.lro.models.Product;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LRORetrysTests {
    private static AutoRestLongRunningOperationTestServiceImpl client;

    @BeforeClass
    public static void setup() {
        final HttpPipeline httpPipeline = HttpPipeline.build(
            new ProtocolPolicyFactory("http"),
            new PortPolicyFactory(3000),
            new RetryPolicyFactory(),
            new CookiePolicyFactory(),
            new DecodingPolicyFactory());
        AzureProxy.setDefaultPollingDelayInMilliseconds(0);
        client = new AutoRestLongRunningOperationTestServiceImpl(httpPipeline)
            .withLongRunningOperationRetryTimeout(0);
    }

    @Test
    public void put201CreatingSucceeded200() throws Exception {
        Product product = new Product();
        product.withLocation("West US");
        Product response = client.lRORetrys().put201CreatingSucceeded200(product);
        Assert.assertEquals("Succeeded", response.provisioningState());
    }

    @Test
    public void putAsyncRelativeRetrySucceeded() throws Exception {
        Product product = new Product();
        product.withLocation("West US");
        Product response = client.lRORetrys().putAsyncRelativeRetrySucceeded(product);
        Assert.assertEquals("Succeeded", response.provisioningState());
    }

    @Test
    public void deleteProvisioning202Accepted200Succeeded() throws Exception {
        Product response = client.lRORetrys().deleteProvisioning202Accepted200Succeeded();
        Assert.assertEquals("Succeeded", response.provisioningState());
    }

    @Test
    public void delete202Retry200() throws Exception {
        client.lRORetrys().delete202Retry200();
    }

    @Test
    public void deleteAsyncRelativeRetrySucceeded() throws Exception {
        client.lRORetrys().deleteAsyncRelativeRetrySucceeded();
    }

    @Test
    public void post202Retry200() throws Exception {
        Product product = new Product();
        product.withLocation("West US");
        client.lRORetrys().post202Retry200(product);
    }

    @Test
    public void postAsyncRelativeRetrySucceeded() throws Exception {
        Product product = new Product();
        product.withLocation("West US");
        client.lRORetrys().postAsyncRelativeRetrySucceeded(product);
    }
}
