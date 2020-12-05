package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.bodybyte.AutoRestSwaggerBATByteServiceClient;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class BodyByteSyncClientTests {
    private static AutoRestSwaggerBATByteServiceClient client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATByteServiceClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getNull() {
        final Response<byte[]> getResponse = client.getNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue());
    }

    @Test
    public void getEmpty() {
        final Response<byte[]> getResponse = client.getEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertArrayEquals("\"\"".getBytes(StandardCharsets.UTF_8), getResponse.getValue());
    }

    @Test
    public void getNonAscii() {
        final Response<byte[]> getResponse = client.getNonAsciiWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        // Previously, byte[] response bodies were automatically base64 decoded by the runtime.
        // This conflicts with the octet-stream (e.g. file/media download) use case,
        // so we're now passing the byte[] through as-is.
        byte[] expected = new byte[] { 34, 47, 47, 55, 57, 47, 80, 118, 54, 43, 102, 106, 51, 57, 103, 61, 61, 34 };
        assertArrayEquals(expected, getResponse.getValue());
    }

    @Test
    public void putNonAscii() {
        byte[] body = new byte[] {
                (byte) 255, (byte) 254, (byte) 253, (byte) 252, (byte) 251,
                (byte) 250, (byte) 249, (byte) 248, (byte) 247, (byte) 246
        };

        final Response<Void> putResponse = client.putNonAsciiWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getInvalid() {
        final Response<byte[]> getResponse = client.getInvalidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertArrayEquals("\"::::SWAGGER::::\"".getBytes(StandardCharsets.UTF_8), getResponse.getValue());
    }
}
