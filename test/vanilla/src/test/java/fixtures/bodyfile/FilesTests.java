package fixtures.bodyfile;

import com.microsoft.rest.v3.util.FluxUtil;
import fixtures.bodyfile.implementation.AutoRestSwaggerBATFileServiceImpl;
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
        client = new AutoRestSwaggerBATFileServiceImpl();
    }

    @Test
    public void getFile() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        Path resourcePath = Paths.get(classLoader.getResource("sample.png").toURI());
        byte[] expected = Files.readAllBytes(resourcePath);
        byte[] actual = FluxUtil.collectBytesInArray(client.files().getFile()).block();
        assertArrayEquals(expected, actual);
    }

    @Test
    @Ignore("Uses Transfer-Encoding: chunked which is not currently supported")
    public void getLargeFile() {
        final long streamSize = 3000L * 1024L * 1024L;
        long skipped = client.files().getFileLarge()
                .reduce(0L, (sum, byteBuffer) -> sum + byteBuffer.remaining()).block();
        assertEquals(streamSize, skipped);
    }

    @Test
    public void getEmptyFile() {
        final byte[] bytes = FluxUtil.collectBytesInArray(client.files().getEmptyFile()).block();
        assertArrayEquals(new byte[0], bytes);
    }
}
