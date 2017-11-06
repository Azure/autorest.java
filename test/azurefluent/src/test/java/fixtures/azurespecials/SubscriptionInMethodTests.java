package fixtures.azurespecials;

import com.microsoft.azure.v2.serializer.AzureJacksonAdapter;
import com.microsoft.rest.v2.RestClient;
import com.microsoft.rest.v2.credentials.TokenCredentials;

import com.microsoft.rest.v2.policy.CredentialsPolicy;
import com.microsoft.rest.v2.policy.RequestIdPolicy;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;

import static org.junit.Assert.fail;

public class SubscriptionInMethodTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        RestClient restClient = new RestClient.Builder()
                .withBaseUrl("http://localhost:3000")
                .withSerializerAdapter(new AzureJacksonAdapter())
                .withCredentialsPolicy(new CredentialsPolicy.Factory(new TokenCredentials(null, UUID.randomUUID().toString())))
                .addRequestPolicy(new RequestIdPolicy.Factory())
                .build();
        client = new AutoRestAzureSpecialParametersTestClientImpl(restClient);
        client.withSubscriptionId("1234-5678-9012-3456");
    }

    @Test
    public void postMethodLocalValid() throws Exception {
        client.subscriptionInMethods().postMethodLocalValid("1234-5678-9012-3456");
    }

    @Test
    public void postMethodLocalNull() throws Exception {
        try {
            client.subscriptionInMethods().postMethodLocalNull(null);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter subscriptionId is required"));
        }
    }

    @Test
    public void postPathLocalValid() throws Exception {
        client.subscriptionInMethods().postPathLocalValid("1234-5678-9012-3456");
    }

    @Test
    public void postSwaggerLocalValid() throws Exception {
        client.subscriptionInMethods().postSwaggerLocalValid("1234-5678-9012-3456");
    }
}
