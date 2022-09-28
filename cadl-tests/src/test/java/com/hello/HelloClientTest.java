// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.hello;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloClientTest {

    HelloClient client = new HelloClientBuilder().buildClient();

    @Test
    void world() {
        String response = client.world();
        Assertions.assertEquals("Hello World!", response);
    }
}