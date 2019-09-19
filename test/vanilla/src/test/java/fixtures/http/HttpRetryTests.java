package fixtures.http;

import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
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
        client = new AutoRestHttpInfrastructureTestServiceImpl(new HttpPipelineBuilder().policies(
                new RetryPolicy(),
                new CookiePolicy()).build());
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
