package fixtures.subscriptionidapiversion;

import com.microsoft.rest.v3.http.HttpPipeline;
import com.microsoft.rest.v3.policy.DecodingPolicyFactory;
import com.microsoft.rest.v3.policy.HostPolicyFactory;
import com.microsoft.rest.v3.policy.PortPolicyFactory;
import com.microsoft.rest.v3.policy.ProtocolPolicyFactory;
import fixtures.subscriptionidapiversion.implementation.MicrosoftAzureTestUrlImpl;
import fixtures.subscriptionidapiversion.models.SampleResourceGroup;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

public class GroupTests {
    private static MicrosoftAzureTestUrlImpl client;

    @BeforeClass
    public static void setup() {
        client = new MicrosoftAzureTestUrlImpl(
            HttpPipeline.build(
                new ProtocolPolicyFactory("http"),
                new HostPolicyFactory("localhost"),
                new PortPolicyFactory(3000),
                new DecodingPolicyFactory()));
    }

    @Test
    public void getSampleResourceGroup() throws Exception {
        client.withSubscriptionId(UUID.randomUUID().toString());
        SampleResourceGroup group = client.groups().getSampleResourceGroup("testgroup101");
        Assert.assertEquals("testgroup101", group.name());
        Assert.assertEquals("West US", group.location());
    }
}
