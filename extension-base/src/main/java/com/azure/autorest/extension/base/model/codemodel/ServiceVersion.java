// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import com.azure.autorest.extension.base.util.JsonUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonWriter;

import java.io.IOException;

/**
 * Represents a service version.
 */
public class ServiceVersion extends Metadata {
    /**
     * Creates a new instance of the ServiceVersion class.
     */
    public ServiceVersion() {
        super();
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return super.toJson(jsonWriter);
    }

    public static ServiceVersion fromJson(JsonReader jsonReader) throws IOException {
        return JsonUtils.readObject(jsonReader, ServiceVersion::new, (serviceVersion, fieldName, reader) -> {
            if (!serviceVersion.tryConsumeParentProperties(serviceVersion, fieldName, reader)) {
                reader.skipChildren();
            }
        });
    }
}
