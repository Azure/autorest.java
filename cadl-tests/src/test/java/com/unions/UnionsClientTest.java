// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.unions;

import com.unions.models.IntegerListSimpleUnionModel;
import com.unions.models.IntegerSimpleUnionModel;
import com.unions.models.Model1;
import com.unions.models.Model1NamedUnionModel;
import com.unions.models.Model2;
import com.unions.models.Model2NamedUnionModel;
import com.unions.models.ModelWithNamedUnionProperty;
import com.unions.models.ModelWithSimpleUnionProperty;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UnionsClientTest {

    private UnionsClient client = new UnionsClientBuilder().buildClient();

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