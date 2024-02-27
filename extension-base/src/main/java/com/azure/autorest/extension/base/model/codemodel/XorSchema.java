// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an XOR relationship between several schemas
 */
public class XorSchema extends ComplexSchema {
    private List<Schema> oneOf = new ArrayList<>();

    /**
     * Gets the set of schemas that this must be one and only one of. (Required)
     *
     * @return The set of schemas that this must be one and only one of.
     */
    public List<Schema> getOneOf() {
        return oneOf;
    }

    /**
     * Sets the set of schemas that this must be one and only one of. (Required)
     *
     * @param oneOf The set of schemas that this must be one and only one of.
     */
    public void setOneOf(List<Schema> oneOf) {
        this.oneOf = oneOf;
    }

    @Override
    public String toString() {
        return XorSchema.class.getName() + "@" + Integer.toHexString(System.identityHashCode(this)) + "[oneOf="
            + Objects.toString(oneOf, "<null>") + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(oneOf);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XorSchema)) {
            return false;
        }

        XorSchema rhs = ((XorSchema) other);
        return Objects.equals(oneOf, rhs.oneOf);
    }
}
