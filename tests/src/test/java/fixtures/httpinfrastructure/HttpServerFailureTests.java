package fixtures.httpinfrastructure;

import fixtures.httpinfrastructure.models.ErrorException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HttpServerFailureTests {
  private static AutoRestHttpInfrastructureTestService client;

  @BeforeClass
  public static void setup() {
    client = new AutoRestHttpInfrastructureTestServiceBuilder().build();
  }

  @Test
  public void head501() throws Exception {
    try {
      client.httpServerFailures().head501();
    } catch (ErrorException ex) {
      Assert.assertEquals(501, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get501() throws Exception {
    try {
      client.httpServerFailures().get501();
    } catch (ErrorException ex) {
      Assert.assertEquals(501, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void post505() throws Exception {
    try {
      client.httpServerFailures().post505();
    } catch (ErrorException ex) {
      Assert.assertEquals(505, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void delete505() throws Exception {
    try {
      client.httpServerFailures().delete505();
    } catch (ErrorException ex) {
      Assert.assertEquals(505, ex.getResponse().getStatusCode());
    }
  }
}
