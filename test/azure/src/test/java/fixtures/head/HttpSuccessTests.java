package fixtures.head;

import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicy;
import com.microsoft.rest.v2.policy.PortPolicy;
import com.microsoft.rest.v2.policy.ProtocolPolicy;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fixtures.head.implementation.AutoRestHeadTestServiceImpl;

public class HttpSuccessTests {
    private static AutoRestHeadTestServiceImpl client;

    @BeforeClass
    public static void setup() throws Exception {
        client = new AutoRestHeadTestServiceImpl(
            HttpPipeline.build(
                new ProtocolPolicy.Factory("http"),
                new PortPolicy.Factory(3000),
                new CredentialsPolicy.Factory(new BasicAuthenticationCredentials(null, null))));
    }

    @Test
    public void head200() throws Exception {
        Assert.assertTrue(client.httpSuccess().head200());
    }

    @Test
    public void head204() throws Exception {
        Assert.assertTrue(client.httpSuccess().head204());
    }

    @Test
    public void head404() throws Exception {
        Assert.assertFalse(client.httpSuccess().head404());
    }
}
