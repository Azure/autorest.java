package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.bodycomplex.ArrayClient;
import com.azure.androidtest.fixtures.bodycomplex.models.ArrayWrapper;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class BodyComplexArraySyncClientTests {
    private static ArrayClient client;

    @BeforeClass
    public static void setup() {
        client = new ArrayClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getValid() {
        final Response<ArrayWrapper> getResponse = client.getValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        ArrayWrapper result = getResponse.getValue();
        assertEquals(5, result.getArray().size());
        assertEquals("&S#$(*Y", result.getArray().get(3));
    }

    @Test
    public void putValid() {
        ArrayWrapper body = new ArrayWrapper();
        body.setArray(Arrays.asList("1, 2, 3, 4", "", null, "&S#$(*Y", "The quick brown fox jumps over the lazy dog"));
        final Response<Void> putResponse = client.putValidWithRestResponse(body.getArray());
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getEmpty() {
        final Response<ArrayWrapper> getResponse = client.getEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        ArrayWrapper result = getResponse.getValue();
        assertEquals(0, result.getArray().size());
    }

    @Test
    public void putEmpty() {
        ArrayWrapper body = new ArrayWrapper();
        body.setArray(new ArrayList<>());
        final Response<Void> putResponse = client.putEmptyWithRestResponse(body.getArray());
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getNotProvided() {
        final Response<ArrayWrapper> getResponse = client.getNotProvidedWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        ArrayWrapper result = getResponse.getValue();
        Assert.assertNull(result.getArray());
    }
}
