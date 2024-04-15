// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.additionalproperties;

import com.type.property.additionalproperties.models.IsModelAdditionalProperties;
import com.type.property.additionalproperties.models.ModelForRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class IsModelClientTest {
    private final IsModelClient client = new AdditionalPropertiesClientBuilder().buildIsModelClient();

    @Test
    public void testPullAndGet() {
        IsModelAdditionalProperties request = new IsModelAdditionalProperties();
        Map<String, ModelForRecord> properties = new LinkedHashMap<>();
        properties.put("prop", new ModelForRecord("ok"));
        request.setAdditionalProperties(properties);
        client.put(request);

        IsModelAdditionalProperties response = client.get();
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getAdditionalProperties());
        Assertions.assertNotNull(response.getAdditionalProperties().get("prop"));
        Assertions.assertEquals("ok", properties.get("prop").getState());
    }
}
