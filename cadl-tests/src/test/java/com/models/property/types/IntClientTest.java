// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.types;

import com.models.property.types.models.IntProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntClientTest {

    IntClient client = new ModelsPropertyTypesClientBuilder().buildIntClient();

    @Test
    void get() {
        IntProperty intProperty = client.get();
        Assertions.assertEquals(42, intProperty.getProperty());
    }

    @Test
    void put() {
        IntProperty intProperty = new IntProperty(42);
        client.put(intProperty);
    }
}