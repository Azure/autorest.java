// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.client.structure.service.generated;

// The Java test files under 'generated' package are generated for your reference.
// If you wish to modify these files, please copy them out of the 'generated' package, and modify there.
// See https://aka.ms/azsdk/dpg/java/tests for guide on adding a test.

import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.test.TestMode;
import com.azure.core.test.TestProxyTestBase;
import com.azure.core.util.Configuration;
import com.client.structure.service.GroupClient;
import com.client.structure.service.RenamedOperationClient;
import com.client.structure.service.RenamedOperationClientBuilder;

class RenamedOperationClientTestBase extends TestProxyTestBase {
    protected RenamedOperationClient renamedOperationClient;

    protected GroupClient groupClient;

    @Override
    protected void beforeTest() {
        RenamedOperationClientBuilder renamedOperationClientbuilder =
                new RenamedOperationClientBuilder()
                        .endpoint(Configuration.getGlobalConfiguration().get("ENDPOINT", "endpoint"))
                        .client(Configuration.getGlobalConfiguration().get("CLIENT", "client"))
                        .httpClient(HttpClient.createDefault())
                        .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            renamedOperationClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            renamedOperationClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        renamedOperationClient = renamedOperationClientbuilder.buildClient();

        RenamedOperationClientBuilder groupClientbuilder =
                new RenamedOperationClientBuilder()
                        .endpoint(Configuration.getGlobalConfiguration().get("ENDPOINT", "endpoint"))
                        .client(Configuration.getGlobalConfiguration().get("CLIENT", "client"))
                        .httpClient(HttpClient.createDefault())
                        .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            groupClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            groupClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        groupClient = groupClientbuilder.buildGroupClient();
    }
}
