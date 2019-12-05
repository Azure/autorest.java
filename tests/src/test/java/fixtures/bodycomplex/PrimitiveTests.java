package fixtures.bodycomplex;

import fixtures.bodycomplex.models.BooleanWrapper;
import fixtures.bodycomplex.models.ByteWrapper;
import fixtures.bodycomplex.models.DateWrapper;
import fixtures.bodycomplex.models.DatetimeWrapper;
import fixtures.bodycomplex.models.Datetimerfc1123Wrapper;
import fixtures.bodycomplex.models.DoubleWrapper;
import fixtures.bodycomplex.models.DurationWrapper;
import fixtures.bodycomplex.models.FloatWrapper;
import fixtures.bodycomplex.models.IntWrapper;
import fixtures.bodycomplex.models.LongWrapper;
import fixtures.bodycomplex.models.StringWrapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class PrimitiveTests {
    private static AutoRestComplexTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestComplexTestServiceBuilder().build();
    }

    @Test
    public void getInt() throws Exception {
        IntWrapper result = client.primitives().getInt();
        Assert.assertEquals(-1, result.getField1());
        Assert.assertEquals(2, result.getField2());
    }

    @Test
    public void putInt() throws Exception {
        IntWrapper body = new IntWrapper();
        body.setField1(-1);
        body.setField2(2);
        client.primitives().putIntWithResponseAsync(body).block();
    }

    @Test
    public void getLong() throws Exception {
        LongWrapper result = client.primitives().getLong();
        Assert.assertEquals(1099511627775L, result.getField1());
        Assert.assertEquals(-999511627788L, result.getField2());
    }

    @Test
    public void putLong() throws Exception {
        LongWrapper body = new LongWrapper();
        body.setField1(1099511627775L);
        body.setField2(-999511627788L);
        client.primitives().putLongWithResponseAsync(body).block();
    }

    @Test
    public void getFloat() throws Exception {
        FloatWrapper result = client.primitives().getFloat();
        Assert.assertEquals(1.05f, result.getField1(), 0f);
        Assert.assertEquals(-0.003f, result.getField2(), 0f);
    }

    @Test
    public void putFloat() throws Exception {
        FloatWrapper body = new FloatWrapper();
        body.setField1(1.05f);
        body.setField2(-0.003f);
        client.primitives().putFloatWithResponseAsync(body).block();
    }

    @Test
    public void getDouble() throws Exception {
        DoubleWrapper result = client.primitives().getDouble();
        Assert.assertEquals(3e-100, result.getField1(), 0f);
        Assert.assertEquals(-0.000000000000000000000000000000000000000000000000000000005,
                result.getField56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose(),
                0f);
    }

    @Test
    public void putDouble() throws Exception {
        DoubleWrapper body = new DoubleWrapper();
        body.setField1(3e-100);
        body.setField56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose(-5e-57);
        client.primitives().putDoubleWithResponseAsync(body).block();
    }

    @Test
    public void getBool() throws Exception {
        BooleanWrapper result = client.primitives().getBool();
        Assert.assertEquals(true, result.isFieldTrue());
        Assert.assertEquals(false, result.isFieldFalse());
    }

    @Test
    public void putBool() throws Exception {
        BooleanWrapper body = new BooleanWrapper();
        body.setFieldFalse(false);
        body.setFieldTrue(true);
        client.primitives().putBoolWithResponseAsync(body).block();
    }

    @Test
    public void getString() throws Exception {
        StringWrapper result = client.primitives().getString();
        Assert.assertEquals("goodrequest", result.getField());
        Assert.assertEquals("", result.getEmpty());
        Assert.assertEquals(null, result.getNullProperty());
    }

    @Test
    public void putString() throws Exception {
        StringWrapper body = new StringWrapper();
        body.setField("goodrequest");
        body.setEmpty("");
        client.primitives().putStringWithResponseAsync(body).block();
    }

    @Test
    public void getDate() throws Exception {
        DateWrapper result = client.primitives().getDate();
        Assert.assertEquals(LocalDate.of(1, 1, 1), result.getField());
        Assert.assertEquals(LocalDate.of(2016, 2, 29), result.getLeap());
    }

    @Test
    public void putDate() throws Exception {
        DateWrapper body = new DateWrapper();
        body.setField(LocalDate.of(1, 1, 1));
        body.setLeap(LocalDate.of(2016, 2, 29));
        client.primitives().putDateWithResponseAsync(body).block();
    }

    @Test
    public void getDateTime() throws Exception {
        DatetimeWrapper result = client.primitives().getDateTime();
        Assert.assertEquals(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC), result.getField());
        Assert.assertEquals(OffsetDateTime.of(2015, 5, 18, 18, 38, 0, 0, ZoneOffset.UTC), result.getNow());
    }

    @Test
    public void putDateTime() throws Exception {
        DatetimeWrapper body = new DatetimeWrapper();
        body.setField(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
        body.setNow(OffsetDateTime.of(2015, 5, 18, 18, 38, 0, 0, ZoneOffset.UTC));
        client.primitives().putDateTimeWithResponseAsync(body).block();
    }

    @Test
    public void getDateTimeRfc1123() throws Exception {
        Datetimerfc1123Wrapper result = client.primitives().getDateTimeRfc1123();
        Assert.assertEquals(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC), result.getField());
        Assert.assertEquals(OffsetDateTime.of(2015, 5, 18, 11, 38, 0, 0, ZoneOffset.UTC), result.getNow());
    }

    @Test
    public void putDateTimeRfc1123() throws Exception {
        Datetimerfc1123Wrapper body = new Datetimerfc1123Wrapper();
        body.setField(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
        body.setNow(OffsetDateTime.of(2015, 5, 18, 11, 38, 0, 0, ZoneOffset.UTC));
        client.primitives().putDateTimeRfc1123WithResponseAsync(body).block();
    }

    @Test
    public void getDuration() throws Exception {
        DurationWrapper result = client.primitives().getDuration();
        Assert.assertEquals(Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11), result.getField());
    }

    @Test
    public void putDuration() throws Exception {
        DurationWrapper body = new DurationWrapper();
        body.setField(Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11));
        client.primitives().putDurationWithResponseAsync(body).block();
    }

    @Test
    public void getByte() throws Exception {
        ByteWrapper result = client.primitives().getByte();
        byte[] expected = new byte[] {
                (byte) 255, (byte) 254, (byte) 253, (byte) 252, (byte) 0,
                (byte) 250, (byte) 249, (byte) 248, (byte) 247, (byte) 246
        };
        Assert.assertArrayEquals(expected, result.getField());
    }

    @Test
    public void putByte() throws Exception {
        ByteWrapper body = new ByteWrapper();
        byte[] byteArray = new byte[] {
                (byte) 255, (byte) 254, (byte) 253, (byte) 252, (byte) 0,
                (byte) 250, (byte) 249, (byte) 248, (byte) 247, (byte) 246
        };
        body.setField(byteArray);
        client.primitives().putByteWithResponseAsync(body).block();
    }
}