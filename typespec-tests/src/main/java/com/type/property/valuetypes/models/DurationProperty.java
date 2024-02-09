// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.valuetypes.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.type.property.valuetypes.implementation.CoreToCodegenBridgeUtils;
import java.io.IOException;
import java.time.Duration;

/**
 * Model with a duration property.
 */
@Immutable
public final class DurationProperty implements JsonSerializable<DurationProperty> {
    /*
     * Property
     */
    @Generated
    private final Duration property;

    /**
     * Creates an instance of DurationProperty class.
     * 
     * @param property the property value to set.
     */
    @Generated
    public DurationProperty(Duration property) {
        this.property = property;
    }

    /**
     * Get the property property: Property.
     * 
     * @return the property value.
     */
    @Generated
    public Duration getProperty() {
        return this.property;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("property", CoreToCodegenBridgeUtils.durationToStringWithDays(this.property));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DurationProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DurationProperty if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the DurationProperty.
     */
    public static DurationProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Duration property = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("property".equals(fieldName)) {
                    property = reader.getNullable(nonNullReader -> Duration.parse(nonNullReader.getString()));
                } else {
                    reader.skipChildren();
                }
            }
            return new DurationProperty(property);
        });
    }
}
