// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.valuetypes.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * Defines values for UnionIntLiteralPropertyProperty.
 */
public final class UnionIntLiteralPropertyProperty extends ExpandableStringEnum<UnionIntLiteralPropertyProperty>
    implements JsonSerializable<UnionIntLiteralPropertyProperty> {
    /**
     * Static value 42 for UnionIntLiteralPropertyProperty.
     */
    @Generated
    public static final UnionIntLiteralPropertyProperty FORTY_TWO = fromLong(42L);

    /**
     * Static value 43 for UnionIntLiteralPropertyProperty.
     */
    @Generated
    public static final UnionIntLiteralPropertyProperty FORTY_THREE = fromLong(43L);

    /**
     * Creates a new instance of UnionIntLiteralPropertyProperty value.
     * 
     * @deprecated Use the {@link #fromLong(long)} factory method.
     */
    @Generated
    @Deprecated
    public UnionIntLiteralPropertyProperty() {
    }

    /**
     * Creates or finds a UnionIntLiteralPropertyProperty from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding UnionIntLiteralPropertyProperty.
     */
    @Generated
    public static UnionIntLiteralPropertyProperty fromLong(long name) {
        return fromString(String.valueOf(name), UnionIntLiteralPropertyProperty.class);
    }

    /**
     * Gets known UnionIntLiteralPropertyProperty values.
     * 
     * @return known UnionIntLiteralPropertyProperty values.
     */
    @Generated
    public static Collection<UnionIntLiteralPropertyProperty> values() {
        return values(UnionIntLiteralPropertyProperty.class);
    }

    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeString(toString());
    }

    /**
     * Reads a UnionIntLiteralPropertyProperty from the JSON stream.
     * <p>
     * The passed JsonReader must be positioned at a JsonToken.STRING value.
     * 
     * @param jsonReader The JsonReader being read.
     * @return The UnionIntLiteralPropertyProperty that the JSON stream represented, may return null.
     * @throws java.io.IOException If a UnionIntLiteralPropertyProperty fails to be read from the JsonReader.
     */
    @Generated
    public static UnionIntLiteralPropertyProperty fromJson(JsonReader jsonReader) throws IOException {
        return fromString(jsonReader.getString(), UnionIntLiteralPropertyProperty.class);
    }
}
