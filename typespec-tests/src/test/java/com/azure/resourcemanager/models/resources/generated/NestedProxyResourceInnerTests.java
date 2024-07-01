// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.azure.resourcemanager.models.resources.generated;

import com.azure.core.util.BinaryData;
import com.azure.resourcemanager.models.resources.fluent.models.NestedProxyResourceInner;
import com.azure.resourcemanager.models.resources.models.NestedProxyResourceProperties;
import org.junit.jupiter.api.Assertions;

public final class NestedProxyResourceInnerTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        NestedProxyResourceInner model = BinaryData.fromString(
            "{\"properties\":{\"provisioningState\":\"Succeeded\",\"description\":\"urkdtmlx\"},\"id\":\"kuksjtxukcdm\",\"name\":\"arcryuanzwuxzdxt\",\"type\":\"yrlhmwhfpmrqobm\"}")
            .toObject(NestedProxyResourceInner.class);
        Assertions.assertEquals("urkdtmlx", model.properties().description());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        NestedProxyResourceInner model = new NestedProxyResourceInner()
            .withProperties(new NestedProxyResourceProperties().withDescription("urkdtmlx"));
        model = BinaryData.fromObject(model).toObject(NestedProxyResourceInner.class);
        Assertions.assertEquals("urkdtmlx", model.properties().description());
    }
}
