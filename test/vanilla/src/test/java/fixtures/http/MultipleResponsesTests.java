package fixtures.http;

import com.microsoft.rest.v2.LogLevel;
import com.microsoft.rest.v2.RestException;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.http.HttpRequest;
import com.microsoft.rest.v2.http.HttpResponse;
import com.microsoft.rest.v2.policy.*;
import fixtures.http.implementation.AutoRestHttpInfrastructureTestServiceImpl;
import fixtures.http.models.A;
import fixtures.http.models.C;
import fixtures.http.models.D;
import fixtures.http.models.Error;
import fixtures.http.models.ErrorException;
import fixtures.http.models.MyException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class MultipleResponsesTests {
    private static AutoRestHttpInfrastructureTestServiceImpl client;
    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeClass
    public static void setup() {
        client = new AutoRestHttpInfrastructureTestServiceImpl(HttpPipeline.build(
                new ProtocolPolicy.Factory("http"),
                new PortPolicy.Factory(3000)));
    }

    @Test
    public void get200Model204NoModelDefaultError200Valid() throws Exception {
        A result = client.multipleResponses().get200Model204NoModelDefaultError200Valid();
        Assert.assertEquals(A.class, result.getClass());
        Assert.assertEquals("200", result.statusCode());
    }

    @Test
    public void get200Model204NoModelDefaultError204Valid() throws Exception {
        A result = client.multipleResponses().get200Model204NoModelDefaultError204ValidWithRestResponseAsync().blockingGet().body();
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
            Assert.assertEquals(400, ex.body().status().intValue());
            Assert.assertEquals("client error", ex.body().message());
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
            Error model = ex.body();
            Assert.assertEquals(400, model.status().intValue());
            Assert.assertEquals("client error", model.message());
        }
    }

    @Test
    public void get202None204NoneDefaultError202None() throws Exception {
        HttpPipeline httpPipeline = HttpPipeline.build(
                new ProtocolPolicy.Factory("http"),
                new PortPolicy.Factory(3000),
                new RequestPolicy.Factory() {
                @Override
                public RequestPolicy create(final RequestPolicy next, RequestPolicy.Options options) {
                    return new RequestPolicy() {
                        @Override
                        public Single<HttpResponse> sendAsync(HttpRequest request) {
                            return next.sendAsync(request)
                                    .doOnSuccess(new Consumer<HttpResponse>() {
                                        @Override
                                        public void accept(HttpResponse httpResponse) {
                                            Assert.assertEquals(202, httpResponse.statusCode());
                                            lock.countDown();
                                        }
                                    })
                                    .doOnError(new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) {
                                            Assert.fail(throwable.getMessage());
                                        }
                                    });
                        }
                    };
                }
            });

        AutoRestHttpInfrastructureTestServiceImpl localClient = new AutoRestHttpInfrastructureTestServiceImpl(httpPipeline);
        localClient.multipleResponses().get202None204NoneDefaultError202NoneAsync().subscribe();
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void get202None204NoneDefaultError204None() throws Exception {
        HttpPipeline httpPipeline = HttpPipeline.build(
                new ProtocolPolicy.Factory("http"),
                new PortPolicy.Factory(3000),
                new RequestPolicy.Factory() {
                @Override
                public RequestPolicy create(final RequestPolicy next, RequestPolicy.Options options) {
                    return new RequestPolicy() {
                        @Override
                        public Single<HttpResponse> sendAsync(HttpRequest request) {
                            return next.sendAsync(request)
                                    .doOnSuccess(new Consumer<HttpResponse>() {
                                        @Override
                                        public void accept(HttpResponse httpResponse) {
                                            Assert.assertEquals(204, httpResponse.statusCode());
                                            lock.countDown();
                                        }
                                    })
                                    .doOnError(new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) {
                                            Assert.fail(throwable.getMessage());
                                        }
                                    });
                        }
                    };
                }
            });

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
            Error model = ex.body();
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
        } catch (RestException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void get202None204NoneDefaultNone400Invalid() throws Exception {
        try {
            client.multipleResponses().get202None204NoneDefaultNone400Invalid();
            fail();
        } catch (RestException ex) {
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
        A result = client.multipleResponses().getDefaultModelA200NoneWithRestResponseAsync().blockingGet().body();
        Assert.assertNull(result);
    }

    @Test
    public void getDefaultModelA400Valid() throws Exception {
        try {
            client.multipleResponses().getDefaultModelA400Valid();
            fail();
        } catch (MyException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
            Assert.assertEquals("400", ex.body().statusCode());
        }
    }

    @Test
    public void getDefaultModelA400None() throws Exception {
        try {
            client.multipleResponses().getDefaultModelA400None();
            fail();
        } catch (MyException ex) {
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
        } catch (RestException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void getDefaultNone400None() throws Exception {
        try {
            client.multipleResponses().getDefaultNone400None();
            fail();
        } catch (RestException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void get200ModelA200None() throws Exception {
        A result = client.multipleResponses().get200ModelA200NoneWithRestResponseAsync().blockingGet().body();
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
        } catch (RestException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
            Assert.assertNull(ex.body());
        }
    }

    @Test
    public void get200ModelA400Valid() throws Exception {
        try {
            client.multipleResponses().get200ModelA400Valid();
            fail();
        } catch (RestException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void get200ModelA400Invalid() throws Exception {
        try {
            client.multipleResponses().get200ModelA400Invalid();
            fail();
        } catch (RestException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void get200ModelA202Valid() throws Exception {
        try {
            client.multipleResponses().get200ModelA202Valid();
            fail();
        } catch (RestException ex) {
            Assert.assertEquals(202, ex.response().statusCode());
        }
    }
}

