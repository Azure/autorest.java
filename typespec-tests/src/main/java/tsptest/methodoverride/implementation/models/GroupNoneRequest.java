// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package tsptest.methodoverride.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The GroupNoneRequest model.
 */
@Fluent
public final class GroupNoneRequest implements JsonSerializable<GroupNoneRequest> {
    /*
     * The prop1 property.
     */
    @Generated
    private final String prop1;

    /*
     * The prop2 property.
     */
    @Generated
    private String prop2;

    /*
     * The prop3 property.
     */
    @Generated
    private String prop3;

    /*
     * The prop4 property.
     */
    @Generated
    private String prop4;

    /*
     * The prop5 property.
     */
    @Generated
    private String prop5;

    /*
     * The prop6 property.
     */
    @Generated
    private String prop6;

    /**
     * Creates an instance of GroupNoneRequest class.
     * 
     * @param prop1 the prop1 value to set.
     */
    @Generated
    public GroupNoneRequest(String prop1) {
        this.prop1 = prop1;
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
     * Set the prop2 property: The prop2 property.
     * 
     * @param prop2 the prop2 value to set.
     * @return the GroupNoneRequest object itself.
     */
    @Generated
    public GroupNoneRequest setProp2(String prop2) {
        this.prop2 = prop2;
        return this;
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
     * Set the prop3 property: The prop3 property.
     * 
     * @param prop3 the prop3 value to set.
     * @return the GroupNoneRequest object itself.
     */
    @Generated
    public GroupNoneRequest setProp3(String prop3) {
        this.prop3 = prop3;
        return this;
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
     * Set the prop4 property: The prop4 property.
     * 
     * @param prop4 the prop4 value to set.
     * @return the GroupNoneRequest object itself.
     */
    @Generated
    public GroupNoneRequest setProp4(String prop4) {
        this.prop4 = prop4;
        return this;
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
     * Set the prop5 property: The prop5 property.
     * 
     * @param prop5 the prop5 value to set.
     * @return the GroupNoneRequest object itself.
     */
    @Generated
    public GroupNoneRequest setProp5(String prop5) {
        this.prop5 = prop5;
        return this;
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

    /**
     * Set the prop6 property: The prop6 property.
     * 
     * @param prop6 the prop6 value to set.
     * @return the GroupNoneRequest object itself.
     */
    @Generated
    public GroupNoneRequest setProp6(String prop6) {
        this.prop6 = prop6;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("prop1", this.prop1);
        jsonWriter.writeStringField("prop2", this.prop2);
        jsonWriter.writeStringField("prop3", this.prop3);
        jsonWriter.writeStringField("prop4", this.prop4);
        jsonWriter.writeStringField("prop5", this.prop5);
        jsonWriter.writeStringField("prop6", this.prop6);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of GroupNoneRequest from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of GroupNoneRequest if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the GroupNoneRequest.
     */
    @Generated
    public static GroupNoneRequest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String prop1 = null;
            String prop2 = null;
            String prop3 = null;
            String prop4 = null;
            String prop5 = null;
            String prop6 = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("prop1".equals(fieldName)) {
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
            GroupNoneRequest deserializedGroupNoneRequest = new GroupNoneRequest(prop1);
            deserializedGroupNoneRequest.prop2 = prop2;
            deserializedGroupNoneRequest.prop3 = prop3;
            deserializedGroupNoneRequest.prop4 = prop4;
            deserializedGroupNoneRequest.prop5 = prop5;
            deserializedGroupNoneRequest.prop6 = prop6;

            return deserializedGroupNoneRequest;
        });
    }
}
