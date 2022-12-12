// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.types;

import com.models.property.types.models.ExtensibleEnumProperty;
import com.models.property.types.models.InnerEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExtensibleEnumClientTest {

    ExtensibleEnumClient client = new TypesClientBuilder().buildExtensibleEnumClient();

    @Test
    void get() {
        ExtensibleEnumProperty extensibleEnumProperty = client.get();
        InnerEnum innerExtensibleEnum = extensibleEnumProperty.getProperty();
        Assertions.assertEquals("UnknownValue", innerExtensibleEnum.toString());
    }

    @Test
    void put() {
        ExtensibleEnumProperty extensibleEnumProperty = new ExtensibleEnumProperty(InnerEnum.fromString("UnknownValue"));
        client.put(extensibleEnumProperty);
    }
}