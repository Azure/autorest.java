// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.optional.generated;

// The Java test files under 'generated' package are generated for your reference.
// If you wish to modify these files, please copy them out of the 'generated' package, and modify there.
// See https://aka.ms/azsdk/dpg/java/tests for guide on adding a test.

import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.test.TestMode;
import com.azure.core.test.TestProxyTestBase;
import com.type.property.optional.BooleanLiteralClient;
import com.type.property.optional.BytesClient;
import com.type.property.optional.CollectionsByteClient;
import com.type.property.optional.CollectionsModelClient;
import com.type.property.optional.DatetimeOperationClient;
import com.type.property.optional.DurationOperationClient;
import com.type.property.optional.FloatLiteralClient;
import com.type.property.optional.IntLiteralClient;
import com.type.property.optional.OptionalClientBuilder;
import com.type.property.optional.PlaindateClient;
import com.type.property.optional.PlaintimeClient;
import com.type.property.optional.RequiredAndOptionalClient;
import com.type.property.optional.StringLiteralClient;
import com.type.property.optional.StringOperationClient;
import com.type.property.optional.UnionFloatLiteralClient;
import com.type.property.optional.UnionIntLiteralClient;
import com.type.property.optional.UnionStringLiteralClient;

class OptionalClientTestBase extends TestProxyTestBase {
    protected StringOperationClient stringOperationClient;

    protected BytesClient bytesClient;

    protected DatetimeOperationClient datetimeOperationClient;

    protected DurationOperationClient durationOperationClient;

    protected PlaindateClient plaindateClient;

    protected PlaintimeClient plaintimeClient;

    protected CollectionsByteClient collectionsByteClient;

    protected CollectionsModelClient collectionsModelClient;

    protected StringLiteralClient stringLiteralClient;

    protected IntLiteralClient intLiteralClient;

    protected FloatLiteralClient floatLiteralClient;

    protected BooleanLiteralClient booleanLiteralClient;

    protected UnionStringLiteralClient unionStringLiteralClient;

    protected UnionIntLiteralClient unionIntLiteralClient;

    protected UnionFloatLiteralClient unionFloatLiteralClient;

    protected RequiredAndOptionalClient requiredAndOptionalClient;

    @Override
    protected void beforeTest() {
        OptionalClientBuilder stringOperationClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            stringOperationClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            stringOperationClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        stringOperationClient = stringOperationClientbuilder.buildStringOperationClient();

        OptionalClientBuilder bytesClientbuilder = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            bytesClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            bytesClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        bytesClient = bytesClientbuilder.buildBytesClient();

        OptionalClientBuilder datetimeOperationClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            datetimeOperationClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            datetimeOperationClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        datetimeOperationClient = datetimeOperationClientbuilder.buildDatetimeOperationClient();

        OptionalClientBuilder durationOperationClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            durationOperationClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            durationOperationClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        durationOperationClient = durationOperationClientbuilder.buildDurationOperationClient();

        OptionalClientBuilder plaindateClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            plaindateClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            plaindateClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        plaindateClient = plaindateClientbuilder.buildPlaindateClient();

        OptionalClientBuilder plaintimeClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            plaintimeClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            plaintimeClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        plaintimeClient = plaintimeClientbuilder.buildPlaintimeClient();

        OptionalClientBuilder collectionsByteClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            collectionsByteClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            collectionsByteClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        collectionsByteClient = collectionsByteClientbuilder.buildCollectionsByteClient();

        OptionalClientBuilder collectionsModelClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            collectionsModelClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            collectionsModelClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        collectionsModelClient = collectionsModelClientbuilder.buildCollectionsModelClient();

        OptionalClientBuilder stringLiteralClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            stringLiteralClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            stringLiteralClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        stringLiteralClient = stringLiteralClientbuilder.buildStringLiteralClient();

        OptionalClientBuilder intLiteralClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            intLiteralClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            intLiteralClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        intLiteralClient = intLiteralClientbuilder.buildIntLiteralClient();

        OptionalClientBuilder floatLiteralClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            floatLiteralClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            floatLiteralClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        floatLiteralClient = floatLiteralClientbuilder.buildFloatLiteralClient();

        OptionalClientBuilder booleanLiteralClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            booleanLiteralClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            booleanLiteralClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        booleanLiteralClient = booleanLiteralClientbuilder.buildBooleanLiteralClient();

        OptionalClientBuilder unionStringLiteralClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            unionStringLiteralClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            unionStringLiteralClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        unionStringLiteralClient = unionStringLiteralClientbuilder.buildUnionStringLiteralClient();

        OptionalClientBuilder unionIntLiteralClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            unionIntLiteralClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            unionIntLiteralClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        unionIntLiteralClient = unionIntLiteralClientbuilder.buildUnionIntLiteralClient();

        OptionalClientBuilder unionFloatLiteralClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            unionFloatLiteralClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            unionFloatLiteralClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        unionFloatLiteralClient = unionFloatLiteralClientbuilder.buildUnionFloatLiteralClient();

        OptionalClientBuilder requiredAndOptionalClientbuilder
            = new OptionalClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            requiredAndOptionalClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            requiredAndOptionalClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        requiredAndOptionalClient = requiredAndOptionalClientbuilder.buildRequiredAndOptionalClient();

    }
}
