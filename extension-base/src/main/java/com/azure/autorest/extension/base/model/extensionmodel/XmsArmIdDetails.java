// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.extensionmodel;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;

import java.io.IOException;
import java.util.List;

import static com.azure.autorest.extension.base.util.JsonUtils.readObject;

/**
 * Represents the details of an ARM ID.
 */
public class XmsArmIdDetails implements JsonSerializable<XmsArmIdDetails> {
    private List<AllowedResource> allowedResources;

    /**
     * Creates a new instance of the XmsArmIdDetails class.
     */
    public XmsArmIdDetails() {
    }

    /**
     * Gets the resources that are allowed to be accessed.
     *
     * @return The resources that are allowed to be accessed.
     */
    public List<AllowedResource> getAllowedResources() {
        return allowedResources;
    }

    /**
     * Sets the resources that are allowed to be accessed.
     *
     * @param allowedResources The resources that are allowed to be accessed.
     */
    public void setAllowedResources(List<AllowedResource> allowedResources) {
        this.allowedResources = allowedResources;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeStartObject()
            .writeArrayField("allowedResources", allowedResources, JsonWriter::writeJson)
            .writeEndObject();
    }

    public static XmsArmIdDetails fromJson(JsonReader jsonReader) throws IOException {
        return readObject(jsonReader, XmsArmIdDetails::new, (xmsArmIdDetails, fieldName, reader) -> {
            if ("allowedResources".equals(fieldName)) {
                xmsArmIdDetails.allowedResources = reader.readArray(AllowedResource::fromJson);
            } else {
                reader.skipChildren();
            }
        });
    }
}
