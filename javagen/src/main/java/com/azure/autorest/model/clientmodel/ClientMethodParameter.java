// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A parameter for a method.
 */
public class ClientMethodParameter extends MethodParameter {

    public static final ClientMethodParameter CONTEXT_PARAMETER = new ClientMethodParameter.Builder()
            .description("The context to associate with this operation.")
            .wireType(ClassType.Context)
            .name("context")
            .location(RequestParameterLocation.NONE)
            .annotations(Collections.emptyList())
            .constant(false)
            .defaultValue(null)
            .fromClient(false)
            .finalParameter(false)
            .required(false)
            .build();

    public static final ClientMethodParameter HTTP_REQUEST_PARAMETER = new ClientMethodParameter.Builder()
            .description("The HTTP request to send.")
            .wireType(ClassType.HttpRequest)
            .name("httpRequest")
            .location(RequestParameterLocation.NONE)
            .annotations(Collections.emptyList())
            .constant(false)
            .defaultValue(null)
            .fromClient(false)
            .finalParameter(false)
            .required(true)
            .build();

    public static final ClientMethodParameter REQUEST_OPTIONS_PARAMETER = new ClientMethodParameter.Builder()
            .description("The options to configure the HTTP request before HTTP client sends it.")
            .wireType(ClassType.RequestOptions)
            .name("requestOptions")
            .location(RequestParameterLocation.NONE)
            .constant(false)
            .required(false)
            .fromClient(false)
            .annotations(Collections.emptyList())
            .build();

    /**
     * Whether this parameter is final.
     */
    private final boolean isFinal;
    /**
     * The annotations that should be part of this Parameter's declaration.
     */
    private final List<ClassType> annotations;

    /**
     * Create a new Parameter with the provided properties.
     * @param description The description of this parameter.
     * @param isFinal Whether this parameter is final.
     * @param wireType The type of this parameter.
     * @param rawType The raw type of this parameter. Result of SchemaMapper.
     * @param name The name of this parameter.
     * @param isRequired Whether this parameter is required.
     * @param isConstant Whether this parameter has a constant value.
     * @param fromClient Whether this parameter is from a client property.
     * @param annotations The annotations that should be part of this Parameter's declaration.
     */
    private ClientMethodParameter(String description, boolean isFinal, IType wireType, IType rawType, String name,
        boolean isRequired, boolean isConstant, boolean fromClient, String defaultValue, List<ClassType> annotations,
        RequestParameterLocation location) {
        super(description, wireType, rawType, wireType.getClientType(), name, location, isConstant, isRequired,
            fromClient, defaultValue);
        this.isFinal = isFinal;
        this.annotations = annotations;
    }

    public final boolean isFinal() {
        return isFinal;
    }

    public final List<ClassType> getAnnotations() {
        return annotations;
    }

    /**
     * Creates a builder that is initialized with all the builder properties set to current values of this instance.
     * @return A new builder instance initialized with properties values of this instance.
     */
    public ClientMethodParameter.Builder toNewBuilder() {
        return new ClientMethodParameter.Builder()
                .fromClient(this.isFromClient())
                .annotations(this.getAnnotations())
                .defaultValue(this.getDefaultValue())
                .constant(this.isConstant())
                .description(this.getDescription())
                .name(this.getName())
                .finalParameter(this.isFinal())
                .required(this.isRequired())
                .location(this.getRequestParameterLocation())
                .rawType(this.getRawType())
                .wireType(this.getWireType());
    }


    /**
     * The full declaration of this parameter as it appears in a method signature.
     */
    public final String getDeclaration() {
        return getAnnotations().stream().map(annotation -> "@" + annotation.getName()).collect(Collectors.joining(""))
            + (isFinal() ? "final " : "") + String.format("%1$s %2$s", getClientType(), getName());
    }

    /**
     * Add this parameter's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether to include imports that are only necessary for method implementations.
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
         * Sets whether this parameter is final.
         * @param isFinal whether this parameter is final
         * @return the Builder itself
         */
        public Builder finalParameter(boolean isFinal) {
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
         * Sets the raw type of this parameter. Result of SchemaMapper.
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
         * Sets whether this parameter is required.
         * @param isRequired whether this parameter is required
         * @return the Builder itself
         */
        public Builder required(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }

        /**
         * Sets whether this parameter has a constant value.
         * @param isConstant whether this parameter has a constant value
         * @return the Builder itself
         */
        public Builder constant(boolean isConstant) {
            this.isConstant = isConstant;
            return this;
        }

        /**
         * Sets whether this parameter is from a client property.
         * @param fromClient whether this parameter is from a client property
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
