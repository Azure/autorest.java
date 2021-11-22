package fixtures.llcupdate;

import com.azure.core.http.rest.RequestOptions;
import fixtures.llcinitial.LlcAsyncClient;
import fixtures.llcinitial.LlcClient;
import fixtures.llcinitial.LlcClientBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;

public class LLCUpdateTests {
    private static LlcAsyncClient asyncClient;

    private static LlcClient client;

    private static fixtures.llcupdate1.LlcAsyncClient asyncClient2;

    private static fixtures.llcupdate1.LlcClient client2;

    @BeforeAll
    public static void setup() {
        asyncClient = new LlcClientBuilder().buildAsyncClient();
        client = new LlcClientBuilder().buildClient();
        asyncClient2 = new fixtures.llcupdate1.LlcClientBuilder().buildAsyncClient();
        client2 = new fixtures.llcupdate1.LlcClientBuilder().buildClient();
    }

    @Disabled
    public void getRequired() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addHeader("parameter1", "1");
        requestOptions.addHeader("parameter2", "2");
        requestOptions.addHeader("parameter3", "3");

        client.getRequiredWithResponse(requestOptions);
        client2.getRequiredWithResponse(requestOptions);
    }
}
