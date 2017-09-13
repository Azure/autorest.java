package fixtures.custombaseurimoreoptions;

import com.microsoft.rest.LogLevel;
import com.microsoft.rest.RestClient;
import org.junit.BeforeClass;
import org.junit.Test;

import fixtures.custombaseurimoreoptions.implementation.AutoRestParameterizedCustomHostTestClientImpl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class CustomBaseUriMoreOptionsTests {
    private static AutoRestParameterizedCustomHostTestClient client;

    @BeforeClass
    public static void setup() {
        RestClient restClient = new RestClient.Builder()
                .withLogLevel(LogLevel.BODY)
                .build();

        client = new AutoRestParameterizedCustomHostTestClientImpl(restClient);
        client.withSubscriptionId("test12");
    }

    // Positive test case
    @Test
    public void getEmpty() throws Exception {
        client.withDnsSuffix("host:3000");
        client.paths().getEmpty("http://lo", "cal", "key1", "v1");
    }
}
