// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com._specs_.azure.clientgenerator.core.usage;

import com._specs_.azure.clientgenerator.core.usage.models.InputModel;
import org.junit.jupiter.api.Test;

public class UsageTests {

    private final UsageClient client = new UsageClientBuilder().buildClient();

    @Test
    public void test() {
        client.inputToInputOutput(new InputModel("Madge"));

        client.outputToInputOutput();
    }
}
