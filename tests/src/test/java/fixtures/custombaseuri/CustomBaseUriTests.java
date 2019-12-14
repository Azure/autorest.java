package fixtures.custombaseuri;

import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.FixedDelay;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.TimeoutPolicy;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class CustomBaseUriTests {
    private static AutoRestParameterizedHostTestClientBuilder clientBuilder;

    @BeforeClass
    public static void setup() {
        clientBuilder = new AutoRestParameterizedHostTestClientBuilder().pipeline(
                new HttpPipelineBuilder().policies(
                        new TimeoutPolicy(Duration.ofSeconds(1)),
                        new RetryPolicy(new FixedDelay(0, Duration.ZERO)))
                        .build());
    }

    // Positive test case
    @Test
    public void getEmptyWithValidCustomUri() throws Exception {
        clientBuilder.build().paths().getEmpty("local", "host:3000");
    }

    @Test
    public void getEmptyWithInvalidCustomUriAccountName() throws Exception {
        try {
            clientBuilder.build().paths().getEmpty("bad", "host:3000");
            Assert.fail();
        }
        catch (RuntimeException e) {
        }
    }

    @Test
    public void getEmptyWithInvalidCustomUriHostName() throws Exception {
        try {
            clientBuilder.build().paths().getEmpty("local", "badhost:3000");
            Assert.fail();
        }
        catch (RuntimeException e) {
        }
    }

    @Test
    public void getEmptyWithEmptyCustomUriAccountName() throws Exception {
        try {
            clientBuilder.build().paths().getEmpty(null, "");
            fail();
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
            clientBuilder.build().paths().getEmpty("local", "host:3000");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        try {
            clientBuilder.build().paths().getEmpty("local", "badhost");
            Assert.fail();
        } catch (Exception ignored){
        }
        try {
            clientBuilder.build().paths().getEmpty("local", "host:3000");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getEmptyMultipleThreads() throws Exception {
        CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(() -> {
            try {
                AutoRestParameterizedHostTestClientBuilder client1 = new AutoRestParameterizedHostTestClientBuilder().pipeline(
                        new HttpPipelineBuilder().policies(
                                new TimeoutPolicy(Duration.ofSeconds(1)))
                                .build());
                client1.build().paths().getEmpty("badlocal", "host:3000");
                fail();
            } catch (RuntimeException e) {
                latch.countDown();
            } catch (Exception e) {
                fail();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                AutoRestParameterizedHostTestClientBuilder client1 = new AutoRestParameterizedHostTestClientBuilder().pipeline(
                        new HttpPipelineBuilder().policies(
                                new TimeoutPolicy(Duration.ofSeconds(1)))
                                .build());
                client1.build().paths().getEmpty("local", "host:3000");
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
