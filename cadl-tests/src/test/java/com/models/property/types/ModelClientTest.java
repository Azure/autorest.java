// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.types;

import com.models.property.types.models.InnerModel;
import com.models.property.types.models.ModelProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ModelClientTest {

    ModelClient client = new ModelsPropertyTypesClientBuilder().buildModelClient();

    @Test
    void get() {
        ModelProperty modelProperty = client.get();
        Assertions.assertEquals("hello", modelProperty.getProperty().getProperty());
    }

    @Test
    void put() {
        InnerModel innerModel = new InnerModel("hello");
        ModelProperty modelProperty = new ModelProperty(innerModel);
        client.put(modelProperty);
    }
}