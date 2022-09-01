// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.types;

import com.models.property.types.models.ExtensibleEnumProperty;
import com.models.property.types.models.InnerExtensibleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExtensibleEnumClientTest {

    ExtensibleEnumClient client = new ExtensibleEnumClientBuilder().buildClient();

    @Test
    void get() {
        ExtensibleEnumProperty extensibleEnumProperty = client.get();
        InnerExtensibleEnum innerExtensibleEnum = extensibleEnumProperty.getProperty();
        Assertions.assertEquals("UnknownValue", innerExtensibleEnum.toString());
    }

    @Test
    void put() {
        ExtensibleEnumProperty extensibleEnumProperty = new ExtensibleEnumProperty(InnerExtensibleEnum.fromString("UnknownValue"));
        client.put(extensibleEnumProperty);
    }
}