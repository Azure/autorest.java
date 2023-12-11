// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.additionalproperties;

import com.type.property.additionalproperties.models.IsFloatAdditionalProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class IsFloatClientTest {
    private final IsFloatClient client = new AdditionalPropertiesClientBuilder().buildIsFloatClient();

    @Test
    public void testPullAndGet() {
        IsFloatAdditionalProperties body = new IsFloatAdditionalProperties(42.42);
        Map<String, Double> propertyMap = new LinkedHashMap<>();
        propertyMap.put("prop", 42.42);
        body.setAdditionalProperties(propertyMap);
        client.put(body);

        IsFloatAdditionalProperties properties = client.get();
        Assertions.assertNotNull(properties);
        Assertions.assertNotNull(properties.getAdditionalProperties());
        Assertions.assertIterableEquals(propertyMap.entrySet(), properties.getAdditionalProperties().entrySet());
    }
}
