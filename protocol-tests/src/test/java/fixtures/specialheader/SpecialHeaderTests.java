// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.specialheader;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpHeader;
import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpPipelineCallContext;
import com.azure.core.http.HttpPipelineNextPolicy;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.HttpResponse;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.util.CoreUtils;
import fixtures.MockHttpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.UUID;

public class SpecialHeaderTests {

    private static SpecialHeaderClient client;

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

        client = new SpecialHeaderClientBuilder()
//                .host("https://httpbin.org/")
                .httpClient(mockHttpClient)
                .addPolicy(VALIDATION_POLICY)
                .httpLogOptions(new HttpLogOptions()
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
                        }))
                .buildClient();
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
}
