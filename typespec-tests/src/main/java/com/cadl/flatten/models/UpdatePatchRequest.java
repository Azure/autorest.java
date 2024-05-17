// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.flatten.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.cadl.flatten.implementation.JsonMergePatchHelper;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * The UpdatePatchRequest model.
 */
@Fluent
public final class UpdatePatchRequest implements JsonSerializable<UpdatePatchRequest> {
    /*
     * The patch property.
     */
    @Generated
    private TodoItemPatch patch;

    @Generated
    private boolean jsonMergePatch;

    @Generated
    boolean isJsonMergePatch() {
        return this.jsonMergePatch;
    }

    /**
     * Stores updated model property, the value is property name, not serialized name.
     */
    @Generated
    private final Set<String> updatedProperties = new HashSet<>();

    @Generated
    private void serializeAsJsonMergePatch(boolean jsonMergePatch) {
        this.jsonMergePatch = jsonMergePatch;
    }

    static {
        JsonMergePatchHelper.setUpdatePatchRequestAccessor((model, jsonMergePatchEnabled) -> {
            model.serializeAsJsonMergePatch(jsonMergePatchEnabled);
            return model;
        });
    }

    /**
     * Creates an instance of UpdatePatchRequest class.
     */
    @Generated
    public UpdatePatchRequest() {
    }

    /**
     * Get the patch property: The patch property.
     * 
     * @return the patch value.
     */
    @Generated
    public TodoItemPatch getPatch() {
        return this.patch;
    }

    /**
     * Set the patch property: The patch property.
     * <p>Required when create the resource.</p>
     * 
     * @param patch the patch value to set.
     * @return the UpdatePatchRequest object itself.
     */
    @Generated
    public UpdatePatchRequest setPatch(TodoItemPatch patch) {
        this.patch = patch;
        this.updatedProperties.add("patch");
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        if (isJsonMergePatch()) {
            return toJsonMergePatch(jsonWriter);
        } else {
            jsonWriter.writeStartObject();
            jsonWriter.writeJsonField("patch", this.patch);
            return jsonWriter.writeEndObject();
        }
    }

    @Generated
    private JsonWriter toJsonMergePatch(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        if (updatedProperties.contains("patch")) {
            if (this.patch == null) {
                jsonWriter.writeNullField("patch");
            } else {
                JsonMergePatchHelper.getTodoItemPatchAccessor().prepareModelForJsonMergePatch(this.patch, true);
                jsonWriter.writeJsonField("patch", this.patch);
                JsonMergePatchHelper.getTodoItemPatchAccessor().prepareModelForJsonMergePatch(this.patch, false);
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of UpdatePatchRequest from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of UpdatePatchRequest if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the UpdatePatchRequest.
     */
    @Generated
    public static UpdatePatchRequest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            UpdatePatchRequest deserializedUpdatePatchRequest = new UpdatePatchRequest();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("patch".equals(fieldName)) {
                    deserializedUpdatePatchRequest.patch = TodoItemPatch.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedUpdatePatchRequest;
        });
    }
}
