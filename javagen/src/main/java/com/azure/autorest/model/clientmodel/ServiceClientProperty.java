package com.azure.autorest.model.clientmodel;

import java.util.Objects;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * A property that exists within a service's client.
 */
public class ServiceClientProperty {
    /**
     * The description of this property.
     */
    private String description;
    /**
     * The type of this property that is exposed via the client.
     */
    private IType type;
    /**
     * The name of this property.
     */
    private String name;
    /**
     * Get whether or not this property's value can be changed by the client library.
     */
    private boolean readOnly;
    /**
     * Get the expression that evaluates to this property's default value.
     */
    private String defaultValueExpression;

    /**
     * Create a new ServiceClientProperty with the provided properties.
     * @param description The description of this property.
     * @param type The type of this property that is exposed via the client.
     * @param name The name of this property.
     * @param readOnly Whether or not this property's value can be changed by the client library.
     * @param defaultValueExpression The expression that evaluates to this property's default value.
     */
    public ServiceClientProperty(String description, IType type, String name, boolean readOnly, String defaultValueExpression) {
        this.description = description;
        this.type = type;
        this.name = name;
        this.readOnly = readOnly;
        this.defaultValueExpression = defaultValueExpression;
    }

    public final String getDescription() {
        return description;
    }

    public final IType getType() {
        return type;
    }

    public final String getName() {
        return name;
    }

    public final boolean isReadOnly() {
        return readOnly;
    }

    public final String getDefaultValueExpression() {
        return defaultValueExpression;
    }

    /**
     * Add this property's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
     */
    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        getType().addImportsTo(imports, includeImplementationImports);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceClientProperty that = (ServiceClientProperty) o;
        return readOnly == that.readOnly &&
                Objects.equals(description, that.description) &&
                Objects.equals(type, that.type) &&
                Objects.equals(name, that.name) &&
                Objects.equals(defaultValueExpression, that.defaultValueExpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, type, name, readOnly, defaultValueExpression);
    }
}
