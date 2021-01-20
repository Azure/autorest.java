package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.android.core.internal.util.serializer.exception.MalformedValueException;
import com.azure.androidtest.fixtures.bodyboolean.AutoRestBoolTestServiceClient;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class BodyBooleanSyncClientTests {
    private static AutoRestBoolTestServiceClient client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestBoolTestServiceClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getNull() {
        final Response<Boolean> getResponse = client.getNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue());
    }

    @Test
    public void getInvalid() {
        try {
            client.getInvalidWithRestResponse();
            fail();
        } catch (Exception exception) {
            // expected
            assertEquals(MalformedValueException.class, exception.getClass());
        }
    }

    @Test
    public void getTrue() {
        final Response<Boolean> getResponse = client.getTrueWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertTrue(getResponse.getValue());
    }

    @Test
    public void getFalse() {
        final Response<Boolean> getResponse = client.getFalseWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertFalse(getResponse.getValue());
    }

    @Test
    public void putTrue() {
        final Response<Void> putResponse = client.putTrueWithRestResponse();
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void putFalse() {
        final Response<Void> putResponse = client.putFalseWithRestResponse();
        assertEquals(200, putResponse.getStatusCode());
    }
}
