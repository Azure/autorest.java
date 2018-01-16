package fixtures.azurespecials;

import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicyFactory;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import com.microsoft.rest.v2.policy.ProtocolPolicyFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;

public class ApiVersionLocalTests {
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
    public void getMethodLocalValid() throws Exception {
        client.apiVersionLocals().getMethodLocalValid();
    }

    @Test
    public void getMethodGlobalNotProvidedValid() throws Exception {
        client.apiVersionLocals().getMethodLocalNull(null);
    }

    @Test
    public void getPathGlobalValid() throws Exception {
        client.apiVersionLocals().getPathLocalValid();
    }

    @Test
    public void getSwaggerGlobalValid() throws Exception {
        client.apiVersionLocals().getSwaggerLocalValid();
    }
}
