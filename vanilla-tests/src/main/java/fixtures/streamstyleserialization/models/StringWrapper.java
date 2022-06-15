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

/** The StringWrapper model. */
@Fluent
public final class StringWrapper implements JsonSerializable<StringWrapper> {
    private String field;

    private String empty;

    private String nullProperty;

    /**
     * Get the field property: The field property.
     *
     * @return the field value.
     */
    public String getField() {
        return this.field;
    }

    /**
     * Set the field property: The field property.
     *
     * @param field the field value to set.
     * @return the StringWrapper object itself.
     */
    public StringWrapper setField(String field) {
        this.field = field;
        return this;
    }

    /**
     * Get the empty property: The empty property.
     *
     * @return the empty value.
     */
    public String getEmpty() {
        return this.empty;
    }

    /**
     * Set the empty property: The empty property.
     *
     * @param empty the empty value to set.
     * @return the StringWrapper object itself.
     */
    public StringWrapper setEmpty(String empty) {
        this.empty = empty;
        return this;
    }

    /**
     * Get the nullProperty property: The null property.
     *
     * @return the nullProperty value.
     */
    public String getNullProperty() {
        return this.nullProperty;
    }

    /**
     * Set the nullProperty property: The null property.
     *
     * @param nullProperty the nullProperty value to set.
     * @return the StringWrapper object itself.
     */
    public StringWrapper setNullProperty(String nullProperty) {
        this.nullProperty = nullProperty;
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
        jsonWriter.writeStringField("field", this.field, false);
        jsonWriter.writeStringField("empty", this.empty, false);
        jsonWriter.writeStringField("null", this.nullProperty, false);
        return jsonWriter.writeEndObject().flush();
    }

    public static StringWrapper fromJson(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
                reader -> {
                    String field = null;
                    String empty = null;
                    String nullProperty = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("field".equals(fieldName)) {
                            field = JsonUtils.getNullableProperty(reader, r -> reader.getStringValue());
                        } else if ("empty".equals(fieldName)) {
                            empty = JsonUtils.getNullableProperty(reader, r -> reader.getStringValue());
                        } else if ("null".equals(fieldName)) {
                            nullProperty = JsonUtils.getNullableProperty(reader, r -> reader.getStringValue());
                        } else {
                            reader.skipChildren();
                        }
                    }
                    StringWrapper deserializedValue = new StringWrapper();
                    deserializedValue.setField(field);
                    deserializedValue.setEmpty(empty);
                    deserializedValue.setNullProperty(nullProperty);

                    return deserializedValue;
                });
    }
}
