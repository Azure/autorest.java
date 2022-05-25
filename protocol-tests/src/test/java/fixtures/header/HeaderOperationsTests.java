package fixtures.header;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class HeaderOperationsTests {
    private static AutoRestSwaggerBatHeaderServiceAsyncClient asyncClient;

    private static AutoRestSwaggerBatHeaderServiceClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new AutoRestSwaggerBatHeaderServiceClientBuilder().buildAsyncClient();
        client = new AutoRestSwaggerBatHeaderServiceClientBuilder().buildClient();
    }

    @Disabled("fail, does not take effect on \"User-Agent\", high-level has same issue")
    public void paramExistingKey() {
        RequestOptions requestOptions = new RequestOptions();
        client.paramExistingKeyWithResponse("overwrite", requestOptions);
    }

    @Test
    public void responseExistingKey() throws Exception {
        Response<Void> response = client.responseExistingKeyWithResponse(null);
        Assertions.assertEquals("overwrite", response.getHeaders().getValue("User-Agent"));
    }

    @Test
    public void paramProtectedKey() {
        RequestOptions requestOptions = new RequestOptions();
        asyncClient.paramProtectedKeyWithResponse("text/html", requestOptions).block();
    }

    @Test
    public void responseProtectedKey() throws Exception {
        Response<Void> response = client.responseProtectedKeyWithResponse(null);
        Assertions.assertTrue(response.getHeaders().getValue("Content-Type").contains("text/html"));
    }

    @Test
    public void paramInteger() {
        RequestOptions requestOptions = new RequestOptions();
        client.paramIntegerWithResponse("positive", 1, requestOptions);

        requestOptions = new RequestOptions();
        client.paramIntegerWithResponse("negative", -2, requestOptions);
    }

    @Test
    public void responseInteger() throws Exception {
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = client.responseIntegerWithResponse("positive", requestOptions);
        Assertions.assertEquals("1", response.getHeaders().getValue("value"));

        requestOptions = new RequestOptions();
        response = client.responseIntegerWithResponse("negative", requestOptions);
        Assertions.assertEquals("-2", response.getHeaders().getValue("value"));
    }

    @Test
    public void customRequestId() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addHeader("x-ms-client-request-id", "9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");
        client.customRequestIdWithResponse(requestOptions);
    }
}
