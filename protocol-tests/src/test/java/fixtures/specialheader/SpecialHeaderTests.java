// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.specialheader;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpHeader;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpPipelineCallContext;
import com.azure.core.http.HttpPipelineNextPolicy;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.HttpResponse;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.test.http.MockHttpResponse;
import com.azure.core.util.BinaryData;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.DateTimeRfc1123;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SpecialHeaderTests {

    private static SpecialHeaderClient client;

    private static SpecialHeaderAsyncClient asyncClient;

    private static class ValidationPolicy implements HttpPipelinePolicy {
        private String repeatabilityRequestId;
        private String repeatabilityFirstSent;
        private boolean validationPass = false;

        public void clear() {
            repeatabilityRequestId = null;
            repeatabilityFirstSent = null;
            validationPass = false;
        }

        public boolean isValidationPass() {
            return validationPass;
        }

        @Override
        public Mono<HttpResponse> process(HttpPipelineCallContext context, HttpPipelineNextPolicy next) {
            HttpHeaders headers = context.getHttpRequest().getHeaders();
            String repeatabilityRequestId = headers.getValue("repeatability-request-id");
            String repeatabilityFirstSent = headers.getValue("repeatability-first-sent");
            // check headers available
            if (!CoreUtils.isNullOrEmpty(repeatabilityRequestId) && !CoreUtils.isNullOrEmpty(repeatabilityFirstSent)) {
                if (this.repeatabilityRequestId == null) {
                    this.repeatabilityRequestId = repeatabilityRequestId;
                    this.repeatabilityFirstSent = repeatabilityFirstSent;
                    this.validationPass = true;
                } else if (this.validationPass) {
                    // check headers not changed
                    if (!this.repeatabilityRequestId.equals(repeatabilityRequestId) || !this.repeatabilityFirstSent.equals(repeatabilityFirstSent)) {
                        this.validationPass = false;
                    }
                }

                if (validationPass) {
                    // check headers valid
                    try {
                        UUID.fromString(repeatabilityRequestId);
                    } catch (IllegalArgumentException e) {
                        System.err.println("invalid GUID: " + repeatabilityRequestId);
                        validationPass = false;
                    }

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH).withZone(ZoneId.of("GMT"));
                        formatter.parse(repeatabilityFirstSent);
                    } catch (DateTimeParseException e) {
                        System.err.println("invalid HTTP-date: " + repeatabilityFirstSent);
                        validationPass = false;
                    }
                }
            } else {
                validationPass = false;
            }
            return next.process();
        }
    }

    private static final ValidationPolicy VALIDATION_POLICY = new ValidationPolicy();

    @BeforeAll
    public static void setup() {
        HttpClient mockHttpClient = request -> Mono.just(new MockHttpResponse(request, 500));

        HttpLogOptions httpLogOptions = new HttpLogOptions()
                .setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS)
                .setRequestLogger((logger, loggingOptions) -> {
                    final HttpRequest request = loggingOptions.getHttpRequest();
                    StringBuilder sb = new StringBuilder();
                    for (HttpHeader header : request.getHeaders()) {
                        String headerName = header.getName();
                        sb.append(headerName).append(":");
                        sb.append(header.getValue());
                        sb.append("; ");
                    }
                    logger.info(sb.toString());
                    return Mono.empty();
                });

        client = new SpecialHeaderClientBuilder()
//                .host("https://httpbin.org/")
                .httpClient(mockHttpClient)
                .addPolicy(VALIDATION_POLICY)
                .httpLogOptions(httpLogOptions)
                .buildClient();

        asyncClient = new SpecialHeaderClientBuilder()
                .httpClient(mockHttpClient)
                .addPolicy(VALIDATION_POLICY)
                .httpLogOptions(httpLogOptions)
                .buildAsyncClient();
    }

    @Test
    public void testRepeatabilityRequest() {
        VALIDATION_POLICY.clear();

        Assertions.assertThrows(HttpResponseException.class,
                () -> client.paramRepeatabilityRequestWithResponse(null));

        Assertions.assertTrue(VALIDATION_POLICY.isValidationPass());
    }

    @Test
    public void testRepeatabilityRequestPut() {
        VALIDATION_POLICY.clear();

        Assertions.assertThrows(HttpResponseException.class,
                () -> client.paramRepeatabilityRequestPutWithResponse(null));

        Assertions.assertTrue(VALIDATION_POLICY.isValidationPass());
    }

    @Test
    public void testRepeatabilityRequestUserProvidedHeader() {
        VALIDATION_POLICY.clear();

        final String id = CoreUtils.randomUuid().toString();
        final String date = DateTimeRfc1123.toRfc1123String(OffsetDateTime.now().minusMinutes(1));

        Assertions.assertThrows(HttpResponseException.class,
                () -> client.paramRepeatabilityRequestWithResponse(new RequestOptions()
                        .setHeader(HttpHeaderName.fromString("Repeatability-Request-ID"), id)
                        .setHeader(HttpHeaderName.fromString("Repeatability-First-Sent"), date)));

        Assertions.assertTrue(VALIDATION_POLICY.isValidationPass());

        Assertions.assertEquals(id, VALIDATION_POLICY.repeatabilityRequestId);
        Assertions.assertEquals(date, VALIDATION_POLICY.repeatabilityFirstSent);
    }

    @Test
    public void testRepeatabilityRequestAsyncDate() throws InterruptedException {
        // make the Mono but do not subscribe
        Instant instantAtMono = OffsetDateTime.now().toInstant();
        Mono<Response<BinaryData>> responseMono = asyncClient.paramRepeatabilityRequestWithResponse(null);

        // wait for 3 sec
        TimeUnit.SECONDS.sleep(3);

        // subscribe - send request
        Instant instantAtRequest = OffsetDateTime.now().toInstant();
        Assertions.assertThrows(HttpResponseException.class, responseMono::block);

        Assertions.assertTrue(VALIDATION_POLICY.isValidationPass());
        Instant instantOfRepeatabilityFirstSent = new DateTimeRfc1123(VALIDATION_POLICY.repeatabilityFirstSent).getDateTime().toInstant();

        // instantOfRepeatabilityFirstSent should be near instantAtRequest, not instantAtMono
        Duration duration = Duration.between(instantAtMono, instantOfRepeatabilityFirstSent);
        // check for 2 sec, as the precision of "repeatability-first-sent" is 1 sec
        Assertions.assertTrue(duration.compareTo(Duration.ofSeconds(2)) > 0);
    }
}
