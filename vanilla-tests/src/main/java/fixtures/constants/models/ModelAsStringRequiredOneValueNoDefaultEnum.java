// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants.models;

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Defines values for ModelAsStringRequiredOneValueNoDefaultEnum.
 */
public final class ModelAsStringRequiredOneValueNoDefaultEnum
    extends ExpandableStringEnum<ModelAsStringRequiredOneValueNoDefaultEnum> {
    /**
     * Static value value1 for ModelAsStringRequiredOneValueNoDefaultEnum.
     */
    public static final ModelAsStringRequiredOneValueNoDefaultEnum VALUE1 = fromString("value1");

    /**
     * Creates a new instance of ModelAsStringRequiredOneValueNoDefaultEnum value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public ModelAsStringRequiredOneValueNoDefaultEnum() {
    }

    /**
     * Creates or finds a ModelAsStringRequiredOneValueNoDefaultEnum from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding ModelAsStringRequiredOneValueNoDefaultEnum.
     */
    public static ModelAsStringRequiredOneValueNoDefaultEnum fromString(String name) {
        return fromString(name, ModelAsStringRequiredOneValueNoDefaultEnum.class);
    }

    /**
     * Gets known ModelAsStringRequiredOneValueNoDefaultEnum values.
     * 
     * @return known ModelAsStringRequiredOneValueNoDefaultEnum values.
     */
    public static Collection<ModelAsStringRequiredOneValueNoDefaultEnum> values() {
        return values(ModelAsStringRequiredOneValueNoDefaultEnum.class);
    }
}
