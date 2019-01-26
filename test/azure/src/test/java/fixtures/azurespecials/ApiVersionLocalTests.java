package fixtures.azurespecials;

import com.microsoft.rest.v3.RestResponse;
import com.microsoft.rest.v3.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v3.http.HttpPipeline;
import com.microsoft.rest.v3.policy.CredentialsPolicyFactory;
import com.microsoft.rest.v3.policy.DecodingPolicyFactory;
import com.microsoft.rest.v3.policy.PortPolicyFactory;
import com.microsoft.rest.v3.policy.ProtocolPolicyFactory;
import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApiVersionLocalTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestAzureSpecialParametersTestClientImpl(
            HttpPipeline.build(
                new ProtocolPolicyFactory("http"),
                new PortPolicyFactory(3000),
                new CredentialsPolicyFactory(new BasicAuthenticationCredentials(null, null)),
                new DecodingPolicyFactory()));
    }

    @Test
    public void getMethodLocalValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionLocals().getMethodLocalValidWithRestResponseAsync().block();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void getMethodGlobalNotProvidedValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionLocals().getMethodLocalNullWithRestResponseAsync().block();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void getPathGlobalValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionLocals().getPathLocalValidWithRestResponseAsync().block();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void getSwaggerGlobalValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionLocals().getSwaggerLocalValidWithRestResponseAsync().block();
        assertEquals(200, response.statusCode());
    }
}
