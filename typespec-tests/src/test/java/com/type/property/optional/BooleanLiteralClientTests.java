// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.optional;

import com.type.property.optional.models.BooleanLiteralProperty;
import com.type.property.optional.models.BooleanLiteralProperty1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanLiteralClientTests {
    BooleanLiteralClient client = new OptionalClientBuilder().buildBooleanLiteralClient();

    @Test
    void getAll() {
        BooleanLiteralProperty booleanLiteralProperty = client.getAll();
        Assertions.assertEquals(BooleanLiteralProperty1.TRUE, booleanLiteralProperty.getProperty());
    }

    @Test
    void getDefault() {
        BooleanLiteralProperty booleanLiteralProperty = client.getDefault();
        Assertions.assertNull(booleanLiteralProperty.getProperty());
    }

    @Test
    void putAll() {
        BooleanLiteralProperty booleanLiteralProperty = new BooleanLiteralProperty();
        booleanLiteralProperty.setProperty(BooleanLiteralProperty1.TRUE);
        client.putAll(booleanLiteralProperty);
    }

    @Test
    void putDefault() {
        BooleanLiteralProperty booleanLiteralProperty = new BooleanLiteralProperty();
        client.putDefault(booleanLiteralProperty);
    }
}
