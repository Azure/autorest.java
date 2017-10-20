package fixtures.header;

import com.microsoft.rest.RestClient;
import com.microsoft.rest.RestClient.Builder;
import com.microsoft.rest.RestResponse;
import com.microsoft.rest.http.HttpHeaders;
import com.microsoft.rest.policy.AddHeadersPolicy;
import com.microsoft.rest.policy.RequestPolicy;
import com.microsoft.rest.serializer.JacksonAdapter;
import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import fixtures.header.implementation.AutoRestSwaggerBATHeaderServiceImpl;
import fixtures.header.models.GreyscaleColors;
import fixtures.header.models.HeaderResponseBoolHeaders;
import fixtures.header.models.HeaderResponseByteHeaders;
import fixtures.header.models.HeaderResponseDateHeaders;
import fixtures.header.models.HeaderResponseDatetimeHeaders;
import fixtures.header.models.HeaderResponseDatetimeRfc1123Headers;
import fixtures.header.models.HeaderResponseDoubleHeaders;
import fixtures.header.models.HeaderResponseDurationHeaders;
import fixtures.header.models.HeaderResponseEnumHeaders;
import fixtures.header.models.HeaderResponseExistingKeyHeaders;
import fixtures.header.models.HeaderResponseFloatHeaders;
import fixtures.header.models.HeaderResponseIntegerHeaders;
import fixtures.header.models.HeaderResponseLongHeaders;
import fixtures.header.models.HeaderResponseProtectedKeyHeaders;
import fixtures.header.models.HeaderResponseStringHeaders;
import rx.functions.Action1;

import static org.junit.Assert.fail;

public class HeaderOperationsTests {
    private static AutoRestSwaggerBATHeaderService client;
    private CountDownLatch lock;

    @BeforeClass
    public static void setup() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-ms-client-request-id", "9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");
        RequestPolicy.Factory addHeadersFactory = new AddHeadersPolicy.Factory(headers);

        RestClient restClient = new RestClient.Builder()
                .withUserAgent("")
                .withBaseUrl("http://localhost:3000")
                .withSerializerAdapter(new JacksonAdapter())
                .addCustomPolicy(addHeadersFactory)
                .build();

