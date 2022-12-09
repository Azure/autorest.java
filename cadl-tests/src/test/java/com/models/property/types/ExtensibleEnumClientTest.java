// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.types;

import com.models.property.types.models.ExtensibleEnumProperty;
import com.models.property.types.models.InnerEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ExtensibleEnumClientTest {

    ExtensibleEnumClient client = new TypesClientBuilder().buildExtensibleEnumClient();

    @Disabled("issue https://github.com/Azure/cadl-ranch/issues/158")
    @Test
    void get() {
        ExtensibleEnumProperty extensibleEnumProperty = client.get();
        InnerEnum innerExtensibleEnum = extensibleEnumProperty.getProperty();
        Assertions.assertEquals("UnknownValue", innerExtensibleEnum.toString());
    }

    @Disabled("issue https://github.com/Azure/cadl-ranch/issues/158")
    @Test
    void put() {
        ExtensibleEnumProperty extensibleEnumProperty = new ExtensibleEnumProperty(InnerEnum.fromString("UnknownValue"));
        client.put(extensibleEnumProperty);
    }
}