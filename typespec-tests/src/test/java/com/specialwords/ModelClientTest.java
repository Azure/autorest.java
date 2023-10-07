// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.specialwords;

import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class ModelClientTest {

    private final ModelsClient client = new SpecialWordsClientBuilder().buildModelsClient();

    @Disabled
    @Test
    void test() throws Exception {
        ReflectHelper.invokeWithResponseMethods(client.getClass(), client, BinaryData.fromObject(Collections.singletonMap("name", "ok")));
    }
}