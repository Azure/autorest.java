// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.core.basic.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * An extensible enum input parameter.
 */
public final class ListItemInputExtensibleEnum extends ExpandableStringEnum<ListItemInputExtensibleEnum>
    implements JsonSerializable<ListItemInputExtensibleEnum> {
    /**
     * The first enum value.
     */
    @Generated
    public static final ListItemInputExtensibleEnum FIRST = fromString("First");

    /**
     * The second enum value.
     */
    @Generated
    public static final ListItemInputExtensibleEnum SECOND = fromString("Second");

    /**
     * Creates a new instance of ListItemInputExtensibleEnum value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public ListItemInputExtensibleEnum() {
    }

    /**
     * Creates or finds a ListItemInputExtensibleEnum from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding ListItemInputExtensibleEnum.
     */
    @Generated
    public static ListItemInputExtensibleEnum fromString(String name) {
        return fromString(name, ListItemInputExtensibleEnum.class);
    }

    /**
     * Gets known ListItemInputExtensibleEnum values.
     * 
     * @return known ListItemInputExtensibleEnum values.
     */
    @Generated
    public static Collection<ListItemInputExtensibleEnum> values() {
        return values(ListItemInputExtensibleEnum.class);
    }

    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeString(toString());
    }

    /**
     * Reads a ListItemInputExtensibleEnum from the JSON stream.
     * <p>
     * The passed JsonReader must be positioned at a JsonToken.STRING value.
     * 
     * @param jsonReader The JsonReader being read.
     * @return The ListItemInputExtensibleEnum that the JSON stream represented, may return null.
     * @throws java.io.IOException If a ListItemInputExtensibleEnum fails to be read from the JsonReader.
     */
    @Generated
    public static ListItemInputExtensibleEnum fromJson(JsonReader jsonReader) throws IOException {
        return fromString(jsonReader.getString(), ListItemInputExtensibleEnum.class);
    }
}
