package fixtures.mediatypes;

import fixtures.mediatypes.models.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class MediaTypesTests {
  private static MediaTypesClient client;

  @BeforeClass
  public static void setup() {
    client = new MediaTypesClientBuilder().build();
  }

  @Test
  public void analyzeWithJson() throws Exception {
    client.analyzeBody("source");
  }

  @Test
  public void analyzeWithPdf() throws Exception {
    client.analyzeBody(ContentType.APPLICATION_PDF, Flux.just(ByteBuffer.wrap("PDF".getBytes(StandardCharsets.UTF_8))), 3L);
  }
}
