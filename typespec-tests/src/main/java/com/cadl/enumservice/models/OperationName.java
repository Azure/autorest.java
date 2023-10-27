// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.enumservice.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/** Defines values for OperationName. */
public final class OperationName extends ExpandableStringEnum<OperationName> {
    /** Static value Read for OperationName. */
    @Generated public static final OperationName READ = fromString("Read");

    /** Static value Write for OperationName. */
    @Generated public static final OperationName WRITE = fromString("Write");

    /**
     * Creates a new instance of OperationName value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public OperationName() {}

    /**
     * Creates or finds a OperationName from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding OperationName.
     */
    @Generated
    @JsonCreator
    public static OperationName fromString(String name) {
        return fromString(name, OperationName.class);
    }

    /**
     * Gets known OperationName values.
     *
     * @return known OperationName values.
     */
    @Generated
    public static Collection<OperationName> values() {
        return values(OperationName.class);
    }
}
