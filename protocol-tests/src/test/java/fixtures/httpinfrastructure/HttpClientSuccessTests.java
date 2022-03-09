// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.httpinfrastructure;

import com.azure.core.http.rest.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HttpClientSuccessTests {

    private static HttpSuccessClient client;

    @BeforeAll
    public static void setup() {
        client = new HttpSuccessClientBuilder().buildClient();
    }

    @Test
    public void head404() {
        Response<Boolean> response = client.head404WithResponse(null);
        Assertions.assertEquals(404, response.getStatusCode());
        Assertions.assertFalse(response.getValue());
    }
}
