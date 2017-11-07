package fixtures.azurespecials;

import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
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
        Void response = client.apiVersionDefaults().getMethodGlobalValidAsync().blockingGet();
//        Assert.assertEquals(200, response.response().code());
    }

    @Test
    public void getMethodGlobalNotProvidedValid() throws Exception {
        Void response = client.apiVersionDefaults().getMethodGlobalNotProvidedValidAsync().blockingGet();
//        Assert.assertEquals(200, response.response().code());
    }

    @Test
    public void getPathGlobalValid() throws Exception {
        Void response = client.apiVersionDefaults().getPathGlobalValidAsync().blockingGet();
//        Assert.assertEquals(200, response.response().code());
    }

    @Test
    public void getSwaggerGlobalValid() throws Exception {
        Void response = client.apiVersionDefaults().getSwaggerGlobalValidAsync().blockingGet();
//        Assert.assertEquals(200, response.response().code());
    }
}
