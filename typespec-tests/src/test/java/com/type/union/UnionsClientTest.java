// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.union;

import com.type.union.models.IntegerListSimpleUnionModel;
import com.type.union.models.IntegerSimpleUnionModel;
import com.type.union.models.Model1;
import com.type.union.models.Model1NamedUnionModel;
import com.type.union.models.Model2;
import com.type.union.models.Model2NamedUnionModel;
import com.type.union.models.ModelWithNamedUnionProperty;
import com.type.union.models.ModelWithSimpleUnionProperty;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class UnionsClientTest {

    private UnionClient client = new UnionClientBuilder().buildClient();

    @Test
    void sendInt() {
        client.sendInt(new ModelWithSimpleUnionProperty(new IntegerSimpleUnionModel(1)));
    }

    @Test
    void sendIntArray() {
        client.sendIntArray(new ModelWithSimpleUnionProperty(new IntegerListSimpleUnionModel(Arrays.asList(1, 2))));
    }

    @Test
    void sendFirstNamedUnionValue() {
        client.sendFirstNamedUnionValue(new ModelWithNamedUnionProperty(
                new Model1NamedUnionModel(new Model1("model1", 1))));
    }

    @Test
    void sendSecondNamedUnionValue() {
        client.sendSecondNamedUnionValue(new ModelWithNamedUnionProperty(
                new Model2NamedUnionModel(new Model2("model2", 2))));
    }
}