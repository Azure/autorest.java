// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.nullable;

import com.azure.core.util.BinaryData;
import com.azure.json.JsonProviders;
import com.azure.json.JsonWriter;
import com.type.property.nullable.models.StringProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;

public class StringClientTests {

    private final StringOperationClient stringClient = new NullableClientBuilder().buildStringOperationClient();

    @Test
    public void testStringNullable() throws IOException {

        Assertions.assertEquals("hello", stringClient.getNonNull().getNullableProperty());

        Assertions.assertNull(stringClient.getNull().getNullableProperty());

        stringClient.patchNonNullWithResponse(BinaryData.fromObject(new StringProperty("foo", "hello")), null);

        StringWriter writer = new StringWriter();
        JsonWriter jsonWriter = JsonProviders.createWriter(writer);
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("requiredProperty", "foo");
        jsonWriter.writeNullField("nullableProperty");
        jsonWriter.writeEndObject();
        jsonWriter.close();
        stringClient.patchNullWithResponse(BinaryData.fromString(writer.toString()), null);
    }
}
