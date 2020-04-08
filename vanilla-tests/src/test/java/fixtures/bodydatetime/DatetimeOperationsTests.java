package fixtures.bodydatetime;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DatetimeOperationsTests {
    private static AutoRestDateTimeTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestDateTimeTestServiceBuilder().buildClient();
    }

    @Test
    public void getNull() {
        Assert.assertNull(client.datetimes().getNull());
    }

    @Test
    public void getInvalidDate() {
        try {
            client.datetimes().getInvalid();
            Assert.fail();
        } catch (RuntimeException exception) {
            Assert.assertEquals(InvalidFormatException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getOverflowDate() {
        OffsetDateTime result = client.datetimes().getOverflow();
        OffsetDateTime expected = OffsetDateTime.of(LocalDate.of(10000, 1, 1), LocalTime.parse("13:59:59.999"), ZoneOffset.UTC);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getUnderflowDate() {
        try {
            client.datetimes().getUnderflow();
            Assert.fail();
        } catch (RuntimeException exception) {
            // expected
            Assert.assertEquals(InvalidFormatException.class, exception.getCause().getClass());
         }
    }

    @Test
    public void putUtcMaxDateTime() {
        OffsetDateTime body = OffsetDateTime.parse("9999-12-31T23:59:59.999-00:00");
        client.datetimes().putUtcMaxDateTime(body);
    }

    @Test
    public void getUtcLowercaseMaxDateTime() {
        OffsetDateTime result = client.datetimes().getUtcLowercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T23:59:59.999-00:00");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getUtcUppercaseMaxDateTime() {
        OffsetDateTime result = client.datetimes().getUtcUppercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T23:59:59.999+00:00");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void putLocalPositiveOffsetMaxDateTime() {
        OffsetDateTime body = OffsetDateTime.parse("9999-12-31T09:59:59.999Z");
        client.datetimes().putLocalPositiveOffsetMaxDateTime(body);
    }

    @Test
    public void getLocalPositiveOffsetLowercaseMaxDateTime() {
        OffsetDateTime result = client.datetimes().getLocalPositiveOffsetLowercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T09:59:59.999+00:00");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getLocalPositiveOffsetUppercaseMaxDateTime() {
        OffsetDateTime result = client.datetimes().getLocalPositiveOffsetUppercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T23:59:59.999+00:00").withOffsetSameLocal(ZoneOffset.ofHours(14)).withOffsetSameInstant(ZoneOffset.UTC);
        Assert.assertEquals(expected, result);
    }

    @Ignore("Test server cannot handle year 10000")
    public void putLocalNegativeOffsetMaxDateTime() {
        OffsetDateTime body = OffsetDateTime.of(LocalDate.of(10000, 1, 1), LocalTime.parse("13:59:59.999"), ZoneOffset.UTC);
        client.datetimes().putLocalNegativeOffsetMaxDateTime(body);
    }

    @Test
    public void getLocalNegativeOffsetLowercaseMaxDateTime() {
        OffsetDateTime result = client.datetimes().getLocalNegativeOffsetLowercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.parse("9999-12-31T23:59:59.999-00:00").withOffsetSameLocal(ZoneOffset.ofHours(-14)).withOffsetSameInstant(ZoneOffset.UTC);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getLocalNegativeOffsetUppercaseMaxDateTime() {
        OffsetDateTime result = client.datetimes().getLocalNegativeOffsetUppercaseMaxDateTime();
        OffsetDateTime expected = OffsetDateTime.of(LocalDate.of(10000, 1, 1), LocalTime.parse("13:59:59.999"), ZoneOffset.UTC);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void putUtcMinDateTime() {
        OffsetDateTime body = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        client.datetimes().putUtcMinDateTime(body);
    }

    @Test
    public void getUtcMinDateTime() {
        OffsetDateTime result = client.datetimes().getUtcMinDateTime();
        OffsetDateTime expected = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void putLocalPositiveOffsetMinDateTime() {
        OffsetDateTime body = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(14));
        client.datetimes().putLocalPositiveOffsetMinDateTime(body);
    }

    @Test
    public void getLocalPositiveOffsetMinDateTime() {
        OffsetDateTime result = client.datetimes().getLocalPositiveOffsetMinDateTime();
        OffsetDateTime expected = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(14)).withOffsetSameInstant(ZoneOffset.UTC);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void putLocalNegativeOffsetMinDateTime() {
        OffsetDateTime body = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(-14));
        client.datetimes().putLocalNegativeOffsetMinDateTime(body);
    }

    @Test
    public void getLocalNegativeOffsetMinDateTime() {
        OffsetDateTime result = client.datetimes().getLocalNegativeOffsetMinDateTime();
        OffsetDateTime expected = OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(-14)).withOffsetSameInstant(ZoneOffset.UTC);
        Assert.assertEquals(expected, result);
    }
}
