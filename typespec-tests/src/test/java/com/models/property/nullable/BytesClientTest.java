// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.nullable;

import com.azure.core.util.BinaryData;
import com.models.property.nullable.models.BytesProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class BytesClientTest {

    BytesClient client = new NullableClientBuilder().buildBytesClient();

    @Test
    void patchNonNullWithResponse() {
        byte[] input = new byte[]{104, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100, 33};
        BytesProperty bytesProperty = new BytesProperty("foo", input);
        client.patchNonNullWithResponse(BinaryData.fromObject(bytesProperty), null);
    }

    @Disabled("patch null not supported yet, com.azure.core.exception.HttpResponseException: Status code 400, \"{\"message\":\"Body provided doesn't match expected body\",\"expected\":{\"requiredProperty\":\"foo\",\"nullableProperty\":null},\"actual\":{\"requiredProperty\":\"foo\"}}\"\n")
    @Test
    void patchNullWithResponse() {
        BytesProperty bytesProperty = new BytesProperty("foo", null);
        client.patchNullWithResponse(BinaryData.fromObject(bytesProperty), null);
    }

    @Test
    void getNonNull() {
        BytesProperty response = client.getNonNull();
        Assertions.assertNotNull(response.getNullableProperty());
    }

    @Test
    void getNull() {
        BytesProperty response = client.getNull();
        Assertions.assertNull(response.getNullableProperty());
    }
}