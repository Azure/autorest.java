// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.optional.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/** Model with collection bytes properties. */
@Fluent
public final class CollectionsByteProperty implements JsonSerializable<CollectionsByteProperty> {
    /*
     * Property
     */
    @Generated private List<byte[]> property;

    /** Creates an instance of CollectionsByteProperty class. */
    @Generated
    public CollectionsByteProperty() {}

    /**
     * Get the property property: Property.
     *
     * @return the property value.
     */
    @Generated
    public List<byte[]> getProperty() {
        return this.property;
    }

    /**
     * Set the property property: Property.
     *
     * @param property the property value to set.
     * @return the CollectionsByteProperty object itself.
     */
    @Generated
    public CollectionsByteProperty setProperty(List<byte[]> property) {
        this.property = property;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeArrayField("property", this.property, (writer, element) -> writer.writeBinary(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of CollectionsByteProperty from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of CollectionsByteProperty if the JsonReader was pointing to an instance of it, or null if it
     *     was pointing to JSON null.
     * @throws IOException If an error occurs while reading the CollectionsByteProperty.
     */
    public static CollectionsByteProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    CollectionsByteProperty deserializedCollectionsByteProperty = new CollectionsByteProperty();
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("property".equals(fieldName)) {
                            List<byte[]> property = reader.readArray(reader1 -> reader1.getBinary());
                            deserializedCollectionsByteProperty.property = property;
                        } else {
                            reader.skipChildren();
                        }
                    }

                    return deserializedCollectionsByteProperty;
                });
    }
}
