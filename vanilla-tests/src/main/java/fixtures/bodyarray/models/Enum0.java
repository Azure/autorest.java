// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodyarray.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Defines values for Enum0.
 */
public final class Enum0 extends ExpandableStringEnum<Enum0> {
    /**
     * Static value foo1 for Enum0.
     */
    public static final Enum0 FOO1 = fromString("foo1");

    /**
     * Static value foo2 for Enum0.
     */
    public static final Enum0 FOO2 = fromString("foo2");

    /**
     * Static value foo3 for Enum0.
     */
    public static final Enum0 FOO3 = fromString("foo3");

    /**
     * Creates a new instance of Enum0 value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public Enum0() {
    }

    /**
     * Creates or finds a Enum0 from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding Enum0.
     */
    @JsonCreator
    public static Enum0 fromString(String name) {
        return fromString(name, Enum0.class);
    }

    /**
     * Gets known Enum0 values.
     * 
     * @return known Enum0 values.
     */
    public static Collection<Enum0> values() {
        return values(Enum0.class);
    }
}
