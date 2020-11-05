package com.azure.autorest.model.clientmodel;

import com.azure.autorest.util.CodeNamer;

import java.util.List;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * A property that exists within a model defined by the client.
 */
public class ClientModelProperty {
    /**
     * Get the name of this property.
     */
    private String name;
    /**
     * Get the description of this property.
     */
    private String description;
    /**
     * Get the arguments that go into this property's JsonProperty annotation.
     */
    private String annotationArguments;
    /**
     * Get whether or not this property is an attribute when serialized to XML.
     */
    private boolean isXmlAttribute;
    /**
     * Get this property's name when serialized to XML.
     */
    private String xmlName;
    private final String xmlNamespace;
    /**
     * Get this property's name when it is serialized.
     */
    private String serializedName;
    /**
     * Get whether or not this property is a container.
     */
    private boolean isXmlWrapper;
    /**
     * The name of each list element tag within an XML list property.
     */
    private String xmlListElementName;
    /**
     * The type of this property as it is transmitted across the network (across the wire).
     */
    private IType wireType;
    /**
     * The type of this property as it will be exposed via the client.
     */
    private IType clientType;
    /**
     * Get whether or not this property has a constant value.
     */
    private boolean isConstant;
    /**
     * Get the default value expression of this property.
     */
    private String defaultValue;
    /**
     * Get whether or not this property's value can be changed by the client library.
     */
    private boolean isReadOnly;
    /**
     * Whether or not this property is required.
     */
    private boolean isRequired;
    /**
     * The prefix of the headers that make up this property's values.
     */
    private String headerCollectionPrefix;

    private boolean isAdditionalProperties;

    private List<Mutability> mutabilities;

    /**
     * Create a new ClientModelProperty with the provided properties.
     * @param name The name of this property.
     * @param description The description of this property.
     * @param annotationArguments The arguments that go into this property's JsonProperty annotation.
     * @param isXmlAttribute Whether or not this property is an attribute when serialized to XML.
     * @param xmlName This property's name when serialized to XML.
     * @param serializedName This property's name when it is serialized.
     * @param isXmlWrapper Whether or not this property is a container.
     * @param xmlListElementName The name of each list element tag within an XML list property.
     * @param wireType The type of this property as it is transmitted across the network (across the wire).
     * @param clientType The type of this property as it will be exposed via the client.
     * @param isConstant Whether or not this property has a constant value.
     * @param defaultValue The default value expression of this property.
     * @param isReadOnly Whether or not this property's value can be changed by the client library.
     * @param mutabilities List of property mutability.
     * @param headerCollectionPrefix The prefix of the headers that make up this property's values.
     * @param isAdditionalProperties Whether or not this property contain the additional properties.
     */
    private ClientModelProperty(String name, String description, String annotationArguments, boolean isXmlAttribute,
            String xmlName, String xmlNamespace, String serializedName, boolean isXmlWrapper, String xmlListElementName,
            IType wireType, IType clientType, boolean isConstant, String defaultValue, boolean isReadOnly, List<Mutability> mutabilities,
            boolean isRequired, String headerCollectionPrefix, boolean isAdditionalProperties) {
        this.name = name;
        this.description = description;
        this.annotationArguments = annotationArguments;
        this.isXmlAttribute = isXmlAttribute;
        this.xmlName = xmlName;
        this.xmlNamespace = xmlNamespace;
        this.serializedName = serializedName;
        this.isXmlWrapper = isXmlWrapper;
        this.xmlListElementName = xmlListElementName;
        this.wireType = wireType;
        this.clientType = clientType;
        this.isConstant = isConstant;
        this.defaultValue = defaultValue;
        this.isReadOnly = isReadOnly;
        this.mutabilities = mutabilities;
        this.isRequired = isRequired;
        this.headerCollectionPrefix = headerCollectionPrefix;
        this.isAdditionalProperties = isAdditionalProperties;
    }

    public final String getName() {
        return name;
    }

    public final String getGetterName() {
        return CodeNamer.getModelNamer().modelPropertyGetterName(this);
    }

    public final String getSetterName() {
        return CodeNamer.getModelNamer().modelPropertySetterName(this);
    }

