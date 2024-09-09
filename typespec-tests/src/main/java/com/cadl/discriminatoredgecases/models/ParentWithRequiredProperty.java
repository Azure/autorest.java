// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.discriminatoredgecases.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The ParentWithRequiredProperty model.
 */
@Immutable
public class ParentWithRequiredProperty implements JsonSerializable<ParentWithRequiredProperty> {
    /*
     * The discriminator property.
     */
    @Generated
    private final String discriminator;

    /*
     * The aProperty property.
     */
    @Generated
    private final String aProperty;

    /**
     * Creates an instance of ParentWithRequiredProperty class.
     * 
     * @param discriminator the discriminator value to set.
     * @param aProperty the aProperty value to set.
     */
    @Generated
    public ParentWithRequiredProperty(String discriminator, String aProperty) {
        this.discriminator = discriminator;
        this.aProperty = aProperty;
    }

    /**
     * Get the discriminator property: The discriminator property.
     * 
     * @return the discriminator value.
     */
    @Generated
    public String getDiscriminator() {
        return this.discriminator;
    }

    /**
     * Get the aProperty property: The aProperty property.
     * 
     * @return the aProperty value.
     */
    @Generated
    public String getAProperty() {
        return this.aProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("discriminator", this.discriminator);
        jsonWriter.writeStringField("aProperty", this.aProperty);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ParentWithRequiredProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ParentWithRequiredProperty if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ParentWithRequiredProperty.
     */
    @Generated
    public static ParentWithRequiredProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String discriminator = null;
            String aProperty = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("discriminator".equals(fieldName)) {
                    discriminator = reader.getString();
                } else if ("aProperty".equals(fieldName)) {
                    aProperty = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return new ParentWithRequiredProperty(discriminator, aProperty);
        });
    }
}
