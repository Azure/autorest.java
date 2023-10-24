// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.encode.bytes.generated;

// The Java test files under 'generated' package are generated for your reference.
// If you wish to modify these files, please copy them out of the 'generated' package, and modify there.
// See https://aka.ms/azsdk/dpg/java/tests for guide on adding a test.

import com.azure.core.credential.AccessToken;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.test.TestMode;
import com.azure.core.test.TestProxyTestBase;
import com.azure.core.util.Configuration;
import com.azure.core.util.serializer.SerializerAdapter;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.encode.bytes.BytesClientBuilder;
import com.encode.bytes.HeaderAsyncClient;
import com.encode.bytes.HeaderClient;
import com.encode.bytes.PropertyAsyncClient;
import com.encode.bytes.PropertyClient;
import com.encode.bytes.QueryAsyncClient;
import com.encode.bytes.QueryClient;
import com.encode.bytes.RequestBodyAsyncClient;
import com.encode.bytes.RequestBodyClient;
import com.encode.bytes.ResponseBodyAsyncClient;
import com.encode.bytes.ResponseBodyClient;
import com.encode.bytes.implementation.BytesClientImpl;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

 class BytesClientTestBase extends TestProxyTestBase {
    protected QueryClient queryClient;

    protected PropertyClient propertyClient;

    protected HeaderClient headerClient;

    protected RequestBodyClient requestBodyClient;

    protected ResponseBodyClient responseBodyClient;

    @Override
    protected void beforeTest() {
        BytesClientBuilder queryClientbuilder = new BytesClientBuilder()
            .httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            queryClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            queryClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        queryClient = queryClientbuilder.buildQueryClient();

        BytesClientBuilder propertyClientbuilder = new BytesClientBuilder()
            .httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            propertyClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            propertyClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        propertyClient = propertyClientbuilder.buildPropertyClient();

        BytesClientBuilder headerClientbuilder = new BytesClientBuilder()
            .httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            headerClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            headerClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        headerClient = headerClientbuilder.buildHeaderClient();

        BytesClientBuilder requestBodyClientbuilder = new BytesClientBuilder()
            .httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            requestBodyClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            requestBodyClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        requestBodyClient = requestBodyClientbuilder.buildRequestBodyClient();

        BytesClientBuilder responseBodyClientbuilder = new BytesClientBuilder()
            .httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            responseBodyClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            responseBodyClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        responseBodyClient = responseBodyClientbuilder.buildResponseBodyClient();

    }
}
