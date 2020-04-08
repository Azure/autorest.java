package fixtures.bodyfile;

import org.junit.BeforeClass;
import org.junit.Ignore;
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
        client = new AutoRestSwaggerBATFileServiceBuilder().buildClient();
    }

    @Ignore("Flaky! Run by itself works but run all tests with mvn fails")
    public void getFile() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        Path resourcePath = Paths.get(classLoader.getResource("sample.png").toURI());
        byte[] expected = Files.readAllBytes(resourcePath);
        InputStream in = client.files().getFile();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;

        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        in.close();
        byte[] actual = out.toByteArray();

        assertArrayEquals(expected, actual);
    }

    @Test
    @Ignore("Uses Transfer-Encoding: chunked which is not currently supported")
    public void getLargeFile() throws Exception {
        final long streamSize = 3000L * 1024L * 1024L;
        InputStream stream = client.files().getFileLarge();
        byte[] buffer = new byte[4096 * 1024];
        long skipped = 0;
        while (true) {
            int read = stream.read(buffer);
            if (read <= 0) {
                break;
            } else {
                skipped += read;
            }
        }
        stream.close();
        assertEquals(streamSize, skipped);
    }

    @Test
    public void getEmptyFile() throws Exception {
        InputStream in = client.files().getEmptyFile();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;

        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        in.close();
        byte[] actual = out.toByteArray();
        assertArrayEquals(new byte[0], actual);
    }
}
