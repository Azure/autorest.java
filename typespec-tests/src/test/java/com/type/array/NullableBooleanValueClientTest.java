// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NullableBooleanValueClientTest {

    NullableBooleanValueClient client = new ArrayClientBuilder().buildNullableBooleanValueClient();

    @Test
    void get() {
        List<Boolean> response = client.get();
        assertEquals(Arrays.asList(true, null, false), response);
    }

    @Test
    void put() {
        List<Boolean> body = Arrays.asList(true, null, false);
        client.put(body);
    }
}