// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A model that is defined by the service.
    /// </summary>
    public class ClientModel
    {
        /// <summary>
        /// Create a new ServiceModel with the provided properties.
        /// </summary>
        /// <param name="package">The package that this model class belongs to.</param>
        /// <param name="name">The name of this model.</param>
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
        public ClientModel(string package, string name, IEnumerable<string> imports, string description, bool isPolymorphic, string polymorphicDiscriminator, string serializedName, bool needsFlatten, ClientModel parentModel, IEnumerable<ClientModel> derivedModels, string xmlName, IEnumerable<ServiceModelProperty> properties)
        {
            Package = package;
            Name = name;
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
        /// The package that this model class belongs to.
        /// </summary>
        public string Package { get; }
        
        /// <summary>
        /// Get the name of this model.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// The full name of this model class (package and name).
        /// </summary>
        public string FullName => $"{Package}.{Name}";

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
        public ClientModel ParentModel { get; }

        /// <summary>
        /// Get the models that derive from this model.
        /// </summary>
        public IEnumerable<ClientModel> DerivedModels { get; }

        /// <summary>
        /// Get the name that will be used for this model's XML element representation.
        /// </summary>
        public string XmlName { get; }

        /// <summary>
        /// Get the properties for this model.
        /// </summary>
        public IEnumerable<ServiceModelProperty> Properties { get; }

        /// <summary>
        /// Add this ServiceModel's imports to the provided ISet of imports.
        /// </summary>
        /// <param name="imports">The set of imports to add to.</param>
        /// <param name="settings">The settings for this Java generator session.</param>
        public void AddImportsTo(ISet<string> imports, JavaSettings settings)
        {
            foreach (string import in Imports)
            {
                imports.Add(import);
            }

            if (ParentModel != null && settings.IsAzureOrFluent)
            {
                if (ParentModel.Name == ClassType.Resource.Name)
                {
                    ClassType.Resource.AddImportsTo(imports, false);
                }
                else if (ParentModel.Name == ClassType.SubResource.Name)
                {
                    ClassType.SubResource.AddImportsTo(imports, false);
                }
                else
                {
                    imports.Add(ParentModel.FullName);
                }
            }

            if (IsPolymorphic)
            {
                imports.Add("com.fasterxml.jackson.annotation.JsonTypeInfo");
                imports.Add("com.fasterxml.jackson.annotation.JsonTypeName");

                if (DerivedModels != null && DerivedModels.Any())
                {
                    imports.Add("com.fasterxml.jackson.annotation.JsonSubTypes");
                }
            }

            foreach (ServiceModelProperty property in Properties)
            {
                property.AddImportsTo(imports, settings);
            }
        }
    }
}
