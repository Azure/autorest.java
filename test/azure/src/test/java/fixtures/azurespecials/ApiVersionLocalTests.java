package fixtures.azurespecials;

import com.microsoft.rest.v2.RestResponse;
import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicyFactory;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import com.microsoft.rest.v2.policy.ProtocolPolicyFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;

import static org.junit.Assert.assertEquals;

public class ApiVersionLocalTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestAzureSpecialParametersTestClientImpl(
            HttpPipeline.build(
                new ProtocolPolicyFactory("http"),
                new PortPolicyFactory(3000),
                new CredentialsPolicyFactory(new BasicAuthenticationCredentials(null, null))));
    }

    @Test
    public void getMethodLocalValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionLocals().getMethodLocalValidWithRestResponseAsync().blockingGet();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void getMethodGlobalNotProvidedValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionLocals().getMethodLocalNullWithRestResponseAsync().blockingGet();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void getPathGlobalValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionLocals().getPathLocalValidWithRestResponseAsync().blockingGet();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void getSwaggerGlobalValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionLocals().getSwaggerLocalValidWithRestResponseAsync().blockingGet();
        assertEquals(200, response.statusCode());
    }
}
