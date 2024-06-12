package fixtures.requiredoptional;

import fixtures.requiredoptional.models.ArrayOptionalWrapper;
import fixtures.requiredoptional.models.ArrayWrapper;
import fixtures.requiredoptional.models.ClassOptionalWrapper;
import fixtures.requiredoptional.models.ClassWrapper;
import fixtures.requiredoptional.models.IntOptionalWrapper;
import fixtures.requiredoptional.models.StringOptionalWrapper;
import fixtures.requiredoptional.models.StringWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExplicitTests {
    private static AutoRestRequiredOptionalTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestRequiredOptionalTestServiceBuilder()
            .requiredGlobalPath("path")
            .requiredGlobalQuery("query")
            .buildClient();
    }

    @Test
    public void postRequiredIntegerParameter() {
        // Compile time error
        //client.getExplicits().postRequiredIntegerParameter(null);
    }

    @Test
    public void postOptionalIntegerParameter() {
        client.getExplicits().postOptionalIntegerParameter(null);
    }

    @Test
    public void postRequiredIntegerProperty() {
        // Compile time error
        //IntWrapper body = new IntWrapper();
        //body.value(null);
    }

    @Test
    public void postOptionalIntegerProperty() {
        IntOptionalWrapper body = new IntOptionalWrapper();
        body.setValue(null);
        client.getExplicits().postOptionalIntegerProperty(body);
    }

    @Test
    public void postRequiredIntegerHeader() {
        // Compile time error
        //client.getExplicits().postRequiredIntegerHeader(null);
    }

    @Test
    public void postOptionalIntegerHeader() {
        client.getExplicits().postOptionalIntegerHeader(null);
    }

    @Test
    public void postRequiredStringParameter() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> client.getExplicits().postRequiredStringParameter(null));
        assertTrue(ex.getMessage().contains("Parameter bodyParameter is required"));
    }

    @Test
    public void postOptionalStringParameter() {
        client.getExplicits().postOptionalStringParameter(null);
    }

    @Test
    public void postRequiredStringProperty() {
        StringWrapper body = new StringWrapper().setValue(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> client.getExplicits().postRequiredStringProperty(body));
        assertTrue(ex.getMessage().contains("Missing required property value"));
    }

    @Test
    public void postOptionalStringProperty() {
        StringOptionalWrapper body = new StringOptionalWrapper();
        body.setValue(null);
        client.getExplicits().postOptionalStringProperty(body);
    }

    @Test
    public void postRequiredStringHeader() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> client.getExplicits().postRequiredStringHeader(null));
        assertTrue(ex.getMessage().contains("Parameter headerParameter is required"));
    }

    @Test
    public void postOptionalStringHeader() {
        client.getExplicits().postOptionalStringHeader(null);
    }

    @Test
    public void postRequiredClassParameter() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> client.getExplicits().postRequiredClassParameter(null));
        assertTrue(ex.getMessage().contains("Parameter bodyParameter is required"));
    }

    @Test
    public void postOptionalClassParameter() {
        client.getExplicits().postOptionalClassParameter(null);
    }

    @Test
    public void postRequiredClassProperty() {
        ClassWrapper body = new ClassWrapper().setValue(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> client.getExplicits().postRequiredClassProperty(body));
        assertTrue(ex.getMessage().contains("Missing required property value"));
    }

    @Test
    public void postOptionalClassProperty() {
        ClassOptionalWrapper body = new ClassOptionalWrapper();
        body.setValue(null);
        client.getExplicits().postOptionalClassProperty(body);
    }

    @Test
    public void postRequiredArrayParameter() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> client.getExplicits().postRequiredArrayParameter(null));
        assertTrue(ex.getMessage().contains("Parameter bodyParameter is required"));
    }

    @Test
    public void postOptionalArrayParameter() {
        client.getExplicits().postOptionalArrayParameter(null);
    }

    @Test
    public void postRequiredArrayProperty() {
        ArrayWrapper body = new ArrayWrapper().setValue(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> client.getExplicits().postRequiredArrayProperty(body));
        assertTrue(ex.getMessage().contains("Missing required property value"));
    }

    @Test
    public void postOptionalArrayProperty() {
        ArrayOptionalWrapper body = new ArrayOptionalWrapper();
        body.setValue(null);
        client.getExplicits().postOptionalArrayProperty(body);
    }

    @Test
    public void postRequiredArrayHeader() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> client.getExplicits().postRequiredArrayHeader(null));
        assertTrue(ex.getMessage().contains("Parameter headerParameter is required"));
    }

    @Test
    public void postOptionalArrayHeader() {
        client.getExplicits().postOptionalArrayHeader(null);
    }
}
