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
import com.cadl.flatten.models.Status;
import com.cadl.flatten.models.User;
import java.io.IOException;
import java.time.OffsetDateTime;

/**
 * The SendLongRequest model.
 */
@Fluent
public final class SendLongRequest implements JsonSerializable<SendLongRequest> {
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

    /*
     * The dataInt property.
     */
    @Generated
    private final int dataInt;

    /*
     * The dataIntOptional property.
     */
    @Generated
    private Integer dataIntOptional;

    /*
     * The dataLong property.
     */
    @Generated
    private Long dataLong;

    /*
     * The data_float property.
     */
    @Generated
    private Double dataFloat;

    /*
     * The item's unique id
     */
    @Generated
    private long id;

    /*
     * The item's title
     */
    @Generated
    private final String title;

    /*
     * A longer description of the todo item in markdown format
     */
    @Generated
    private String description;

    /*
     * The status of the todo item
     */
    @Generated
    private final Status status;

    /*
     * When the todo item was created.
     */
    @Generated
    private OffsetDateTime createdAt;

    /*
     * When the todo item was last updated
     */
    @Generated
    private OffsetDateTime updatedAt;

    /*
     * When the todo item was makred as completed
     */
    @Generated
    private OffsetDateTime completedAt;

    /*
     * The _dummy property.
     */
    @Generated
    private String dummy;

    /*
     * The constant property.
     */
    @Generated
    private final String constant = "constant";

