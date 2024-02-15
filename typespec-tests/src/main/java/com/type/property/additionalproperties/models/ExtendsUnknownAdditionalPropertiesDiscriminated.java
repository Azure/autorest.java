// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.additionalproperties.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The model extends from Record&lt;unknown&gt; with a discriminator.
 */
@Fluent
public class ExtendsUnknownAdditionalPropertiesDiscriminated
    implements JsonSerializable<ExtendsUnknownAdditionalPropertiesDiscriminated> {
    /*
     * The discriminator
     */
    @Generated
    private String kind;

    /*
     * The name property
     */
    @Generated
    private final String name;

    /*
     * Additional properties
     */
    @Generated
    private Map<String, Object> additionalProperties;

    /**
     * Creates an instance of ExtendsUnknownAdditionalPropertiesDiscriminated class.
     * 
     * @param name the name value to set.
     */
    @Generated
    public ExtendsUnknownAdditionalPropertiesDiscriminated(String name) {
        this.kind = null;
        this.name = name;
    }

    /**
     * Get the kind property: The discriminator.
     * 
     * @return the kind value.
     */
    @Generated
    public String getKind() {
        return this.kind;
    }

    /**
     * Set the kind property: The discriminator.
     * 
     * @param kind the kind value to set.
     * @return the ExtendsUnknownAdditionalPropertiesDiscriminated object itself.
     */
    ExtendsUnknownAdditionalPropertiesDiscriminated setKind(String kind) {
        this.kind = kind;
        return this;
    }

    /**
     * Get the name property: The name property.
     * 
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Get the additionalProperties property: Additional properties.
     * 
     * @return the additionalProperties value.
     */
    @Generated
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Set the additionalProperties property: Additional properties.
     * 
     * @param additionalProperties the additionalProperties value to set.
     * @return the ExtendsUnknownAdditionalPropertiesDiscriminated object itself.
     */
    @Generated
    public ExtendsUnknownAdditionalPropertiesDiscriminated
        setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeStringField("kind", this.kind);
        if (additionalProperties != null) {
            for (Map.Entry<String, Object> additionalProperty : additionalProperties.entrySet()) {
                jsonWriter.writeUntypedField(additionalProperty.getKey(), additionalProperty.getValue());
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ExtendsUnknownAdditionalPropertiesDiscriminated from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ExtendsUnknownAdditionalPropertiesDiscriminated if the JsonReader was pointing to an
     * instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ExtendsUnknownAdditionalPropertiesDiscriminated.
     */
    public static ExtendsUnknownAdditionalPropertiesDiscriminated fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String discriminatorValue = null;
            try (JsonReader readerToUse = reader.bufferObject()) {
                readerToUse.nextToken(); // Prepare for reading
                while (readerToUse.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = readerToUse.getFieldName();
                    readerToUse.nextToken();
                    if ("kind".equals(fieldName)) {
                        discriminatorValue = readerToUse.getString();
                        break;
                    } else {
                        readerToUse.skipChildren();
                    }
                }
                // Use the discriminator value to determine which subtype should be deserialized.
                if ("derived".equals(discriminatorValue)) {
                    return ExtendsUnknownAdditionalPropertiesDiscriminatedDerived.fromJson(readerToUse.reset());
                } else {
                    return fromJsonKnownDiscriminator(readerToUse.reset());
                }
            }
        });
    }

    static ExtendsUnknownAdditionalPropertiesDiscriminated fromJsonKnownDiscriminator(JsonReader jsonReader)
        throws IOException {
        return jsonReader.readObject(reader -> {
            String name = null;
            String kind = null;
            Map<String, Object> additionalProperties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else if ("kind".equals(fieldName)) {
                    kind = reader.getString();
                } else {
                    if (additionalProperties == null) {
                        additionalProperties = new LinkedHashMap<>();
                    }

                    additionalProperties.put(fieldName, reader.readUntyped());
                }
            }
            ExtendsUnknownAdditionalPropertiesDiscriminated deserializedExtendsUnknownAdditionalPropertiesDiscriminated
                = new ExtendsUnknownAdditionalPropertiesDiscriminated(name);
            deserializedExtendsUnknownAdditionalPropertiesDiscriminated.kind = kind;
            deserializedExtendsUnknownAdditionalPropertiesDiscriminated.additionalProperties = additionalProperties;

            return deserializedExtendsUnknownAdditionalPropertiesDiscriminated;
        });
    }
}
