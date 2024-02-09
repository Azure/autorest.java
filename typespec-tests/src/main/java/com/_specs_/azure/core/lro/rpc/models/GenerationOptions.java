// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.core.lro.rpc.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Options for the generation.
 */
@Immutable
public final class GenerationOptions implements JsonSerializable<GenerationOptions> {
    /*
     * Prompt.
     */
    @Generated
    private final String prompt;

    /**
     * Creates an instance of GenerationOptions class.
     * 
     * @param prompt the prompt value to set.
     */
    @Generated
    public GenerationOptions(String prompt) {
        this.prompt = prompt;
    }

    /**
     * Get the prompt property: Prompt.
     * 
     * @return the prompt value.
     */
    @Generated
    public String getPrompt() {
        return this.prompt;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("prompt", this.prompt);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of GenerationOptions from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of GenerationOptions if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the GenerationOptions.
     */
    public static GenerationOptions fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String prompt = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("prompt".equals(fieldName)) {
                    prompt = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return new GenerationOptions(prompt);
        });
    }
}
