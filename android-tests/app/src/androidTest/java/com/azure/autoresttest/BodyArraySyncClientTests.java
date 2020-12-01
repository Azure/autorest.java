package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.android.core.util.Base64Url;
import com.azure.android.core.util.DateTimeRfc1123;
import com.azure.androidtest.fixtures.bodyarray.AutoRestSwaggerBATArrayServiceClient;
import com.azure.androidtest.fixtures.bodyarray.models.Enum0;
import com.azure.androidtest.fixtures.bodyarray.models.Enum1;
import com.azure.androidtest.fixtures.bodyarray.models.FooEnum;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class BodyArraySyncClientTests {
    private static AutoRestSwaggerBATArrayServiceClient client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATArrayServiceClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getNull() {
        final Response<List<List<String>>> getResponse = client.getArrayNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue());
    }

    @Test
    public void getInvalid() {
        try {
            client.getInvalidWithRestResponse();
            fail();
        } catch (RuntimeException exception) {
            Assert.assertTrue(exception.getMessage().contains("Unexpected end-of-input"));
        }
    }

    @Test
    public void getEmpty() {
        final Response<List<Integer>> getResponse = client.getEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertEquals(0, getResponse.getValue().size());
    }

    @Test
    public void putEmpty() {
        final Response<Void> putResponse = client.putEmptyWithRestResponse(new ArrayList<String>());
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getBooleanTfft() {
        final Response<List<Boolean>> getResponse = client.getBooleanTfftWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Object[] exected = new Boolean[] {true, false, false, true};
        Assert.assertArrayEquals(exected, getResponse.getValue().toArray());
    }

    @Test
    public void putBooleanTfft() {
        final Response<Void> putResponse = client.putBooleanTfftWithRestResponse(Arrays.asList(true, false, false, true));
        assertEquals(200, putResponse.getStatusCode());
    }

    /* This fails because deserializer returned [true, false, false] where we expect [true, null, false] (or exception throw)
    The fix is to modify JacksonAdapter in azure-sdk-for-android to throw on null when deserializing boolean
    @Test
    public void getBooleanInvalidNull() {
        final Response<List<Boolean>> getResponse = client.getBooleanInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue().get(1));
    }

     */

    @Test
    public void getBooleanInvalidString() {
        try {
            client.getBooleanInvalidStringWithRestResponse();
            fail();
        } catch (RuntimeException ex) {
            Assert.assertTrue(ex.getMessage().contains("Cannot deserialize value"));
        }
    }

    @Test
    public void getIntegerValid() {
        final Response<List<Integer>> getResponse = client.getIntegerValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Object[] expected = new Integer[] {1, -1, 3, 300};
        Assert.assertArrayEquals(expected, getResponse.getValue().toArray());
    }

    @Test
    public void putIntegerValid() {
        final Response<Void> putResponse = client.putIntegerValidWithRestResponse(Arrays.asList(1, -1, 3, 300));
        assertEquals(200, putResponse.getStatusCode());
    }

    /* This fails because deserializer returned [1, 0, 0] where we expect [1, null, 0] (or exception throw)
    The fix is to modify JacksonAdapter in azure-sdk-for-android to throw on null when deserializing int
    @Test
    public void getIntInvalidNull() {
        final Response<List<Integer>> getResponse = client.getIntInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue().get(1));
    }
     */

    @Test
    public void getIntInvalidString() {
        try {
            client.getIntInvalidStringWithRestResponse();
            fail();
        } catch (RuntimeException ex) {
            Assert.assertTrue(ex.getMessage().contains("Cannot deserialize value"));
        }
    }

    @Test
    public void getLongValid() {
        final Response<List<Long>> getResponse = client.getLongValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Object[] expected = new Long[] {1L, -1L, 3L, 300L};
        Assert.assertArrayEquals(expected, getResponse.getValue().toArray());
    }

    @Test
    public void putLongValid() {
        final Response<Void> putResponse = client.putLongValidWithRestResponse(Arrays.asList(1L, -1L, 3L, 300L));
        assertEquals(200, putResponse.getStatusCode());
    }

    /* This fails because deserializer returned [1, 0, 0] where we expect [1, null, 0] (or exception throw)
    The fix is to modify JacksonAdapter in azure-sdk-for-android to throw on null when deserializing long
    @Test
    public void getLongInvalidNull() {
        final Response<List<Long>> getResponse = client.getLongInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue().get(1));
    }

     */

    @Test
    public void getLongInvalidString() {
        try {
            client.getLongInvalidStringWithRestResponse();
            fail();
        } catch (RuntimeException ex) {
            Assert.assertTrue(ex.getMessage().contains("Cannot deserialize value"));
        }
    }

    @Test
    public void getFloatValid() {
        final Response<List<Float>> getResponse = client.getFloatValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Object[] expected = new Float[] {0f, -0.01f, -1.2e20f};
        Assert.assertArrayEquals(expected, getResponse.getValue().toArray());
    }

    @Test
    public void putFloatValid() {
        final Response<Void> putResponse = client.putFloatValidWithRestResponse(Arrays.asList(0f, -0.01f, -1.2e20f));
        assertEquals(200, putResponse.getStatusCode());
    }

    /* This fails because deserializer returned [0, 0, 1.2e20] where we expect [0, null, 1.2e20] (or exception throw)
    The fix is to modify JacksonAdapter in azure-sdk-for-android to throw on null when deserializing float
    @Test
    public void getFloatInvalidNull() {
        final Response<List<Float>> getResponse = client.getFloatInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue().get(1));
    }

     */

    @Test
    public void getFloatInvalidString() {
        try {
            client.getFloatInvalidStringWithRestResponse();
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("Cannot deserialize value"));
        }
    }

    @Test
    public void getDoubleValid() {
        final Response<List<Double>> getResponse = client.getDoubleValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Object[] expected = new Double[] {0d, -0.01, -1.2e20};
        Assert.assertArrayEquals(expected, getResponse.getValue().toArray());
    }

    @Test
    public void putDoubleValid() {
        final Response<Void> putResponse = client.putDoubleValidWithRestResponse(Arrays.asList(0d, -0.01d, -1.2e20d));
        assertEquals(200, putResponse.getStatusCode());
    }

    /* This fails because deserializer returned [0, 0, 1.2e20] where we expect [0, null, 1.2e20] (or exception throw)
    The fix is to modify JacksonAdapter in azure-sdk-for-android to throw on null when deserializing double
    @Test
    public void getDoubleInvalidNull() {
        final Response<List<Double>> getResponse = client.getDoubleInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue().get(1));
    }

     */

    @Test
    public void getDoubleInvalidString() {
        try {
            client.getDoubleInvalidStringWithRestResponse();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("Cannot deserialize value"));
        }
    }

    @Test
    public void getStringValid() {
        final Response<List<String>> getResponse = client.getStringValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Object[] expected = new String[] {"foo1", "foo2", "foo3"};
        Assert.assertArrayEquals(expected, getResponse.getValue().toArray());
    }

    @Test
    public void putStringValid() {
        final Response<Void> putResponse = client.putStringValidWithRestResponse(Arrays.asList("foo1", "foo2", "foo3"));
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getEnumValid() {
        final Response<List<FooEnum>> getResponse = client.getEnumValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Object[] expected = new FooEnum[] {FooEnum.FOO1, FooEnum.FOO2, FooEnum.FOO3};
        Assert.assertArrayEquals(expected, getResponse.getValue().toArray());
    }

    @Test
    public void putEnumValid() {
        final Response<Void> putResponse = client.putEnumValidWithRestResponse(Arrays.asList(FooEnum.FOO1, FooEnum.FOO2, FooEnum.FOO3));
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getStringEnumValid() {
        final Response<List<Enum0>> getResponse = client.getStringEnumValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Object[] expected = new Enum0[] {Enum0.FOO1, Enum0.FOO2, Enum0.FOO3};
        Assert.assertArrayEquals(expected, getResponse.getValue().toArray());
    }

    @Test
    public void putStringEnumValid() {
        final Response<Void> putResponse = client.putStringEnumValidWithRestResponse(Arrays.asList(Enum1.FOO1, Enum1.FOO2, Enum1.FOO3));
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getStringWithNull() {
        final Response<List<String>> getResponse = client.getStringWithNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue().get(1));
    }

    @Test
    public void getStringWithInvalid() {
        final Response<List<String>> getResponse = client.getStringWithInvalidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertEquals("foo", getResponse.getValue().get(0));
        assertEquals("123", getResponse.getValue().get(1));
        assertEquals("foo2", getResponse.getValue().get(2));
    }

    @Test
    public void getUuidValid() {
        final Response<List<UUID>> getResponse = client.getUuidValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Object[] expected = new UUID[] {UUID.fromString("6dcc7237-45fe-45c4-8a6b-3a8a3f625652"),
                UUID.fromString("d1399005-30f7-40d6-8da6-dd7c89ad34db"),
                UUID.fromString("f42f6aa1-a5bc-4ddf-907e-5f915de43205")};
        Assert.assertArrayEquals(expected, getResponse.getValue().toArray());
    }

    @Test
    public void putUuidValid() {
        final Response<Void> putResponse = client.putUuidValidWithRestResponse(Arrays.asList(UUID.fromString("6dcc7237-45fe-45c4-8a6b-3a8a3f625652"),
                UUID.fromString("d1399005-30f7-40d6-8da6-dd7c89ad34db"), UUID.fromString("f42f6aa1-a5bc-4ddf-907e-5f915de43205")));
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getUuidInvalidChars() {
        try {
            client.getUuidInvalidCharsWithRestResponse();
            fail();
        } catch (RuntimeException ex) {
            Assert.assertTrue(ex.getMessage().contains("Cannot deserialize value"));
        }
    }

    /* This fails because deserialization cannot construct LocalDate from serialized string from TestServer, e.g. '2000-12-01'
    The fix is to add LocalDate deserializer to ThreeTenModule in azure-sdk-for-android
    @Test
    public void getDateValid() {
        final Response<List<LocalDate>> getResponse = client.getDateValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Object[] expected = new LocalDate[] {
                LocalDate.of(2000, 12, 1),
                LocalDate.of(1980, 1, 2),
                LocalDate.of(1492, 10, 12)
        };
        Assert.assertArrayEquals(expected, getResponse.getValue().toArray());
    }
     */

    /* This fails because LocalDate is serialized as { day: d, month: m, year: y } which TestServer expects 'yyyy-mm-dd'
    The fix is to add LocalDate serializer to ThreeTenModule in azure-sdk-for-android
    @Test
    public void putDateValid() {
        final Response<Void> putResponse = client.putDateValidWithRestResponse(Arrays.asList(
                LocalDate.of(2000, 12, 1),
                LocalDate.of(1980, 1, 2),
                LocalDate.of(1492, 10, 12)
        ));
        assertEquals(200, putResponse.getStatusCode());
    }
     */

    @Test
    public void getDateInvalidNull() {
        try {
            final Response<List<LocalDate>> getResponse = client.getDateInvalidNullWithRestResponse();
            fail();
        } catch (Exception ex) {
            Assert.assertTrue(ex.getMessage().contains("Cannot construct instance"));
        }
    }

    @Test
    public void getDateInvalidString() {
        try {
            client.getDateInvalidCharsWithRestResponse();
            fail();
        } catch (RuntimeException ex) {
            Assert.assertTrue(ex.getMessage().contains("Cannot construct instance"));
        }
    }

    @Test
    public void getDateTimeValid() {
        final Response<List<OffsetDateTime>> getResponse = client.getDateTimeValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Object[] expected = new OffsetDateTime[] {
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)
        };
        Assert.assertArrayEquals(expected, getResponse.getValue().toArray());
    }

    @Test
    public void putDateTimeValid() {
        final Response<Void> putResponse = client.putDateTimeValidWithRestResponse(Arrays.asList(
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)
        ));
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getDateTimeInvalidNull() {
        final Response<List<OffsetDateTime>> getResponse = client.getDateTimeInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue().get(1));
    }

    @Test
    public void getDateTimeInvalidString() {
        try {
            client.getDateTimeInvalidCharsWithRestResponse();
            fail();
        } catch (RuntimeException ex) {
            Assert.assertTrue(ex.getMessage().contains("Cannot deserialize value"));
        }
    }

    @Test
    public void getDateTimeRfc1123Valid() {
        final Response<List<DateTimeRfc1123>> getResponse = client.getDateTimeRfc1123ValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Object[] expected = new DateTimeRfc1123[] {
                new DateTimeRfc1123(OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC)),
                new DateTimeRfc1123(OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC)),
                new DateTimeRfc1123(OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC))
        };
        Assert.assertArrayEquals(expected, getResponse.getValue().toArray());
    }

    @Test
    public void putDateTimeRfc1123Valid() {
        final Response<Void> putResponse = client.putDateTimeRfc1123ValidWithRestResponse(Arrays.asList(
                new DateTimeRfc1123(OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC)),
                new DateTimeRfc1123(OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC)),
                new DateTimeRfc1123(OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC))
        ));
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getDurationValid() {
        final Response<List<Duration>> getResponse = client.getDurationValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Duration[] expected = new Duration[] {
                Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11),
                Duration.ofDays(5).plusHours(1)
        };
        Assert.assertArrayEquals(expected, getResponse.getValue().toArray());
    }

    @Test
    public void putDurationValid() {
        final Response<Void> putResponse = client.putDurationValidWithRestResponse(Arrays.asList(
                Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11),
                Duration.ofDays(5).plusHours(1)));
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getByteValid() {
        final Response<List<byte[]>> getResponse = client.getByteValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Object[] expected = new byte[][] {
                new byte[] {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFA},
                new byte[] {(byte) 0x01, (byte) 0x02, (byte) 0x03},
                new byte[] {(byte) 0x25, (byte) 0x29, (byte) 0x43}
        };
        Assert.assertArrayEquals(expected, getResponse.getValue().toArray());
    }

    @Test
    public void putByteValid() {
        final Response<Void> putResponse = client.putByteValidWithRestResponse(Arrays.asList(
                new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFA},
                new byte[]{(byte) 0x01, (byte) 0x02, (byte) 0x03},
                new byte[]{(byte) 0x25, (byte) 0x29, (byte) 0x43}
        ));
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getByteInvalidNull() {
        final Response<List<byte[]>> getResponse = client.getByteInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue().get(1));
    }

    @Test
    public void getBase64Url() {
        final Response<List<Base64Url>> getResponse = client.getBase64UrlWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        List<Base64Url> result = getResponse.getValue();
        assertEquals(3, result.size());
        assertEquals("a string that gets encoded with base64url", BodyStringSyncClientTests.byteToString(result.get(0).encodedBytes()));
        assertEquals("test string", BodyStringSyncClientTests.byteToString(result.get(1).encodedBytes()));
        assertEquals("Lorem ipsum", BodyStringSyncClientTests.byteToString(result.get(2).encodedBytes()));
    }

    @Test
    public void getComplexNull() {
        final Response<List<Product1>> getResponse = client.getComplexNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue());
    }

    @Test
    public void getComplexEmpty() {
        final Response<List<Product1>> getResponse = client.getComplexEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertEquals(0, getResponse.getValue().size());
    }

    @Test
    public void getComplexItemNull() {
        final Response<List<Product1>> getResponse = client.getComplexItemNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertEquals(3, getResponse.getValue().size());
        assertNull(getResponse.getValue().get(1));
    }

    @Test
    public void getComplexItemEmpty() {
        final Response<List<Product1>> getResponse = client.getComplexItemEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        List<Product1> result = getResponse.getValue();
        assertEquals(3, result.size());
        assertNull(result.get(1).getString());
    }

    @Test
    public void getComplexValid() {
        final Response<List<Product1>> getResponse = client.getComplexValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        List<Product1> result = getResponse.getValue();
        assertEquals(3, result.size());
        assertEquals(5, result.get(2).getInteger().intValue());
        assertEquals("6", result.get(2).getString());
    }

    @Test
    public void putComplexValid() {
        List<Product1> body = new ArrayList<Product1>();
        Product1 p1 = new Product1();
        p1.setInteger(1);
        p1.setString("2");
        body.add(p1);
        Product1 p2 = new Product1();
        p2.setInteger(3);
        p2.setString("4");
        body.add(p2);
        Product1 p3 = new Product1();
        p3.setInteger(5);
        p3.setString("6");
        body.add(p3);
        final Response<Void> putResponse = client.putComplexValidWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getArrayNull() {
        final Response<List<List<String>>> getResponse = client.getArrayNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue());
    }

    @Test
    public void getArrayEmpty() {
        final Response<List<List<String>>> getResponse = client.getArrayEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        List<List<String>> result = getResponse.getValue();
        assertEquals(0, result.size());
    }

    @Test
    public void getArrayItemNull() {
        final Response<List<List<String>>> getResponse = client.getArrayItemNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        List<List<String>> result = getResponse.getValue();
        assertEquals(3, result.size());
        assertNull(result.get(1));
    }

    @Test
    public void getArrayItemEmpty() {
        final Response<List<List<String>>> getResponse = client.getArrayItemEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        List<List<String>> result = getResponse.getValue();
        assertEquals(3, result.size());
        assertEquals(0, result.get(1).size());
    }

    @Test
    public void getArrayValid() {
        final Response<List<List<String>>> getResponse = client.getArrayValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        List<List<String>> result = getResponse.getValue();
        Assert.assertArrayEquals(new String[]{"1", "2", "3"}, result.get(0).toArray());
        Assert.assertArrayEquals(new String[]{"4", "5", "6"}, result.get(1).toArray());
        Assert.assertArrayEquals(new String[] {"7", "8", "9"}, result.get(2).toArray());
    }

    @Test
    public void putArrayValid() {
        List<List<String>> body = new ArrayList<List<String>>();
        body.add(Arrays.asList("1", "2", "3"));
        body.add(Arrays.asList("4", "5", "6"));
        body.add(Arrays.asList("7", "8", "9"));
        final Response<Void> putResponse = client.putArrayValidWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getDictionaryNull() {
        final Response<List<Map<String, String>>> getResponse = client.getDictionaryNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue());
    }

    @Test
    public void getDictionaryEmpty() {
        final Response<List<Map<String, String>>> getResponse = client.getDictionaryEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        List<Map<String, String>> result = getResponse.getValue();
        assertEquals(0, result.size());
    }

    @Test
    public void getDictionaryItemNull() {
        final Response<List<Map<String, String>>> getResponse = client.getDictionaryItemNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        List<Map<String, String>> result = getResponse.getValue();
        assertEquals(3, result.size());
        assertNull(result.get(1));
    }

    @Test
    public void getDictionaryItemEmpty() {
        final Response<List<Map<String, String>>> getResponse = client.getDictionaryItemEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        List<Map<String, String>> result = getResponse.getValue();
        assertEquals(3, result.size());
        assertEquals(0, result.get(1).size());
    }

    @Test
    public void getDictionaryValid() {
        final Response<List<Map<String, String>>> getResponse = client.getDictionaryValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        List<Map<String, String>> result = getResponse.getValue();
        assertEquals("seven", result.get(2).get("7"));
        assertEquals("five", result.get(1).get("5"));
        assertEquals("three", result.get(0).get("3"));
    }

    @Test
    public void putDictionaryValid() {
        List<Map<String, String>> body = new ArrayList<Map<String, String>>();
        Map<String, String> m1 = new HashMap<String, String>();
        m1.put("1", "one");
        m1.put("2", "two");
        m1.put("3", "three");
        body.add(m1);
        Map<String, String> m2 = new HashMap<String, String>();
        m2.put("4", "four");
        m2.put("5", "five");
        m2.put("6", "six");
        body.add(m2);
        Map<String, String> m3 = new HashMap<String, String>();
        m3.put("7", "seven");
        m3.put("8", "eight");
        m3.put("9", "nine");
        body.add(m3);
        final Response<Void> putResponse = client.putDictionaryValidWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }
}
