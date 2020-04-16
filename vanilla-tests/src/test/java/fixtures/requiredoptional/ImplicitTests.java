package fixtures.requiredoptional;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ImplicitTests {
    private static AutoRestRequiredOptionalTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestRequiredOptionalTestServiceBuilder().buildClient();
    }

    @Test
    public void getRequiredPath() throws Exception {
        try {
            client.getImplicits().getRequiredPath(null);
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Parameter pathParameter is required"));
        }
    }

    @Test
    public void putOptionalQuery() throws Exception {
        client.getImplicits().putOptionalQuery(null);
    }

    @Test
    public void putOptionalHeader() throws Exception {
        client.getImplicits().putOptionalHeader(null);
    }

    @Test
    public void putOptionalBody() throws Exception {
        client.getImplicits().putOptionalBody(null);
    }

    @Test
    public void getRequiredGlobalPath() throws Exception {
        try {
            client.getImplicits().getRequiredGlobalPath();
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("this.client.getRequiredGlobalPath() is required"));
        }
    }

    @Test
    public void getRequiredGlobalQuery() throws Exception {
        try {
            client.getImplicits().getRequiredGlobalQuery();
            fail();
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("this.client.getRequiredGlobalQuery() is required"));
        }
    }

    @Test
    public void getOptionalGlobalQuery() throws Exception {
        client.getImplicits().getOptionalGlobalQuery();
    }
}
