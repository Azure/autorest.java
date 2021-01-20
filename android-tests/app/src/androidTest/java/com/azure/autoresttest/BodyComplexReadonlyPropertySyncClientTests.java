package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.bodycomplex.ReadonlypropertyClient;
import com.azure.androidtest.fixtures.bodycomplex.models.ReadonlyObj;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class BodyComplexReadonlyPropertySyncClientTests {
    private static ReadonlypropertyClient client;

    @BeforeClass
    public static void setup() {
        client = new ReadonlypropertyClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getReadOnlyProperty() {
        final Response<ReadonlyObj> getResponse = client.getValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertEquals("1234", getResponse.getValue().getId());
        assertTrue(2 == getResponse.getValue().getSize());
    }

    @Test
    public void putReadOnlyPropertyValid() {
        final Response<Void> putResponse = client.putValidWithRestResponse(5);
        assertEquals(200, putResponse.getStatusCode());
    }
}
