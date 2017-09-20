package fixtures.azurespecials;

import com.microsoft.rest.ServiceResponse;
import com.microsoft.rest.credentials.BasicAuthenticationCredentials;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;

@Ignore("RestProxy doesn't currently support response status or headers")
public class ApiVersionDefaultTests {
    private static AutoRestAzureSpecialParametersTestClientImpl client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestAzureSpecialParametersTestClientImpl("http://localhost:3000", new BasicAuthenticationCredentials(null, null));
    }

    @Test
    public void getMethodGlobalValid() throws Exception {
        Void response = client.apiVersionDefaults().getMethodGlobalValidAsync().toBlocking().value();
//        Assert.assertEquals(200, response.response().code());
    }

    @Test
    public void getMethodGlobalNotProvidedValid() throws Exception {
        Void response = client.apiVersionDefaults().getMethodGlobalNotProvidedValidAsync().toBlocking().value();
//        Assert.assertEquals(200, response.response().code());
    }

    @Test
    public void getPathGlobalValid() throws Exception {
        Void response = client.apiVersionDefaults().getPathGlobalValidAsync().toBlocking().value();
//        Assert.assertEquals(200, response.response().code());
    }

    @Test
    public void getSwaggerGlobalValid() throws Exception {
        Void response = client.apiVersionDefaults().getSwaggerGlobalValidAsync().toBlocking().value();
//        Assert.assertEquals(200, response.response().code());
    }
}
