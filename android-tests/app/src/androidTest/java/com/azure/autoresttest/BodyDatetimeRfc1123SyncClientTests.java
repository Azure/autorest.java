package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.android.core.util.DateTimeRfc1123;
import com.azure.androidtest.fixtures.bodydatetimerfc1123.AutoRestRFC1123DateTimeTestServiceClient;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class BodyDatetimeRfc1123SyncClientTests {
    private static AutoRestRFC1123DateTimeTestServiceClient client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestRFC1123DateTimeTestServiceClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getNull() {
        final Response<DateTimeRfc1123> getResponse = client.getNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue());
    }

    @Test
    public void getInvalidDate() {
        try {
            client.getInvalidWithRestResponse();
            fail();
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Cannot construct instance"));
        }
    }

    @Test
    public void getOverflowDate() {
        final Response<DateTimeRfc1123> getResponse = client.getOverflowWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        DateTimeRfc1123 result = getResponse.getValue();
        DateTimeRfc1123 expected = new DateTimeRfc1123(OffsetDateTime.of(10000, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
        assertEquals(expected, result);
    }

    @Test
    public void getUnderflowDate() {
        try {
            client.getUnderflowWithRestResponse();
            fail();
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Cannot construct instance"));
        }
    }

    @Test
    public void putUtcMaxDateTime() {
        OffsetDateTime body = OffsetDateTime.of(9999, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        final Response<Void> putResponse = client.putUtcMaxDateTimeWithRestResponse(new DateTimeRfc1123(body));
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getUtcLowercaseMaxDateTime() {
        final Response<DateTimeRfc1123> getResponse = client.getUtcLowercaseMaxDateTimeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        DateTimeRfc1123 result = getResponse.getValue();
        DateTimeRfc1123 expected = new DateTimeRfc1123(OffsetDateTime.of(9999, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC));
        assertEquals(expected, result);
    }

    @Test
    public void getUtcUppercaseMaxDateTime() {
        final Response<DateTimeRfc1123> getResponse = client.getUtcUppercaseMaxDateTimeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());

        DateTimeRfc1123 result = getResponse.getValue();
        DateTimeRfc1123 expected = new DateTimeRfc1123(OffsetDateTime.of(9999, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC));
        assertEquals(expected, result);
    }

    @Test
    public void putUtcMinDateTime() {
        OffsetDateTime body = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        final Response<Void> putResponse = client.putUtcMinDateTimeWithRestResponse(new DateTimeRfc1123(body));
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getUtcMinDateTime() {
        final Response<DateTimeRfc1123> getResponse = client.getUtcMinDateTimeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        DateTimeRfc1123 result = getResponse.getValue();
        DateTimeRfc1123 expected = new DateTimeRfc1123(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
        assertEquals(expected, result);
    }
}
