package fixtures.requiredoptional;

import fixtures.requiredoptional.models.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ExplicitTests {
    private static AutoRestRequiredOptionalTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestRequiredOptionalTestServiceBuilder().buildClient();
    }

    @Test
    public void postRequiredIntegerParameter() throws Exception {
        // Compile time error
        //client.getExplicits().postRequiredIntegerParameter(null);
    }

    @Test
    public void postOptionalIntegerParameter() throws Exception {
        client.getExplicits().postOptionalIntegerParameter(null);
    }

    @Test
    public void postRequiredIntegerProperty() throws Exception {
        // Compile time error
        //IntWrapper body = new IntWrapper();
        //body.value(null);
    }

    @Test
    public void postOptionalIntegerProperty() throws Exception {
        IntOptionalWrapper body = new IntOptionalWrapper();
        body.setValue(null);
        client.getExplicits().postOptionalIntegerProperty(body);
    }

    @Test
    public void postRequiredIntegerHeader() throws Exception {
        // Compile time error
        //client.getExplicits().postRequiredIntegerHeader(null);
    }

    @Test
    public void postOptionalIntegerHeader() throws Exception {
        client.getExplicits().postOptionalIntegerHeader(null);
    }

    @Test
    public void postRequiredStringParameter() throws Exception {
        try {
            client.getExplicits().postRequiredStringParameter(null);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter bodyParameter is required"));
        }
    }

    @Test
    public void postOptionalStringParameter() throws Exception {
        client.getExplicits().postOptionalStringParameter(null);
    }

    @Test
    public void postRequiredStringProperty() throws Exception {
        try {
            StringWrapper body = new StringWrapper();
            body.setValue(null);
            client.getExplicits().postRequiredStringProperty(body);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Missing required property value"));
        }
    }

    @Test
    public void postOptionalStringProperty() throws Exception {
        StringOptionalWrapper body = new StringOptionalWrapper();
        body.setValue(null);
        client.getExplicits().postOptionalStringProperty(body);
    }

    @Test
    public void postRequiredStringHeader() throws Exception {
        try {
            client.getExplicits().postRequiredStringHeader(null);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter headerParameter is required"));
        }
    }

    @Test
    public void postOptionalStringHeader() throws Exception {
        client.getExplicits().postOptionalStringHeader(null);
    }

    @Test
    public void postRequiredClassParameter() throws Exception {
        try {
            client.getExplicits().postRequiredClassParameter(null);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter bodyParameter is required"));
        }
    }

    @Test
    public void postOptionalClassParameter() throws Exception {
        client.getExplicits().postOptionalClassParameter(null);
    }

    @Test
    public void postRequiredClassProperty() throws Exception {
        try {
            ClassWrapper body = new ClassWrapper();
            body.setValue(null);
            client.getExplicits().postRequiredClassProperty(body);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Missing required property value"));
        }
    }

    @Test
    public void postOptionalClassProperty() throws Exception {
        ClassOptionalWrapper body = new ClassOptionalWrapper();
        body.setValue(null);
        client.getExplicits().postOptionalClassProperty(body);
    }

    @Test
    public void postRequiredArrayParameter() throws Exception {
        try {
            client.getExplicits().postRequiredArrayParameter(null);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter bodyParameter is required"));
        }
    }

    @Test
    public void postOptionalArrayParameter() throws Exception {
        client.getExplicits().postOptionalArrayParameter(null);
    }

    @Test
    public void postRequiredArrayProperty() throws Exception {
        try {
            ArrayWrapper body = new ArrayWrapper();
            body.setValue(null);
            client.getExplicits().postRequiredArrayProperty(body);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Missing required property value"));
        }
    }

    @Test
    public void postOptionalArrayProperty() throws Exception {
        ArrayOptionalWrapper body = new ArrayOptionalWrapper();
        body.setValue(null);
        client.getExplicits().postOptionalArrayProperty(body);
    }

    @Test
    public void postRequiredArrayHeader() throws Exception {
        try {
            client.getExplicits().postRequiredArrayHeader(null);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter headerParameter is required"));
        }
    }

    @Test
    public void postOptionalArrayHeader() throws Exception {
        client.getExplicits().postOptionalArrayHeader(null);
    }
}
