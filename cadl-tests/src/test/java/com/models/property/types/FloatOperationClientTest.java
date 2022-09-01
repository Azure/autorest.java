// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.types;

import com.models.property.types.models.FloatProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatOperationClientTest {

    FloatOperationClient client = new FloatOperationClientBuilder().buildClient();

    @Test
    void get() {
        FloatProperty floatProperty = client.get();
        Assertions.assertEquals(42.42, floatProperty.getProperty());
    }

    @Test
    void put() {
        FloatProperty floatProperty = new FloatProperty(42.42);
        client.put(floatProperty);
    }
}