// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.core.lro.standard.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Details about a user.
 */
@Immutable
public final class User implements JsonSerializable<User> {
    /*
     * The name of user.
     */
    @Generated
    private String name;

    /*
     * The role of user
     */
    @Generated
    private final String role;

    /**
     * Creates an instance of User class.
     * 
     * @param role the role value to set.
     */
    @Generated
    public User(String role) {
        this.role = role;
    }

    /**
     * Get the name property: The name of user.
     * 
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Get the role property: The role of user.
     * 
     * @return the role value.
     */
    @Generated
    public String getRole() {
        return this.role;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("role", this.role);
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
            String name = null;
            String role = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else if ("role".equals(fieldName)) {
                    role = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            User deserializedUser = new User(role);
            deserializedUser.name = name;

            return deserializedUser;
        });
    }
}
