// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.specialwords;

import org.junit.jupiter.api.Test;

class OperationClientTest {

    OperationClient client = new SpecialWordsClientBuilder().buildOperationClient();

    @Test
    void forMethod() {
        client.forMethod();
    }
}