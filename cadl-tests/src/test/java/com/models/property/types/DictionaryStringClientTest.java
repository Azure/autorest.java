// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.types;

import com.models.property.types.models.DictionaryStringProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class DictionaryStringClientTest {

    DictionaryStringClient client = new DictionaryStringClientBuilder().buildClient();

    @Test
    void get() {
        DictionaryStringProperty dictionaryStringProperty = client.get();
        Map<String, String> property = dictionaryStringProperty.getProperty();
        Assertions.assertEquals("hello", property.get("k1"));
        Assertions.assertEquals("world", property.get("k2"));
    }

    @Test
    void put() {
        Map<String, String> property = new HashMap<>();
        property.put("k1", "hello");
        property.put("k2", "world");
        DictionaryStringProperty dictionaryStringProperty = new DictionaryStringProperty(property);
        client.put(dictionaryStringProperty);
    }
}