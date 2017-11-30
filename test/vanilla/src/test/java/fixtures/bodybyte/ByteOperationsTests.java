package fixtures.bodybyte;

import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.PortPolicy;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fixtures.bodybyte.implementation.AutoRestSwaggerBATByteServiceImpl;

public class ByteOperationsTests {
    private static AutoRestSwaggerBATByteService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATByteServiceImpl(HttpPipeline.build(new PortPolicy.Factory(3000)));
    }

    @Test
    public void getEmpty() throws Exception {
        byte[] result = client.bytes().getNullWithRestResponseAsync().blockingGet().body();
        Assert.assertEquals(0, result.length);
    }

    @Test
    public void getNonAscii() throws Exception {
        byte[] result = client.bytes().getNonAscii();
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

        client.bytes().putNonAscii(body);
    }
}
