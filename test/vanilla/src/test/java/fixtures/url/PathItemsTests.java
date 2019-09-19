package fixtures.url;

import fixtures.url.implementation.AutoRestUrlTestServiceBuilder;
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
        client.build().pathItems().getAllWithValues(
                "localStringPath",
                "pathItemStringPath",
                "localStringQuery",
                "pathItemStringQuery"
        );
    }

    @Test
    public void getGlobalQueryNull() throws Exception {
        client.globalStringPath("globalStringPath");
        client.globalStringQuery(null);
        client.build().pathItems().getGlobalQueryNull(
                "localStringPath",
                "pathItemStringPath",
                "localStringQuery",
                "pathItemStringQuery"
        );
    }

    @Test
    public void getGlobalAndLocalQueryNull() throws Exception {
        client.globalStringPath("globalStringPath");
        client.globalStringQuery(null);
        client.build().pathItems().getGlobalAndLocalQueryNull(
                "localStringPath",
                "pathItemStringPath",
                null,
                "pathItemStringQuery"
        );
    }

    @Test
    public void getLocalPathItemQueryNull() throws Exception {
        client.globalStringPath("globalStringPath");
        client.globalStringQuery("globalStringQuery");
        client.build().pathItems().getLocalPathItemQueryNull(
                "localStringPath",
                "pathItemStringPath",
                null,
                null
        );
    }
}
