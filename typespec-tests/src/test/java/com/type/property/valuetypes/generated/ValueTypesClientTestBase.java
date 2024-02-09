// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.valuetypes.generated;

// The Java test files under 'generated' package are generated for your reference.
// If you wish to modify these files, please copy them out of the 'generated' package, and modify there.
// See https://aka.ms/azsdk/dpg/java/tests for guide on adding a test.

import com.azure.core.http.HttpClient;

import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;

import com.azure.core.test.TestMode;
import com.azure.core.test.TestProxyTestBase;

import com.type.property.valuetypes.BooleanLiteralClient;

import com.type.property.valuetypes.BooleanOperationClient;

import com.type.property.valuetypes.BytesClient;

import com.type.property.valuetypes.CollectionsIntClient;

import com.type.property.valuetypes.CollectionsModelClient;

import com.type.property.valuetypes.CollectionsStringClient;

import com.type.property.valuetypes.DatetimeOperationClient;

import com.type.property.valuetypes.Decimal128Client;

import com.type.property.valuetypes.DecimalClient;

import com.type.property.valuetypes.DictionaryStringClient;

import com.type.property.valuetypes.DurationOperationClient;

import com.type.property.valuetypes.EnumClient;

import com.type.property.valuetypes.ExtensibleEnumClient;

import com.type.property.valuetypes.FloatLiteralClient;

import com.type.property.valuetypes.FloatOperationClient;

import com.type.property.valuetypes.IntClient;

import com.type.property.valuetypes.IntLiteralClient;

import com.type.property.valuetypes.ModelClient;

import com.type.property.valuetypes.NeverClient;

import com.type.property.valuetypes.StringLiteralClient;

import com.type.property.valuetypes.StringOperationClient;

import com.type.property.valuetypes.UnionFloatLiteralClient;

import com.type.property.valuetypes.UnionIntLiteralClient;

import com.type.property.valuetypes.UnionStringLiteralClient;

import com.type.property.valuetypes.UnknownArrayClient;

import com.type.property.valuetypes.UnknownDictClient;

import com.type.property.valuetypes.UnknownIntClient;

import com.type.property.valuetypes.UnknownStringClient;
import com.type.property.valuetypes.ValueTypesClientBuilder;

class ValueTypesClientTestBase extends TestProxyTestBase {
    protected BooleanOperationClient booleanOperationClient;

    protected StringOperationClient stringOperationClient;

    protected BytesClient bytesClient;

    protected IntClient intClient;

    protected FloatOperationClient floatOperationClient;

    protected DecimalClient decimalClient;

    protected Decimal128Client decimal128Client;

    protected DatetimeOperationClient datetimeOperationClient;

    protected DurationOperationClient durationOperationClient;

    protected EnumClient enumClient;

    protected ExtensibleEnumClient extensibleEnumClient;

    protected ModelClient modelClient;

    protected CollectionsStringClient collectionsStringClient;

    protected CollectionsIntClient collectionsIntClient;

    protected CollectionsModelClient collectionsModelClient;

    protected DictionaryStringClient dictionaryStringClient;

    protected NeverClient neverClient;

    protected UnknownStringClient unknownStringClient;

    protected UnknownIntClient unknownIntClient;

    protected UnknownDictClient unknownDictClient;

    protected UnknownArrayClient unknownArrayClient;

    protected StringLiteralClient stringLiteralClient;

    protected IntLiteralClient intLiteralClient;

    protected FloatLiteralClient floatLiteralClient;

    protected BooleanLiteralClient booleanLiteralClient;

    protected UnionStringLiteralClient unionStringLiteralClient;

    protected UnionIntLiteralClient unionIntLiteralClient;

    protected UnionFloatLiteralClient unionFloatLiteralClient;

