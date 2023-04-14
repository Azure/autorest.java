// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.arrays.itemtypes;

import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NullableFloatValueClientTest {

    NullableFloatValueClient client = new ItemTypesClientBuilder().buildNullableFloatValueClient();

    @Test
    void get() {
        List<Double> response = client.get();
        assertEquals(Arrays.asList(1.2, null, 3.0), response);
    }

    @Test
    void put() {
        List<Double> body = Arrays.asList(1.2, null, 3.0);
        client.put(body);
    }
}