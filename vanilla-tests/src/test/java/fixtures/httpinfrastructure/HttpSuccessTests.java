package fixtures.httpinfrastructure;

import org.junit.BeforeClass;
import org.junit.Test;

public class HttpSuccessTests {
  private static AutoRestHttpInfrastructureTestService client;

  @BeforeClass
  public static void setup() {
    client = new AutoRestHttpInfrastructureTestServiceBuilder().buildClient();
  }

  @Test
  public void head200() throws Exception {
    client.getHttpSuccess().head200();
  }

  @Test
  public void get200() throws Exception {
    client.getHttpSuccess().get200();
  }

  @Test
  public void put200() throws Exception {
    client.getHttpSuccess().put200();
  }

  @Test
  public void patch200() throws Exception {
    client.getHttpSuccess().patch200();
  }

  @Test
  public void post200() throws Exception {
    client.getHttpSuccess().post200();
  }

  @Test
  public void delete200() throws Exception {
    client.getHttpSuccess().delete200();
  }

  @Test
  public void put201() throws Exception {
    client.getHttpSuccess().put201();
  }

  @Test
  public void post201() throws Exception {
    client.getHttpSuccess().post201();
  }

  @Test
  public void put202() throws Exception {
    client.getHttpSuccess().put202();
  }

  @Test
  public void patch202() throws Exception {
    client.getHttpSuccess().patch202();
  }

  @Test
  public void post202() throws Exception {
    client.getHttpSuccess().post202();
  }

  @Test
  public void delete202() throws Exception {
    client.getHttpSuccess().delete202();
  }

  @Test
  public void head204() throws Exception {
    client.getHttpSuccess().head204();
  }

  @Test
  public void put204() throws Exception {
    client.getHttpSuccess().put204();
  }

  @Test
  public void patch204() throws Exception {
    client.getHttpSuccess().patch204();
  }

  @Test
  public void post204() throws Exception {
    client.getHttpSuccess().post204();
  }

  @Test
  public void delete204() throws Exception {
    client.getHttpSuccess().delete204();
  }

  @Test
  public void head404() throws Exception {
    client.getHttpSuccess().head404();
  }
}
