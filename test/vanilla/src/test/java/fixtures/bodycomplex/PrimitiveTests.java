package fixtures.bodycomplex;

import fixtures.bodycomplex.implementation.AutoRestComplexTestServiceImpl;
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
        client = new AutoRestComplexTestServiceImpl();
    }

    @Test
    public void getInt() throws Exception {
        IntWrapper result = client.primitives().getInt();
        Assert.assertEquals(Integer.valueOf(-1), result.field1());
        Assert.assertEquals(Integer.valueOf(2), result.field2());
    }

    @Test
    public void putInt() throws Exception {
        IntWrapper body = new IntWrapper();
        body.field1(-1);
        body.field2(2);
        client.primitives().putInt(body);
    }

    @Test
    public void getLong() throws Exception {
        LongWrapper result = client.primitives().getLong();
        Assert.assertEquals(Long.valueOf(1099511627775L), result.field1());
        Assert.assertEquals(Long.valueOf(-999511627788L), result.field2());
    }

    @Test
    public void putLong() throws Exception {
        LongWrapper body = new LongWrapper();
        body.field1(1099511627775L);
        body.field2(-999511627788L);
        client.primitives().putLong(body);
    }

    @Test
    public void getFloat() throws Exception {
        FloatWrapper result = client.primitives().getFloat();
        Assert.assertEquals(1.05, result.field1(), 0f);
        Assert.assertEquals(-0.003, result.field2(), 0f);
    }

    @Test
    public void putFloat() throws Exception {
        FloatWrapper body = new FloatWrapper();
        body.field1(1.05);
        body.field2(-0.003);
        client.primitives().putFloat(body);
    }

    @Test
    public void getDouble() throws Exception {
        DoubleWrapper result = client.primitives().getDouble();
        Assert.assertEquals(3e-100, result.field1(), 0f);
        Assert.assertEquals(-0.000000000000000000000000000000000000000000000000000000005,
                result.field56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose(),
                0f);
    }

    @Test
    public void putDouble() throws Exception {
        DoubleWrapper body = new DoubleWrapper();
        body.field1(3e-100);
        body.field56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose(-5e-57);
        client.primitives().putDouble(body);
    }

    @Test
    public void getBool() throws Exception {
        BooleanWrapper result = client.primitives().getBool();
        Assert.assertEquals(true, result.fieldTrue());
        Assert.assertEquals(false, result.fieldFalse());
    }

    @Test
    public void putBool() throws Exception {
        BooleanWrapper body = new BooleanWrapper();
        body.fieldFalse(false);
        body.fieldTrue(true);
        client.primitives().putBool(body);
    }

    @Test
    public void getString() throws Exception {
        StringWrapper result = client.primitives().getString();
        Assert.assertEquals("goodrequest", result.field());
        Assert.assertEquals("", result.empty());
        Assert.assertEquals(null, result.nullProperty());
    }

    @Test
    public void putString() throws Exception {
        StringWrapper body = new StringWrapper();
        body.field("goodrequest");
        body.empty("");
        client.primitives().putString(body);
    }

    @Test
    public void getDate() throws Exception {
        DateWrapper result = client.primitives().getDate();
        Assert.assertEquals(LocalDate.of(1, 1, 1), result.field());
        Assert.assertEquals(LocalDate.of(2016, 2, 29), result.leap());
    }

    @Test
    public void putDate() throws Exception {
        DateWrapper body = new DateWrapper();
        body.field(LocalDate.of(1, 1, 1));
        body.leap(LocalDate.of(2016, 2, 29));
        client.primitives().putDate(body);
    }

    @Test
    public void getDateTime() throws Exception {
        DatetimeWrapper result = client.primitives().getDateTime();
        Assert.assertEquals(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC), result.field());
        Assert.assertEquals(OffsetDateTime.of(2015, 5, 18, 18, 38, 0, 0, ZoneOffset.UTC), result.now());
    }

    @Test
    public void putDateTime() throws Exception {
        DatetimeWrapper body = new DatetimeWrapper();
        body.field(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
        body.now(OffsetDateTime.of(2015, 5, 18, 18, 38, 0, 0, ZoneOffset.UTC));
        client.primitives().putDateTime(body);
    }

    @Test
    public void getDateTimeRfc1123() throws Exception {
        Datetimerfc1123Wrapper result = client.primitives().getDateTimeRfc1123();
        Assert.assertEquals(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC), result.field());
        Assert.assertEquals(OffsetDateTime.of(2015, 5, 18, 11, 38, 0, 0, ZoneOffset.UTC), result.now());
    }

    @Test
    public void putDateTimeRfc1123() throws Exception {
        Datetimerfc1123Wrapper body = new Datetimerfc1123Wrapper();
        body.field(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
        body.now(OffsetDateTime.of(2015, 5, 18, 11, 38, 0, 0, ZoneOffset.UTC));
        client.primitives().putDateTimeRfc1123(body);
    }

    @Test
    public void getDuration() throws Exception {
        DurationWrapper result = client.primitives().getDuration();
        Assert.assertEquals(Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11), result.field());
    }

    @Test
    public void putDuration() throws Exception {
        DurationWrapper body = new DurationWrapper();
        body.field(Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11));
        client.primitives().putDuration(body);
    }

    @Test
    public void getByte() throws Exception {
        ByteWrapper result = client.primitives().getByte();
        byte[] expected = new byte[] {
                (byte) 255, (byte) 254, (byte) 253, (byte) 252, (byte) 0,
                (byte) 250, (byte) 249, (byte) 248, (byte) 247, (byte) 246
        };
        Assert.assertArrayEquals(expected, result.field());
    }

    @Test
    public void putByte() throws Exception {
        ByteWrapper body = new ByteWrapper();
        byte[] byteArray = new byte[] {
                (byte) 255, (byte) 254, (byte) 253, (byte) 252, (byte) 0,
                (byte) 250, (byte) 249, (byte) 248, (byte) 247, (byte) 246
        };
        body.field(byteArray);
        client.primitives().putByte(body);
    }
}
