// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.union.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The User model.
 */
@Immutable
public final class User implements JsonSerializable<User> {

    /*
     * The user property.
     */
    @Generated
    private final String user;

    /**
     * Creates an instance of User class.
     *
     * @param user the user value to set.
     */
    @Generated
    public User(String user) {
        this.user = user;
    }

    /**
     * Get the user property: The user property.
     *
     * @return the user value.
     */
    @Generated
    public String getUser() {
        return this.user;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("user", this.user);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of User from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of User if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the User.
     */
    public static User fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String user = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("user".equals(fieldName)) {
                    user = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return new User(user);
        });
    }
}
