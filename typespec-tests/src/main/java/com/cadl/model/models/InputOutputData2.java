// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.model.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The InputOutputData2 model.
 */
@Immutable
public final class InputOutputData2 implements JsonSerializable<InputOutputData2> {
    /*
     * The data property.
     */
    @Generated
    private final String data;

    /**
     * Creates an instance of InputOutputData2 class.
     * 
     * @param data the data value to set.
     */
    @Generated
    public InputOutputData2(String data) {
        this.data = data;
    }

    /**
     * Get the data property: The data property.
     * 
     * @return the data value.
     */
    @Generated
    public String getData() {
        return this.data;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("data", this.data);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of InputOutputData2 from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of InputOutputData2 if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the InputOutputData2.
     */
    public static InputOutputData2 fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean dataFound = false;
            String data = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("data".equals(fieldName)) {
                    data = reader.getString();
                    dataFound = true;
                } else {
                    reader.skipChildren();
                }
            }
            if (dataFound) {
                InputOutputData2 deserializedInputOutputData2 = new InputOutputData2(data);

                return deserializedInputOutputData2;
            }
            List<String> missingProperties = new ArrayList<>();
            if (!dataFound) {
                missingProperties.add("data");
            }

            throw new IllegalStateException(
                "Missing required property/properties: " + String.join(", ", missingProperties));
        });
    }
}
