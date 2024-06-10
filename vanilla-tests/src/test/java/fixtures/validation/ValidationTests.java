package fixtures.validation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ValidationTests {
    private static AutoRestValidationTest client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestValidationTestBuilder().buildClient();
    }

    @Test
    public void getWithConstantInPath() {
        client.getWithConstantInPath();
    }
}
