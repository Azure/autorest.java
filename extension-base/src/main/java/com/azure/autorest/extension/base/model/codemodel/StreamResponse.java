// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import com.azure.autorest.extension.base.util.JsonUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonWriter;

import java.io.IOException;

/**
 * Represents a stream response.
 */
public class StreamResponse extends Response {
    /**
     * Creates a new instance of the StreamResponse class.
     */
    public StreamResponse() {
        super();
    }

    /**
     * Whether the response is a stream.
     *
     * @return Whether the response is a stream.
     */
    public boolean isStream() {
        return true;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return super.toJson(jsonWriter);
    }

    public static StreamResponse fromJson(JsonReader jsonReader) throws IOException {
        return JsonUtils.readObject(jsonReader, StreamResponse::new, (response, fieldName, reader) -> {
            if (!response.tryConsumeParentProperties(response, fieldName, reader)) {
                reader.skipChildren();
            }
        });
    }
}
