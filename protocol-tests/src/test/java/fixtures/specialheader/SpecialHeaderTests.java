// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.specialheader;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.HttpHeader;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class SpecialHeaderTests {

    private static SpecialHeaderClient client;

    @BeforeAll
    public static void setup() {
        client = new SpecialHeaderClientBuilder()
                .host("https://httpbin.org/")
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
        Assertions.assertThrows(HttpResponseException.class,
                () -> client.paramRepeatabilityRequestWithResponse(null));
    }
}
