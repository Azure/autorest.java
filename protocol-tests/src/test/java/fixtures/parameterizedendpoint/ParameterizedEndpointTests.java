// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.parameterizedendpoint;

import com.azure.core.http.HttpMethod;
import com.azure.core.http.HttpRequest;
import com.azure.core.util.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ParameterizedEndpointTests {

    private static final String ENDPOINT = "http://localhost:3000";

    private static ParmaterizedEndpointAsyncClient asyncClient;
    private static ParmaterizedEndpointClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new ParmaterizedEndpointClientBuilder()
                .endpoint(ENDPOINT)
                .buildAsyncClient();
        client = new ParmaterizedEndpointClientBuilder()
                .endpoint(ENDPOINT)
                .buildClient();
    }

    @Test
    public void testParameterizedEndpoint() throws MalformedURLException {
        Assertions.assertEquals(ENDPOINT, asyncClient.getEndpoint());
        Assertions.assertEquals(ENDPOINT, client.getEndpoint());

        Assertions.assertEquals(200, client.getWithResponse(null).getStatusCode());

        HttpRequest request = new HttpRequest(HttpMethod.GET, new URL(client.getEndpoint() + "/parameterizedEndpoint/get"));
        Assertions.assertEquals(200, client.sendRequest(request, Context.NONE).getStatusCode());
    }
}
