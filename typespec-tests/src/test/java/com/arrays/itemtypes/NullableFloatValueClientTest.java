// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.arrays.itemtypes;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NullableFloatValueClientTest {

    NullableFloatValueClient client = new ItemTypesClientBuilder().buildNullableFloatValueClient();

    @Test
    void get() {
        List<Double> response = client.get();
        assertEquals(Arrays.asList(1.2, null, 3.0), response);
    }

    @Test
    void put() {
        List<Double> body = Arrays.asList(1.2, null, 3.0);
        client.put(body);
    }
}