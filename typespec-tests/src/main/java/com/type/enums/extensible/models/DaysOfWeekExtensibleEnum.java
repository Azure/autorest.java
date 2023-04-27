// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.type.enums.extensible.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/** Defines values for DaysOfWeekExtensibleEnum. */
public final class DaysOfWeekExtensibleEnum extends ExpandableStringEnum<DaysOfWeekExtensibleEnum> {
    /** Static value Monday for DaysOfWeekExtensibleEnum. */
    @Generated public static final DaysOfWeekExtensibleEnum MONDAY = fromString("Monday");

    /** Static value Tuesday for DaysOfWeekExtensibleEnum. */
    @Generated public static final DaysOfWeekExtensibleEnum TUESDAY = fromString("Tuesday");

    /** Static value Wednesday for DaysOfWeekExtensibleEnum. */
    @Generated public static final DaysOfWeekExtensibleEnum WEDNESDAY = fromString("Wednesday");

    /** Static value Thursday for DaysOfWeekExtensibleEnum. */
    @Generated public static final DaysOfWeekExtensibleEnum THURSDAY = fromString("Thursday");

    /** Static value Friday for DaysOfWeekExtensibleEnum. */
    @Generated public static final DaysOfWeekExtensibleEnum FRIDAY = fromString("Friday");

    /** Static value Saturday for DaysOfWeekExtensibleEnum. */
    @Generated public static final DaysOfWeekExtensibleEnum SATURDAY = fromString("Saturday");

    /** Static value Sunday for DaysOfWeekExtensibleEnum. */
    @Generated public static final DaysOfWeekExtensibleEnum SUNDAY = fromString("Sunday");

    /**
     * Creates a new instance of DaysOfWeekExtensibleEnum value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public DaysOfWeekExtensibleEnum() {}

    /**
     * Creates or finds a DaysOfWeekExtensibleEnum from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding DaysOfWeekExtensibleEnum.
     */
    @Generated
    @JsonCreator
    public static DaysOfWeekExtensibleEnum fromString(String name) {
        return fromString(name, DaysOfWeekExtensibleEnum.class);
    }

    /**
     * Gets known DaysOfWeekExtensibleEnum values.
     *
     * @return known DaysOfWeekExtensibleEnum values.
     */
    @Generated
    public static Collection<DaysOfWeekExtensibleEnum> values() {
        return values(DaysOfWeekExtensibleEnum.class);
    }
}
