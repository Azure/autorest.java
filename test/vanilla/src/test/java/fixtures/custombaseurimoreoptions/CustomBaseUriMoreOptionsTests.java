package fixtures.custombaseurimoreoptions;

import org.junit.BeforeClass;
import org.junit.Test;

import fixtures.custombaseurimoreoptions.implementation.AutoRestParameterizedCustomHostTestClientImpl;

public class CustomBaseUriMoreOptionsTests {
    private static AutoRestParameterizedCustomHostTestClient client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestParameterizedCustomHostTestClientImpl();
        client.withSubscriptionId("test12");
    }

    // Positive test case
    @Test
    public void getEmpty() throws Exception {
        client.withDnsSuffix("host:3000");
        client.paths().getEmpty("http://lo", "cal", "key1", "v1");
    }
}
