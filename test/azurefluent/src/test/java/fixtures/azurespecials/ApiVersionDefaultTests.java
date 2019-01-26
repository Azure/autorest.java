package fixtures.azurespecials;

import com.microsoft.rest.v3.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v3.http.HttpPipeline;
import com.microsoft.rest.v3.policy.CredentialsPolicyFactory;
import com.microsoft.rest.v3.policy.PortPolicyFactory;
import com.microsoft.rest.v3.policy.ProtocolPolicyFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;

public class ApiVersionDefaultTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        final HttpPipeline httpPipeline = HttpPipeline.build(
                new ProtocolPolicyFactory("http"),
                new PortPolicyFactory(3000),
                new CredentialsPolicyFactory(new BasicAuthenticationCredentials(null, null)));
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
