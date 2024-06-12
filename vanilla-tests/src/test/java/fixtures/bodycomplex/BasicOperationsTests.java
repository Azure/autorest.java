package fixtures.bodycomplex;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import fixtures.bodycomplex.models.Basic;
import fixtures.bodycomplex.models.CMYKColors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasicOperationsTests {
    private static AutoRestComplexTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestComplexTestServiceBuilder().buildClient();
    }

    @Test
    public void getValid() {
        Basic result = client.getBasics().getValid();
        assertEquals(2, result.getId().intValue());
        assertEquals("abc", result.getName());
        assertEquals(CMYKColors.YELLOW, result.getColor());
    }

    @Test
    public void putValid() {
        Basic body = new Basic();
        body.setId(2);
        body.setName("abc");
        body.setColor(CMYKColors.MAGENTA);
        client.getBasics().putValidWithResponseAsync(body).block();
    }

    @Test
    public void getInvalid() {
        Exception exception = assertThrows(Exception.class, () -> client.getBasics().getInvalid());
        assertInstanceOf(IOException.class, exception.getCause());
    }

    @Test
    public void getEmpty() throws Exception {
        Basic result = client.getBasics().getEmpty();
        assertNull(result.getName());
    }

    @Test
    public void getNull() throws Exception {
        Basic result = client.getBasics().getNull();
        assertNull(result.getName());
    }

    @Test
    public void getNotProvided() {
        assertNull(client.getBasics().getNotProvided());
    }
}
