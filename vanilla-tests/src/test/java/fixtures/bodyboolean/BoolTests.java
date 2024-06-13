package fixtures.bodyboolean;

import com.azure.core.exception.HttpResponseException;
import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoolTests {
    private static AutoRestBoolTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestBoolTestServiceBuilder().buildClient();
    }

    @Test
    public void getNull() {
        assertThrows(NullPointerException.class, () -> client.getBools().getNull());
    }

    @Test
    public void getInvalid() {
        HttpResponseException ex = assertThrows(HttpResponseException.class, () -> client.getBools().getInvalid());
        assertInstanceOf(JsonParseException.class, ex.getCause());
    }

    @Test
    public void getTrue() {
        boolean result = client.getBools().getTrue();
        assertTrue(result);
    }

    @Test
    public void getFalse() {
        boolean result = client.getBools().getFalse();
        assertFalse(result);
    }

    @Test
    public void putTrue() {
        client.getBools().putTrueWithResponseAsync().block();
    }

    @Test
    public void putFalse() {
        client.getBools().putFalseWithResponseAsync().block();
    }
}
