package fixtures.bodyfile;

import com.microsoft.rest.v2.util.FlowableUtil;
import io.reactivex.functions.BiFunction;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;
import java.nio.ByteBuffer;

import fixtures.bodyfile.implementation.AutoRestSwaggerBATFileServiceImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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
            byte[] actual = FlowableUtil.collectBytes(client.files().getFile()).blockingGet();
            byte[] expected = IOUtils.toByteArray(file);
            assertArrayEquals(expected, actual);
        }
    }

    @Test
    @Ignore("Uses Transfer-Encoding: chunked which is not currently supported")
    public void getLargeFile() {
        final long streamSize = 3000L * 1024L * 1024L;
        long skipped = client.files().getFileLarge()
                .reduce(0L, new BiFunction<Long, ByteBuffer, Long>() {
                    @Override
                    public Long apply(Long sum, ByteBuffer byteBuffer) {
                        return sum + byteBuffer.remaining();
                    }
                }).blockingGet();
        assertEquals(streamSize, skipped);
    }

    @Test
    public void getEmptyFile() {
        final byte[] bytes = FlowableUtil.collectBytes(client.files().getEmptyFile()).blockingGet();
        assertArrayEquals(new byte[0], bytes);
    }
}
