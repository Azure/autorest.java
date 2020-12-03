package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.bodydate.AutoRestDateTestServiceClient;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class BodyDateSyncClientTests {
    private static AutoRestDateTestServiceClient client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestDateTestServiceClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getInvalidDate() {
        try {
            client.getInvalidDateWithRestResponse();
            fail();
        } catch (RuntimeException exception) {
            assertTrue(exception.getMessage().contains("Cannot construct instance"));
        }
    }

    @Test
    public void getOverflowDate() {
        try {
            client.getOverflowDateWithRestResponse();
            fail();
        } catch (RuntimeException exception) {
            assertTrue(exception.getMessage().contains("Cannot construct instance"));
        }
    }

    @Test
    public void getUnderflowDate() {
        try {
            client.getUnderflowDateWithRestResponse();
            fail();
        } catch (RuntimeException exception) {
            assertTrue(exception.getMessage().contains("Cannot construct instance"));
        }
    }

    /* These tests fail because
    . deserialization cannot construct LocalDate from serialized string from TestServer, e.g. '2000-12-01', or
    . LocalDate is serialized as { day: d, month: m, year: y } which TestServer expects 'yyyy-mm-dd'
    The fix is to add LocalDate serializer and deserializer to ThreeTenModule in azure-sdk-for-android
    @Test
    public void putMaxDate() {
        LocalDate body = LocalDate.of(9999, 12, 31);
        final Response<Void> putResponse = client.putMaxDateWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getMaxDate() {
        final Response<LocalDate> getResponse = client.getMaxDateWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        LocalDate expected = LocalDate.of(9999, 12, 31);
        LocalDate result = getResponse.getValue();
        assertEquals(expected, result);
    }

    @Test
    public void putMinDate() {
        LocalDate body = LocalDate.of(1, 1, 1);
        final Response<Void> putResponse = client.putMinDateWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getMinDate() {
        final Response<LocalDate> getResponse = client.getMinDateWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        LocalDate expected = LocalDate.of(1, 1, 1);
        LocalDate result = getResponse.getValue();
        assertEquals(expected, result);
    }


*/
}
