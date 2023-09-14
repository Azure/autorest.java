// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.valuetypes.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/** Enum that will be used as a property for model EnumProperty. Non-extensible. */
public final class InnerEnum extends ExpandableStringEnum<InnerEnum> {
    /** First value. */
    @Generated public static final InnerEnum VALUE_ONE = fromString("ValueOne");

    /** Second value. */
    @Generated public static final InnerEnum VALUE_TWO = fromString("ValueTwo");

    /**
     * Creates a new instance of InnerEnum value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public InnerEnum() {}

    /**
     * Creates or finds a InnerEnum from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding InnerEnum.
     */
    @Generated
    @JsonCreator
    public static InnerEnum fromString(String name) {
        return fromString(name, InnerEnum.class);
    }

    /**
     * Gets known InnerEnum values.
     *
     * @return known InnerEnum values.
     */
    @Generated
    public static Collection<InnerEnum> values() {
        return values(InnerEnum.class);
    }
}
