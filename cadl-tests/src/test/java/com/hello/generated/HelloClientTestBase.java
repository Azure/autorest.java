// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.hello.generated;

import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.test.TestBase;
import com.azure.core.test.TestMode;
import com.hello.HelloClient;
import com.hello.HelloClientBuilder;

class HelloClientTestBase extends TestBase {
    protected HelloClient helloClient;

    @Override
    protected void beforeTest() {
        HelloClientBuilder helloClientbuilder =
                new HelloClientBuilder()
                        .httpClient(HttpClient.createDefault())
                        .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            helloClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            helloClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        helloClient = helloClientbuilder.buildClient();
    }
}
