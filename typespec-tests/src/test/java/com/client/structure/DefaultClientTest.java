// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.client.structure;

import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.client.structure.service.ServiceClientClient;
import com.client.structure.service.ServiceClientClientBuilder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DefaultClientTest {

    private final ServiceClientClient client = new ServiceClientClientBuilder().client("default").httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS)).buildClient();

    @Disabled("mockapi bug")
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
