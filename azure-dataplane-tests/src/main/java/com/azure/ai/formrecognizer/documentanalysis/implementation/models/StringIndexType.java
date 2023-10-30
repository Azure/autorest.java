// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Method used to compute string offset and length.
 */
public final class StringIndexType extends ExpandableStringEnum<StringIndexType> {
    /**
     * Static value textElements for StringIndexType.
     */
    public static final StringIndexType TEXT_ELEMENTS = fromString("textElements");

    /**
     * Static value unicodeCodePoint for StringIndexType.
     */
    public static final StringIndexType UNICODE_CODE_POINT = fromString("unicodeCodePoint");

    /**
     * Static value utf16CodeUnit for StringIndexType.
     */
    public static final StringIndexType UTF16CODE_UNIT = fromString("utf16CodeUnit");

    /**
     * Creates a new instance of StringIndexType value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public StringIndexType() {
    }

    /**
     * Creates or finds a StringIndexType from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding StringIndexType.
     */
    @JsonCreator
    public static StringIndexType fromString(String name) {
        return fromString(name, StringIndexType.class);
    }

    /**
     * Gets known StringIndexType values.
     * 
     * @return known StringIndexType values.
     */
    public static Collection<StringIndexType> values() {
        return values(StringIndexType.class);
    }
}
