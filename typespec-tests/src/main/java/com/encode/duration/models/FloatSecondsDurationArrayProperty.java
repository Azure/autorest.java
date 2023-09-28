// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.encode.duration.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/** The FloatSecondsDurationArrayProperty model. */
@Immutable
public final class FloatSecondsDurationArrayProperty implements JsonSerializable<FloatSecondsDurationArrayProperty> {
    /*
     * The value property.
     */
    @Generated private final List<Double> value;

    /**
     * Creates an instance of FloatSecondsDurationArrayProperty class.
     *
     * @param value the value value to set.
     */
    @Generated
    public FloatSecondsDurationArrayProperty(List<Duration> value) {
        this.value =
                value.stream()
                        .map(el -> (double) el.toNanos() / 1000_000_000L)
                        .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    @Generated
    public List<Duration> getValue() {
        if (this.value == null) {
            return null;
        }
        return this.value.stream()
                .map(el -> Duration.ofNanos((long) (el * 1000_000_000L)))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeArrayField("value", this.value, (writer, element) -> writer.writeDouble(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of FloatSecondsDurationArrayProperty from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of FloatSecondsDurationArrayProperty if the JsonReader was pointing to an instance of it, or
     *     null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the FloatSecondsDurationArrayProperty.
     */
    public static FloatSecondsDurationArrayProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    boolean valueFound = false;
                    List<Duration> value = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("value".equals(fieldName)) {
                            value = reader.readArray(reader1 -> reader1.getDouble());
                            valueFound = true;
                        } else {
                            reader.skipChildren();
                        }
                    }
                    if (valueFound) {
                        FloatSecondsDurationArrayProperty deserializedFloatSecondsDurationArrayProperty =
                                new FloatSecondsDurationArrayProperty(value);

                        return deserializedFloatSecondsDurationArrayProperty;
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
