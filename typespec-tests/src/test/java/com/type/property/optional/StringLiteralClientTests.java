// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.optional;

import com.type.property.optional.models.StringLiteralProperty;
import com.type.property.optional.models.StringLiteralProperty1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringLiteralClientTests {
    StringLiteralClient client = new OptionalClientBuilder().buildStringLiteralClient();

    @Test
    void getAll() {
        StringLiteralProperty stringLiteralProperty = client.getAll();
        Assertions.assertEquals(StringLiteralProperty1.HELLO, stringLiteralProperty.getProperty());
    }

    @Test
    void getDefault() {
        StringLiteralProperty stringLiteralProperty = client.getDefault();
        Assertions.assertNull(stringLiteralProperty.getProperty());
    }

    @Test
    void putAll() {
        StringLiteralProperty stringLiteralProperty = new StringLiteralProperty();
        stringLiteralProperty.setProperty(StringLiteralProperty1.HELLO);
        client.putAll(stringLiteralProperty);
    }

    @Test
    void putDefault() {
        StringLiteralProperty stringLiteralProperty = new StringLiteralProperty();
        client.putDefault(stringLiteralProperty);
    }
}
