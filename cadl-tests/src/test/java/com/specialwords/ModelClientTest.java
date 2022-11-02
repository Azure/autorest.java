// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.specialwords;

import com.specialwords.models.BaseModel;
import com.specialwords.models.DerivedModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ModelClientTest {

    ModelClient client = new SpecialWordsClientBuilder().buildModelClient();

    @Test
    void get() {
        DerivedModel response = (DerivedModel) client.get();
        Assertions.assertEquals("my.name", response.getDerivedName());
        Assertions.assertEquals("value", response.getForProperty());
    }

    @Test
    void put() {
        BaseModel body = new DerivedModel("my.name", "value");
        client.put(body);
    }
}