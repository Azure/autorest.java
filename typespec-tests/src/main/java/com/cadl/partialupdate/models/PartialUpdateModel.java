// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.partialupdate.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The PartialUpdateModel model.
 */
@Immutable
public final class PartialUpdateModel implements JsonSerializable<PartialUpdateModel> {

    /*
     * The boolean property.
     */
    @JsonProperty(value = "boolean", required = true)
    private boolean booleanProperty;

    /*
     * The string property.
     */
    @JsonProperty(value = "string", required = true)
    private String string;

    /*
     * The bytes property.
     */
    @JsonProperty(value = "bytes", required = true)
    private byte[] bytes;

    /**
     * Creates an instance of PartialUpdateModel class.
     *
     * @param booleanProperty the booleanProperty value to set.
     * @param string the string value to set.
     * @param bytes the bytes value to set.
     */
    @JsonCreator
    private PartialUpdateModel(@JsonProperty(value = "boolean", required = true) boolean booleanProperty,
        @JsonProperty(value = "string", required = true) String string,
        @JsonProperty(value = "bytes", required = true) byte[] bytes) {
        this.booleanProperty = booleanProperty;
        this.string = string;
        this.bytes = bytes;
    }

    /**
     * Get the booleanProperty property: The boolean property.
     *
     * @return the booleanProperty value.
     */
    public boolean isBooleanProperty() {
        return this.booleanProperty;
    }

    /**
     * Get the string property: The string property.
     *
     * @return the string value.
     */
    public String getString() {
        return this.string;
    }

    /**
     * Get the bytes property: The bytes property.
     *
     * @return the bytes value.
     */
    public byte[] getBytes() {
        return CoreUtils.clone(this.bytes);
    }

    /*
     * The aggregation function to be applied on the client metric. Allowed functions
     * - ‘percentage’ - for error metric , ‘avg’, ‘p50’, ‘p90’, ‘p95’, ‘p99’, ‘min’,
     * ‘max’ - for response_time_ms and latency metric, ‘avg’ - for requests_per_sec,
     * ‘count’ - for requests
     */
    @Generated
    private String aggregate;

    /**
     * Get the aggregate property: The aggregation function to be applied on the client metric. Allowed functions
     * - ‘percentage’ - for error metric , ‘avg’, ‘p50’, ‘p90’, ‘p95’, ‘p99’, ‘min’,
     * ‘max’ - for response_time_ms and latency metric, ‘avg’ - for requests_per_sec,
     * ‘count’ - for requests.
     *
     * @return the aggregate value.
     */
    @Generated
    public String getAggregate() {
        return this.aggregate;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("boolean", this.booleanProperty);
        jsonWriter.writeStringField("string", this.string);
        jsonWriter.writeBinaryField("bytes", this.bytes);
        jsonWriter.writeStringField("aggregate", this.aggregate);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of PartialUpdateModel from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of PartialUpdateModel if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the PartialUpdateModel.
     */
    public static PartialUpdateModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean booleanPropertyFound = false;
            boolean booleanProperty = false;
            boolean stringFound = false;
            String string = null;
            boolean bytesFound = false;
            byte[] bytes = null;
            String aggregate = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("boolean".equals(fieldName)) {
                    booleanProperty = reader.getBoolean();
                    booleanPropertyFound = true;
                } else if ("string".equals(fieldName)) {
                    string = reader.getString();
                    stringFound = true;
                } else if ("bytes".equals(fieldName)) {
                    bytes = reader.getBinary();
                    bytesFound = true;
                } else if ("aggregate".equals(fieldName)) {
                    aggregate = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            if (booleanPropertyFound && stringFound && bytesFound) {
                PartialUpdateModel deserializedPartialUpdateModel
                    = new PartialUpdateModel(booleanProperty, string, bytes);
                deserializedPartialUpdateModel.aggregate = aggregate;
                return deserializedPartialUpdateModel;
            }
            List<String> missingProperties = new ArrayList<>();
            if (!booleanPropertyFound) {
                missingProperties.add("boolean");
            }
            if (!stringFound) {
                missingProperties.add("string");
            }
            if (!bytesFound) {
                missingProperties.add("bytes");
            }
            throw new IllegalStateException(
                "Missing required property/properties: " + String.join(", ", missingProperties));
        });
    }
}
