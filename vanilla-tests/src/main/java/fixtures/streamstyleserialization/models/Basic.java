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

/** The Basic model. */
@Fluent
public final class Basic implements JsonSerializable<Basic> {
    private Integer id;

    private String name;

    private CMYKColors color;

    /**
     * Get the id property: Basic Id.
     *
     * @return the id value.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Set the id property: Basic Id.
     *
     * @param id the id value to set.
     * @return the Basic object itself.
     */
    public Basic setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Get the name property: Name property with a very long description that does not fit on a single line and a line
     * break.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: Name property with a very long description that does not fit on a single line and a line
     * break.
     *
     * @param name the name value to set.
     * @return the Basic object itself.
     */
    public Basic setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the color property: The color property.
     *
     * @return the color value.
     */
    public CMYKColors getColor() {
        return this.color;
    }

    /**
     * Set the color property: The color property.
     *
     * @param color the color value to set.
     * @return the Basic object itself.
     */
    public Basic setColor(CMYKColors color) {
        this.color = color;
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

    public static Basic fromJson(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
                reader -> {
                    Basic deserializedValue = new Basic();
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("id".equals(fieldName)) {
                            if (reader.currentToken() != JsonToken.NULL) {
                                deserializedValue.setId(reader.getIntValue());
                            }
                        } else if ("name".equals(fieldName)) {
                            deserializedValue.setName(reader.getStringValue());
                        } else if ("color".equals(fieldName)) {
                            if (reader.currentToken() != JsonToken.NULL) {
                                deserializedValue.setColor(CMYKColors.fromString(reader.getStringValue()));
                            }
                        } else {
                            reader.skipChildren();
                        }
                    }
                    return deserializedValue;
                });
    }
}
