// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NullableInt32ValueClientTest {

    NullableInt32ValueClient client = new ArrayClientBuilder().buildNullableInt32ValueClient();

    @Test
    void get() {
        List<Integer> response = client.get();
        assertEquals(Arrays.asList(1, null, 3), response);
    }

    @Test
    void put() {
        List<Integer> body = Arrays.asList(1, null, 3);
        client.put(body);
    }
}