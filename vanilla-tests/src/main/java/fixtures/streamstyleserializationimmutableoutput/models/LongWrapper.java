// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationimmutableoutput.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The LongWrapper model.
 */
@Fluent
public final class LongWrapper implements JsonSerializable<LongWrapper> {
    /*
     * The field1 property.
     */
    private Long field1;

    /*
     * The field2 property.
     */
    private Long field2;

    /**
     * Creates an instance of LongWrapper class.
     */
    public LongWrapper() {
    }

    /**
     * Get the field1 property: The field1 property.
     * 
     * @return the field1 value.
     */
    public Long getField1() {
        return this.field1;
    }

    /**
     * Set the field1 property: The field1 property.
     * 
     * @param field1 the field1 value to set.
     * @return the LongWrapper object itself.
     */
    public LongWrapper setField1(Long field1) {
        this.field1 = field1;
        return this;
    }

    /**
     * Get the field2 property: The field2 property.
     * 
     * @return the field2 value.
     */
    public Long getField2() {
        return this.field2;
    }

    /**
     * Set the field2 property: The field2 property.
     * 
     * @param field2 the field2 value to set.
     * @return the LongWrapper object itself.
     */
    public LongWrapper setField2(Long field2) {
        this.field2 = field2;
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
        jsonWriter.writeNumberField("field1", this.field1);
        jsonWriter.writeNumberField("field2", this.field2);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of LongWrapper from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of LongWrapper if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the LongWrapper.
     */
    public static LongWrapper fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            LongWrapper deserializedLongWrapper = new LongWrapper();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("field1".equals(fieldName)) {
                    deserializedLongWrapper.field1 = reader.getNullable(JsonReader::getLong);
                } else if ("field2".equals(fieldName)) {
                    deserializedLongWrapper.field2 = reader.getNullable(JsonReader::getLong);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedLongWrapper;
        });
    }
}
