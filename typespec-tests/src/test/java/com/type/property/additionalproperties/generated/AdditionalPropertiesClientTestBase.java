// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.additionalproperties.generated;

// The Java test files under 'generated' package are generated for your reference.
// If you wish to modify these files, please copy them out of the 'generated' package, and modify there.
// See https://aka.ms/azsdk/dpg/java/tests for guide on adding a test.

import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.test.TestMode;
import com.azure.core.test.TestProxyTestBase;
import com.type.property.additionalproperties.AdditionalPropertiesClientBuilder;
import com.type.property.additionalproperties.ExtendsFloatClient;
import com.type.property.additionalproperties.ExtendsModelArrayClient;
import com.type.property.additionalproperties.ExtendsModelClient;
import com.type.property.additionalproperties.ExtendsStringClient;
import com.type.property.additionalproperties.ExtendsUnknownClient;
import com.type.property.additionalproperties.ExtendsUnknownDerivedClient;
import com.type.property.additionalproperties.ExtendsUnknownDiscriminatedClient;
import com.type.property.additionalproperties.IsFloatClient;
import com.type.property.additionalproperties.IsModelArrayClient;
import com.type.property.additionalproperties.IsModelClient;
import com.type.property.additionalproperties.IsStringClient;
import com.type.property.additionalproperties.IsUnknownClient;
import com.type.property.additionalproperties.IsUnknownDerivedClient;
import com.type.property.additionalproperties.IsUnknownDiscriminatedClient;

class AdditionalPropertiesClientTestBase extends TestProxyTestBase {
    protected ExtendsUnknownClient extendsUnknownClient;

    protected ExtendsUnknownDerivedClient extendsUnknownDerivedClient;

    protected ExtendsUnknownDiscriminatedClient extendsUnknownDiscriminatedClient;

    protected IsUnknownClient isUnknownClient;

    protected IsUnknownDerivedClient isUnknownDerivedClient;

    protected IsUnknownDiscriminatedClient isUnknownDiscriminatedClient;

    protected ExtendsStringClient extendsStringClient;

    protected IsStringClient isStringClient;

    protected ExtendsFloatClient extendsFloatClient;

    protected IsFloatClient isFloatClient;

    protected ExtendsModelClient extendsModelClient;

    protected IsModelClient isModelClient;

    protected ExtendsModelArrayClient extendsModelArrayClient;

    protected IsModelArrayClient isModelArrayClient;

    @Override
    protected void beforeTest() {
        AdditionalPropertiesClientBuilder extendsUnknownClientbuilder
            = new AdditionalPropertiesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            extendsUnknownClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            extendsUnknownClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        extendsUnknownClient = extendsUnknownClientbuilder.buildExtendsUnknownClient();

        AdditionalPropertiesClientBuilder extendsUnknownDerivedClientbuilder
            = new AdditionalPropertiesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            extendsUnknownDerivedClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            extendsUnknownDerivedClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        extendsUnknownDerivedClient = extendsUnknownDerivedClientbuilder.buildExtendsUnknownDerivedClient();

        AdditionalPropertiesClientBuilder extendsUnknownDiscriminatedClientbuilder
            = new AdditionalPropertiesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            extendsUnknownDiscriminatedClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            extendsUnknownDiscriminatedClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        extendsUnknownDiscriminatedClient
            = extendsUnknownDiscriminatedClientbuilder.buildExtendsUnknownDiscriminatedClient();

        AdditionalPropertiesClientBuilder isUnknownClientbuilder
            = new AdditionalPropertiesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            isUnknownClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            isUnknownClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        isUnknownClient = isUnknownClientbuilder.buildIsUnknownClient();

        AdditionalPropertiesClientBuilder isUnknownDerivedClientbuilder
            = new AdditionalPropertiesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            isUnknownDerivedClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            isUnknownDerivedClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        isUnknownDerivedClient = isUnknownDerivedClientbuilder.buildIsUnknownDerivedClient();

        AdditionalPropertiesClientBuilder isUnknownDiscriminatedClientbuilder
            = new AdditionalPropertiesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            isUnknownDiscriminatedClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            isUnknownDiscriminatedClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        isUnknownDiscriminatedClient = isUnknownDiscriminatedClientbuilder.buildIsUnknownDiscriminatedClient();

        AdditionalPropertiesClientBuilder extendsStringClientbuilder
            = new AdditionalPropertiesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            extendsStringClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            extendsStringClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        extendsStringClient = extendsStringClientbuilder.buildExtendsStringClient();

        AdditionalPropertiesClientBuilder isStringClientbuilder
            = new AdditionalPropertiesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            isStringClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            isStringClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        isStringClient = isStringClientbuilder.buildIsStringClient();

        AdditionalPropertiesClientBuilder extendsFloatClientbuilder
            = new AdditionalPropertiesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            extendsFloatClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            extendsFloatClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        extendsFloatClient = extendsFloatClientbuilder.buildExtendsFloatClient();

        AdditionalPropertiesClientBuilder isFloatClientbuilder
            = new AdditionalPropertiesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            isFloatClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            isFloatClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        isFloatClient = isFloatClientbuilder.buildIsFloatClient();

        AdditionalPropertiesClientBuilder extendsModelClientbuilder
            = new AdditionalPropertiesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            extendsModelClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            extendsModelClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        extendsModelClient = extendsModelClientbuilder.buildExtendsModelClient();

        AdditionalPropertiesClientBuilder isModelClientbuilder
            = new AdditionalPropertiesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            isModelClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            isModelClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        isModelClient = isModelClientbuilder.buildIsModelClient();

        AdditionalPropertiesClientBuilder extendsModelArrayClientbuilder
            = new AdditionalPropertiesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            extendsModelArrayClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            extendsModelArrayClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        extendsModelArrayClient = extendsModelArrayClientbuilder.buildExtendsModelArrayClient();

        AdditionalPropertiesClientBuilder isModelArrayClientbuilder
            = new AdditionalPropertiesClientBuilder().httpClient(HttpClient.createDefault())
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            isModelArrayClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            isModelArrayClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        isModelArrayClient = isModelArrayClientbuilder.buildIsModelArrayClient();

    }
}
