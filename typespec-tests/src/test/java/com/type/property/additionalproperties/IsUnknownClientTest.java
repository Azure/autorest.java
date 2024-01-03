// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.additionalproperties;

import com.type.property.additionalproperties.models.IsUnknownAdditionalProperties;
import com.type.property.additionalproperties.models.IsUnknownAdditionalPropertiesDerived;
import com.type.property.additionalproperties.models.IsUnknownAdditionalPropertiesDiscriminated;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class IsUnknownClientTest {
    private final IsUnknownClient client = new AdditionalPropertiesClientBuilder().buildIsUnknownClient();
    private final IsUnknownDerivedClient isUnknownDerivedClient = new AdditionalPropertiesClientBuilder().buildIsUnknownDerivedClient();
    private final IsUnknownDiscriminatedClient isUnknownDiscriminatedClient = new AdditionalPropertiesClientBuilder().buildIsUnknownDiscriminatedClient();

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

    @Test
    public void testIsUnknownDerivedClient() {
        Map<String, Object> additionalProperty = new LinkedHashMap<>();
        additionalProperty.put("name", "IsUnknownAdditionalProperties");
        additionalProperty.put("prop1", 32);
        additionalProperty.put("prop2", true) ;
        additionalProperty.put("prop3", "abc") ;

        IsUnknownAdditionalPropertiesDerived body = new IsUnknownAdditionalPropertiesDerived(314);
        body.setAge(2.71828);
        body.setAdditionalProperties(additionalProperty);
        isUnknownDerivedClient.put(body);

        IsUnknownAdditionalPropertiesDerived properties = isUnknownDerivedClient.get();
        Assertions.assertNotNull(properties);
        Assertions.assertNotNull(properties.getAdditionalProperties());
        Assertions.assertEquals(2.71828, properties.getAge());
        Assertions.assertEquals(314, properties.getIndex());
        Assertions.assertIterableEquals(additionalProperty.entrySet(), properties.getAdditionalProperties().entrySet());
    }

    @Test
    public void testIsUnknownDiscriminatedClient() {
        Map<String, Object> additionalProperty = new LinkedHashMap<>();
        additionalProperty.put("kind", "derived");
        additionalProperty.put("index", 314);
        additionalProperty.put("age", 2.71828) ;
        additionalProperty.put("prop1", 32);
        additionalProperty.put("prop2", true) ;
        additionalProperty.put("prop3", "abc") ;

        IsUnknownAdditionalPropertiesDiscriminated body = new IsUnknownAdditionalPropertiesDiscriminated("Derived");
        body.setAdditionalProperties(additionalProperty);
        isUnknownDiscriminatedClient.put(body);

        IsUnknownAdditionalPropertiesDiscriminated properties = isUnknownDiscriminatedClient.get();
        Assertions.assertNotNull(properties);
        Assertions.assertNotNull(properties.getAdditionalProperties());
        Assertions.assertEquals("Derived", properties.getName());
        Assertions.assertIterableEquals(additionalProperty.entrySet(), properties.getAdditionalProperties().entrySet());
    }
}
