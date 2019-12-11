package com.azure.autorest.model.clientmodel;

import com.azure.autorest.util.CodeNamer;

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
     * Whether or not this property was flattened.
     */
    private boolean wasFlattened;
    /**
     * The prefix of the headers that make up this property's values.
     */
    private String headerCollectionPrefix;

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
     * @param headerCollectionPrefix The prefix of the headers that make up this property's values.
     */
    public ClientModelProperty(String name, String description, String annotationArguments, boolean isXmlAttribute, String xmlName, String serializedName, boolean isXmlWrapper, String xmlListElementName, IType wireType, IType clientType, boolean isConstant, String defaultValue, boolean isReadOnly, boolean wasFlattened, String headerCollectionPrefix) {
        this.name = name;
        this.description = description;
        this.annotationArguments = annotationArguments;
        this.isXmlAttribute = isXmlAttribute;
        this.xmlName = xmlName;
        this.serializedName = serializedName;
        this.isXmlWrapper = isXmlWrapper;
        this.xmlListElementName = xmlListElementName;
        this.wireType = wireType;
        this.clientType = clientType;
        this.isConstant = isConstant;
        this.defaultValue = defaultValue;
        this.isReadOnly = isReadOnly;
        this.wasFlattened = wasFlattened;
        this.headerCollectionPrefix = headerCollectionPrefix;
    }

    public final String getName() {
        return name;
    }

    public final String getGetterName() {
        String prefix = "get";
        if (clientType == PrimitiveType.Boolean || clientType == ClassType.Boolean) {
            prefix = "is";
            if (CodeNamer.toCamelCase(getName()).startsWith(prefix)) {
                return CodeNamer.toCamelCase(getName());
            }
        }
        return prefix + CodeNamer.toPascalCase(getName());
    }

    public final String getSetterName() {
        return "set" + CodeNamer.toPascalCase(getName());
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

    public final boolean getWasFlattened() {
        return wasFlattened;
    }

    public final String getHeaderCollectionPrefix() {
        return headerCollectionPrefix;
    }

    /**
     * Add this ServiceModelProperty's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     */
    public final void addImportsTo(Set<String> imports, boolean shouldGenerateXmlSerialization) {
        if (getHeaderCollectionPrefix() != null && !getHeaderCollectionPrefix().isEmpty()) {
            imports.add("com.azure.core.annotation.HeaderCollection");
        }

        getWireType().addImportsTo(imports, false);
        getClientType().addImportsTo(imports, false);

        if (getClientType().equals(ArrayType.ByteArray)) {
            imports.add("com.azure.core.util.CoreUtils");
        }

        if (getWasFlattened()) {
            imports.add("com.azure.core.annotation.JsonFlatten");
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
}