// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.specialheaders.clientrequestid;

import com.azure.specialheaders.xmsclientrequestid.XmsClientRequestIdClient;
import com.azure.specialheaders.xmsclientrequestid.XmsClientRequestIdClientBuilder;
import org.junit.jupiter.api.Test;

public class RequestIdTests {

    private final XmsClientRequestIdClient client = new XmsClientRequestIdClientBuilder().buildClient();

    @Test
    public void testRequestId() {
        client.get();
    }
}
