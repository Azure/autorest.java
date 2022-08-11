// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AzureClientTests {

    @Test
    public void testHelloWorld() {
        AzureClient client = new AzureClientBuilder().endpoint("http://localhost:3000").buildClient();
        String value = client.world();
        Assertions.assertEquals("Hello World!", value);
    }
}
