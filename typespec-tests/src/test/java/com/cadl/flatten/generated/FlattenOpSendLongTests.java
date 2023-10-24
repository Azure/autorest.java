// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.flatten.generated;

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
import com.cadl.flatten.FlattenAsyncClient;
import com.cadl.flatten.FlattenClient;
import com.cadl.flatten.FlattenClientBuilder;
import com.cadl.flatten.FlattenServiceVersion;
import com.cadl.flatten.implementation.FlattenClientImpl;
import com.cadl.flatten.models.SendLongOptions;
import com.cadl.flatten.models.User;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

@Disabled
public final class FlattenOpSendLongTests extends FlattenClientTestBase {
    @Test
    @Disabled
    public void testFlattenOpSendLongTests() {
        // method invocation
        flattenClient.sendLong(new SendLongOptions("myRequiredId", "myRequiredInput", 11).setFilter("name=myName").setUser(new User("myOptionalUser")).setDataIntOptional(12).setDataLong(13L).setDataFloat(14.0D));
    }
}
