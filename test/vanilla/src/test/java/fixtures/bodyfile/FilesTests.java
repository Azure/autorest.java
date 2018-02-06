package fixtures.bodyfile;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.microsoft.rest.v2.util.ByteBufferUtil;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;
import java.nio.ByteBuffer;

import fixtures.bodyfile.implementation.AutoRestSwaggerBATFileServiceImpl;

import static org.junit.Assert.assertArrayEquals;

public class FilesTests {
    private static AutoRestSwaggerBATFileService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATFileServiceImpl();
    }

    @Test
    public void getFile() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream file = classLoader.getResourceAsStream("sample.png")) {
            byte[] actual = client.files().getFileAsync()
                .flatMapSingle(new Function<Flowable<ByteBuffer>, Single<byte[]>>() {
                    @Override
                    public Single<byte[]> apply(Flowable<ByteBuffer> stream) {
                        return coalesce(stream);
                    }
                }).blockingGet();
            byte[] expected = IOUtils.toByteArray(file);
            Assert.assertArrayEquals(expected, actual);
        }
    }

    @Test
    @Ignore("Uses Transfer-Encoding: chunked which is not currently supported by RestProxy")
    public void getLargeFile() {
        final long streamSize = 3000L * 1024L * 1024L;
        long skipped = client.files().getFileLargeAsync()
            .flatMapSingle(new Function<Flowable<ByteBuffer>, Single<Long>>() {
                @Override
                public Single<Long> apply(Flowable<ByteBuffer> asyncInputStream) {
                    // Dispose of the response content stream
                    return asyncInputStream.reduce(0L, new BiFunction<Long, ByteBuffer, Long>() {
                        @Override
                        public Long apply(Long sum, ByteBuffer bytes) {
                            return sum + bytes.remaining();
                        }
                    });
                }
            }).blockingGet();
        Assert.assertEquals(streamSize, skipped);
    }

    @Test
    public void getEmptyFile() {
        Flowable<ByteBuffer> stream = client.files().getEmptyFile();
        final byte[] bytes = coalesce(stream).blockingGet();
        assertArrayEquals(new byte[0], bytes);
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
