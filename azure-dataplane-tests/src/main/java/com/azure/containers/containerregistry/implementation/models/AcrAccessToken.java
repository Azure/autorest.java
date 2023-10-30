// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The AcrAccessToken model.
 */
@Fluent
public final class AcrAccessToken implements JsonSerializable<AcrAccessToken> {
    /*
     * The access token for performing authenticated requests
     */
    private String accessToken;

    /**
     * Creates an instance of AcrAccessToken class.
     */
    public AcrAccessToken() {
    }

    /**
     * Get the accessToken property: The access token for performing authenticated requests.
     * 
     * @return the accessToken value.
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * Set the accessToken property: The access token for performing authenticated requests.
     * 
     * @param accessToken the accessToken value to set.
     * @return the AcrAccessToken object itself.
     */
    public AcrAccessToken setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("access_token", this.accessToken);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AcrAccessToken from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of AcrAccessToken if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the AcrAccessToken.
     */
    public static AcrAccessToken fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AcrAccessToken deserializedAcrAccessToken = new AcrAccessToken();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("access_token".equals(fieldName)) {
                    deserializedAcrAccessToken.accessToken = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAcrAccessToken;
        });
    }
}
