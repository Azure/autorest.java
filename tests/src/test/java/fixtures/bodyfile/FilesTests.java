package fixtures.bodyfile;

import com.azure.core.util.FluxUtil;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FilesTests {
    private static AutoRestSwaggerBATFileService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATFileServiceBuilder().build();
    }

    @Test
    public void getFile() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        Path resourcePath = Paths.get(classLoader.getResource("sample.png").toURI());
        byte[] expected = Files.readAllBytes(resourcePath);
        byte[] actual = FluxUtil.collectBytesInByteBufferStream(client.files().getFileWithResponseAsync().block().getValue()).block();
        assertArrayEquals(expected, actual);
    }

    @Test
    @Ignore("Uses Transfer-Encoding: chunked which is not currently supported")
    public void getLargeFile() {
        final long streamSize = 3000L * 1024L * 1024L;
        long skipped = client.files().getFileLargeWithResponseAsync().block().getValue()
                .reduce(0L, (sum, byteBuffer) -> sum + byteBuffer.remaining()).block();
        assertEquals(streamSize, skipped);
    }

    @Test
    public void getEmptyFile() {
        final byte[] bytes = FluxUtil.collectBytesInByteBufferStream(client.files().getEmptyFileWithResponseAsync().block().getValue()).block();
        assertArrayEquals(new byte[0], bytes);
    }
}