    @Override
    protected void beforeTest() {
        ValueTypesClientBuilder booleanOperationClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            booleanOperationClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            booleanOperationClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        booleanOperationClient = booleanOperationClientbuilder.buildBooleanOperationClient();

        ValueTypesClientBuilder stringOperationClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            stringOperationClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            stringOperationClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        stringOperationClient = stringOperationClientbuilder.buildStringOperationClient();

        ValueTypesClientBuilder bytesClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            bytesClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            bytesClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        bytesClient = bytesClientbuilder.buildBytesClient();

        ValueTypesClientBuilder intClientbuilder = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            intClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            intClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        intClient = intClientbuilder.buildIntClient();

        ValueTypesClientBuilder floatOperationClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            floatOperationClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            floatOperationClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        floatOperationClient = floatOperationClientbuilder.buildFloatOperationClient();

        ValueTypesClientBuilder decimalClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            decimalClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            decimalClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        decimalClient = decimalClientbuilder.buildDecimalClient();

        ValueTypesClientBuilder decimal128Clientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            decimal128Clientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            decimal128Clientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        decimal128Client = decimal128Clientbuilder.buildDecimal128Client();

        ValueTypesClientBuilder datetimeOperationClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            datetimeOperationClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            datetimeOperationClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        datetimeOperationClient = datetimeOperationClientbuilder.buildDatetimeOperationClient();

        ValueTypesClientBuilder durationOperationClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            durationOperationClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            durationOperationClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        durationOperationClient = durationOperationClientbuilder.buildDurationOperationClient();

        ValueTypesClientBuilder enumClientbuilder = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            enumClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            enumClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        enumClient = enumClientbuilder.buildEnumClient();

        ValueTypesClientBuilder extensibleEnumClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            extensibleEnumClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            extensibleEnumClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        extensibleEnumClient = extensibleEnumClientbuilder.buildExtensibleEnumClient();

        ValueTypesClientBuilder modelClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            modelClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            modelClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        modelClient = modelClientbuilder.buildModelClient();

        ValueTypesClientBuilder collectionsStringClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            collectionsStringClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            collectionsStringClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        collectionsStringClient = collectionsStringClientbuilder.buildCollectionsStringClient();

        ValueTypesClientBuilder collectionsIntClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            collectionsIntClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            collectionsIntClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        collectionsIntClient = collectionsIntClientbuilder.buildCollectionsIntClient();

        ValueTypesClientBuilder collectionsModelClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            collectionsModelClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            collectionsModelClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        collectionsModelClient = collectionsModelClientbuilder.buildCollectionsModelClient();

        ValueTypesClientBuilder dictionaryStringClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            dictionaryStringClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            dictionaryStringClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        dictionaryStringClient = dictionaryStringClientbuilder.buildDictionaryStringClient();

        ValueTypesClientBuilder neverClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            neverClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            neverClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        neverClient = neverClientbuilder.buildNeverClient();

        ValueTypesClientBuilder unknownStringClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            unknownStringClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            unknownStringClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        unknownStringClient = unknownStringClientbuilder.buildUnknownStringClient();

        ValueTypesClientBuilder unknownIntClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            unknownIntClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            unknownIntClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        unknownIntClient = unknownIntClientbuilder.buildUnknownIntClient();

        ValueTypesClientBuilder unknownDictClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            unknownDictClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            unknownDictClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        unknownDictClient = unknownDictClientbuilder.buildUnknownDictClient();

        ValueTypesClientBuilder unknownArrayClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            unknownArrayClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            unknownArrayClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        unknownArrayClient = unknownArrayClientbuilder.buildUnknownArrayClient();

        ValueTypesClientBuilder stringLiteralClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            stringLiteralClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            stringLiteralClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        stringLiteralClient = stringLiteralClientbuilder.buildStringLiteralClient();

        ValueTypesClientBuilder intLiteralClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            intLiteralClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            intLiteralClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        intLiteralClient = intLiteralClientbuilder.buildIntLiteralClient();

        ValueTypesClientBuilder floatLiteralClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            floatLiteralClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            floatLiteralClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        floatLiteralClient = floatLiteralClientbuilder.buildFloatLiteralClient();

        ValueTypesClientBuilder booleanLiteralClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            booleanLiteralClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            booleanLiteralClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        booleanLiteralClient = booleanLiteralClientbuilder.buildBooleanLiteralClient();

        ValueTypesClientBuilder unionStringLiteralClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            unionStringLiteralClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            unionStringLiteralClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        unionStringLiteralClient = unionStringLiteralClientbuilder.buildUnionStringLiteralClient();

        ValueTypesClientBuilder unionIntLiteralClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            unionIntLiteralClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            unionIntLiteralClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        unionIntLiteralClient = unionIntLiteralClientbuilder.buildUnionIntLiteralClient();

        ValueTypesClientBuilder unionFloatLiteralClientbuilder
            = new ValueTypesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            unionFloatLiteralClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            unionFloatLiteralClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        unionFloatLiteralClient = unionFloatLiteralClientbuilder.buildUnionFloatLiteralClient();

    }
}
