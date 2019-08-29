package fixtures.requiredoptional;

import fixtures.requiredoptional.implementation.AutoRestRequiredOptionalTestServiceImpl;
import fixtures.requiredoptional.models.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ExplicitTests {
    private static AutoRestRequiredOptionalTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestRequiredOptionalTestServiceImpl();
    }

    @Test
    public void postRequiredIntegerParameter() throws Exception {
        // Compile time error
        //client.explicits().postRequiredIntegerParameter(null);
    }

    @Test
    public void postOptionalIntegerParameter() throws Exception {
        client.explicits().postOptionalIntegerParameter(null);
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
        body.value(null);
        client.explicits().postOptionalIntegerProperty(body);
    }

    @Test
    public void postRequiredIntegerHeader() throws Exception {
        // Compile time error
        //client.explicits().postRequiredIntegerHeader(null);
    }

    @Test
    public void postOptionalIntegerHeader() throws Exception {
        client.explicits().postOptionalIntegerHeader(null);
    }

    @Test
    public void postRequiredStringParameter() throws Exception {
        try {
            client.explicits().postRequiredStringParameter(null);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter bodyParameter is required"));
        }
    }

    @Test
    public void postOptionalStringParameter() throws Exception {
        client.explicits().postOptionalStringParameter(null);
    }

    @Test
    public void postRequiredStringProperty() throws Exception {
        try {
            StringWrapper body = new StringWrapper();
            body.value(null);
            client.explicits().postRequiredStringProperty(body);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("value is required"));
        }
    }

    @Test
    public void postOptionalStringProperty() throws Exception {
        StringOptionalWrapper body = new StringOptionalWrapper();
        body.value(null);
        client.explicits().postOptionalStringProperty(body);
    }

    @Test
    public void postRequiredStringHeader() throws Exception {
        try {
            client.explicits().postRequiredStringHeader(null);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter headerParameter is required"));
        }
    }

    @Test
    public void postOptionalStringHeader() throws Exception {
        client.explicits().postOptionalStringHeader(null);
    }

    @Test
    public void postRequiredClassParameter() throws Exception {
        try {
            client.explicits().postRequiredClassParameter(null);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter bodyParameter is required"));
        }
    }

    @Test
    public void postOptionalClassParameter() throws Exception {
        client.explicits().postOptionalClassParameter(null);
    }

    @Test
    public void postRequiredClassProperty() throws Exception {
        try {
            ClassWrapper body = new ClassWrapper();
            body.value(null);
            client.explicits().postRequiredClassProperty(body);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("value is required"));
        }
    }

    @Test
    public void postOptionalClassProperty() throws Exception {
        ClassOptionalWrapper body = new ClassOptionalWrapper();
        body.value(null);
        client.explicits().postOptionalClassProperty(body);
    }

    @Test
    public void postRequiredArrayParameter() throws Exception {
        try {
            client.explicits().postRequiredArrayParameter(null);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter bodyParameter is required"));
        }
    }

    @Test
    public void postOptionalArrayParameter() throws Exception {
        client.explicits().postOptionalArrayParameter(null);
    }

    @Test
    public void postRequiredArrayProperty() throws Exception {
        try {
            ArrayWrapper body = new ArrayWrapper();
            body.value(null);
            client.explicits().postRequiredArrayProperty(body);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("value is required"));
        }
    }

    @Test
    public void postOptionalArrayProperty() throws Exception {
        ArrayOptionalWrapper body = new ArrayOptionalWrapper();
        body.value(null);
        client.explicits().postOptionalArrayProperty(body);
    }

    @Test
    public void postRequiredArrayHeader() throws Exception {
        try {
            client.explicits().postRequiredArrayHeader(null);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter headerParameter is required"));
        }
    }

    @Test
    public void postOptionalArrayHeader() throws Exception {
        client.explicits().postOptionalArrayHeader(null);
    }
}
