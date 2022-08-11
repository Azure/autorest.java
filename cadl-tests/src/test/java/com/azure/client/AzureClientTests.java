// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.client;

import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AzureClientTests {

    @Test
    public void testHelloWorld() {
        AzureClient client = new AzureClientBuilder().endpoint("http://localhost:3000").buildClient();
        Response<BinaryData> response = client.worldWithResponse(null);
        BinaryData data = response.getValue();
        Assertions.assertEquals("Hello World!", data.toObject(String.class));
    }
}
