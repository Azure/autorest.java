// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import com.azure.autorest.extension.base.model.extensionmodel.XmsEnum;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;

import java.io.IOException;

import static com.azure.autorest.extension.base.util.JsonUtils.readObject;

/**
 * Represents the version of the dictionary API.
 */
public class DictionaryApiVersion implements JsonSerializable<DictionaryApiVersion> {

    /**
     * Creates a new instance of the DictionaryApiVersion class.
     */
    public DictionaryApiVersion() {
    }

    @Override
    public String toString() {
        return DictionaryApiVersion.class.getName() + "@" + Integer.toHexString(System.identityHashCode(this)) + "[]";
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof DictionaryApiVersion;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeStartObject().writeEndObject();
    }

    public static XmsEnum fromJson(JsonReader jsonReader) throws IOException {
        return readObject(jsonReader, XmsEnum::new, (xmsEnum, fieldName, reader) -> reader.skipChildren());
    }
}
