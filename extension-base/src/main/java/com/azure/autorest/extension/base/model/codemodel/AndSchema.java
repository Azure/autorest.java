// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An AND relationship between several schemas.
 */
public class AndSchema extends ComplexSchema {

    /**
     * the set of schemas that this schema is composed of.
     * (Required)
     */
    private List<ComplexSchema> allOf = new ArrayList<ComplexSchema>();
    private String discriminatorValue;

    /**
     * Gets the schemas that this schema composes. (Required)
     *
     * @return The schemas that this schema composes.
     */
    public List<ComplexSchema> getAllOf() {
        return allOf;
    }

    /**
     * Sets the schemas that this schema composes. (Required)
     *
     * @param allOf The schemas that this schema composes.
     */
    public void setAllOf(List<ComplexSchema> allOf) {
        this.allOf = allOf;
    }

    /**
     * Gets the value of the discriminator for this schema.
     *
     * @return The value of the discriminator for this schema.
     */
    public String getDiscriminatorValue() {
        return discriminatorValue;
    }

    /**
     * Sets the value of the discriminator for this schema.
     *
     * @param discriminatorValue The value of the discriminator for this schema.
     */
    public void setDiscriminatorValue(String discriminatorValue) {
        this.discriminatorValue = discriminatorValue;
    }

    @Override
    public String toString() {
        return AndSchema.class.getName() + "@" + Integer.toHexString(System.identityHashCode(this)) + "[allOf="
            + Objects.toString(allOf, "<null>") + ",discriminatorValue="
            + Objects.toString(discriminatorValue, "<null>") + ']';
    }

    @Override
    public int hashCode() {
        return Objects.hash(allOf, discriminatorValue);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AndSchema)) {
            return false;
        }
        AndSchema rhs = ((AndSchema) other);
        return Objects.equals(allOf, rhs.allOf) && Objects.equals(discriminatorValue, rhs.discriminatorValue);
    }
}
