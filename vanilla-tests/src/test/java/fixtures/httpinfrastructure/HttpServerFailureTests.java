package fixtures.httpinfrastructure;

import fixtures.httpinfrastructure.models.ErrorException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HttpServerFailureTests {
    private static AutoRestHttpInfrastructureTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestHttpInfrastructureTestServiceBuilder().buildClient();
    }

    @Test
    public void head501() {
      ErrorException ex = assertThrows(ErrorException.class, () -> client.getHttpServerFailures().head501());
      assertEquals(501, ex.getResponse().getStatusCode());
    }

    @Test
    public void get501() {
      ErrorException ex = assertThrows(ErrorException.class, () -> client.getHttpServerFailures().get501());
      assertEquals(501, ex.getResponse().getStatusCode());
    }

    @Test
    public void post505() {
      ErrorException ex = assertThrows(ErrorException.class, () -> client.getHttpServerFailures().post505());
      assertEquals(505, ex.getResponse().getStatusCode());
    }

    @Test
    public void delete505() {
      ErrorException ex = assertThrows(ErrorException.class, () -> client.getHttpServerFailures().delete505());
      assertEquals(505, ex.getResponse().getStatusCode());
    }
}
