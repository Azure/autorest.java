package fixtures.azurespecials;

import com.microsoft.rest.v3.RestResponse;
import com.microsoft.rest.v3.credentials.BasicAuthenticationCredentials;
import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;
import fixtures.azurespecials.models.HeaderCustomNamedRequestIdHeadHeaders;
import fixtures.azurespecials.models.HeaderCustomNamedRequestIdHeaders;
import fixtures.azurespecials.models.HeaderCustomNamedRequestIdParamGroupingHeaders;
import fixtures.azurespecials.models.HeadersCustomNamedRequestIdParamGroupingParameters;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HeaderOperationsTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestAzureSpecialParametersTestClientImpl(
            new BasicAuthenticationCredentials(null, null));
    }

    @Test
    public void customNamedRequestId() {
        RestResponse<HeaderCustomNamedRequestIdHeaders, Void> response = client.headers().customNamedRequestIdWithRestResponseAsync("9C4D50EE-2D56-4CD3-8152-34347DC9F2B0").block();
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("123", response.headers().fooRequestId());
    }

    @Test
    public void customNamedRequestIdParamGrouping() {
        HeadersCustomNamedRequestIdParamGroupingParameters group = new HeadersCustomNamedRequestIdParamGroupingParameters();
        group.withFooClientRequestId("9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");
        RestResponse<HeaderCustomNamedRequestIdParamGroupingHeaders, Void> response = client.headers().customNamedRequestIdParamGroupingWithRestResponseAsync(group).block();
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("123", response.headers().fooRequestId());
    }

    @Test
    public void customNamedRequestIdHead() {
        RestResponse<HeaderCustomNamedRequestIdHeadHeaders, Boolean> response = client.headers().customNamedRequestIdHeadWithRestResponseAsync("9C4D50EE-2D56-4CD3-8152-34347DC9F2B0").block();
        Assert.assertEquals(200, response.statusCode());
        Assert.assertTrue(response.body());
        Assert.assertEquals("123", response.headers().fooRequestId());
    }
}
