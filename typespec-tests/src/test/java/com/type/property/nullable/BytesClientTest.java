// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.nullable;

import com.azure.core.util.BinaryData;
import com.azure.json.JsonProviders;
import com.azure.json.JsonWriter;
import com.type.property.nullable.models.BytesProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;

class BytesClientTest {

    BytesClient client = new NullableClientBuilder().buildBytesClient();

    @Test
    void patchNonNullWithResponse() {
        byte[] input = new byte[]{104, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100, 33};
        BytesProperty bytesProperty = new BytesProperty("foo", input);
        client.patchNonNullWithResponse(BinaryData.fromObject(bytesProperty), null);
    }

    @Test
    void patchNullWithResponse() throws IOException {
        StringWriter writer = new StringWriter();
        JsonWriter jsonWriter = JsonProviders.createWriter(writer);
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("requiredProperty", "foo");
        jsonWriter.writeNullField("nullableProperty");
        jsonWriter.writeEndObject();
        jsonWriter.close();
        client.patchNullWithResponse(BinaryData.fromString(writer.toString()), null);
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