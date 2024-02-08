// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.type.property.optional.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
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
@Fluent
public final class DatetimeProperty implements JsonSerializable<DatetimeProperty> {

    /*
     * Property
     */
    @Generated
    private OffsetDateTime property;

    /**
     * Creates an instance of DatetimeProperty class.
     */
    @Generated
    public DatetimeProperty() {
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

    /**
     * Set the property property: Property.
     *
     * @param property the property value to set.
     * @return the DatetimeProperty object itself.
     */
    @Generated
    public DatetimeProperty setProperty(OffsetDateTime property) {
        this.property = property;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("property",
            this.property == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.property));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DatetimeProperty from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of DatetimeProperty if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the DatetimeProperty.
     */
    public static DatetimeProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DatetimeProperty deserializedDatetimeProperty = new DatetimeProperty();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("property".equals(fieldName)) {
                    deserializedDatetimeProperty.property
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else {
                    reader.skipChildren();
                }
            }
            return deserializedDatetimeProperty;
        });
    }
}
