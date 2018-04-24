package fixtures.custombaseuri;

import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.http.HttpPipelineBuilder;
import com.microsoft.rest.v2.policy.DecodingPolicyFactory;
import com.microsoft.rest.v2.policy.HttpLogDetailLevel;
import com.microsoft.rest.v2.policy.HttpLoggingPolicyFactory;
import com.microsoft.rest.v2.policy.TimeoutPolicyFactory;
import fixtures.custombaseuri.implementation.AutoRestParameterizedHostTestClientImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class CustomBaseUriTests {
    private static AutoRestParameterizedHostTestClient client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestParameterizedHostTestClientImpl(
                new HttpPipelineBuilder()
                    .withTimeoutPolicy(1, TimeUnit.SECONDS)
                    .withRetryPolicy(0, 0, TimeUnit.MILLISECONDS)
                    .withDecodingPolicy()
                    .build());
    }

    // Positive test case
    @Test
    public void getEmptyWithValidCustomUri() throws Exception {
        client.withHost("host:3000");
        client.paths().getEmpty("local");
    }

    @Test
    public void getEmptyWithInvalidCustomUriAccountName() throws Exception {
        try {
            client.paths().getEmpty("bad");
            Assert.fail();
        }
        catch (RuntimeException e) {
        }
    }

    @Test
    public void getEmptyWithInvalidCustomUriHostName() throws Exception {
        try {
            client.withHost("badhost");
            client.paths().getEmpty("local");
            Assert.fail();
        }
        catch (RuntimeException e) {
        }
        finally {
            client.withHost("host:3000");
        }
    }

    @Test
    public void getEmptyWithEmptyCustomUriAccountName() throws Exception {
        try {
            client.paths().getEmpty(null);
            Assert.assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void getGoodBadGood() throws Exception {
        // Short repro for a Windows-specific error where failing to open
        // a connection to badhost would cause the connection
        // to localhost:3000 to be closed.
        // For now, we're working around it by retrying.
        try {
            client.withHost("host:3000").paths().getEmpty("local");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        try {
            client.withHost("badhost").paths().getEmpty("local");
            Assert.fail();
        } catch (Exception ignored){
        }
        try {
            client.withHost("host:3000").paths().getEmpty("local");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getEmptyMultipleThreads() throws Exception {
        CountDownLatch latch = new CountDownLatch(2);
        AutoRestParameterizedHostTestClient client1 = new AutoRestParameterizedHostTestClientImpl(
                new HttpPipelineBuilder()
                    .withTimeoutPolicy(1, TimeUnit.SECONDS)
                    .withDecodingPolicy()
                    .build());
        client1.withHost("host:3000");
        Thread t1 = new Thread(() -> {
            try {
                client1.paths().getEmpty("badlocal");
                fail();
            } catch (RuntimeException e) {
                latch.countDown();
            } catch (Exception e) {
                fail();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                client1.paths().getEmpty("local");
                latch.countDown();
            } catch (Exception ex) {
                fail();
            }
        });
        t1.start();
        t2.start();
        Assert.assertTrue(latch.await(15, TimeUnit.SECONDS));
    }
}
