// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com._specs_.azure.clientgenerator.core.usage;

import com._specs_.azure.clientgenerator.core.usage.models.InputModel;
import com._specs_.azure.clientgenerator.core.usage.models.OutputModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class UsageTests {

    private final UsageClient client = new UsageClientBuilder().buildClient();

    @Test
    public void test() throws NoSuchMethodException {
        client.inputToInputOutput(new InputModel("Madge"));

        client.outputToInputOutput();

        // verify OutputModel has private constructor
        Constructor<OutputModel> ctor = OutputModel.class.getDeclaredConstructor(String.class);
        Assertions.assertEquals("public", Modifier.toString(ctor.getModifiers()));
    }
}
