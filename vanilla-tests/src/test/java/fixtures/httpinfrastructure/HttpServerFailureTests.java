package fixtures.httpinfrastructure;

import fixtures.httpinfrastructure.models.ErrorException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HttpServerFailureTests {
  private static AutoRestHttpInfrastructureTestService client;

  @BeforeClass
  public static void setup() {
    client = new AutoRestHttpInfrastructureTestServiceBuilder().buildClient();
  }

  @Test
  public void head501() throws Exception {
    try {
      client.getHttpServerFailures().head501();
    } catch (ErrorException ex) {
      Assert.assertEquals(501, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get501() throws Exception {
    try {
      client.getHttpServerFailures().get501();
    } catch (ErrorException ex) {
      Assert.assertEquals(501, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void post505() throws Exception {
    try {
      client.getHttpServerFailures().post505();
    } catch (ErrorException ex) {
      Assert.assertEquals(505, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void delete505() throws Exception {
    try {
      client.getHttpServerFailures().delete505();
    } catch (ErrorException ex) {
      Assert.assertEquals(505, ex.getResponse().getStatusCode());
    }
  }
}
