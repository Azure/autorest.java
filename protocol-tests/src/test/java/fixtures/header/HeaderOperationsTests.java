package fixtures.header;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class HeaderOperationsTests {
    private static AutoRestSwaggerBATHeaderServiceAsyncClient asyncClient;

    private static AutoRestSwaggerBATHeaderServiceClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new AutoRestSwaggerBATHeaderServiceBuilder().buildAsyncClient();
        client = new AutoRestSwaggerBATHeaderServiceBuilder().buildClient();
    }

    @Disabled("fail, does not take effect on \"User-Agent\", high-level has same issue")
    public void paramExistingKey() {
        client.paramExistingKey("overwrite", null);
    }

    @Test
    public void responseExistingKey() throws Exception {
        Response<Void> response = client.responseExistingKeyWithResponse(null, null);
        Assertions.assertEquals("overwrite", response.getHeaders().getValue("User-Agent"));
    }

    @Test
    public void paramProtectedKey() {
        asyncClient.paramProtectedKey("text/html", null).block();
    }

    @Test
    public void responseProtectedKey() throws Exception {
        Response<Void> response = client.responseProtectedKeyWithResponse(null, null);
        Assertions.assertTrue(response.getHeaders().getValue("Content-Type").contains("text/html"));
    }

    @Test
    public void paramInteger() {
        client.paramInteger("positive", 1, null);
        client.paramInteger("negative", -2, null);
    }

    @Test
    public void responseInteger() throws Exception {
        Response<Void> response = client.responseIntegerWithResponse("positive", null, null);
        Assertions.assertEquals("1", response.getHeaders().getValue("value"));
        response = client.responseIntegerWithResponse("negative", null, null);
        Assertions.assertEquals("-2", response.getHeaders().getValue("value"));
    }

    @Test
    public void customRequestId() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addHeader("x-ms-client-request-id", "9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");
        client.customRequestId(requestOptions);
    }
}
