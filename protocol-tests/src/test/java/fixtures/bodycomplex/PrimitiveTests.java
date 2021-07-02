package fixtures.bodycomplex;

import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;

public class PrimitiveTests {
    private static PrimitiveAsyncClient asyncClient;

    private static PrimitiveClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new AutoRestComplexTestServiceBuilder().buildPrimitiveAsyncClient();
        client = new AutoRestComplexTestServiceBuilder().buildPrimitiveClient();
    }

    @Test
    public void getInt() throws Exception {
        BinaryData binaryData = client.getInt(null);
        Map map = binaryData.toObject(Map.class);
        Assertions.assertEquals(-1, map.get("field1"));
        Assertions.assertEquals(2, map.get("field2"));
    }

    @Test
    public void putInt() throws Exception {
        BinaryData binaryData = BinaryData.fromString("{\"field1\": -1, \"field2\": 2}");
        asyncClient.putIntWithResponse(binaryData, null).block();
    }

//    @Test
//    public void getLong() throws Exception {
//        LongWrapper result = client.getPrimitives().getLong();
//        Assertions.assertEquals(1099511627775L, result.getField1().longValue());
//        Assertions.assertEquals(-999511627788L, result.getField2().longValue());
//    }
//
//    @Test
//    public void putLong() throws Exception {
//        LongWrapper body = new LongWrapper();
//        body.setField1(1099511627775L);
//        body.setField2(-999511627788L);
//        client.getPrimitives().putLongWithResponseAsync(body).block();
//    }
//
//    @Test
//    public void getFloat() throws Exception {
//        FloatWrapper result = client.getPrimitives().getFloat();
//        Assertions.assertEquals(1.05f, result.getField1(), 0f);
//        Assertions.assertEquals(-0.003f, result.getField2(), 0f);
//    }
//
//    @Test
//    public void putFloat() throws Exception {
//        FloatWrapper body = new FloatWrapper();
//        body.setField1(1.05f);
//        body.setField2(-0.003f);
//        client.getPrimitives().putFloatWithResponseAsync(body).block();
//    }
//
//    @Test
//    public void getDouble() throws Exception {
//        DoubleWrapper result = client.getPrimitives().getDouble();
//        Assertions.assertEquals(3e-100, result.getField1(), 0f);
//        Assertions.assertEquals(-0.000000000000000000000000000000000000000000000000000000005,
//                result.getField56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose(),
//                0f);
//    }
//
//    @Test
//    public void putDouble() throws Exception {
//        DoubleWrapper body = new DoubleWrapper();
//        body.setField1(3e-100);
//        body.setField56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose(-5e-57);
//        client.getPrimitives().putDoubleWithResponseAsync(body).block();
//    }
//
//    @Test
//    public void getBool() throws Exception {
//        BooleanWrapper result = client.getPrimitives().getBool();
//        Assertions.assertEquals(true, result.isFieldTrue());
//        Assertions.assertEquals(false, result.isFieldFalse());
//    }
//
//    @Test
//    public void putBool() throws Exception {
//        BooleanWrapper body = new BooleanWrapper();
//        body.setFieldFalse(false);
//        body.setFieldTrue(true);
//        client.getPrimitives().putBoolWithResponseAsync(body).block();
//    }
//
//    @Test
//    public void getString() throws Exception {
//        StringWrapper result = client.getPrimitives().getString();
//        Assertions.assertEquals("goodrequest", result.getField());
//        Assertions.assertEquals("", result.getEmpty());
//        Assertions.assertEquals(null, result.getNullProperty());
//    }
//
//    @Test
//    public void putString() throws Exception {
//        StringWrapper body = new StringWrapper();
//        body.setField("goodrequest");
//        body.setEmpty("");
//        client.getPrimitives().putStringWithResponseAsync(body).block();
//    }
//
//    @Test
//    public void getDate() throws Exception {
//        DateWrapper result = client.getPrimitives().getDate();
//        Assertions.assertEquals(LocalDate.of(1, 1, 1), result.getField());
//        Assertions.assertEquals(LocalDate.of(2016, 2, 29), result.getLeap());
//    }
//
//    @Test
//    public void putDate() throws Exception {
//        DateWrapper body = new DateWrapper();
//        body.setField(LocalDate.of(1, 1, 1));
//        body.setLeap(LocalDate.of(2016, 2, 29));
//        client.getPrimitives().putDateWithResponseAsync(body).block();
//    }
//
//    @Test
//    public void getDateTime() throws Exception {
//        DatetimeWrapper result = client.getPrimitives().getDateTime();
//        Assertions.assertEquals(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC), result.getField());
//        Assertions.assertEquals(OffsetDateTime.of(2015, 5, 18, 18, 38, 0, 0, ZoneOffset.UTC), result.getNow());
//    }
//
//    @Test
//    public void putDateTime() throws Exception {
//        DatetimeWrapper body = new DatetimeWrapper();
//        body.setField(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
//        body.setNow(OffsetDateTime.of(2015, 5, 18, 18, 38, 0, 0, ZoneOffset.UTC));
//        client.getPrimitives().putDateTimeWithResponseAsync(body).block();
//    }
//
//    @Test
//    public void getDateTimeRfc1123() throws Exception {
//        Datetimerfc1123Wrapper result = client.getPrimitives().getDateTimeRfc1123();
//        Assertions.assertEquals(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC), result.getField());
//        Assertions.assertEquals(OffsetDateTime.of(2015, 5, 18, 11, 38, 0, 0, ZoneOffset.UTC), result.getNow());
//    }
//
//    @Test
//    public void putDateTimeRfc1123() throws Exception {
//        Datetimerfc1123Wrapper body = new Datetimerfc1123Wrapper();
//        body.setField(OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
//        body.setNow(OffsetDateTime.of(2015, 5, 18, 11, 38, 0, 0, ZoneOffset.UTC));
//        client.getPrimitives().putDateTimeRfc1123WithResponseAsync(body).block();
//    }
//
//    @Test
//    public void getDuration() throws Exception {
//        DurationWrapper result = client.getPrimitives().getDuration();
//        Assertions.assertEquals(Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11), result.getField());
//    }
//
//    @Test
//    public void putDuration() throws Exception {
//        DurationWrapper body = new DurationWrapper();
//        body.setField(Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11));
//        client.getPrimitives().putDurationWithResponseAsync(body).block();
//    }
//
//    @Test
//    public void getByte() throws Exception {
//        ByteWrapper result = client.getPrimitives().getByte();
//        byte[] expected = new byte[] {
//                (byte) 255, (byte) 254, (byte) 253, (byte) 252, (byte) 0,
//                (byte) 250, (byte) 249, (byte) 248, (byte) 247, (byte) 246
//        };
//        Assertions.assertArrayEquals(expected, result.getField());
//    }
//
//    @Test
//    public void putByte() throws Exception {
//        ByteWrapper body = new ByteWrapper();
//        byte[] byteArray = new byte[] {
//                (byte) 255, (byte) 254, (byte) 253, (byte) 252, (byte) 0,
//                (byte) 250, (byte) 249, (byte) 248, (byte) 247, (byte) 246
//        };
//        body.setField(byteArray);
//        client.getPrimitives().putByteWithResponseAsync(body).block();
//    }
}
