package fixtures.httpinfrastructure;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.HttpPipelinePolicy;
import fixtures.httpinfrastructure.models.C;
import fixtures.httpinfrastructure.models.D;
import fixtures.httpinfrastructure.models.Error;
import fixtures.httpinfrastructure.models.ErrorException;
import fixtures.httpinfrastructure.models.MyException;
import fixtures.httpinfrastructure.models.MyExceptionException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class MultipleResponsesTests {
  private static AutoRestHttpInfrastructureTestService client;
  private CountDownLatch lock = new CountDownLatch(1);

  @BeforeClass
  public static void setup() {
    client = new AutoRestHttpInfrastructureTestServiceBuilder().buildClient();
  }

  @Test
  public void get200Model204NoModelDefaultError200Valid() throws Exception {
    MyException result = client.getMultipleResponses().get200Model204NoModelDefaultError200Valid();
    Assert.assertEquals(MyException.class, result.getClass());
    Assert.assertEquals("200", result.getStatusCode());
  }

  @Test
  public void get200Model204NoModelDefaultError204Valid() throws Exception {
    MyException result = client.getMultipleResponses().get200Model204NoModelDefaultError204Valid();
    Assert.assertNull(result);
  }

  @Test
  public void get200Model204NoModelDefaultError201Invalid() throws Exception {
    try {
      MyException result = client.getMultipleResponses().get200Model204NoModelDefaultError201Invalid();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(201, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get200Model204NoModelDefaultError202None() throws Exception {
    try {
      client.getMultipleResponses().get200Model204NoModelDefaultError202None();
    } catch (ErrorException ex) {
      Assert.assertEquals(202, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get200Model204NoModelDefaultError400Valid() throws Exception {
    try {
      client.getMultipleResponses().get200Model204NoModelDefaultError400Valid();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get200Model201ModelDefaultError200Valid() throws Exception {
    MyException result = client.getMultipleResponses().get200Model201ModelDefaultError200Valid();
    Assert.assertEquals("200", result.getStatusCode());
  }

  @Test
  public void get200Model201ModelDefaultError201Valid() throws Exception {
    MyException result = client.getMultipleResponses().get200Model201ModelDefaultError201Valid();
    Assert.assertEquals("201", result.getStatusCode());
  }

  @Test
  public void get200Model201ModelDefaultError400Valid() throws Exception {
    try {
      client.getMultipleResponses().get200Model201ModelDefaultError400Valid();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
      Assert.assertEquals(400, ex.getValue().getStatus().intValue());
      Assert.assertEquals("client error", ex.getValue().getMessage());
    }
  }

  @Test
  @Ignore("Not currently supported by RestProxy")
  public void get200ModelA201ModelC404ModelDDefaultError200Valid() throws Exception {
    Object result = client.getMultipleResponses().get200ModelA201ModelC404ModelDDefaultError200Valid();
    MyException actual = (MyException) result;
    Assert.assertEquals("200", actual.getStatusCode());
  }

  @Test
  @Ignore("Not currently supported by RestProxy")
  public void get200ModelA201ModelC404ModelDDefaultError201Valid() throws Exception {
    Object result = client.getMultipleResponses().get200ModelA201ModelC404ModelDDefaultError201Valid();
    C actual = (C) result;
    Assert.assertEquals("201", actual.getHttpCode());
  }

  @Test
  @Ignore("Not currently supported by RestProxy")
  public void get200ModelA201ModelC404ModelDDefaultError404Valid() throws Exception {
    Object result = client.getMultipleResponses().get200ModelA201ModelC404ModelDDefaultError404Valid();
    D actual = (D) result;
    Assert.assertEquals("404", actual.getHttpStatusCode());
  }

  @Test
  public void get200ModelA201ModelC404ModelDDefaultError400Valid() throws Exception {
    try {
      client.getMultipleResponses().get200ModelA201ModelC404ModelDDefaultError400Valid();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
      Error model = ex.getValue();
      Assert.assertEquals(400, model.getStatus().intValue());
      Assert.assertEquals("client error", model.getMessage());
    }
  }

  @Test
  public void get202None204NoneDefaultError202None() throws Exception {
    HttpPipeline httpPipeline = new HttpPipelineBuilder().policies(
        (HttpPipelinePolicy) (context, next) -> next.process()
            .doOnSuccess(httpResponse -> {
              Assert.assertEquals(202, httpResponse.getStatusCode());
              lock.countDown();
            })
            .doOnError(throwable -> {
              fail(throwable.getMessage());
            })).build();

    AutoRestHttpInfrastructureTestService localClient =
        new AutoRestHttpInfrastructureTestServiceBuilder().pipeline(httpPipeline).buildClient();
    localClient.getMultipleResponses().get202None204NoneDefaultError202NoneAsync().subscribe();
    Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
  }

  @Test
  public void get202None204NoneDefaultError204None() throws Exception {
    HttpPipeline httpPipeline = new HttpPipelineBuilder().policies(
        (HttpPipelinePolicy) (context, next) -> next.process()
            .doOnSuccess(httpResponse -> {
              Assert.assertEquals(204, httpResponse.getStatusCode());
              lock.countDown();
            })
            .doOnError(throwable -> {
              Assert.fail(throwable.getMessage());
            })).build();

    AutoRestHttpInfrastructureTestService localClient =
        new AutoRestHttpInfrastructureTestServiceBuilder().pipeline(httpPipeline).buildClient();
    localClient.getMultipleResponses().get202None204NoneDefaultError204NoneAsync().subscribe();
    Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
  }

  @Test
  public void get202None204NoneDefaultError400Valid() throws Exception {
    try {
      client.getMultipleResponses().get202None204NoneDefaultError400Valid();
      fail();
    } catch (ErrorException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
      Error model = ex.getValue();
      Assert.assertEquals(400, model.getStatus().intValue());
      Assert.assertEquals("client error", model.getMessage());
    }
  }

  @Test
  public void get202None204NoneDefaultNone202Invalid() throws Exception {
    client.getMultipleResponses().get202None204NoneDefaultNone202Invalid();
  }

  @Test
  public void get202None204NoneDefaultNone204None() throws Exception {
    client.getMultipleResponses().get202None204NoneDefaultNone204None();
  }

  @Test
  public void get202None204NoneDefaultNone400None() throws Exception {
    try {
      client.getMultipleResponses().get202None204NoneDefaultNone400None();
      fail();
    } catch (HttpResponseException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get202None204NoneDefaultNone400Invalid() throws Exception {
    try {
      client.getMultipleResponses().get202None204NoneDefaultNone400Invalid();
      fail();
    } catch (HttpResponseException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void getDefaultModelA200Valid() throws Exception {
    client.getMultipleResponses().getDefaultModelA200Valid();
  }

  @Test
  public void getDefaultModelA200None() throws Exception {
    client.getMultipleResponses().getDefaultModelA200None();
  }

  @Test
  public void getDefaultModelA400Valid() throws Exception {
    try {
      client.getMultipleResponses().getDefaultModelA400Valid();
      fail();
    } catch (MyExceptionException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
      Assert.assertEquals("400", ex.getValue().getStatusCode());
    }
  }

  @Test
  public void getDefaultModelA400None() throws Exception {
    try {
      client.getMultipleResponses().getDefaultModelA400None();
      fail();
    } catch (MyExceptionException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void getDefaultNone200Invalid() throws Exception {
    client.getMultipleResponses().getDefaultNone200Invalid();
  }

  @Test
  public void getDefaultNone200None() throws Exception {
    client.getMultipleResponses().getDefaultNone200None();
  }

  @Test
  public void getDefaultNone400Invalid() throws Exception {
    try {
      client.getMultipleResponses().getDefaultNone400Invalid();
      fail();
    } catch (HttpResponseException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void getDefaultNone400None() throws Exception {
    try {
      client.getMultipleResponses().getDefaultNone400None();
      fail();
    } catch (HttpResponseException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get200ModelA200None() throws Exception {
    MyException result = client.getMultipleResponses().get200ModelA200None();
    Assert.assertNull(result);
  }

  @Test
  public void get200ModelA200Valid() throws Exception {
    MyException result = client.getMultipleResponses().get200ModelA200Valid();
    Assert.assertEquals("200", result.getStatusCode());
  }

  @Test
  public void get200ModelA200Invalid() throws Exception {
    Assert.assertEquals(null, client.getMultipleResponses().get200ModelA200Invalid().getStatusCode());
  }

  @Test
  public void get200ModelA400None() throws Exception {
    try {
      client.getMultipleResponses().get200ModelA400None();
      fail();
    } catch (HttpResponseException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
      Assert.assertNull(ex.getValue());
    }
  }

  @Test
  public void get200ModelA400Valid() throws Exception {
    try {
      client.getMultipleResponses().get200ModelA400Valid();
      fail();
    } catch (HttpResponseException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get200ModelA400Invalid() throws Exception {
    try {
      client.getMultipleResponses().get200ModelA400Invalid();
      fail();
    } catch (HttpResponseException ex) {
      Assert.assertEquals(400, ex.getResponse().getStatusCode());
    }
  }

  @Test
  public void get200ModelA202Valid() throws Exception {
    try {
      client.getMultipleResponses().get200ModelA202Valid();
      fail();
    } catch (HttpResponseException ex) {
      Assert.assertEquals(202, ex.getResponse().getStatusCode());
    }
  }
}
