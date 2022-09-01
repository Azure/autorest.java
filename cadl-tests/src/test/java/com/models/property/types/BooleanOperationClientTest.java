// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.types;

import com.models.property.types.models.BooleanProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanOperationClientTest {

    BooleanOperationClient client = new BooleanOperationClientBuilder().buildClient();

    @Test
    void get() {
        BooleanProperty response = client.get();
        Assertions.assertTrue(response.isProperty());
    }

    @Test
    void put() {
        BooleanProperty booleanProperty = new BooleanProperty(true);
        client.put(booleanProperty);
    }
}