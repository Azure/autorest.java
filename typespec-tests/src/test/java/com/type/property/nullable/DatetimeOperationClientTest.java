// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.property.nullable;

import com.azure.core.util.BinaryData;
import com.azure.json.JsonProviders;
import com.azure.json.JsonWriter;
import com.type.property.nullable.models.DatetimeProperty;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class DatetimeOperationClientTest {

    DatetimeOperationClient client = new NullableClientBuilder().buildDatetimeOperationClient();

    @Test
    void patchNonNullWithResponse() {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse("2022-08-26T18:38:00Z");
        DatetimeProperty datetimeProperty = new DatetimeProperty("foo", offsetDateTime);
        client.patchNonNullWithResponse(BinaryData.fromObject(datetimeProperty), null);
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
        DatetimeProperty response = client.getNonNull();
        assertNotNull(response.getNullableProperty());
    }

    @Test
    void getNull() {
        DatetimeProperty response = client.getNull();
        assertNull(response.getNullableProperty());
    }
}