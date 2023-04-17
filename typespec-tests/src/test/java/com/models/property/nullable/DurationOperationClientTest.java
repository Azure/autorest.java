// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.nullable;

import com.azure.core.util.BinaryData;
import com.azure.json.JsonProviders;
import com.azure.json.JsonWriter;
import com.models.property.nullable.models.DurationProperty;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class DurationOperationClientTest {

    DurationOperationClient client = new NullableClientBuilder().buildDurationOperationClient();

    @Test
    void patchNonNullWithResponse() {
        DurationProperty property = new DurationProperty("foo", Duration.parse("PT2974H14M12.011S"));
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
        DurationProperty response = client.getNonNull();
        assertNotNull(response.getNullableProperty());
    }

    @Test
    void getNull() {
        DurationProperty response = client.getNull();
        assertNull(response.getNullableProperty());

    }
}