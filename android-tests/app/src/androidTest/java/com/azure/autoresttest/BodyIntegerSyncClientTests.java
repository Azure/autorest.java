package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.android.core.internal.util.serializer.exception.MalformedValueException;
import com.azure.androidtest.fixtures.bodyinteger.AutoRestIntegerTestServiceClient;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.exc.InputCoercionException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class BodyIntegerSyncClientTests {
    private static AutoRestIntegerTestServiceClient client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestIntegerTestServiceClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getNull() {
        Response<Integer> nullResponse = client.getNullWithRestResponse();
        assertEquals(200, nullResponse.getStatusCode());
        assertNull(nullResponse.getValue());
    }

    @Test
    public void getInvalid() {
        try {
            client.getInvalidWithRestResponse();
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertEquals(JsonParseException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getOverflowInt32() {
        try {
            client.getOverflowInt32WithRestResponse();
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertEquals(InputCoercionException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getUnderflowInt32() {
        try {
            client.getUnderflowInt32WithRestResponse();
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertEquals(InputCoercionException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getOverflowInt64() {
        try {
            long value = client.getOverflowInt64WithRestResponse().getValue();
            Assert.fail(); //Assert.assertEquals(Long.MAX_VALUE, value);
        } catch (Exception exception) {
            Assert.assertEquals(InputCoercionException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getUnderflowInt64() {
        try {
            long value = client.getUnderflowInt64WithRestResponse().getValue();
            Assert.fail(); // Assert.assertEquals(Long.MIN_VALUE, value);
        } catch (Exception exception) {
            Assert.assertEquals(InputCoercionException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void putMax32() throws Exception {
        Response<Void> putResponse = client.putMax32WithRestResponse(Integer.MAX_VALUE);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void putMax64() throws Exception {
        Response<Void> putResponse = client.putMax64WithRestResponse(Long.MAX_VALUE);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void putMin32() throws Exception {
        Response<Void> putResponse = client.putMin32WithRestResponse(Integer.MIN_VALUE);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void putMin64() throws Exception {
        Response<Void> putResponse = client.putMin64WithRestResponse(Long.MIN_VALUE);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getUnixDatetime() {
        Response<OffsetDateTime> unixDatetimeResponse = client.getUnixTimeWithRestResponse();
        assertEquals(200, unixDatetimeResponse.getStatusCode());
        assertEquals(OffsetDateTime.of(2016, 4, 13, 0, 0, 0, 0, ZoneOffset.UTC), unixDatetimeResponse.getValue());
    }

    @Test
    public void putUnixTimeDate() {
        Response<Void> putResponse = client.putUnixTimeDateWithRestResponse(OffsetDateTime.of(2016, 4, 13, 0, 0, 0, 0, ZoneOffset.UTC));
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getInvalidUnixTime() {
        try {
            client.getInvalidUnixTimeWithRestResponse();
            Assert.fail();
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("Unexpected character"));
        }
    }
}
