// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.wiretype.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** The SuperClassMismatch model. */
@Immutable
public class SuperClassMismatch implements JsonSerializable<SuperClassMismatch> {
    /*
     * The dateTimeRfc7231 property.
     */
    @Generated private final DateTimeRfc1123 dateTimeRfc7231;

    /**
     * Creates an instance of SuperClassMismatch class.
     *
     * @param dateTimeRfc7231 the dateTimeRfc7231 value to set.
     */
    @Generated
    public SuperClassMismatch(OffsetDateTime dateTimeRfc7231) {
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
        jsonWriter.writeStringField("dateTimeRfc7231", Objects.toString(this.dateTimeRfc7231, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SuperClassMismatch from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of SuperClassMismatch if the JsonReader was pointing to an instance of it, or null if it was
     *     pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SuperClassMismatch.
     */
    public static SuperClassMismatch fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    boolean dateTimeRfc7231Found = false;
                    OffsetDateTime dateTimeRfc7231 = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("dateTimeRfc7231".equals(fieldName)) {
                            DateTimeRfc1123 dateTimeRfc7231 =
                                    reader.getNullable(nonNullReader -> new DateTimeRfc1123(nonNullReader.getString()));
                            if (dateTimeRfc7231 != null) {
                                dateTimeRfc7231 = dateTimeRfc7231.getDateTime();
                            }
                            dateTimeRfc7231Found = true;
                        } else {
                            reader.skipChildren();
                        }
                    }
                    if (dateTimeRfc7231Found) {
                        SuperClassMismatch deserializedSuperClassMismatch = new SuperClassMismatch(dateTimeRfc7231);

                        return deserializedSuperClassMismatch;
                    }
                    List<String> missingProperties = new ArrayList<>();
                    if (!dateTimeRfc7231Found) {
                        missingProperties.add("dateTimeRfc7231");
                    }

                    throw new IllegalStateException(
                            "Missing required property/properties: " + String.join(", ", missingProperties));
                });
    }
}
