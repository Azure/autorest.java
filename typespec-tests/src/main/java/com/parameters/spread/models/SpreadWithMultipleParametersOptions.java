// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.parameters.spread.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Options for spreadWithMultipleParameters API.
 */
@Immutable
public final class SpreadWithMultipleParametersOptions
    implements JsonSerializable<SpreadWithMultipleParametersOptions> {
    /*
     * The id property.
     */
    @Generated
    private final String id;

    /*
     * The x-ms-test-header property.
     */
    @Generated
    private final String xMsTestHeader;

    /*
     * The prop1 property.
     */
    @Generated
    private final String prop1;

    /*
     * The prop2 property.
     */
    @Generated
    private final String prop2;

    /*
     * The prop3 property.
     */
    @Generated
    private final String prop3;

    /*
     * The prop4 property.
     */
    @Generated
    private final String prop4;

    /*
     * The prop5 property.
     */
    @Generated
    private final String prop5;

    /*
     * The prop6 property.
     */
    @Generated
    private final String prop6;

    /**
     * Creates an instance of SpreadWithMultipleParametersOptions class.
     * 
     * @param id the id value to set.
     * @param xMsTestHeader the xMsTestHeader value to set.
     * @param prop1 the prop1 value to set.
     * @param prop2 the prop2 value to set.
     * @param prop3 the prop3 value to set.
     * @param prop4 the prop4 value to set.
     * @param prop5 the prop5 value to set.
     * @param prop6 the prop6 value to set.
     */
    @Generated
    public SpreadWithMultipleParametersOptions(String id, String xMsTestHeader, String prop1, String prop2,
        String prop3, String prop4, String prop5, String prop6) {
        this.id = id;
        this.xMsTestHeader = xMsTestHeader;
        this.prop1 = prop1;
        this.prop2 = prop2;
        this.prop3 = prop3;
        this.prop4 = prop4;
        this.prop5 = prop5;
        this.prop6 = prop6;
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
     * Get the xMsTestHeader property: The x-ms-test-header property.
     * 
     * @return the xMsTestHeader value.
     */
    @Generated
    public String getXMsTestHeader() {
        return this.xMsTestHeader;
    }

    /**
     * Get the prop1 property: The prop1 property.
     * 
     * @return the prop1 value.
     */
    @Generated
    public String getProp1() {
        return this.prop1;
    }

    /**
     * Get the prop2 property: The prop2 property.
     * 
     * @return the prop2 value.
     */
    @Generated
    public String getProp2() {
        return this.prop2;
    }

    /**
     * Get the prop3 property: The prop3 property.
     * 
     * @return the prop3 value.
     */
    @Generated
    public String getProp3() {
        return this.prop3;
    }

    /**
     * Get the prop4 property: The prop4 property.
     * 
     * @return the prop4 value.
     */
    @Generated
    public String getProp4() {
        return this.prop4;
    }

    /**
     * Get the prop5 property: The prop5 property.
     * 
     * @return the prop5 value.
     */
    @Generated
    public String getProp5() {
        return this.prop5;
    }

    /**
     * Get the prop6 property: The prop6 property.
     * 
     * @return the prop6 value.
     */
    @Generated
    public String getProp6() {
        return this.prop6;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", this.id);
        jsonWriter.writeStringField("x-ms-test-header", this.xMsTestHeader);
        jsonWriter.writeStringField("prop1", this.prop1);
        jsonWriter.writeStringField("prop2", this.prop2);
        jsonWriter.writeStringField("prop3", this.prop3);
        jsonWriter.writeStringField("prop4", this.prop4);
        jsonWriter.writeStringField("prop5", this.prop5);
        jsonWriter.writeStringField("prop6", this.prop6);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SpreadWithMultipleParametersOptions from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SpreadWithMultipleParametersOptions if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SpreadWithMultipleParametersOptions.
     */
    public static SpreadWithMultipleParametersOptions fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String id = null;
            String xMsTestHeader = null;
            String prop1 = null;
            String prop2 = null;
            String prop3 = null;
            String prop4 = null;
            String prop5 = null;
            String prop6 = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    id = reader.getString();
                } else if ("x-ms-test-header".equals(fieldName)) {
                    xMsTestHeader = reader.getString();
                } else if ("prop1".equals(fieldName)) {
                    prop1 = reader.getString();
                } else if ("prop2".equals(fieldName)) {
                    prop2 = reader.getString();
                } else if ("prop3".equals(fieldName)) {
                    prop3 = reader.getString();
                } else if ("prop4".equals(fieldName)) {
                    prop4 = reader.getString();
                } else if ("prop5".equals(fieldName)) {
                    prop5 = reader.getString();
                } else if ("prop6".equals(fieldName)) {
                    prop6 = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return new SpreadWithMultipleParametersOptions(id, xMsTestHeader, prop1, prop2, prop3, prop4, prop5, prop6);
        });
    }
}
