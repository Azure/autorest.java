// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.additionalproperties;

import com.azure.core.util.BinaryData;
import com.type.property.additionalproperties.models.MultipleSpreadRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class MultipleSpreadTests {
    private final MultipleSpreadClient multipleSpreadClient = new AdditionalPropertiesClientBuilder().buildMultipleSpreadClient();

    @Test
    @Disabled("The body provided of 'put' request doesn't match expected body")
    public void testMultipleSpread() {
        Map<String, BinaryData> propertyMap = new LinkedHashMap<>();
        propertyMap.put("prop1", BinaryData.fromString("abc"));
        propertyMap.put("prop2", BinaryData.fromObject(43.125));
        MultipleSpreadRecord body = new MultipleSpreadRecord(true);
        body.setAdditionalProperties(propertyMap);
        multipleSpreadClient.put(body);

        MultipleSpreadRecord record = multipleSpreadClient.get();
        Assertions.assertNotNull(record);
        Assertions.assertTrue(record.isFlag());
        Assertions.assertNotNull(record.getAdditionalProperties());
        Assertions.assertEquals("\"abc\"", record.getAdditionalProperties().get("prop1").toString());
        Assertions.assertEquals(43.125, Double.parseDouble(record.getAdditionalProperties().get("prop2").toString()));
    }
}
