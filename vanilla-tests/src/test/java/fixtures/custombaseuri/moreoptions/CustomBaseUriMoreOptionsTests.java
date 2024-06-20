package fixtures.custombaseuri.moreoptions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CustomBaseUriMoreOptionsTests {
    private static AutoRestParameterizedCustomHostTestClientBuilder client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestParameterizedCustomHostTestClientBuilder();
        client.subscriptionId("test12");
    }

    // Positive test case
    @Test
    public void getEmpty() throws Exception {
        client.dnsSuffix("host:3000").buildClient().getPaths().getEmpty("http://lo", "cal", "key1", "v1");
    }
}
