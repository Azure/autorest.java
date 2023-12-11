// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.data.schemaregistry.implementation.models;

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Defines values for SchemaFormat.
 */
public final class SchemaFormat extends ExpandableStringEnum<SchemaFormat> {
    /**
     * Static value application/json; serialization=Avro for SchemaFormat.
     */
    public static final SchemaFormat APPLICATION_JSON_SERIALIZATION_AVRO
        = fromString("application/json; serialization=Avro");

    /**
     * Static value application/json; serialization=Json for SchemaFormat.
     */
    public static final SchemaFormat APPLICATION_JSON_SERIALIZATION_JSON
        = fromString("application/json; serialization=Json");

    /**
     * Static value text/plain; charset=utf-8 for SchemaFormat.
     */
    public static final SchemaFormat TEXT_PLAIN_CHARSET_UTF8 = fromString("text/plain; charset=utf-8");

    /**
     * Creates a new instance of SchemaFormat value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public SchemaFormat() {
    }

    /**
     * Creates or finds a SchemaFormat from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding SchemaFormat.
     */
    public static SchemaFormat fromString(String name) {
        return fromString(name, SchemaFormat.class);
    }

    /**
     * Gets known SchemaFormat values.
     * 
     * @return known SchemaFormat values.
     */
    public static Collection<SchemaFormat> values() {
        return values(SchemaFormat.class);
    }
}
