package fixtures.bodyfile;

import com.microsoft.rest.v2.http.AsyncInputStream;
import com.microsoft.rest.v2.util.FlowableUtil;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;

import fixtures.bodyfile.implementation.AutoRestSwaggerBATFileServiceImpl;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

public class FilesTests {
    private static AutoRestSwaggerBATFileService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATFileServiceImpl(HttpPipeline.build(new PortPolicyFactory(3000)));
    }

    @Test
    public void getFile() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream file = classLoader.getResourceAsStream("sample.png")) {
            byte[] actual = client.files().getFileAsync()
                .flatMapSingle(new Function<Flowable<byte[]>, Single<byte[]>>() {
                    @Override
                    public Single<byte[]> apply(Flowable<byte[]> stream) {
                        return FlowableUtil.collectBytes(stream);
                    }
                }).blockingGet();
            byte[] expected = IOUtils.toByteArray(file);
            Assert.assertArrayEquals(expected, actual);
        }
    }

    @Test
    @Ignore("Uses Transfer-Encoding: chunked which is not currently supported by RestProxy")
    public void getLargeFile() throws Exception {
        final long streamSize = 3000L * 1024L * 1024L;
        long skipped = client.files().getFileLargeAsync()
            .flatMapSingle(new Function<Flowable<byte[]>, Single<Long>>() {
                @Override
                public Single<Long> apply(Flowable<byte[]> asyncInputStream) throws Exception {
                    // Dispose of the response content stream
                    return asyncInputStream.reduce(0L, new BiFunction<Long, byte[], Long>() {
                        @Override
                        public Long apply(Long sum, byte[] bytes) throws Exception {
                            return sum + bytes.length;
                        }
                    });
                }
            }).blockingGet();
        Assert.assertEquals(streamSize, skipped);
    }

    @Test
    public void getEmptyFile() {
        Flowable<byte[]> stream = client.files().getEmptyFile();
        final byte[] bytes = FlowableUtil.collectBytes(stream).blockingGet();
        assertArrayEquals(new byte[0], bytes);
    }
}
