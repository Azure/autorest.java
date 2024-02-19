// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants.models;

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Defines values for ModelAsStringNoRequiredOneValueNoDefaultEnum.
 */
public final class ModelAsStringNoRequiredOneValueNoDefaultEnum
    extends ExpandableStringEnum<ModelAsStringNoRequiredOneValueNoDefaultEnum> {
    /**
     * Static value value1 for ModelAsStringNoRequiredOneValueNoDefaultEnum.
     */
    public static final ModelAsStringNoRequiredOneValueNoDefaultEnum VALUE1 = fromString("value1");

    /**
     * Creates a new instance of ModelAsStringNoRequiredOneValueNoDefaultEnum value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public ModelAsStringNoRequiredOneValueNoDefaultEnum() {
    }

    /**
     * Creates or finds a ModelAsStringNoRequiredOneValueNoDefaultEnum from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding ModelAsStringNoRequiredOneValueNoDefaultEnum.
     */
    public static ModelAsStringNoRequiredOneValueNoDefaultEnum fromString(String name) {
        return fromString(name, ModelAsStringNoRequiredOneValueNoDefaultEnum.class);
    }

    /**
     * Gets known ModelAsStringNoRequiredOneValueNoDefaultEnum values.
     * 
     * @return known ModelAsStringNoRequiredOneValueNoDefaultEnum values.
     */
    public static Collection<ModelAsStringNoRequiredOneValueNoDefaultEnum> values() {
        return values(ModelAsStringNoRequiredOneValueNoDefaultEnum.class);
    }
}
