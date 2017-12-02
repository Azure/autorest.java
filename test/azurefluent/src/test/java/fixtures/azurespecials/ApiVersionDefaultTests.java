package fixtures.azurespecials;

import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicy;
import com.microsoft.rest.v2.policy.PortPolicy;
import com.microsoft.rest.v2.policy.ProtocolPolicy;
import org.junit.BeforeClass;
import org.junit.Test;

import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;

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
        client.apiVersionDefaults().getMethodGlobalValid();
    }

    @Test
    public void getMethodGlobalNotProvidedValid() throws Exception {
        client.apiVersionDefaults().getMethodGlobalNotProvidedValid();
    }

    @Test
    public void getPathGlobalValid() throws Exception {
        client.apiVersionDefaults().getPathGlobalValid();
    }

    @Test
    public void getSwaggerGlobalValid() throws Exception {
        client.apiVersionDefaults().getSwaggerGlobalValid();
    }
}
