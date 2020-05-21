package fixtures.bodybyte;

import fixtures.httpinfrastructure.models.ErrorException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class ByteOperationsTests {
    private static AutoRestSwaggerBATByteService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATByteServiceBuilder().buildClient();
    }

    @Test
    public void getNull() throws Exception {
        byte[] result = client.getByteOperations().getNull();
        Assert.assertEquals(0, result.length);
    }

    @Test
    public void getEmpty() throws Exception {
        byte[] result = client.getByteOperations().getEmpty();
        Assert.assertArrayEquals("\"\"".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void getNonAscii() throws Exception {
        byte[] result = client.getByteOperations().getNonAscii();
        // Previously, byte[] response bodies were automatically base64 decoded by the runtime.
        // This conflicts with the octet-stream  (e.g. file/media download) use case,
        // so we're now passing the byte[] through as-is.
        byte[] expected = new byte[] { 34, 47, 47, 55, 57, 47, 80, 118, 54, 43, 102, 106, 51, 57, 103, 61, 61, 34 };
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void putNonAscii() throws Exception {
        byte[] body = new byte[] {
                (byte) 255, (byte) 254, (byte) 253, (byte) 252, (byte) 251,
                (byte) 250, (byte) 249, (byte) 248, (byte) 247, (byte) 246
        };

        client.getByteOperations().putNonAscii(body);
    }

    @Test
    public void getInvalid() throws Exception {
        try {
            byte[] result = client.getByteOperations().getInvalid();
            Assert.assertArrayEquals("\"::::SWAGGER::::\"".getBytes(StandardCharsets.UTF_8), result);
        } catch (ErrorException e) {
            e.printStackTrace();
        }
    }
}
