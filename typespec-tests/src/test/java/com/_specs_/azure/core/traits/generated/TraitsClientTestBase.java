// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com._specs_.azure.core.traits.generated;

// The Java test files under 'generated' package are generated for your reference.
// If you wish to modify these files, please copy them out of the 'generated' package, and modify there.
// See https://aka.ms/azsdk/dpg/java/tests for guide on adding a test.
import com._specs_.azure.core.traits.TraitsClient;
import com._specs_.azure.core.traits.TraitsClientBuilder;
import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.test.TestMode;
import com.azure.core.test.TestProxyTestBase;

class TraitsClientTestBase extends TestProxyTestBase {

    protected TraitsClient traitsClient;

    @Override
    protected void beforeTest() {
        TraitsClientBuilder traitsClientbuilder = new TraitsClientBuilder().httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            traitsClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            traitsClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        traitsClient = traitsClientbuilder.buildClient();
    }
}
