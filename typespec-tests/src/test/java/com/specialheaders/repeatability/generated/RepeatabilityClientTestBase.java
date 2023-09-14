// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.specialheaders.repeatability.generated;

// The Java test files under 'generated' package are generated for your reference.
// If you wish to modify these files, please copy them out of the 'generated' package, and modify there.
// See https://aka.ms/azsdk/dpg/java/tests for guide on adding a test.

import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.test.TestMode;
import com.azure.core.test.TestProxyTestBase;
import com.specialheaders.repeatability.RepeatabilityClient;
import com.specialheaders.repeatability.RepeatabilityClientBuilder;

class RepeatabilityClientTestBase extends TestProxyTestBase {
    protected RepeatabilityClient repeatabilityClient;

    @Override
    protected void beforeTest() {
        RepeatabilityClientBuilder repeatabilityClientbuilder =
                new RepeatabilityClientBuilder()
                        .httpClient(HttpClient.createDefault())
                        .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            repeatabilityClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            repeatabilityClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        repeatabilityClient = repeatabilityClientbuilder.buildClient();
    }
}
