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
            boolean b = client.bools().getNull();
            fail();
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void getInvalid() throws Exception {
        try {
            client.bools().getInvalid();
            Assert.assertTrue(false);
        } catch (Exception exception) {
            // expected
            Assert.assertEquals(MalformedValueException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getTrue() throws Exception {
        boolean result = client.bools().getTrue();
        Assert.assertTrue(result);
    }

    @Test
    public void getFalse() throws Exception {
        boolean result = client.bools().getFalse();
        Assert.assertFalse(result);
    }

    @Test
    public void putTrue() throws Exception {
        client.bools().putTrueWithResponseAsync(true).block();
    }

    @Test
    public void putFalse() throws Exception {
        client.bools().putFalseWithResponseAsync(false).block();
    }
}
