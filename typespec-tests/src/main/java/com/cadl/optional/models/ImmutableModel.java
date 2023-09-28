// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.optional.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** The ImmutableModel model. */
@Immutable
public final class ImmutableModel implements JsonSerializable<ImmutableModel> {
    /*
     * The stringReadWriteRequired property.
     */
    @Generated private final String stringReadWriteRequired;

    /*
     * The stringReadOnlyOptional property.
     */
    @Generated private String stringReadOnlyOptional;

    /**
     * Creates an instance of ImmutableModel class.
     *
     * @param stringReadWriteRequired the stringReadWriteRequired value to set.
     */
    @Generated
    private ImmutableModel(String stringReadWriteRequired) {
        this.stringReadWriteRequired = stringReadWriteRequired;
    }

    /**
     * Get the stringReadWriteRequired property: The stringReadWriteRequired property.
     *
     * @return the stringReadWriteRequired value.
     */
    @Generated
    public String getStringReadWriteRequired() {
        return this.stringReadWriteRequired;
    }

    /**
     * Get the stringReadOnlyOptional property: The stringReadOnlyOptional property.
     *
     * @return the stringReadOnlyOptional value.
     */
    @Generated
    public String getStringReadOnlyOptional() {
        return this.stringReadOnlyOptional;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("stringReadWriteRequired", this.stringReadWriteRequired);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ImmutableModel from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ImmutableModel if the JsonReader was pointing to an instance of it, or null if it was
     *     pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ImmutableModel.
     */
    public static ImmutableModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    boolean stringReadWriteRequiredFound = false;
                    String stringReadWriteRequired = null;
                    String stringReadOnlyOptional = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("stringReadWriteRequired".equals(fieldName)) {
                            stringReadWriteRequired = reader.getString();
                            stringReadWriteRequiredFound = true;
                        } else if ("stringReadOnlyOptional".equals(fieldName)) {
                            stringReadOnlyOptional = reader.getString();
                        } else {
                            reader.skipChildren();
                        }
                    }
                    if (stringReadWriteRequiredFound) {
                        ImmutableModel deserializedImmutableModel = new ImmutableModel(stringReadWriteRequired);
                        deserializedImmutableModel.stringReadOnlyOptional = stringReadOnlyOptional;

                        return deserializedImmutableModel;
                    }
                    List<String> missingProperties = new ArrayList<>();
                    if (!stringReadWriteRequiredFound) {
                        missingProperties.add("stringReadWriteRequired");
                    }

                    throw new IllegalStateException(
                            "Missing required property/properties: " + String.join(", ", missingProperties));
                });
    }
}
