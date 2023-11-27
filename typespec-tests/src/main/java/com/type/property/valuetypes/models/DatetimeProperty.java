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
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Model with a datetime property.
 */
@Immutable
public final class DatetimeProperty implements JsonSerializable<DatetimeProperty> {
    private static final DateTimeFormatter ISO_8601 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    /*
     * Property
     */
    @Generated
    private final OffsetDateTime property;

    /**
     * Creates an instance of DatetimeProperty class.
     * 
     * @param property the property value to set.
     */
    @Generated
    public DatetimeProperty(OffsetDateTime property) {
        this.property = property;
    }

    /**
     * Get the property property: Property.
     * 
     * @return the property value.
     */
    @Generated
    public OffsetDateTime getProperty() {
        return this.property;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("property", this.property == null ? null : ISO_8601.format(this.property));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DatetimeProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DatetimeProperty if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the DatetimeProperty.
     */
    public static DatetimeProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            OffsetDateTime property = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("property".equals(fieldName)) {
                    property = reader
                        .getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString(), ISO_8601));
                } else {
                    reader.skipChildren();
                }
            }
            return new DatetimeProperty(property);
        });
    }
}
