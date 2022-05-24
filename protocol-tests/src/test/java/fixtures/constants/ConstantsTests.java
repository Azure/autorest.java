// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.constants;

import com.azure.core.http.rest.RequestOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ConstantsTests {

    private static AutoRestSwaggerConstantServiceClient client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestSwaggerConstantServiceClientBuilder().buildClient();
    }

    @Test
    public void putClientConstantsTests() {
        RequestOptions options = new RequestOptions()
                // we should not set these parameters, as they are from client
                .addHeader("header-constant", "true");
        Assertions.assertEquals(200, client.putClientConstantsWithResponse(options).getStatusCode());
    }
}
