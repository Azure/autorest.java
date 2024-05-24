// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.core.basic.models;

import com._specs_.azure.core.basic.implementation.JsonMergePatchHelper;
import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * UserOrder for testing list with expand.
 */
@Fluent
public final class UserOrder implements JsonSerializable<UserOrder> {
    /*
     * The user's id.
     */
    @Generated
    private int id;

    /*
     * The user's id.
     */
    @Generated
    private int userId;

    /*
     * The user's order detail
     */
    @Generated
    private String detail;

    /**
     * Stores updated model property, the value is property name, not serialized name.
     */
    @Generated
    private final Set<String> updatedProperties = new HashSet<>();

    @Generated
    private boolean jsonMergePatch;

    @Generated
    private void serializeAsJsonMergePatch(boolean jsonMergePatch) {
        this.jsonMergePatch = jsonMergePatch;
    }

    static {
        JsonMergePatchHelper.setUserOrderAccessor(new JsonMergePatchHelper.UserOrderAccessor() {
            @Override
            public UserOrder prepareModelForJsonMergePatch(UserOrder model, boolean jsonMergePatchEnabled) {
                model.serializeAsJsonMergePatch(jsonMergePatchEnabled);
                return model;
            }

            @Override
            public boolean isJsonMergePatch(UserOrder model) {
                return model.jsonMergePatch;
            }
        });
    }

    /**
     * Creates an instance of UserOrder class.
     */
    @Generated
    public UserOrder() {
    }

    /**
     * Get the id property: The user's id.
     * 
     * @return the id value.
     */
    @Generated
    public int getId() {
        return this.id;
    }

    /**
     * Get the userId property: The user's id.
     * 
     * @return the userId value.
     */
    @Generated
    public int getUserId() {
        return this.userId;
    }

    /**
     * Set the userId property: The user's id.
     * <p>Required when create the resource.</p>
     * 
     * @param userId the userId value to set.
     * @return the UserOrder object itself.
     */
    @Generated
    public UserOrder setUserId(int userId) {
        this.userId = userId;
        this.updatedProperties.add("userId");
        return this;
    }

    /**
     * Get the detail property: The user's order detail.
     * 
     * @return the detail value.
     */
    @Generated
    public String getDetail() {
        return this.detail;
    }

    /**
     * Set the detail property: The user's order detail.
     * <p>Required when create the resource.</p>
     * 
     * @param detail the detail value to set.
     * @return the UserOrder object itself.
     */
    @Generated
    public UserOrder setDetail(String detail) {
        this.detail = detail;
        this.updatedProperties.add("detail");
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        if (jsonMergePatch) {
            return toJsonMergePatch(jsonWriter);
        } else {
            jsonWriter.writeStartObject();
            jsonWriter.writeIntField("userId", this.userId);
            jsonWriter.writeStringField("detail", this.detail);
            return jsonWriter.writeEndObject();
        }
    }

    @Generated
    private JsonWriter toJsonMergePatch(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("userId", this.userId);
        if (updatedProperties.contains("detail")) {
            if (this.detail == null) {
                jsonWriter.writeNullField("detail");
            } else {
                jsonWriter.writeStringField("detail", this.detail);
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of UserOrder from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of UserOrder if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the UserOrder.
     */
    @Generated
    public static UserOrder fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            UserOrder deserializedUserOrder = new UserOrder();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedUserOrder.id = reader.getInt();
                } else if ("userId".equals(fieldName)) {
                    deserializedUserOrder.userId = reader.getInt();
                } else if ("detail".equals(fieldName)) {
                    deserializedUserOrder.detail = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedUserOrder;
        });
    }
}
