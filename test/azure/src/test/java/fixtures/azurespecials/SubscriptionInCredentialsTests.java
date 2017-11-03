package fixtures.azurespecials;

import com.microsoft.azure.v2.serializer.AzureJacksonAdapter;
import com.microsoft.rest.v2.RestClient;
import com.microsoft.rest.v2.credentials.TokenCredentials;

import com.microsoft.rest.v2.policy.RequestIdPolicy;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;

public class SubscriptionInCredentialsTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        RestClient restClient = new RestClient.Builder()
                .withBaseUrl("http://localhost:3000")
                .withSerializerAdapter(new AzureJacksonAdapter())
                .withCredentials(new TokenCredentials(null, UUID.randomUUID().toString()))
                .addRequestPolicy(new RequestIdPolicy.Factory())
                .build();
        client = new AutoRestAzureSpecialParametersTestClientImpl(restClient);
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
