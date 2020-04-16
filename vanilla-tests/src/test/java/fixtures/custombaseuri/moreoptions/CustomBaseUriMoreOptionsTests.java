package fixtures.custombaseuri.moreoptions;

import org.junit.BeforeClass;
import org.junit.Test;

public class CustomBaseUriMoreOptionsTests {
    private static AutoRestParameterizedCustomHostTestClientBuilder client;

    @BeforeClass
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