        client = new AutoRestSwaggerBATHeaderServiceImpl(restClient);
    }

    @Test
    public void paramExistingKey() throws Exception {
        client.headers().paramExistingKey("overwrite");
    }

    @Test
    public void responseExistingKey() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseExistingKeyWithRestResponseAsync()
            .subscribe(new Action1<RestResponse<HeaderResponseExistingKeyHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseExistingKeyHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("User-Agent") != null) {
                        Assert.assertEquals("overwrite", headers.get("User-Agent"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramProtectedKey() throws Exception {
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
            .subscribe(new Action1<RestResponse<HeaderResponseProtectedKeyHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseProtectedKeyHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("Content-Type") != null) {
                        Assert.assertTrue(headers.get("Content-Type").contains("text/html"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramInteger() throws Exception {
        client.headers().paramInteger("positive", 1);
        client.headers().paramInteger("negative", -2);
    }

    @Test
    public void responseInteger() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseIntegerWithRestResponseAsync("positive")
            .subscribe(new Action1<RestResponse<HeaderResponseIntegerHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseIntegerHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("1", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseIntegerWithRestResponseAsync("negative")
            .subscribe(new Action1<RestResponse<HeaderResponseIntegerHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseIntegerHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("-2", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramLong() throws Exception {
        client.headers().paramLong("positive", 105);
        client.headers().paramLong("negative", -2);
    }

    @Test
    public void responseLong() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseLongWithRestResponseAsync("positive")
            .subscribe(new Action1<RestResponse<HeaderResponseLongHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseLongHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("105", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseLongWithRestResponseAsync("negative")
            .subscribe(new Action1<RestResponse<HeaderResponseLongHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseLongHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("-2", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramFloat() throws Exception {
        client.headers().paramFloat("positive", 0.07);
        client.headers().paramFloat("negative", -3.0);
    }

    @Test
    public void responseFloat() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseFloatWithRestResponseAsync("positive")
            .subscribe(new Action1<RestResponse<HeaderResponseFloatHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseFloatHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("0.07", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseFloatWithRestResponseAsync("negative")
            .subscribe(new Action1<RestResponse<HeaderResponseFloatHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseFloatHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("-3", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramDouble() throws Exception {
        client.headers().paramDouble("positive", 7e120);
        client.headers().paramDouble("negative", -3.0);
    }

    @Test
    public void responseDouble() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseDoubleWithRestResponseAsync("positive")
            .subscribe(new Action1<RestResponse<HeaderResponseDoubleHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseDoubleHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("7e+120", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseDoubleWithRestResponseAsync("negative")
            .subscribe(new Action1<RestResponse<HeaderResponseDoubleHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseDoubleHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("-3", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramBool() throws Exception {
        client.headers().paramBool("true", true);
        client.headers().paramBool("false", false);
    }

    @Test
    public void responseBool() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseBoolWithRestResponseAsync("true")
            .subscribe(new Action1<RestResponse<HeaderResponseBoolHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseBoolHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("true", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseBoolWithRestResponseAsync("false")
            .subscribe(new Action1<RestResponse<HeaderResponseBoolHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseBoolHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("false", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramString() throws Exception {
        client.headers().paramString("valid", "The quick brown fox jumps over the lazy dog");
        client.headers().paramString("null", null);
        client.headers().paramString("empty", "");
    }

    @Test
    public void responseString() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseStringWithRestResponseAsync("valid")
            .subscribe(new Action1<RestResponse<HeaderResponseStringHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseStringHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("The quick brown fox jumps over the lazy dog", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseStringWithRestResponseAsync("null")
            .subscribe(new Action1<RestResponse<HeaderResponseStringHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseStringHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("null", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseStringWithRestResponseAsync("empty")
            .subscribe(new Action1<RestResponse<HeaderResponseStringHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseStringHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramDate() throws Exception {
        client.headers().paramDate("valid", new LocalDate(2010, 1, 1));
        client.headers().paramDate("min", new LocalDate(1, 1, 1));
    }

    @Test
    public void responseDate() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseDateWithRestResponseAsync("valid")
            .subscribe(new Action1<RestResponse<HeaderResponseDateHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseDateHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("2010-01-01", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseDateWithRestResponseAsync("min")
            .subscribe(new Action1<RestResponse<HeaderResponseDateHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseDateHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("0001-01-01", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramDuration() throws Exception {
        client.headers().paramDuration("valid", new Period(0, 0, 0, 123, 22, 14, 12, 11));
    }

    @Test
    public void responseDuration() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseDurationWithRestResponseAsync("valid")
            .subscribe(new Action1<RestResponse<HeaderResponseDurationHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseDurationHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("P123DT22H14M12.011S", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramDatetimeRfc1123() throws Exception {
        client.headers().paramDatetimeRfc1123("valid", new DateTime(2010, 1, 1, 12, 34, 56, DateTimeZone.UTC));
        client.headers().paramDatetimeRfc1123("min", new DateTime(1, 1, 1, 0, 0, 0, DateTimeZone.UTC));
    }

    @Test
    public void responseDatetimeRfc1123() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseDatetimeRfc1123WithRestResponseAsync("valid")
            .subscribe(new Action1<RestResponse<HeaderResponseDatetimeRfc1123Headers, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseDatetimeRfc1123Headers, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("Fri, 01 Jan 2010 12:34:56 GMT", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(100000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseDatetimeRfc1123WithRestResponseAsync("min")
            .subscribe(new Action1<RestResponse<HeaderResponseDatetimeRfc1123Headers, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseDatetimeRfc1123Headers, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("Mon, 01 Jan 0001 00:00:00 GMT", headers.get("value"));
                        lock.countDown();

                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramDatetime() throws Exception {
        client.headers().paramDatetime("valid", new DateTime(2010, 1, 1, 12, 34, 56, DateTimeZone.UTC));
        client.headers().paramDatetime("min", new DateTime(1, 1, 1, 0, 0, 0, DateTimeZone.UTC));
    }

    @Test
    public void responseDatetime() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseDatetimeWithRestResponseAsync("valid")
            .subscribe(new Action1<RestResponse<HeaderResponseDatetimeHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseDatetimeHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("2010-01-01T12:34:56Z", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseDatetimeWithRestResponseAsync("min")
            .subscribe(new Action1<RestResponse<HeaderResponseDatetimeHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseDatetimeHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("0001-01-01T00:00:00Z", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramByte() throws Exception {
        client.headers().paramByte("valid", "啊齄丂狛狜隣郎隣兀﨩".getBytes(Charset.forName("UTF-8")));
    }

    @Test
    public void responseByte() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseByteWithRestResponseAsync("valid")
            .subscribe(new Action1<RestResponse<HeaderResponseByteHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseByteHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        byte[] value = Base64.decodeBase64(headers.get("value"));
                        String actual = new String(value, Charset.forName("UTF-8"));
                        Assert.assertEquals("啊齄丂狛狜隣郎隣兀﨩", actual);
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void paramEnum() throws Exception {
        client.headers().paramEnum("valid", GreyscaleColors.GREY);
        client.headers().paramEnum("null", null);
    }

    @Test
    public void responseEnum() throws Exception {
        lock = new CountDownLatch(1);
        client.headers().responseEnumWithRestResponseAsync("valid")
            .subscribe(new Action1<RestResponse<HeaderResponseEnumHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseEnumHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("GREY", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
        lock = new CountDownLatch(1);
        client.headers().responseEnumWithRestResponseAsync("null")
            .subscribe(new Action1<RestResponse<HeaderResponseEnumHeaders, Void>>() {
                @Override
                public void call(RestResponse<HeaderResponseEnumHeaders, Void> response) {
                    Map<String, String> headers = response.rawHeaders();
                    if (headers.get("value") != null) {
                        Assert.assertEquals("", headers.get("value"));
                        lock.countDown();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    fail();
                }
            });
        Assert.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void customRequestId() throws Exception {
        client.headers().customRequestId();
    }
}
