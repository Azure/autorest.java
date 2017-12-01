package fixtures.headexceptions;

import com.microsoft.rest.v2.RestException;
import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicy;
import com.microsoft.rest.v2.policy.PortPolicy;
import com.microsoft.rest.v2.policy.ProtocolPolicy;
import org.junit.BeforeClass;
import org.junit.Test;

import fixtures.headexceptions.implementation.AutoRestHeadExceptionTestServiceImpl;

public class HeadExceptionTests {
    private static AutoRestHeadExceptionTestServiceImpl client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestHeadExceptionTestServiceImpl(
            HttpPipeline.build(
                new ProtocolPolicy.Factory("http"),
                new PortPolicy.Factory(3000),
                new CredentialsPolicy.Factory(new BasicAuthenticationCredentials(null, null))));
    }

    @Test
    public void headException200() throws Exception {
        client.headExceptions().head200();
    }

    @Test
    public void headException204() throws Exception {
        client.headExceptions().head204();
    }

    @Test(expected = RestException.class)
    public void headException404() throws Exception {
        client.headExceptions().head404();
    }
}
