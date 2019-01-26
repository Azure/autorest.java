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

public class ApiVersionDefaultTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        final HttpPipeline httpPipeline = HttpPipeline.build(
                new ProtocolPolicyFactory("http"),
                new PortPolicyFactory(3000),
                new CredentialsPolicyFactory(new BasicAuthenticationCredentials(null, null)),
                new DecodingPolicyFactory());
        client = new AutoRestAzureSpecialParametersTestClientImpl(httpPipeline);
    }

    @Test
    public void getMethodGlobalValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionDefaults().getMethodGlobalValidWithRestResponseAsync().block();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void getMethodGlobalNotProvidedValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionDefaults().getMethodGlobalNotProvidedValidWithRestResponseAsync().block();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void getPathGlobalValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionDefaults().getPathGlobalValidWithRestResponseAsync().block();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void getSwaggerGlobalValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionDefaults().getSwaggerGlobalValidWithRestResponseAsync().block();
        assertEquals(200, response.statusCode());
    }
}
