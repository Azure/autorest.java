// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.dpgcustomization;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpMethod;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.policy.AddHeadersFromContextPolicy;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

public class DpgSendRequestTests {

    private static DpgClient client;
    private static DpgAsyncClient asyncClient;

    @BeforeAll
    public static void setup() {
        client = new DpgClientBuilder()
                .addPolicy(new AddHeadersFromContextPolicy())
                .buildClient();
        asyncClient = new DpgClientBuilder()
                .addPolicy(new AddHeadersFromContextPolicy())
                .buildAsyncClient();
    }

    @Test
    public void testSendRequestMethodWithContext() {
        String requestIdKey = "x-ms-client-request-id";
        String requestIdValue = UUID.randomUUID().toString();
        HttpHeaders requestHeaders = new HttpHeaders().set(requestIdKey, requestIdValue);

        HttpRequest request = new HttpRequest(HttpMethod.GET, "https://httpbin.org/headers");
        Response<BinaryData> response = client.sendRequest(request,
                new Context(AddHeadersFromContextPolicy.AZURE_REQUEST_HTTP_HEADERS_KEY, requestHeaders));
        Assertions.assertEquals(200, response.getStatusCode());
        Map<String, Object> responseJson = (Map<String, Object>) response.getValue().toObject(Object.class);
        Map<String, String> headersJson = (Map<String, String>) responseJson.get("headers");
        String requestIdKeyInResponse = headersJson.keySet().stream()
                .filter(requestIdKey::equalsIgnoreCase)
                .findFirst().get();
        Assertions.assertEquals(requestIdValue, headersJson.get(requestIdKeyInResponse));

        response = asyncClient.sendRequest(request)
                .contextWrite(context -> context.put(AddHeadersFromContextPolicy.AZURE_REQUEST_HTTP_HEADERS_KEY, requestHeaders)).block();
        Assertions.assertEquals(200, response.getStatusCode());
        responseJson = (Map<String, Object>) response.getValue().toObject(Object.class);
        headersJson = (Map<String, String>) responseJson.get("headers");
        Assertions.assertEquals(requestIdValue, headersJson.get(requestIdKeyInResponse));
    }
}
