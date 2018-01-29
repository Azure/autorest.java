// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A property that exists within a model defined by the service.
    /// </summary>
    public class ServiceModelProperty
    {
        /// <summary>
        /// Create a new ServiceProperty with the provided properties.
        /// </summary>
        /// <param name="name">The name of this property.</param>
        /// <param name="description">The description of this property.</param>
        /// <param name="annotationArguments">The arguments that go into this property's JsonProperty annotation.</param>
        /// <param name="isXmlAttribute">Whether or not this property is an attribute when serialized to XML.</param>
        /// <param name="xmlName">This property's name when serialized to XML.</param>
        /// <param name="serializedName">This property's name when it is serialized.</param>
        /// <param name="isXmlWrapper">Whether or not this property is a container.</param>
        /// <param name="xmlListElementName">The name of each list element tag within an XML list property.</param>
        /// <param name="wireTypeName">The name of this property's type when it is sent through the network (across the wire).</param>
        /// <param name="isConstant">Whether or not this property has a constant value.</param>
        /// <param name="modelTypeIsSequence">Whether or not this property's model type is a sequence type.</param>
        /// <param name="modelTypeIsComposite">Whether or not this property's model type is a composite type.</param>
        /// <param name="clientTypeName">The name of this property's type when it is exposed via the client library.</param>
        /// <param name="defaultValue">The default value expression of this property.</param>
        /// <param name="isReadOnly">Whether or not this property's value can be changed by the client library.</param>
        public ServiceModelProperty(string name, string description, string annotationArguments, bool isXmlAttribute, string xmlName, string serializedName, bool isXmlWrapper, string xmlListElementName, string wireTypeName, bool isConstant, bool modelTypeIsSequence, bool modelTypeIsComposite, string clientTypeName, string defaultValue, bool isReadOnly)
        {
            Name = name;
            Description = description;
            AnnotationArguments = annotationArguments;
            IsXmlAttribute = isXmlAttribute;
            XmlName = xmlName;
            SerializedName = serializedName;
            IsXmlWrapper = isXmlWrapper;
            XmlListElementName = xmlListElementName;
            WireTypeName = wireTypeName;
            IsConstant = isConstant;
            ModelTypeIsSequence = modelTypeIsSequence;
            ModelTypeIsComposite = modelTypeIsComposite;
            ClientTypeName = clientTypeName;
            DefaultValue = defaultValue;
            IsReadOnly = isReadOnly;
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
        /// Get the name of this property's type when it is sent through the network (across the wire).
        /// </summary>
        public string WireTypeName { get; }

        /// <summary>
        /// Get whether or not this property has a constant value.
        /// </summary>
        public bool IsConstant { get; }

        /// <summary>
        /// Get whether or not this property's model type is a sequence type.
        /// </summary>
        public bool ModelTypeIsSequence { get; }

        /// <summary>
        /// Get whether or not this property's model type is a composite type.
        /// </summary>
        public bool ModelTypeIsComposite { get; }

        /// <summary>
        /// Get the name of this property's type when it is exposed via the client library.
        /// </summary>
        public string ClientTypeName { get; }

        /// <summary>
        /// Get the default value expression of this property.
        /// </summary>
        public string DefaultValue { get; }

        /// <summary>
        /// Get whether or not this property's value can be changed by the client library.
        /// </summary>
        public bool IsReadOnly { get; }
    }
}
