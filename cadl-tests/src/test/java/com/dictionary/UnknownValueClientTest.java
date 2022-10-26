// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.dictionary;

import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class UnknownValueClientTest {

    UnknownValueClient client = new UnknownValueClientBuilder().buildClient();

    @Test
    void get() {
       Map<String, Object> response = client.get();
       Assertions.assertEquals(1, response.get("k1"));
       Assertions.assertEquals("hello", response.get("k2"));
       Assertions.assertEquals(null, response.get("k3"));
    }

    @Test
    void put() {
        client.putWithResponse(BinaryData.fromString("{\"k1\":1,\"k2\":\"hello\",\"k3\":null}"), null);
    }
}