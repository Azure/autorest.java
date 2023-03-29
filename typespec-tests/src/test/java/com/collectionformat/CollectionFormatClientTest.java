// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.collectionformat;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

    @Test
    void testCsvHeader() {
        client.testCsvHeader(Arrays.asList("blue", "red", "green"));
    }

    @Test
    void testDefaultHeader() {
        client.testDefaultHeader(Arrays.asList("blue", "red", "green"));
    }

}