// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.specialwords;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ParameterClientTest {

    private final ParametersClient client = new SpecialWordsClientBuilder().buildParametersClient();

    @Disabled
    @Test
    void test() throws Exception {
        ReflectHelper.invokeWithResponseMethods(client.getClass(), client, "ok");
    }
}