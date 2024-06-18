// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.azure.arm.models.resources.generated;

import com.azure.arm.models.resources.models.NestedProxyResourceUpdate;
import com.azure.arm.models.resources.models.NestedProxyResourceUpdateProperties;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;

public final class NestedProxyResourceUpdateTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        NestedProxyResourceUpdate model = BinaryData.fromString("{\"properties\":{\"description\":\"jbpzvgnwzsymg\"}}")
            .toObject(NestedProxyResourceUpdate.class);
        Assertions.assertEquals("jbpzvgnwzsymg", model.properties().description());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        NestedProxyResourceUpdate model = new NestedProxyResourceUpdate()
            .withProperties(new NestedProxyResourceUpdateProperties().withDescription("jbpzvgnwzsymg"));
        model = BinaryData.fromObject(model).toObject(NestedProxyResourceUpdate.class);
        Assertions.assertEquals("jbpzvgnwzsymg", model.properties().description());
    }
}
