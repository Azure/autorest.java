// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.client.structure;

import com.client.structure.service.ServiceClientClient;
import com.client.structure.service.ServiceClientClientBuilder;
import org.junit.jupiter.api.Test;

public class DefaultClientTests {

    private final ServiceClientClient client = new ServiceClientClientBuilder()
            .endpoint("http://localhost:3000")
            .client("default").buildClient();

    @Test
    public void testClient() {
        client.one();
        client.two();
        client.three();
        client.four();
        client.five();
        client.six();
    }
}
