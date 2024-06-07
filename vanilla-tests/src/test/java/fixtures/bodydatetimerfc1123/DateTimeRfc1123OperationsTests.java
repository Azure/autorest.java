package fixtures.bodydatetimerfc1123;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTimeRfc1123OperationsTests {
    private static AutoRestRFC1123DateTimeTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestRFC1123DateTimeTestServiceBuilder().buildClient();
    }

    @Test
    public void getNull() {
        assertThrows(NullPointerException.class, () -> client.getDatetimerfc1123s().getNull());
    }

    @Test
    public void getInvalidDate() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> client.getDatetimerfc1123s().getInvalid());
        assertInstanceOf(JsonMappingException.class, e.getCause());
    }

    @Test
    public void getOverflowDate() {
        OffsetDateTime result = client.getDatetimerfc1123s().getOverflow();
        OffsetDateTime expected = OffsetDateTime.of(10000, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        assertEquals(expected, result);
    }

    @Test
    public void getUnderflowDate() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> client.getDatetimerfc1123s().getUnderflow());
        assertInstanceOf(JsonMappingException.class, e.getCause());
    }

    @Test
    public void putUtcMaxDateTime() {
        OffsetDateTime body = OffsetDateTime.of(9999, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        client.getDatetimerfc1123s().putUtcMaxDateTime(body);
    }

    @Test
    public void getUtcLowercaseMaxDateTime() {
        OffsetDateTime result = client.getDatetimerfc1123s().getUtcLowercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.of(9999, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        assertEquals(expected, result);
    }

    @Test
    public void getUtcUppercaseMaxDateTime() {
        OffsetDateTime result = client.getDatetimerfc1123s().getUtcUppercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.of(9999, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        assertEquals(expected, result);
    }

    @Test
    public void putUtcMinDateTime() {
        OffsetDateTime body = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        client.getDatetimerfc1123s().putUtcMinDateTime(body);
    }

    @Test
    public void getUtcMinDateTime() {
        OffsetDateTime result = client.getDatetimerfc1123s().getUtcMinDateTime();
        OffsetDateTime expected = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        assertEquals(expected, result);
    }
}
