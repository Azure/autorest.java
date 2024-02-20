// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.naming.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * summary of Request
 * 
 * description of Request.
 */
@Fluent
public final class DataRequest implements JsonSerializable<DataRequest> {
    /*
     * The parameters property.
     */
    @Generated
    private RequestParameters parameters;

    /**
     * Creates an instance of DataRequest class.
     */
    @Generated
    public DataRequest() {
    }

    /**
     * Get the parameters property: The parameters property.
     * 
     * @return the parameters value.
     */
    @Generated
    public RequestParameters getParameters() {
        return this.parameters;
    }

    /**
     * Set the parameters property: The parameters property.
     * 
     * @param parameters the parameters value to set.
     * @return the DataRequest object itself.
     */
    @Generated
    public DataRequest setParameters(RequestParameters parameters) {
        this.parameters = parameters;
        return this;
    }

    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("parameters", this.parameters);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DataRequest from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DataRequest if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the DataRequest.
     */
    @Generated
    public static DataRequest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DataRequest deserializedDataRequest = new DataRequest();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("parameters".equals(fieldName)) {
                    deserializedDataRequest.parameters = RequestParameters.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDataRequest;
        });
    }
}
