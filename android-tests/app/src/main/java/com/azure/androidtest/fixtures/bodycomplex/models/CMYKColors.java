package com.azure.androidtest.fixtures.bodycomplex.models;

import com.azure.android.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Defines values for CMYKColors.
 */
public final class CMYKColors extends ExpandableStringEnum<CMYKColors> {
    /**
     * Static value cyan for CMYKColors.
     */
    public static final CMYKColors CYAN = fromString("cyan");

    /**
     * Static value Magenta for CMYKColors.
     */
    public static final CMYKColors MAGENTA = fromString("Magenta");

    /**
     * Static value YELLOW for CMYKColors.
     */
    public static final CMYKColors YELLOW = fromString("YELLOW");

    /**
     * Static value blacK for CMYKColors.
     */
    public static final CMYKColors BLACK = fromString("blacK");

    /**
     * Creates or finds a CMYKColors from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding CMYKColors.
     */
    @JsonCreator
    public static CMYKColors fromString(String name) {
        return fromString(name, CMYKColors.class);
    }

    /**
     * @return known CMYKColors values.
     */
    public static Collection<CMYKColors> values() {
        return values(CMYKColors.class);
    }
}
