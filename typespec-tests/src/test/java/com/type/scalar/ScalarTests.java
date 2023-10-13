// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.scalar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScalarTests {

    private final StringOperationClient stringClient = new ScalarClientBuilder().buildStringOperationClient();
    private final BooleanOperationClient booleanClient = new ScalarClientBuilder().buildBooleanOperationClient();
    private final UnknownClient unknownClient = new ScalarClientBuilder().buildUnknownClient();

    @Test
    public void test() {
        Assertions.assertEquals("test", stringClient.get());
        stringClient.put("test");

        Assertions.assertTrue(booleanClient.get());
        booleanClient.put(true);

        Assertions.assertEquals("test", unknownClient.get());
        unknownClient.put("test");
    }
}
