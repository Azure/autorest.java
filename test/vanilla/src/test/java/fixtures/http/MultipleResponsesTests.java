package fixtures.http;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.HttpPipelinePolicy;
import fixtures.http.implementation.AutoRestHttpInfrastructureTestServiceImpl;
import fixtures.http.models.Error;
import fixtures.http.models.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class MultipleResponsesTests {
    private static AutoRestHttpInfrastructureTestServiceImpl client;
    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeClass
    public static void setup() {
        client = new AutoRestHttpInfrastructureTestServiceImpl();
    }

    @Test
    public void get200Model204NoModelDefaultError200Valid() throws Exception {
        A result = client.multipleResponses().get200Model204NoModelDefaultError200Valid();
        Assert.assertEquals(A.class, result.getClass());
        Assert.assertEquals("200", result.statusCode());
    }

    @Test
    public void get200Model204NoModelDefaultError204Valid() throws Exception {
        A result = client.multipleResponses().get200Model204NoModelDefaultError204Valid();
        Assert.assertNull(result);
    }

    @Test
    public void get200Model204NoModelDefaultError201Invalid() throws Exception {
        try {
            A result = client.multipleResponses().get200Model204NoModelDefaultError201Invalid();
            fail();
        } catch (ErrorException ex) {
            Assert.assertEquals(201, ex.response().statusCode());
        }
    }

    @Test
    public void get200Model204NoModelDefaultError202None() throws Exception {
        try {
            client.multipleResponses().get200Model204NoModelDefaultError202None();
        } catch (ErrorException ex) {
            Assert.assertEquals(202, ex.response().statusCode());
        }
    }

    @Test
    public void get200Model204NoModelDefaultError400Valid() throws Exception {
        try {
            client.multipleResponses().get200Model204NoModelDefaultError400Valid();
            fail();
        } catch (ErrorException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void get200Model201ModelDefaultError200Valid() throws Exception {
        A result = client.multipleResponses().get200Model201ModelDefaultError200Valid();
        Assert.assertEquals("200", result.statusCode());
    }

    @Test
    public void get200Model201ModelDefaultError201Valid() throws Exception {
        A result = client.multipleResponses().get200Model201ModelDefaultError201Valid();
        Assert.assertEquals("201", result.statusCode());
    }

    @Test
    public void get200Model201ModelDefaultError400Valid() throws Exception {
        try {
            client.multipleResponses().get200Model201ModelDefaultError400Valid();
            fail();
        } catch (ErrorException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
            Assert.assertEquals(400, ex.value().status().intValue());
            Assert.assertEquals("client error", ex.value().message());
        }
    }

    @Test
    @Ignore("Not currently supported by RestProxy")
    public void get200ModelA201ModelC404ModelDDefaultError200Valid() throws Exception {
        Object result = client.multipleResponses().get200ModelA201ModelC404ModelDDefaultError200Valid();
        A actual = (A) result;
        Assert.assertEquals("200", actual.statusCode());
    }

    @Test
    @Ignore("Not currently supported by RestProxy")
    public void get200ModelA201ModelC404ModelDDefaultError201Valid() throws Exception {
        Object result = client.multipleResponses().get200ModelA201ModelC404ModelDDefaultError201Valid();
        C actual = (C) result;
        Assert.assertEquals("201", actual.httpCode());
    }

    @Test
    @Ignore("Not currently supported by RestProxy")
    public void get200ModelA201ModelC404ModelDDefaultError404Valid() throws Exception {
        Object result = client.multipleResponses().get200ModelA201ModelC404ModelDDefaultError404Valid();
        D actual = (D) result;
        Assert.assertEquals("404", actual.httpStatusCode());
    }

    @Test
    public void get200ModelA201ModelC404ModelDDefaultError400Valid() throws Exception {
        try {
            client.multipleResponses().get200ModelA201ModelC404ModelDDefaultError400Valid();
            fail();
        } catch (ErrorException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
            Error model = ex.value();
            Assert.assertEquals(400, model.status().intValue());
            Assert.assertEquals("client error", model.message());
        }
    }

    @Test
    public void get202None204NoneDefaultError202None() throws Exception {
        HttpPipeline httpPipeline = new HttpPipelineBuilder().policies(
                (HttpPipelinePolicy) (context, next) -> next.process()
                        .doOnSuccess(httpResponse -> {
                            Assert.assertEquals(202, httpResponse.statusCode());
                            lock.countDown();
                        })
                        .doOnError(throwable -> {
                            fail(throwable.getMessage());
                        })).build();

        AutoRestHttpInfrastructureTestServiceImpl localClient = new AutoRestHttpInfrastructureTestServiceImpl(httpPipeline);
        localClient.multipleResponses().get202None204NoneDefaultError202NoneAsync().subscribe();
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void get202None204NoneDefaultError204None() throws Exception {
        HttpPipeline httpPipeline = new HttpPipelineBuilder().policies(
                (HttpPipelinePolicy) (context, next) -> next.process()
                        .doOnSuccess(httpResponse -> {
                            Assert.assertEquals(204, httpResponse.statusCode());
                            lock.countDown();
                        })
                        .doOnError(throwable -> {
                            Assert.fail(throwable.getMessage());
                        })).build();

        AutoRestHttpInfrastructureTestServiceImpl localClient = new AutoRestHttpInfrastructureTestServiceImpl(httpPipeline);
        localClient.multipleResponses().get202None204NoneDefaultError204NoneAsync().subscribe();
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void get202None204NoneDefaultError400Valid() throws Exception {
        try {
            client.multipleResponses().get202None204NoneDefaultError400Valid();
            fail();
        } catch (ErrorException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
            Error model = ex.value();
            Assert.assertEquals(400, model.status().intValue());
            Assert.assertEquals("client error", model.message());
        }
    }

    @Test
    public void get202None204NoneDefaultNone202Invalid() throws Exception {
        client.multipleResponses().get202None204NoneDefaultNone202Invalid();
    }

    @Test
    public void get202None204NoneDefaultNone204None() throws Exception {
        client.multipleResponses().get202None204NoneDefaultNone204None();
    }

    @Test
    public void get202None204NoneDefaultNone400None() throws Exception {
        try {
            client.multipleResponses().get202None204NoneDefaultNone400None();
            fail();
        } catch (HttpResponseException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void get202None204NoneDefaultNone400Invalid() throws Exception {
        try {
            client.multipleResponses().get202None204NoneDefaultNone400Invalid();
            fail();
        } catch (HttpResponseException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void getDefaultModelA200Valid() throws Exception {
        A result = client.multipleResponses().getDefaultModelA200Valid();
        Assert.assertEquals("200", result.statusCode());
    }

    @Test
    public void getDefaultModelA200None() throws Exception {
        A result = client.multipleResponses().getDefaultModelA200None();
        Assert.assertNull(result);
    }

    @Test
    public void getDefaultModelA400Valid() throws Exception {
        try {
            client.multipleResponses().getDefaultModelA400Valid();
            fail();
        } catch (AException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
            Assert.assertEquals("400", ex.value().statusCode());
        }
    }

    @Test
    public void getDefaultModelA400None() throws Exception {
        try {
            client.multipleResponses().getDefaultModelA400None();
            fail();
        } catch (AException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void getDefaultNone200Invalid() throws Exception {
        client.multipleResponses().getDefaultNone200Invalid();
    }

    @Test
    public void getDefaultNone200None() throws Exception {
        client.multipleResponses().getDefaultNone200None();
    }

    @Test
    public void getDefaultNone400Invalid() throws Exception {
        try {
            client.multipleResponses().getDefaultNone400Invalid();
            fail();
        } catch (HttpResponseException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void getDefaultNone400None() throws Exception {
        try {
            client.multipleResponses().getDefaultNone400None();
            fail();
        } catch (HttpResponseException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void get200ModelA200None() throws Exception {
        A result = client.multipleResponses().get200ModelA200None();
        Assert.assertNull(result);
    }

    @Test
    public void get200ModelA200Valid() throws Exception {
        A result = client.multipleResponses().get200ModelA200Valid();
        Assert.assertEquals("200", result.statusCode());
    }

    @Test
    public void get200ModelA200Invalid() throws Exception {
        Assert.assertEquals(null, client.multipleResponses().get200ModelA200Invalid().statusCode());
    }

    @Test
    public void get200ModelA400None() throws Exception {
        try {
            client.multipleResponses().get200ModelA400None();
            fail();
        } catch (HttpResponseException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
            Assert.assertNull(ex.value());
        }
    }

    @Test
    public void get200ModelA400Valid() throws Exception {
        try {
            client.multipleResponses().get200ModelA400Valid();
            fail();
        } catch (HttpResponseException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void get200ModelA400Invalid() throws Exception {
        try {
            client.multipleResponses().get200ModelA400Invalid();
            fail();
        } catch (HttpResponseException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void get200ModelA202Valid() throws Exception {
        try {
            client.multipleResponses().get200ModelA202Valid();
            fail();
        } catch (HttpResponseException ex) {
            Assert.assertEquals(202, ex.response().statusCode());
        }
    }
}

