package com.azure.androidtest.fixtures.bodyarray.models;

import com.azure.android.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Defines values for Enum1.
 */
public final class Enum1 extends ExpandableStringEnum<Enum1> {
    /**
     * Static value foo1 for Enum1.
     */
    public static final Enum1 FOO1 = fromString("foo1");

    /**
     * Static value foo2 for Enum1.
     */
    public static final Enum1 FOO2 = fromString("foo2");

    /**
     * Static value foo3 for Enum1.
     */
    public static final Enum1 FOO3 = fromString("foo3");

    /**
     * Creates or finds a Enum1 from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding Enum1.
     */
    @JsonCreator
    public static Enum1 fromString(String name) {
        return fromString(name, Enum1.class);
    }

    /**
     * @return known Enum1 values.
     */
    public static Collection<Enum1> values() {
        return values(Enum1.class);
    }
}
