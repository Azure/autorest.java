package fixtures.bodycomplex;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import com.microsoft.rest.v2.LogLevel;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.PortPolicy;
import fixtures.bodycomplex.implementation.AutoRestComplexTestServiceImpl;
import fixtures.bodycomplex.models.Basic;
import fixtures.bodycomplex.models.CMYKColors;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BasicOperationsTests {
    private static AutoRestComplexTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestComplexTestServiceImpl(HttpPipeline.build(new PortPolicy.Factory(3000)));
        client.withApiVersion("2015-05-01");
    }

    @Test
    public void getValid() throws Exception {
        Basic result = client.basics().getValid();
        Assert.assertEquals(2, result.id().intValue());
        Assert.assertEquals("abc", result.name());
        Assert.assertEquals(CMYKColors.YELLOW, result.color());
    }

    @Test
    public void putValid() throws Exception {
        Basic body = new Basic();
        body.withId(2);
        body.withName("abc");
        body.withColor(CMYKColors.MAGENTA);
        client.basics().putValid(body);
    }

    @Test
    public void getInvalid() throws Exception {
        try {
            client.basics().getInvalid();
            Assert.assertTrue(false);
        } catch (Exception exception) {
            // expected
            Assert.assertEquals(InvalidFormatException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getEmpty() throws Exception {
        Basic result = client.basics().getEmpty();
        Assert.assertNull(result.name());
    }

    @Test
    public void getNull() throws Exception {
        Basic result = client.basics().getNullWithRestResponseAsync().blockingGet().body();
        Assert.assertNull(result.name());
    }

    @Test
    public void getNotProvided() throws Exception {
        Assert.assertNull(client.basics().getNotProvidedWithRestResponseAsync().blockingGet().body());
    }
}
