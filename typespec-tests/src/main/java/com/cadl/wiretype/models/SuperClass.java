// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.wiretype.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** The SuperClass model. */
@Immutable
public class SuperClass implements JsonSerializable<SuperClass> {
    /*
     * The dateTime property.
     */
    @Generated private final OffsetDateTime dateTime;

    /**
     * Creates an instance of SuperClass class.
     *
     * @param dateTime the dateTime value to set.
     */
    @Generated
    public SuperClass(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Get the dateTime property: The dateTime property.
     *
     * @return the dateTime value.
     */
    @Generated
    public OffsetDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("dateTime", Objects.toString(this.dateTime, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SuperClass from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of SuperClass if the JsonReader was pointing to an instance of it, or null if it was pointing
     *     to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SuperClass.
     */
    public static SuperClass fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    boolean dateTimeFound = false;
                    OffsetDateTime dateTime = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("dateTime".equals(fieldName)) {
                            dateTime =
                                    reader.getNullable(
                                            nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                            dateTimeFound = true;
                        } else {
                            reader.skipChildren();
                        }
                    }
                    if (dateTimeFound) {
                        SuperClass deserializedSuperClass = new SuperClass(dateTime);

                        return deserializedSuperClass;
                    }
                    List<String> missingProperties = new ArrayList<>();
                    if (!dateTimeFound) {
                        missingProperties.add("dateTime");
                    }

                    throw new IllegalStateException(
                            "Missing required property/properties: " + String.join(", ", missingProperties));
                });
    }
}
