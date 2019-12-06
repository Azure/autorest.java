package fixtures.custombaseuri;

import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.FixedDelay;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.TimeoutPolicy;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
        clientBuilder.host("host:3000").accountName("local");
        clientBuilder.build().paths().getEmpty();
    }

    @Test
    public void getEmptyWithInvalidCustomUriAccountName() throws Exception {
        try {
            clientBuilder.accountName("bad").build().paths().getEmpty();
            Assert.fail();
        }
        catch (RuntimeException e) {
        }
    }

    @Test
    public void getEmptyWithInvalidCustomUriHostName() throws Exception {
        try {
            clientBuilder.host("badhost").accountName("local");
            clientBuilder.build().paths().getEmpty();
            Assert.fail();
        }
        catch (RuntimeException e) {
        }
        finally {
            clientBuilder.host("host:3000");
        }
    }

    @Ignore("required-optional")
    public void getEmptyWithEmptyCustomUriAccountName() throws Exception {
        try {
            clientBuilder.build().paths().getEmpty();
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
            clientBuilder.host("host:3000").accountName("local").build().paths().getEmpty();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        try {
            clientBuilder.host("badhost").accountName("local").build().paths().getEmpty();
            Assert.fail();
        } catch (Exception ignored){
        }
        try {
            clientBuilder.host("host:3000").accountName("local").build().paths().getEmpty();
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
                client1.host("host:3000").accountName("badlocal").build()
                        .paths().getEmpty();
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
                client1.host("host:3000").accountName("local").build()
                        .paths().getEmpty();
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
