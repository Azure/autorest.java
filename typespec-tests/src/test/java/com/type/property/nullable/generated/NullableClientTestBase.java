// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.type.property.nullable.generated;

// The Java test files under 'generated' package are generated for your reference.
// If you wish to modify these files, please copy them out of the 'generated' package, and modify there.
// See https://aka.ms/azsdk/dpg/java/tests for guide on adding a test.
import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.test.TestMode;
import com.azure.core.test.TestProxyTestBase;
import com.type.property.nullable.BytesClient;
import com.type.property.nullable.CollectionsByteClient;
import com.type.property.nullable.CollectionsModelClient;
import com.type.property.nullable.DatetimeOperationClient;
import com.type.property.nullable.DurationOperationClient;
import com.type.property.nullable.NullableClientBuilder;
import com.type.property.nullable.StringOperationClient;

class NullableClientTestBase extends TestProxyTestBase {

    protected StringOperationClient stringOperationClient;

    protected BytesClient bytesClient;

    protected DatetimeOperationClient datetimeOperationClient;

    protected DurationOperationClient durationOperationClient;

    protected CollectionsByteClient collectionsByteClient;

    protected CollectionsModelClient collectionsModelClient;

    @Override
    protected void beforeTest() {
        NullableClientBuilder stringOperationClientbuilder
            = new NullableClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            stringOperationClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            stringOperationClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        stringOperationClient = stringOperationClientbuilder.buildStringOperationClient();
        NullableClientBuilder bytesClientbuilder = new NullableClientBuilder().httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            bytesClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            bytesClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        bytesClient = bytesClientbuilder.buildBytesClient();
        NullableClientBuilder datetimeOperationClientbuilder
            = new NullableClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            datetimeOperationClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            datetimeOperationClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        datetimeOperationClient = datetimeOperationClientbuilder.buildDatetimeOperationClient();
        NullableClientBuilder durationOperationClientbuilder
            = new NullableClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            durationOperationClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            durationOperationClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        durationOperationClient = durationOperationClientbuilder.buildDurationOperationClient();
        NullableClientBuilder collectionsByteClientbuilder
            = new NullableClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            collectionsByteClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            collectionsByteClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        collectionsByteClient = collectionsByteClientbuilder.buildCollectionsByteClient();
        NullableClientBuilder collectionsModelClientbuilder
            = new NullableClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            collectionsModelClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            collectionsModelClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        collectionsModelClient = collectionsModelClientbuilder.buildCollectionsModelClient();
    }
}
