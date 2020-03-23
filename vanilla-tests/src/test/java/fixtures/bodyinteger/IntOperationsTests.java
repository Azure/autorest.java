package fixtures.bodyinteger;

import com.azure.core.implementation.serializer.MalformedValueException;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class IntOperationsTests {
  private static AutoRestIntegerTestService client;
  private CountDownLatch lock = new CountDownLatch(1);

  @BeforeClass
  public static void setup() {
    client = new AutoRestIntegerTestServiceBuilder().build();
  }

  @Test
  public void getNull() {
    try {
      client.ints().getNull();
      Assert.fail();
    } catch (NullPointerException e) {
      // expected
    }
  }

  @Test
  public void getNullAsync() {
    Integer i = client.ints().getNullAsync().block();
    Assert.assertNull(i);
  }

  @Test
  public void getInvalid() {
    try {
      client.ints().getInvalid();
      Assert.fail();
    } catch (Exception exception) {
      Assert.assertEquals(MalformedValueException.class, exception.getCause().getClass());
    }
  }

  @Test
  public void getOverflowInt32() {
    try {
      client.ints().getOverflowInt32();
      Assert.fail();
    } catch (Exception exception) {
      Assert.assertEquals(InputCoercionException.class, exception.getCause().getClass());
    }
  }

  @Test
  public void getUnderflowInt32() {
    try {
      client.ints().getUnderflowInt32();
      Assert.fail();
    } catch (Exception exception) {
      Assert.assertEquals(InputCoercionException.class, exception.getCause().getClass());
    }
  }

  @Test
  public void getOverflowInt64() {
    try {
      long value = client.ints().getOverflowInt64();
      Assert.assertEquals(Long.MAX_VALUE, value);
    } catch (Exception exception) {
      Assert.assertEquals(InputCoercionException.class, exception.getCause().getClass());
    }
  }

  @Test
  public void getUnderflowInt64() {
    try {
      long value = client.ints().getUnderflowInt64();
      Assert.assertEquals(Long.MIN_VALUE, value);
    } catch (Exception exception) {
      Assert.assertEquals(InputCoercionException.class, exception.getCause().getClass());
    }
  }

  @Test
  public void putMax32() throws Exception {
    client.ints().putMax32Async(Integer.MAX_VALUE).subscribe(v -> {}, t -> fail(t.getMessage()), () -> lock.countDown());
    Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
  }

  @Test
  public void putMax64() throws Exception {
    client.ints().putMax64Async(Long.MAX_VALUE).subscribe(v -> {}, t -> fail(t.getMessage()), () -> lock.countDown());
    Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
  }

  @Test
  public void putMin32() throws Exception {
    client.ints().putMin32Async(Integer.MIN_VALUE).subscribe(v -> {}, t -> fail(t.getMessage()), () -> lock.countDown());
    Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
  }

  @Test
  public void putMin64() throws Exception {
    client.ints().putMin64Async(Long.MIN_VALUE).subscribe(v -> {}, t -> fail(t.getMessage()), () -> lock.countDown());
    Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
  }

  @Test
  public void getUnixTime() {
    OffsetDateTime result = client.ints().getUnixTime();
    Assert.assertEquals(OffsetDateTime.of(2016, 4, 13, 0, 0, 0, 0, ZoneOffset.UTC), result);
  }

  @Test
  public void putUnixTimeDate() {
    client.ints().putUnixTimeDate(OffsetDateTime.of(2016, 4, 13, 0, 0, 0, 0, ZoneOffset.UTC));
  }

  @Test
  public void getInvalidUnixTime() {
    try {
      client.ints().getInvalidUnixTime();
      Assert.fail();
    } catch (RuntimeException e) {
      Assert.assertTrue(e.getMessage().contains("HTTP response has a malformed body"));
    }
  }

  @Test
  public void getNullUnixTime() {
    Assert.assertNull(client.ints().getNullUnixTime());
  }
}
