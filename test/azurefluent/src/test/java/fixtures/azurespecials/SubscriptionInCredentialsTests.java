package fixtures.azurespecials;

import com.microsoft.rest.v2.credentials.TokenCredentials;

import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicy;
import com.microsoft.rest.v2.policy.PortPolicy;
import com.microsoft.rest.v2.policy.ProtocolPolicy;
import com.microsoft.rest.v2.policy.RequestIdPolicy;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;

public class SubscriptionInCredentialsTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        final HttpPipeline httpPipeline = HttpPipeline.build(
                new ProtocolPolicy.Factory("http"),
                new PortPolicy.Factory(3000),
                new CredentialsPolicy.Factory(new TokenCredentials(null, UUID.randomUUID().toString())),
                new RequestIdPolicy.Factory());
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
