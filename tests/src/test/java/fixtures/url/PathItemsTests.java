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
        client.build().pathItems().getAllWithValues(
                "pathItemStringPath",
                "pathItemStringQuery",
                "localStringPath",
                "localStringQuery"
        );
    }

    @Test
    public void getGlobalQueryNull() throws Exception {
        client.globalStringPath("globalStringPath");
        client.globalStringQuery(null);
        client.build().pathItems().getGlobalQueryNull(
                "pathItemStringPath",
                "pathItemStringQuery",
                "localStringPath",
                "localStringQuery"
        );
    }

    @Test
    public void getGlobalAndLocalQueryNull() throws Exception {
        client.globalStringPath("globalStringPath");
        client.globalStringQuery(null);
        client.build().pathItems().getGlobalAndLocalQueryNull(
                "pathItemStringPath",
                "pathItemStringQuery",
                "localStringPath",
                null
        );
    }

    @Test
    public void getLocalPathItemQueryNull() throws Exception {
        client.globalStringPath("globalStringPath");
        client.globalStringQuery("globalStringQuery");
        client.build().pathItems().getLocalPathItemQueryNull(
                "pathItemStringPath",
                null,
                "localStringPath",
                null
        );
    }
}