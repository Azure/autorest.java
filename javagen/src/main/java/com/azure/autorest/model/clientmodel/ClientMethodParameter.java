package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;

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
     * The raw type of this parameter. In low-level mode, wireType might be BinaryData.
     */
    private IType rawType;
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
    /**
     * The default value for the parameter.
     */
    private String defaultValue;
    /**
     * The annotations that should be part of this Parameter's declaration.
     */
    private List<ClassType> annotations;

    private RequestParameterLocation location;

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
    private ClientMethodParameter(String description, boolean isFinal, IType wireType, IType rawType, String name, boolean isRequired, boolean isConstant, boolean fromClient, String defaultValue, List<ClassType> annotations, RequestParameterLocation location) {
        this.description = description;
        this.isFinal = isFinal;
        this.wireType = wireType;
        this.rawType = rawType;
        this.name = name;
        this.isRequired = isRequired;
        this.isConstant = isConstant;
        this.fromClient = fromClient;
        this.defaultValue = defaultValue;
        this.annotations = annotations;
        this.location = location;
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

    public final IType getRawType() {
        return rawType;
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

    public final RequestParameterLocation getLocation() {
        return location;
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

    public static class Builder {
        private String description;
        private boolean isFinal;
        private IType wireType;
        private IType rawType;
        private String name;
        private boolean isRequired;
        private boolean isConstant;
        private boolean fromClient;
        private String defaultValue;
        private List<ClassType> annotations;
        private RequestParameterLocation location;

        /**
         * Sets the description of this parameter.
         * @param description the description of this parameter
         * @return the Builder itself
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets whether or not this parameter is final.
         * @param isFinal whether or not this parameter is final
         * @return the Builder itself
         */
        public Builder isFinal(boolean isFinal) {
            this.isFinal = isFinal;
            return this;
        }

        /**
         * Sets the type of this parameter.
         * @param wireType the type of this parameter
         * @return the Builder itself
         */
        public Builder wireType(IType wireType) {
            this.wireType = wireType;
            return this;
        }

        /**
         * Sets the raw type of this parameter.
         * @param rawType the raw type of this parameter
         * @return the Builder itself
         */
        public Builder rawType(IType rawType) {
            this.rawType = rawType;
            return this;
        }

        /**
         * Sets the name of this parameter.
         * @param name the name of this parameter
         * @return the Builder itself
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets whether or not this parameter is required.
         * @param isRequired whether or not this parameter is required
         * @return the Builder itself
         */
        public Builder isRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }

        /**
         * Sets whether or not this parameter has a constant value.
         * @param isConstant whether or not this parameter has a constant value
         * @return the Builder itself
         */
        public Builder isConstant(boolean isConstant) {
            this.isConstant = isConstant;
            return this;
        }

        /**
         * Sets whether or not this parameter is from a client property.
         * @param fromClient whether or not this parameter is from a client property
         * @return the Builder itself
         */
        public Builder fromClient(boolean fromClient) {
            this.fromClient = fromClient;
            return this;
        }

        /**
         * Sets the default value for the parameter.
         * @param defaultValue the default value for the parameter
         * @return the Builder itself
         */
        public Builder defaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        /**
         * Sets the annotations that should be part of this Parameter's declaration.
         * @param annotations the annotations that should be part of this Parameter's declaration
         * @return the Builder itself
         */
        public Builder annotations(List<ClassType> annotations) {
            this.annotations = annotations;
            return this;
        }

        /**
         * Sets the location of the parameter.
         * @param location the location of the parameter
         * @return the Builder itself
         */
        public Builder location(RequestParameterLocation location) {
            this.location = location;
            return this;
        }

        public ClientMethodParameter build() {
            return new ClientMethodParameter(description,
                    isFinal,
                    wireType,
                    rawType,
                    name,
                    isRequired,
                    isConstant,
                    fromClient,
                    defaultValue,
                    annotations,
                    location);
        }
    }
}
