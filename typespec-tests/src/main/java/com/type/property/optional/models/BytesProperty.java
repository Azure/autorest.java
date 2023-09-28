// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.optional.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/** Template type for testing models with optional property. Pass in the type of the property you are looking for. */
@Fluent
public final class BytesProperty implements JsonSerializable<BytesProperty> {
    /*
     * Property
     */
    @Generated private byte[] property;

    /** Creates an instance of BytesProperty class. */
    @Generated
    public BytesProperty() {}

    /**
     * Get the property property: Property.
     *
     * @return the property value.
     */
    @Generated
    public byte[] getProperty() {
        return CoreUtils.clone(this.property);
    }

    /**
     * Set the property property: Property.
     *
     * @param property the property value to set.
     * @return the BytesProperty object itself.
     */
    @Generated
    public BytesProperty setProperty(byte[] property) {
        this.property = CoreUtils.clone(property);
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBinaryField("property", this.property);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BytesProperty from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BytesProperty if the JsonReader was pointing to an instance of it, or null if it was
     *     pointing to JSON null.
     * @throws IOException If an error occurs while reading the BytesProperty.
     */
    public static BytesProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    BytesProperty deserializedBytesProperty = new BytesProperty();
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("property".equals(fieldName)) {
                            deserializedBytesProperty.property = reader.getBinary();
                        } else {
                            reader.skipChildren();
                        }
                    }

                    return deserializedBytesProperty;
                });
    }
}
