// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.payload.jsonmergepatch.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.payload.jsonmergepatch.implementation.JsonMergePatchHelper;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Details about a resource for patch operation.
 */
@Fluent
public final class ResourcePatch implements JsonSerializable<ResourcePatch> {
    /*
     * The description property.
     */
    @Generated
    private String description;

    /*
     * The map property.
     */
    @Generated
    private Map<String, InnerModel> map;

    /*
     * The array property.
     */
    @Generated
    private List<InnerModel> array;

    /*
     * The intValue property.
     */
    @Generated
    private Integer intValue;

    /*
     * The floatValue property.
     */
    @Generated
    private Double floatValue;

    /*
     * The innerModel property.
     */
    @Generated
    private InnerModel innerModel;

    /*
     * The intArray property.
     */
    @Generated
    private List<Integer> intArray;

    @Generated
    private boolean jsonMergePatch;

    /**
     * Stores updated model property, the value is property name, not serialized name.
     */
    @Generated
    private final Set<String> updatedProperties = new HashSet<>();

    @Generated
    void serializeAsJsonMergePatch(boolean jsonMergePatch) {
        this.jsonMergePatch = jsonMergePatch;
    }

    static {
        JsonMergePatchHelper.setResourcePatchAccessor((model, jsonMergePatchEnabled) -> {
            model.serializeAsJsonMergePatch(jsonMergePatchEnabled);
            return model;
        });
    }

    /**
     * Creates an instance of ResourcePatch class.
     */
    @Generated
    public ResourcePatch() {
    }

    /**
     * Get the description property: The description property.
     * 
     * @return the description value.
     */
    @Generated
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: The description property.
     * 
     * @param description the description value to set.
     * @return the ResourcePatch object itself.
     */
    @Generated
    public ResourcePatch setDescription(String description) {
        this.description = description;
        this.updatedProperties.add("description");
        return this;
    }

    /**
     * Get the map property: The map property.
     * 
     * @return the map value.
     */
    @Generated
    public Map<String, InnerModel> getMap() {
        return this.map;
    }

    /**
     * Set the map property: The map property.
     * 
     * @param map the map value to set.
     * @return the ResourcePatch object itself.
     */
    @Generated
    public ResourcePatch setMap(Map<String, InnerModel> map) {
        this.map = map;
        this.updatedProperties.add("map");
        return this;
    }

    /**
     * Get the array property: The array property.
     * 
     * @return the array value.
     */
    @Generated
    public List<InnerModel> getArray() {
        return this.array;
    }

    /**
     * Set the array property: The array property.
     * 
     * @param array the array value to set.
     * @return the ResourcePatch object itself.
     */
    @Generated
    public ResourcePatch setArray(List<InnerModel> array) {
        this.array = array;
        this.updatedProperties.add("array");
        return this;
    }

    /**
     * Get the intValue property: The intValue property.
     * 
     * @return the intValue value.
     */
    @Generated
    public Integer getIntValue() {
        return this.intValue;
    }

    /**
     * Set the intValue property: The intValue property.
     * 
     * @param intValue the intValue value to set.
     * @return the ResourcePatch object itself.
     */
    @Generated
    public ResourcePatch setIntValue(Integer intValue) {
        this.intValue = intValue;
        this.updatedProperties.add("intValue");
        return this;
    }

    /**
     * Get the floatValue property: The floatValue property.
     * 
     * @return the floatValue value.
     */
    @Generated
    public Double getFloatValue() {
        return this.floatValue;
    }

    /**
     * Set the floatValue property: The floatValue property.
     * 
     * @param floatValue the floatValue value to set.
     * @return the ResourcePatch object itself.
     */
    @Generated
    public ResourcePatch setFloatValue(Double floatValue) {
        this.floatValue = floatValue;
        this.updatedProperties.add("floatValue");
        return this;
    }

    /**
     * Get the innerModel property: The innerModel property.
     * 
     * @return the innerModel value.
     */
    @Generated
    public InnerModel getInnerModel() {
        return this.innerModel;
    }

    /**
     * Set the innerModel property: The innerModel property.
     * 
     * @param innerModel the innerModel value to set.
     * @return the ResourcePatch object itself.
     */
    @Generated
    public ResourcePatch setInnerModel(InnerModel innerModel) {
        this.innerModel = innerModel;
        this.updatedProperties.add("innerModel");
        return this;
    }

