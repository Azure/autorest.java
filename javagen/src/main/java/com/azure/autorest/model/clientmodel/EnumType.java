package com.azure.autorest.model.clientmodel;

import java.util.List;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * The details of an enumerated type that is used by a service.
 */
public class EnumType implements IType {
    /**
     * The name of the new Enum.
     */
    private String name;
    /**
     * The package that this enumeration belongs to.
     */
    private String packageName;
    /**
     * Whether or not this will be an ExpandableStringEnum type.
     */
    private boolean expandable;
    /**
     * The values of the Enum.
     */
    private List<ClientEnumValue> values;

    /**
     * Create a new Enum with the provided properties.
     * @param name The name of the new Enum.
     * @param expandable Whether or not this will be an ExpandableStringEnum type.
     * @param values The values of the Enum.
     */
    public EnumType(String package_Keyword, String name, boolean expandable, List<ClientEnumValue> values) {
        this.name = name;
        packageName = package_Keyword;
        this.expandable = expandable;
        this.values = values;
    }

    public final String getName() {
        return name;
    }

    public final String getPackage() {
        return packageName;
    }

    public final boolean getExpandable() {
        return expandable;
    }

    public final List<ClientEnumValue> getValues() {
        return values;
    }

    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        imports.add(String.format("%1$s.%2$s", getPackage(), getName()));
    }

    public final IType asNullable() {
        return this;
    }

    public final boolean contains(IType type) {
        return this == type;
    }

    public final String defaultValueExpression(String sourceExpression) {
        if (sourceExpression == null) {
            return null;
        }
        return String.format("%1$s.%2$s", getName(), sourceExpression.toUpperCase());
    }

    public final IType getClientType() {
        return this;
    }

    public final String convertToClientType(String expression) {
        return expression;
    }

    public final String convertFromClientType(String expression) {
        return expression;
    }

    public final String validate(String expression) {
        return null;
    }

    @Override
    public String toString() {
        return getName();
    }
}