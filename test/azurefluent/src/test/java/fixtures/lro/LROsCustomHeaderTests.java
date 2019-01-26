package fixtures.lro;

import com.microsoft.rest.v3.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v3.http.HttpHeaders;
import com.microsoft.rest.v3.http.HttpPipeline;
import com.microsoft.rest.v3.policy.AddHeadersPolicyFactory;
import com.microsoft.rest.v3.policy.CredentialsPolicyFactory;
import com.microsoft.rest.v3.policy.PortPolicyFactory;
import com.microsoft.rest.v3.policy.ProtocolPolicyFactory;
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
        final HttpPipeline httpPipeline = HttpPipeline.build(
                new ProtocolPolicyFactory("http"),
                new PortPolicyFactory(3000),
                new CredentialsPolicyFactory(new BasicAuthenticationCredentials(null, null)),
                new AddHeadersPolicyFactory(headers));
        client = new AutoRestLongRunningOperationTestServiceImpl(httpPipeline);
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
