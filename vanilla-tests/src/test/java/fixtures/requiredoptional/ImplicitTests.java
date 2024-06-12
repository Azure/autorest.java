package fixtures.requiredoptional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImplicitTests {
    private static AutoRestRequiredOptionalTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestRequiredOptionalTestServiceBuilder()
            .requiredGlobalPath("path")
            .requiredGlobalQuery("query")
            .buildClient();
    }

    @Test
    public void getRequiredPath() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> client.getImplicits().getRequiredPath(null));
        assertTrue(exception.getMessage().contains("Parameter pathParameter is required"));
    }

    @Test
    public void putOptionalQuery() {
        client.getImplicits().putOptionalQuery(null);
    }

    @Test
    public void putOptionalHeader() {
        client.getImplicits().putOptionalHeader(null);
    }

    @Test
    public void putOptionalBody() {
        client.getImplicits().putOptionalBody(null);
    }

    @Disabled("Client builder requires requiredGlobalPath and requiredGlobalQuery, so this case cannot happen")
    @Test
    public void getRequiredGlobalPath() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> client.getImplicits().getRequiredGlobalPath());
        assertTrue(exception.getMessage().contains("this.client.getRequiredGlobalPath() is required"));
    }

    @Disabled("Client builder requires requiredGlobalPath and requiredGlobalQuery, so this case cannot happen")
    @Test
    public void getRequiredGlobalQuery() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> client.getImplicits().getRequiredGlobalQuery());
        assertTrue(exception.getMessage().contains("this.client.getRequiredGlobalQuery() is required"));
    }

    @Test
    public void getOptionalGlobalQuery() {
        client.getImplicits().getOptionalGlobalQuery();
    }
}
