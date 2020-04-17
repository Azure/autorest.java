package fixtures.bodydate;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DateOperationsTests {
    private static AutoRestDateTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestDateTestServiceBuilder().buildClient();
    }

    @Test
    public void getNull() {
        assertNull(client.getDates().getNull());
    }

    @Test
    public void getInvalidDate() {
        try {
            client.getDates().getInvalidDate();
            fail();
        } catch (RuntimeException exception) {
            assertEquals(InvalidFormatException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getOverflowDate() {
        try {
            client.getDates().getOverflowDate();
            fail();
        } catch (RuntimeException exception) {
            assertEquals(InvalidFormatException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getUnderflowDate() {
        try {
            client.getDates().getUnderflowDate();
            fail();
        } catch (RuntimeException exception) {
            assertEquals(InvalidFormatException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void putMaxDate() {
        LocalDate body = LocalDate.of(9999, 12, 31);
        client.getDates().putMaxDate(body);
    }

    @Test
    public void getMaxDate() {
        LocalDate expected = LocalDate.of(9999, 12, 31);
        LocalDate result = client.getDates().getMaxDate();
        assertEquals(expected, result);
    }

    @Test
    public void putMinDate() {
        LocalDate body = LocalDate.of(1, 1, 1);
        client.getDates().putMinDate(body);
    }

    @Test
    public void getMinDate() {
        LocalDate expected = LocalDate.of(1, 1, 1);
        LocalDate result = client.getDates().getMinDate();
        assertEquals(expected, result);
    }
}
