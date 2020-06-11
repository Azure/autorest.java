package fixtures.validation;

import org.junit.BeforeClass;
import org.junit.Test;

public class ValidationTests {
    private static AutoRestValidationTest client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestValidationTestBuilder().buildClient();
    }

    @Test
    public void getWithConstantInPath() {
        client.getWithConstantInPath();
    }
}
