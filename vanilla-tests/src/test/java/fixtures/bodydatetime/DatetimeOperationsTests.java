package fixtures.bodydatetime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DatetimeOperationsTests {
    private static AutoRestDateTimeTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestDateTimeTestServiceBuilder().buildClient();
    }

    @Test
    public void getNull() {
        assertNull(client.getDatetimeOperations().getNull());
    }

    @Test
    public void getInvalidDate() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> client.getDatetimeOperations().getInvalid());
        assertInstanceOf(DateTimeParseException.class, exception.getCause());
    }

    @Test
    public void getOverflowDate() {
        OffsetDateTime result = client.getDatetimeOperations().getOverflow();
        OffsetDateTime expected = OffsetDateTime.of(LocalDate.of(10000, 1, 1), LocalTime.parse("13:59:59.999"), ZoneOffset.UTC);
        assertEquals(expected.toInstant().toEpochMilli(), result.toInstant().toEpochMilli());
    }

    @Test
    public void getUnderflowDate() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> client.getDatetimeOperations().getUnderflow());
        assertInstanceOf(DateTimeException.class, exception.getCause());
    }

    @Test
    public void putUtcMaxDateTime() {
        OffsetDateTime body = OffsetDateTime.parse("9999-12-31T23:59:59.999-00:00");
        client.getDatetimeOperations().putUtcMaxDateTime(body);
    }

    @Test
    public void getUtcLowercaseMaxDateTime() {
        OffsetDateTime result = client.getDatetimeOperations().getUtcLowercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T23:59:59.999-00:00");
        assertEquals(expected, result);
    }

    @Test
    public void getUtcUppercaseMaxDateTime() {
        OffsetDateTime result = client.getDatetimeOperations().getUtcUppercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T23:59:59.999+00:00");
        assertEquals(expected, result);
    }

    @Test
    public void putLocalPositiveOffsetMaxDateTime() {
        OffsetDateTime body = OffsetDateTime.parse("9999-12-31T09:59:59.999Z");
        client.getDatetimeOperations().putLocalPositiveOffsetMaxDateTime(body);
    }

    @Test
    public void getLocalPositiveOffsetLowercaseMaxDateTime() {
        OffsetDateTime result = client.getDatetimeOperations().getLocalPositiveOffsetLowercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T09:59:59.999+00:00");
        assertEquals(expected.toInstant().toEpochMilli(), result.toInstant().toEpochMilli());
    }

    @Test
    public void getLocalPositiveOffsetUppercaseMaxDateTime() {
        OffsetDateTime result = client.getDatetimeOperations().getLocalPositiveOffsetUppercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T23:59:59.999+00:00").withOffsetSameLocal(ZoneOffset.ofHours(14)).withOffsetSameInstant(ZoneOffset.UTC);
        assertEquals(expected.toInstant().toEpochMilli(), result.toInstant().toEpochMilli());
    }

    @Disabled("Test server cannot handle year 10000")
    @Test
    public void putLocalNegativeOffsetMaxDateTime() {
        OffsetDateTime body = OffsetDateTime.of(LocalDate.of(10000, 1, 1), LocalTime.parse("13:59:59.999"), ZoneOffset.UTC);
        client.getDatetimeOperations().putLocalNegativeOffsetMaxDateTime(body);
    }

    @Test
    public void getLocalNegativeOffsetLowercaseMaxDateTime() {
        OffsetDateTime result = client.getDatetimeOperations().getLocalNegativeOffsetLowercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T23:59:59.999-00:00").withOffsetSameLocal(ZoneOffset.ofHours(-14)).withOffsetSameInstant(ZoneOffset.UTC);
        assertEquals(expected.toInstant().toEpochMilli(), result.toInstant().toEpochMilli());
    }

    @Test
    public void getLocalNegativeOffsetUppercaseMaxDateTime() {
        OffsetDateTime result = client.getDatetimeOperations().getLocalNegativeOffsetUppercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.of(LocalDate.of(10000, 1, 1), LocalTime.parse("13:59:59.999"), ZoneOffset.UTC);
        assertEquals(expected.toInstant().toEpochMilli(), result.toInstant().toEpochMilli());
    }

    @Test
    public void putUtcMinDateTime() {
        OffsetDateTime body = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        client.getDatetimeOperations().putUtcMinDateTime(body);
    }

    @Test
    public void getUtcMinDateTime() {
        OffsetDateTime result = client.getDatetimeOperations().getUtcMinDateTime();
        OffsetDateTime expected = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        assertEquals(expected, result);
    }

    @Test
    public void putLocalPositiveOffsetMinDateTime() {
        OffsetDateTime body = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(14));
        client.getDatetimeOperations().putLocalPositiveOffsetMinDateTime(body);
    }

    @Test
    public void getLocalPositiveOffsetMinDateTime() {
        OffsetDateTime result = client.getDatetimeOperations().getLocalPositiveOffsetMinDateTime();
        OffsetDateTime expected = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(14)).withOffsetSameInstant(ZoneOffset.UTC);
        assertEquals(expected.toInstant().toEpochMilli(), result.toInstant().toEpochMilli());
    }

    @Test
    public void putLocalNegativeOffsetMinDateTime() {
        OffsetDateTime body = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(-14));
        client.getDatetimeOperations().putLocalNegativeOffsetMinDateTime(body);
    }

    @Test
    public void getLocalNegativeOffsetMinDateTime() {
        OffsetDateTime result = client.getDatetimeOperations().getLocalNegativeOffsetMinDateTime();
        OffsetDateTime expected = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(-14)).withOffsetSameInstant(ZoneOffset.UTC);
        assertEquals(expected.toInstant().toEpochMilli(), result.toInstant().toEpochMilli());
    }
}
