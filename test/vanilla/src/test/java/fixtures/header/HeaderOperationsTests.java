package fixtures.header;

import com.microsoft.rest.v2.RestResponse;
import com.microsoft.rest.v2.http.HttpHeaders;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.http.HttpPipelineBuilder;
import com.microsoft.rest.v2.policy.AddHeadersPolicyFactory;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import com.microsoft.rest.v2.policy.ProtocolPolicyFactory;
import com.microsoft.rest.v2.util.Base64Util;
import fixtures.header.implementation.AutoRestSwaggerBATHeaderServiceImpl;
import fixtures.header.models.GreyscaleColors;
import fixtures.header.models.HeaderResponseBoolResponse;
import fixtures.header.models.HeaderResponseByteHeaders;
import fixtures.header.models.HeaderResponseDateHeaders;
import fixtures.header.models.HeaderResponseDatetimeResponse;
import fixtures.header.models.HeaderResponseDatetimeRfc1123Response;
import fixtures.header.models.HeaderResponseDoubleHeaders;
import fixtures.header.models.HeaderResponseDurationHeaders;
import fixtures.header.models.HeaderResponseEnumHeaders;
import fixtures.header.models.HeaderResponseExistingKeyHeaders;
import fixtures.header.models.HeaderResponseFloatHeaders;
import fixtures.header.models.HeaderResponseIntegerHeaders;
import fixtures.header.models.HeaderResponseLongHeaders;
import fixtures.header.models.HeaderResponseProtectedKeyHeaders;
import fixtures.header.models.HeaderResponseStringHeaders;
import io.reactivex.functions.Consumer;
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
        headers.set("x-ms-client-request-id", "9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");

        HttpPipeline httpPipeline = new HttpPipelineBuilder()
                .withUserAgentPolicy("")
                .withRequestPolicy(new AddHeadersPolicyFactory(headers))
                .withRequestPolicy(new ProtocolPolicyFactory("http"))
                .withRequestPolicy(new PortPolicyFactory(3000))
                .withRetryPolicy(3, 0, TimeUnit.SECONDS)
                .withDecodingPolicy()
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
            .subscribe((Consumer<RestResponse<HeaderResponseExistingKeyHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
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
            .subscribe((Consumer<RestResponse<HeaderResponseProtectedKeyHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
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
            .subscribe((Consumer<RestResponse<HeaderResponseIntegerHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
                if (headers.get("value") != null) {
                    Assert.assertEquals("1", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(10000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseIntegerWithRestResponseAsync("negative")
            .subscribe((Consumer<RestResponse<HeaderResponseIntegerHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
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
            .subscribe((Consumer<RestResponse<HeaderResponseLongHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
                if (headers.get("value") != null) {
                    Assert.assertEquals("105", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseLongWithRestResponseAsync("negative")
            .subscribe((Consumer<RestResponse<HeaderResponseLongHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
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
            .subscribe((Consumer<RestResponse<HeaderResponseFloatHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
                if (headers.get("value") != null) {
                    Assert.assertEquals("0.07", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseFloatWithRestResponseAsync("negative")
            .subscribe((Consumer<RestResponse<HeaderResponseFloatHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
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
            .subscribe((Consumer<RestResponse<HeaderResponseDoubleHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
                if (headers.get("value") != null) {
                    Assert.assertEquals("7e+120", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseDoubleWithRestResponseAsync("negative")
            .subscribe((Consumer<RestResponse<HeaderResponseDoubleHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
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
        HeaderResponseBoolResponse response = client.headers().responseBoolWithRestResponseAsync("true").blockingGet();
        Map<String, String> headers = response.rawHeaders();
        if (headers.get("value") != null) {
            Assert.assertEquals("true", headers.get("value"));
        }

        response = client.headers().responseBoolWithRestResponseAsync("false").blockingGet();
        headers = response.rawHeaders();
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
            .subscribe((Consumer<RestResponse<HeaderResponseStringHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
                if (headers.get("value") != null) {
                    Assert.assertEquals("The quick brown fox jumps over the lazy dog", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseStringWithRestResponseAsync("null")
            .subscribe((Consumer<RestResponse<HeaderResponseStringHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
                if (headers.get("value") != null) {
                    Assert.assertEquals("null", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseStringWithRestResponseAsync("empty")
            .subscribe((Consumer<RestResponse<HeaderResponseStringHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
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
            .subscribe((Consumer<RestResponse<HeaderResponseDateHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
                if (headers.get("value") != null) {
                    Assert.assertEquals("2010-01-01", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseDateWithRestResponseAsync("min")
            .subscribe((Consumer<RestResponse<HeaderResponseDateHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
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
            .subscribe((Consumer<RestResponse<HeaderResponseDurationHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
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
        HeaderResponseDatetimeRfc1123Response response = client.headers().responseDatetimeRfc1123WithRestResponseAsync("valid").blockingGet();
        Map<String, String> headers = response.rawHeaders();
        if (headers.get("value") != null) {
            Assert.assertEquals("Fri, 01 Jan 2010 12:34:56 GMT", headers.get("value"));
        }

        response = client.headers().responseDatetimeRfc1123WithRestResponseAsync("min").blockingGet();
        headers = response.rawHeaders();
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
        HeaderResponseDatetimeResponse response = client.headers().responseDatetimeWithRestResponseAsync("valid").blockingGet();
        Map<String, String> headers = response.rawHeaders();
        if (headers.get("value") != null) {
            Assert.assertEquals("2010-01-01T12:34:56Z", headers.get("value"));
        }
        response = client.headers().responseDatetimeWithRestResponseAsync("min").blockingGet();
        headers = response.rawHeaders();
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
            .subscribe((Consumer<RestResponse<HeaderResponseByteHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
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
            .subscribe((Consumer<RestResponse<HeaderResponseEnumHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
                if (headers.get("value") != null) {
                    Assert.assertEquals("GREY", headers.get("value"));
                    lock.countDown();
                }
            }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseEnumWithRestResponseAsync("null")
            .subscribe((Consumer<RestResponse<HeaderResponseEnumHeaders, Void>>) response -> {
                Map<String, String> headers = response.rawHeaders();
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
