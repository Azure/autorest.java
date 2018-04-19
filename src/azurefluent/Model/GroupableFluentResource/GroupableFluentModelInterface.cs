// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Azure.Model;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class GroupableFluentModelInterface : CreatableUpdatableModel
    {
        private readonly FluentModel rawFluentModel;
        private GroupableFluentModelImpl impl;

        private readonly string package = Settings.Instance.Namespace.ToLower();

        public GroupableFluentModelInterface(FluentModel rawFluentModel, FluentMethodGroup fluentMethodGroup) : 
            base(fluentMethodGroup, 
                new GroupableFluentModelMemberVariablesForCreate(fluentMethodGroup), 
                new GroupableFluentModelMemberVariablesForUpdate(fluentMethodGroup), 
                new GroupableFluentModelMemberVariablesForGet(fluentMethodGroup), 
                rawFluentModel.InnerModel.Name)
        {
            this.rawFluentModel = rawFluentModel;
        }

        public string JavaInterfaceName
        {
            get
            {
                return this.rawFluentModel.JavaInterfaceName;
            }
        }

        public GroupableFluentModelImpl Impl
        {
            get
            {
                if (impl == null)
                {
                    this.impl = new GroupableFluentModelImpl(this);
                }
                return this.impl;
            }
        }

        public override bool SupportsCreating
        {
            get
            {
                return this.FluentMethodGroup.ResourceCreateDescription.SupportsCreating
                    && this.FluentMethodGroup.ResourceCreateDescription.CreateType == CreateType.WithResourceGroupAsParent;
            }
        }

        protected override bool UpdateSupported
        {
            get
            {
                return this.FluentMethodGroup.ResourceUpdateDescription.SupportsUpdating
                    && this.FluentMethodGroup.ResourceUpdateDescription.UpdateType == UpdateType.WithResourceGroupAsParent;
            }
        }

        public override bool SupportsGetting
        {
            get
            {
                return this.FluentMethodGroup.ResourceGetDescription.SupportsGetByResourceGroup;
            }
        }

        private bool SupportsListing
        {
            get
            {
                return this.FluentMethodGroup.ResourceListingDescription.SupportsListByResourceGroup;
            }
        }

        /// <summary>
        /// The metadata of inner model that the groupable model interface wraps.
        /// </summary>
        public CompositeTypeJvaf InnerModel
        {
            get
            {
                return this.rawFluentModel.InnerModel;
            }
        }

        /// <summary>
        /// The metadata of inner model used as palyload to create the azure resource that groupable interface wraps.
        /// </summary>
        public CompositeTypeJvaf CreatePayloadInnerModel
        {
            get
            {
                if (this.SupportsCreating)
                {
                    FluentMethod createMethod = this.FluentMethodGroup.ResourceCreateDescription.CreateMethod;
                    if (createMethod.InnerMethod.Body is ParameterJv parameter)
                    {
                        if (parameter.ClientType is CompositeTypeJvaf compositeType)
                        {
                            return compositeType;
                        }
                        else
                        {
                            throw new InvalidOperationException("Unable to derive the inner create payload");
                        }
                    }
                    else
                    {
                        throw new InvalidOperationException("Unable to derive the inner create payload");
                    }
                }
                else
                {
                    return null;
                }
            }
        }

        /// <summary>
        /// The metadata of inner model used as palyload to update the azure resource that groupable interface wraps.
        /// </summary>
        public CompositeTypeJvaf UpdatePayloadInnerModel
        {
            get
            {
                if (this.SupportsUpdating)
                {
                    FluentMethod updateMethod = this.FluentMethodGroup.ResourceUpdateDescription.UpdateMethod;
                    if (updateMethod.InnerMethod.Body is ParameterJv parameter)
                    {
                        if (parameter.ClientType is CompositeTypeJvaf compositeType)
                        {
                            return compositeType;
                        }
                        else
                        {
                            throw new InvalidOperationException("Unable to derive the inner update payload");
                        }
                    }
                    else
                    {
                        throw new InvalidOperationException("Unable to derive the inner update payload");
                    }
                }
                else
                {
                    return null;
                }
            }
        }

        /// <summary>
        /// The interfaces that the groupable model interface extends from.
        /// </summary>
        public string ExtendsFrom
        {
            get
            {
                List<string> extends = new List<string>
                {
                    $"HasInner<{this.InnerModel.Name}>",
                    $"Resource",
                    $"GroupableResource<{this.FluentMethodGroup.ManagerTypeName}, {this.InnerModel.Name}>",
                    $"HasResourceGroup"
                };

                if (this.SupportsGetting)
                {
                    extends.Add($"Refreshable<{this.rawFluentModel.JavaInterfaceName}>");
                }

                if (this.SupportsUpdating)
                {
                    extends.Add($"Updatable<{this.rawFluentModel.JavaInterfaceName}.Update>");
                }

                extends.Add($"HasManager<{this.FluentMethodGroup.ManagerTypeName}>");

                if (extends.Count() > 0)
                {
                    return $" extends {String.Join(", ", extends)}";
                }

                else
                {
                    return String.Empty;
                }
            }
        }

        /// <summary>
        /// All the imports needs by the groupable resource interface.
        /// </summary>
        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    "com.microsoft.azure.management.resources.fluentcore.model.HasInner",
                    "com.microsoft.azure.management.resources.fluentcore.arm.models.Resource",
                    "com.microsoft.azure.management.resources.fluentcore.arm.models.HasResourceGroup",
                };
                if (this.SupportsGetting)
                {
                    imports.Add("com.microsoft.azure.management.resources.fluentcore.model.Refreshable");
                }

                if (this.SupportsUpdating)
                {
                    imports.AddRange(this.FluentMethodGroup.ResourceUpdateDescription.ImportsForModelInterface);
                }

                if (this.SupportsCreating)
                {
                    imports.AddRange(this.FluentMethodGroup.ResourceCreateDescription.ImportsForModelInterface);
                    imports.Add("com.microsoft.azure.management.resources.fluentcore.arm.models.GroupableResource"); // GroupableResource.DefinitionWithRegion<WithGroup>
                    imports.Add("com.microsoft.azure.management.resources.fluentcore.arm.models.Resource");          // Resource.DefinitionWithTags<WithCreate>
                }

                imports.Add("com.microsoft.azure.management.resources.fluentcore.arm.models.HasManager");
                imports.Add($"{this.package}.implementation.{this.FluentMethodGroup.ManagerTypeName}");

                imports.AddRange(this.ImportsForInterface);
                imports.Add($"{this.package}.implementation.{InnerModel.Name}");

                return imports;
            }
        }

        public string DefinitionExtendsFrom
        {
            get
            {
                if (this.SupportsCreating)
                {
                    List<string> extends = new List<string>
                    {
                        "DefinitionStages.Blank",
                        "DefinitionStages.WithGroup"
                    };
                    foreach (FluentDefinitionOrUpdateStage stage in this.RequiredDefinitionStages)
                    {
                        extends.Add($"DefinitionStages.{stage.Name}");
                    }
                    extends.Add("DefinitionStages.WithCreate");

                    if (extends.Count > 0)
                    {
                        return $" extends {String.Join(", ", extends)}";
                    }
                    else
                    {
                        return String.Empty;
                    }
                }
                else
                {
                    return String.Empty;
                }
            }
        }

        public string WithCreateExtendsFrom
        {
            get
            {
                if (this.SupportsCreating)
                {
                    List<string> extends = new List<string>
                    {
                        $"Creatable<{this.JavaInterfaceName}>",
                        "Resource.DefinitionWithTags<WithCreate>"
                    };
                    foreach (FluentDefinitionOrUpdateStage stage in this.OptionalDefinitionStages)
                    {
                        extends.Add($"DefinitionStages.{stage.Name}");
                    }
                    return $" extends {String.Join(", ", extends)}";
                }
                else
                {
                    return String.Empty;
                }
            }
        }

        public string StageAfterResourceGroup
        {
            get
            {
                if (this.SupportsCreating)
                {
                    var defRequiredStages = this.RequiredDefinitionStages;
                    if (defRequiredStages.Count > 0)
                    {
                        return defRequiredStages.First().Name;
                    }
                    else
                    {
                        return "WithCreate";
                    }
                }
                else
                {
                    return String.Empty;
                }
            }
        }

        public string UpdateExtendsFrom
        {
            get
            {
                if (this.SupportsUpdating)
                {
                    List<string> extends = new List<string>
                    {
                        $"Appliable<{this.JavaInterfaceName}>",
                        "Resource.UpdateWithTags<Update>"
                    };
                    foreach (FluentDefinitionOrUpdateStage stage in this.UpdateStages)
                    {
                        extends.Add($"UpdateStages.{stage.Name}");
                    }

                    if (extends.Count > 0)
                    {
                        return $" extends {String.Join(", ", extends)}";
                    }
                    else
                    {
                        return String.Empty;
                    }
                }
                else
                {
                    return String.Empty;
                }
            }
        }

        public override IEnumerable<Property> LocalProperties
        {
            get
            {
                CompositeTypeJvaf innerModel = this.InnerModel;
                return innerModel.ComposedProperties
                       .OrderBy(p => p.Name.ToLowerInvariant())
                       .Where(p => !ARMTrackedResourceProperties.Contains(p.Name.ToString(), StringComparer.OrdinalIgnoreCase));
            }
        }

        private static List<string> ARMTrackedResourceProperties
        {
            get
            {
                return new List<string>
                {
                    "id",
                    "type",
                    "name",
                    "location",
                    "tags"
                };
            }
        }

        public static IEqualityComparer<GroupableFluentModelInterface> EqualityComparer()
        {
            return new GFMComparerBasedOnJvaInterfaceName();
        }

        public static HashSet<string>  PropertyImports(PropertyJvaf property, string innerModelPackage)
        {
            HashSet<string> imports = new HashSet<string>();
            var propertyImports = property.Imports;
            // var propertyImports = property.Imports.Where(import => !import.EqualsIgnoreCase(thisPackage));
            //
            string modelTypeName = property.ModelTypeName;
            if (property.ModelType is SequenceTypeJva)
            {
                var modelType = property.ModelType;
                while (modelType is SequenceTypeJva)
                {
                    SequenceTypeJva sequenceType = (SequenceTypeJva)modelType;
                    modelType = sequenceType.ElementType;
                }
                modelTypeName = modelType.ClassName;
            }
            if (modelTypeName.EndsWith("Inner"))
            {
                imports.Add($"{innerModelPackage}.{modelTypeName}");
            }
            imports.AddRange(propertyImports);
            return imports;
        }
    }

    class GFMComparerBasedOnJvaInterfaceName : IEqualityComparer<GroupableFluentModelInterface>
    {
        public bool Equals(GroupableFluentModelInterface x, GroupableFluentModelInterface y)
        {
            return x.JavaInterfaceName.EqualsIgnoreCase(y.JavaInterfaceName);
        }

        public int GetHashCode(GroupableFluentModelInterface obj)
        {
            return obj.JavaInterfaceName.GetHashCode();
        }
    }
}
