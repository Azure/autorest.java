// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a set of parameters.
 */
public class ParameterGroupSchema extends ComplexSchema {
    private List<Parameter> parameters = new ArrayList<>();

    /**
     * Gets the collection of properties that are in this object. (Required)
     *
     * @return The collection of properties that are in this object.
     */
    public List<Parameter> getParameters() {
        return parameters;
    }

    /**
     * Sets the collection of properties that are in this object. (Required)
     *
     * @param parameters The collection of properties that are in this object.
     */
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return ParameterGroupSchema.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this))
            + "[parameters=" + Objects.toString(parameters, "<null>") + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(parameters);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ParameterGroupSchema)) {
            return false;
        }

        ParameterGroupSchema rhs = ((ParameterGroupSchema) other);
        return Objects.equals(parameters, rhs.parameters);
    }

}
