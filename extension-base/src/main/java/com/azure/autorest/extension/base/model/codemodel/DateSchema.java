// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import com.azure.autorest.extension.base.util.JsonUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonWriter;

import java.io.IOException;

/**
 * Represents a date value.
 */
public class DateSchema extends PrimitiveSchema {

    /**
     * Creates a new instance of the DateSchema class.
     */
    public DateSchema() {
        super();
    }

    @Override
    public String toString() {
        return DateSchema.class.getName() + "@" + Integer.toHexString(System.identityHashCode(this)) + "[]";
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

        return other instanceof DateSchema;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return super.toJson(jsonWriter);
    }

    public static DateSchema fromJson(JsonReader jsonReader) throws IOException {
        return JsonUtils.readObject(jsonReader, DateSchema::new, (schema, fieldName, reader) -> {
            if (!schema.tryConsumeParentProperties(schema, fieldName, reader)) {
                reader.skipChildren();
            }
        });
    }
}
