package fixtures.bodyformdata;

import com.google.common.base.Charsets;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.microsoft.rest.v2.util.ByteBufferUtil;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;
import java.nio.ByteBuffer;

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
        Flowable<ByteBuffer> result = client.formdatas().uploadFile(Flowable.just(ByteBuffer.wrap(bytes)), "sample.png");
        byte[] allContent = coalesce(result).blockingGet();
        Assert.assertEquals(new String(bytes, Charsets.UTF_8), new String(allContent, Charsets.UTF_8));
    }

    @Ignore("Transfer-Encoding: Chunked not yet supported")
    @Test
    public void uploadFileViaBody() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream("upload.txt")) {
            byte[] bytes = IOUtils.toByteArray(stream);
            stream.close();
            byte[] actual = client.formdatas().uploadFileViaBodyAsync(Flowable.just(ByteBuffer.wrap(bytes)))
                    .flatMapSingle(new Function<Flowable<ByteBuffer>, Single<byte[]>>() {
                        @Override
                        public Single<byte[]> apply(Flowable<ByteBuffer> stream) {
                            return coalesce(stream);
                        }
                    }).blockingGet();
            Assert.assertEquals(new String(bytes), IOUtils.toString(actual));
        }
    }



    /**
     * Coalesce ByteBuffers emitted by a Flowable into a single byte array.
     * @param content A stream which emits ByteBuffers.
     * @return A Single which emits the concatenation of all the ByteBuffers given by the source Flowable.
     */
    private static Single<byte[]> coalesce(Flowable<ByteBuffer> content) {
        return content.collectInto(ByteStreams.newDataOutput(), new BiConsumer<ByteArrayDataOutput, ByteBuffer>() {
            @Override
            public void accept(ByteArrayDataOutput out, ByteBuffer chunk) {
                out.write(ByteBufferUtil.toByteArray(chunk));
            }
        }).map(new Function<ByteArrayDataOutput, byte[]>() {
            @Override
            public byte[] apply(ByteArrayDataOutput out) {
                return out.toByteArray();
            }
        });
    }
}
