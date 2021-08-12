package fixtures.bodyarray;

import com.azure.core.exception.HttpResponseException;
import com.fasterxml.jackson.core.JsonParseException;
import fixtures.bodyarray.models.Enum0;
import fixtures.bodyarray.models.Enum1;
import fixtures.bodyarray.models.ErrorException;
import fixtures.bodyarray.models.FooEnum;
import fixtures.bodyarray.models.Product;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ArrayTests {
    private static AutoRestSwaggerBATArrayService client;

    @BeforeClass
    public static void setup() throws Exception {
        client = new AutoRestSwaggerBATArrayServiceBuilder().buildClient();
    }

    @Test
    public void getNull() throws Exception {
        Assert.assertNull(client.getArrays().getNull());
    }

    @Test
    public void getInvalid() throws Exception {
        try {
            List<Integer> result = client.getArrays().getInvalid();
            Assert.assertTrue(false);
        } catch (HttpResponseException exception) {
            // expected
            Assert.assertTrue(exception.getCause() instanceof JsonParseException);
        }
    }

    @Test
    public void getEmpty() throws Exception {
        List<Integer> result = client.getArrays().getEmpty();
        assertEquals(0, result.size());
    }

    @Test
    public void putEmpty() throws Exception {
        client.getArrays().putEmpty(new ArrayList<String>());
    }

    @Test
    public void getBooleanTfft() throws Exception {
        List<Boolean> result = client.getArrays().getBooleanTfft();
        Object[] exected = new Boolean[] {true, false, false, true};
        Assert.assertArrayEquals(exected, result.toArray());
    }

    @Test
    public void putBooleanTfft() throws Exception {
        client.getArrays().putBooleanTfft(Arrays.asList(true, false, false, true));
    }

    @Test
    public void getBooleanInvalidNull() throws Exception {
        try {
            List<Boolean> result = client.getArrays().getBooleanInvalidNull();
        } catch (ErrorException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getBooleanInvalidString() throws Exception {
        try {
            List<Boolean> result = client.getArrays().getBooleanInvalidString();
        } catch (RuntimeException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("Deserialization Failed"));
        }
    }

    @Test
    public void getIntegerValid() throws Exception {
        List<Integer> result = client.getArrays().getIntegerValid();
        Object[] expected = new Integer[] {1, -1, 3, 300};
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putIntegerValid() throws Exception {
        client.getArrays().putIntegerValid(Arrays.asList(1, -1, 3, 300));
    }

    @Test
    public void getIntInvalidNull() throws Exception {
        try {
            List<Integer> result = client.getArrays().getIntInvalidNull();
        } catch (ErrorException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getIntInvalidString() throws Exception {
        try {
            List<Integer> result = client.getArrays().getIntInvalidString();
        } catch (RuntimeException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("Deserialization Failed"));
        }
    }

    @Test
    public void getLongValid() throws Exception {
        List<Long> result = client.getArrays().getLongValid();
        Object[] expected = new Long[] {1L, -1L, 3L, 300L};
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putLongValid() throws Exception {
        client.getArrays().putLongValid(Arrays.asList(1L, -1L, 3L, 300L));
    }

    @Test
    public void getLongInvalidNull() throws Exception {
        try {
            List<Long> result = client.getArrays().getLongInvalidNull();
        } catch (ErrorException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getLongInvalidString() throws Exception {
        try {
            List<Long> result = client.getArrays().getLongInvalidString();
        } catch (RuntimeException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("Deserialization Failed"));
        }
    }

    @Test
    public void getFloatValid() throws Exception {
        List<Float> result = client.getArrays().getFloatValid();
        Object[] expected = new Float[] {0f, -0.01f, -1.2e20f};
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putFloatValid() throws Exception {
        client.getArrays().putFloatValid(Arrays.asList(0f, -0.01f, -1.2e20f));
    }

    @Test
    public void getFloatInvalidNull() throws Exception {
        try {
            List<Float> result = client.getArrays().getFloatInvalidNull();
        } catch (ErrorException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getFloatInvalidString() throws Exception {
        try {
            List<Float> result = client.getArrays().getFloatInvalidString();
        } catch (RuntimeException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("Deserialization Failed"));
        }
    }

    @Test
    public void getDoubleValid() throws Exception {
        List<Double> result = client.getArrays().getDoubleValid();
        Object[] expected = new Double[] {0d, -0.01, -1.2e20};
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putDoubleValid() throws Exception {
        client.getArrays().putDoubleValid(Arrays.asList(0d, -0.01d, -1.2e20d));
    }

    @Test
    public void getDoubleInvalidNull() throws Exception {
        try {
            List<Double> result = client.getArrays().getDoubleInvalidNull();
        } catch (ErrorException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getDoubleInvalidString() throws Exception {
        try {
            List<Double> result = client.getArrays().getDoubleInvalidString();
        } catch (RuntimeException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("Deserialization Failed"));
        }
    }

    @Test
    public void getStringValid() throws Exception {
        List<String> result = client.getArrays().getStringValid();
        Object[] expected = new String[] {"foo1", "foo2", "foo3"};
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putStringValid() throws Exception {
        client.getArrays().putStringValid(Arrays.asList("foo1", "foo2", "foo3"));
    }

    @Test
    public void getEnumValid() throws Exception {
        List<FooEnum> result = client.getArrays().getEnumValid();
        Object[] expected = new FooEnum[] {FooEnum.FOO1, FooEnum.FOO2, FooEnum.FOO3};
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putEnumValid() throws Exception {
        client.getArrays().putEnumValid(Arrays.asList(FooEnum.FOO1, FooEnum.FOO2, FooEnum.FOO3));
    }

    @Test
    public void getStringEnumValid() throws Exception {
        List<Enum0> result = client.getArrays().getStringEnumValid();
        Object[] expected = new Enum0[] {Enum0.FOO1, Enum0.FOO2, Enum0.FOO3};
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putStringEnumValid() throws Exception {
        client.getArrays().putStringEnumValid(Arrays.asList(Enum1.FOO1, Enum1.FOO2, Enum1.FOO3));
    }

    @Test
    public void getStringWithNull() throws Exception {
        try {
            List<String> result = client.getArrays().getStringWithNull();
        } catch (ErrorException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getStringWithInvalid() throws Exception {
        try {
            List<String> result = client.getArrays().getStringWithInvalid();
        } catch (ErrorException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("InvalidFormatException"));
        }
    }

    @Test
    public void getUuidValid() throws Exception {
        List<UUID> result = client.getArrays().getUuidValid();
        Object[] expected = new UUID[] {UUID.fromString("6dcc7237-45fe-45c4-8a6b-3a8a3f625652"),
                UUID.fromString("d1399005-30f7-40d6-8da6-dd7c89ad34db"),
                UUID.fromString("f42f6aa1-a5bc-4ddf-907e-5f915de43205")};
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putUuidValid() throws Exception {
        client.getArrays().putUuidValid(Arrays.asList(UUID.fromString("6dcc7237-45fe-45c4-8a6b-3a8a3f625652"),
                UUID.fromString("d1399005-30f7-40d6-8da6-dd7c89ad34db"), UUID.fromString("f42f6aa1-a5bc-4ddf-907e-5f915de43205")));
    }

    @Test
    public void getUuidInvalidChars() throws Exception {
        try {
            List<UUID> result = client.getArrays().getUuidInvalidChars();
            Assert.fail();
        } catch (RuntimeException ex) {
            // expected
            Assert.assertTrue(ex.getMessage(), ex.getMessage().contains("Deserialization Failed"));
        }
    }
    @Test
    public void getDateValid() throws Exception {
        List<LocalDate> result = client.getArrays().getDateValid();
        Object[] expected = new LocalDate[] {
                LocalDate.of(2000, 12, 1),
                LocalDate.of(1980, 1, 2),
                LocalDate.of(1492, 10, 12)
        };
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putDateValid() throws Exception {
        client.getArrays().putDateValid(Arrays.asList(
                LocalDate.of(2000, 12, 1),
                LocalDate.of(1980, 1, 2),
                LocalDate.of(1492, 10, 12)
        ));
    }

    @Test
    public void getDateInvalidNull() throws Exception {
        try {
            List<LocalDate> result = client.getArrays().getDateInvalidNull();
        } catch (ErrorException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getDateInvalidString() throws Exception {
        try {
            List<LocalDate> result = client.getArrays().getDateInvalidChars();
        } catch (RuntimeException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("Deserialization Failed"));
        }
    }

    @Test
    public void getDateTimeValid() throws Exception {
        List<OffsetDateTime> result = client.getArrays().getDateTimeValid();
        OffsetDateTime[] expected = new OffsetDateTime[] {
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)
        };

        assertEquals(result.size(), expected.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(result.get(i).toEpochSecond(), expected[i].toEpochSecond());
        }
    }

    @Test
    public void putDateTimeValid() throws Exception {
        client.getArrays().putDateTimeValid(Arrays.asList(
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)
        ));
    }

    @Test
    public void getDateTimeInvalidNull() throws Exception {
        try {
            List<OffsetDateTime> result = client.getArrays().getDateTimeInvalidNull();
        } catch (ErrorException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getDateTimeInvalidString() throws Exception {
        try {
            List<OffsetDateTime> result = client.getArrays().getDateTimeInvalidChars();
        } catch (RuntimeException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("Deserialization Failed"));
        }
    }

    @Test
    public void getDateTimeRfc1123Valid() throws Exception {
        List<OffsetDateTime> result = client.getArrays().getDateTimeRfc1123Valid();
        Object[] expected = new OffsetDateTime[] {
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)
        };
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putDateTimeRfc1123Valid() throws Exception {
        client.getArrays().putDateTimeRfc1123Valid(Arrays.asList(
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)
        ));
    }

    @Test
    public void getDurationValid() throws Exception {
        List<Duration> result = client.getArrays().getDurationValid();
        Duration[] expected = new Duration[] {
                Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11),
                Duration.ofDays(5).plusHours(1)
        };
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putDurationValid() throws Exception {
        client.getArrays().putDurationValid(Arrays.asList(
                Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11),
                Duration.ofDays(5).plusHours(1)));
    }

    @Test
    public void getByteValid() throws Exception {
        List<byte[]> result = client.getArrays().getByteValid();
        Object[] expected = new byte[][] {
                new byte[] {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFA},
                new byte[] {(byte) 0x01, (byte) 0x02, (byte) 0x03},
                new byte[] {(byte) 0x25, (byte) 0x29, (byte) 0x43}
        };
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putByteValid() throws Exception {
        client.getArrays().putByteValid(Arrays.asList(
                new byte[] {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFA},
                new byte[] {(byte) 0x01, (byte) 0x02, (byte) 0x03},
                new byte[] {(byte) 0x25, (byte) 0x29, (byte) 0x43}
        ));
    }

    @Test
    public void getByteInvalidNull() throws Exception {
        try {
            List<byte[]> result = client.getArrays().getByteInvalidNull();
        } catch (ErrorException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getBase64Url() throws Exception {
        List<byte[]> result = client.getArrays().getBase64Url();
        assertEquals(3, result.size());
        assertEquals("a string that gets encoded with base64url", new String(result.get(0)));
        assertEquals("test string", new String(result.get(1)));
        assertEquals("Lorem ipsum", new String(result.get(2)));
    }

    @Test
    public void getComplexNull() throws Exception {
        try {
            List<Product> result = client.getArrays().getComplexNull();
        } catch (ErrorException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getComplexEmpty() throws Exception {
        List<Product> result = client.getArrays().getComplexEmpty();
        assertEquals(0, result.size());
    }

    @Test
    public void getComplexItemNull() throws Exception {
        List<Product> result = client.getArrays().getComplexItemNull();
        assertEquals(3, result.size());
        Assert.assertNull(result.get(1));
    }

    @Test
    public void getComplexItemEmpty() throws Exception {
        List<Product> result = client.getArrays().getComplexItemEmpty();
        assertEquals(3, result.size());
        Assert.assertNull(result.get(1).getString());
    }

    @Test
    public void getComplexValid() throws Exception {
        List<Product> result = client.getArrays().getComplexValid();
        assertEquals(3, result.size());
        assertEquals(5, result.get(2).getInteger().intValue());
        assertEquals("6", result.get(2).getString());
    }

    @Test
    public void putComplexValid() throws Exception {
        List<Product> body = new ArrayList<Product>();
        Product p1 = new Product();
        p1.setInteger(1);
        p1.setString("2");
        body.add(p1);
        Product p2 = new Product();
        p2.setInteger(3);
        p2.setString("4");
        body.add(p2);
        Product p3 = new Product();
        p3.setInteger(5);
        p3.setString("6");
        body.add(p3);
        client.getArrays().putComplexValid(body);
    }

    @Test
    public void getArrayNull() throws Exception {
        try {
            List<List<String>> result = client.getArrays().getArrayNull();
        } catch (ErrorException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getArrayEmpty() throws Exception {
        List<List<String>> result = client.getArrays().getArrayEmpty();
        assertEquals(0, result.size());
    }

    @Test
    public void getArrayItemNull() throws Exception {
        List<List<String>> result = client.getArrays().getArrayItemNull();
        assertEquals(3, result.size());
        Assert.assertNull(result.get(1));
    }

    @Test
    public void getArrayItemEmpty() throws Exception {
        List<List<String>> result = client.getArrays().getArrayItemEmpty();
        assertEquals(3, result.size());
        assertEquals(0, result.get(1).size());
    }

    @Test
    public void getArrayValid() throws Exception {
        List<List<String>> result = client.getArrays().getArrayValid();
        Assert.assertArrayEquals(new String[]{"1", "2", "3"}, result.get(0).toArray());
        Assert.assertArrayEquals(new String[]{"4", "5", "6"}, result.get(1).toArray());
        Assert.assertArrayEquals(new String[] {"7", "8", "9"}, result.get(2).toArray());
    }

    @Test
    public void putArrayValid() throws Exception {
        List<List<String>> body = new ArrayList<List<String>>();
        body.add(Arrays.asList("1", "2", "3"));
        body.add(Arrays.asList("4", "5", "6"));
        body.add(Arrays.asList("7", "8", "9"));
        client.getArrays().putArrayValid(body);
    }

    @Test
    public void getDictionaryNull() throws Exception {
        try {
            List<Map<String, String>> result = client.getArrays().getDictionaryNull();
        } catch (ErrorException ex) {
            // expected
            Assert.assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getDictionaryEmpty() throws Exception {
        List<Map<String, String>> result = client.getArrays().getDictionaryEmpty();
        assertEquals(0, result.size());
    }

    @Test
    public void getDictionaryItemNull() throws Exception {
        List<Map<String, String>> result = client.getArrays().getDictionaryItemNull();
        assertEquals(3, result.size());
        Assert.assertNull(result.get(1));
    }

    @Test
    public void getDictionaryItemEmpty() throws Exception {
        List<Map<String, String>> result = client.getArrays().getDictionaryItemEmpty();
        assertEquals(3, result.size());
        assertEquals(0, result.get(1).size());
    }

    @Test
    public void getDictionaryValid() throws Exception {
        List<Map<String, String>> result = client.getArrays().getDictionaryValid();
        assertEquals("seven", result.get(2).get("7"));
        assertEquals("five", result.get(1).get("5"));
        assertEquals("three", result.get(0).get("3"));
    }

    @Test
    public void putDictionaryValid() throws Exception {
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
        client.getArrays().putDictionaryValid(body);
    }
}
