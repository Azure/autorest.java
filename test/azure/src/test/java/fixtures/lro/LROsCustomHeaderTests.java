package fixtures.lro;

import com.microsoft.azure.AzureProxy;
import com.microsoft.azure.serializer.AzureJacksonAdapter;
import com.microsoft.rest.RestClient;
import com.microsoft.rest.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.http.HttpHeaders;
import com.microsoft.rest.policy.AddHeadersPolicy;
import fixtures.lro.implementation.AutoRestLongRunningOperationTestServiceImpl;
import fixtures.lro.models.Product;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LROsCustomHeaderTests {
    private static AutoRestLongRunningOperationTestServiceImpl client;

    @BeforeClass
    public static void setup() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-ms-client-request-id", "9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");

        RestClient restClient = new RestClient.Builder()
                .withBaseUrl("http://localhost:3000")
                .withCredentials(new BasicAuthenticationCredentials(null, null))
                .withSerializerAdapter(new AzureJacksonAdapter())
                .addCustomPolicy(new AddHeadersPolicy.Factory(headers))
                .build();
        AzureProxy.setDefaultDelayInMilliseconds(0);
        client = new AutoRestLongRunningOperationTestServiceImpl(restClient);
    }

    @Test
    public void putAsyncRetrySucceeded() throws Exception {
        Product product = new Product();
        product.withLocation("West US");
        Product response = client.lROsCustomHeaders().putAsyncRetrySucceeded(product);
        Assert.assertEquals("Succeeded", response.provisioningState());
    }

    @Test
    public void put201CreatingSucceeded200() throws Exception {
        Product product = new Product();
        product.withLocation("West US");
        Product response = client.lROsCustomHeaders().put201CreatingSucceeded200(product);
        Assert.assertEquals("Succeeded", response.provisioningState());
    }

    @Test
    public void post202Retry200() throws Exception {
        Product product = new Product();
        product.withLocation("West US");
        client.lROsCustomHeaders().post202Retry200(product);
    }

    @Test
    public void postAsyncRetrySucceeded() throws Exception {
        Product product = new Product();
        product.withLocation("West US");
        client.lROsCustomHeaders().postAsyncRetrySucceeded(product);
    }
}
