package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.bodydatetime.AutoRestDateTimeTestServiceClient;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class BodyDatetimeSyncClientTests {
    private static AutoRestDateTimeTestServiceClient client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestDateTimeTestServiceClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getNull() {
        final Response<OffsetDateTime> getResponse = client.getNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue());
    }

    @Test
    public void getInvalidDate() {
        try {
            client.getInvalidWithRestResponse();
            fail();
        } catch (RuntimeException exception) {
            assertEquals(InvalidFormatException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getOverflowDate() {
        final Response<OffsetDateTime> getResponse = client.getOverflowWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        OffsetDateTime expected = OffsetDateTime.of(LocalDate.of(10000, 1, 1), LocalTime.parse("13:59:59.999"), ZoneOffset.UTC);
        assertEquals(expected, getResponse.getValue());
    }

    @Test
    public void getUnderflowDate() {
        try {
            client.getUnderflowWithRestResponse();
            fail();
        } catch (RuntimeException exception) {
            // expected
            assertEquals(InvalidFormatException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void putUtcMaxDateTime() {
        OffsetDateTime body = OffsetDateTime.parse("9999-12-31T23:59:59.999-00:00");
        final Response<Void> getResponse = client.putUtcMaxDateTimeWithRestResponse(body);
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void getUtcLowercaseMaxDateTime() {
        final Response<OffsetDateTime> getResponse = client.getUtcLowercaseMaxDateTimeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        OffsetDateTime result = getResponse.getValue();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T23:59:59.999-00:00");
        assertEquals(expected, result);
    }

    @Test
    public void getUtcUppercaseMaxDateTime() {
        final Response<OffsetDateTime> getResponse = client.getUtcUppercaseMaxDateTimeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        OffsetDateTime result = getResponse.getValue();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T23:59:59.999+00:00");
        assertEquals(expected, result);
    }

    @Test
    public void putLocalPositiveOffsetMaxDateTime() {
        OffsetDateTime body = OffsetDateTime.parse("9999-12-31T09:59:59.999Z");
        final Response<Void> putResponse = client.putLocalPositiveOffsetMaxDateTimeWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getLocalPositiveOffsetLowercaseMaxDateTime() {
        final Response<OffsetDateTime> getResponse = client.getLocalPositiveOffsetLowercaseMaxDateTimeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        OffsetDateTime result = getResponse.getValue();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T09:59:59.999+00:00");
        assertEquals(expected, result);
    }

    @Test
    public void getLocalPositiveOffsetUppercaseMaxDateTime() {
        final Response<OffsetDateTime> getResponse = client.getLocalNegativeOffsetUppercaseMaxDateTimeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        OffsetDateTime result = getResponse.getValue();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T23:59:59.999+00:00").withOffsetSameInstant(ZoneOffset.UTC).plusHours(14);
        assertEquals(expected, result);
    }

    @Ignore("Test server cannot handle year 10000")
    public void putLocalNegativeOffsetMaxDateTime() {
        OffsetDateTime body = OffsetDateTime.of(LocalDate.of(10000, 1, 1), LocalTime.parse("13:59:59.999"), ZoneOffset.UTC);
        final Response<Void> putResponse = client.putLocalNegativeOffsetMaxDateTimeWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getLocalNegativeOffsetLowercaseMaxDateTime() {
        final Response<OffsetDateTime> getResponse = client.getLocalNegativeOffsetLowercaseMaxDateTimeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        OffsetDateTime result = getResponse.getValue();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T23:59:59.999-00:00").withOffsetSameLocal(ZoneOffset.ofHours(-14)).withOffsetSameInstant(ZoneOffset.UTC);
        assertEquals(expected, result);
    }

    @Test
    public void getLocalNegativeOffsetUppercaseMaxDateTime() {
        final Response<OffsetDateTime> getResponse = client.getLocalNegativeOffsetUppercaseMaxDateTimeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        OffsetDateTime result = getResponse.getValue();
        OffsetDateTime expected = OffsetDateTime.of(LocalDate.of(10000, 1, 1), LocalTime.parse("13:59:59.999"), ZoneOffset.UTC);
        assertEquals(expected, result);
    }

    @Test
    public void putUtcMinDateTime() {
        OffsetDateTime body = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        final Response<Void> putResponse = client.putUtcMinDateTimeWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getUtcMinDateTime() {
        final Response<OffsetDateTime> getResponse = client.getUtcMinDateTimeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        OffsetDateTime result = getResponse.getValue();
        OffsetDateTime expected = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        assertEquals(expected, result);
    }

    @Test
    public void putLocalPositiveOffsetMinDateTime() {
        OffsetDateTime body = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(14));
        final Response<Void> putResponse = client.putLocalPositiveOffsetMinDateTimeWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getLocalPositiveOffsetMinDateTime() {
        final Response<OffsetDateTime> getResponse = client.getLocalPositiveOffsetMinDateTimeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        OffsetDateTime result = getResponse.getValue();
        OffsetDateTime expected = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(14)).withOffsetSameInstant(ZoneOffset.UTC);
        assertEquals(expected, result);
    }

    @Test
    public void putLocalNegativeOffsetMinDateTime() {
        OffsetDateTime body = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(-14));
        final Response<Void> putResponse = client.putLocalNegativeOffsetMinDateTimeWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getLocalNegativeOffsetMinDateTime() {
        final Response<OffsetDateTime> getResponse = client.getLocalNegativeOffsetMinDateTimeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        OffsetDateTime result = getResponse.getValue();
        OffsetDateTime expected = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(-14)).withOffsetSameInstant(ZoneOffset.UTC);
        assertEquals(expected, result);
    }
}
