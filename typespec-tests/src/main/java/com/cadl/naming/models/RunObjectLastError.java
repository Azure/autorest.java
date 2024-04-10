// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.naming.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The RunObjectLastError model.
 */
@Immutable
public final class RunObjectLastError implements JsonSerializable<RunObjectLastError> {
    /*
     * The code property.
     */
    @Generated
    private final RunObjectCode code;

    /**
     * Creates an instance of RunObjectLastError class.
     * 
     * @param code the code value to set.
     */
    @Generated
    private RunObjectLastError(RunObjectCode code) {
        this.code = code;
    }

    /**
     * Get the code property: The code property.
     * 
     * @return the code value.
     */
    @Generated
    public RunObjectCode getCode() {
        return this.code;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("code", this.code == null ? null : this.code.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of RunObjectLastError from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of RunObjectLastError if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the RunObjectLastError.
     */
    @Generated
    public static RunObjectLastError fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            RunObjectCode code = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("code".equals(fieldName)) {
                    code = RunObjectCode.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }
            return new RunObjectLastError(code);
        });
    }
}
