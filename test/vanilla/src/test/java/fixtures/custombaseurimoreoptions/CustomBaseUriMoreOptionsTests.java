package fixtures.custombaseurimoreoptions;

import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.PortPolicy;
import org.junit.BeforeClass;
import org.junit.Test;

import fixtures.custombaseurimoreoptions.implementation.AutoRestParameterizedCustomHostTestClientImpl;

public class CustomBaseUriMoreOptionsTests {
    private static AutoRestParameterizedCustomHostTestClient client;

    @BeforeClass
    public static void setup() {
        // Manually specifying the base URL because the generated one is https.
        // The local autorest test server can't talk to it that way without a cert.
        client = new AutoRestParameterizedCustomHostTestClientImpl(HttpPipeline.build(new PortPolicy.Factory(3000)));

        client.withSubscriptionId("test12");
    }

    // Positive test case
    @Test
    public void getEmpty() throws Exception {
        client.withDnsSuffix("host:3000");
        client.paths().getEmpty("lo", "cal", "key1", "v1");
    }
}
