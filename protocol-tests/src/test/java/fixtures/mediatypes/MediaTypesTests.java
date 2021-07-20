package fixtures.mediatypes;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MediaTypesTests {
    private static MediaTypesAsyncClient asyncClient;

    private static MediaTypesClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new MediaTypesClientBuilder().buildAsyncClient();
        client = new MediaTypesClientBuilder().buildClient();
    }

    @Test
    public void analyzeWithJson() throws Exception {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addHeader("Content-Type", "application/json");
        requestOptions.setBody(BinaryData.fromString("{\"source\": \"source\"}"));
        client.analyzeBody(requestOptions);
    }

    @Test
    public void analyzeWithPdf() throws Exception {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addHeader("Content-Type", "application/pdf");
        requestOptions.addHeader("Content-Length", "3");
        requestOptions.setBody(BinaryData.fromString("PDF"));
        client.analyzeBody(requestOptions);
    }
}
