package fixtures.azurespecials;

import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicyFactory;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import com.microsoft.rest.v2.policy.ProtocolPolicyFactory;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import fixtures.azurespecials.implementation.AutoRestAzureSpecialParametersTestClientImpl;
import fixtures.azurespecials.implementation.SkipUrlEncodingsInner;

public class SkipUrlEncodingTests {
    private static String unencodedPath = "path1/path2/path3";
    private static String unencodedQuery = "value1&q2=value2&q3=value3";

    private static SkipUrlEncodingsInner client;

    @BeforeClass
    public static void setup() {
        final HttpPipeline httpPipeline = HttpPipeline.build(
                new ProtocolPolicyFactory("http"),
                new PortPolicyFactory(3000),
                new CredentialsPolicyFactory(new BasicAuthenticationCredentials(null, null)));
        client = new AutoRestAzureSpecialParametersTestClientImpl(httpPipeline).skipUrlEncodings();
    }

    @Test
    public void getMethodPathValid() throws Exception {
        client.getMethodPathValid(unencodedPath);
    }

    @Test
    public void getPathPathValid() throws Exception {
        client.getPathPathValid(unencodedPath);
    }

    @Test
    public void getSwaggerPathValid() throws Exception {
        client.getSwaggerPathValid();
    }

    @Ignore("Not supported by OkHttp: https://github.com/square/okhttp/issues/2623")
    public void getMethodQueryValid() throws Exception {
        client.getMethodQueryValid(unencodedQuery);
    }

    @Ignore("Not supported by OkHttp: https://github.com/square/okhttp/issues/2623")
    public void getPathQueryValid() throws Exception {
        client.getPathQueryValid(unencodedQuery);
    }

    @Ignore("Not supported by OkHttp: https://github.com/square/okhttp/issues/2623")
    public void getSwaggerQueryValid() throws Exception {
        client.getSwaggerQueryValid();
    }

    @Test
    public void getMethodQueryNull() throws Exception {
        client.getMethodQueryNull(null);
    }

}
