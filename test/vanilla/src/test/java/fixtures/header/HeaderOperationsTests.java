package fixtures.header;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.*;
import com.azure.core.implementation.util.Base64Util;
import fixtures.header.implementation.AutoRestSwaggerBATHeaderServiceImpl;
import fixtures.header.models.GreyscaleColors;
import fixtures.header.models.HeadersResponseBoolResponse;
import fixtures.header.models.HeadersResponseDatetimeResponse;
import fixtures.header.models.HeadersResponseDatetimeRfc1123Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.charset.Charset;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class HeaderOperationsTests {
    private static AutoRestSwaggerBATHeaderService client;
    private CountDownLatch lock;

    @BeforeClass
    public static void setup() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("x-ms-client-request-id", "9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");

        HttpPipeline httpPipeline = new HttpPipelineBuilder().policies(
                new UserAgentPolicy(""),
                new AddHeadersPolicy(headers),
                new ProtocolPolicy("http", true),
                new PortPolicy(3000, true),
                new RetryPolicy(3, Duration.ZERO))
                .build();

        client = new AutoRestSwaggerBATHeaderServiceImpl(httpPipeline);
    }

    @Test
    public void paramExistingKey() {
        client.headers().paramExistingKey("overwrite");
    }

    @Test
    public void responseExistingKey() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseExistingKeyWithRestResponseAsync()
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("User-Agent") != null) {
                    Assert.assertEquals("overwrite", headers.get("User-Agent"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramProtectedKey() {
        try {
            client.headers().paramProtectedKey("text/html");
        } catch (RuntimeException ex) {
            // OkHttp can actually overwrite header "Content-Type"
        }
    }

    @Test
    public void responseProtectedKey() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseProtectedKeyWithRestResponseAsync()
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("Content-Type") != null) {
                    Assert.assertTrue(headers.get("Content-Type").contains("text/html"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramInteger() {
        client.headers().paramInteger("positive", 1);
        client.headers().paramInteger("negative", -2);
    }

    @Test
    public void responseInteger() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseIntegerWithRestResponseAsync("positive")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("1", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(10000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseIntegerWithRestResponseAsync("negative")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("-2", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(10000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramLong() {
        client.headers().paramLong("positive", 105);
        client.headers().paramLong("negative", -2);
    }

    @Test
    public void responseLong() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseLongWithRestResponseAsync("positive")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("105", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseLongWithRestResponseAsync("negative")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("-2", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramFloat() {
        client.headers().paramFloat("positive", 0.07);
        client.headers().paramFloat("negative", -3.0);
    }

    @Test
    public void responseFloat() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseFloatWithRestResponseAsync("positive")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("0.07", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseFloatWithRestResponseAsync("negative")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("-3", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramDouble() {
        client.headers().paramDouble("positive", 7e120);
        client.headers().paramDouble("negative", -3.0);
    }

    @Test
    public void responseDouble() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseDoubleWithRestResponseAsync("positive")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("7e+120", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseDoubleWithRestResponseAsync("negative")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("-3", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramBool() {
        client.headers().paramBool("true", true);
        client.headers().paramBool("false", false);
    }

    @Test
    public void responseBool() throws Exception {
        HeadersResponseBoolResponse response = client.headers().responseBoolWithRestResponseAsync("true").block();
        Map<String, String> headers = response.headers().toMap();
        if (headers.get("value") != null) {
            Assert.assertEquals("true", headers.get("value"));
        }

        response = client.headers().responseBoolWithRestResponseAsync("false").block();
        headers = response.headers().toMap();
        if (headers.get("value") != null) {
            Assert.assertEquals("false", headers.get("value"));
        }
    }

    @Test
    public void paramString() {
        client.headers().paramString("valid", "The quick brown fox jumps over the lazy dog");
        client.headers().paramString("null", null);
        client.headers().paramString("empty", "");
    }

    @Test
    public void responseString() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseStringWithRestResponseAsync("valid")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("The quick brown fox jumps over the lazy dog", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseStringWithRestResponseAsync("null")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("null", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseStringWithRestResponseAsync("empty")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramDate() {
        client.headers().paramDate("valid", LocalDate.of(2010, 1, 1));
        client.headers().paramDate("min", LocalDate.of(1, 1, 1));
    }

    @Test
    public void responseDate() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseDateWithRestResponseAsync("valid")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("2010-01-01", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseDateWithRestResponseAsync("min")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("0001-01-01", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramDuration() {
        client.headers().paramDuration("valid", Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11));
    }

    @Test
    public void responseDuration() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseDurationWithRestResponseAsync("valid")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("P123DT22H14M12.011S", headers.get("value"));
                    lock.countDown();
                }
            }, (Throwable throwable) -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramDatetimeRfc1123() {
        client.headers().paramDatetimeRfc1123("valid", OffsetDateTime.of(2010, 1, 1, 12, 34, 56, 0, ZoneOffset.UTC));
        client.headers().paramDatetimeRfc1123("min", OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
    }

    @Test
    public void responseDatetimeRfc1123() throws Exception {
        HeadersResponseDatetimeRfc1123Response response = client.headers().responseDatetimeRfc1123WithRestResponseAsync("valid").block();
        Map<String, String> headers = response.headers().toMap();
        if (headers.get("value") != null) {
            Assert.assertEquals("Fri, 01 Jan 2010 12:34:56 GMT", headers.get("value"));
        }

        response = client.headers().responseDatetimeRfc1123WithRestResponseAsync("min").block();
        headers = response.headers().toMap();
        if (headers.get("value") != null) {
            Assert.assertEquals("Mon, 01 Jan 0001 00:00:00 GMT", headers.get("value"));
        }
    }

    @Test
    public void paramDatetime() {
        client.headers().paramDatetime("valid", OffsetDateTime.of(2010, 1, 1, 12, 34, 56, 0, ZoneOffset.UTC));
        client.headers().paramDatetime("min", OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
    }

    @Test
    public void responseDatetime() throws Exception {
        HeadersResponseDatetimeResponse response = client.headers().responseDatetimeWithRestResponseAsync("valid").block();
        Map<String, String> headers = response.headers().toMap();
        if (headers.get("value") != null) {
            Assert.assertEquals("2010-01-01T12:34:56Z", headers.get("value"));
        }
        response = client.headers().responseDatetimeWithRestResponseAsync("min").block();
        headers = response.headers().toMap();
        if (headers.get("value") != null) {
            Assert.assertEquals("0001-01-01T00:00:00Z", headers.get("value"));
        }
    }

    @Test
    public void paramByte() {
        client.headers().paramByte("valid", "啊齄丂狛狜隣郎隣兀﨩".getBytes(Charset.forName("UTF-8")));
    }

    @Test
    public void responseByte() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseByteWithRestResponseAsync("valid")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    byte[] value = Base64Util.decodeString(headers.get("value"));
                    String actual = new String(value, Charset.forName("UTF-8"));
                    Assert.assertEquals("啊齄丂狛狜隣郎隣兀﨩", actual);
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramEnum() {
        client.headers().paramEnum("valid", GreyscaleColors.GREY);
        client.headers().paramEnum("null", null);
    }

    @Test
    public void responseEnum() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseEnumWithRestResponseAsync("valid")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("GREY", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseEnumWithRestResponseAsync("null")
            .subscribe(response -> {
                Map<String, String> headers = response.headers().toMap();
                if (headers.get("value") != null) {
                    Assert.assertEquals("", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void customRequestId() {
        client.headers().customRequestId();
    }
}
