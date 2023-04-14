// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.nullable;

import com.azure.core.util.BinaryData;
import com.azure.json.JsonProviders;
import com.azure.json.JsonWriter;
import com.models.property.nullable.models.CollectionsByteProperty;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
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
        CollectionsByteProperty response= client.getNonNull();
        assertNotNull(response.getNullableProperty());
    }

    @Test
    void getNull() {
        CollectionsByteProperty response= client.getNull();
        assertNull(response.getNullableProperty());
    }
}