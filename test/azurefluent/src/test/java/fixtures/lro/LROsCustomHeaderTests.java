package fixtures.lro;

import com.microsoft.rest.v2.RestClient;
import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpHeaders;
import com.microsoft.rest.v2.policy.AddHeadersPolicy;
import com.microsoft.rest.v2.policy.CredentialsPolicy;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;

import fixtures.lro.implementation.AutoRestLongRunningOperationTestServiceImpl;
import fixtures.lro.implementation.ProductInner;

public class LROsCustomHeaderTests {
    private static AutoRestLongRunningOperationTestServiceImpl client;

    @BeforeClass
    public static void setup() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-ms-client-request-id", "9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");

        RestClient config = new RestClient.Builder()
                .withBaseUrl("http://localhost:3000")
                .withCredentialsPolicy(new CredentialsPolicy.Factory(new BasicAuthenticationCredentials(null, null)))
                .addRequestPolicy(new AddHeadersPolicy.Factory(headers))
                .build();

        client = new AutoRestLongRunningOperationTestServiceImpl(config);
    }

    @Ignore("Pending headermap")
    public void putAsyncRetrySucceeded() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        ProductInner response = client.lROsCustomHeaders().putAsyncRetrySucceeded(product);
        Assert.assertEquals("Succeeded", response.provisioningState());
    }

    @Ignore("Pending headermap")
    public void put201CreatingSucceeded200() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        ProductInner response = client.lROsCustomHeaders().put201CreatingSucceeded200(product);
        Assert.assertEquals("Succeeded", response.provisioningState());
    }

    @Ignore("Pending headermap")
    public void post202Retry200() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        client.lROsCustomHeaders().post202Retry200(product);
    }

    @Ignore("Pending headermap")
    public void postAsyncRetrySucceeded() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        client.lROsCustomHeaders().postAsyncRetrySucceeded(product);
    }
}
