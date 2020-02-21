package com.azure.autorest.model.clientmodel;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * A parameter for a method.
 */
public class ClientMethodParameter {
    /**
     * The description of this parameter.
     */
    private String description;
    /**
     * Whether or not this parameter is final.
     */
    private boolean isFinal;
    /**
     * The type of this parameter.
     */
    private IType wireType;
    /**
     * The name of this parameter.
     */
    private String name;
    /**
     * Whether or not this parameter is required.
     */
    private boolean isRequired;
    /**
     * Whether or not this parameter has a constant value.
     */
    private boolean isConstant;
    /**
     * Whether or not this parameter is from a client property.
     */
    private boolean fromClient;
    private String defaultValue;
    /**
     * The annotations that should be part of this Parameter's declaration.
     */
    private List<ClassType> annotations;

    /**
     * Create a new Parameter with the provided properties.
     * @param description The description of this parameter.
     * @param isFinal Whether or not this parameter is final.
     * @param wireType The type of this parameter.
     * @param name The name of this parameter.
     * @param isRequired Whether or not this parameter is required.
     * @param isConstant Whether or not this parameter has a constant value.
     * @param fromClient Whether or not this parameter is from a client property.
     * @param annotations The annotations that should be part of this Parameter's declaration.
     */
    public ClientMethodParameter(String description, boolean isFinal, IType wireType, String name, boolean isRequired, boolean isConstant, boolean fromClient, String defaultValue, List<ClassType> annotations) {
        this.description = description;
        this.isFinal = isFinal;
        this.wireType = wireType;
        this.name = name;
        this.isRequired = isRequired;
        this.isConstant = isConstant;
        this.fromClient = fromClient;
        this.defaultValue = defaultValue;
        this.annotations = annotations;
    }

    public final String getDescription() {
        return description;
    }

    public final boolean getIsFinal() {
        return isFinal;
    }

    /**
     * The type of this parameter.
     */
    public final IType getClientType() {
        return getWireType().getClientType();
    }

    public final IType getWireType() {
        return wireType;
    }

    public final String getName() {
        return name;
    }

    public final boolean getIsRequired() {
        return isRequired;
    }

    public final boolean getIsConstant() {
        return isConstant;
    }

    public final boolean getFromClient() {
        return fromClient;
    }

    public final String getDefaultValue() {
        return defaultValue;
    }

    public final List<ClassType> getAnnotations() {
        return annotations;
    }

    /**
     * The full declaration of this parameter as it appears in a method signature.
     */
    public final String getDeclaration() {
        return getAnnotations().stream().map((ClassType annotation) -> String.format("@%1$s ", annotation.getName())).collect(Collectors.joining("")) + (getIsFinal() ? "final " : "") + String.format("%1$s %2$s", getClientType(), getName());
    }

    /**
     * Add this parameter's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
     */
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        for (ClassType annotation : getAnnotations()) {
            annotation.addImportsTo(imports, includeImplementationImports);
        }
        getClientType().addImportsTo(imports, includeImplementationImports);
    }
}
