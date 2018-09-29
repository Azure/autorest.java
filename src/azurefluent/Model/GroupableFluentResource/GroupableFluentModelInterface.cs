// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class GroupableFluentModelInterface : CreatableUpdatableModel
    {
        public GroupableFluentModelInterface(StandardModel standardModel) : 
            base(new GroupableFluentModelMemberVariablesForCreate(standardModel.FluentMethodGroup), 
                new GroupableFluentModelMemberVariablesForUpdate(standardModel.FluentMethodGroup), 
                new GroupableFluentModelMemberVariablesForGet(standardModel.FluentMethodGroup), 
                standardModel)
        {
            if (standardModel.Type != StanardModelType.GroupableTopLevel)
            {
                throw new ArgumentException($"Expected 'StanardModelType.GroupableTopLevel' received {standardModel.Type}");
            }
        }

        private GroupableFluentModelImpl impl;
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
                if (!this.HasArmId)
                {
                    return false;
                }
                else
                {
                    return this.FluentMethodGroup.ResourceUpdateDescription.SupportsUpdating
                        && this.FluentMethodGroup.ResourceUpdateDescription.UpdateType == UpdateType.WithResourceGroupAsParent;
                }
            }
        }

        public override bool SupportsGetting
        {
            get
            {
                if (!this.HasArmId)
                {
                    return false;
                }
                else
                {
                    return this.FluentMethodGroup.ResourceGetDescription.SupportsGetByResourceGroup;
                }
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
        /// The metadata of inner model used as palyload to create the azure resource that groupable interface wraps.
        /// </summary>
        public CompositeTypeJvaf CreatePayloadInnerModel
        {
            get
            {
                if (this.SupportsCreating)
                {
                    StandardFluentMethod createMethod = this.FluentMethodGroup.ResourceCreateDescription.CreateMethod;
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
                    StandardFluentMethod updateMethod = this.FluentMethodGroup.ResourceUpdateDescription.UpdateMethod;
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
                    $"GroupableResourceCore<{this.FluentMethodGroup.ManagerName}, {this.InnerModel.Name}>",
                    $"HasResourceGroup"
                };

                if (this.SupportsGetting)
                {
                    extends.Add($"Refreshable<{this.JavaInterfaceName}>");
                }

                if (this.SupportsUpdating)
                {
                    extends.Add($"Updatable<{this.JavaInterfaceName}.Update>");
                }

                extends.Add($"HasManager<{this.FluentMethodGroup.ManagerName}>");

                if (extends.Count() > 0)
                {
                    return $" extends {string.Join(", ", extends)}";
                }

                else
                {
                    return string.Empty;
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
                    "com.microsoft.azure.arm.model.HasInner",
                    "com.microsoft.azure.arm.resources.models.Resource",
                    "com.microsoft.azure.arm.resources.models.GroupableResourceCore",
                    "com.microsoft.azure.arm.resources.models.HasResourceGroup",
                };
                if (this.SupportsGetting)
                {
                    imports.Add("com.microsoft.azure.arm.model.Refreshable");
                }

                if (this.SupportsUpdating)
                {
                    imports.AddRange(this.FluentMethodGroup.ResourceUpdateDescription.ImportsForModelInterface);
                }

                if (this.SupportsCreating)
                {
                    imports.AddRange(this.FluentMethodGroup.ResourceCreateDescription.ImportsForModelInterface);
                }

                imports.Add("com.microsoft.azure.arm.resources.models.HasManager");
                imports.Add($"{this.package}.implementation.{this.FluentMethodGroup.ManagerName}");

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
                        return $" extends {string.Join(", ", extends)}";
                    }
                    else
                    {
                        return string.Empty;
                    }
                }
                else
                {
                    return string.Empty;
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
                    return $" extends {string.Join(", ", extends)}";
                }
                else
                {
                    return string.Empty;
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
                    return string.Empty;
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
                        return $" extends {string.Join(", ", extends)}";
                    }
                    else
                    {
                        return string.Empty;
                    }
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        protected override IEnumerable<Property> LocalProperties
        {
            get
            {
                return this.InnerModel.ComposedProperties
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
    }
}
