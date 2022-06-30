// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/** Defines values for ModelAsStringRequiredTwoValueDefaultOpEnum. */
public final class ModelAsStringRequiredTwoValueDefaultOpEnum
        extends ExpandableStringEnum<ModelAsStringRequiredTwoValueDefaultOpEnum> {
    /** Static value value1 for ModelAsStringRequiredTwoValueDefaultOpEnum. */
    public static final ModelAsStringRequiredTwoValueDefaultOpEnum VALUE1 = fromString("value1");

    /** Static value value2 for ModelAsStringRequiredTwoValueDefaultOpEnum. */
    public static final ModelAsStringRequiredTwoValueDefaultOpEnum VALUE2 = fromString("value2");

    /**
     * Creates or finds a ModelAsStringRequiredTwoValueDefaultOpEnum from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding ModelAsStringRequiredTwoValueDefaultOpEnum.
     */
    @JsonCreator
    public static ModelAsStringRequiredTwoValueDefaultOpEnum fromString(String name) {
        return fromString(name, ModelAsStringRequiredTwoValueDefaultOpEnum.class);
    }

    /**
     * Gets known ModelAsStringRequiredTwoValueDefaultOpEnum values.
     *
     * @return known ModelAsStringRequiredTwoValueDefaultOpEnum values.
     */
    public static Collection<ModelAsStringRequiredTwoValueDefaultOpEnum> values() {
        return values(ModelAsStringRequiredTwoValueDefaultOpEnum.class);
    }
}
