package fixtures.subscriptionidapiversion;

import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CookiePolicyFactory;
import com.microsoft.rest.v2.policy.DecodingPolicyFactory;
import com.microsoft.rest.v2.policy.HostPolicyFactory;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import com.microsoft.rest.v2.policy.ProtocolPolicyFactory;
import com.microsoft.rest.v2.policy.RetryPolicyFactory;
import fixtures.subscriptionidapiversion.implementation.MicrosoftAzureTestUrlImpl;
import fixtures.subscriptionidapiversion.implementation.SampleResourceGroupInner;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

public class GroupTests {
    private static MicrosoftAzureTestUrlImpl client;

    @BeforeClass
    public static void setup() {
        final HttpPipeline httpPipeline = HttpPipeline.build(
            new ProtocolPolicyFactory("http"),
            new HostPolicyFactory("localhost"),
            new PortPolicyFactory(3000),
            new DecodingPolicyFactory());
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
