package com.azure.autorest.model.clientmodel;

import com.azure.autorest.util.CodeNamer;

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
    private final String name;
    /**
     * The package that this enumeration belongs to.
     */
    private final String packageName;
    /**
     * Whether this will be an ExpandableStringEnum type.
     */
    private final boolean expandable;
    /**
     * The values of the Enum.
     */
    private final List<ClientEnumValue> values;

    private final IType elementType;

    /**
     * Create a new Enum with the provided properties.
     * @param name The name of the new Enum.
     * @param expandable Whether this will be an ExpandableStringEnum type.
     * @param values The values of the Enum.
     */
    private EnumType(String package_Keyword, String name, boolean expandable, List<ClientEnumValue> values, IType elementType) {
        this.name = name;
        packageName = package_Keyword;
        this.expandable = expandable;
        this.values = values;
        this.elementType = elementType;
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

    public final IType getElementType() {
        return elementType;
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
        if (this.getExpandable()) {
            for (ClientEnumValue enumValue : this.getValues()) {
                if (sourceExpression.equals(enumValue.getValue())) {
                    return String.format("%1$s.%2$s", getName(), enumValue.getName());
                }
            }
            return String.format("%1$s.from%2$s(%3$s)", getName(), CodeNamer.toPascalCase(this.getElementType().toString()), this.getElementType().defaultValueExpression(sourceExpression));
        } else {
            for (ClientEnumValue enumValue : this.getValues()) {
                if (sourceExpression.equals(enumValue.getValue())) {
                    return String.format("%1$s.%2$s", getName(), enumValue.getName());
                }
            }
            return null;
        }
    }

    public final String getFromJsonMethodName() {
        return "from" + CodeNamer.toPascalCase(elementType.getClientType().toString());
    }

    public final String getToJsonMethodName() {
        return "to" + CodeNamer.toPascalCase(elementType.getClientType().toString());
    }

    @Override
    public String defaultValueExpression() {
        return "null";
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

    public static class Builder {
        private String name;
        private String packageName;
        private boolean expandable;
        private List<ClientEnumValue> values;
        private IType elementType = ClassType.String;

        /**
         * Sets the name of the Enum.
         * @param name the name of the Enum
         * @return the Builder
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the package name of the Enum.
         * @param packageName the package name of the Enum
         * @return the Builder
         */
        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        /**
         * Sets whether the Enum is expandable.
         * @param expandable whether the Enum is expandable
         * @return the Builder
         */
        public Builder expandable(boolean expandable) {
            this.expandable = expandable;
            return this;
        }

        /**
         * Sets the values of the Enum.
         * @param values the values of the Enum
         * @return the Builder
         */
        public Builder values(List<ClientEnumValue> values) {
            this.values = values;
            return this;
        }

        /**
         * Sets the type of elements of the Enum.
         * @param elementType the type of elements of the Enum
         * @return the Builder
         */
        public Builder elementType(IType elementType) {
            if (elementType != null) {
                this.elementType = elementType;
            }
            return this;
        }

        /**
         * @return an immutable EnumType instance with the configurations on this builder.
         */
        public EnumType build() {
            return new EnumType(
                    packageName,
                    name,
                    expandable,
                    values,
                    elementType
            );
        }
    }
}
