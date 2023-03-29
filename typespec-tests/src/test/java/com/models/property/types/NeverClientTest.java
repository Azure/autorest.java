// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.types;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NeverClientTest {

    NeverClient client = new TypesClientBuilder().buildNeverClient();

    @Test
    void get() {
        Object response = client.get();
        Assertions.assertEquals("{}", response.toString());
    }

    @Test
    void put() {
        client.put(new Object());
    }
}