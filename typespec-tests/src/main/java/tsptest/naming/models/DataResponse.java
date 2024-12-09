// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package tsptest.naming.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * summary of Response
 * 
 * description of Response. Include tab ' ' in doc.
 */
@Immutable
public final class DataResponse implements JsonSerializable<DataResponse> {
    /*
     * summary of name property
     * 
     * description of name property
     */
    @Generated
    private final String name;

    /*
     * summary of data property
     * 
     * description of data property
     */
    @Generated
    private final BinaryData data;

    /*
     * summary of type property
     * 
     * description of type property
     */
    @Generated
    private final TypesModel dataType;

    /*
     * summary of status property
     * 
     * description of status property
     */
    @Generated
    private final DataStatus status;

    /*
     * The anonymous property.
     */
    @Generated
    private final RunObject anonymous;

    /**
     * Creates an instance of DataResponse class.
     * 
     * @param name the name value to set.
     * @param data the data value to set.
     * @param dataType the dataType value to set.
     * @param status the status value to set.
     * @param anonymous the anonymous value to set.
     */
    @Generated
    private DataResponse(String name, BinaryData data, TypesModel dataType, DataStatus status, RunObject anonymous) {
        this.name = name;
        this.data = data;
        this.dataType = dataType;
        this.status = status;
        this.anonymous = anonymous;
    }

    /**
     * Get the name property: summary of name property
     * 
     * description of name property.
     * 
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Get the data property: summary of data property
     * 
     * description of data property.
     * 
     * @return the data value.
     */
    @Generated
    public BinaryData getData() {
        return this.data;
    }

    /**
     * Get the dataType property: summary of type property
     * 
     * description of type property.
     * 
     * @return the dataType value.
     */
    @Generated
    public TypesModel getDataType() {
        return this.dataType;
    }

    /**
     * Get the status property: summary of status property
     * 
     * description of status property.
     * 
     * @return the status value.
     */
    @Generated
    public DataStatus getStatus() {
        return this.status;
    }

    /**
     * Get the anonymous property: The anonymous property.
     * 
     * @return the anonymous value.
     */
    @Generated
    public RunObject getAnonymous() {
        return this.anonymous;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeJsonField("data", this.data);
        jsonWriter.writeStringField("type", this.dataType == null ? null : this.dataType.toString());
        jsonWriter.writeStringField("status", this.status == null ? null : this.status.toString());
        jsonWriter.writeJsonField("anonymous", this.anonymous);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DataResponse from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DataResponse if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the DataResponse.
     */
    @Generated
    public static DataResponse fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String name = null;
            BinaryData data = null;
            TypesModel dataType = null;
            DataStatus status = null;
            RunObject anonymous = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else if ("data".equals(fieldName)) {
                    data = BinaryData.fromJson(reader);
                } else if ("type".equals(fieldName)) {
                    dataType = TypesModel.fromString(reader.getString());
                } else if ("status".equals(fieldName)) {
                    status = DataStatus.fromString(reader.getString());
                } else if ("anonymous".equals(fieldName)) {
                    anonymous = RunObject.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            return new DataResponse(name, data, dataType, status, anonymous);
        });
    }
}
