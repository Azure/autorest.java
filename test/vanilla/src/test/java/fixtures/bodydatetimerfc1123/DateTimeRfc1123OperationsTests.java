package fixtures.bodydatetimerfc1123;

import com.fasterxml.jackson.databind.JsonMappingException;
import fixtures.bodydatetimerfc1123.implementation.AutoRestRFC1123DateTimeTestServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class DateTimeRfc1123OperationsTests {
    private static AutoRestRFC1123DateTimeTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestRFC1123DateTimeTestServiceImpl();
    }

    @Test
    public void getNull() {
        Assert.assertNull(client.datetimerfc1123s().getNull());
    }

    @Test
    public void getInvalidDate() {
        try {
            client.datetimerfc1123s().getInvalid();
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getCause(), instanceOf(JsonMappingException.class));
        }
    }

    @Test
    public void getOverflowDate() {
        OffsetDateTime result = client.datetimerfc1123s().getOverflow();
        OffsetDateTime expected = OffsetDateTime.of(10000, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getUnderflowDate() {
        try {
            client.datetimerfc1123s().getUnderflow();
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getCause(), instanceOf(JsonMappingException.class));
        }
    }

    @Test
    public void putUtcMaxDateTime() {
        OffsetDateTime body = OffsetDateTime.of(9999, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        client.datetimerfc1123s().putUtcMaxDateTime(body);
    }

    @Test
    public void getUtcLowercaseMaxDateTime() {
        OffsetDateTime result = client.datetimerfc1123s().getUtcLowercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.of(9999, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getUtcUppercaseMaxDateTime() {
        OffsetDateTime result = client.datetimerfc1123s().getUtcUppercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.of(9999, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void putUtcMinDateTime() {
        OffsetDateTime body = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        client.datetimerfc1123s().putUtcMinDateTime(body);
    }

    @Test
    public void getUtcMinDateTime() {
        OffsetDateTime result = client.datetimerfc1123s().getUtcMinDateTime();
        OffsetDateTime expected = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        Assert.assertEquals(expected, result);
    }
}
