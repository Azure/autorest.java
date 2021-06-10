package fixtures.bodyboolean;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.implementation.serializer.MalformedValueException;
import com.fasterxml.jackson.core.JsonParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.fail;

public class BoolTests {
    private static AutoRestBoolTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestBoolTestServiceBuilder().buildClient();
    }

    @Test
    public void getNull() throws Exception {
        try {
            boolean b = client.getBools().getNull();
            fail();
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void getInvalid() throws Exception {
        try {
            client.getBools().getInvalid();
            Assert.assertTrue(false);
        } catch (HttpResponseException exception) {
            // expected
            Assert.assertTrue(exception.getCause() instanceof JsonParseException);
        }
    }

    @Test
    public void getTrue() throws Exception {
        boolean result = client.getBools().getTrue();
        Assert.assertTrue(result);
    }

    @Test
    public void getFalse() throws Exception {
        boolean result = client.getBools().getFalse();
        Assert.assertFalse(result);
    }

    @Test
    public void putTrue() throws Exception {
        client.getBools().putTrueWithResponseAsync().block();
    }

    @Test
    public void putFalse() throws Exception {
        client.getBools().putFalseWithResponseAsync().block();
    }
}
