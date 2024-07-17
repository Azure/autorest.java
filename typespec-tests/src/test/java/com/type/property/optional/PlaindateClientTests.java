// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.optional;

import com.type.property.optional.models.PlaindateProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PlaindateClientTests {
    private final PlaindateClient client = new OptionalClientBuilder().buildPlaindateClient();

    @Test
    public void getAll() {
        PlaindateProperty plaindateProperty = client.getAll();
        Assertions.assertEquals(LocalDate.parse("2022-12-12"), plaindateProperty.getProperty());
    }

    @Test
    public void getDefault() {
        PlaindateProperty plaindateProperty = client.getDefault();
        Assertions.assertNull(plaindateProperty.getProperty());
    }

    @Test
    public void putAll() {
        client.putAll(new PlaindateProperty().setProperty(LocalDate.parse("2022-12-12")));
    }

    @Test
    public void putDefault() {
        client.putAll(new PlaindateProperty().setProperty(LocalDate.parse("2022-12-12")));
    }
}
