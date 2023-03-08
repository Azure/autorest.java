// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.dictionary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class Float32ValueClientTest {

    Float32ValueClient client = new DictionaryClientBuilder().buildFloat32ValueClient();

    @Test
    void get() {
        Map<String, Double> response = client.get();
        Assertions.assertTrue(response.containsKey("k1"));
        Assertions.assertEquals(42.42, response.get("k1"));
    }

    @Test
    void put() {
        Map<String, Double> map = new HashMap<>();
        map.put("k1", 42.42);
        client.put(map);
    }
}