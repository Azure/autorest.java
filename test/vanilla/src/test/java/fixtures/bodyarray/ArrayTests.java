package fixtures.bodyarray;

import com.fasterxml.jackson.core.io.JsonEOFException;
import fixtures.bodyarray.implementation.AutoRestSwaggerBATArrayServiceImpl;
import fixtures.bodyarray.models.Product;
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

import static org.junit.Assert.*;

public class ArrayTests {
    private static AutoRestSwaggerBATArrayService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATArrayServiceImpl();
    }

    @Test
    public void getNull() {
        assertNull(client.arrays().getNull());
    }

    @Test
    public void getInvalid() {
        try {
            client.arrays().getInvalid();
            fail();
        } catch (RuntimeException exception) {
            // expected
            assertEquals(JsonEOFException.class, exception.getCause().getClass());
            assertTrue(exception.getMessage().contains("HTTP response has a malformed body"));
        }
    }

    @Test
    public void getEmpty() {
        List<Integer> result = client.arrays().getEmpty();
        assertEquals(0, result.size());
    }

    @Test
    public void putEmpty() {
        client.arrays().putEmpty(new ArrayList<>());
    }

    @Test
    public void getBooleanTfft() {
        List<Boolean> result = client.arrays().getBooleanTfft();
        Boolean[] expected = new Boolean[] {true, false, false, true};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putBooleanTfft() {
        client.arrays().putBooleanTfft(Arrays.asList(true, false, false, true));
    }

    @Test
    public void getBooleanInvalidNull() {
        final List<Boolean> result = client.arrays().getBooleanInvalidNull();
        final Boolean[] expected = new Boolean[] { true, null, false };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void getBooleanInvalidString() {
        try {
            client.arrays().getBooleanInvalidString();
            fail();
        } catch (RuntimeException ex) {
            // expected
            assertTrue(ex.getMessage().contains("only \"true\" or \"false\" recognized"));
        }
    }

    @Test
    public void getIntegerValid() {
        List<Integer> result = client.arrays().getIntegerValid();
        Integer[] expected = new Integer[] {1, -1, 3, 300};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putIntegerValid() {
        client.arrays().putIntegerValid(Arrays.asList(1, -1, 3, 300));
    }

    @Test
    public void getIntInvalidNull() {
        final List<Integer> result = client.arrays().getIntInvalidNull();
        final Integer[] expected = new Integer[] { 1, null, 0 };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void getIntInvalidString() {
        try {
            client.arrays().getIntInvalidString();
            fail();
        } catch (RuntimeException ex) {
            // expected
            assertTrue(ex.getMessage().contains("not a valid Integer value"));
        }
    }

    @Test
    public void getLongValid() {
        List<Long> result = client.arrays().getLongValid();
        Object[] expected = new Long[] {1L, -1L, 3L, 300L};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putLongValid() {
        client.arrays().putLongValid(Arrays.asList(1L, -1L, 3L, 300L));
    }

    @Test
    public void getLongInvalidNull() {
        final List<Long> result = client.arrays().getLongInvalidNull();
        final Long[] expected = new Long[] { 1L, null, 0L };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void getLongInvalidString() {
        try {
            client.arrays().getLongInvalidString();
            fail();
        } catch (RuntimeException ex) {
            // expected
            assertTrue(ex.getMessage().contains("not a valid Long value"));
        }
    }

    @Test
    public void getFloatValid() {
        List<Double> result = client.arrays().getFloatValid();
        Object[] expected = new Double[] {0d, -0.01, -1.2e20};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putFloatValid() {
        client.arrays().putFloatValid(Arrays.asList(0d, -0.01d, -1.2e20d));
    }

    @Test
    public void getFloatInvalidNull() {
        final List<Double> result = client.arrays().getFloatInvalidNull();
        final Double[] expected = new Double[] { 0.0, null, -1.2E20 };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void getFloatInvalidString() {
        try {
            client.arrays().getFloatInvalidString();
            fail();
        } catch (RuntimeException ex) {
            // expected
            assertTrue(ex.getMessage().contains("not a valid Double value"));
        }
    }

    @Test
    public void getDoubleValid() {
        List<Double> result = client.arrays().getDoubleValid();
        Object[] expected = new Double[] {0d, -0.01, -1.2e20};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putDoubleValid() {
        client.arrays().putDoubleValid(Arrays.asList(0d, -0.01d, -1.2e20d));
    }

    @Test
    public void getDoubleInvalidNull() {
        final List<Double> result = client.arrays().getDoubleInvalidNull();
        final Double[] expected = new Double[] { 0.0, null, -1.2E20 };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void getDoubleInvalidString() {
        try {
            client.arrays().getDoubleInvalidString();
            fail();
        } catch (RuntimeException ex) {
            // expected
            assertTrue(ex.getMessage().contains("not a valid Double value"));
        }
    }

    @Test
    public void getStringValid() {
        List<String> result = client.arrays().getStringValid();
        String[] expected = new String[] {"foo1", "foo2", "foo3"};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putStringValid() {
        client.arrays().putStringValid(Arrays.asList("foo1", "foo2", "foo3"));
    }

    @Test
    public void getStringWithNull() {
        final List<String> result = client.arrays().getStringWithNull();
        final String[] expected = new String[] { "foo", null, "foo2" };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void getStringWithInvalid() {
        final List<String> result = client.arrays().getStringWithInvalid();
        final String[] expected = new String[] { "foo", "123", "foo2" };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void getUuidValid() {
        List<UUID> result = client.arrays().getUuidValid();
        UUID[] expected = new UUID[] {UUID.fromString("6dcc7237-45fe-45c4-8a6b-3a8a3f625652"),
                                      UUID.fromString("d1399005-30f7-40d6-8da6-dd7c89ad34db"),
                                      UUID.fromString("f42f6aa1-a5bc-4ddf-907e-5f915de43205")};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putUuidValid() {
        client.arrays().putUuidValid(Arrays.asList(
                UUID.fromString("6dcc7237-45fe-45c4-8a6b-3a8a3f625652"),
                UUID.fromString("d1399005-30f7-40d6-8da6-dd7c89ad34db"),
                UUID.fromString("f42f6aa1-a5bc-4ddf-907e-5f915de43205")));
    }

    @Test
    public void getUuidInvalidChars() {
        try {
            client.arrays().getUuidInvalidChars();
            fail();
        } catch (RuntimeException ex) {
            // expected
            assertTrue(ex.getMessage(), ex.getMessage().contains("UUID has to be represented"));
        }
    }
    @Test
    public void getDateValid() {
        List<LocalDate> result = client.arrays().getDateValid();
        Object[] expected = new LocalDate[] {
                LocalDate.of(2000, 12, 1),
                LocalDate.of(1980, 1, 2),
                LocalDate.of(1492, 10, 12)
        };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putDateValid() {
        client.arrays().putDateValid(Arrays.asList(
                LocalDate.of(2000, 12, 1),
                LocalDate.of(1980, 1, 2),
                LocalDate.of(1492, 10, 12)
        ));
    }

    @Test
    public void getDateInvalidNull() {
        final List<LocalDate> result = client.arrays().getDateInvalidNull();
        final LocalDate[] expected = new LocalDate[] {
                LocalDate.of(2012, 1, 1),
                null,
                LocalDate.of(1776, 7, 4)
        };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void getDateInvalidString() {
        try {
            client.arrays().getDateInvalidChars();
            fail();
        } catch (RuntimeException ex) {
            // expected
            assertTrue(ex.getMessage().contains("Can not deserialize value"));
        }
    }

    @Test
    public void getDateTimeValid() {
        List<OffsetDateTime> result = client.arrays().getDateTimeValid();
        OffsetDateTime[] expected = new OffsetDateTime[] {
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)
        };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putDateTimeValid() {
        client.arrays().putDateTimeValid(Arrays.asList(
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)
        ));
    }

    @Test
    public void getDateTimeInvalidNull() {
        final List<OffsetDateTime> result = client.arrays().getDateTimeInvalidNull();
        final OffsetDateTime[] expected = new OffsetDateTime[] {
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                null
        };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void getDateTimeInvalidString() {
        try {
            client.arrays().getDateTimeInvalidChars();
            fail();
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Can not deserialize value"));
        }
    }

    @Test
    public void getDateTimeRfc1123Valid() {
        List<OffsetDateTime> result = client.arrays().getDateTimeRfc1123Valid();
        OffsetDateTime[] expected = new OffsetDateTime[] {
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)
        };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putDateTimeRfc1123Valid() {
        client.arrays().putDateTimeRfc1123Valid(Arrays.asList(
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)
        ));
    }

    @Test
    public void getDurationValid() {
        List<Duration> result = client.arrays().getDurationValid();
        Duration[] expected = new Duration[] {
                Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11),
                Duration.ofDays(5).plusHours(1)
        };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putDurationValid() {
        client.arrays().putDurationValid(Arrays.asList(
                Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11),
                Duration.ofDays(5).plusHours(1)));
    }

    @Test
    public void getByteValid() {
        List<byte[]> result = client.arrays().getByteValid();
        Object[] expected = new byte[][] {
                new byte[] {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFA},
                new byte[] {(byte) 0x01, (byte) 0x02, (byte) 0x03},
                new byte[] {(byte) 0x25, (byte) 0x29, (byte) 0x43}
        };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putByteValid() {
        client.arrays().putByteValid(Arrays.asList(
                new byte[] {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFA},
                new byte[] {(byte) 0x01, (byte) 0x02, (byte) 0x03},
                new byte[] {(byte) 0x25, (byte) 0x29, (byte) 0x43}
        ));
    }

    @Test
    public void getByteInvalidNull() {
        final List<byte[]> result = client.arrays().getByteInvalidNull();
        final byte[][] expected = new byte[][] {
                new byte[] { -85, -84, -83 },
                null
        };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void getBase64Url() {
        List<byte[]> result = client.arrays().getBase64Url();
        assertEquals(3, result.size());
        assertEquals("a string that gets encoded with base64url", new String(result.get(0)));
        assertEquals("test string", new String(result.get(1)));
        assertEquals("Lorem ipsum", new String(result.get(2)));
    }

    @Test
    public void getComplexNull() {
        final List<Product> result = client.arrays().getComplexNull();
        assertNull(result);
    }

    @Test
    public void getComplexEmpty() {
        List<Product> result = client.arrays().getComplexEmpty();
        assertEquals(0, result.size());
    }

    @Test
    public void getComplexItemNull() {
        List<Product> result = client.arrays().getComplexItemNull();
        assertEquals(3, result.size());
        assertNull(result.get(1));
    }

    @Test
    public void getComplexItemEmpty() {
        List<Product> result = client.arrays().getComplexItemEmpty();
        assertEquals(3, result.size());
        assertNull(result.get(1).stringProperty());
    }

    @Test
    public void getComplexValid() {
        List<Product> result = client.arrays().getComplexValid();
        assertEquals(3, result.size());
        assertEquals(5, result.get(2).integer().intValue());
        assertEquals("6", result.get(2).stringProperty());
    }

    @Test
    public void putComplexValid() {
        List<Product> body = new ArrayList<>();
        Product p1 = new Product();
        p1.withInteger(1);
        p1.withStringProperty("2");
        body.add(p1);
        Product p2 = new Product();
        p2.withInteger(3);
        p2.withStringProperty("4");
        body.add(p2);
        Product p3 = new Product();
        p3.withInteger(5);
        p3.withStringProperty("6");
        body.add(p3);
        client.arrays().putComplexValid(body);
    }

    @Test
    public void getArrayNull() {
        List<List<String>> result = client.arrays().getArrayNull();
        assertNull(result);
    }

    @Test
    public void getArrayEmpty() {
        List<List<String>> result = client.arrays().getArrayEmpty();
        assertEquals(0, result.size());
    }

    @Test
    public void getArrayItemNull() {
        List<List<String>> result = client.arrays().getArrayItemNull();
        assertEquals(3, result.size());
        assertNull(result.get(1));
    }

    @Test
    public void getArrayItemEmpty() {
        List<List<String>> result = client.arrays().getArrayItemEmpty();
        assertEquals(3, result.size());
        assertEquals(0, result.get(1).size());
    }

    @Test
    public void getArrayValid() {
        List<List<String>> result = client.arrays().getArrayValid();
        assertArrayEquals(new String[]{"1", "2", "3"}, result.get(0).toArray());
        assertArrayEquals(new String[]{"4", "5", "6"}, result.get(1).toArray());
        assertArrayEquals(new String[] {"7", "8", "9"}, result.get(2).toArray());
    }

    @Test
    public void putArrayValid() {
        List<List<String>> body = new ArrayList<>();
        body.add(Arrays.asList("1", "2", "3"));
        body.add(Arrays.asList("4", "5", "6"));
        body.add(Arrays.asList("7", "8", "9"));
        client.arrays().putArrayValid(body);
    }

    @Test
    public void getDictionaryNull() {
        final List<Map<String,String>> result = client.arrays().getDictionaryNull();
        assertNull(result);
    }

    @Test
    public void getDictionaryEmpty() {
        List<Map<String, String>> result = client.arrays().getDictionaryEmpty();
        assertEquals(0, result.size());
    }

    @Test
    public void getDictionaryItemNull() {
        List<Map<String, String>> result = client.arrays().getDictionaryItemNull();
        assertEquals(3, result.size());
        assertNull(result.get(1));
    }

    @Test
    public void getDictionaryItemEmpty() {
        List<Map<String, String>> result = client.arrays().getDictionaryItemEmpty();
        assertEquals(3, result.size());
        assertEquals(0, result.get(1).size());
    }

    @Test
    public void getDictionaryValid() {
        List<Map<String, String>> result = client.arrays().getDictionaryValid();
        assertEquals("seven", result.get(2).get("7"));
        assertEquals("five", result.get(1).get("5"));
        assertEquals("three", result.get(0).get("3"));
    }

    @Test
    public void putDictionaryValid() {
        List<Map<String, String>> body = new ArrayList<>();
        Map<String, String> m1 = new HashMap<>();
        m1.put("1", "one");
        m1.put("2", "two");
        m1.put("3", "three");
        body.add(m1);
        Map<String, String> m2 = new HashMap<>();
        m2.put("4", "four");
        m2.put("5", "five");
        m2.put("6", "six");
        body.add(m2);
        Map<String, String> m3 = new HashMap<>();
        m3.put("7", "seven");
        m3.put("8", "eight");
        m3.put("9", "nine");
        body.add(m3);
        client.arrays().putDictionaryValid(body);
    }
}
