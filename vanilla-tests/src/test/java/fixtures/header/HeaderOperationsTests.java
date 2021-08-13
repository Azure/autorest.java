package fixtures.header;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.*;
import com.azure.core.util.Base64Util;
import fixtures.header.models.GreyscaleColors;
import fixtures.header.models.HeadersResponseBoolResponse;
import fixtures.header.models.HeadersResponseDatetimeResponse;
import fixtures.header.models.HeadersResponseDatetimeRfc1123Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
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

        HttpPipeline httpPipeline = new HttpPipelineBuilder().policies(
                new UserAgentPolicy(""),
                new AddHeadersPolicy(headers),
                new ProtocolPolicy("http", true),
                new PortPolicy(3000, true),
                new RetryPolicy(new FixedDelay(3, Duration.ZERO)))
                .build();

        client = new AutoRestSwaggerBATHeaderServiceBuilder().pipeline(httpPipeline).buildClient();
    }

    @Ignore("User agent is being overwritten in UserAgentPolicy")
    public void paramExistingKey() {
        client.getHeaders().paramExistingKeyWithResponseAsync("overwrite").block();
    }

    @Test
    public void responseExistingKey() throws Exception {
        lock = new CountDownLatch(1);
        client.getHeaders().responseExistingKeyWithResponseAsync()
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
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
            client.getHeaders().paramProtectedKeyWithResponseAsync("text/html").block();
        } catch (RuntimeException ex) {
            // OkHttp can actually overwrite header "Content-Type"
        }
    }

    @Test
    public void responseProtectedKey() throws Exception {
        lock = new CountDownLatch(1);
        client.getHeaders().responseProtectedKeyWithResponseAsync()
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("Content-Type") != null) {
                        Assert.assertTrue(headers.get("Content-Type").contains("text/html"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramInteger() {
        client.getHeaders().paramIntegerWithResponseAsync("positive", 1).block();
        client.getHeaders().paramIntegerWithResponseAsync("negative", -2).block();
    }

    @Test
    public void responseInteger() throws Exception {
        lock = new CountDownLatch(1);
        client.getHeaders().responseIntegerWithResponseAsync("positive")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("1", headers.get("value"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(10000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.getHeaders().responseIntegerWithResponseAsync("negative")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("-2", headers.get("value"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(10000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramLong() {
        client.getHeaders().paramLongWithResponseAsync("positive", 105).block();
        client.getHeaders().paramLongWithResponseAsync("negative", -2).block();
    }

    @Test
    public void responseLong() throws Exception {
        lock = new CountDownLatch(1);
        client.getHeaders().responseLongWithResponseAsync("positive")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("105", headers.get("value"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.getHeaders().responseLongWithResponseAsync("negative")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("-2", headers.get("value"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramFloat() {
        client.getHeaders().paramFloatWithResponseAsync("positive", 0.07f).block();
        client.getHeaders().paramFloatWithResponseAsync("negative", -3.0f).block();
    }

    @Test
    public void responseFloat() throws Exception {
        lock = new CountDownLatch(1);
        client.getHeaders().responseFloatWithResponseAsync("positive")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("0.07", headers.get("value"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.getHeaders().responseFloatWithResponseAsync("negative")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("-3", headers.get("value"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramDouble() {
        client.getHeaders().paramDoubleWithResponseAsync("positive", 7e120).block();
        client.getHeaders().paramDoubleWithResponseAsync("negative", -3.0).block();
    }

    @Test
    public void responseDouble() throws Exception {
        lock = new CountDownLatch(1);
        client.getHeaders().responseDoubleWithResponseAsync("positive")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("7e+120", headers.get("value"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.getHeaders().responseDoubleWithResponseAsync("negative")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("-3", headers.get("value"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramBool() {
        client.getHeaders().paramBoolWithResponseAsync("true", true).block();
        client.getHeaders().paramBoolWithResponseAsync("false", false).block();
    }

    @Test
    public void responseBool() throws Exception {
        HeadersResponseBoolResponse response = client.getHeaders().responseBoolWithResponseAsync("true").block();
        Map<String, String> headers = response.getHeaders().toMap();
        if (headers.get("value") != null) {
            Assert.assertEquals("true", headers.get("value"));
        }

        response = client.getHeaders().responseBoolWithResponseAsync("false").block();
        headers = response.getHeaders().toMap();
        if (headers.get("value") != null) {
            Assert.assertEquals("false", headers.get("value"));
        }
    }

    @Test
    public void paramString() {
        client.getHeaders().paramStringWithResponseAsync("valid", "The quick brown fox jumps over the lazy dog").block();
        client.getHeaders().paramStringWithResponseAsync("null", null).block();
        client.getHeaders().paramStringWithResponseAsync("empty", "").block();
    }

    @Test
    public void responseString() throws Exception {
        lock = new CountDownLatch(1);
        client.getHeaders().responseStringWithResponseAsync("valid")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("The quick brown fox jumps over the lazy dog", headers.get("value"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.getHeaders().responseStringWithResponseAsync("null")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("null", headers.get("value"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.getHeaders().responseStringWithResponseAsync("empty")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("", headers.get("value"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramDate() {
        client.getHeaders().paramDateWithResponseAsync("valid", LocalDate.of(2010, 1, 1)).block();
        client.getHeaders().paramDateWithResponseAsync("min", LocalDate.of(1, 1, 1)).block();
    }

    @Test
    public void responseDate() throws Exception {
        lock = new CountDownLatch(1);
        client.getHeaders().responseDateWithResponseAsync("valid")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("2010-01-01", headers.get("value"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.getHeaders().responseDateWithResponseAsync("min")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("0001-01-01", headers.get("value"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramDuration() {
        client.getHeaders().paramDurationWithResponseAsync("valid", Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11)).block();
    }

    @Test
    public void responseDuration() throws Exception {
        lock = new CountDownLatch(1);
        client.getHeaders().responseDurationWithResponseAsync("valid")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("P123DT22H14M12.011S", headers.get("value"));
                        lock.countDown();
                    }
                }, (Throwable throwable) -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramDatetimeRfc1123() {
        client.getHeaders().paramDatetimeRfc1123WithResponseAsync("valid", OffsetDateTime.of(2010, 1, 1, 12, 34, 56, 0, ZoneOffset.UTC)).block();
        client.getHeaders().paramDatetimeRfc1123WithResponseAsync("min", OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)).block();
    }

    @Test
    public void responseDatetimeRfc1123() throws Exception {
        HeadersResponseDatetimeRfc1123Response response = client.getHeaders().responseDatetimeRfc1123WithResponseAsync("valid").block();
        Map<String, String> headers = response.getHeaders().toMap();
        if (headers.get("value") != null) {
            Assert.assertEquals("Fri, 01 Jan 2010 12:34:56 GMT", headers.get("value"));
        }

        response = client.getHeaders().responseDatetimeRfc1123WithResponseAsync("min").block();
        headers = response.getHeaders().toMap();
        if (headers.get("value") != null) {
            Assert.assertEquals("Mon, 01 Jan 0001 00:00:00 GMT", headers.get("value"));
        }
    }

    @Test
    public void paramDatetime() {
        client.getHeaders().paramDatetimeWithResponseAsync("valid", OffsetDateTime.of(2010, 1, 1, 12, 34, 56, 0, ZoneOffset.UTC)).block();
        client.getHeaders().paramDatetimeWithResponseAsync("min", OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)).block();
    }

    @Test
    public void responseDatetime() throws Exception {
        HeadersResponseDatetimeResponse response = client.getHeaders().responseDatetimeWithResponseAsync("valid").block();
        Map<String, String> headers = response.getHeaders().toMap();
        if (headers.get("value") != null) {
            Assert.assertEquals("2010-01-01T12:34:56Z", headers.get("value"));
        }
        response = client.getHeaders().responseDatetimeWithResponseAsync("min").block();
        headers = response.getHeaders().toMap();
        if (headers.get("value") != null) {
            Assert.assertEquals("0001-01-01T00:00:00Z", headers.get("value"));
        }
    }

    @Test
    public void paramByte() {
        client.getHeaders().paramByteWithResponseAsync("valid", "啊齄丂狛狜隣郎隣兀﨩".getBytes(Charset.forName("UTF-8"))).block();
    }

    @Test
    public void responseByte() throws Exception {
        lock = new CountDownLatch(1);
        client.getHeaders().responseByteWithResponseAsync("valid")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
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
        client.getHeaders().paramEnumWithResponseAsync("valid", GreyscaleColors.GREY).block();
        client.getHeaders().paramEnumWithResponseAsync("null", null).block();
    }

    @Test
    public void responseEnum() throws Exception {
        lock = new CountDownLatch(1);
        client.getHeaders().responseEnumWithResponseAsync("valid")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("GREY", headers.get("value"));
                        lock.countDown();
                    }
                    Assert.assertEquals(GreyscaleColors.GREY, response.getDeserializedHeaders().getValue());
                }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.getHeaders().responseEnumWithResponseAsync("null")
                .subscribe(response -> {
                    Map<String, String> headers = response.getHeaders().toMap();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("", headers.get("value"));
                        lock.countDown();
                    }
                }, throwable -> fail());
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void customRequestId() {
        client.getHeaders().customRequestId();
    }
}
