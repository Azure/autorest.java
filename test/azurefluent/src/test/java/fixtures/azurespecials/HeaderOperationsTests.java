package fixtures.azurespecials;

import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicyFactory;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import com.microsoft.rest.v2.policy.ProtocolPolicyFactory;
import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


public class HeaderOperationsTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        final HttpPipeline httpPipeline = HttpPipeline.build(
                new ProtocolPolicyFactory("http"),
                new PortPolicyFactory(3000),
                new CredentialsPolicyFactory(new BasicAuthenticationCredentials(null, null)));
        client = new AutoRestAzureSpecialParametersTestClientImpl(httpPipeline);
    }

    @Ignore("Response status and headers not currently exposed through RestProxy")
    @Test
    public void customNamedRequestId() throws Exception {
//        ServiceResponseWithHeaders<Void, HeaderCustomNamedRequestIdHeadersInner> response = null;//client.headers().customNamedRequestIdWithServiceResponseAsync("9C4D50EE-2D56-4CD3-8152-34347DC9F2B0").toBlocking().last();
//        Assert.assertEquals(200, response.response().code());
//        Assert.assertEquals("123", response.headers().fooRequestId());
    }
}
