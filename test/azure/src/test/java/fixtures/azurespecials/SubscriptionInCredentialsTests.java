package fixtures.azurespecials;

import com.microsoft.rest.v2.credentials.TokenCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicyFactory;
import com.microsoft.rest.v2.policy.DecodingPolicyFactory;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import com.microsoft.rest.v2.policy.ProtocolPolicyFactory;
import com.microsoft.rest.v2.policy.RequestIdPolicyFactory;
import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

public class SubscriptionInCredentialsTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        final HttpPipeline httpPipeline = HttpPipeline.build(
                new ProtocolPolicyFactory("http"),
                new PortPolicyFactory(3000),
                new CredentialsPolicyFactory(new TokenCredentials(null, UUID.randomUUID().toString())),
                new RequestIdPolicyFactory(),
                new DecodingPolicyFactory());
        client = new AutoRestAzureSpecialParametersTestClientImpl(httpPipeline);
        client.withSubscriptionId("1234-5678-9012-3456");
    }

    @Test
    public void postMethodGlobalValid() throws Exception {
        client.subscriptionInCredentials().postMethodGlobalValid();
    }

    @Test
    public void postMethodGlobalNotProvidedValid() throws Exception {
        client.subscriptionInCredentials().postMethodGlobalNotProvidedValid();
    }

    @Test
    public void postPathGlobalValid() throws Exception {
        client.subscriptionInCredentials().postPathGlobalValid();
    }

    @Test
    public void postSwaggerGlobalValid() throws Exception {
        client.subscriptionInCredentials().postSwaggerGlobalValid();
    }
}
