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
        client = new AutoRestSwaggerBATFileServiceBuilder().buildClient();
    }

    @Test
    public void getFile() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        Path resourcePath = Paths.get(classLoader.getResource("sample.png").toURI());
        byte[] expected = Files.readAllBytes(resourcePath);
        InputStream in = client.getFiles().getFile();
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
    public void getLargeFile() throws Exception {
        final long streamSize = 3000L * 1024L * 1024L;
        InputStream stream = client.getFiles().getFileLarge();
        long skipped = stream.skip(streamSize);
        stream.close();
        assertEquals(streamSize, skipped);
    }

    @Test
    public void getEmptyFile() throws Exception {
        InputStream in = client.getFiles().getEmptyFile();
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
