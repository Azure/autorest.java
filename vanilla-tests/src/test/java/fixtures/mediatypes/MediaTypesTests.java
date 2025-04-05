package fixtures.mediatypes;

import com.azure.core.util.BinaryData;
import fixtures.mediatypes.models.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class MediaTypesTests {
    private static MediaTypesClient client;

    @BeforeAll
    public static void setup() {
        client = new MediaTypesClientBuilder().buildClient();
    }

    @Test
    public void analyzeWithJson() {
        client.analyzeBody("source");
    }

    @Test
    public void analyzeWithPdf() {
        client.analyzeBody(ContentType.APPLICATION_PDF,
                BinaryData.fromBytes("PDF".getBytes(StandardCharsets.UTF_8)), 3L);
    }
}
