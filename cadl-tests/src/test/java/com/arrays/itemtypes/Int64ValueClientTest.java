// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.arrays.itemtypes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class Int64ValueClientTest {

    Int64ValueClient client = new ArraysItemTypesClientBuilder().buildInt64ValueClient();

    @Test
    void get() {
        List<Long> response = client.get();
        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals(9007199254740991L, response.get(0));
        Assertions.assertEquals(-9007199254740991L, response.get(1));
    }

    @Test
    void put() {
        client.put(Arrays.asList(9007199254740991L, -9007199254740991L));
    }
}