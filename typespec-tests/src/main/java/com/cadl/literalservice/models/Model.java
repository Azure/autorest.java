// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.literalservice.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Model model.
 */
@Fluent
public final class Model implements JsonSerializable<Model> {
    /*
     * The literal property.
     */
    @Generated
    private final String literal = "literal";

    /*
     * The optionalLiteral property.
     */
    @Generated
    private ModelOptionalLiteral optionalLiteral;

    /**
     * Creates an instance of Model class.
     */
    @Generated
    public Model() {
    }

    /**
     * Get the literal property: The literal property.
     * 
     * @return the literal value.
     */
    @Generated
    public String getLiteral() {
        return this.literal;
    }

    /**
     * Get the optionalLiteral property: The optionalLiteral property.
     * 
     * @return the optionalLiteral value.
     */
    @Generated
    public ModelOptionalLiteral getOptionalLiteral() {
        return this.optionalLiteral;
    }

    /**
     * Set the optionalLiteral property: The optionalLiteral property.
     * 
     * @param optionalLiteral the optionalLiteral value to set.
     * @return the Model object itself.
     */
    @Generated
    public Model setOptionalLiteral(ModelOptionalLiteral optionalLiteral) {
        this.optionalLiteral = optionalLiteral;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("literal", this.literal);
        jsonWriter.writeStringField("optionalLiteral",
            this.optionalLiteral == null ? null : this.optionalLiteral.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Model from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Model if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Model.
     */
    public static Model fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean literalFound = false;
            String literal = null;
            ModelOptionalLiteral optionalLiteral = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("literal".equals(fieldName)) {
                    literal = reader.getString();
                    literalFound = true;
                } else if ("optionalLiteral".equals(fieldName)) {
                    optionalLiteral = ModelOptionalLiteral.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }
            if (literalFound) {
                Model deserializedModel = new Model();
                deserializedModel.optionalLiteral = optionalLiteral;

                return deserializedModel;
            }
            List<String> missingProperties = new ArrayList<>();
            if (!literalFound) {
                missingProperties.add("literal");
            }

            throw new IllegalStateException(
                "Missing required property/properties: " + String.join(", ", missingProperties));
        });
    }
}
