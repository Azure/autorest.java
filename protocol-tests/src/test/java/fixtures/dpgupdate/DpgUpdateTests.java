package fixtures.dpgupdate;

import com.azure.core.http.HttpMethod;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import fixtures.llcinitial.DpgAsyncClient;
import fixtures.llcinitial.DpgClient;
import fixtures.llcinitial.DpgClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class DpgUpdateTests {
    private static DpgAsyncClient asyncClient;

    private static DpgClient client;

    private static fixtures.llcupdate1.DpgAsyncClient asyncClient2;

    private static fixtures.llcupdate1.DpgClient client2;

    @BeforeAll
    public static void setup() {
        asyncClient = new DpgClientBuilder().buildAsyncClient();
        client = new DpgClientBuilder().buildClient();
        asyncClient2 = new fixtures.llcupdate1.DpgClientBuilder().buildAsyncClient();
        client2 = new fixtures.llcupdate1.DpgClientBuilder().buildClient();
    }

    @Test
    public void headNoParams() {
        RequestOptions requestOptions = new RequestOptions();

        Response<BinaryData> response = client.headNoParamsWithResponse(requestOptions);
        Assertions.assertEquals(200, response.getStatusCode());

        requestOptions.addQueryParam("new_parameter", "new_parameter");

        response = client2.headNoParamsWithResponse(requestOptions);
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void getRequired() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("parameter", "parameter");

        Response<BinaryData> response = client.getRequiredWithResponse(requestOptions);
        Assertions.assertEquals(200, response.getStatusCode());

        requestOptions.addQueryParam("new_parameter", "new_parameter");

        response = client2.getRequiredWithResponse(requestOptions);
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    @Disabled("not implemented in testserver")
    public void putRequiredOptional() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("requiredParam", "requiredParam");
        requestOptions.addQueryParam("optionalParam", "optionalParam");
        requestOptions.setBody(BinaryData.fromString("{}"));

        client.putRequiredOptionalWithResponse(requestOptions);

        requestOptions.addQueryParam("new_parameter", "new_parameter");

        client2.putRequiredOptionalWithResponse(requestOptions);
    }

    @Test
    public void postParameters() {
        RequestOptions requestOptions = new RequestOptions();
        BinaryData parameter = BinaryData.fromString("{\"url\":\"http://example.org/myimage.jpeg\"}");

        Response<BinaryData> response = client.postParametersWithResponse(parameter, requestOptions);
        Assertions.assertEquals(200, response.getStatusCode());

        requestOptions.setHeader("content-type", "image/jpeg");
//        requestOptions.setHeader("content-length", String.valueOf(parameter.getLength()));

        response = client2.postParametersWithResponse(parameter, requestOptions);
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    @Disabled("not implemented in testserver")
    public void getOptional() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("optionalParam", "optionalParam");

        client.getOptionalWithResponse(requestOptions);

        requestOptions.addQueryParam("new_parameter", "new_parameter");

        client2.getOptionalWithResponse(requestOptions);
    }

    @Test
    public void deleteParameters() {
        RequestOptions requestOptions = new RequestOptions();

        Response<Void> response = client2.deleteParametersWithResponse(requestOptions);
        Assertions.assertEquals(204, response.getStatusCode());
    }


    @Test
    public void getNewOperation() {
        RequestOptions requestOptions = new RequestOptions();

        Response<BinaryData> response = client2.getNewOperationWithResponse(requestOptions);
        Assertions.assertEquals(200, response.getStatusCode());
    }


    @Test
    public void testSendRequestMethod() {
        HttpRequest request = new HttpRequest(HttpMethod.DELETE, "http://localhost:3000/serviceDriven/parameters");
        Response<BinaryData> response = client2.sendRequest(request, Context.NONE);
        Assertions.assertEquals(204, response.getStatusCode());
        Assertions.assertEquals(0, response.getValue().getLength());

        response = asyncClient2.sendRequest(request).block();
        Assertions.assertEquals(204, response.getStatusCode());
        Assertions.assertEquals(0, response.getValue().getLength());
    }
}
