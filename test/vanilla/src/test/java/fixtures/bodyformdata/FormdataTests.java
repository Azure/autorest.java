package fixtures.bodyformdata;

import com.google.common.base.Charsets;
import com.microsoft.rest.v2.http.AsyncInputStream;
import com.microsoft.rest.v2.util.FlowableUtil;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import fixtures.bodyformdata.implementation.AutoRestSwaggerBATFormDataServiceImpl;

public class FormdataTests {
    private static AutoRestSwaggerBATFormDataService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATFormDataServiceImpl(HttpPipeline.build(new PortPolicyFactory(3000)));
    }

    @Ignore("Multipart form data not currently supported")
    @Test
    public void uploadFileMultipart() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream stream = classLoader.getResourceAsStream("upload.txt");
        byte[] bytes = IOUtils.toByteArray(stream);
        stream.close();
        Flowable<byte[]> result = client.formdatas().uploadFile(AsyncInputStream.create(bytes).content(), "sample.png");
        byte[] allContent = FlowableUtil.collectBytes(result).blockingGet();
        Assert.assertEquals(new String(bytes, Charsets.UTF_8), new String(allContent, Charsets.UTF_8));
    }

    @Ignore("Transfer-Encoding: Chunked not yet supported")
    @Test
    public void uploadFileViaBody() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream("upload.txt")) {
            byte[] bytes = IOUtils.toByteArray(stream);
            stream.close();
            byte[] actual = client.formdatas().uploadFileViaBodyAsync(AsyncInputStream.create(bytes).content())
                    .flatMapSingle(new Function<Flowable<byte[]>, Single<byte[]>>() {
                        @Override
                        public Single<byte[]> apply(Flowable<byte[]> stream) throws Exception {
                            return FlowableUtil.collectBytes(stream);
                        }
                    }).blockingGet();
            Assert.assertEquals(new String(bytes), IOUtils.toString(actual));
        }
    }
}
