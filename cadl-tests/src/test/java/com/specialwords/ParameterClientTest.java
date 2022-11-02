// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.specialwords;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParameterClientTest {

    ParameterClient client = new SpecialWordsClientBuilder().buildParameterClient();

    @Test
    void getWithIf() {
        client.getWithIf("weekend");
    }

    @Test
    void getWithFilter() {
        client.getWithFilter("abc*.");
    }
}