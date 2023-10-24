// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.projection.projectedname.generated;

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
import com.projection.projectedname.ProjectedNameAsyncClient;
import com.projection.projectedname.ProjectedNameClient;
import com.projection.projectedname.ProjectedNameClientBuilder;
import com.projection.projectedname.PropertyAsyncClient;
import com.projection.projectedname.PropertyClient;
import com.projection.projectedname.implementation.ProjectedNameClientImpl;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

 class ProjectedNameClientTestBase extends TestProxyTestBase {
    protected ProjectedNameClient projectedNameClient;

    protected PropertyClient propertyClient;

    @Override
    protected void beforeTest() {
        ProjectedNameClientBuilder projectedNameClientbuilder = new ProjectedNameClientBuilder()
            .httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            projectedNameClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            projectedNameClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        projectedNameClient = projectedNameClientbuilder.buildClient();

        ProjectedNameClientBuilder propertyClientbuilder = new ProjectedNameClientBuilder()
            .httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            propertyClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            propertyClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        propertyClient = propertyClientbuilder.buildPropertyClient();

    }
}
