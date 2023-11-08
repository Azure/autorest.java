// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.union;

import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.type.union.models.Model1;
import com.type.union.models.Model2;
import com.type.union.models.ModelWithNamedUnionProperty;
import com.type.union.models.ModelWithNamedUnionPropertyInResponse;
import com.type.union.models.ModelWithSimpleUnionProperty;
import com.type.union.models.ModelWithSimpleUnionPropertyInResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class UnionsClientTest {

    private final UnionClient client = new UnionClientBuilder().buildClient();

    @Test
    void sendInt() {
        client.sendInt(new ModelWithSimpleUnionProperty(BinaryData.fromObject(1)));
    }

    @Test
    void sendIntArray() {
        client.sendIntArray(new ModelWithSimpleUnionProperty(BinaryData.fromObject(Arrays.asList(1, 2))));
    }

    @Test
    void sendFirstNamedUnionValue() {
        client.sendFirstNamedUnionValue(new ModelWithNamedUnionProperty(BinaryData.fromObject(new Model1("model1", 1))));
    }

    @Test
    void sendSecondNamedUnionValue() {
        client.sendSecondNamedUnionValue(new ModelWithNamedUnionProperty(BinaryData.fromObject(new Model2("model2", 2))));
    }

    @Test
    void receiveString() {
        ModelWithSimpleUnionPropertyInResponse response = client.receiveString();
        Assertions.assertEquals("string", response.getSimpleUnion().toObject(String.class));
    }

    @Test
    void receiveIntArray() {
        ModelWithSimpleUnionPropertyInResponse response = client.receiveIntArray();
        Assertions.assertEquals(Arrays.asList(1, 2), response.getSimpleUnion().toObject(List.class));
    }

    @Test
    void receiveFirstNamedUnionValue() {
        ModelWithNamedUnionPropertyInResponse response = client.receiveFirstNamedUnionValue();
        Assertions.assertEquals(1, response.getNamedUnion().toObject(Model1.class).getProp1());
    }

    @Test
    void receiveSecondNamedUnionValue() {
        ModelWithNamedUnionPropertyInResponse response = client.receiveSecondNamedUnionValue();
        Assertions.assertEquals(2, response.getNamedUnion().toObject(Model2.class).getProp2());
    }
}
