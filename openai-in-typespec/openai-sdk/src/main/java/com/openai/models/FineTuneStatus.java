// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.openai.models;

import com.generic.core.annotation.Generated;
import com.generic.core.models.ExpandableStringEnum;
import java.util.Collection;

/**
 * Defines values for FineTuneStatus.
 */
public final class FineTuneStatus extends ExpandableStringEnum<FineTuneStatus> {
    /**
     * Static value created for FineTuneStatus.
     */
    @Generated
    public static final FineTuneStatus CREATED = fromString("created");

    /**
     * Static value running for FineTuneStatus.
     */
    @Generated
    public static final FineTuneStatus RUNNING = fromString("running");

    /**
     * Static value succeeded for FineTuneStatus.
     */
    @Generated
    public static final FineTuneStatus SUCCEEDED = fromString("succeeded");

    /**
     * Static value failed for FineTuneStatus.
     */
    @Generated
    public static final FineTuneStatus FAILED = fromString("failed");

    /**
     * Static value cancelled for FineTuneStatus.
     */
    @Generated
    public static final FineTuneStatus CANCELLED = fromString("cancelled");

    /**
     * Creates a new instance of FineTuneStatus value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public FineTuneStatus() {
    }

    /**
     * Creates or finds a FineTuneStatus from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding FineTuneStatus.
     */
    @Generated
    public static FineTuneStatus fromString(String name) {
        return fromString(name, FineTuneStatus.class);
    }

    /**
     * Gets known FineTuneStatus values.
     * 
     * @return known FineTuneStatus values.
     */
    @Generated
    public static Collection<FineTuneStatus> values() {
        return values(FineTuneStatus.class);
    }
}
