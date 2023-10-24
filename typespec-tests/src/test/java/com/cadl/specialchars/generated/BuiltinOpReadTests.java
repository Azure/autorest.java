// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.specialchars.generated;

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
import com.cadl.specialchars.SpecialCharsAsyncClient;
import com.cadl.specialchars.SpecialCharsClient;
import com.cadl.specialchars.SpecialCharsClientBuilder;
import com.cadl.specialchars.implementation.SpecialCharsClientImpl;
import com.cadl.specialchars.models.Resource;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

@Disabled
public final class BuiltinOpReadTests extends SpecialCharsClientTestBase {
    @Test
    @Disabled
    public void testBuiltinOpReadTests() {
        // method invocation
        Resource response = specialCharsClient.read(null);

        // response assertion
        Assertions.assertNotNull(response);
    }
}
