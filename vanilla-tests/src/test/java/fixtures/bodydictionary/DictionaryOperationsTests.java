package fixtures.bodydictionary;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import fixtures.bodydictionary.models.Widget;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DictionaryOperationsTests {
  private static AutoRestSwaggerBATDictionaryServiceClient client;
  private static AutoRestSwaggerBATDictionaryServiceAsyncClient asyncClient;

  @BeforeClass
  public static void setup() {
    client = new AutoRestSwaggerBATDictionaryServiceBuilder().buildClient();
    asyncClient = new AutoRestSwaggerBATDictionaryServiceBuilder().buildAsyncClient();
  }

  @Test
  public void getNull() {
    assertNull(client.getNull());
  }

  @Test
  public void getInvalid() {
    try {
      client.getInvalid();
      fail();
    } catch (RuntimeException exception) {
      assertTrue(exception.getMessage().contains("HTTP response has a malformed body"));
    }
  }

  @Test
  public void getEmpty() {
    Map<String, Integer> result = client.getEmpty();
    assertEquals(0, result.keySet().size());
  }

  @Test
  public void putEmpty() {
    client.putEmpty(new HashMap<>());
  }

  @Test
  public void getNullValue() {
    Map<String, String> result = client.getNullValue();
    assertNull(result.get("key1"));
  }

  @Test
  public void getNullKey() {
    try {
      client.getNullKey();
      fail();
    } catch (RuntimeException exception) {
      assertTrue(exception.getMessage().contains("HTTP response has a malformed body"));
    }
  }

  @Test
  public void getEmptyStringKey() {
    Map<String, String> result = client.getEmptyStringKey();
    assertEquals("val1", result.get(""));
  }

  @Test
  public void getBooleanTfft() {
    Map<String, Boolean>  result = client.getBooleanTfft();
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
    client.putBooleanTfft(testData);
  }

  @Test
  public void getBooleanInvalidNull() {
    Map<String, Boolean> result = client.getBooleanInvalidNull();
    assertNull(result.get("1"));
  }

  @Test
  public void getBooleanInvalidString() {
    try {
      client.getBooleanInvalidString();
    } catch (RuntimeException ex) {
      // expected
      assertTrue(ex.getCause().getMessage().contains("only \"true\" or \"false\" recognized"));
    }
  }

  @Test
  public void getIntegerValid() {
    Map<String, Integer> result = client.getIntegerValid();
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
    client.putIntegerValid(testdata);
  }

  @Test
  public void getIntInvalidNull() {
    Map<String, Integer> result = client.getIntInvalidNull();
    assertNull(result.get("1"));
  }

  @Test
  public void getIntInvalidString() {
    try {
      client.getIntInvalidString();
      fail();
    } catch (RuntimeException ex) {
      // expected
      assertTrue(ex.getCause().getMessage().contains("not a valid Integer value"));
    }
  }

  @Test
  public void getLongValid() {
    Map<String, Long> result = client.getLongValid();
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
    client.putLongValid(expected);
  }

  @Test
  public void getLongInvalidNull() {
    Map<String, Long> result = client.getLongInvalidNull();
    assertNull(result.get("1"));
  }

  @Test
  public void getLongInvalidString() {
    try {
      client.getLongInvalidString();
      fail();
    } catch (RuntimeException ex) {
      // expected
      assertTrue(ex.getCause().getMessage().contains("not a valid Long value"));
    }
  }

  @Test
  public void getFloatValid() {
    Map<String, Float> result = client.getFloatValid();
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
    client.putFloatValid(testdata);
  }

  @Test
  public void getFloatInvalidNull() {
    Map<String, Float> result = client.getFloatInvalidNull();
    assertNull(result.get("1"));
  }

  @Test
  public void getFloatInvalidString() {
    try {
      client.getFloatInvalidString();
      fail();
    } catch (RuntimeException ex) {
      // expected
      assertTrue(ex.getCause().getMessage().contains("not a valid Float value"));
    }
  }

  @Test
  public void getDoubleValid() {
    Map<String, Double> result = client.getDoubleValid();
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
    client.putDoubleValid(testdata);
  }

  @Test
  public void getDoubleInvalidNull() {
    Map<String, Double> result = client.getDoubleInvalidNull();
    assertNull(result.get("1"));
  }

  @Test
  public void getDoubleInvalidString() {
    try {
      client.getDoubleInvalidString();
      fail();
    } catch (RuntimeException ex) {
      // expected
      assertTrue(ex.getCause().getMessage().contains("not a valid Double value"));
    }
  }

  @Test
  public void getStringValid() {
    Map<String, String> result = client.getStringValid();
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
    client.putStringValid(testdata);
  }

  @Test
  public void getStringWithNull() {
    Map<String, String> result = client.getStringWithNull();
    assertNull(result.get("1"));
  }

  @Test
  public void getStringWithInvalid() {
    Map<String, String> result = client.getStringWithInvalid();
    assertEquals("123", result.get("1"));
  }

  @Test
  public void getDateValid() {
    Map<String, LocalDate> result = client.getDateValid();
    Map<String, LocalDate> expected = new HashMap<>();
    expected.put("0", LocalDate.of(2000, 12, 1));
    expected.put("1", LocalDate.of(1980, 1, 2));
    expected.put("2", LocalDate.of(1492, 10, 12));
    assertEquals(expected, result);
  }

  @Test
  public void putDateValid() {
    Map<String, LocalDate> testdata = new HashMap<>();
    testdata.put("0", LocalDate.of(2000, 12, 1));
    testdata.put("1", LocalDate.of(1980, 1, 2));
    testdata.put("2", LocalDate.of(1492, 10, 12));
    client.putDateValid(testdata);
  }

  @Test
  public void getDateInvalidNull() {
    Map<String, LocalDate> result = client.getDateInvalidNull();
    assertNull(result.get("1"));
  }

  @Test
  public void getDateInvalidString() {
    try {
      client.getDateInvalidChars();
      fail();
    } catch (RuntimeException ex) {
      assertEquals(InvalidFormatException.class, ex.getCause().getClass());
    }
  }

  @Test
  public void getDateTimeValid() {
    Map<String, OffsetDateTime> result = client.getDateTimeValid();
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
    client.putDateTimeValid(testdata);
  }

  @Test
  public void getDateTimeInvalidNull() {
    Map<String, OffsetDateTime> result = client.getDateTimeInvalidNull();
    assertNull(result.get("1"));
  }

  @Test
  public void getDateTimeInvalidString() {
    try {
      client.getDateTimeInvalidChars();
      fail();
    } catch (RuntimeException ex) {
      assertEquals(InvalidFormatException.class, ex.getCause().getClass());
    }
  }

  @Test
  public void getDateTimeRfc1123Valid() {
    Map<String, OffsetDateTime> result = client.getDateTimeRfc1123Valid();
    Map<String, OffsetDateTime> expected = new HashMap<>();
    expected.put("0", OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC));
    expected.put("1", OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC));
    expected.put("2", OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC));
    assertEquals(expected, result);
  }

  @Test
  public void putDateTimeRfc1123Valid() {
    Map<String, OffsetDateTime> testdata = new HashMap<>();
    testdata.put("0", OffsetDateTime.of(2000, 12, 1, 0, 0, 1, 0, ZoneOffset.UTC));
    testdata.put("1", OffsetDateTime.of(1980, 1, 2, 0, 11, 35, 0, ZoneOffset.UTC));
    testdata.put("2", OffsetDateTime.of(1492, 10, 12, 10, 15, 1, 0, ZoneOffset.UTC));
    client.putDateTimeRfc1123Valid(testdata);
  }

  @Test
  public void getDurationValid() {
    Map<String, Duration> result = client.getDurationValid();
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
    client.putDurationValid(testdata);
  }

  @Test
  public void getByteValid() {
    Map<String, byte[]> result = client.getByteValid();
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
    client.putByteValid(testdata);
  }

  @Test
  public void getByteInvalidNull() {
    Map<String, byte[]> result = client.getByteInvalidNull();
    assertNull(result.get("1"));
  }

  @Test
  public void getBase64Url() {
    Map<String, byte[]> result = client.getBase64Url();
    assertEquals("a string that gets encoded with base64url", new String(result.get("0")));
    assertEquals("test string", new String(result.get("1")));
    assertEquals("Lorem ipsum", new String(result.get("2")));
  }

  @Test
  public void getComplexNull() {
    Map<String, Widget> result = client.getComplexNull();
    assertNull(result);
  }

  @Test
  public void getComplexEmpty() {
    Map<String, Widget> result = client.getComplexEmpty();
    assertEquals(0, result.size());
  }

  @Test
  public void getComplexItemNull() {
    Map<String, Widget> result = client.getComplexItemNull();
    assertEquals(3, result.size());
    assertNull(result.get("1"));
  }

  @Test
  public void getComplexItemEmpty() {
    Map<String, Widget> result = client.getComplexItemEmpty();
    assertEquals(3, result.size());
    assertNull(result.get("1").getInteger());
    assertNull(result.get("1").getString());
  }

  @Test
  public void getComplexValid() {
    Map<String, Widget> result = client.getComplexValid();
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
    client.putComplexValid(body);
  }

  @Test
  public void getArrayNull() {
    Map<String, List<String>> result = client.getArrayNull();
    assertNull(result);
  }

  @Test
  public void getArrayEmpty() {
    Map<String, List<String>> result = client.getArrayEmpty();
    assertEquals(0, result.size());
  }

  @Test
  public void getArrayItemNull() {
    Map<String, List<String>> result = client.getArrayItemNull();
    assertNull(result.get("1"));
  }

  @Test
  public void getArrayItemEmpty() {
    Map<String, List<String>> result = client.getArrayItemEmpty();
    assertEquals(0, result.get("1").size());
  }

  @Test
  public void getArrayValid() {
    Map<String, List<String>> result = client.getArrayValid();
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
    client.putArrayValid(body);
  }

  @Test
  public void getDictionaryNull() {
    assertNull(client.getDictionaryNull());
  }

  @Test
  public void getDictionaryEmpty() {
    Map<String, Map<String, String>> result = client.getDictionaryEmpty();
    assertEquals(0, result.size());
  }

  @Test
  public void getDictionaryItemNull() {
    Map<String, Map<String, String>> result = client.getDictionaryItemNull();
    assertNull(result.get("1"));
  }

  @Test
  public void getDictionaryItemEmpty() {
    Map<String, Map<String, String>> result = client.getDictionaryItemEmpty();
    Map<String, String> value = (Map<String, String>) result.get("1");
    assertEquals(0, value.size());
  }

  @Test
  public void getDictionaryValid() {
    Map<String, Map<String, String>> result = client.getDictionaryValid();
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
    Map<String, Map<String, String>> body = new HashMap<>();
    body.put("0", map1);
    body.put("1", map2);
    body.put("2", map3);
    client.putDictionaryValid(body);
  }
}
