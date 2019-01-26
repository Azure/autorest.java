package fixtures.bodyformdata;

import com.microsoft.rest.v3.http.HttpPipeline;
import com.microsoft.rest.v3.policy.DecodingPolicyFactory;
import com.microsoft.rest.v3.util.FluxUtil;
import fixtures.bodyformdata.implementation.AutoRestSwaggerBATFormDataServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FormdataTests {
    private static AutoRestSwaggerBATFormDataService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATFormDataServiceImpl(HttpPipeline.build(new DecodingPolicyFactory()));
    }

    @Ignore("Multipart form data not currently supported")
    @Test
    public void uploadFileMultipart() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        Path resourcePath = Paths.get(classLoader.getResource("upload.txt").toURI());
        byte[] bytes = Files.readAllBytes(resourcePath);
        Flux<ByteBuffer> result = client.formdatas().uploadFile(Flux.just(ByteBuffer.wrap(bytes)), "sample.png");
        byte[] allContent = FluxUtil.collectBytesInArray(result).block();
        Assert.assertEquals(new String(bytes, StandardCharsets.UTF_8), new String(allContent, StandardCharsets.UTF_8));
    }

    @Test
    public void uploadFileViaBody() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        Path resourcePath = Paths.get(classLoader.getResource("upload.txt").toURI());
        byte[] bytes = Files.readAllBytes(resourcePath);
        byte[] actual = FluxUtil.collectBytesInArray(client.formdatas().uploadFileViaBody(bytes.length, Flux.just(ByteBuffer.wrap(bytes)))).block();
        Assert.assertEquals(new String(bytes, StandardCharsets.UTF_8), new String(actual, StandardCharsets.UTF_8));
    }
}
