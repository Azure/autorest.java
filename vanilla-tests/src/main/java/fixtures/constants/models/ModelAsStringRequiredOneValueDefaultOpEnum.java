// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Defines values for ModelAsStringRequiredOneValueDefaultOpEnum.
 */
public final class ModelAsStringRequiredOneValueDefaultOpEnum
    extends ExpandableStringEnum<ModelAsStringRequiredOneValueDefaultOpEnum> {
    /**
     * Static value value1 for ModelAsStringRequiredOneValueDefaultOpEnum.
     */
    public static final ModelAsStringRequiredOneValueDefaultOpEnum VALUE1 = fromString("value1");

    /**
     * Creates a new instance of ModelAsStringRequiredOneValueDefaultOpEnum value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public ModelAsStringRequiredOneValueDefaultOpEnum() {}

    /**
     * Creates or finds a ModelAsStringRequiredOneValueDefaultOpEnum from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding ModelAsStringRequiredOneValueDefaultOpEnum.
     */
    @JsonCreator
    public static ModelAsStringRequiredOneValueDefaultOpEnum fromString(String name) {
        return fromString(name, ModelAsStringRequiredOneValueDefaultOpEnum.class);
    }

    /**
     * Gets known ModelAsStringRequiredOneValueDefaultOpEnum values.
     * 
     * @return known ModelAsStringRequiredOneValueDefaultOpEnum values.
     */
    public static Collection<ModelAsStringRequiredOneValueDefaultOpEnum> values() {
        return values(ModelAsStringRequiredOneValueDefaultOpEnum.class);
    }
}
