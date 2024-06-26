// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.specialheader;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpHeader;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.test.http.MockHttpResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpecialHeaderTests {

    private static Headers client;

    @BeforeAll
    public static void setup() {
        HttpClient mockHttpClient = request -> Mono.just(new MockHttpResponse(request, 500));

        client = new SpecialHeaderBuilder()
//            .host("https://httpbin.org/")
            .httpClient(mockHttpClient)
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS)
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
            .buildClient()
            .getHeaders();
    }

    @Test
    public void testRepeatabilityRequest() {
        assertThrows(HttpResponseException.class, () -> client.paramRepeatabilityRequest());
    }
}
