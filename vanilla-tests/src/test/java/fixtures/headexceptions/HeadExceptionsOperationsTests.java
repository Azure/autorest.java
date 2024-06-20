package fixtures.headexceptions;

import com.azure.core.exception.HttpResponseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HeadExceptionsOperationsTests {
    private static AutoRestHeadExceptionTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestHeadExceptionTestServiceBuilder().buildClient();
    }

    @Test
    public void head200() {
        assertEquals(200, client.getHeadExceptions().head200WithResponseAsync().block().getStatusCode());
    }

    @Test
    public void head204() {
        assertEquals(204, client.getHeadExceptions().head204WithResponseAsync().block().getStatusCode());
    }

    @Test
    public void head404() {
        assertThrows(HttpResponseException.class, () -> client.getHeadExceptions().head404());
    }
}
