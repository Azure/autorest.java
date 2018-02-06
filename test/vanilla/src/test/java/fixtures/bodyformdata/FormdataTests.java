package fixtures.bodyformdata;

import com.google.common.base.Charsets;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.DecodingPolicyFactory;
import com.microsoft.rest.v2.util.FlowableUtil;
import fixtures.bodyformdata.implementation.AutoRestSwaggerBATFormDataServiceImpl;
import io.reactivex.Flowable;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

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
        InputStream stream = classLoader.getResourceAsStream("upload.txt");
        byte[] bytes = IOUtils.toByteArray(stream);
        stream.close();
        Flowable<ByteBuffer> result = client.formdatas().uploadFile(Flowable.just(ByteBuffer.wrap(bytes)), "sample.png");
        byte[] allContent = FlowableUtil.collectBytesInArray(result).blockingGet();
        Assert.assertEquals(new String(bytes, Charsets.UTF_8), new String(allContent, Charsets.UTF_8));
    }

    // FIXME: nothing here is actually applying Transfer-Encoding: chunked
    @Ignore("Transfer-Encoding: chunked not currently supported")
    @Test
    public void uploadFileViaBody() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream("upload.txt")) {
            byte[] bytes = IOUtils.toByteArray(stream);
            stream.close();
            byte[] actual = FlowableUtil.collectBytesInArray(client.formdatas().uploadFileViaBody(Flowable.just(ByteBuffer.wrap(bytes)))).blockingGet();
            Assert.assertEquals(new String(bytes, StandardCharsets.UTF_8), new String(actual, StandardCharsets.UTF_8));
        }
    }
}
