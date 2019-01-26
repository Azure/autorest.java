package fixtures.azurespecials;

import com.microsoft.rest.v3.RestResponse;
import com.microsoft.rest.v3.credentials.TokenCredentials;
import com.microsoft.rest.v3.http.HttpHeaders;
import com.microsoft.rest.v3.http.HttpPipeline;
import com.microsoft.rest.v3.policy.AddHeadersPolicyFactory;
import com.microsoft.rest.v3.policy.CredentialsPolicyFactory;
import com.microsoft.rest.v3.policy.DecodingPolicyFactory;
import com.microsoft.rest.v3.policy.PortPolicyFactory;
import com.microsoft.rest.v3.policy.ProtocolPolicyFactory;
import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class XMsClientRequestIdTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-ms-client-request-id", "9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");

        final HttpPipeline httpPipeline = HttpPipeline.build(
                new ProtocolPolicyFactory("http"),
                new PortPolicyFactory(3000),
                new CredentialsPolicyFactory(new TokenCredentials(null, UUID.randomUUID().toString())),
                new AddHeadersPolicyFactory(headers),
                new DecodingPolicyFactory());
        client = new AutoRestAzureSpecialParametersTestClientImpl(httpPipeline);
        client.withSubscriptionId("1234-5678-9012-3456");
    }

    @Test
    public void get() throws Exception {
        RestResponse<Void, Void> response = client.xMsClientRequestIds().getWithRestResponseAsync().block();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void paramGet() throws Exception {
        RestResponse<Void, Void> response = client.xMsClientRequestIds().paramGetWithRestResponseAsync("9C4D50EE-2D56-4CD3-8152-34347DC9F2B0").block();
        assertEquals(200, response.statusCode());
    }
}
