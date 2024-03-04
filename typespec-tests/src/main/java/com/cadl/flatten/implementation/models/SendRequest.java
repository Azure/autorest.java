// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.flatten.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.cadl.flatten.models.User;
import java.io.IOException;

/**
 * The SendRequest model.
 */
@Fluent
public final class SendRequest implements JsonSerializable<SendRequest> {
    /*
     * The user property.
     */
    @Generated
    private User user;

    /*
     * The input property.
     */
    @Generated
    private final String input;

    /**
     * Creates an instance of SendRequest class.
     * 
     * @param input the input value to set.
     */
    @Generated
    public SendRequest(String input) {
        this.input = input;
    }

    /**
     * Get the user property: The user property.
     * 
     * @return the user value.
     */
    @Generated
    public User getUser() {
        return this.user;
    }

    /**
     * Set the user property: The user property.
     * 
     * @param user the user value to set.
     * @return the SendRequest object itself.
     */
    @Generated
    public SendRequest setUser(User user) {
        this.user = user;
        return this;
    }

    /**
     * Get the input property: The input property.
     * 
     * @return the input value.
     */
    @Generated
    public String getInput() {
        return this.input;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("input", this.input);
        jsonWriter.writeJsonField("user", this.user);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SendRequest from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SendRequest if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SendRequest.
     */
    @Generated
    public static SendRequest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String input = null;
            User user = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("input".equals(fieldName)) {
                    input = reader.getString();
                } else if ("user".equals(fieldName)) {
                    user = User.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            SendRequest deserializedSendRequest = new SendRequest(input);
            deserializedSendRequest.user = user;

            return deserializedSendRequest;
        });
    }
}
