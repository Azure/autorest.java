package com.azure.autorest.model.clientmodel;

import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 A property that exists within a model defined by the client.
*/
public class ClientModelProperty
{
    /**
     Create a new ClientModelProperty with the provided properties.

     @param name The name of this property.
     @param description The description of this property.
     @param annotationArguments The arguments that go into this property's JsonProperty annotation.
     @param isXmlAttribute Whether or not this property is an attribute when serialized to XML.
     @param xmlName This property's name when serialized to XML.
     @param serializedName This property's name when it is serialized.
     @param isXmlWrapper Whether or not this property is a container.
     @param xmlListElementName The name of each list element tag within an XML list property.
     @param wireType The type of this property as it is transmitted across the network (across the wire).
     @param clientType The type of this property as it will be exposed via the client.
     @param isConstant Whether or not this property has a constant value.
     @param defaultValue The default value expression of this property.
     @param isReadOnly Whether or not this property's value can be changed by the client library.
     @param headerCollectionPrefix The prefix of the headers that make up this property's values.
    */
    public ClientModelProperty(String name, String description, String annotationArguments, boolean isXmlAttribute, String xmlName, String serializedName, boolean isXmlWrapper, String xmlListElementName, IType wireType, IType clientType, boolean isConstant, String defaultValue, boolean isReadOnly, boolean wasFlattened, String headerCollectionPrefix)
    {
        Name = name;
        Description = description;
        AnnotationArguments = annotationArguments;
        IsXmlAttribute = isXmlAttribute;
        XmlName = xmlName;
        SerializedName = serializedName;
        IsXmlWrapper = isXmlWrapper;
        XmlListElementName = xmlListElementName;
        WireType = wireType;
        ClientType = clientType;
        IsConstant = isConstant;
        DefaultValue = defaultValue;
        IsReadOnly = isReadOnly;
        WasFlattened = wasFlattened;
        HeaderCollectionPrefix = headerCollectionPrefix;
    }

    /**
     Get the name of this property.
    */
    private String Name;
    public final String getName()
    {
        return Name;
    }

    /**
     Get the description of this property.
    */
    private String Description;
    public final String getDescription()
    {
        return Description;
    }

    /**
     Get the arguments that go into this property's JsonProperty annotation.
    */
    private String AnnotationArguments;
    public final String getAnnotationArguments()
    {
        return AnnotationArguments;
    }

    /**
     Get whether or not this property is an attribute when serialized to XML.
    */
    private boolean IsXmlAttribute;
    public final boolean getIsXmlAttribute()
    {
        return IsXmlAttribute;
    }

    /**
     Get this property's name when serialized to XML.
    */
    private String XmlName;
    public final String getXmlName()
    {
        return XmlName;
    }

    /**
     Get this property's name when it is serialized.
    */
    private String SerializedName;
    public final String getSerializedName()
    {
        return SerializedName;
    }

    /**
     Get whether or not this property is a container.
    */
    private boolean IsXmlWrapper;
    public final boolean getIsXmlWrapper()
    {
        return IsXmlWrapper;
    }

    /**
     The name of each list element tag within an XML list property.
    */
    private String XmlListElementName;
    public final String getXmlListElementName()
    {
        return XmlListElementName;
    }

    /**
     The type of this property as it is transmitted across the network (across the wire).
    */
    private IType WireType;
    public final IType getWireType()
    {
        return WireType;
    }

    /**
     The type of this property as it will be exposed via the client.
    */
    private IType ClientType;
    public final IType getClientType()
    {
        return ClientType;
    }

    /**
     Get whether or not this property has a constant value.
    */
    private boolean IsConstant;
    public final boolean getIsConstant()
    {
        return IsConstant;
    }

    /**
     Get the default value expression of this property.
    */
    private String DefaultValue;
    public final String getDefaultValue()
    {
        return DefaultValue;
    }

    /**
     Get whether or not this property's value can be changed by the client library.
    */
    private boolean IsReadOnly;
    public final boolean getIsReadOnly()
    {
        return IsReadOnly;
    }

    /**
     Whether or not this property was flattened.
    */
    private boolean WasFlattened;
    public final boolean getWasFlattened()
    {
        return WasFlattened;
    }

    /**
     The prefix of the headers that make up this property's values.
    */
    private String HeaderCollectionPrefix;
    public final String getHeaderCollectionPrefix()
    {
        return HeaderCollectionPrefix;
    }

    /**
     Add this ServiceModelProperty's imports to the provided ISet of imports.

     @param imports The set of imports to add to.
    */
    public final void AddImportsTo(Set<String> imports, boolean shouldGenerateXmlSerialization)
    {
        if (getHeaderCollectionPrefix() != null && !getHeaderCollectionPrefix().isEmpty())
        {
            imports.add("com.azure.core.implementation.annotation.HeaderCollection");
        }

        getWireType().AddImportsTo(imports, false);
        getClientType().AddImportsTo(imports, false);

        if (getClientType().equals(ArrayType.ByteArray))
        {
            imports.add("com.azure.core.implementation.util.ImplUtils");
        }

        if (getWasFlattened())
        {
            imports.add("com.azure.core.implementation.annotation.JsonFlatten");
        }

        if (shouldGenerateXmlSerialization)
        {
            imports.add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement");
            if (getIsXmlWrapper())
            {
                imports.add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty");
            }
        }
        else
        {
            imports.add("com.fasterxml.jackson.annotation.JsonProperty");
        }
    }
}