    public final String getDescription() {
        return description;
    }

    public final String getAnnotationArguments() {
        return annotationArguments;
    }

    public final boolean getIsXmlAttribute() {
        return isXmlAttribute;
    }

    public final String getXmlName() {
        return xmlName;
    }

    public String getXmlNamespace() {
        return xmlNamespace;
    }

    public final String getSerializedName() {
        return serializedName;
    }

    public final boolean getIsXmlWrapper() {
        return isXmlWrapper;
    }

    public final String getXmlListElementName() {
        return xmlListElementName;
    }

    public final IType getWireType() {
        return wireType;
    }

    public final IType getClientType() {
        return clientType;
    }

    public final boolean getIsConstant() {
        return isConstant;
    }

    public final String getDefaultValue() {
        return defaultValue;
    }

    public final boolean getIsReadOnly() {
        return isReadOnly;
    }

    public final boolean getIsReadOnlyForCreate() {
        return isReadOnly || (this.getMutabilities() != null && !this.getMutabilities().contains(Mutability.CREATE));
    }

    public final boolean getIsReadOnlyForUpdate() {
        return isReadOnly || (this.getMutabilities() != null && !this.getMutabilities().contains(Mutability.UPDATE));
    }

    public final String getHeaderCollectionPrefix() {
        return headerCollectionPrefix;
    }

    /**
     * @return List of property mutability.
     */
    public List<Mutability> getMutabilities() {
        return mutabilities;
    }

