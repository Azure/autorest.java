// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.enumservice.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * Defines values for ColorModel.
 */
public final class ColorModel extends ExpandableStringEnum<ColorModel> implements JsonSerializable<ColorModel> {
    /**
     * Static value Red for ColorModel.
     */
    @Generated
    public static final ColorModel RED = fromString("Red");

    /**
     * Static value Blue for ColorModel.
     */
    @Generated
    public static final ColorModel BLUE = fromString("Blue");

    /**
     * Static value Green for ColorModel.
     */
    @Generated
    public static final ColorModel GREEN = fromString("Green");

    /**
     * Creates a new instance of ColorModel value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public ColorModel() {
    }

    /**
     * Creates or finds a ColorModel from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding ColorModel.
     */
    @Generated
    public static ColorModel fromString(String name) {
        return fromString(name, ColorModel.class);
    }

    /**
     * Gets known ColorModel values.
     * 
     * @return known ColorModel values.
     */
    @Generated
    public static Collection<ColorModel> values() {
        return values(ColorModel.class);
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeString(toString());
    }

    /**
     * Reads a ColorModel from the JSON stream.
     * &lt;p&gt;.
     * The passed JsonReader must be positioned at a JsonToken.STRING value.
     * 
     * @param jsonReader The JsonReader being read.
     * @return The ColorModel that the JSON stream represented, may return null.
     * @throws java.io.IOException If a ColorModel fails to be read from the JsonReader.
     */
    public static ColorModel fromJson(JsonReader jsonReader) throws IOException {
        return fromString(jsonReader.getString(), ColorModel.class);
    }
}
