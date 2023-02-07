// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.enums.fixed.generated;

import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.test.TestBase;
import com.azure.core.test.TestMode;
import com.enums.fixed.FixedClient;
import com.enums.fixed.FixedClientBuilder;

class FixedClientTestBase extends TestBase {
    protected FixedClient fixedClient;

    @Override
    protected void beforeTest() {
        FixedClientBuilder fixedClientbuilder =
                new FixedClientBuilder()
                        .httpClient(HttpClient.createDefault())
                        .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            fixedClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            fixedClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        fixedClient = fixedClientbuilder.buildClient();
    }
}
