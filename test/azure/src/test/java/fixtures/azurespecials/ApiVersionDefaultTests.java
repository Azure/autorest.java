package fixtures.azurespecials;

import com.microsoft.rest.v2.RestResponse;
import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicy;
import com.microsoft.rest.v2.policy.PortPolicy;
import com.microsoft.rest.v2.policy.ProtocolPolicy;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;

import static org.junit.Assert.assertEquals;

public class ApiVersionDefaultTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        final HttpPipeline httpPipeline = HttpPipeline.build(
                new ProtocolPolicy.Factory("http"),
                new PortPolicy.Factory(3000),
                new CredentialsPolicy.Factory(new BasicAuthenticationCredentials(null, null)));
        client = new AutoRestAzureSpecialParametersTestClientImpl(httpPipeline);
    }

    @Test
    public void getMethodGlobalValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionDefaults().getMethodGlobalValidWithRestResponseAsync().blockingGet();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void getMethodGlobalNotProvidedValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionDefaults().getMethodGlobalNotProvidedValidWithRestResponseAsync().blockingGet();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void getPathGlobalValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionDefaults().getPathGlobalValidWithRestResponseAsync().blockingGet();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void getSwaggerGlobalValid() throws Exception {
        RestResponse<Void, Void> response = client.apiVersionDefaults().getSwaggerGlobalValidWithRestResponseAsync().blockingGet();
        assertEquals(200, response.statusCode());
    }
}
