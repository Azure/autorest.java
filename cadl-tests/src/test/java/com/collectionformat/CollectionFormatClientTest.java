// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.collectionformat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@Disabled("Pending on fix pr: https://github.com/Azure/cadl-ranch/pull/216")
class CollectionFormatClientTest {

    CollectionFormatClient client = new CollectionFormatClientBuilder().buildClient();

    @Test
    void testMulti() {
        client.testMulti(Arrays.asList("blue", "red", "green"));
    }

    @Test
    void testCsv() {
        client.testCsv(Arrays.asList("blue", "red", "green"));
    }
}