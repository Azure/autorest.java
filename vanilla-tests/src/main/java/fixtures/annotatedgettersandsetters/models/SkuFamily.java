// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.annotatedgettersandsetters.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/** Defines values for SkuFamily. */
public final class SkuFamily extends ExpandableStringEnum<SkuFamily> {
    /** Static value A for SkuFamily. */
    public static final SkuFamily A = fromString("A");

    /**
     * Creates or finds a SkuFamily from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding SkuFamily.
     */
    @JsonCreator
    public static SkuFamily fromString(String name) {
        return fromString(name, SkuFamily.class);
    }

    /**
     * Gets known SkuFamily values.
     *
     * @return known SkuFamily values.
     */
    public static Collection<SkuFamily> values() {
        return values(SkuFamily.class);
    }
}
