package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.android.core.util.DateTimeRfc1123;
import com.azure.androidtest.fixtures.bodycomplex.PrimitiveClient;
import com.azure.androidtest.fixtures.bodycomplex.models.BooleanWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.ByteWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.DateWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.DatetimeWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.Datetimerfc1123Wrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.DoubleWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.DurationWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.FloatWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.IntWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.LongWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.StringWrapper;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class BodyComplexPrimitiveSyncClientTests {
    private static PrimitiveClient client;

    @BeforeClass
    public static void setup() {
        client = new PrimitiveClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }
    
    @Test
    public void getInt() {
        final Response<IntWrapper> getResponse = client.getIntWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        IntWrapper result = getResponse.getValue();
        assertEquals(-1, result.getField1().intValue());
        assertEquals(2, result.getField2().intValue());
    }

    @Test
    public void putInt() {
        IntWrapper body = new IntWrapper();
        body.setField1(-1);
        body.setField2(2);
        final Response<Void> putResponse = client.putIntWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getLong() {
        final Response<LongWrapper> getResponse = client.getLongWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        LongWrapper result = getResponse.getValue();
        assertEquals(1099511627775L, result.getField1().longValue());
        assertEquals(-999511627788L, result.getField2().longValue());
    }

    @Test
    public void putLong() {
        LongWrapper body = new LongWrapper();
        body.setField1(1099511627775L);
        body.setField2(-999511627788L);
        final Response<Void> putResponse = client.putLongWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getFloat() {
        final Response<FloatWrapper> getResponse = client.getFloatWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        FloatWrapper result = getResponse.getValue();
        assertEquals(1.05f, result.getField1(), 0f);
        assertEquals(-0.003f, result.getField2(), 0f);
    }

    @Test
    public void putFloat() {
        FloatWrapper body = new FloatWrapper();
        body.setField1(1.05f);
        body.setField2(-0.003f);
        final Response<Void> putResponse = client.putFloatWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getDouble() {
        final Response<DoubleWrapper> getResponse = client.getDoubleWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        DoubleWrapper result = getResponse.getValue();
        assertEquals(3e-100, result.getField1(), 0f);
        assertEquals(-0.000000000000000000000000000000000000000000000000000000005,
                result.getField56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose(),
                0f);
    }

    @Test
    public void putDouble() {
        DoubleWrapper body = new DoubleWrapper();
        body.setField1(3e-100);
        body.setField56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose(-5e-57);
        final Response<Void> putResponse = client.putDoubleWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getBool() {
        final Response<BooleanWrapper> getResponse = client.getBoolWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        BooleanWrapper result = getResponse.getValue();
        assertEquals(true, result.isFieldTrue());
        assertEquals(false, result.isFieldFalse());
    }

    @Test
    public void putBool() {
        BooleanWrapper body = new BooleanWrapper();
        body.setFieldFalse(false);
        body.setFieldTrue(true);
        final Response<Void> putResponse = client.putBoolWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getString() {
        final Response<StringWrapper> getResponse = client.getStringWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        StringWrapper result = getResponse.getValue();
        assertEquals("goodrequest", result.getField());
        assertEquals("", result.getEmpty());
        assertEquals(null, result.getNullProperty());
    }

    @Test
    public void putString() {
        StringWrapper body = new StringWrapper();
        body.setField("goodrequest");
        body.setEmpty("");
        final Response<Void> putResponse = client.putStringWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Ignore("Cannot desrialize LocalDate from server serialization. Fix LocalDate serialization")
    public void getDate() {
        final Response<DateWrapper> getResponse = client.getDateWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        DateWrapper result = getResponse.getValue();
        assertEquals(LocalDate.of(1, 1, 1), result.getField());
        assertEquals(LocalDate.of(2016, 2, 29), result.getLeap());
    }

    @Ignore("Serialization of LocalDate is not acceptable by Server. Fix LocalDate serialization")
    public void putDate() {
        DateWrapper body = new DateWrapper();
        body.setField(LocalDate.of(1, 1, 1));
        body.setLeap(LocalDate.of(2016, 2, 29));
        final Response<Void> putResponse = client.putDateWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getDateTime() {
        final Response<DatetimeWrapper> getResponse = client.getDateTimeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        DatetimeWrapper result = getResponse.getValue();
        assertEquals(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC), result.getField());
        assertEquals(OffsetDateTime.of(2015, 5, 18, 18, 38, 0, 0, ZoneOffset.UTC), result.getNow());
    }

    @Test
    public void putDateTime() {
        DatetimeWrapper body = new DatetimeWrapper();
        body.setField(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
        body.setNow(OffsetDateTime.of(2015, 5, 18, 18, 38, 0, 0, ZoneOffset.UTC));
        final Response<Void> putResponse = client.putDateTimeWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getDateTimeRfc1123() {
        final Response<Datetimerfc1123Wrapper> getResponse = client.getDateTimeRfc1123WithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Datetimerfc1123Wrapper result = getResponse.getValue();
        assertEquals(new DateTimeRfc1123(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)),
                result.getField());
        assertEquals(new DateTimeRfc1123(OffsetDateTime.of(2015, 5, 18, 11, 38, 0, 0, ZoneOffset.UTC)),
                result.getNow());
    }

    @Test
    public void putDateTimeRfc1123() {
        Datetimerfc1123Wrapper body = new Datetimerfc1123Wrapper();
        body.setField(new DateTimeRfc1123(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)));
        body.setNow(new DateTimeRfc1123(OffsetDateTime.of(2015, 5, 18, 11, 38, 0, 0, ZoneOffset.UTC)));
        final Response<Void> putResponse = client.putDateTimeRfc1123WithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getDuration() {
        final Response<DurationWrapper> getResponse = client.getDurationWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        DurationWrapper result = getResponse.getValue();
        assertEquals(Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11), result.getField());
    }

    @Test
    public void putDuration() {
        DurationWrapper body = new DurationWrapper();
        body.setField(Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11));
        final Response<Void> putResponse = client.putDurationWithRestResponse(body.getField());
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getByte() {
        final Response<ByteWrapper> getResponse = client.getByteWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        ByteWrapper result = getResponse.getValue();
        byte[] expected = new byte[] {
                (byte) 255, (byte) 254, (byte) 253, (byte) 252, (byte) 0,
                (byte) 250, (byte) 249, (byte) 248, (byte) 247, (byte) 246
        };
        Assert.assertArrayEquals(expected, result.getField());
    }

    @Test
    public void putByte() {
        ByteWrapper body = new ByteWrapper();
        byte[] byteArray = new byte[] {
                (byte) 255, (byte) 254, (byte) 253, (byte) 252, (byte) 0,
                (byte) 250, (byte) 249, (byte) 248, (byte) 247, (byte) 246
        };
        body.setField(byteArray);
        final Response<Void> putResponse = client.putByteWithRestResponse(body.getField());
        assertEquals(200, putResponse.getStatusCode());
    }
}
