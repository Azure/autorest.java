// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.model.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The Resource3 model.
 */
@Immutable
public final class Resource3 implements JsonSerializable<Resource3> {
    /*
     * The name property.
     */
    @Generated
    private final String name;

    /*
     * The outputData3 property.
     */
    @Generated
    private final OutputData3 outputData3;

    /**
     * Creates an instance of Resource3 class.
     * 
     * @param name the name value to set.
     * @param outputData3 the outputData3 value to set.
     */
    @Generated
    private Resource3(String name, OutputData3 outputData3) {
        this.name = name;
        this.outputData3 = outputData3;
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
     * Get the outputData3 property: The outputData3 property.
     * 
     * @return the outputData3 value.
     */
    @Generated
    public OutputData3 getOutputData3() {
        return this.outputData3;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeJsonField("outputData3", this.outputData3);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Resource3 from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Resource3 if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Resource3.
     */
    @Generated
    public static Resource3 fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            long foundTracker = 0;
            String name = null;
            OutputData3 outputData3 = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else if ("outputData3".equals(fieldName)) {
                    outputData3 = OutputData3.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            return new Resource3(name, outputData3);
        });
    }
}
