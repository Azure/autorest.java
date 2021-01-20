package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.android.core.internal.util.serializer.exception.MalformedValueException;
import com.azure.android.core.util.Base64Url;
import com.azure.android.core.util.DateTimeRfc1123;
import com.azure.androidtest.fixtures.bodydictionary.AutoRestSwaggerBATDictionaryServiceClient;
import com.azure.androidtest.fixtures.bodydictionary.models.Widget;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class BodyDictionarySyncClientTests {
    private static AutoRestSwaggerBATDictionaryServiceClient client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATDictionaryServiceClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getNull() {
        final Response<Map<String, Integer>> getResponse = client.getNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue());
    }

    @Test
    public void getInvalid() {
        try {
            client.getInvalidWithRestResponse();
            fail();
        } catch (RuntimeException exception) {
            assertEquals(MalformedValueException.class, exception.getClass());
            assertTrue(exception.getMessage().contains("Unexpected character"));
        }
    }

    @Test
    public void getEmpty() {
        final Response<Map<String, Integer>> getResponse = client.getEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Integer> result = getResponse.getValue();
        assertEquals(0, result.keySet().size());
    }

    @Test
    public void putEmpty() {
        final Response<Void> putResponse = client.putEmptyWithRestResponse(new HashMap<>());
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getNullValue() {
        final Response<Map<String, String>> getResponse = client.getNullValueWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, String> result = getResponse.getValue();
        assertNull(result.get("key1"));
    }

    @Test
    public void getNullKey() {
        try {
            client.getNullKeyWithRestResponse();
            fail();
        } catch (RuntimeException exception) {
            assertEquals(MalformedValueException.class, exception.getClass());
            assertTrue(exception.getMessage().contains("Unexpected character"));
        }
    }

    @Test
    public void getEmptyStringKey() {
        final Response<Map<String, String>> getResponse = client.getEmptyStringKeyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, String> result = getResponse.getValue();
        assertEquals("val1", result.get(""));
    }

    @Test
    public void getBooleanTfft() {
        final Response<Map<String, Boolean>> getResponse = client.getBooleanTfftWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Boolean>  result = getResponse.getValue();
        Map<String, Boolean> expected = new HashMap<>();
        expected.put("0", true);
        expected.put("1", false);
        expected.put("2", false);
        expected.put("3", true);
        assertEquals(expected, result);
    }

    @Test
    public void putBooleanTfft() {
        Map<String, Boolean> testData = new HashMap<>();
        testData.put("0", true);
        testData.put("1", false);
        testData.put("2", false);
        testData.put("3", true);
        final Response<Void> putResponse = client.putBooleanTfftWithRestResponse(testData);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Ignore("Deserialization failed to return null. Fix deserialization to throw or return null")
    public void getBooleanInvalidNull() {
        final Response<Map<String, Boolean>> getResponse = client.getBooleanInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Boolean> result = getResponse.getValue();
        assertNull(result.get("1"));
    }

    @Test
    public void getBooleanInvalidString() {
        try {
            client.getBooleanInvalidStringWithRestResponse();
            fail();
        } catch (RuntimeException ex) {
            // expected
            assertTrue(ex.getCause().getMessage().contains("only \"true\" or \"false\" recognized"));
        }
    }

    @Test
    public void getIntegerValid() {
        final Response<Map<String, Integer>> getResponse = client.getIntegerValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Integer> result = getResponse.getValue();
        Map<String, Integer> expected = new HashMap<>();
        expected.put("0", 1);
        expected.put("1", -1);
        expected.put("2", 3);
        expected.put("3", 300);
        assertEquals(expected, result);
    }

    @Test
    public void putIntegerValid() {
        Map<String, Integer> testdata = new HashMap<>();
        testdata.put("0", 1);
        testdata.put("1", -1);
        testdata.put("2", 3);
        testdata.put("3", 300);
        final Response<Void> putResponse = client.putIntegerValidWithRestResponse(testdata);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Ignore("Deserialization failed to return null. Fix deserialization")
    public void getIntInvalidNull() {
        final Response<Map<String, Integer>> getResponse = client.getIntInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Integer> result = getResponse.getValue();
        assertNull(result.get("1"));
    }

    @Test
    public void getIntInvalidString() {
        try {
            client.getIntInvalidStringWithRestResponse();
            fail();
        } catch (RuntimeException ex) {
            // expected
            assertTrue(ex.getCause().getMessage().contains("not a valid Integer value"));
        }
    }

    @Test
    public void getLongValid() {
        final Response<Map<String, Long>> getResponse = client.getLongValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Long> result = getResponse.getValue();
        HashMap<String, Long> expected = new HashMap<>();
        expected.put("0", 1L);
        expected.put("1", -1L);
        expected.put("2", 3L);
        expected.put("3", 300L);
        assertEquals(expected, result);
    }

    @Test
    public void putLongValid() {
        HashMap<String, Long> expected = new HashMap<>();
        expected.put("0", 1L);
        expected.put("1", -1L);
        expected.put("2", 3L);
        expected.put("3", 300L);
        final Response<Void> putResponse = client.putLongValidWithRestResponse(expected);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Ignore("Deserialization failed to return null. Fix deserialization to return null or throw")
    public void getLongInvalidNull() {
        final Response<Map<String, Long>> getResponse = client.getLongInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Long> result = getResponse.getValue();
        assertNull(result.get("1"));
    }

    @Test
    public void getLongInvalidString() {
        try {
            client.getLongInvalidStringWithRestResponse();
            fail();
        } catch (RuntimeException ex) {
            // expected
            assertTrue(ex.getCause().getMessage().contains("not a valid Long value"));
        }
    }

    @Test
    public void getFloatValid() {
        final Response<Map<String, Float>> getResponse = client.getFloatValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Float> result = getResponse.getValue();
        Map<String, Float> expected = new HashMap<>();
        expected.put("0", 0f);
        expected.put("1", -0.01f);
        expected.put("2", -1.2e20f);
        assertEquals(expected, result);
    }

    @Test
    public void putFloatValid() {
        Map<String, Float> testdata = new HashMap<>();
        testdata.put("0", 0f);
        testdata.put("1", -0.01f);
        testdata.put("2", -1.2e20f);
        final Response<Void> putResponse = client.putFloatValidWithRestResponse(testdata);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Ignore("Deserialization failed to return null. Fix deserialization to return null or throw")
    public void getFloatInvalidNull() {
        final Response<Map<String, Float>> getResponse = client.getFloatInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Float> result = getResponse.getValue();
        assertNull(result.get("1"));
    }

    @Test
    public void getFloatInvalidString() {
        try {
            client.getFloatInvalidStringWithRestResponse();
            fail();
        } catch (RuntimeException ex) {
            // expected
            assertTrue(ex.getCause().getMessage().contains("not a valid Float value"));
        }
    }

    @Test
    public void getDoubleValid() {
        final Response<Map<String, Double>> getResponse = client.getDoubleValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Double> result = getResponse.getValue();
        Map<String, Double> expected = new HashMap<>();
        expected.put("0", 0d);
        expected.put("1", -0.01d);
        expected.put("2", -1.2e20d);
        assertEquals(expected, result);
    }

    @Test
    public void putDoubleValid() {
        //{"0": 0, "1": -0.01, "2": 1.2e20}
        Map<String, Double> testdata = new HashMap<>();
        testdata.put("0", 0d);
        testdata.put("1", -0.01d);
        testdata.put("2", -1.2e20d);
        final Response<Void> putResponse = client.putDoubleValidWithRestResponse(testdata);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Ignore("Deserialization failed to return null. Fix deserialization to return null or throw")
    public void getDoubleInvalidNull() {
        final Response<Map<String, Double>> getResponse = client.getDoubleInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Double> result = getResponse.getValue();
        assertNull(result.get("1"));
    }

    @Test
    public void getDoubleInvalidString() {
        try {
            client.getDoubleInvalidStringWithRestResponse();
            fail();
        } catch (RuntimeException ex) {
            // expected
            assertTrue(ex.getCause().getMessage().contains("not a valid Double value"));
        }
    }

    @Test
    public void getStringValid() {
        final Response<Map<String, String>> getResponse = client.getStringValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, String> result = getResponse.getValue();
        Map<String, String> expected = new HashMap<>();
        expected.put("0", "foo1");
        expected.put("1", "foo2");
        expected.put("2", "foo3");
        assertEquals(expected, result);
    }

    @Test
    public void putStringValid() {
        Map<String, String> testdata = new HashMap<>();
        testdata.put("0", "foo1");
        testdata.put("1", "foo2");
        testdata.put("2", "foo3");
        final Response<Void> putResponse = client.putStringValidWithRestResponse(testdata);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getStringWithNull() {
        final Response<Map<String, String>> getResponse = client.getStringWithNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, String> result = getResponse.getValue();
        assertNull(result.get("1"));
    }

    @Test
    public void getStringWithInvalid() {
        final Response<Map<String, String>> getResponse = client.getStringWithInvalidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, String> result = getResponse.getValue();
        assertEquals("123", result.get("1"));
    }

    @Ignore("Failed to deserialize LocalDate. Fix deserialization of LocalDate")
    public void getDateValid() {
        final Response<Map<String, LocalDate>> getResponse = client.getDateValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, LocalDate> result = getResponse.getValue();
        Map<String, LocalDate> expected = new HashMap<>();
        expected.put("0", LocalDate.of(2000, 12, 1));
        expected.put("1", LocalDate.of(1980, 1, 2));
        expected.put("2", LocalDate.of(1492, 10, 12));
        assertEquals(expected, result);
    }

    @Ignore("Server doesn't like the serialization. Fix LocalDate serialization")
    public void putDateValid() {
        Map<String, LocalDate> testdata = new HashMap<>();
        testdata.put("0", LocalDate.of(2000, 12, 1));
        testdata.put("1", LocalDate.of(1980, 1, 2));
        testdata.put("2", LocalDate.of(1492, 10, 12));
        final Response<Void> putResponse = client.putDateValidWithRestResponse(testdata);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Ignore("Failed to derialize LocalDate time. Fix deserialization of LocalDate")
    public void getDateInvalidNull() {
        final Response<Map<String, LocalDate>> getResponse = client.getDateInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, LocalDate> result = getResponse.getValue();
        assertNull(result.get("1"));
    }

    @Test
    public void getDateInvalidString() {
        try {
            client.getDateInvalidCharsWithRestResponse();
            fail();
        } catch (RuntimeException ex) {
            assertTrue(ex.getMessage().contains("Cannot construct instance"));
        }
    }

    @Test
    public void getDateTimeValid() {
        final Response<Map<String, OffsetDateTime>> getResponse = client.getDateTimeValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, OffsetDateTime> result = getResponse.getValue();
        Map<String, OffsetDateTime> expected = new HashMap<>();
        expected.put("0", OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC));
        expected.put("1", OffsetDateTime.of(1980, 1, 1, 23, 11, 35, 0, ZoneOffset.UTC));
        expected.put("2", OffsetDateTime.of(1492, 10, 12, 18, 15, 1, 0, ZoneOffset.UTC));
        assertEquals(expected, result);
    }

    @Test
    public void putDateTimeValid() {
        Map<String, OffsetDateTime> testdata = new HashMap<>();
        testdata.put("0", OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC));
        testdata.put("1", OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.ofHours(1)));
        testdata.put("2", OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.ofHours(-8)));
        final Response<Void> putResponse = client.putDateTimeValidWithRestResponse(testdata);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getDateTimeInvalidNull() {
        final Response<Map<String, OffsetDateTime>> getResponse = client.getDateTimeInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, OffsetDateTime> result = getResponse.getValue();
        assertNull(result.get("1"));
    }

    @Test
    public void getDateTimeInvalidString() {
        try {
            client.getDateTimeInvalidCharsWithRestResponse();
            fail();
        } catch (RuntimeException ex) {
            assertEquals(InvalidFormatException.class, ex.getCause().getClass());
        }
    }

    @Test
    public void getDateTimeRfc1123Valid() {
        final Response<Map<String, DateTimeRfc1123>> getResponse = client.getDateTimeRfc1123ValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, DateTimeRfc1123> result = getResponse.getValue();
        Map<String, DateTimeRfc1123> expected = new HashMap<>();
        expected.put("0", new DateTimeRfc1123(OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC)));
        expected.put("1", new DateTimeRfc1123(OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC)));
        expected.put("2", new DateTimeRfc1123(OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)));
        assertEquals(expected, result);
    }

    @Test
    public void putDateTimeRfc1123Valid() {
        Map<String, DateTimeRfc1123> testdata = new HashMap<>();
        testdata.put("0", new DateTimeRfc1123(OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC)));
        testdata.put("1", new DateTimeRfc1123(OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC)));
        testdata.put("2", new DateTimeRfc1123(OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)));
        final Response<Void> putResponse = client.putDateTimeRfc1123ValidWithRestResponse(testdata);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getDurationValid() {
        final Response<Map<String, Duration>> getResponse = client.getDurationValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Duration> result = getResponse.getValue();
        Map<String, Duration> expected = new HashMap<>();
        expected.put("0", Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11));
        expected.put("1", Duration.ofDays(5).plusHours(1));
        assertEquals(expected, result);
    }

    @Test
    public void putDurationValid() {
        Map<String, Duration> testdata = new HashMap<>();
        testdata.put("0", Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11));
        testdata.put("1", Duration.ofDays(5).plusHours(1));
        final Response<Void> putResponse = client.putDurationValidWithRestResponse(testdata);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getByteValid() {
        final Response<Map<String, byte[]>> getResponse = client.getByteValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, byte[]> result = getResponse.getValue();
        Map<String, byte[]> expected = new HashMap<>();
        expected.put("0", new byte[] {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFA});
        expected.put("1", new byte[] {(byte) 0x01, (byte) 0x02, (byte) 0x03});
        expected.put("2", new byte[] {(byte) 0x25, (byte) 0x29, (byte) 0x43});
        assertArrayEquals(expected.get("0"), result.get("0"));
        assertArrayEquals(expected.get("1"), result.get("1"));
        assertArrayEquals(expected.get("2"), result.get("2"));
    }

    @Test
    public void putByteValid() {
        Map<String, byte[]> testdata = new HashMap<>();
        testdata.put("0", new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFA});
        testdata.put("1", new byte[]{(byte) 0x01, (byte) 0x02, (byte) 0x03});
        testdata.put("2", new byte[]{(byte) 0x25, (byte) 0x29, (byte) 0x43});
        final Response<Void> putResponse = client.putByteValidWithRestResponse(testdata);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getByteInvalidNull() {
        final Response<Map<String, byte[]>> getResponse = client.getByteInvalidNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, byte[]> result = getResponse.getValue();
        assertNull(result.get("1"));
    }

    @Test
    public void getBase64Url() {
        final Response<Map<String, Base64Url>> getResponse = client.getBase64UrlWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Base64Url> result = getResponse.getValue();
        assertEquals("a string that gets encoded with base64url", BodyStringSyncClientTests.byteToString(result.get("0").encodedBytes()));
        assertEquals("test string", BodyStringSyncClientTests.byteToString(result.get("1").encodedBytes()));
        assertEquals("Lorem ipsum", BodyStringSyncClientTests.byteToString(result.get("2").encodedBytes()));
    }

    @Test
    public void getComplexNull() {
        final Response<Map<String, Widget>> getResponse = client.getComplexNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Widget> result = getResponse.getValue();
        assertNull(result);
    }

    @Test
    public void getComplexEmpty() {
        final Response<Map<String, Widget>> getResponse = client.getComplexEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Widget> result = getResponse.getValue();
        assertEquals(0, result.size());
    }

    @Test
    public void getComplexItemNull() {
        final Response<Map<String, Widget>> getResponse = client.getComplexItemNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Widget> result = getResponse.getValue();
        assertEquals(3, result.size());
        assertNull(result.get("1"));
    }

    @Test
    public void getComplexItemEmpty() {
        final Response<Map<String, Widget>> getResponse = client.getComplexItemEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Widget> result = getResponse.getValue();
        assertEquals(3, result.size());
        assertNull(result.get("1").getInteger());
        assertNull(result.get("1").getString());
    }

    @Test
    public void getComplexValid() {
        final Response<Map<String, Widget>> getResponse = client.getComplexValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Widget> result = getResponse.getValue();
        assertEquals(3, result.size());
        assertEquals(1, result.get("0").getInteger().intValue());
        assertEquals("4", result.get("1").getString());
    }

    @Test
    public void putComplexValid() {
        Map<String, Widget> body = new HashMap<>();
        Widget w1 = new Widget();
        w1.setInteger(1);
        w1.setString("2");
        body.put("0", w1);
        Widget w2 = new Widget();
        w2.setInteger(3);
        w2.setString("4");
        body.put("1", w2);
        Widget w3 = new Widget();
        w3.setInteger(5);
        w3.setString("6");
        body.put("2", w3);
        final Response<Void> putResponse = client.putComplexValidWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getArrayNull() {
        final Response<Map<String, List<String>>> getResponse = client.getArrayNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, List<String>> result = getResponse.getValue();
        assertNull(result);
    }

    @Test
    public void getArrayEmpty() {
        final Response<Map<String, List<String>>> getResponse = client.getArrayEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, List<String>> result = getResponse.getValue();
        assertEquals(0, result.size());
    }

    @Test
    public void getArrayItemNull() {
        final Response<Map<String, List<String>>> getResponse = client.getArrayItemNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, List<String>> result = getResponse.getValue();
        assertNull(result.get("1"));
    }

    @Test
    public void getArrayItemEmpty() {
        final Response<Map<String, List<String>>> getResponse = client.getArrayItemEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, List<String>> result = getResponse.getValue();
        assertEquals(0, result.get("1").size());
    }

    @Test
    public void getArrayValid() {
        final Response<Map<String, List<String>>> getResponse = client.getArrayValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, List<String>> result = getResponse.getValue();
        assertArrayEquals(new String[] {"1", "2", "3" }, result.get("0").toArray());
        assertArrayEquals(new String[] {"4", "5", "6" }, result.get("1").toArray());
        assertArrayEquals(new String[] {"7", "8", "9" }, result.get("2").toArray());
    }

    @Test
    public void putArrayValid() {
        Map<String, List<String>> body = new HashMap<>();
        body.put("0", Arrays.asList("1", "2", "3"));
        body.put("1", Arrays.asList("4", "5", "6"));
        body.put("2", Arrays.asList("7", "8", "9"));
        final Response<Void> putResponse = client.putArrayValidWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getDictionaryNull() {
        final Response<Map<String, Object>> getResponse = client.getDictionaryNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue());
    }

    @Test
    public void getDictionaryEmpty() {
        final Response<Map<String, Object>> getResponse = client.getDictionaryEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Object> result = getResponse.getValue();
        assertEquals(0, result.size());
    }

    @Test
    public void getDictionaryItemNull() {
        final Response<Map<String, Object>> getResponse = client.getDictionaryItemNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Object> result = getResponse.getValue();
        assertNull(result.get("1"));
    }

    @Test
    public void getDictionaryItemEmpty() {
        final Response<Map<String, Object>> getResponse = client.getDictionaryItemEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Object> result = getResponse.getValue();
        Map<String, String> value = (Map<String, String>) result.get("1");
        assertEquals(0, value.size());
    }

    @Test
    public void getDictionaryValid() {
        final Response<Map<String, Object>> getResponse = client.getDictionaryValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Map<String, Object> result = getResponse.getValue();
        Map<String, String> map1 = new HashMap<>();
        map1.put("1", "one");
        map1.put("2", "two");
        map1.put("3", "three");
        Map<String, String> map2 = new HashMap<>();
        map2.put("4", "four");
        map2.put("5", "five");
        map2.put("6", "six");
        Map<String, String> map3 = new HashMap<>();
        map3.put("7", "seven");
        map3.put("8", "eight");
        map3.put("9", "nine");
        Map<String, Map<String, String>> expected = new HashMap<>();
        expected.put("0", map1);
        expected.put("1", map2);
        expected.put("2", map3);
        assertEquals(expected, result);
    }

    @Test
    public void putDictionaryValid() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("1", "one");
        map1.put("2", "two");
        map1.put("3", "three");
        Map<String, String> map2 = new HashMap<>();
        map2.put("4", "four");
        map2.put("5", "five");
        map2.put("6", "six");
        Map<String, String> map3 = new HashMap<>();
        map3.put("7", "seven");
        map3.put("8", "eight");
        map3.put("9", "nine");
        Map<String, Object> body = new HashMap<>();
        body.put("0", map1);
        body.put("1", map2);
        body.put("2", map3);
        final Response<Void> putResponse = client.putDictionaryValidWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }
}
