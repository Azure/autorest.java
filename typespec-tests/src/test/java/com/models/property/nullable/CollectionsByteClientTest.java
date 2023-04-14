// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.nullable;

import com.azure.core.util.BinaryData;
import com.models.property.nullable.models.CollectionsByteProperty;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CollectionsByteClientTest {

    CollectionsByteClient client = new NullableClientBuilder().buildCollectionsByteClient();

    @Test
    void patchNonNullWithResponse() {
        CollectionsByteProperty collectionsByteProperty = new CollectionsByteProperty("foo", Arrays.asList(new byte[]{104, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100, 33}, new byte[]{104, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100, 33}));
        client.patchNonNullWithResponse(BinaryData.fromObject(collectionsByteProperty), null);
    }

    @Disabled("patch null not supported yet, com.azure.core.exception.HttpResponseException: Status code 400, \"{\"message\":\"Body provided doesn't match expected body\",\"expected\":{\"requiredProperty\":\"foo\",\"nullableProperty\":null},\"actual\":{\"requiredProperty\":\"foo\"}}\"\n")
    @Test
    void patchNullWithResponse() {
        CollectionsByteProperty collectionsByteProperty = new CollectionsByteProperty("foo", null);
        client.patchNullWithResponse(BinaryData.fromObject(collectionsByteProperty), null);
    }

    @Test
    void getNonNull() {
        CollectionsByteProperty response= client.getNonNull();
        assertNotNull(response.getNullableProperty());
    }

    @Test
    void getNull() {
        CollectionsByteProperty response= client.getNull();
        assertNull(response.getNullableProperty());
    }
}