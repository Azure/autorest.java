package fixtures.header;

import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.AddHeadersPolicy;
import com.azure.core.http.policy.FixedDelay;
import com.azure.core.http.policy.PortPolicy;
import com.azure.core.http.policy.ProtocolPolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.Base64Util;
import fixtures.header.models.GreyscaleColors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeaderOperationsTests {
    private static AutoRestSwaggerBATHeaderService client;

    @BeforeAll
    public static void setup() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaderName.X_MS_CLIENT_REQUEST_ID, "9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");

        HttpPipeline httpPipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(""),
            new AddHeadersPolicy(headers), new ProtocolPolicy("http", true), new PortPolicy(3000, true),
            new RetryPolicy(new FixedDelay(3, Duration.ZERO))).build();

        client = new AutoRestSwaggerBATHeaderServiceBuilder().pipeline(httpPipeline).buildClient();
    }

    @Disabled("User agent is being overwritten in UserAgentPolicy")
    @Test
    public void paramExistingKey() {
        StepVerifier.create(client.getHeaders().paramExistingKeyWithResponseAsync("overwrite"))
            .expectNextCount(1)
            .verifyComplete();
    }

    @Test
    public void responseExistingKey() {
        StepVerifier.create(client.getHeaders().responseExistingKeyWithResponseAsync())
            .assertNext(response -> {
                Map<String, String> headers = response.getHeaders().toMap();
                if (headers.get("User-Agent") != null) {
                    assertEquals("overwrite", headers.get("User-Agent"));
                }
            })
            .expectComplete()
            .verify(Duration.ofMillis(1000));
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
    public void responseProtectedKey() {
        StepVerifier.create(client.getHeaders().responseProtectedKeyWithResponseAsync())
            .assertNext(response -> {
                Map<String, String> headers = response.getHeaders().toMap();
                if (headers.get("Content-Type") != null) {
                    assertTrue(headers.get("Content-Type").contains("text/html"));
                }
            })
            .expectComplete()
            .verify(Duration.ofMillis(1000));
    }

    @Test
    public void paramInteger() {
        StepVerifier.create(client.getHeaders().paramIntegerWithResponseAsync("positive", 1))
            .expectNextCount(1)
            .verifyComplete();

        StepVerifier.create(client.getHeaders().paramIntegerWithResponseAsync("negative", -2))
            .expectNextCount(1)
            .verifyComplete();
    }

    @Test
    public void responseInteger() {
        StepVerifier.create(client.getHeaders().responseIntegerWithResponseAsync("positive"))
            .assertNext(response -> {
                Map<String, String> headers = response.getHeaders().toMap();
                if (headers.get("value") != null) {
                    assertEquals("1", headers.get("value"));
                }
            })
            .expectComplete()
            .verify(Duration.ofMillis(1000));

        StepVerifier.create(client.getHeaders().responseIntegerWithResponseAsync("negative"))
            .assertNext(response -> {
                Map<String, String> headers = response.getHeaders().toMap();
                if (headers.get("value") != null) {
                    assertEquals("-2", headers.get("value"));
                }
            })
            .expectComplete()
            .verify(Duration.ofMillis(1000));
    }

    @Test
    public void paramLong() {
        StepVerifier.create(client.getHeaders().paramLongWithResponseAsync("positive", 105))
            .expectNextCount(1)
            .verifyComplete();

        StepVerifier.create(client.getHeaders().paramLongWithResponseAsync("negative", -2))
            .expectNextCount(1)
            .verifyComplete();
    }

    @Test
    public void responseLong() {
        StepVerifier.create(client.getHeaders().responseLongWithResponseAsync("positive"))
            .assertNext(response -> {
                Map<String, String> headers = response.getHeaders().toMap();
                if (headers.get("value") != null) {
                    assertEquals("105", headers.get("value"));
                }
            })
            .expectComplete()
            .verify(Duration.ofMillis(1000));

        StepVerifier.create(client.getHeaders().responseLongWithResponseAsync("negative"))
            .assertNext(response -> {
                Map<String, String> headers = response.getHeaders().toMap();
                if (headers.get("value") != null) {
                    assertEquals("-2", headers.get("value"));
                }
            })
            .expectComplete()
            .verify(Duration.ofMillis(1000));
    }

    @Test
    public void paramFloat() {
        StepVerifier.create(client.getHeaders().paramFloatWithResponseAsync("positive", 0.07f))
            .expectNextCount(1)
            .verifyComplete();

        StepVerifier.create(client.getHeaders().paramFloatWithResponseAsync("negative", -3.0f))
            .expectNextCount(1)
            .verifyComplete();
    }

    @Test
    public void responseFloat() {
        StepVerifier.create(client.getHeaders().responseFloatWithResponseAsync("positive"))
            .assertNext(response -> {
                Map<String, String> headers = response.getHeaders().toMap();
                if (headers.get("value") != null) {
                    assertEquals("0.07", headers.get("value"));
                }
            })
            .expectComplete()
            .verify(Duration.ofMillis(1000));

        StepVerifier.create(client.getHeaders().responseFloatWithResponseAsync("negative"))
            .assertNext(response -> {
                Map<String, String> headers = response.getHeaders().toMap();
                if (headers.get("value") != null) {
                    assertEquals("-3", headers.get("value"));
                }
            })
            .expectComplete()
            .verify(Duration.ofMillis(1000));
    }

    @Test
    public void paramDouble() {
        StepVerifier.create(client.getHeaders().paramDoubleWithResponseAsync("positive", 7e120))
            .expectNextCount(1)
            .verifyComplete();

        StepVerifier.create(client.getHeaders().paramDoubleWithResponseAsync("negative", -3.0))
            .expectNextCount(1)
            .verifyComplete();
    }

    @Test
    public void responseDouble() {
        StepVerifier.create(client.getHeaders().responseDoubleWithResponseAsync("positive"))
            .assertNext(response -> {
                Map<String, String> headers = response.getHeaders().toMap();
                if (headers.get("value") != null) {
                    assertEquals("7e+120", headers.get("value"));
                }
            })
            .expectComplete()
            .verify(Duration.ofMillis(1000));

        StepVerifier.create(client.getHeaders().responseDoubleWithResponseAsync("negative"))
            .assertNext(response -> {
                Map<String, String> headers = response.getHeaders().toMap();
                if (headers.get("value") != null) {
                    assertEquals("-3", headers.get("value"));
                }
            })
            .expectComplete()
            .verify(Duration.ofMillis(1000));
    }

    @Test
    public void paramBool() {
        StepVerifier.create(client.getHeaders().paramBoolWithResponseAsync("true", true))
            .expectNextCount(1)
            .verifyComplete();

        StepVerifier.create(client.getHeaders().paramBoolWithResponseAsync("false", false))
            .expectNextCount(1)
            .verifyComplete();
    }

    @Test
    public void responseBool() {
        StepVerifier.create(client.getHeaders().responseBoolWithResponseAsync("true")).assertNext(response -> {
            Map<String, String> headers = response.getHeaders().toMap();
            if (headers.get("value") != null) {
                assertEquals("true", headers.get("value"));
            }
        }).verifyComplete();

        StepVerifier.create(client.getHeaders().responseBoolWithResponseAsync("false")).assertNext(response -> {
            Map<String, String> headers = response.getHeaders().toMap();
            if (headers.get("value") != null) {
                assertEquals("false", headers.get("value"));
            }
        }).verifyComplete();
    }

    @Test
    public void paramString() {
        StepVerifier.create(
                client.getHeaders().paramStringWithResponseAsync("valid", "The quick brown fox jumps over the lazy dog"))
            .expectNextCount(1)
            .verifyComplete();

        StepVerifier.create(client.getHeaders().paramStringWithResponseAsync("null", null))
            .expectNextCount(1)
            .verifyComplete();

        StepVerifier.create(client.getHeaders().paramStringWithResponseAsync("empty", ""))
            .expectNextCount(1)
            .verifyComplete();
    }

    @Test
    public void responseString() {
        StepVerifier.create(client.getHeaders().responseStringWithResponseAsync("valid")).assertNext(response -> {
            Map<String, String> headers = response.getHeaders().toMap();
            if (headers.get("value") != null) {
                assertEquals("The quick brown fox jumps over the lazy dog", headers.get("value"));
            }
        }).expectComplete().verify(Duration.ofMillis(1000));

        StepVerifier.create(client.getHeaders().responseStringWithResponseAsync("null")).assertNext(response -> {
            Map<String, String> headers = response.getHeaders().toMap();
            if (headers.get("value") != null) {
                assertEquals("null", headers.get("value"));
            }
        }).expectComplete().verify(Duration.ofMillis(1000));

        StepVerifier.create(client.getHeaders().responseStringWithResponseAsync("empty")).assertNext(response -> {
            Map<String, String> headers = response.getHeaders().toMap();
            if (headers.get("value") != null) {
                assertEquals("", headers.get("value"));
            }
        }).expectComplete().verify(Duration.ofMillis(1000));
    }

    @Test
    public void paramDate() {
        StepVerifier.create(client.getHeaders().paramDateWithResponseAsync("valid", LocalDate.of(2010, 1, 1)))
            .expectNextCount(1)
            .verifyComplete();

        StepVerifier.create(client.getHeaders().paramDateWithResponseAsync("min", LocalDate.of(1, 1, 1)))
            .expectNextCount(1)
            .verifyComplete();
    }

    @Test
    public void responseDate() {
        StepVerifier.create(client.getHeaders().responseDateWithResponseAsync("valid")).assertNext(response -> {
            Map<String, String> headers = response.getHeaders().toMap();
            if (headers.get("value") != null) {
                assertEquals("2010-01-01", headers.get("value"));
            }
        }).expectComplete().verify(Duration.ofMillis(1000));

        StepVerifier.create(client.getHeaders().responseDateWithResponseAsync("min")).assertNext(response -> {
            Map<String, String> headers = response.getHeaders().toMap();
            if (headers.get("value") != null) {
                assertEquals("0001-01-01", headers.get("value"));
            }
        }).expectComplete().verify(Duration.ofMillis(1000));
    }

    @Test
    public void paramDuration() {
        StepVerifier.create(client.getHeaders()
                .paramDurationWithResponseAsync("valid",
                    Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMillis(11)))
            .expectNextCount(1)
            .verifyComplete();
    }

    @Test
    public void responseDuration() {
        StepVerifier.create(client.getHeaders().responseDurationWithResponseAsync("valid")).assertNext(response -> {
            Map<String, String> headers = response.getHeaders().toMap();
            if (headers.get("value") != null) {
                assertEquals("P123DT22H14M12.011S", headers.get("value"));
            }
        }).expectComplete().verify(Duration.ofMillis(1000));
    }

    @Test
    public void paramDatetimeRfc1123() {
        StepVerifier.create(client.getHeaders()
            .paramDatetimeRfc1123WithResponseAsync("valid",
                OffsetDateTime.of(2010, 1, 1, 12, 34, 56, 0, ZoneOffset.UTC))).expectNextCount(1).verifyComplete();

        StepVerifier.create(client.getHeaders()
                .paramDatetimeRfc1123WithResponseAsync("min", OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
            .expectNextCount(1)
            .verifyComplete();
    }

    @Test
    public void responseDatetimeRfc1123() {
        StepVerifier.create(client.getHeaders().responseDatetimeRfc1123WithResponseAsync("valid"))
            .assertNext(response -> {
                Map<String, String> headers = response.getHeaders().toMap();
                if (headers.get("value") != null) {
                    assertEquals("Fri, 01 Jan 2010 12:34:56 GMT", headers.get("value"));
                }
            })
            .verifyComplete();

        StepVerifier.create(client.getHeaders().responseDatetimeRfc1123WithResponseAsync("min"))
            .assertNext(response -> {
                Map<String, String> headers = response.getHeaders().toMap();
                if (headers.get("value") != null) {
                    assertEquals("Mon, 01 Jan 0001 00:00:00 GMT", headers.get("value"));
                }
            })
            .verifyComplete();
    }

    @Test
    public void paramDatetime() {
        StepVerifier.create(client.getHeaders()
                .paramDatetimeWithResponseAsync("valid", OffsetDateTime.of(2010, 1, 1, 12, 34, 56, 0, ZoneOffset.UTC)))
            .expectNextCount(1)
            .verifyComplete();

        StepVerifier.create(client.getHeaders()
                .paramDatetimeWithResponseAsync("min", OffsetDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
            .expectNextCount(1)
            .verifyComplete();
    }

    @Test
    public void responseDatetime() {
        StepVerifier.create(client.getHeaders().responseDatetimeWithResponseAsync("valid")).assertNext(response -> {
            Map<String, String> headers = response.getHeaders().toMap();
            if (headers.get("value") != null) {
                assertEquals("2010-01-01T12:34:56Z", headers.get("value"));
            }
        }).verifyComplete();

        StepVerifier.create(client.getHeaders().responseDatetimeWithResponseAsync("min")).assertNext(response -> {
            Map<String, String> headers = response.getHeaders().toMap();
            if (headers.get("value") != null) {
                assertEquals("0001-01-01T00:00:00Z", headers.get("value"));
            }
        }).verifyComplete();
    }

    @Test
    public void paramByte() {
        StepVerifier.create(client.getHeaders()
                .paramByteWithResponseAsync("valid", "啊齄丂狛狜隣郎隣兀﨩".getBytes(StandardCharsets.UTF_8)))
            .expectNextCount(1)
            .verifyComplete();
    }

    @Test
    public void responseByte() {
        StepVerifier.create(client.getHeaders().responseByteWithResponseAsync("valid")).assertNext(response -> {
            Map<String, String> headers = response.getHeaders().toMap();
            if (headers.get("value") != null) {
                byte[] value = Base64Util.decodeString(headers.get("value"));
                String actual = new String(value, StandardCharsets.UTF_8);
                assertEquals("啊齄丂狛狜隣郎隣兀﨩", actual);
            }
        }).expectComplete().verify(Duration.ofMillis(1000));
    }

    @Test
    public void paramEnum() {
        StepVerifier.create(client.getHeaders().paramEnumWithResponseAsync("valid", GreyscaleColors.GREY))
            .expectNextCount(1)
            .verifyComplete();

        StepVerifier.create(client.getHeaders().paramEnumWithResponseAsync("null", null))
            .expectNextCount(1)
            .verifyComplete();
    }

    @Test
    public void responseEnum() {
        StepVerifier.create(client.getHeaders().responseEnumWithResponseAsync("valid")).assertNext(response -> {
            Map<String, String> headers = response.getHeaders().toMap();
            if (headers.get("value") != null) {
                assertEquals("GREY", headers.get("value"));
            }
            assertEquals(GreyscaleColors.GREY, response.getDeserializedHeaders().getValue());
        }).expectComplete().verify(Duration.ofMillis(1000));

        StepVerifier.create(client.getHeaders().responseEnumWithResponseAsync("null")).assertNext(response -> {
            Map<String, String> headers = response.getHeaders().toMap();
            if (headers.get("value") != null) {
                assertEquals("", headers.get("value"));
            }
        }).expectComplete().verify(Duration.ofMillis(1000));
    }

    @Test
    public void customRequestId() {
        client.getHeaders().customRequestId();
    }
}