    /**
     * Get the intArray property: The intArray property.
     * 
     * @return the intArray value.
     */
    @Generated
    public List<Integer> getIntArray() {
        return this.intArray;
    }

    /**
     * Set the intArray property: The intArray property.
     * 
     * @param intArray the intArray value to set.
     * @return the ResourcePatch object itself.
     */
    @Generated
    public ResourcePatch setIntArray(List<Integer> intArray) {
        this.intArray = intArray;
        this.updatedProperties.add("intArray");
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
            jsonWriter.writeStringField("description", this.description);
            jsonWriter.writeMapField("map", this.map, (writer, element) -> writer.writeJson(element));
            jsonWriter.writeArrayField("array", this.array, (writer, element) -> writer.writeJson(element));
            jsonWriter.writeNumberField("intValue", this.intValue);
            jsonWriter.writeNumberField("floatValue", this.floatValue);
            jsonWriter.writeJsonField("innerModel", this.innerModel);
            jsonWriter.writeArrayField("intArray", this.intArray, (writer, element) -> writer.writeInt(element));
            return jsonWriter.writeEndObject();
        }
    }

    @Generated
    public JsonWriter toJsonMergePatch(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        if (this.description != null) {
            jsonWriter.writeStringField("description", this.description);
        } else if (updatedProperties.contains("description")) {
            jsonWriter.writeNullField("description");
        }
        if (this.map != null) {
            jsonWriter.writeMapField("map", this.map, (writer, element) -> {
                if (element != null) {
                    element.serializeAsJsonMergePatch(true);
                    writer.writeJson(element);
                    element.serializeAsJsonMergePatch(false);
                } else {
                    writer.writeNull();
                }
            });
        } else if (updatedProperties.contains("map")) {
            jsonWriter.writeNullField("map");
        }
        if (this.array != null) {
            jsonWriter.writeArrayField("array", this.array, (writer, element) -> writer.writeJson(element));
        } else if (updatedProperties.contains("array")) {
            jsonWriter.writeNullField("array");
        }
        if (this.intValue != null) {
            jsonWriter.writeNumberField("intValue", this.intValue);
        } else if (updatedProperties.contains("intValue")) {
            jsonWriter.writeNullField("intValue");
        }
        if (this.floatValue != null) {
            jsonWriter.writeNumberField("floatValue", this.floatValue);
        } else if (updatedProperties.contains("floatValue")) {
            jsonWriter.writeNullField("floatValue");
        }
        if (this.innerModel != null) {
            this.innerModel.serializeAsJsonMergePatch(true);
            jsonWriter.writeJsonField("innerModel", this.innerModel);
            this.innerModel.serializeAsJsonMergePatch(false);
        } else if (updatedProperties.contains("innerModel")) {
            jsonWriter.writeNullField("innerModel");
        }
        if (this.intArray != null) {
            jsonWriter.writeArrayField("intArray", this.intArray, (writer, element) -> writer.writeInt(element));
        } else if (updatedProperties.contains("intArray")) {
            jsonWriter.writeNullField("intArray");
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ResourcePatch from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ResourcePatch if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the ResourcePatch.
     */
    @Generated
    public static ResourcePatch fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ResourcePatch deserializedResourcePatch = new ResourcePatch();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("description".equals(fieldName)) {
                    deserializedResourcePatch.description = reader.getString();
                } else if ("map".equals(fieldName)) {
                    Map<String, InnerModel> map = reader.readMap(reader1 -> InnerModel.fromJson(reader1));
                    deserializedResourcePatch.map = map;
                } else if ("array".equals(fieldName)) {
                    List<InnerModel> array = reader.readArray(reader1 -> InnerModel.fromJson(reader1));
                    deserializedResourcePatch.array = array;
                } else if ("intValue".equals(fieldName)) {
                    deserializedResourcePatch.intValue = reader.getNullable(JsonReader::getInt);
                } else if ("floatValue".equals(fieldName)) {
                    deserializedResourcePatch.floatValue = reader.getNullable(JsonReader::getDouble);
                } else if ("innerModel".equals(fieldName)) {
                    deserializedResourcePatch.innerModel = InnerModel.fromJson(reader);
                } else if ("intArray".equals(fieldName)) {
                    List<Integer> intArray = reader.readArray(reader1 -> reader1.getInt());
                    deserializedResourcePatch.intArray = intArray;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedResourcePatch;
        });
    }
}
