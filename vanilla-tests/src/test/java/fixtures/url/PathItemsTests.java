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
        client.buildClient().pathItems().getAllWithValues(
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
        client.buildClient().pathItems().getGlobalQueryNull(
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
        client.buildClient().pathItems().getGlobalAndLocalQueryNull(
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
        client.buildClient().pathItems().getLocalPathItemQueryNull(
                "pathItemStringPath",
                "localStringPath",
                null,
                null
        );
    }
}