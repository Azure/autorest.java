package fixtures.llcupdate;

import com.azure.core.http.rest.RequestOptions;
import fixtures.llcinitial.DpgAsyncClient;
import fixtures.llcinitial.DpgClient;
import fixtures.llcinitial.DpgClientBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LLCUpdateTests {
    private static DpgAsyncClient asyncClient;

    private static DpgClient client;

    private static fixtures.llcupdate1.DpgAsyncClient asyncClient2;

    private static fixtures.llcupdate1.DpgClient client2;

    @BeforeAll
    public static void setup() {
        asyncClient = new DpgClientBuilder().buildAsyncClient();
        client = new DpgClientBuilder().buildClient();
        asyncClient2 = new fixtures.llcupdate1.DpgClientBuilder().buildAsyncClient();
        client2 = new fixtures.llcupdate1.DpgClientBuilder().buildClient();
    }

    @Test
    public void getRequired() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addHeader("parameter1", "1");
        requestOptions.addHeader("parameter2", "2");
        requestOptions.addHeader("parameter3", "3");

        client.getRequiredWithResponse(requestOptions);
        //client2.getRequiredWithResponse(requestOptions);
    }
}
