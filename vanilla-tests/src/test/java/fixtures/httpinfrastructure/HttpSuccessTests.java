package fixtures.httpinfrastructure;

import org.junit.BeforeClass;
import org.junit.Test;

public class HttpSuccessTests {
  private static AutoRestHttpInfrastructureTestService client;

  @BeforeClass
  public static void setup() {
    client = new AutoRestHttpInfrastructureTestServiceBuilder().build();
  }

  @Test
  public void head200() throws Exception {
    client.httpSuccess().head200();
  }

  @Test
  public void get200() throws Exception {
    client.httpSuccess().get200();
  }

  @Test
  public void put200() throws Exception {
    client.httpSuccess().put200();
  }

  @Test
  public void patch200() throws Exception {
    client.httpSuccess().patch200();
  }

  @Test
  public void post200() throws Exception {
    client.httpSuccess().post200();
  }

  @Test
  public void delete200() throws Exception {
    client.httpSuccess().delete200();
  }

  @Test
  public void put201() throws Exception {
    client.httpSuccess().put201();
  }

  @Test
  public void post201() throws Exception {
    client.httpSuccess().post201();
  }

  @Test
  public void put202() throws Exception {
    client.httpSuccess().put202();
  }

  @Test
  public void patch202() throws Exception {
    client.httpSuccess().patch202();
  }

  @Test
  public void post202() throws Exception {
    client.httpSuccess().post202();
  }

  @Test
  public void delete202() throws Exception {
    client.httpSuccess().delete202();
  }

  @Test
  public void head204() throws Exception {
    client.httpSuccess().head204();
  }

  @Test
  public void put204() throws Exception {
    client.httpSuccess().put204();
  }

  @Test
  public void patch204() throws Exception {
    client.httpSuccess().patch204();
  }

  @Test
  public void post204() throws Exception {
    client.httpSuccess().post204();
  }

  @Test
  public void delete204() throws Exception {
    client.httpSuccess().delete204();
  }

  @Test
  public void head404() throws Exception {
    client.httpSuccess().head404();
  }
}
