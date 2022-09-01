// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.types;

import com.models.property.types.models.CollectionsIntProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class CollectionsIntClientTest {

    CollectionsIntClient client = new CollectionsIntClientBuilder().buildClient();

    @Test
    void get() {
        CollectionsIntProperty collectionsIntProperty = client.get();
        List<Long> properties = collectionsIntProperty.getProperty();
        Assertions.assertEquals(1L, properties.get(0));
        Assertions.assertEquals(2L, properties.get(1));
    }

    @Test
    void put() {
        CollectionsIntProperty collectionsIntProperty = new CollectionsIntProperty(Arrays.asList(1L, 2L));
        client.put(collectionsIntProperty);
    }
}