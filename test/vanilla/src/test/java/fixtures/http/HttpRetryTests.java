package fixtures.http;

import com.microsoft.rest.v3.http.HttpPipeline;
import com.microsoft.rest.v3.policy.CookiePolicyFactory;
import com.microsoft.rest.v3.policy.DecodingPolicyFactory;
import com.microsoft.rest.v3.policy.RetryPolicyFactory;
import fixtures.http.implementation.AutoRestHttpInfrastructureTestServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class HttpRetryTests {
    private static AutoRestHttpInfrastructureTestService client;
    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeClass
    public static void setup() {
        client = new AutoRestHttpInfrastructureTestServiceImpl(HttpPipeline.build(
                new RetryPolicyFactory(),
                new CookiePolicyFactory(),
                new DecodingPolicyFactory()));
    }

    @Test
    public void head408() throws Exception {
        client.httpRetrys().head408WithRestResponseAsync()
            .subscribe(response -> {
                Assert.assertEquals(200, response.statusCode());
                lock.countDown();
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void put500() throws Exception {
        client.httpRetrys().put500WithRestResponseAsync(true)
            .subscribe(response -> {
                Assert.assertEquals(200, response.statusCode());
                lock.countDown();
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void patch500() throws Exception {
        client.httpRetrys().patch500WithRestResponseAsync(true)
            .subscribe(response -> {
                Assert.assertEquals(200, response.statusCode());
                lock.countDown();
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void get502() throws Exception {
        client.httpRetrys().get502WithRestResponseAsync()
            .subscribe(response -> {
                Assert.assertEquals(200, response.statusCode());
                lock.countDown();
            });
        Assert.assertTrue(lock.await(50000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void post503() throws Exception {
        client.httpRetrys().post503WithRestResponseAsync(true)
            .subscribe(response -> {
                Assert.assertEquals(200, response.statusCode());
                lock.countDown();
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void delete503() throws Exception {
        client.httpRetrys().delete503WithRestResponseAsync(true)
            .subscribe(response -> {
                Assert.assertEquals(200, response.statusCode());
                lock.countDown();
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void put504() throws Exception {
        client.httpRetrys().put504WithRestResponseAsync(true)
            .subscribe(response -> {
                Assert.assertEquals(200, response.statusCode());
                lock.countDown();
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void patch504() throws Exception {
        client.httpRetrys().patch504WithRestResponseAsync(true)
            .subscribe(response -> {
                Assert.assertEquals(200, response.statusCode());
                lock.countDown();
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }
}
