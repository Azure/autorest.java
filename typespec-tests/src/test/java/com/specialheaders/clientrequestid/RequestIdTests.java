// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.specialheaders.clientrequestid;

import org.junit.jupiter.api.Test;

public class RequestIdTests {

    private final ClientRequestIdClient client = new ClientRequestIdClientBuilder().buildClient();

    @Test
    public void testRequestId() {
        client.get();
    }
}
