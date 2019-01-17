package fixtures.bodyformdata;

import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.DecodingPolicyFactory;
import com.microsoft.rest.v2.util.FlowableUtil;
import fixtures.bodyformdata.implementation.AutoRestSwaggerBATFormDataServiceImpl;
import io.reactivex.Flowable;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

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
        Flowable<ByteBuffer> result = client.formdatas().uploadFile(Flowable.just(ByteBuffer.wrap(bytes)), "sample.png");
        byte[] allContent = FlowableUtil.collectBytesInArray(result).blockingGet();
        Assert.assertEquals(new String(bytes, StandardCharsets.UTF_8), new String(allContent, StandardCharsets.UTF_8));
    }

    @Test
    public void uploadFileViaBody() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        Path resourcePath = Paths.get(classLoader.getResource("upload.txt").toURI());
        byte[] bytes = Files.readAllBytes(resourcePath);
        byte[] actual = FlowableUtil.collectBytesInArray(client.formdatas().uploadFileViaBody(bytes.length, Flowable.just(ByteBuffer.wrap(bytes)))).blockingGet();
        Assert.assertEquals(new String(bytes, StandardCharsets.UTF_8), new String(actual, StandardCharsets.UTF_8));
    }
}
