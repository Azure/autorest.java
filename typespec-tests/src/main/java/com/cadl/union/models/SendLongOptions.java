// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.union.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.BinaryData;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Options for sendLong API.
 */
@Fluent
public final class SendLongOptions implements JsonSerializable<SendLongOptions> {

    /*
     * The id property.
     */
    @Generated
    private final String id;

    /*
     * The filter property.
     */
    @Generated
    private String filter;

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
     * The dataUnion property.
     */
    @Generated
    private BinaryData dataUnion;

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

    /**
     * Creates an instance of SendLongOptions class.
     *
     * @param id the id value to set.
     * @param input the input value to set.
     * @param dataInt the dataInt value to set.
     */
    @Generated
    public SendLongOptions(String id, String input, int dataInt) {
        this.id = id;
        this.input = input;
        this.dataInt = dataInt;
    }

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    @Generated
    public String getId() {
        return this.id;
    }

    /**
     * Get the filter property: The filter property.
     *
     * @return the filter value.
     */
    @Generated
    public String getFilter() {
        return this.filter;
    }

    /**
     * Set the filter property: The filter property.
     *
     * @param filter the filter value to set.
     * @return the SendLongOptions object itself.
     */
    @Generated
    public SendLongOptions setFilter(String filter) {
        this.filter = filter;
        return this;
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
     * @return the SendLongOptions object itself.
     */
    @Generated
    public SendLongOptions setUser(User user) {
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
     * Get the dataUnion property: The dataUnion property.
     *
     * @return the dataUnion value.
     */
    @Generated
    public BinaryData getDataUnion() {
        return this.dataUnion;
    }

    /**
     * Set the dataUnion property: The dataUnion property.
     *
     * @param dataUnion the dataUnion value to set.
     * @return the SendLongOptions object itself.
     */
    @Generated
    public SendLongOptions setDataUnion(BinaryData dataUnion) {
        this.dataUnion = dataUnion;
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
     * @return the SendLongOptions object itself.
     */
    @Generated
    public SendLongOptions setDataLong(Long dataLong) {
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
     * @return the SendLongOptions object itself.
     */
    @Generated
    public SendLongOptions setDataFloat(Double dataFloat) {
        this.dataFloat = dataFloat;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", this.id);
        jsonWriter.writeStringField("input", this.input);
        jsonWriter.writeIntField("dataInt", this.dataInt);
        jsonWriter.writeStringField("filter", this.filter);
        jsonWriter.writeJsonField("user", this.user);
        if (this.dataUnion != null) {
            jsonWriter.writeUntypedField("dataUnion", this.dataUnion.toObject(Object.class));
        }
        jsonWriter.writeNumberField("dataLong", this.dataLong);
        jsonWriter.writeNumberField("data_float", this.dataFloat);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SendLongOptions from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of SendLongOptions if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SendLongOptions.
     */
    public static SendLongOptions fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String id = null;
            String input = null;
            int dataInt = 0;
            String filter = null;
            User user = null;
            BinaryData dataUnion = null;
            Long dataLong = null;
            Double dataFloat = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("id".equals(fieldName)) {
                    id = reader.getString();
                } else if ("input".equals(fieldName)) {
                    input = reader.getString();
                } else if ("dataInt".equals(fieldName)) {
                    dataInt = reader.getInt();
                } else if ("filter".equals(fieldName)) {
                    filter = reader.getString();
                } else if ("user".equals(fieldName)) {
                    user = User.fromJson(reader);
                } else if ("dataUnion".equals(fieldName)) {
                    dataUnion = reader.getNullable(nonNullReader -> BinaryData.fromObject(nonNullReader.readUntyped()));
                } else if ("dataLong".equals(fieldName)) {
                    dataLong = reader.getNullable(JsonReader::getLong);
                } else if ("data_float".equals(fieldName)) {
                    dataFloat = reader.getNullable(JsonReader::getDouble);
                } else {
                    reader.skipChildren();
                }
            }
            SendLongOptions deserializedSendLongOptions = new SendLongOptions(id, input, dataInt);
            deserializedSendLongOptions.filter = filter;
            deserializedSendLongOptions.user = user;
            deserializedSendLongOptions.dataUnion = dataUnion;
            deserializedSendLongOptions.dataLong = dataLong;
            deserializedSendLongOptions.dataFloat = dataFloat;
            return deserializedSendLongOptions;
        });
    }
}
