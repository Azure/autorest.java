// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.arrays.itemtypes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class Float32ValueClientTest {

    Float32ValueClient client = new ArraysItemTypesClientBuilder().buildFloat32ValueClient();

    @Test
    void get() {
        List<Double> response = client.get();
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(42.42, response.get(0));
    }

    @Test
    void put() {
        client.put(Arrays.asList(42.42));
    }
}