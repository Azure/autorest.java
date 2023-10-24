// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.specialheaders.repeatability.generated;

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
import com.specialheaders.repeatability.RepeatabilityAsyncClient;
import com.specialheaders.repeatability.RepeatabilityClient;
import com.specialheaders.repeatability.RepeatabilityClientBuilder;
import com.specialheaders.repeatability.implementation.RepeatabilityClientImpl;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

 class RepeatabilityClientTestBase extends TestProxyTestBase {
    protected RepeatabilityClient repeatabilityClient;

    @Override
    protected void beforeTest() {
        RepeatabilityClientBuilder repeatabilityClientbuilder = new RepeatabilityClientBuilder()
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
