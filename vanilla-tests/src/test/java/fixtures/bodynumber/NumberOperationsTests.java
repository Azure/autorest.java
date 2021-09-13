package fixtures.bodynumber;

import static org.junit.Assert.fail;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.implementation.serializer.MalformedValueException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class NumberOperationsTests {
  private static AutoRestNumberTestService client;

  @BeforeClass
  public static void setup() {
    client = new AutoRestNumberTestService("http://localhost:3000");
  }

  @Test
  public void getNull() throws Exception {
    try {
      double d = client.getNumbers().getNull();
      fail();
    } catch (NullPointerException e) {
      // expected
    }
  }

  @Test
  public void getInvalidFloat() throws Exception {
    try {
      client.getNumbers().getInvalidFloat();
      Assert.assertTrue(false);
    } catch (HttpResponseException exception) {
      // expected
      Assert.assertTrue(exception.getCause() instanceof JsonParseException);
    }
  }

  @Test
  public void getInvalidDouble() throws Exception {
    try {
      client.getNumbers().getInvalidDouble();
      Assert.assertTrue(false);
    } catch (HttpResponseException exception) {
      // expected
      Assert.assertTrue(exception.getCause() instanceof JsonParseException);
    }
  }

  @Test
  public void putBigFloat() throws Exception {
    client.getNumbers().putBigFloat(3.402823e+20f);
  }

  @Test
  public void putBigDouble() throws Exception {
    client.getNumbers().putBigDouble(2.5976931e+101);
  }

  @Test
  public void getBigFloat() throws Exception {
    double result = client.getNumbers().getBigFloat();
    Assert.assertEquals(3.402823e+20f, result, 0.0f);
  }

  @Test
  public void getBigDouble() throws Exception {
    double result = client.getNumbers().getBigDouble();
    Assert.assertEquals(2.5976931e+101, result, 0.0f);
  }

  @Test
  public void putBigDoublePositiveDecimal() throws Exception {
    client.getNumbers().putBigDoublePositiveDecimal();
  }

  @Test
  public void getBigDoublePositiveDecimal() throws Exception {
    double result = client.getNumbers().getBigDoublePositiveDecimal();
    Assert.assertEquals(99999999.99, result, 0.0f);
  }

  @Test
  public void putBigDoubleNegativeDecimal() throws Exception {
    client.getNumbers().putBigDoubleNegativeDecimal();
  }

  @Test
  public void getBigDoubleNegativeDecimal() throws Exception {
    double result = client.getNumbers().getBigDoubleNegativeDecimal();
    Assert.assertEquals(-99999999.99, result, 0.0f);
  }

  @Test
  public void putSmallFloat() throws Exception {
    client.getNumbers().putSmallFloat(3.402823e-20f);
  }

  @Test
  public void getSmallFloat() throws Exception {
    double result = client.getNumbers().getSmallFloat();
    Assert.assertEquals(3.402823e-20, result, 0.0f);
  }

  @Test
  public void putSmallDouble() throws Exception {
    client.getNumbers().putSmallDouble(2.5976931e-101);
  }

  @Test
  public void getSmallDouble() throws Exception {
    double result = client.getNumbers().getSmallDouble();
    Assert.assertEquals(2.5976931e-101, result, 0.0f);
  }

  @Test
  public void putBigDecimalPositiveDecimalTest() throws Exception {
    client.getNumbers().putBigDecimalPositiveDecimal();
  }

  @Test
  public void putBigDecimalNegativeDecimalTest() throws Exception {
    client.getNumbers().putBigDecimalNegativeDecimal();
  }

  @Test
  public void getBigDecimalTest() throws Exception {
    BigDecimal result = client.getNumbers().getBigDecimal();
    Assert.assertEquals(BigDecimal.valueOf(2.5976931E+101), result);
  }

  @Test
  public void getBigDecimalPositiveDecimalTest() throws Exception {
    BigDecimal result = client.getNumbers().getBigDecimalPositiveDecimal();
    Assert.assertEquals(BigDecimal.valueOf(99999999.99), result);
  }

  @Test
  public void getBigDecimalNegativeDecimalTest() throws Exception {
    BigDecimal result = client.getNumbers().getBigDecimalNegativeDecimal();
    Assert.assertEquals(BigDecimal.valueOf(-99999999.99), result);
  }

  @Test
  public void putBigDecimalTest() throws Exception {
    BigDecimal request = new BigDecimal("2.5976931e+101");
    client.getNumbers().putBigDecimal(request);
  }

}
