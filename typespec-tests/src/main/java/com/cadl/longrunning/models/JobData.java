// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.longrunning.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The JobData model.
 */
@Fluent
public final class JobData implements JsonSerializable<JobData> {

    /*
     * The configuration property.
     */
    @Generated
    private String configuration;

    /**
     * Creates an instance of JobData class.
     */
    @Generated
    public JobData() {
    }

    /**
     * Get the configuration property: The configuration property.
     *
     * @return the configuration value.
     */
    @Generated
    public String getConfiguration() {
        return this.configuration;
    }

    /**
     * Set the configuration property: The configuration property.
     *
     * @param configuration the configuration value to set.
     * @return the JobData object itself.
     */
    @Generated
    public JobData setConfiguration(String configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("configuration", this.configuration);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of JobData from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of JobData if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IOException If an error occurs while reading the JobData.
     */
    public static JobData fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            JobData deserializedJobData = new JobData();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("configuration".equals(fieldName)) {
                    deserializedJobData.configuration = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return deserializedJobData;
        });
    }
}
