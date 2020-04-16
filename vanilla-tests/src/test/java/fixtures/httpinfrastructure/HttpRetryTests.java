package fixtures.httpinfrastructure;

import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HttpRetryTests {
  private static AutoRestHttpInfrastructureTestService client;
  private CountDownLatch lock = new CountDownLatch(1);

  @BeforeClass
  public static void setup() {
    client = new AutoRestHttpInfrastructureTestServiceBuilder()
        .pipeline(new HttpPipelineBuilder().policies(new RetryPolicy(), new CookiePolicy()).build()).buildClient();
  }

  @Test
  public void head408() throws Exception {
    client.getHttpRetrys().head408WithResponseAsync()
        .subscribe(response -> {
          Assert.assertEquals(200, response.getStatusCode());
          lock.countDown();
        });
    Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
  }

  @Test
  public void put500() throws Exception {
    client.getHttpRetrys().put500WithResponseAsync()
        .subscribe(response -> {
          Assert.assertEquals(200, response.getStatusCode());
          lock.countDown();
        });
    Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
  }

  @Test
  public void patch500() throws Exception {
    client.getHttpRetrys().patch500WithResponseAsync()
        .subscribe(response -> {
          Assert.assertEquals(200, response.getStatusCode());
          lock.countDown();
        });
    Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
  }

  @Test
  public void get502() throws Exception {
    client.getHttpRetrys().get502WithResponseAsync()
        .subscribe(response -> {
          Assert.assertEquals(200, response.getStatusCode());
          lock.countDown();
        });
    Assert.assertTrue(lock.await(50000, TimeUnit.MILLISECONDS));
  }

  @Test
  public void post503() throws Exception {
    client.getHttpRetrys().post503WithResponseAsync()
        .subscribe(response -> {
          Assert.assertEquals(200, response.getStatusCode());
          lock.countDown();
        });
    Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
  }

  @Test
  public void delete503() throws Exception {
    client.getHttpRetrys().delete503WithResponseAsync()
        .subscribe(response -> {
          Assert.assertEquals(200, response.getStatusCode());
          lock.countDown();
        });
    Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
  }

  @Test
  public void put504() throws Exception {
    client.getHttpRetrys().put504WithResponseAsync()
        .subscribe(response -> {
          Assert.assertEquals(200, response.getStatusCode());
          lock.countDown();
        });
    Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
  }

  @Test
  public void patch504() throws Exception {
    client.getHttpRetrys().patch504WithResponseAsync()
        .subscribe(response -> {
          Assert.assertEquals(200, response.getStatusCode());
          lock.countDown();
        });
    Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
  }
}
