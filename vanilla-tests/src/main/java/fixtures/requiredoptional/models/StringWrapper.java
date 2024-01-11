// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.requiredoptional.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The StringWrapper model.
 */
@Fluent
public final class StringWrapper implements JsonSerializable<StringWrapper> {
    /*
     * The value property.
     */
    private String value;

    /**
     * Creates an instance of StringWrapper class.
     */
    public StringWrapper() {
    }

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the StringWrapper object itself.
     */
    public StringWrapper setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getValue() == null) {
            throw new IllegalArgumentException("Missing required property value in model StringWrapper");
        }
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("value", this.value);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of StringWrapper from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of StringWrapper if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the StringWrapper.
     */
    public static StringWrapper fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            StringWrapper deserializedStringWrapper = new StringWrapper();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("value".equals(fieldName)) {
                    deserializedStringWrapper.value = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedStringWrapper;
        });
    }
}
