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
        client = new AutoRestComplexTestService().setApiVersion("2015-05-01");
    }

    @Test
    public void getValid() throws Exception {
        Basic result = client.basics().getValidWithResponseAsync().block().getValue();
        Assert.assertEquals(2, result.getId());
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
            client.basics().getInvalidWithResponseAsync().block();
            Assert.assertTrue(false);
        } catch (Exception exception) {
            // expected
            Assert.assertEquals(InvalidFormatException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getEmpty() throws Exception {
        Basic result = client.basics().getEmptyWithResponseAsync().block().getValue();
        Assert.assertNull(result.getName());
    }

    @Test
    public void getNull() throws Exception {
        Basic result = client.basics().getNullWithResponseAsync().block().getValue();
        Assert.assertNull(result.getName());
    }

    @Test
    public void getNotProvided() throws Exception {
        Assert.assertNull(client.basics().getNotProvidedWithResponseAsync().block().getValue());
    }
}