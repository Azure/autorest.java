package fixtures.bodydate;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateOperationsTests {
    private static AutoRestDateTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestDateTestServiceBuilder().buildClient();
    }

    @Test
    public void getNull() {
        assertNull(client.getDateOperations().getNull());
    }

    @Test
    public void getInvalidDate() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> client.getDateOperations().getInvalidDate());
        assertInstanceOf(InvalidFormatException.class, exception.getCause());
    }

    @Test
    public void getOverflowDate() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> client.getDateOperations().getOverflowDate());
        assertInstanceOf(InvalidFormatException.class, exception.getCause());
    }

    @Test
    public void getUnderflowDate() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> client.getDateOperations().getUnderflowDate());
        assertInstanceOf(InvalidFormatException.class, exception.getCause());
    }

    @Test
    public void putMaxDate() {
        LocalDate body = LocalDate.of(9999, 12, 31);
        client.getDateOperations().putMaxDate(body);
    }

    @Test
    public void getMaxDate() {
        LocalDate expected = LocalDate.of(9999, 12, 31);
        LocalDate result = client.getDateOperations().getMaxDate();
        assertEquals(expected, result);
    }

    @Test
    public void putMinDate() {
        LocalDate body = LocalDate.of(1, 1, 1);
        client.getDateOperations().putMinDate(body);
    }

    @Test
    public void getMinDate() {
        LocalDate expected = LocalDate.of(1, 1, 1);
        LocalDate result = client.getDateOperations().getMinDate();
        assertEquals(expected, result);
    }
}
