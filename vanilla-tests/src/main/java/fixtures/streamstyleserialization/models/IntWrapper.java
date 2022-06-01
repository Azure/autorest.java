// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.serializer.JsonUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

/** The IntWrapper model. */
@Fluent
public final class IntWrapper implements JsonSerializable<IntWrapper> {
    private Integer field1;

    private Integer field2;

    /**
     * Get the field1 property: The field1 property.
     *
     * @return the field1 value.
     */
    public Integer getField1() {
        return this.field1;
    }

    /**
     * Set the field1 property: The field1 property.
     *
     * @param field1 the field1 value to set.
     * @return the IntWrapper object itself.
     */
    public IntWrapper setField1(Integer field1) {
        this.field1 = field1;
        return this;
    }

    /**
     * Get the field2 property: The field2 property.
     *
     * @return the field2 value.
     */
    public Integer getField2() {
        return this.field2;
    }

    /**
     * Set the field2 property: The field2 property.
     *
     * @param field2 the field2 value to set.
     * @return the IntWrapper object itself.
     */
    public IntWrapper setField2(Integer field2) {
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
        return jsonWriter.flush();
    }

    public static IntWrapper fromJson(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
                reader -> {
                    Integer field1 = null;
                    Integer field2 = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("field1".equals(fieldName)) {
                            if (reader.currentToken() != JsonToken.NULL) {
                                field1 = reader.getIntValue();
                            }
                        } else if ("field2".equals(fieldName)) {
                            if (reader.currentToken() != JsonToken.NULL) {
                                field2 = reader.getIntValue();
                            }
                        } else {
                            reader.skipChildren();
                        }
                    }
                    IntWrapper deserializedValue = new IntWrapper();
                    deserializedValue.setField1(field1);
                    deserializedValue.setField2(field2);

                    return deserializedValue;
                });
    }
}
