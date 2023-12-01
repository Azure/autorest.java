// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.optional;

import com.type.property.optional.models.UnionStringLiteralProperty;
import com.type.property.optional.models.UnionStringLiteralPropertyProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnionStringLiteralClientTests {
    UnionStringLiteralClient client = new OptionalClientBuilder().buildUnionStringLiteralClient();

    @Test
    void getAll() {
        UnionStringLiteralProperty unionStringLiteralProperty = client.getAll();
        Assertions.assertEquals(UnionStringLiteralPropertyProperty.WORLD, unionStringLiteralProperty.getProperty());
    }

    @Test
    void getDefault() {
        UnionStringLiteralProperty unionStringLiteralProperty = client.getDefault();
        Assertions.assertNull(unionStringLiteralProperty.getProperty());
    }

    @Test
    void putAll() {
        UnionStringLiteralProperty unionStringLiteralProperty = new UnionStringLiteralProperty();
        unionStringLiteralProperty.setProperty(UnionStringLiteralPropertyProperty.WORLD);
        client.putAll(unionStringLiteralProperty);
    }

    @Test
    void putDefault() {
        UnionStringLiteralProperty unionStringLiteralProperty = new UnionStringLiteralProperty();
        client.putDefault(unionStringLiteralProperty);
    }
}
