package fixtures.llcupdate;

import com.azure.core.http.rest.RequestOptions;
import fixtures.llcinitial.LLCAsyncClient;
import fixtures.llcinitial.LLCClient;
import fixtures.llcinitial.LLCClientBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;

public class LLCUpdateTests {
    private static LLCAsyncClient asyncClient;

    private static LLCClient client;

    private static fixtures.llcupdate1.LLCAsyncClient asyncClient2;

    private static fixtures.llcupdate1.LLCClient client2;

    @BeforeAll
    public static void setup() {
        asyncClient = new LLCClientBuilder().buildAsyncClient();
        client = new LLCClientBuilder().buildClient();
        asyncClient2 = new fixtures.llcupdate1.LLCClientBuilder().buildAsyncClient();
        client2 = new fixtures.llcupdate1.LLCClientBuilder().buildClient();
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
