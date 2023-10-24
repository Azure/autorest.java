// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.builtin.generated;

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
import com.azure.core.util.Base64Url;
import com.azure.core.util.Configuration;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.core.util.serializer.SerializerAdapter;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.cadl.builtin.BuiltinAsyncClient;
import com.cadl.builtin.BuiltinClient;
import com.cadl.builtin.BuiltinClientBuilder;
import com.cadl.builtin.implementation.BuiltinClientImpl;
import com.cadl.builtin.models.Builtin;
import com.cadl.builtin.models.Encoded;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

@Disabled
public final class BuiltinOpReadTests extends BuiltinClientTestBase {
    @Test
    @Disabled
    public void testBuiltinOpReadTests() {
        // method invocation
        Builtin response = builtinClient.read(null, null, null, "myFilter", null, null);

        // response assertion
        Assertions.assertNotNull(response);
        // verify property "booleanProperty"
        Assertions.assertEquals(true, response.isBooleanProperty());
        // verify property "string"
        Assertions.assertEquals("myString", response.getString());
        // verify property "safeint"
        Assertions.assertEquals(32L, response.getSafeint());
        // verify property "longProperty"
        Assertions.assertEquals(64L, response.getLongProperty());
        // verify property "floatProperty"
        Assertions.assertEquals(32.0, response.getFloatProperty());
        // verify property "doubleProperty"
        Assertions.assertEquals(64.0, response.getDoubleProperty());
        // verify property "duration"
        Assertions.assertNotNull(response.getDuration());
        // verify property "date"
        Assertions.assertNotNull(response.getDate());
        // verify property "dateTime"
        Assertions.assertNotNull(response.getDateTime());
        // verify property "stringList"
        List<String> responseStringList = response.getStringList();
        Assertions.assertEquals("a", responseStringList.iterator().next());
        // verify property "url"
        Assertions.assertEquals("https://www.github.com", response.getUrl());
        // verify property "nullableFloatDict"
        Assertions.assertNotNull(response.getNullableFloatDict());
        // verify property "encoded"
        Encoded responseEncoded = response.getEncoded();
        Assertions.assertNotNull(responseEncoded);
        Assertions.assertNotNull(responseEncoded.getTimeInSeconds());
        Assertions.assertNotNull(responseEncoded.getTimeInSecondsFraction());
        Assertions.assertNotNull(responseEncoded.getDateTime());
        Assertions.assertNotNull(responseEncoded.getDateTimeRfc7231());
        Assertions.assertNotNull(responseEncoded.getUnixTimestamp());
        Assertions.assertNotNull(responseEncoded.getBase64());
        Assertions.assertNotNull(responseEncoded.getBase64Url());
    }
}
