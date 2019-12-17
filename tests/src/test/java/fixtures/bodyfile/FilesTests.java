package fixtures.bodyfile;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
        ByteArrayOutputStream download = new ByteArrayOutputStream();
        client.files().getFile(download);
        byte[] actual = download.toByteArray();
        assertArrayEquals(expected, actual);
    }

    @Test
//    @Ignore("Uses Transfer-Encoding: chunked which is not currently supported")
    public void getLargeFile() throws Exception {
        final long streamSize = 3000L * 1024L * 1024L;
        InputStream stream = client.files().getFileLarge();
        long skipped = stream.skip(streamSize);
//        long skipped = client.files().getFileLargeWithResponseAsync().block().getValue()
//                .reduce(0L, (sum, byteBuffer) -> sum + byteBuffer.remaining()).block();
        assertEquals(streamSize, skipped);
    }

    @Test
    public void getEmptyFile() {
        ByteArrayOutputStream download = new ByteArrayOutputStream();
        client.files().getEmptyFile(download);
        final byte[] bytes = download.toByteArray();
        assertArrayEquals(new byte[0], bytes);
    }
}
