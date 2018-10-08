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
    /// <summary>
    /// The model used by the template to generate Java Interface representing 'Groupable Resource' such an interface is called "Groupable Resource Interface".
    /// Groupable Resource: Represents an Azure resource that appear immediately under Resource Group and is a Tracked Resource [see Utils.IsTrackedResource(param)]
    /// A Java class that implements "Groupable Resource Interface" is called "Groupable Resource Implementation".
    /// 
    /// A Groupable Resource Interface extends from:
    ///     https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/resources/models/GroupableResourceCore.java
    /// </summary>
    public class ClientFluentGroupableModelInterface : CreatableUpdatableModel
    {
        /// <summary>
        /// Creates ClientFluentGroupableModelInterface instance.
        /// </summary>
        /// <param name="standardModel">The standard model describing Groupable Resource</param>
        public ClientFluentGroupableModelInterface(StandardModel standardModel) : 
            base(new GroupableModelMemberVariablesForCreate(standardModel.FluentMethodGroup), 
                new GroupableModelMemberVariablesForUpdate(standardModel.FluentMethodGroup), 
                new GroupableModelMemberVariablesForGet(standardModel.FluentMethodGroup), 
                standardModel)
        {
            if (standardModel.Type != StanardModelType.GroupableTopLevel)
            {
                throw new ArgumentException($"Expected 'StanardModelType.GroupableTopLevel' received {standardModel.Type}");
            }
        }

        private ClientFluentGroupableModelImpl impl;
        /// <summary>
        /// Retruns the model used to generate "Groupable Resource Implementation".
        /// </summary>
        public ClientFluentGroupableModelImpl Impl
        {
            get
            {
                if (impl == null)
                {
                    this.impl = new ClientFluentGroupableModelImpl(this);
                }
                return this.impl;
            }
        }

        /// <summary>
        /// Return true if the Groupable Resource is creatable, false otherwise.
        /// </summary>
        public override bool SupportsCreating
        {
            get
            {
                return this.FluentMethodGroup.ResourceCreateDescription.SupportsCreating
                    && this.FluentMethodGroup.ResourceCreateDescription.CreateType == CreateType.WithResourceGroupAsParent;
            }
        }

        /// <summary>
        /// Return true if the Groupable Resource is updatable, false otherwise.
        /// </summary>
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

        /// <summary>
        /// Return true if Groupable Resource can be retrieved, false otherwise.
        /// </summary>
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

        /// <summary>
        /// Return true if retrieving list of Groupable Resources are supported 
        /// </summary>
        private bool SupportsListing
        {
            get
            {
                return this.FluentMethodGroup.ResourceListingDescription.SupportsListByResourceGroup;
            }
        }

        /// <summary>
        /// The metadata of Inner Model used as payload to create the Groupable Resource in Azure.
        /// e.g. The Groupable Resource interface 'VirtualMachine' represents Azure VM, the payload model used to create it is 'VirtualMachineInner'
        ///          VirtualMachinesInner::createOrUpdateAsync(string resourceGroupName, string name, VirtualMachineInner virtualMachineInner)
        ///      The Groupable Resource interface 'StorageAccount' represents Azure Storage account, the payload model used to create it is 'StorageAccountCreateParameter'
        ///          StorageAccountsInner::createAsync(string resourceGroupName, string name, StorageAccountCreateParameter createParameter)
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
        /// The metadata of Inner Model used as payload to update the Groupable Resource in Azure.
        /// e.g. The Groupable Resource interface 'VirtualMachine' represents Azure VM, the payload model used to update it is 'VirtualMachineInner'
        ///          VirtualMachinesInner::createOrUpdateAsync(string resourceGroupName, string name, VirtualMachineInner virtualMachineInner)
        ///      The Groupable Resource interface 'StorageAccount' represents Azure VM, the payload model used to update it is 'StorageAccountUpdateParameter'
        ///          StorageAccountsInner::updateAsync(string resourceGroupName, string name, StorageAccountUpdateParameter updateParameter)
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
        /// The interfaces that the Groupable Resource Interface extends from.
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
        /// All the imports needed in the Groupable Resource Java interface.
        /// e.g. All the imports in the 'VirtualMachine.java' interface.
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

        /// <summary>
        /// The interfaces from which "Definition" Java nested interface extends from.
        /// 
        /// If a Groupable Resource is Creatable then such an interface contains nested interfaces.
        /// Each interface represents a stage of the resource defintion/creation.
        /// Each interface exposes methods that take resource Create method parameter.
        /// 
        /// An interface named "Definition" extends from all the defintion stage interfaces those
        /// exposes methods to take resource Create method Required parameter.
        /// </summary>
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

        /// <summary>
        /// The interfaces from which "WithCreate" Java nested interface extends from.
        /// 
        /// If a Groupable Resource is Creatable then such an interface contains nested interfaces.
        /// Each interface represents a stage of the resource defintion/creation.
        /// Each interface exposes methods that take resource Create method parameter.
        /// 
        /// An interface named "WithCreate" extends from all the defintion stage interfaces those
        /// exposes methods to take resource Create method Optional parameter.
        /// </summary>
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

        /// <summary>
        /// The name of definition stage interface that follows the 'WithResourceGroup' definition stage interface.
        /// By "follows" we mean the return type of methods in 'WithResourceGroup' definition stage interface.
        /// </summary>
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

        /// <summary>
        /// The interfaces from which "Update" Java nested interface extends from.
        /// 
        /// If a Groupable Resource is Update then such an interface contains nested interfaces.
        /// Each the interface represents a stage of the resource update.
        /// Each interface exposes methods that take resource Update method parameter.
        /// 
        /// An interface named "Update" extends from all the update stage interfaces.
        /// </summary>
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

        /// <summary>
        /// The name of the properties of ARMTrackedResource.
        /// Definition of TrackedResource: https://github.com/Azure/azure-rest-api-specs/blob/64266364a9517d82448d09622b70ff753a9fbaa9/specification/common-types/resource-management/v1/types.json#L45
        /// </summary>
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
