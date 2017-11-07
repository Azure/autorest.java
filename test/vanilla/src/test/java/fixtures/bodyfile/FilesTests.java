package fixtures.bodyfile;

import io.reactivex.functions.Function;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;

import fixtures.bodyfile.implementation.AutoRestSwaggerBATFileServiceImpl;

public class FilesTests {
    private static AutoRestSwaggerBATFileService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATFileServiceImpl("http://localhost:3000");
    }

    @Test
    public void getFile() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream file = classLoader.getResourceAsStream("sample.png")) {
            byte[] actual = client.files().getFileAsync()
                .map(new Function<InputStream, byte[]>() {
                    @Override
                    public byte[] apply(InputStream inputStreamServiceResponse) throws Exception {
                        return IOUtils.toByteArray(inputStreamServiceResponse);
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
            .map(new Function<InputStream, Long>() {
                @Override
                public Long apply(InputStream inputStreamServiceResponse) throws Exception {
                    return inputStreamServiceResponse.skip(streamSize);
                }
            }).blockingGet();
        Assert.assertEquals(streamSize, skipped);
    }

    @Test
    public void getEmptyFile() throws Exception {
        try (InputStream result = client.files().getEmptyFile()) {
            byte[] actual = IOUtils.toByteArray(result);
            Assert.assertEquals(0, actual.length);
        }
    }
}
