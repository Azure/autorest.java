// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.multicontenttypes.generated;

// The Java test files under 'generated' package are generated for your reference.
// If you wish to modify these files, please copy them out of the 'generated' package, and modify there.
// See https://aka.ms/azsdk/dpg/java/tests for guide on adding a test.

import com.azure.core.http.HttpClient;

import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;

import com.azure.core.test.TestMode;
import com.azure.core.test.TestProxyTestBase;
import com.azure.core.util.Configuration;

import com.cadl.multicontenttypes.MultiContentTypesClient;
import com.cadl.multicontenttypes.MultiContentTypesClientBuilder;

import com.cadl.multicontenttypes.MultipleContentTypesOnRequestClient;

import com.cadl.multicontenttypes.SingleContentTypeClient;

class MultiContentTypesClientTestBase extends TestProxyTestBase {
    protected MultiContentTypesClient multiContentTypesClient;

    protected SingleContentTypeClient singleContentTypeClient;

    protected MultipleContentTypesOnRequestClient multipleContentTypesOnRequestClient;

    @Override
    protected void beforeTest() {
        MultiContentTypesClientBuilder multiContentTypesClientbuilder = new MultiContentTypesClientBuilder()
            .endpoint(Configuration.getGlobalConfiguration().get("ENDPOINT", "endpoint"))
            .httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            multiContentTypesClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            multiContentTypesClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        multiContentTypesClient = multiContentTypesClientbuilder.buildClient();

        MultiContentTypesClientBuilder singleContentTypeClientbuilder = new MultiContentTypesClientBuilder()
            .endpoint(Configuration.getGlobalConfiguration().get("ENDPOINT", "endpoint"))
            .httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            singleContentTypeClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            singleContentTypeClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        singleContentTypeClient = singleContentTypeClientbuilder.buildSingleContentTypeClient();

        MultiContentTypesClientBuilder multipleContentTypesOnRequestClientbuilder = new MultiContentTypesClientBuilder()
            .endpoint(Configuration.getGlobalConfiguration().get("ENDPOINT", "endpoint"))
            .httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            multipleContentTypesOnRequestClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            multipleContentTypesOnRequestClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        multipleContentTypesOnRequestClient
            = multipleContentTypesOnRequestClientbuilder.buildMultipleContentTypesOnRequestClient();

    }
}
