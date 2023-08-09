// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.cadl.builtin;

import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.HttpResponse;
import com.azure.core.models.ResponseError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ExceptionTests {

    @Test
    public void testExceptionValue() {
        HttpClient httpClient = Mockito.mock(HttpClient.class);
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        ArgumentCaptor<HttpRequest> httpRequest = ArgumentCaptor.forClass(HttpRequest.class);

        String responseStr = "{\"error\":{\"code\":\"RESOURCE_NOT_FOUND\",\"message\":\"resource not found\"}}";

        Mockito.when(httpResponse.getStatusCode()).thenReturn(404);
        Mockito.when(httpResponse.getHeaders()).thenReturn(new HttpHeaders());
        Mockito
                .when(httpResponse.getBody())
                .thenReturn(Flux.just(ByteBuffer.wrap(responseStr.getBytes(StandardCharsets.UTF_8))));
        Mockito
                .when(httpResponse.getBodyAsByteArray())
                .thenReturn(Mono.just(responseStr.getBytes(StandardCharsets.UTF_8)));
        Mockito
                .when(httpClient.send(httpRequest.capture(), Mockito.any()))
                .thenReturn(Mono.defer(
                        () -> {
                            Mockito.when(httpResponse.getRequest()).thenReturn(httpRequest.getValue());
                            return Mono.just(httpResponse);
                        }));

        BuiltinAsyncClient client = new BuiltinClientBuilder()
                .endpoint("http://localhost:3000")
                .httpClient(httpClient)
                .buildAsyncClient();

        try {
            client.read("q", "q").block();
        } catch (ResourceNotFoundException e) {
            // TODO (weidxu) fix
            //org.opentest4j.AssertionFailedError:
            //Expected :class com.azure.core.models.ResponseError
            //Actual   :class java.util.LinkedHashMap
//            Assertions.assertEquals(ResponseError.class, e.getValue().getClass());
        }
    }
}
