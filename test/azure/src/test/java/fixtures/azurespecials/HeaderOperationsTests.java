package fixtures.azurespecials;

import com.microsoft.rest.v2.RestResponse;
import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicy;
import com.microsoft.rest.v2.policy.PortPolicy;
import com.microsoft.rest.v2.policy.ProtocolPolicy;
import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;
import fixtures.azurespecials.models.HeaderCustomNamedRequestIdHeadHeaders;
import fixtures.azurespecials.models.HeaderCustomNamedRequestIdHeaders;
import fixtures.azurespecials.models.HeaderCustomNamedRequestIdParamGroupingHeaders;
import fixtures.azurespecials.models.HeaderCustomNamedRequestIdParamGroupingParameters;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class HeaderOperationsTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestAzureSpecialParametersTestClientImpl(
            HttpPipeline.build(
                new ProtocolPolicy.Factory("http"),
                new PortPolicy.Factory(3000),
                new CredentialsPolicy.Factory(new BasicAuthenticationCredentials(null, null))));
    }

    @Test
    public void customNamedRequestId() throws Exception {
        RestResponse<HeaderCustomNamedRequestIdHeaders, Void> response = client.headers().customNamedRequestIdWithRestResponseAsync("9C4D50EE-2D56-4CD3-8152-34347DC9F2B0").blockingGet();
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("123", response.headers().fooRequestId());
    }

    @Test
    public void customNamedRequestIdParamGrouping() throws Exception {
        HeaderCustomNamedRequestIdParamGroupingParameters group = new HeaderCustomNamedRequestIdParamGroupingParameters();
        group.withFooClientRequestId("9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");
        RestResponse<HeaderCustomNamedRequestIdParamGroupingHeaders, Void> response = client.headers().customNamedRequestIdParamGroupingWithRestResponseAsync(group).blockingGet();
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("123", response.headers().fooRequestId());
    }

    @Test
    public void customNamedRequestIdHead() throws Exception {
        RestResponse<HeaderCustomNamedRequestIdHeadHeaders, Boolean> response = client.headers().customNamedRequestIdHeadWithRestResponseAsync("9C4D50EE-2D56-4CD3-8152-34347DC9F2B0").blockingGet();
        Assert.assertEquals(200, response.statusCode());
        Assert.assertTrue(response.body());
        Assert.assertEquals("123", response.headers().fooRequestId());
    }
}
