// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.openai.models;

import com.generic.core.annotation.Generated;
import com.generic.core.models.ExpandableStringEnum;
import java.util.Collection;

/**
 * Defines values for FineTuningJobEventLevel.
 */
public final class FineTuningJobEventLevel extends ExpandableStringEnum<FineTuningJobEventLevel> {
    /**
     * Static value info for FineTuningJobEventLevel.
     */
    @Generated
    public static final FineTuningJobEventLevel INFO = fromString("info");

    /**
     * Static value warn for FineTuningJobEventLevel.
     */
    @Generated
    public static final FineTuningJobEventLevel WARN = fromString("warn");

    /**
     * Static value error for FineTuningJobEventLevel.
     */
    @Generated
    public static final FineTuningJobEventLevel ERROR = fromString("error");

    /**
     * Creates a new instance of FineTuningJobEventLevel value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public FineTuningJobEventLevel() {
    }

    /**
     * Creates or finds a FineTuningJobEventLevel from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding FineTuningJobEventLevel.
     */
    @Generated
    public static FineTuningJobEventLevel fromString(String name) {
        return fromString(name, FineTuningJobEventLevel.class);
    }

    /**
     * Gets known FineTuningJobEventLevel values.
     * 
     * @return known FineTuningJobEventLevel values.
     */
    @Generated
    public static Collection<FineTuningJobEventLevel> values() {
        return values(FineTuningJobEventLevel.class);
    }
}
