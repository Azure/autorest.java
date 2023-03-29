// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.types;

import com.models.property.types.models.BytesProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BytesClientTest {

    BytesClient client = new TypesClientBuilder().buildBytesClient();

    @Test
    void get() {
        BytesProperty bytesProperty = client.get();
        Assertions.assertNotNull(bytesProperty.getProperty());
    }

    @Test
    void put() {
        byte[] input = new byte[]{104, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100, 33};
        BytesProperty bytesProperty = new BytesProperty(input);
        client.put(bytesProperty);
    }
}