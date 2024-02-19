// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.extensibleenums.models;

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Defines values for IntEnum.
 */
public final class IntEnum extends ExpandableStringEnum<IntEnum> {
    /**
     * This is a really long comment to see what wrapping looks like. This comment is really long and it should wrap for
     * readability. Please wrap. This should wrap.
     */
    public static final IntEnum ONE = fromString("1");

    /**
     * two.
     */
    public static final IntEnum TWO = fromString("2");

    /**
     * three.
     */
    public static final IntEnum THREE = fromString("3");

    /**
     * Creates a new instance of IntEnum value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public IntEnum() {
    }

    /**
     * Creates or finds a IntEnum from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding IntEnum.
     */
    public static IntEnum fromString(String name) {
        return fromString(name, IntEnum.class);
    }

    /**
     * Gets known IntEnum values.
     * 
     * @return known IntEnum values.
     */
    public static Collection<IntEnum> values() {
        return values(IntEnum.class);
    }
}
