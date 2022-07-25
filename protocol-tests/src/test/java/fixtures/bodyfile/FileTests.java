package fixtures.bodyfile;

import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FileTests {
    private static AutoRestSwaggerBatFileServiceAsyncClient asyncClient;

    private static AutoRestSwaggerBatFileServiceClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new AutoRestSwaggerBatFileServiceClientBuilder().buildAsyncClient();
        client = new AutoRestSwaggerBatFileServiceClientBuilder().buildClient();
    }

    @Test
    public void testGetFile() {
        Response<BinaryData> response = client.getFileWithResponse(null);
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertNotNull(response.getValue());
    }


}
