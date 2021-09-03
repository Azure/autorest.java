package fixtures.httpinfrastructure;

import org.junit.jupiter.api.BeforeAll;

public class MultipleResponseTests {
    private static MultipleResponsesAsyncClient asyncClient;

    private static MultipleResponsesClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new AutoRestHttpInfrastructureTestServiceBuilder().buildMultipleResponsesAsyncClient();
        client = new AutoRestHttpInfrastructureTestServiceBuilder().buildMultipleResponsesClient();
    }

}
