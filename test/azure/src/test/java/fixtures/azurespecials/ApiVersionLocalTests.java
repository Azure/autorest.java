package fixtures.azurespecials;

import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicy;
import com.microsoft.rest.v2.policy.PortPolicy;
import com.microsoft.rest.v2.policy.ProtocolPolicy;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;

@Ignore("RestProxy doesn't currently support ServiceResponse")
public class ApiVersionLocalTests {
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
    public void getMethodLocalValid() throws Exception {
        Void response = client.apiVersionLocals().getMethodLocalValidAsync().toBlocking().value();
//        Assert.assertEquals(200, response.response().code());
    }

    @Test
    public void getMethodGlobalNotProvidedValid() throws Exception {
        Void response = client.apiVersionLocals().getMethodLocalNullAsync().toBlocking().value();
//        Assert.assertEquals(200, response.response().code());
    }

    @Test
    public void getPathGlobalValid() throws Exception {
        Void response = client.apiVersionLocals().getPathLocalValidAsync().toBlocking().value();
//        Assert.assertEquals(200, response.response().code());
    }

    @Test
    public void getSwaggerGlobalValid() throws Exception {
        Void response = client.apiVersionLocals().getSwaggerLocalValidAsync().toBlocking().value();
//        Assert.assertEquals(200, response.response().code());
    }
}
