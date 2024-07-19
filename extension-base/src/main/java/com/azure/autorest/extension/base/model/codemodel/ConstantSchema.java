// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import com.azure.autorest.extension.base.util.JsonUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonWriter;

import java.io.IOException;

/**
 * Represents a constant schema.
 */
public class ConstantSchema extends Schema {
    private Schema valueType;
    private ConstantValue value;

    /**
     * Creates a new instance of the ConstantSchema class.
     */
    public ConstantSchema() {
        super();
    }

    /**
     * Gets the value type. (Required)
     *
     * @return The value type.
     */
    public Schema getValueType() {
        return valueType;
    }

    /**
     * Sets the value type. (Required)
     *
     * @param valueType The value type.
     */
    public void setValueType(Schema valueType) {
        this.valueType = valueType;
    }

    /**
     * Gets the actual constant value. (Required)
     *
     * @return The actual constant value.
     */
    public ConstantValue getValue() {
        return value;
    }

    /**
     * Sets the actual constant value. (Required)
     *
     * @param value The actual constant value.
     */
    public void setValue(ConstantValue value) {
        this.value = value;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeStartObject()
            .writeJsonField("valueType", valueType)
            .writeJsonField("value", value)
            .writeEndObject();
    }

    public static ConstantSchema fromJson(JsonReader jsonReader) throws IOException {
        return JsonUtils.readObject(jsonReader, ConstantSchema::new, (schema, fieldName, reader) -> {
            if ("valueType".equals(fieldName)) {
                schema.valueType = Schema.fromJson(reader);
            } else if ("value".equals(fieldName)) {
                schema.value = ConstantValue.fromJson(reader);
            } else {
                reader.skipChildren();
            }
        });
    }
}
