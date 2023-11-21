// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.wiretype.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The SubClassMismatch model.
 */
@Immutable
public final class SubClassMismatch extends SuperClass {
    /*
     * The dateTimeRfc7231 property.
     */
    @Generated
    private final DateTimeRfc1123 dateTimeRfc7231;

    /**
     * Creates an instance of SubClassMismatch class.
     * 
     * @param dateTime the dateTime value to set.
     * @param dateTimeRfc7231 the dateTimeRfc7231 value to set.
     */
    @Generated
    public SubClassMismatch(OffsetDateTime dateTime, OffsetDateTime dateTimeRfc7231) {
        super(dateTime);
        this.dateTimeRfc7231 = new DateTimeRfc1123(dateTimeRfc7231);
    }

    /**
     * Get the dateTimeRfc7231 property: The dateTimeRfc7231 property.
     * 
     * @return the dateTimeRfc7231 value.
     */
    @Generated
    public OffsetDateTime getDateTimeRfc7231() {
        if (this.dateTimeRfc7231 == null) {
            return null;
        }
        return this.dateTimeRfc7231.getDateTime();
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("dateTime",
            getDateTime() == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getDateTime()));
        jsonWriter.writeStringField("dateTimeRfc7231", Objects.toString(this.dateTimeRfc7231, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SubClassMismatch from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SubClassMismatch if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SubClassMismatch.
     */
    public static SubClassMismatch fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            OffsetDateTime dateTime = null;
            OffsetDateTime dateTimeRfc7231 = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("dateTime".equals(fieldName)) {
                    dateTime = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString(),
                        DateTimeFormatter.ISO_OFFSET_DATE_TIME));
                } else if ("dateTimeRfc7231".equals(fieldName)) {
                    DateTimeRfc1123 dateTimeRfc7231Holder
                        = reader.getNullable(nonNullReader -> new DateTimeRfc1123(nonNullReader.getString()));
                    if (dateTimeRfc7231Holder != null) {
                        dateTimeRfc7231 = dateTimeRfc7231Holder.getDateTime();
                    }
                } else {
                    reader.skipChildren();
                }
            }
            return new SubClassMismatch(dateTime, dateTimeRfc7231);
        });
    }
}
