package fixtures.azurespecials;

import com.microsoft.rest.RestClient;
import com.microsoft.rest.credentials.TokenCredentials;

import com.microsoft.rest.http.HttpHeaders;
import com.microsoft.rest.policy.AddHeadersPolicy;
import com.microsoft.rest.policy.AddHeadersPolicy.Factory;
import com.microsoft.rest.serializer.JacksonAdapter;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;

public class XMsClientRequestIdTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-ms-client-request-id", "9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");

        RestClient restClient = new RestClient.Builder()
                .withBaseUrl("http://localhost.:3000")
                .withCredentials(new TokenCredentials(null, UUID.randomUUID().toString()))
                .addCustomPolicy(new AddHeadersPolicy.Factory(headers))
                .withSerializerAdapter(new JacksonAdapter())
                .build();

        client = new AutoRestAzureSpecialParametersTestClientImpl(restClient);
        client.withSubscriptionId("1234-5678-9012-3456");
    }

    @Test
    public void get() throws Exception {
        client.xMsClientRequestIds().get();
    }

    @Test
    public void paramGet() throws Exception {
        client.xMsClientRequestIds().paramGet("9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");
    }
}
