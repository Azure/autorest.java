package fixtures.bodynumber;

import com.azure.core.exception.HttpResponseException;
import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberOperationsTests {
    private static AutoRestNumberTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestNumberTestService("http://localhost:3000");
    }

    @Test
    public void getNull() throws Exception {
        assertThrows(NullPointerException.class, () -> client.getNumbers().getNull());
    }

    @Test
    public void getInvalidFloat() {
        HttpResponseException exception = assertThrows(HttpResponseException.class, () -> client.getNumbers().getInvalidFloat());
        assertInstanceOf(JsonParseException.class, exception.getCause());
    }

    @Test
    public void getInvalidDouble() {
        HttpResponseException exception = assertThrows(HttpResponseException.class, () -> client.getNumbers().getInvalidDouble());
        assertInstanceOf(JsonParseException.class, exception.getCause());
    }

    @Test
    public void putBigFloat() {
        client.getNumbers().putBigFloat(3.402823e+20f);
    }

    @Test
    public void putBigDouble() {
        client.getNumbers().putBigDouble(2.5976931e+101);
    }

    @Test
    public void getBigFloat() {
        double result = client.getNumbers().getBigFloat();
        assertEquals(3.402823e+20f, result, 0.0f);
    }

    @Test
    public void getBigDouble() {
        double result = client.getNumbers().getBigDouble();
        assertEquals(2.5976931e+101, result, 0.0f);
    }

    @Test
    public void putBigDoublePositiveDecimal() {
        client.getNumbers().putBigDoublePositiveDecimal();
    }

    @Test
    public void getBigDoublePositiveDecimal() {
        double result = client.getNumbers().getBigDoublePositiveDecimal();
        assertEquals(99999999.99, result, 0.0f);
    }

    @Test
    public void putBigDoubleNegativeDecimal() {
        client.getNumbers().putBigDoubleNegativeDecimal();
    }

    @Test
    public void getBigDoubleNegativeDecimal() {
        double result = client.getNumbers().getBigDoubleNegativeDecimal();
        assertEquals(-99999999.99, result, 0.0f);
    }

    @Test
    public void putSmallFloat() {
        client.getNumbers().putSmallFloat(3.402823e-20f);
    }

    @Test
    public void getSmallFloat() {
        double result = client.getNumbers().getSmallFloat();
        assertEquals(3.402823e-20, result, 0.0f);
    }

    @Test
    public void putSmallDouble() {
        client.getNumbers().putSmallDouble(2.5976931e-101);
    }

    @Test
    public void getSmallDouble() {
        double result = client.getNumbers().getSmallDouble();
        assertEquals(2.5976931e-101, result, 0.0f);
    }

    @Test
    public void putBigDecimalPositiveDecimalTest() {
        client.getNumbers().putBigDecimalPositiveDecimal();
    }

    @Test
    public void putBigDecimalNegativeDecimalTest() {
        client.getNumbers().putBigDecimalNegativeDecimal();
    }

    @Test
    public void getBigDecimalTest() {
        BigDecimal result = client.getNumbers().getBigDecimal();
        assertEquals(BigDecimal.valueOf(2.5976931E+101), result);
    }

    @Test
    public void getBigDecimalPositiveDecimalTest() {
        BigDecimal result = client.getNumbers().getBigDecimalPositiveDecimal();
        assertEquals(BigDecimal.valueOf(99999999.99), result);
    }

    @Test
    public void getBigDecimalNegativeDecimalTest() {
        BigDecimal result = client.getNumbers().getBigDecimalNegativeDecimal();
        assertEquals(BigDecimal.valueOf(-99999999.99), result);
    }

    @Test
    public void putBigDecimalTest() {
        BigDecimal request = new BigDecimal("2.5976931e+101");
        client.getNumbers().putBigDecimal(request);
    }

}
