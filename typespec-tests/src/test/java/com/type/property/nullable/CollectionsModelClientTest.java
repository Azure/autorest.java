// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.nullable;

import com.azure.core.util.BinaryData;
import com.azure.json.JsonProviders;
import com.azure.json.JsonWriter;
import com.type.property.nullable.models.CollectionsModelProperty;
import com.type.property.nullable.models.InnerModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CollectionsModelClientTest {

    CollectionsModelClient client = new NullableClientBuilder().buildCollectionsModelClient();

    @Test
    void patchNonNullWithResponse() {
        CollectionsModelProperty property = new CollectionsModelProperty("foo", Arrays.asList(new InnerModel("hello"), new InnerModel("world")));
        client.patchNonNullWithResponse(BinaryData.fromObject(property), null);
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
        CollectionsModelProperty response = client.getNonNull();
        assertNotNull(response.getNullableProperty());
    }

    @Test
    void getNull() {
        CollectionsModelProperty response = client.getNull();
        assertNull(response.getNullableProperty());
    }
}