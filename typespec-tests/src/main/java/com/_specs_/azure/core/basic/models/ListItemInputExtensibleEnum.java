// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com._specs_.azure.core.basic.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * An extensible enum input parameter.
 */
public final class ListItemInputExtensibleEnum extends ExpandableStringEnum<ListItemInputExtensibleEnum> {

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
}
