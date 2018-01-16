package fixtures.subscriptionidapiversion;

import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import fixtures.subscriptionidapiversion.implementation.MicrosoftAzureTestUrlImpl;
import fixtures.subscriptionidapiversion.implementation.SampleResourceGroupInner;

public class GroupTests {
    private static MicrosoftAzureTestUrlImpl client;

    @BeforeClass
    public static void setup() {
        final HttpPipeline httpPipeline = HttpPipeline.build(
            new ProtocolPolicyFactory("http"),
            new HostPolicyFactory("localhost"),
            new PortPolicyFactory(3000),
            new RetryPolicyFactory(),
            new CookiePolicyFactory());
        client = new MicrosoftAzureTestUrlImpl(httpPipeline);
    }

    @Test
    public void getSampleResourceGroup() throws Exception {
        client.withSubscriptionId(UUID.randomUUID().toString());
        SampleResourceGroupInner group = client.groups().getSampleResourceGroup("testgroup101");
        Assert.assertEquals("testgroup101", group.name());
        Assert.assertEquals("West US", group.location());
    }
}
