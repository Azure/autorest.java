package fixtures.bodyinteger;

import com.fasterxml.jackson.core.JsonParseException;
import com.microsoft.rest.v2.ServiceCallback;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import fixtures.bodyinteger.implementation.AutoRestIntegerTestServiceImpl;
import io.reactivex.Observable;

import static org.junit.Assert.fail;

public class IntOperationsTests {
    private static AutoRestIntegerTestService client;
    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeClass
    public static void setup() {
        client = new AutoRestIntegerTestServiceImpl(HttpPipeline.build(new PortPolicyFactory(3000)));
    }

    @Test
    public void getNull() throws Exception {
        try {
            int i = client.ints().getNull();
            fail();
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void getNullAsync() throws Exception {
        Observable.fromFuture(client.ints().getNullAsync(null)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {}

            @Override
            public void onNext(Integer integerServiceResponse) {
                System.out.println(integerServiceResponse);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error" + e);
            }

            @Override
            public void onComplete() {
                System.out.println("completed");
            }
        });
        System.out.println("checkpoint");
    }

    @Test
    public void getInvalid() throws Exception {
        try {
            client.ints().getInvalid();
            Assert.assertTrue(false);
        } catch (Exception exception) {
            Assert.assertEquals(JsonParseException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getOverflowInt32() throws Exception {
        try {
            client.ints().getOverflowInt32();
            Assert.assertTrue(false);
        } catch (Exception exception) {
            Assert.assertEquals(JsonParseException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getUnderflowInt32() throws Exception {
        try {
            client.ints().getUnderflowInt32();
            Assert.assertTrue(false);
        } catch (Exception exception) {
            Assert.assertEquals(JsonParseException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getOverflowInt64() throws Exception {
        try {
            long value = client.ints().getOverflowInt64();
            Assert.assertEquals(Long.MAX_VALUE, value);
        } catch (Exception exception) {
            Assert.assertEquals(JsonParseException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void getUnderflowInt64() throws Exception {
        try {
            long value = client.ints().getUnderflowInt64();
            Assert.assertEquals(Long.MIN_VALUE, value);
        } catch (Exception exception) {
            Assert.assertEquals(JsonParseException.class, exception.getCause().getClass());
        }
    }

    @Test
    public void putMax32() throws Exception {
        client.ints().putMax32Async(Integer.MAX_VALUE, new ServiceCallback<Void>() {
            @Override
            public void failure(Throwable t) {
            }

            @Override
            public void success(Void response) {
                lock.countDown();
            }
        });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void putMax64() throws Exception {
        client.ints().putMax64Async(Long.MAX_VALUE, new ServiceCallback<Void>() {
            @Override
            public void failure(Throwable t) {
            }

            @Override
            public void success(Void response) {
                lock.countDown();
            }
        });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void putMin32() throws Exception {
        client.ints().putMin32Async(Integer.MIN_VALUE, new ServiceCallback<Void>() {
            @Override
            public void failure(Throwable t) {
            }

            @Override
            public void success(Void response) {
                lock.countDown();
            }
        });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void putMin64() throws Exception {
        client.ints().putMin64Async(Long.MIN_VALUE, new ServiceCallback<Void>() {
            @Override
            public void failure(Throwable t) {
            }

            @Override
            public void success(Void response) {
                lock.countDown();
            }
        });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void getUnixTime() throws Exception {
        DateTime result = client.ints().getUnixTime();
        Assert.assertEquals(new DateTime(2016, 4, 13, 0, 0, 0, DateTimeZone.UTC), result);
    }

    @Test
    public void putUnixTimeDate() throws Exception {
        client.ints().putUnixTimeDate(new DateTime(2016, 4, 13, 0, 0, 0, DateTimeZone.UTC));
    }

    @Test
    public void getInvalidUnixTime() throws Exception {
        try {
            client.ints().getInvalidUnixTime();
            fail();
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("Unexpected character"));
        }
    }

    @Test
    public void getNullUnixTime() throws Exception {
        DateTime result = client.ints().getNullUnixTime();
        Assert.assertNull(result);
    }
}