    /**
     * Creates an instance of SendLongRequest class.
     * 
     * @param input the input value to set.
     * @param dataInt the dataInt value to set.
     * @param title the title value to set.
     * @param status the status value to set.
     */
    @Generated
    public SendLongRequest(String input, int dataInt, String title, Status status) {
        this.input = input;
        this.dataInt = dataInt;
        this.title = title;
        this.status = status;
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
     * @return the SendLongRequest object itself.
     */
    @Generated
    public SendLongRequest setUser(User user) {
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
     * Get the dataInt property: The dataInt property.
     * 
     * @return the dataInt value.
     */
    @Generated
    public int getDataInt() {
        return this.dataInt;
    }

    /**
     * Get the dataIntOptional property: The dataIntOptional property.
     * 
     * @return the dataIntOptional value.
     */
    @Generated
    public Integer getDataIntOptional() {
        return this.dataIntOptional;
    }

    /**
     * Set the dataIntOptional property: The dataIntOptional property.
     * 
     * @param dataIntOptional the dataIntOptional value to set.
     * @return the SendLongRequest object itself.
     */
    @Generated
    public SendLongRequest setDataIntOptional(Integer dataIntOptional) {
        this.dataIntOptional = dataIntOptional;
        return this;
    }

    /**
     * Get the dataLong property: The dataLong property.
     * 
     * @return the dataLong value.
     */
    @Generated
    public Long getDataLong() {
        return this.dataLong;
    }

    /**
     * Set the dataLong property: The dataLong property.
     * 
     * @param dataLong the dataLong value to set.
     * @return the SendLongRequest object itself.
     */
    @Generated
    public SendLongRequest setDataLong(Long dataLong) {
        this.dataLong = dataLong;
        return this;
    }

    /**
     * Get the dataFloat property: The data_float property.
     * 
     * @return the dataFloat value.
     */
    @Generated
    public Double getDataFloat() {
        return this.dataFloat;
    }

    /**
     * Set the dataFloat property: The data_float property.
     * 
     * @param dataFloat the dataFloat value to set.
     * @return the SendLongRequest object itself.
     */
    @Generated
    public SendLongRequest setDataFloat(Double dataFloat) {
        this.dataFloat = dataFloat;
        return this;
    }

    /**
     * Get the id property: The item's unique id.
     * 
     * @return the id value.
     */
    @Generated
    public long getId() {
        return this.id;
    }

    /**
     * Get the title property: The item's title.
     * 
     * @return the title value.
     */
    @Generated
    public String getTitle() {
        return this.title;
    }

    /**
     * Get the description property: A longer description of the todo item in markdown format.
     * 
     * @return the description value.
     */
    @Generated
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: A longer description of the todo item in markdown format.
     * 
     * @param description the description value to set.
     * @return the SendLongRequest object itself.
     */
    @Generated
    public SendLongRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the status property: The status of the todo item.
     * 
     * @return the status value.
     */
    @Generated
    public Status getStatus() {
        return this.status;
    }

    /**
     * Get the createdAt property: When the todo item was created.
     * 
     * @return the createdAt value.
     */
    @Generated
    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Get the updatedAt property: When the todo item was last updated.
     * 
     * @return the updatedAt value.
     */
    @Generated
    public OffsetDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Get the completedAt property: When the todo item was makred as completed.
     * 
     * @return the completedAt value.
     */
    @Generated
    public OffsetDateTime getCompletedAt() {
        return this.completedAt;
    }

    /**
     * Get the dummy property: The _dummy property.
     * 
     * @return the dummy value.
     */
    @Generated
    public String getDummy() {
        return this.dummy;
    }

    /**
     * Set the dummy property: The _dummy property.
     * 
     * @param dummy the dummy value to set.
     * @return the SendLongRequest object itself.
     */
    @Generated
    public SendLongRequest setDummy(String dummy) {
        this.dummy = dummy;
        return this;
    }

    /**
     * Get the constant property: The constant property.
     * 
     * @return the constant value.
     */
    @Generated
    public String getConstant() {
        return this.constant;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("input", this.input);
        jsonWriter.writeIntField("dataInt", this.dataInt);
        jsonWriter.writeStringField("title", this.title);
        jsonWriter.writeStringField("status", this.status == null ? null : this.status.toString());
        jsonWriter.writeStringField("constant", this.constant);
        jsonWriter.writeJsonField("user", this.user);
        jsonWriter.writeNumberField("dataIntOptional", this.dataIntOptional);
        jsonWriter.writeNumberField("dataLong", this.dataLong);
        jsonWriter.writeNumberField("data_float", this.dataFloat);
        jsonWriter.writeStringField("description", this.description);
        jsonWriter.writeStringField("_dummy", this.dummy);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SendLongRequest from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SendLongRequest if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SendLongRequest.
     */
    @Generated
    public static SendLongRequest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String input = null;
            int dataInt = 0;
            long id = 0L;
            String title = null;
            Status status = null;
            OffsetDateTime createdAt = null;
            OffsetDateTime updatedAt = null;
            User user = null;
            Integer dataIntOptional = null;
            Long dataLong = null;
            Double dataFloat = null;
            String description = null;
            OffsetDateTime completedAt = null;
            String dummy = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("input".equals(fieldName)) {
                    input = reader.getString();
                } else if ("dataInt".equals(fieldName)) {
                    dataInt = reader.getInt();
                } else if ("id".equals(fieldName)) {
                    id = reader.getLong();
                } else if ("title".equals(fieldName)) {
                    title = reader.getString();
                } else if ("status".equals(fieldName)) {
                    status = Status.fromString(reader.getString());
                } else if ("createdAt".equals(fieldName)) {
                    createdAt = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("updatedAt".equals(fieldName)) {
                    updatedAt = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("user".equals(fieldName)) {
                    user = User.fromJson(reader);
                } else if ("dataIntOptional".equals(fieldName)) {
                    dataIntOptional = reader.getNullable(JsonReader::getInt);
                } else if ("dataLong".equals(fieldName)) {
                    dataLong = reader.getNullable(JsonReader::getLong);
                } else if ("data_float".equals(fieldName)) {
                    dataFloat = reader.getNullable(JsonReader::getDouble);
                } else if ("description".equals(fieldName)) {
                    description = reader.getString();
                } else if ("completedAt".equals(fieldName)) {
                    completedAt = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("_dummy".equals(fieldName)) {
                    dummy = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            SendLongRequest deserializedSendLongRequest = new SendLongRequest(input, dataInt, title, status);
            deserializedSendLongRequest.id = id;
            deserializedSendLongRequest.createdAt = createdAt;
            deserializedSendLongRequest.updatedAt = updatedAt;
            deserializedSendLongRequest.user = user;
            deserializedSendLongRequest.dataIntOptional = dataIntOptional;
            deserializedSendLongRequest.dataLong = dataLong;
            deserializedSendLongRequest.dataFloat = dataFloat;
            deserializedSendLongRequest.description = description;
            deserializedSendLongRequest.completedAt = completedAt;
            deserializedSendLongRequest.dummy = dummy;

            return deserializedSendLongRequest;
        });
    }
}
