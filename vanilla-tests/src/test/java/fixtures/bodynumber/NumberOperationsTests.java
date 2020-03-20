package fixtures.bodynumber;

import static org.junit.Assert.fail;

import com.azure.core.implementation.serializer.MalformedValueException;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class NumberOperationsTests {
  private static AutoRestNumberTestService client;

  @BeforeClass
  public static void setup() {
    client = new AutoRestNumberTestServiceBuilder().build();
  }

  @Test
  public void getNull() throws Exception {
    try {
      double d = client.numbers().getNull();
      fail();
    } catch (NullPointerException e) {
      // expected
    }
  }

  @Test
  public void getInvalidFloat() throws Exception {
    try {
      client.numbers().getInvalidFloat();
      Assert.assertTrue(false);
    } catch (Exception exception) {
      // expected
      Assert.assertEquals(MalformedValueException.class, exception.getCause().getClass());
    }
  }

  @Test
  public void getInvalidDouble() throws Exception {
    try {
      client.numbers().getInvalidDouble();
      Assert.assertTrue(false);
    } catch (Exception exception) {
      // expected
      Assert.assertEquals(MalformedValueException.class, exception.getCause().getClass());
    }
  }

  @Test
  public void putBigFloat() throws Exception {
    client.numbers().putBigFloat(3.402823e+20f);
  }

  @Test
  public void putBigDouble() throws Exception {
    client.numbers().putBigDouble(2.5976931e+101);
  }

  @Test
  public void getBigFloat() throws Exception {
    double result = client.numbers().getBigFloat();
    Assert.assertEquals(3.402823e+20f, result, 0.0f);
  }

  @Test
  public void getBigDouble() throws Exception {
    double result = client.numbers().getBigDouble();
    Assert.assertEquals(2.5976931e+101, result, 0.0f);
  }

  @Test
  public void putBigDoublePositiveDecimal() throws Exception {
    client.numbers().putBigDoublePositiveDecimal();
  }

  @Test
  public void getBigDoublePositiveDecimal() throws Exception {
    double result = client.numbers().getBigDoublePositiveDecimal();
    Assert.assertEquals(99999999.99, result, 0.0f);
  }

  @Test
  public void putBigDoubleNegativeDecimal() throws Exception {
    client.numbers().putBigDoubleNegativeDecimal();
  }

  @Test
  public void getBigDoubleNegativeDecimal() throws Exception {
    double result = client.numbers().getBigDoubleNegativeDecimal();
    Assert.assertEquals(-99999999.99, result, 0.0f);
  }

  @Test
  public void putSmallFloat() throws Exception {
    client.numbers().putSmallFloat(3.402823e-20f);
  }

  @Test
  public void getSmallFloat() throws Exception {
    double result = client.numbers().getSmallFloat();
    Assert.assertEquals(3.402823e-20, result, 0.0f);
  }

  @Test
  public void putSmallDouble() throws Exception {
    client.numbers().putSmallDouble(2.5976931e-101);
  }

  @Test
  public void getSmallDouble() throws Exception {
    double result = client.numbers().getSmallDouble();
    Assert.assertEquals(2.5976931e-101, result, 0.0f);
  }

  @Test
  public void putBigDecimalPositiveDecimalTest() throws Exception {
    client.numbers().putBigDecimalPositiveDecimal();
  }

  @Test
  public void putBigDecimalNegativeDecimalTest() throws Exception {
    client.numbers().putBigDecimalNegativeDecimal();
  }

  @Test
  public void getBigDecimalTest() throws Exception {
    BigDecimal result = client.numbers().getBigDecimal();
    Assert.assertEquals(BigDecimal.valueOf(2.5976931E+101), result);
  }

  @Test
  public void getBigDecimalPositiveDecimalTest() throws Exception {
    BigDecimal result = client.numbers().getBigDecimalPositiveDecimal();
    Assert.assertEquals(BigDecimal.valueOf(99999999.99), result);
  }

  @Test
  public void getBigDecimalNegativeDecimalTest() throws Exception {
    BigDecimal result = client.numbers().getBigDecimalNegativeDecimal();
    Assert.assertEquals(BigDecimal.valueOf(-99999999.99), result);
  }

  @Test
  public void putBigDecimalTest() throws Exception {
    BigDecimal request = new BigDecimal("2.5976931e+101");
    client.numbers().putBigDecimal(request);
  }

}