    /**
     * Add this ServiceModelProperty's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     */
    public final void addImportsTo(Set<String> imports, boolean shouldGenerateXmlSerialization) {
        if (getHeaderCollectionPrefix() != null && !getHeaderCollectionPrefix().isEmpty()) {
            imports.add("com.azure.core.annotation.HeaderCollection");
        }
        if (isAdditionalProperties) {
            imports.add("com.fasterxml.jackson.annotation.JsonIgnore");
            imports.add("com.fasterxml.jackson.annotation.JsonAnySetter");
            imports.add("com.fasterxml.jackson.annotation.JsonAnyGetter");
            imports.add("java.util.HashMap");
        }

        if (getWireType() != null) {
            getWireType().addImportsTo(imports, false);
        }
        getClientType().addImportsTo(imports, false);

        if (getClientType().equals(ArrayType.ByteArray)) {
            imports.add("com.azure.core.util.CoreUtils");
        }

        if (shouldGenerateXmlSerialization) {
            imports.add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement");
            if (getIsXmlWrapper()) {
                imports.add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty");
            }
        } else {
            imports.add("com.fasterxml.jackson.annotation.JsonProperty");
        }
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public boolean isAdditionalProperties() {
        return isAdditionalProperties;
    }

    public enum Mutability {
        CREATE, UPDATE, READ
    }

    public static class Builder {
        private String name;
        private String description;
        private String annotationArguments;
        private boolean isXmlAttribute;
        private String xmlName;
        private String serializedName;
        private boolean isXmlWrapper;
        private String xmlListElementName;
        private IType wireType;
        private IType clientType;
        private boolean isConstant = false;
        private String defaultValue;
        private boolean isReadOnly;
        private boolean isRequired;
        private String headerCollectionPrefix;
        private boolean isAdditionalProperties = false;
        private String xmlNamespace;
        private List<Mutability> mutabilities;

        /**
         * Sets the name of this property.
         * @param name the name of this property
         * @return the Builder itself
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the description of this property.
         * @param description the description of this property
         * @return the Builder itself
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the arguments that go into this property's JsonProperty annotation.
         * @param annotationArguments the arguments that go into this property's JsonProperty annotation
         * @return the Builder itself
         */
        public Builder annotationArguments(String annotationArguments) {
            this.annotationArguments = annotationArguments;
            return this;
        }

        /**
         * Sets whether or not this property is an attribute when serialized to XML.
         * @param isXmlAttribute whether or not this property is an attribute when serialized to XML
         * @return the Builder itself
         */
        public Builder isXmlAttribute(boolean isXmlAttribute) {
            this.isXmlAttribute = isXmlAttribute;
            return this;
        }

        /**
         * Sets this property's name when serialized to XML.
         * @param xmlName this property's name when serialized to XML
         * @return the Builder itself
         */
        public Builder xmlName(String xmlName) {
            this.xmlName = xmlName;
            return this;
        }

        /**
         * Sets this property's namespace when serialized to XML.
         * @param xmlNamespace this property's namespace when serialized to XML
         * @return the Builder itself
         */
        public Builder xmlNamespace(String xmlNamespace) {
            this.xmlNamespace = xmlNamespace;
            return this;
        }

        /**
         * Sets this property's name when it is serialized.
         * @param serializedName this property's name when it is serialized
         * @return the Builder itself
         */
        public Builder serializedName(String serializedName) {
            this.serializedName = serializedName;
            return this;
        }

        /**
         * Sets whether or not this property is a container.
         * @param isXmlWrapper whether or not this property is a container
         * @return the Builder itself
         */
        public Builder isXmlWrapper(boolean isXmlWrapper) {
            this.isXmlWrapper = isXmlWrapper;
            return this;
        }

        /**
         * Sets the name of each list element tag within an XML list property.
         * @param xmlListElementName the name of each list element tag within an XML list property
         * @return the Builder itself
         */
        public Builder xmlListElementName(String xmlListElementName) {
            this.xmlListElementName = xmlListElementName;
            return this;
        }

        /**
         * Sets the type of this property as it is transmitted across the network (across the wire).
         * @param wireType the type of this property as it is transmitted across the network (across the wire)
         * @return the Builder itself
         */
        public Builder wireType(IType wireType) {
            this.wireType = wireType;
            return this;
        }

        /**
         * Sets the type of this property as it will be exposed via the client.
         * @param clientType the type of this property as it will be exposed via the client
         * @return the Builder itself
         */
        public Builder clientType(IType clientType) {
            this.clientType = clientType;
            return this;
        }

        /**
         * Sets whether or not this property has a constant value.
         * @param isConstant whether or not this property has a constant value
         * @return the Builder itself
         */
        public Builder isConstant(boolean isConstant) {
            this.isConstant = isConstant;
            return this;
        }

        /**
         * Sets the default value expression of this property.
         * @param defaultValue the default value expression of this property
         * @return the Builder itself
         */
        public Builder defaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        /**
         * Sets whether or not this property's value can be changed by the client library.
         * @param isReadOnly whether or not this property's value can be changed by the client library
         * @return the Builder itself
         */
        public Builder isReadOnly(boolean isReadOnly) {
            this.isReadOnly = isReadOnly;
            return this;
        }

        /**
         * Sets whether or not this property is required.
         * @param isRequired whether or not this property is required
         * @return the Builder itself
         */
        public Builder isRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }

        /**
         * Sets the prefix of the headers that make up this property's values.
         * @param headerCollectionPrefix the prefix of the headers that make up this property's values
         * @return the Builder itself
         */
        public Builder headerCollectionPrefix(String headerCollectionPrefix) {
            this.headerCollectionPrefix = headerCollectionPrefix;
            return this;
        }

        /**
         * Sets whether or not this property contain the additional properties.
         * @param isAdditionalProperties whether or not this property contain the additional properties
         * @return the Builder itself
         */
        public Builder isAdditionalProperties(boolean isAdditionalProperties) {
            this.isAdditionalProperties = isAdditionalProperties;
            return this;
        }

        /**
         * Sets list of property mutability.
         * @param mutabilities list of mutability.
         * @return the Builder itself
         */
        public Builder mutabilities(List<Mutability> mutabilities) {
            this.mutabilities = mutabilities;
            return this;
        }

        public ClientModelProperty build() {
            return new ClientModelProperty(name,
                    description,
                    annotationArguments,
                    isXmlAttribute,
                    xmlName,
                    xmlNamespace,
                    serializedName,
                    isXmlWrapper,
                    xmlListElementName,
                    wireType,
                    clientType,
                    isConstant,
                    defaultValue,
                    isReadOnly,
                    mutabilities,
                    isRequired,
                    headerCollectionPrefix,
                    isAdditionalProperties);
        }
    }
}
