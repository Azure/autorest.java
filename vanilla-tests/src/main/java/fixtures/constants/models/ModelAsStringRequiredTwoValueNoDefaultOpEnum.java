// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Defines values for ModelAsStringRequiredTwoValueNoDefaultOpEnum.
 */
public final class ModelAsStringRequiredTwoValueNoDefaultOpEnum
    extends ExpandableStringEnum<ModelAsStringRequiredTwoValueNoDefaultOpEnum> {
    /**
     * Static value value1 for ModelAsStringRequiredTwoValueNoDefaultOpEnum.
     */
    @Generated
    public static final ModelAsStringRequiredTwoValueNoDefaultOpEnum VALUE1 = fromString("value1");

    /**
     * Static value value2 for ModelAsStringRequiredTwoValueNoDefaultOpEnum.
     */
    @Generated
    public static final ModelAsStringRequiredTwoValueNoDefaultOpEnum VALUE2 = fromString("value2");

    /**
     * Creates a new instance of ModelAsStringRequiredTwoValueNoDefaultOpEnum value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public ModelAsStringRequiredTwoValueNoDefaultOpEnum() {
    }

    /**
     * Creates or finds a ModelAsStringRequiredTwoValueNoDefaultOpEnum from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding ModelAsStringRequiredTwoValueNoDefaultOpEnum.
     */
    @Generated
    public static ModelAsStringRequiredTwoValueNoDefaultOpEnum fromString(String name) {
        return fromString(name, ModelAsStringRequiredTwoValueNoDefaultOpEnum.class);
    }

    /**
     * Gets known ModelAsStringRequiredTwoValueNoDefaultOpEnum values.
     * 
     * @return known ModelAsStringRequiredTwoValueNoDefaultOpEnum values.
     */
    @Generated
    public static Collection<ModelAsStringRequiredTwoValueNoDefaultOpEnum> values() {
        return values(ModelAsStringRequiredTwoValueNoDefaultOpEnum.class);
    }
}
