package fixtures.url;

import com.azure.core.http.rest.RequestOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PathItemsTests {
    private static PathItemsAsyncClient asyncClient;

    private static PathItemsClient client;

    @BeforeAll
    public static void setup() {
        AutoRestUrlTestServiceBuilder builder = new AutoRestUrlTestServiceBuilder();
        builder.globalStringPath("globalStringPath");
        asyncClient = builder.buildPathItemsAsyncClient();
        client = builder.buildPathItemsClient();
    }

    @Test
    public void getAllWithValues() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("globalStringQuery", "globalStringQuery");
        requestOptions.addQueryParam("pathItemStringQuery", "pathItemStringQuery");
        requestOptions.addQueryParam("localStringQuery", "localStringQuery");
        client.getAllWithValuesWithResponse("pathItemStringPath", "localStringPath", requestOptions);
    }

    @Test
    public void getGlobalQueryNull() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("pathItemStringQuery", "pathItemStringQuery");
        requestOptions.addQueryParam("localStringQuery", "localStringQuery");
        client.getGlobalQueryNullWithResponse("pathItemStringPath", "localStringPath", requestOptions);
    }

    @Test
    public void getGlobalAndLocalQueryNull() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("pathItemStringQuery", "pathItemStringQuery");
        client.getGlobalAndLocalQueryNullWithResponse("pathItemStringPath", "localStringPath", requestOptions);
    }

    @Test
    public void getLocalPathItemQueryNull() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("globalStringQuery", "globalStringQuery");
        client.getLocalPathItemQueryNullWithResponse("pathItemStringPath", "localStringPath", requestOptions);
    }
}
