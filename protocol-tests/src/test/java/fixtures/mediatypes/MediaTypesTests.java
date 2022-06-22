package fixtures.mediatypes;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        requestOptions.setBody(BinaryData.fromString("{\"source\": \"source\"}"));
        client.analyzeBodyWithResponse("application/json", requestOptions);
    }

    @Test
    public void analyzeWithPdf() throws Exception {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addHeader("Content-Length", "3");
        requestOptions.setBody(BinaryData.fromString("PDF"));
        client.analyzeBodyWithResponse("application/pdf", requestOptions);
    }

    @Test
    public void test() {
//        List<Boolean> l = Arrays.asList(null, true, false);
        List<Boolean> l = new ArrayList<>();
//        boolean res = l.stream().map(value -> {
//            if(value == null) return false;
//            else return value;
//        }).findFirst().orElse(false);
        boolean res = l.stream().findFirst().orElse(false);
        System.out.println(res);
    }

}
