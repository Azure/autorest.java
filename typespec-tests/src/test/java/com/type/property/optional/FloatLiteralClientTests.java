// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.optional;

import com.type.property.optional.models.FloatLiteralProperty;
import com.type.property.optional.models.FloatLiteralProperty1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FloatLiteralClientTests {
    private final FloatLiteralClient client = new OptionalClientBuilder().buildFloatLiteralClient();

    @Test
    public void getAll() {
        FloatLiteralProperty floatLiteralProperty = client.getAll();
        Assertions.assertEquals(FloatLiteralProperty1.ONE_TWO, floatLiteralProperty.getProperty());
    }

    @Test
    public void getDefault() {
        FloatLiteralProperty floatLiteralProperty = client.getDefault();
        Assertions.assertNull(floatLiteralProperty.getProperty());
    }

    @Test
    public void putAll() {
        FloatLiteralProperty floatLiteralProperty = new FloatLiteralProperty();
        floatLiteralProperty.setProperty(FloatLiteralProperty1.ONE_TWO);
        client.putAll(floatLiteralProperty);
    }

    @Test
    public void putDefault() {
        FloatLiteralProperty floatLiteralProperty = new FloatLiteralProperty();
        client.putDefault(floatLiteralProperty);
    }
}
