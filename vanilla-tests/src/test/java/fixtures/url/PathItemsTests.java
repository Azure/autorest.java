package fixtures.url;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PathItemsTests {
    private static AutoRestUrlTestServiceBuilder client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestUrlTestServiceBuilder();
    }

    @Test
    public void getAllWithValues() {
        client.globalStringPath("globalStringPath");
        client.globalStringQuery("globalStringQuery");
        client.buildClient()
            .getPathItems()
            .getAllWithValues("pathItemStringPath", "localStringPath", "pathItemStringQuery", "localStringQuery");
    }

    @Test
    public void getGlobalQueryNull() {
        client.globalStringPath("globalStringPath");
        client.globalStringQuery(null);
        client.buildClient()
            .getPathItems()
            .getGlobalQueryNull("pathItemStringPath", "localStringPath", "pathItemStringQuery", "localStringQuery");
    }

    @Test
    public void getGlobalAndLocalQueryNull() {
        client.globalStringPath("globalStringPath");
        client.globalStringQuery(null);
        client.buildClient()
            .getPathItems()
            .getGlobalAndLocalQueryNull("pathItemStringPath", "localStringPath", "pathItemStringQuery", null);
    }

    @Test
    public void getLocalPathItemQueryNull() {
        client.globalStringPath("globalStringPath");
        client.globalStringQuery("globalStringQuery");
        client.buildClient()
            .getPathItems()
            .getLocalPathItemQueryNull("pathItemStringPath", "localStringPath", null, null);
    }
}
