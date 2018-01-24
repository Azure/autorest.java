// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A model that is defined by the service.
    /// </summary>
    public class ServiceModel
    {
        /// <summary>
        /// Create a new ServiceModel with the provided properties.
        /// </summary>
        /// <param name="name">The name of this model.</param>
        /// <param name="package">The package that this model is in.</param>
        /// <param name="imports">The imports for this model.</param>
        /// <param name="description">The description of this model.</param>
        /// <param name="isPolymorphic">Whether or not this model has model types that derive from it.</param>
        /// <param name="polymorphicDiscriminator">The name of the property that determines which polymorphic model type to create.</param>
        /// <param name="serializedName">The name that is used for this model when it is serialized.</param>
        /// <param name="needsFlatten">Whether or not this model needs serialization flattening.</param>
        /// <param name="parentModel">The parent model of this model.</param>
        /// <param name="derivedModels">The models that derive from this model.</param>
        /// <param name="xmlName">The name that will be used for this model's XML element representation.</param>
        /// <param name="properties">The properties for this model.</param>
        public ServiceModel(string name, string package, IEnumerable<string> imports, string description, bool isPolymorphic, string polymorphicDiscriminator, string serializedName, bool needsFlatten, ServiceModel parentModel, IEnumerable<ServiceModel> derivedModels, string xmlName, IEnumerable<ServiceModelProperty> properties)
        {
            Name = name;
            Package = package;
            Imports = imports;
            Description = description;
            IsPolymorphic = isPolymorphic;
            PolymorphicDiscriminator = polymorphicDiscriminator;
            SerializedName = serializedName;
            NeedsFlatten = needsFlatten;
            ParentModel = parentModel;
            DerivedModels = derivedModels;
            XmlName = xmlName;
            Properties = properties;
        }

        /// <summary>
        /// Get the name of this model.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// Get the package that this model is in.
        /// </summary>
        public string Package { get; }

        /// <summary>
        /// Get the imports for this model.
        /// </summary>
        public IEnumerable<string> Imports { get; }

        /// <summary>
        /// Get the description of this model.
        /// </summary>
        public string Description { get; }

        /// <summary>
        /// Get whether or not this model has model types that derive from it.
        /// </summary>
        public bool IsPolymorphic { get; }

        /// <summary>
        /// Get the name of the property that determines which polymorphic model type to create.
        /// </summary>
        public string PolymorphicDiscriminator { get; }

        /// <summary>
        /// Get the name that is used for this model when it is serialized.
        /// </summary>
        public string SerializedName { get; }

        /// <summary>
        /// Get whether or not this model needs serialization flattening.
        /// </summary>
        public bool NeedsFlatten { get; }

        /// <summary>
        /// Get the parent model of this model.
        /// </summary>
        public ServiceModel ParentModel { get; }

        /// <summary>
        /// Get the models that derive from this model.
        /// </summary>
        public IEnumerable<ServiceModel> DerivedModels { get; }

        /// <summary>
        /// Get the name that will be used for this model's XML element representation.
        /// </summary>
        public string XmlName { get; }

        /// <summary>
        /// Get the properties for this model.
        /// </summary>
        public IEnumerable<ServiceModelProperty> Properties { get; }
    }
}
