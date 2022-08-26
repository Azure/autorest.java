// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

/** The FloatWrapper model. */
@Fluent
public final class FloatWrapper implements JsonSerializable<FloatWrapper> {
    /*
     * The field1 property.
     */
    private Float field1;

    /*
     * The field2 property.
     */
    private Float field2;

    /**
     * Get the field1 property: The field1 property.
     *
     * @return the field1 value.
     */
    public Float getField1() {
        return this.field1;
    }

    /**
     * Set the field1 property: The field1 property.
     *
     * @param field1 the field1 value to set.
     * @return the FloatWrapper object itself.
     */
    public FloatWrapper setField1(Float field1) {
        this.field1 = field1;
        return this;
    }

    /**
     * Get the field2 property: The field2 property.
     *
     * @return the field2 value.
     */
    public Float getField2() {
        return this.field2;
    }

    /**
     * Set the field2 property: The field2 property.
     *
     * @param field2 the field2 value to set.
     * @return the FloatWrapper object itself.
     */
    public FloatWrapper setField2(Float field2) {
        this.field2 = field2;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField("field1", this.field1);
        jsonWriter.writeNumberField("field2", this.field2);
        return jsonWriter.writeEndObject().flush();
    }

    /**
     * Reads an instance of FloatWrapper from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of FloatWrapper if the JsonReader was pointing to an instance of it, or null if it was
     *     pointing to JSON null.
     */
    public static FloatWrapper fromJson(JsonReader jsonReader) {
        return jsonReader.readObject(
                reader -> {
                    Float field1 = null;
                    Float field2 = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("field1".equals(fieldName)) {
                            field1 = reader.getNullable(JsonReader::getFloat);
                        } else if ("field2".equals(fieldName)) {
                            field2 = reader.getNullable(JsonReader::getFloat);
                        } else {
                            reader.skipChildren();
                        }
                    }
                    FloatWrapper deserializedValue = new FloatWrapper();
                    deserializedValue.field1 = field1;
                    deserializedValue.field2 = field2;

                    return deserializedValue;
                });
    }
}
