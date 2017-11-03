package fixtures.azurespecials;

import com.microsoft.azure.v2.serializer.AzureJacksonAdapter;
import com.microsoft.rest.v2.RestClient;
import com.microsoft.rest.v2.credentials.TokenCredentials;
import com.microsoft.rest.v2.http.HttpHeaders;
import com.microsoft.rest.v2.policy.AddHeadersPolicy;
import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.UUID;

public class XMsClientRequestIdTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-ms-client-request-id", "9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");

        RestClient restClient = new RestClient.Builder()
                .withBaseUrl("http://localhost:3000")
                .withCredentials(new TokenCredentials(null, UUID.randomUUID().toString()))
                .withSerializerAdapter(new AzureJacksonAdapter())
                .addRequestPolicy(new AddHeadersPolicy.Factory(headers))
                .build();

        client = new AutoRestAzureSpecialParametersTestClientImpl(restClient);
        client.withSubscriptionId("1234-5678-9012-3456");
    }

    @Test
    @Ignore("RestProxy doesn't support reading HTTP response codes yet")
    public void get() throws Exception {
        Void response = client.xMsClientRequestIds().getAsync().toBlocking().value();
//        Assert.assertEquals(200, response.response().code());
    }

    @Test
    @Ignore("RestProxy doesn't support reading HTTP response codes yet")
    public void paramGet() throws Exception {
        Void response = client.xMsClientRequestIds().paramGetAsync("9C4D50EE-2D56-4CD3-8152-34347DC9F2B0").toBlocking().value();
//        Assert.assertEquals(200, response.response().code());
    }
}
