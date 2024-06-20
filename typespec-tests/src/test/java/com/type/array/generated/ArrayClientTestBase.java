// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.array.generated;

// The Java test files under 'generated' package are generated for your reference.
// If you wish to modify these files, please copy them out of the 'generated' package, and modify there.
// See https://aka.ms/azsdk/dpg/java/tests for guide on adding a test.

import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.test.TestMode;
import com.azure.core.test.TestProxyTestBase;
import com.type.array.ArrayClientBuilder;
import com.type.array.BooleanValueClient;
import com.type.array.DatetimeValueClient;
import com.type.array.DurationValueClient;
import com.type.array.Float32ValueClient;
import com.type.array.Int32ValueClient;
import com.type.array.Int64ValueClient;
import com.type.array.ModelValueClient;
import com.type.array.NullableBooleanValueClient;
import com.type.array.NullableFloatValueClient;
import com.type.array.NullableInt32ValueClient;
import com.type.array.NullableModelValueClient;
import com.type.array.NullableStringValueClient;
import com.type.array.StringValueClient;
import com.type.array.UnknownValueClient;

class ArrayClientTestBase extends TestProxyTestBase {
    protected Int32ValueClient int32ValueClient;

    protected Int64ValueClient int64ValueClient;

    protected BooleanValueClient booleanValueClient;

    protected StringValueClient stringValueClient;

    protected Float32ValueClient float32ValueClient;

    protected DatetimeValueClient datetimeValueClient;

    protected DurationValueClient durationValueClient;

    protected UnknownValueClient unknownValueClient;

    protected ModelValueClient modelValueClient;

    protected NullableFloatValueClient nullableFloatValueClient;

    protected NullableInt32ValueClient nullableInt32ValueClient;

    protected NullableBooleanValueClient nullableBooleanValueClient;

    protected NullableStringValueClient nullableStringValueClient;

    protected NullableModelValueClient nullableModelValueClient;

    @Override
    protected void beforeTest() {
        ArrayClientBuilder int32ValueClientbuilder = new ArrayClientBuilder().httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            int32ValueClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            int32ValueClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        int32ValueClient = int32ValueClientbuilder.buildInt32ValueClient();

        ArrayClientBuilder int64ValueClientbuilder = new ArrayClientBuilder().httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            int64ValueClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            int64ValueClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        int64ValueClient = int64ValueClientbuilder.buildInt64ValueClient();

        ArrayClientBuilder booleanValueClientbuilder = new ArrayClientBuilder().httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            booleanValueClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            booleanValueClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        booleanValueClient = booleanValueClientbuilder.buildBooleanValueClient();

        ArrayClientBuilder stringValueClientbuilder = new ArrayClientBuilder().httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            stringValueClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            stringValueClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        stringValueClient = stringValueClientbuilder.buildStringValueClient();

        ArrayClientBuilder float32ValueClientbuilder = new ArrayClientBuilder().httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            float32ValueClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            float32ValueClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        float32ValueClient = float32ValueClientbuilder.buildFloat32ValueClient();

        ArrayClientBuilder datetimeValueClientbuilder = new ArrayClientBuilder().httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            datetimeValueClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            datetimeValueClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        datetimeValueClient = datetimeValueClientbuilder.buildDatetimeValueClient();

        ArrayClientBuilder durationValueClientbuilder = new ArrayClientBuilder().httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            durationValueClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            durationValueClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        durationValueClient = durationValueClientbuilder.buildDurationValueClient();

        ArrayClientBuilder unknownValueClientbuilder = new ArrayClientBuilder().httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            unknownValueClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            unknownValueClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        unknownValueClient = unknownValueClientbuilder.buildUnknownValueClient();

        ArrayClientBuilder modelValueClientbuilder = new ArrayClientBuilder().httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            modelValueClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            modelValueClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        modelValueClient = modelValueClientbuilder.buildModelValueClient();

        ArrayClientBuilder nullableFloatValueClientbuilder
            = new ArrayClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            nullableFloatValueClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            nullableFloatValueClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        nullableFloatValueClient = nullableFloatValueClientbuilder.buildNullableFloatValueClient();

        ArrayClientBuilder nullableInt32ValueClientbuilder
            = new ArrayClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            nullableInt32ValueClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            nullableInt32ValueClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        nullableInt32ValueClient = nullableInt32ValueClientbuilder.buildNullableInt32ValueClient();

        ArrayClientBuilder nullableBooleanValueClientbuilder
            = new ArrayClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            nullableBooleanValueClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            nullableBooleanValueClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        nullableBooleanValueClient = nullableBooleanValueClientbuilder.buildNullableBooleanValueClient();

        ArrayClientBuilder nullableStringValueClientbuilder
            = new ArrayClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            nullableStringValueClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            nullableStringValueClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        nullableStringValueClient = nullableStringValueClientbuilder.buildNullableStringValueClient();

        ArrayClientBuilder nullableModelValueClientbuilder
            = new ArrayClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            nullableModelValueClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            nullableModelValueClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        nullableModelValueClient = nullableModelValueClientbuilder.buildNullableModelValueClient();

    }
}
