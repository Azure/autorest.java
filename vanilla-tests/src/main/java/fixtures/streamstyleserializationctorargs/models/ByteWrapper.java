// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationctorargs.models;

import com.azure.core.annotation.Fluent;

import com.azure.core.util.CoreUtils;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

import java.io.IOException;

/**
 * The ByteWrapper model.
 */
@Fluent
public final class ByteWrapper implements JsonSerializable<ByteWrapper> {
    /*
     * The field property.
     */
    private byte[] field;

    /**
     * Creates an instance of ByteWrapper class.
     */
    public ByteWrapper() {
    }

    /**
     * Get the field property: The field property.
     * 
     * @return the field value.
     */
    public byte[] getField() {
        return CoreUtils.clone(this.field);
    }

    /**
     * Set the field property: The field property.
     * 
     * @param field the field value to set.
     * @return the ByteWrapper object itself.
     */
    public ByteWrapper setField(byte[] field) {
        this.field = CoreUtils.clone(field);
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBinaryField("field", this.field);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ByteWrapper from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ByteWrapper if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the ByteWrapper.
     */
    public static ByteWrapper fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ByteWrapper deserializedByteWrapper = new ByteWrapper();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("field".equals(fieldName)) {
                    deserializedByteWrapper.field = reader.getBinary();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedByteWrapper;
        });
    }
}
