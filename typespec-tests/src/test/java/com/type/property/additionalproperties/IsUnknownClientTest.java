// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.additionalproperties;

import com.type.property.additionalproperties.models.IsUnknownAdditionalProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class IsUnknownClientTest {
    private final IsUnknownClient client = new AdditionalPropertiesClientBuilder().buildIsUnknownClient();

    @Test
    public void testPullAndGet() {
        IsUnknownAdditionalProperties body =
                new IsUnknownAdditionalProperties("IsUnknownAdditionalProperties");
        Map<String, Object> propertyMap = new LinkedHashMap<>();
        propertyMap.put("prop1", 32);
        propertyMap.put("prop2", true) ;
        propertyMap.put("prop3", "abc") ;
        body.setAdditionalProperties(propertyMap);
        client.put(body);

        IsUnknownAdditionalProperties properties = client.get();
        Assertions.assertNotNull(properties);
        Assertions.assertNotNull(properties.getAdditionalProperties());
        Assertions.assertEquals("IsUnknownAdditionalProperties", properties.getName());
        Assertions.assertIterableEquals(propertyMap.entrySet(), properties.getAdditionalProperties().entrySet());
    }
}
