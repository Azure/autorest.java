// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.additionalproperties.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The model extends from a type that is Record&lt;unknown&gt; type.
 */
@Fluent
public final class IsUnknownAdditionalPropertiesDerived extends IsUnknownAdditionalProperties {
    /*
     * The index property
     */
    @Generated
    private final int index;

    /*
     * The age property
     */
    @Generated
    private Double age;

    /**
     * Creates an instance of IsUnknownAdditionalPropertiesDerived class.
     * 
     * @param name the name value to set.
     * @param index the index value to set.
     */
    @Generated
    public IsUnknownAdditionalPropertiesDerived(String name, int index) {
        super(name);
        this.index = index;
    }

    /**
     * Get the index property: The index property.
     * 
     * @return the index value.
     */
    @Generated
    public int getIndex() {
        return this.index;
    }

    /**
     * Get the age property: The age property.
     * 
     * @return the age value.
     */
    @Generated
    public Double getAge() {
        return this.age;
    }

    /**
     * Set the age property: The age property.
     * 
     * @param age the age value to set.
     * @return the IsUnknownAdditionalPropertiesDerived object itself.
     */
    @Generated
    public IsUnknownAdditionalPropertiesDerived setAge(Double age) {
        this.age = age;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("name", getName());
        jsonWriter.writeIntField("index", this.index);
        jsonWriter.writeNumberField("age", this.age);
        if (getAdditionalProperties() != null) {
            for (Map.Entry<String, Object> additionalProperty : getAdditionalProperties().entrySet()) {
                jsonWriter.writeUntypedField(additionalProperty.getKey(), additionalProperty.getValue());
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of IsUnknownAdditionalPropertiesDerived from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of IsUnknownAdditionalPropertiesDerived if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the IsUnknownAdditionalPropertiesDerived.
     */
    @Generated
    public static IsUnknownAdditionalPropertiesDerived fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String name = null;
            int index = 0;
            Double age = null;
            Map<String, Object> additionalProperties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else if ("index".equals(fieldName)) {
                    index = reader.getInt();
                } else if ("age".equals(fieldName)) {
                    age = reader.getNullable(JsonReader::getDouble);
                } else {
                    if (additionalProperties == null) {
                        additionalProperties = new LinkedHashMap<>();
                    }

                    additionalProperties.put(fieldName, reader.readUntyped());
                    additionalProperties.put(fieldName, null);
                }
            }
            IsUnknownAdditionalPropertiesDerived deserializedIsUnknownAdditionalPropertiesDerived
                = new IsUnknownAdditionalPropertiesDerived(name, index);
            deserializedIsUnknownAdditionalPropertiesDerived.age = age;
            deserializedIsUnknownAdditionalPropertiesDerived.setAdditionalProperties(additionalProperties);

            return deserializedIsUnknownAdditionalPropertiesDerived;
        });
    }
}
