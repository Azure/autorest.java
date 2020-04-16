package fixtures.url;

import org.junit.BeforeClass;
import org.junit.Test;

public class PathItemsTests {
    private static AutoRestUrlTestServiceBuilder client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestUrlTestServiceBuilder();
    }

    @Test
    public void getAllWithValues() throws Exception {
        client.globalStringPath("globalStringPath");
        client.globalStringQuery("globalStringQuery");
        client.buildClient().getPathItems().getAllWithValues(
                "pathItemStringPath",
                "localStringPath",
                "pathItemStringQuery",
                "localStringQuery"
        );
    }

    @Test
    public void getGlobalQueryNull() throws Exception {
        client.globalStringPath("globalStringPath");
        client.globalStringQuery(null);
        client.buildClient().getPathItems().getGlobalQueryNull(
                "pathItemStringPath",
                "localStringPath",
                "pathItemStringQuery",
                "localStringQuery"
        );
    }

    @Test
    public void getGlobalAndLocalQueryNull() throws Exception {
        client.globalStringPath("globalStringPath");
        client.globalStringQuery(null);
        client.buildClient().getPathItems().getGlobalAndLocalQueryNull(
                "pathItemStringPath",
                "localStringPath",
                "pathItemStringQuery",
                null
        );
    }

    @Test
    public void getLocalPathItemQueryNull() throws Exception {
        client.globalStringPath("globalStringPath");
        client.globalStringQuery("globalStringQuery");
        client.buildClient().getPathItems().getLocalPathItemQueryNull(
                "pathItemStringPath",
                "localStringPath",
                null,
                null
        );
    }
}
