// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.extensionmodel;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;

import java.io.IOException;

import static com.azure.autorest.extension.base.util.JsonUtils.readObject;

/**
 * Represents an anonymous schema.
 */
public class XmsInternalAutorestAnonymousSchema implements JsonSerializable<XmsInternalAutorestAnonymousSchema> {
    private boolean anonymous = true;

    /**
     * Creates a new instance of the XmsInternalAutorestAnonymousSchema class.
     */
    public XmsInternalAutorestAnonymousSchema() {
    }

    /**
     * Gets whether the schema is anonymous.
     *
     * @return Whether the schema is anonymous.
     */
    public boolean isAnonymous() {
        return anonymous;
    }

    /**
     * Sets whether the schema is anonymous.
     *
     * @param anonymous Whether the schema is anonymous.
     */
    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeStartObject()
            .writeBooleanField("anonymous", anonymous)
            .writeEndObject();
    }

    public static XmsInternalAutorestAnonymousSchema fromJson(JsonReader jsonReader) throws IOException {
        return readObject(jsonReader, XmsInternalAutorestAnonymousSchema::new, (anonymousSchema, fieldName, reader) -> {
            if ("anonymous".equals(fieldName)) {
                anonymousSchema.anonymous = reader.getBoolean();
            } else {
                reader.skipChildren();
            }
        });
    }
}
