// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.cadl.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServerTests {

    @Test
    public void serverTests() {
        // use default server
        ServerClient client = new ServerClientBuilder().buildClient();
        Assertions.assertEquals(200, client.statusWithResponse(200, null).getStatusCode());

        // use specified server
        client = new ServerClientBuilder()
                .domain("httpbin")
                .tld("org")
                .buildClient();
        Assertions.assertEquals(204, client.statusWithResponse(204, null).getStatusCode());
    }
}
