// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.models;

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Colors possible.
 */
public final class GoblinSharkColor extends ExpandableStringEnum<GoblinSharkColor> {
    /**
     * Static value pink for GoblinSharkColor.
     */
    public static final GoblinSharkColor PINK = fromString("pink");

    /**
     * Static value gray for GoblinSharkColor.
     */
    public static final GoblinSharkColor GRAY = fromString("gray");

    /**
     * Static value brown for GoblinSharkColor.
     */
    public static final GoblinSharkColor BROWN = fromString("brown");

    /**
     * Uppercase RED.
     */
    public static final GoblinSharkColor UPPER_RED = fromString("RED");

    /**
     * Lowercase RED.
     */
    public static final GoblinSharkColor LOWER_RED = fromString("red");

    /**
     * Creates a new instance of GoblinSharkColor value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public GoblinSharkColor() {
    }

    /**
     * Creates or finds a GoblinSharkColor from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding GoblinSharkColor.
     */
    public static GoblinSharkColor fromString(String name) {
        return fromString(name, GoblinSharkColor.class);
    }

    /**
     * Gets known GoblinSharkColor values.
     * 
     * @return known GoblinSharkColor values.
     */
    public static Collection<GoblinSharkColor> values() {
        return values(GoblinSharkColor.class);
    }
}
