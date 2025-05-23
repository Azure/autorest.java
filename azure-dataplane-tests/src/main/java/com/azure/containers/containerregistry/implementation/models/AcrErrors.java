// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/**
 * Acr error response describing why the operation failed.
 */
@Fluent
public final class AcrErrors implements JsonSerializable<AcrErrors> {
    /*
     * Array of detailed error
     */
    @Generated
    private List<AcrErrorInfo> errors;

    /**
     * Creates an instance of AcrErrors class.
     */
    @Generated
    public AcrErrors() {
    }

    /**
     * Get the errors property: Array of detailed error.
     * 
     * @return the errors value.
     */
    @Generated
    public List<AcrErrorInfo> getErrors() {
        return this.errors;
    }

    /**
     * Set the errors property: Array of detailed error.
     * 
     * @param errors the errors value to set.
     * @return the AcrErrors object itself.
     */
    @Generated
    public AcrErrors setErrors(List<AcrErrorInfo> errors) {
        this.errors = errors;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeArrayField("errors", this.errors, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AcrErrors from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of AcrErrors if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IOException If an error occurs while reading the AcrErrors.
     */
    @Generated
    public static AcrErrors fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AcrErrors deserializedAcrErrors = new AcrErrors();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("errors".equals(fieldName)) {
                    List<AcrErrorInfo> errors = reader.readArray(reader1 -> AcrErrorInfo.fromJson(reader1));
                    deserializedAcrErrors.errors = errors;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAcrErrors;
        });
    }
}
