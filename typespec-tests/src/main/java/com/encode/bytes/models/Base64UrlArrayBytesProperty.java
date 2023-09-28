// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.encode.bytes.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.Base64Url;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** The Base64UrlArrayBytesProperty model. */
@Immutable
public final class Base64UrlArrayBytesProperty implements JsonSerializable<Base64UrlArrayBytesProperty> {
    /*
     * The value property.
     */
    @Generated private final List<Base64Url> value;

    /**
     * Creates an instance of Base64UrlArrayBytesProperty class.
     *
     * @param value the value value to set.
     */
    @Generated
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    public Base64UrlArrayBytesProperty(List<byte[]> value) {
        this.value = value.stream().map(el -> Base64Url.encode(el)).collect(java.util.stream.Collectors.toList());
    }

    @Generated
    private Base64UrlArrayBytesProperty() {}

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    @Generated
    public List<byte[]> getValue() {
        if (this.value == null) {
            return null;
        }
        return this.value.stream().map(el -> el.decodedBytes()).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeArrayField(
                "value", this.value, (writer, element) -> writer.writeString(Objects.toString(element, null)));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Base64UrlArrayBytesProperty from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of Base64UrlArrayBytesProperty if the JsonReader was pointing to an instance of it, or null
     *     if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Base64UrlArrayBytesProperty.
     */
    public static Base64UrlArrayBytesProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    boolean valueFound = false;
                    List<byte[]> value = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("value".equals(fieldName)) {
                            value =
                                    reader.readArray(
                                            reader1 ->
                                                    reader1.getNullable(
                                                            nonNullReader -> new Base64Url(nonNullReader.getString())));
                            valueFound = true;
                        } else {
                            reader.skipChildren();
                        }
                    }
                    if (valueFound) {
                        Base64UrlArrayBytesProperty deserializedBase64UrlArrayBytesProperty =
                                new Base64UrlArrayBytesProperty(value);

                        return deserializedBase64UrlArrayBytesProperty;
                    }
                    List<String> missingProperties = new ArrayList<>();
                    if (!valueFound) {
                        missingProperties.add("value");
                    }

                    throw new IllegalStateException(
                            "Missing required property/properties: " + String.join(", ", missingProperties));
                });
    }
}
