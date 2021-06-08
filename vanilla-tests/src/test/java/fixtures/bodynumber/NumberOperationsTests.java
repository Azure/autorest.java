package fixtures.bodynumber;

import com.azure.core.implementation.serializer.MalformedValueException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

public class NumberOperationsTests {
    private static AutoRestNumberTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestNumberTestServiceBuilder().buildClient();
    }

    @Test
    public void getNull() {
        assertThrows(NullPointerException.class, () -> client.getNumbers().getNull());
    }

    @Test
    public void getInvalidFloat() {
        try {
            client.getNumbers().getInvalidFloat();
            fail("Method should have thrown an exception.");
        } catch (Exception exception) {
            // expected
            Assert.assertEquals(exception.toString(), MalformedValueException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getInvalidDouble() {
        try {
            client.getNumbers().getInvalidDouble();
            fail("Method should have thrown an exception.");
        } catch (Exception exception) {
            // expected
            Assert.assertEquals(exception.toString(), MalformedValueException.class, exception.getCause().getClass());
        }
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
        Assert.assertEquals(3.402823e+20f, result, 0.0f);
    }

    @Test
    public void getBigDouble() {
        double result = client.getNumbers().getBigDouble();
        Assert.assertEquals(2.5976931e+101, result, 0.0f);
    }

    @Test
    public void putBigDoublePositiveDecimal() {
        client.getNumbers().putBigDoublePositiveDecimal();
    }

    @Test
    public void getBigDoublePositiveDecimal() {
        double result = client.getNumbers().getBigDoublePositiveDecimal();
        Assert.assertEquals(99999999.99, result, 0.0f);
    }

    @Test
    public void putBigDoubleNegativeDecimal() {
        client.getNumbers().putBigDoubleNegativeDecimal();
    }

    @Test
    public void getBigDoubleNegativeDecimal() {
        double result = client.getNumbers().getBigDoubleNegativeDecimal();
        Assert.assertEquals(-99999999.99, result, 0.0f);
    }

    @Test
    public void putSmallFloat() {
        client.getNumbers().putSmallFloat(3.402823e-20f);
    }

    @Test
    public void getSmallFloat() {
        double result = client.getNumbers().getSmallFloat();
        Assert.assertEquals(3.402823e-20, result, 0.0f);
    }

    @Test
    public void putSmallDouble() {
        client.getNumbers().putSmallDouble(2.5976931e-101);
    }

    @Test
    public void getSmallDouble() {
        double result = client.getNumbers().getSmallDouble();
        Assert.assertEquals(2.5976931e-101, result, 0.0f);
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
        Assert.assertEquals(BigDecimal.valueOf(2.5976931E+101), result);
    }

    @Test
    public void getBigDecimalPositiveDecimalTest() {
        BigDecimal result = client.getNumbers().getBigDecimalPositiveDecimal();
        Assert.assertEquals(BigDecimal.valueOf(99999999.99), result);
    }

    @Test
    public void getBigDecimalNegativeDecimalTest() {
        BigDecimal result = client.getNumbers().getBigDecimalNegativeDecimal();
        Assert.assertEquals(BigDecimal.valueOf(-99999999.99), result);
    }

    @Test
    public void putBigDecimalTest() {
        BigDecimal request = new BigDecimal("2.5976931e+101");
        client.getNumbers().putBigDecimal(request);
    }

}
