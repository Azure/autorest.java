// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A property that exists within a model defined by the client.
    /// </summary>
    public class ClientModelProperty
    {
        /// <summary>
        /// Create a new ClientModelProperty with the provided properties.
        /// </summary>
        /// <param name="name">The name of this property.</param>
        /// <param name="description">The description of this property.</param>
        /// <param name="annotationArguments">The arguments that go into this property's JsonProperty annotation.</param>
        /// <param name="isXmlAttribute">Whether or not this property is an attribute when serialized to XML.</param>
        /// <param name="xmlName">This property's name when serialized to XML.</param>
        /// <param name="serializedName">This property's name when it is serialized.</param>
        /// <param name="isXmlWrapper">Whether or not this property is a container.</param>
        /// <param name="xmlListElementName">The name of each list element tag within an XML list property.</param>
        /// <param name="wireType">The type of this property as it is transmitted across the network (across the wire).</param>
        /// <param name="clientType">The type of this property as it will be exposed via the client.</param>
        /// <param name="isConstant">Whether or not this property has a constant value.</param>
        /// <param name="defaultValue">The default value expression of this property.</param>
        /// <param name="isReadOnly">Whether or not this property's value can be changed by the client library.</param>
        /// <param name="headerCollectionPrefix">The prefix of the headers that make up this property's values.</param>
        public ClientModelProperty(string name, string description, string annotationArguments, bool isXmlAttribute, string xmlName, string serializedName, bool isXmlWrapper, string xmlListElementName, IType wireType, IType clientType, bool isConstant, string defaultValue, bool isReadOnly, bool wasFlattened, string headerCollectionPrefix)
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

        /// <summary>
        /// Get the name of this property.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// Get the description of this property.
        /// </summary>
        public string Description { get; }

        /// <summary>
        /// Get the arguments that go into this property's JsonProperty annotation.
        /// </summary>
        public string AnnotationArguments { get; }

        /// <summary>
        /// Get whether or not this property is an attribute when serialized to XML.
        /// </summary>
        public bool IsXmlAttribute { get; }

        /// <summary>
        /// Get this property's name when serialized to XML.
        /// </summary>
        public string XmlName { get; }

        /// <summary>
        /// Get this property's name when it is serialized.
        /// </summary>
        public string SerializedName { get; }

        /// <summary>
        /// Get whether or not this property is a container.
        /// </summary>
        public bool IsXmlWrapper { get; }

        /// <summary>
        /// The name of each list element tag within an XML list property.
        /// </summary>
        public string XmlListElementName { get; }

        /// <summary>
        /// The type of this property as it is transmitted across the network (across the wire).
        /// </summary>
        public IType WireType { get; }

        /// <summary>
        /// The type of this property as it will be exposed via the client.
        /// </summary>
        public IType ClientType { get; }

        /// <summary>
        /// Get whether or not this property has a constant value.
        /// </summary>
        public bool IsConstant { get; }

        /// <summary>
        /// Get the default value expression of this property.
        /// </summary>
        public string DefaultValue { get; }

        /// <summary>
        /// Get whether or not this property's value can be changed by the client library.
        /// </summary>
        public bool IsReadOnly { get; }

        /// <summary>
        /// Whether or not this property was flattened.
        /// </summary>
        public bool WasFlattened { get; }

        /// <summary>
        /// The prefix of the headers that make up this property's values.
        /// </summary>
        public string HeaderCollectionPrefix { get; }

        /// <summary>
        /// Add this ServiceModelProperty's imports to the provided ISet of imports.
        /// </summary>
        /// <param name="imports">The set of imports to add to.</param>
        /// <param name="settings">The settings for this Java generator session.</param>
        public void AddImportsTo(ISet<string> imports, JavaSettings settings)
        {
            if (!string.IsNullOrEmpty(HeaderCollectionPrefix))
            {
                imports.Add("com.azure.common.annotations.HeaderCollection");
            }

            WireType.AddImportsTo(imports, false);
            ClientType.AddImportsTo(imports, false);

            if (WasFlattened)
            {
                imports.Add("com.azure.common.implementation.serializer.JsonFlatten");
            }

            if (settings.ShouldGenerateXmlSerialization)
            {
                imports.Add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement");
                imports.Add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty");
            }
            else
            {
                imports.Add("com.fasterxml.jackson.annotation.JsonProperty");
            }
        }
    }
}
