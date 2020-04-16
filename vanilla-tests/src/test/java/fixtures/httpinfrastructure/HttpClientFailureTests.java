package fixtures.httpinfrastructure;

import fixtures.httpinfrastructure.models.ErrorException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.fail;

public class HttpClientFailureTests {
  private static AutoRestHttpInfrastructureTestService client;

  @BeforeClass
  public static void setup() {
    client = new AutoRestHttpInfrastructureTestServiceBuilder().buildClient();
  }

  @Test
  public void head400() throws Exception {
    try {
      client.getHttpClientFailures().head400();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get400() throws Exception {
    try {
      client.getHttpClientFailures().get400();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void put400() throws Exception {
    try {
      client.getHttpClientFailures().put400();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void patch400() throws Exception {
    try {
      client.getHttpClientFailures().patch400();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void post400() throws Exception {
    try {
      client.getHttpClientFailures().post400();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void delete400() throws Exception {
    try {
      client.getHttpClientFailures().delete400();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void head401() throws Exception {
    try {
      client.getHttpClientFailures().head401();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(401, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get402() throws Exception {
    try {
      client.getHttpClientFailures().get402();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(402, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get403() throws Exception {
    try {
      client.getHttpClientFailures().get403();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(403, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void put404() throws Exception {
    try {
      client.getHttpClientFailures().put404();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(404, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void patch405() throws Exception {
    try {
      client.getHttpClientFailures().patch405();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(405, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void post406() throws Exception {
    try {
      client.getHttpClientFailures().post406();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(406, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void delete407() throws Exception {
    try {
      client.getHttpClientFailures().delete407();
      fail();
    } catch (RuntimeException ex) {
      Assert.assertTrue(ex.getMessage().contains("Status code 407"));
    }
  }

  @Test
  public void put409() throws Exception {
    try {
      client.getHttpClientFailures().put409();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(409, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void head410() throws Exception {
    try {
      client.getHttpClientFailures().head410();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(410, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get411() throws Exception {
    try {
      client.getHttpClientFailures().get411();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(411, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get412() throws Exception {
    try {
      client.getHttpClientFailures().get412();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(412, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void put413() throws Exception {
    try {
      client.getHttpClientFailures().put413();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(413, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void patch414() throws Exception {
    try {
      client.getHttpClientFailures().patch414();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(414, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void post415() throws Exception {
    try {
      client.getHttpClientFailures().post415();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(415, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get416() throws Exception {
    try {
      client.getHttpClientFailures().get416();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(416, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void delete417() throws Exception {
    try {
      client.getHttpClientFailures().delete417();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(417, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void head429() throws Exception {
    try {
      client.getHttpClientFailures().head429();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(429, ex.getResponse().getStatusCode());
    }
  }
}
