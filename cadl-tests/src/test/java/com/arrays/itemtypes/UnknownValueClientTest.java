// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.arrays.itemtypes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnknownValueClientTest {

    UnknownValueClient client = new UnknownValueClientBuilder().buildClient();

    @Test
    void get() {
        List<Object> response = client.get();
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals(1, response.get(0));
        Assertions.assertEquals("hello", response.get(1));
        Assertions.assertEquals(null, response.get(2));
    }

    @Test
    void put() {
        client.put(Arrays.asList(1, "hello", null));
    }
}