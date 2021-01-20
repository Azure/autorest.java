package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.bodycomplex.BasicClient;
import com.azure.androidtest.fixtures.bodycomplex.models.Basic;
import com.azure.androidtest.fixtures.bodycomplex.models.CMYKColors;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class BodyComplexBasicSyncClientTests {
    private static BasicClient client;

    @BeforeClass
    public static void setup() {
        client = new BasicClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getValid() {
        final Response<Basic> getResponse = client.getValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Basic result = getResponse.getValue();
        assertEquals(2, result.getId().intValue());
        assertEquals("abc", result.getName());
        assertEquals(CMYKColors.YELLOW, result.getColor());
    }

    @Test
    public void putValid() {
        Basic body = new Basic();
        body.setId(2);
        body.setName("abc");
        body.setColor(CMYKColors.MAGENTA);
        final Response<Void> putResponse = client.putValidWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getInvalid() {
        try {
            client.getInvalidWithRestResponse();
            fail();
        } catch (Exception exception) {
            // expected
            assertEquals(InvalidFormatException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getEmpty() {
        final Response<Basic> getResponse = client.getEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Basic result = getResponse.getValue();
        assertNull(result.getName());
    }

    @Test
    public void getNull() {
        final Response<Basic> getResponse = client.getNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Basic result = getResponse.getValue();
        assertNull(result.getName());
    }

    @Test
    public void getNotProvided() {
        final Response<Basic> getResponse = client.getNotProvidedWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue());
    }
}
