// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.parameters.collectionformat;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class CollectionFormatClientTest {

    QueryClient client = new CollectionFormatClientBuilder().buildQueryClient();
    HeaderClient headerClient = new CollectionFormatClientBuilder().buildHeaderClient();

    @Test
    void testMulti() {
        client.multi(Arrays.asList("blue", "red", "green"));
    }

    @Test
    void testCsv() {
        client.csv(Arrays.asList("blue", "red", "green"));
    }

    @Test
    void testCsvHeader() {
        headerClient.csv(Arrays.asList("blue", "red", "green"));
    }

}