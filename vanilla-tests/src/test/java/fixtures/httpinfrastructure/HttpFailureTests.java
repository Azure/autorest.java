package fixtures.httpinfrastructure;

import com.azure.core.exception.HttpResponseException;
import fixtures.httpinfrastructure.models.ErrorException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class HttpFailureTests {
    private static AutoRestHttpInfrastructureTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestHttpInfrastructureTestServiceBuilder().buildClient();
    }

    @Test
    public void getEmptyError() {
        ErrorException ex = assertThrows(ErrorException.class, () -> client.getHttpFailures().getEmptyError());
        assertEquals(400, ex.getResponse().getStatusCode());
    }

    @Test
    public void getNoModelError() {
        HttpResponseException ex = assertThrows(HttpResponseException.class, () -> client.getHttpFailures().getNoModelError());
        assertEquals(400, ex.getResponse().getStatusCode());
    }
}
