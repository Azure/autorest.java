// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants.models;

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Defines values for ModelAsStringNoRequiredTwoValueDefaultOpEnum.
 */
public final class ModelAsStringNoRequiredTwoValueDefaultOpEnum
    extends ExpandableStringEnum<ModelAsStringNoRequiredTwoValueDefaultOpEnum> {
    /**
     * Static value value1 for ModelAsStringNoRequiredTwoValueDefaultOpEnum.
     */
    public static final ModelAsStringNoRequiredTwoValueDefaultOpEnum VALUE1 = fromString("value1");

    /**
     * Static value value2 for ModelAsStringNoRequiredTwoValueDefaultOpEnum.
     */
    public static final ModelAsStringNoRequiredTwoValueDefaultOpEnum VALUE2 = fromString("value2");

    /**
     * Creates a new instance of ModelAsStringNoRequiredTwoValueDefaultOpEnum value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public ModelAsStringNoRequiredTwoValueDefaultOpEnum() {
    }

    /**
     * Creates or finds a ModelAsStringNoRequiredTwoValueDefaultOpEnum from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding ModelAsStringNoRequiredTwoValueDefaultOpEnum.
     */
    public static ModelAsStringNoRequiredTwoValueDefaultOpEnum fromString(String name) {
        return fromString(name, ModelAsStringNoRequiredTwoValueDefaultOpEnum.class);
    }

    /**
     * Gets known ModelAsStringNoRequiredTwoValueDefaultOpEnum values.
     * 
     * @return known ModelAsStringNoRequiredTwoValueDefaultOpEnum values.
     */
    public static Collection<ModelAsStringNoRequiredTwoValueDefaultOpEnum> values() {
        return values(ModelAsStringNoRequiredTwoValueDefaultOpEnum.class);
    }
}
