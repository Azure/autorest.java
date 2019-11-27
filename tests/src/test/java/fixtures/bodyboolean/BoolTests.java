package fixtures.bodyboolean;

import com.azure.core.implementation.serializer.MalformedValueException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.fail;

public class BoolTests {
    private static AutoRestBoolTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestBoolTestServiceBuilder().build();
    }

    @Test
    public void getNull() throws Exception {
        try {
            boolean b = client.bools().getNullWithResponseAsync().block().getValue();
            fail();
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void getInvalid() throws Exception {
        try {
            client.bools().getInvalidWithResponseAsync().block().getValue();
            Assert.assertTrue(false);
        } catch (Exception exception) {
            // expected
            Assert.assertEquals(MalformedValueException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getTrue() throws Exception {
        boolean result = client.bools().getTrueWithResponseAsync().block().getValue();
        Assert.assertTrue(result);
    }

    @Test
    public void getFalse() throws Exception {
        boolean result = client.bools().getFalseWithResponseAsync().block().getValue();
        Assert.assertFalse(result);
    }

    @Test
    public void putTrue() throws Exception {
        client.bools().putTrueWithResponseAsync(true);
    }

    @Test
    public void putFalse() throws Exception {
        client.bools().putFalseWithResponseAsync(false);
    }
}
