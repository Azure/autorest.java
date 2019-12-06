package fixtures.bodycomplex;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import fixtures.bodycomplex.models.Basic;
import fixtures.bodycomplex.models.CMYKColors;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BasicOperationsTests {
    private static AutoRestComplexTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestComplexTestServiceBuilder().apiVersion("2015-05-01").build();
    }

    @Test
    public void getValid() throws Exception {
        Basic result = client.basics().getValid();
        Assert.assertEquals(2, result.getId().intValue());
        Assert.assertEquals("abc", result.getName());
        Assert.assertEquals(CMYKColors.YELLOW, result.getColor());
    }

    @Test
    public void putValid() throws Exception {
        Basic body = new Basic();
        body.setId(2);
        body.setName("abc");
        body.setColor(CMYKColors.MAGENTA);
        client.basics().putValidWithResponseAsync(body).block();
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
        Assert.assertNull(result.getName());
    }

    @Test
    public void getNull() throws Exception {
        Basic result = client.basics().getNull();
        Assert.assertNull(result.getName());
    }

    @Test
    public void getNotProvided() throws Exception {
        Assert.assertNull(client.basics().getNotProvided());
    }
}
