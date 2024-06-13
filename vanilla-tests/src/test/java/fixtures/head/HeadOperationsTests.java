package fixtures.head;

import com.azure.core.http.rest.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeadOperationsTests {
    private static AutoRestHeadTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestHeadTestServiceBuilder().buildClient();
    }

    @Test
    public void head200() {
        assertEquals(200, client.getHttpSuccess().head200WithResponseAsync().block().getStatusCode());
    }

    @Test
    public void head204() {
        Response<Boolean> response = client.getHttpSuccess().head204WithResponseAsync().block();
        assertEquals(204, response.getStatusCode());
        assertTrue(response.getValue()); // 204 means true
    }

    @Test
    public void head404() {
        Response<Boolean> response = client.getHttpSuccess().head404WithResponseAsync().block();
        // both status code 204 and 404 is not error, 404 means false
        assertEquals(404, response.getStatusCode());
        assertFalse(response.getValue());
    }
}
