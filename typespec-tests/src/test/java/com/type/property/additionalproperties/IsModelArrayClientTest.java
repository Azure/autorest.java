// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.additionalproperties;

import com.type.property.additionalproperties.models.ModelForRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IsModelArrayClientTest {
    private final IsModelArrayClient client = new AdditionalPropertiesClientBuilder().buildIsModelArrayClient();

    @Test
    public void testPullAndGet() {
        Map<String, List<ModelForRecord>> properties = new LinkedHashMap<>();
        properties.put("prop", Arrays.asList(new ModelForRecord("ok"), new ModelForRecord("ok")));
        client.put(properties);

        properties = client.get();
        Assertions.assertNotNull(properties);
        Assertions.assertNotNull(properties.get("prop"));
        properties.get("prop").forEach(modelForRecord ->
                Assertions.assertEquals("ok", modelForRecord.getState()));
    }
}
