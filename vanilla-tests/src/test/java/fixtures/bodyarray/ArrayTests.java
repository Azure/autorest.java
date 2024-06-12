package fixtures.bodyarray;

import com.azure.core.exception.HttpResponseException;
import com.fasterxml.jackson.core.JsonParseException;
import fixtures.bodyarray.models.Enum0;
import fixtures.bodyarray.models.Enum1;
import fixtures.bodyarray.models.ErrorException;
import fixtures.bodyarray.models.FooEnum;
import fixtures.bodyarray.models.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayTests {
    private static AutoRestSwaggerBATArrayService client;

    @BeforeAll
    public static void setup() throws Exception {
        client = new AutoRestSwaggerBATArrayServiceBuilder().buildClient();
    }

    @Test
    public void getNull() throws Exception {
        assertNull(client.getArrays().getNull());
    }

    @Test
    public void getInvalid() {
        HttpResponseException exception = assertThrows(HttpResponseException.class,
            () -> client.getArrays().getInvalid());
        assertInstanceOf(JsonParseException.class, exception.getCause());
    }

    @Test
    public void getEmpty() throws Exception {
        List<Integer> result = client.getArrays().getEmpty();
        assertEquals(0, result.size());
    }

    @Test
    public void putEmpty() {
        client.getArrays().putEmpty(new ArrayList<>());
    }

    @Test
    public void getBooleanTfft() {
        List<Boolean> result = client.getArrays().getBooleanTfft();
        Object[] exected = new Boolean[] {true, false, false, true};
        assertArrayEquals(exected, result.toArray());
    }

    @Test
    public void putBooleanTfft() {
        client.getArrays().putBooleanTfft(Arrays.asList(true, false, false, true));
    }

    @Disabled("Java generics always support null but the Swagger spec is configured with x-nullable: false")
    @Test
    public void getBooleanInvalidNull() {
        ErrorException ex = assertThrows(ErrorException.class, () -> client.getArrays().getBooleanInvalidNull());
        assertTrue(ex.getMessage().contains("JsonMappingException"));
    }

    @Test
    public void getBooleanInvalidString() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> client.getArrays().getBooleanInvalidString());
        assertTrue(ex.getMessage().contains("Deserialization Failed"));
    }

    @Test
    public void getIntegerValid() {
        List<Integer> result = client.getArrays().getIntegerValid();
        Object[] expected = new Integer[] {1, -1, 3, 300};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putIntegerValid() {
        client.getArrays().putIntegerValid(Arrays.asList(1, -1, 3, 300));
    }

    @Disabled("Java generics always support null but the Swagger spec is configured with x-nullable: false")
    @Test
    public void getIntInvalidNull() {
        ErrorException ex = assertThrows(ErrorException.class, () -> client.getArrays().getIntInvalidNull());
        assertTrue(ex.getMessage().contains("JsonMappingException"));
    }

    @Test
    public void getIntInvalidString() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> client.getArrays().getIntInvalidString());
        assertTrue(ex.getMessage().contains("Deserialization Failed"));
    }

    @Test
    public void getLongValid() {
        List<Long> result = client.getArrays().getLongValid();
        Object[] expected = new Long[] {1L, -1L, 3L, 300L};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putLongValid() {
        client.getArrays().putLongValid(Arrays.asList(1L, -1L, 3L, 300L));
    }

    @Disabled("Java generics always support null but the Swagger spec is configured with x-nullable: false")
    @Test
    public void getLongInvalidNull() {
        ErrorException ex = assertThrows(ErrorException.class, () -> client.getArrays().getLongInvalidNull());
        assertTrue(ex.getMessage().contains("JsonMappingException"));
    }

    @Test
    public void getLongInvalidString() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> client.getArrays().getLongInvalidString());
        assertTrue(ex.getMessage().contains("Deserialization Failed"));
    }

    @Test
    public void getFloatValid() {
        List<Float> result = client.getArrays().getFloatValid();
        Object[] expected = new Float[] {0f, -0.01f, -1.2e20f};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putFloatValid() {
        client.getArrays().putFloatValid(Arrays.asList(0f, -0.01f, -1.2e20f));
    }

    @Disabled("Java generics always support null but the Swagger spec is configured with x-nullable: false")
    @Test
    public void getFloatInvalidNull() {
        ErrorException ex = assertThrows(ErrorException.class, () -> client.getArrays().getFloatInvalidNull());
        assertTrue(ex.getMessage().contains("JsonMappingException"));
    }

    @Test
    public void getFloatInvalidString() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> client.getArrays().getFloatInvalidString());
        assertTrue(ex.getMessage().contains("Deserialization Failed"));
    }

    @Test
    public void getDoubleValid() {
        List<Double> result = client.getArrays().getDoubleValid();
        Object[] expected = new Double[] {0d, -0.01, -1.2e20};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putDoubleValid() {
        client.getArrays().putDoubleValid(Arrays.asList(0d, -0.01d, -1.2e20d));
    }

    @Disabled("Java generics always support null but the Swagger spec is configured with x-nullable: false")
    @Test
    public void getDoubleInvalidNull() {
        ErrorException ex = assertThrows(ErrorException.class, () -> client.getArrays().getDoubleInvalidNull());
        assertTrue(ex.getMessage().contains("JsonMappingException"));
    }

    @Test
    public void getDoubleInvalidString() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> client.getArrays().getDoubleInvalidString());
        assertTrue(ex.getMessage().contains("Deserialization Failed"));
    }

    @Test
    public void getStringValid() {
        List<String> result = client.getArrays().getStringValid();
        Object[] expected = new String[] {"foo1", "foo2", "foo3"};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putStringValid() {
        client.getArrays().putStringValid(Arrays.asList("foo1", "foo2", "foo3"));
    }

    @Disabled
    @Test
    public void getEnumValid() {
        // TODO (alzimmer): enum serialization uses the enum name, not the value.
        //  This is an issue when using azure-json code generation but serialization has to fallback to Jackson to
        //  handle requests or responses that aren't JsonSerializable. Jackson by default uses the enum name. So, here
        //  we get 'FOO1', 'FOO2', 'FOO3' instead of 'foo1', 'foo2', 'foo3'.
        List<FooEnum> result = client.getArrays().getEnumValid();
        Object[] expected = new FooEnum[] {FooEnum.FOO1, FooEnum.FOO2, FooEnum.FOO3};
        assertArrayEquals(expected, result.toArray());
    }

    @Disabled
    @Test
    public void putEnumValid() {
        // TODO (alzimmer): enum serialization uses the enum name, not the value.
        //  This is an issue when using azure-json code generation but serialization has to fallback to Jackson to
        //  handle requests or responses that aren't JsonSerializable. Jackson by default uses the enum name. So, here
        //  we get 'FOO1', 'FOO2', 'FOO3' instead of 'foo1', 'foo2', 'foo3'.
        client.getArrays().putEnumValid(Arrays.asList(FooEnum.FOO1, FooEnum.FOO2, FooEnum.FOO3));
    }

    @Test
    public void getStringEnumValid() {
        List<Enum0> result = client.getArrays().getStringEnumValid();
        Object[] expected = new Enum0[] {Enum0.FOO1, Enum0.FOO2, Enum0.FOO3};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putStringEnumValid() {
        client.getArrays().putStringEnumValid(Arrays.asList(Enum1.FOO1, Enum1.FOO2, Enum1.FOO3));
    }

    @Disabled("Java generics always support null but the Swagger spec is configured with x-nullable: false")
    @Test
    public void getStringWithNull() {
        ErrorException ex = assertThrows(ErrorException.class, () -> client.getArrays().getStringWithNull());
        assertTrue(ex.getMessage().contains("JsonMappingException"));
    }

    @Disabled("The non-string value is handled as a raw JSON string instead of an error.")
    @Test
    public void getStringWithInvalid() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> client.getArrays().getStringWithInvalid());
        assertTrue(ex.getMessage().contains("InvalidFormatException"));
    }

    @Test
    public void getUuidValid() {
        List<UUID> result = client.getArrays().getUuidValid();
        Object[] expected = new UUID[] {UUID.fromString("6dcc7237-45fe-45c4-8a6b-3a8a3f625652"),
                UUID.fromString("d1399005-30f7-40d6-8da6-dd7c89ad34db"),
                UUID.fromString("f42f6aa1-a5bc-4ddf-907e-5f915de43205")};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putUuidValid() {
        client.getArrays().putUuidValid(Arrays.asList(UUID.fromString("6dcc7237-45fe-45c4-8a6b-3a8a3f625652"),
                UUID.fromString("d1399005-30f7-40d6-8da6-dd7c89ad34db"), UUID.fromString("f42f6aa1-a5bc-4ddf-907e-5f915de43205")));
    }

    @Test
    public void getUuidInvalidChars() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> client.getArrays().getUuidInvalidChars());
        assertTrue(ex.getMessage().contains("Deserialization Failed"));
    }
    @Test
    public void getDateValid() {
        List<LocalDate> result = client.getArrays().getDateValid();
        Object[] expected = new LocalDate[] {
                LocalDate.of(2000, 12, 1),
                LocalDate.of(1980, 1, 2),
                LocalDate.of(1492, 10, 12)
        };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putDateValid() {
        client.getArrays().putDateValid(Arrays.asList(
                LocalDate.of(2000, 12, 1),
                LocalDate.of(1980, 1, 2),
                LocalDate.of(1492, 10, 12)
        ));
    }

    @Disabled("Java generics always support null but the Swagger spec is configured with x-nullable: false")
    @Test
    public void getDateInvalidNull() {
        ErrorException ex = assertThrows(ErrorException.class, () -> client.getArrays().getDateInvalidNull());
        assertTrue(ex.getMessage().contains("JsonMappingException"));
    }

    @Test
    public void getDateInvalidString() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> client.getArrays().getDateInvalidChars());
        assertTrue(ex.getMessage().contains("Deserialization Failed"));
    }

    @Test
    public void getDateTimeValid() {
        List<OffsetDateTime> result = client.getArrays().getDateTimeValid();
        OffsetDateTime[] expected = new OffsetDateTime[] {
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.ofHours(1)),
                OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.ofHours(-8))
        };

        assertEquals(result.size(), expected.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(result.get(i).toEpochSecond(), expected[i].toEpochSecond());
        }
    }

    @Test
    public void putDateTimeValid() {
        client.getArrays().putDateTimeValid(Arrays.asList(
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)
        ));
    }

    @Disabled("Java generics always support null but the Swagger spec is configured with x-nullable: false")
    @Test
    public void getDateTimeInvalidNull() {
        ErrorException ex = assertThrows(ErrorException.class, () -> client.getArrays().getDateTimeInvalidNull());
        assertTrue(ex.getMessage().contains("JsonMappingException"));
    }

    @Test
    public void getDateTimeInvalidString() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> client.getArrays().getDateTimeInvalidChars());
        assertTrue(ex.getMessage().contains("Deserialization Failed"));
    }

    @Test
    public void getDateTimeRfc1123Valid() {
        List<OffsetDateTime> result = client.getArrays().getDateTimeRfc1123Valid();
        Object[] expected = new OffsetDateTime[] {
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)
        };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putDateTimeRfc1123Valid() {
        client.getArrays().putDateTimeRfc1123Valid(Arrays.asList(
                OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC),
                OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC)
        ));
    }

    @Test
    public void getDurationValid() {
        List<Duration> result = client.getArrays().getDurationValid();
        Duration[] expected = new Duration[] {
                Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11),
                Duration.ofDays(5).plusHours(1)
        };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putDurationValid() {
        client.getArrays().putDurationValid(Arrays.asList(
                Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11),
                Duration.ofDays(5).plusHours(1)));
    }

    @Test
    public void getByteValid() {
        List<byte[]> result = client.getArrays().getByteValid();
        Object[] expected = new byte[][] {
                new byte[] {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFA},
                new byte[] {(byte) 0x01, (byte) 0x02, (byte) 0x03},
                new byte[] {(byte) 0x25, (byte) 0x29, (byte) 0x43}
        };
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void putByteValid() {
        client.getArrays().putByteValid(Arrays.asList(
                new byte[] {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFA},
                new byte[] {(byte) 0x01, (byte) 0x02, (byte) 0x03},
                new byte[] {(byte) 0x25, (byte) 0x29, (byte) 0x43}
        ));
    }

    @Test
    public void getByteInvalidNull() {
        List<byte[]> result = client.getArrays().getByteInvalidNull();

        assertNotNull(result.get(0));
        byte[] expected = new byte[]{(byte) 171, (byte) 172, (byte) 173};
        assertArrayEquals(expected, result.get(0));

        assertNull(result.get(1));
    }

    @Test
    public void getBase64Url() {
        List<byte[]> result = client.getArrays().getBase64Url();
        assertEquals(3, result.size());
        assertEquals("a string that gets encoded with base64url", new String(result.get(0)));
        assertEquals("test string", new String(result.get(1)));
        assertEquals("Lorem ipsum", new String(result.get(2)));
    }

    @Disabled("Java generics always support null but the Swagger spec is configured with x-nullable: false")
    @Test
    public void getComplexNull() {
        ErrorException ex = assertThrows(ErrorException.class, () -> client.getArrays().getComplexNull());
        assertTrue(ex.getMessage().contains("JsonMappingException"));
    }

    @Test
    public void getComplexEmpty() {
        List<Product> result = client.getArrays().getComplexEmpty();
        assertEquals(0, result.size());
    }

    @Test
    public void getComplexItemNull() {
        List<Product> result = client.getArrays().getComplexItemNull();
        assertEquals(3, result.size());
        assertNull(result.get(1));
    }

    @Test
    public void getComplexItemEmpty() {
        List<Product> result = client.getArrays().getComplexItemEmpty();
        assertEquals(3, result.size());
        assertNull(result.get(1).getString());
    }

    @Test
    public void getComplexValid() {
        List<Product> result = client.getArrays().getComplexValid();
        assertEquals(3, result.size());
        assertEquals(5, result.get(2).getInteger().intValue());
        assertEquals("6", result.get(2).getString());
    }

    @Test
    public void putComplexValid() {
        List<Product> body = new ArrayList<>();
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

    @Disabled("Java generics always support null but the Swagger spec is configured with x-nullable: false")
    @Test
    public void getArrayNull() {
        ErrorException ex = assertThrows(ErrorException.class, () -> client.getArrays().getArrayNull());
        assertTrue(ex.getMessage().contains("JsonMappingException"));
    }

    @Test
    public void getArrayEmpty() {
        List<List<String>> result = client.getArrays().getArrayEmpty();
        assertEquals(0, result.size());
    }

    @Test
    public void getArrayItemNull() {
        List<List<String>> result = client.getArrays().getArrayItemNull();
        assertEquals(3, result.size());
        assertNull(result.get(1));
    }

    @Test
    public void getArrayItemEmpty() {
        List<List<String>> result = client.getArrays().getArrayItemEmpty();
        assertEquals(3, result.size());
        assertEquals(0, result.get(1).size());
    }

    @Test
    public void getArrayValid() {
        List<List<String>> result = client.getArrays().getArrayValid();
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
        client.getArrays().putArrayValid(body);
    }

    @Disabled("Java generics always support null but the Swagger spec is configured with x-nullable: false")
    @Test
    public void getDictionaryNull() {
        ErrorException ex = assertThrows(ErrorException.class, () -> client.getArrays().getDictionaryNull());
        assertTrue(ex.getMessage().contains("JsonMappingException"));
    }

    @Test
    public void getDictionaryEmpty() {
        List<Map<String, String>> result = client.getArrays().getDictionaryEmpty();
        assertEquals(0, result.size());
    }

    @Test
    public void getDictionaryItemNull() {
        List<Map<String, String>> result = client.getArrays().getDictionaryItemNull();
        assertEquals(3, result.size());
        assertNull(result.get(1));
    }

    @Test
    public void getDictionaryItemEmpty() {
        List<Map<String, String>> result = client.getArrays().getDictionaryItemEmpty();
        assertEquals(3, result.size());
        assertEquals(0, result.get(1).size());
    }

    @Test
    public void getDictionaryValid() {
        List<Map<String, String>> result = client.getArrays().getDictionaryValid();
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
        client.getArrays().putDictionaryValid(body);
    }
}
