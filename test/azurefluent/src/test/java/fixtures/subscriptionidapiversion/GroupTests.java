package fixtures.subscriptionidapiversion;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.HostPolicy;
import com.azure.core.http.policy.PortPolicy;
import com.azure.core.http.policy.ProtocolPolicy;
import fixtures.subscriptionidapiversion.implementation.MicrosoftAzureTestUrlBuilder;
import fixtures.subscriptionidapiversion.implementation.MicrosoftAzureTestUrlImpl;
import fixtures.subscriptionidapiversion.implementation.SampleResourceGroupInner;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

public class GroupTests {
    private static MicrosoftAzureTestUrlBuilder client;

    @BeforeClass
    public static void setup() {
        final HttpPipeline httpPipeline = new HttpPipelineBuilder().policies(
            new ProtocolPolicy("http", true),
            new HostPolicy("localhost"),
            new PortPolicy(3000, true)).build();
        client = new MicrosoftAzureTestUrlBuilder().pipeline(httpPipeline);
    }

    @Test
    public void getSampleResourceGroup() throws Exception {
        client.subscriptionId(UUID.randomUUID().toString());
        SampleResourceGroupInner group = client.build().groups().getSampleResourceGroup("testgroup101");
        Assert.assertEquals("testgroup101", group.name());
        Assert.assertEquals("West US", group.location());
    }
}
