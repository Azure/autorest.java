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
        requestOptions.addQueryParam("parameter", "I am a required parameter");

        client.getRequiredWithResponse(requestOptions);

        requestOptions.addQueryParam("new_parameter", "I'm a new input optional parameter");

        client2.getRequiredWithResponse(requestOptions);
    }
}
