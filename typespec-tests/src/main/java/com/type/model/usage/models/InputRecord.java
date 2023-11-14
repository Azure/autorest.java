// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.usage.models;

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
 * Record used in operation parameters.
 */
@Immutable
public final class InputRecord implements JsonSerializable<InputRecord> {
    /*
     * The requiredProp property.
     */
    @Generated
    private final String requiredProp;

    /**
     * Creates an instance of InputRecord class.
     * 
     * @param requiredProp the requiredProp value to set.
     */
    @Generated
    public InputRecord(String requiredProp) {
        this.requiredProp = requiredProp;
    }

    /**
     * Get the requiredProp property: The requiredProp property.
     * 
     * @return the requiredProp value.
     */
    @Generated
    public String getRequiredProp() {
        return this.requiredProp;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("requiredProp", this.requiredProp);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of InputRecord from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of InputRecord if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the InputRecord.
     */
    public static InputRecord fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean requiredPropFound = false;
            String requiredProp = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("requiredProp".equals(fieldName)) {
                    requiredProp = reader.getString();
                    requiredPropFound = true;
                } else {
                    reader.skipChildren();
                }
            }
            if (requiredPropFound) {
                InputRecord deserializedInputRecord = new InputRecord(requiredProp);

                return deserializedInputRecord;
            }
            List<String> missingProperties = new ArrayList<>();
            if (!requiredPropFound) {
                missingProperties.add("requiredProp");
            }

            throw new IllegalStateException(
                "Missing required property/properties: " + String.join(", ", missingProperties));
        });
    }
}
