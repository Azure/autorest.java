// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The model used by the template to generate Java Interface representing 'Non-Groupable TopLevel Resource' such an interface is called "Non-Groupable TopLevel Resource Interface".
    /// Non-Groupable TopLevel Resource: Represents an Azure resource that is at level 0 and is Not a Tracked Resource [see Utils.IsTrackedResource(param)]
    /// A Java class that implements "Non-Groupable TopLevel Resource Interface" is called "Non-Groupable TopLevel Resource Implementation".
    /// </summary>
    public class ClientFluentNonGroupableTopLevelModelInterface : CreatableUpdatableModel
    {
        private ClientFluentNonGroupableTopLevelModelImpl impl;

        /// <summary>
        /// Creates ClientFluentNonGroupableTopLevelModelInterface instance.
        /// </summary>
        /// <param name="standardModel">The standard model describing Non-Groupable TopLevel Resource</param>
        public ClientFluentNonGroupableTopLevelModelInterface(StandardModel standardModel) : 
            base(new NonGroupableTopLevelModelMemberVariablesForCreate(standardModel.FluentMethodGroup), 
                new NonGroupableTopLevelModelMemberVariablesForUpdate(standardModel.FluentMethodGroup), 
                new NonGroupableTopLevelModelMemberVariablesForGet(standardModel.FluentMethodGroup), 
                standardModel)
        {
            if (standardModel.Type != StanardModelType.NonGroupableTopLevel)
            {
                throw new ArgumentException($"Expected 'StanardModelType.NonGroupableTopLevel' received {standardModel.Type}");
            }
        }

        /// <summary>
        /// Retruns the model used to generate "Non-Groupable TopLevel Resource Implementation".
        /// </summary>
        public ClientFluentNonGroupableTopLevelModelImpl Impl
        {
            get
            {
                if (impl == null)
                {
                    this.impl = new ClientFluentNonGroupableTopLevelModelImpl(this);
                }
                return this.impl;
            }
        }

        /// <summary>
        /// Return true if the Non-Groupable TopLevel Resource is creatable, false otherwise.
        /// </summary>
        public override bool SupportsCreating
        {
            get
            {
                return this.FluentMethodGroup.ResourceCreateDescription.SupportsCreating;
            }
        }

        /// <summary>
        /// Return true if the Non-Groupable TopLevel Resource is updatable, false otherwise.
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
                    return this.FluentMethodGroup.ResourceUpdateDescription.SupportsUpdating;
                }
            }
        }

        /// <summary>
        /// Return true if Non-Groupable TopLevel Resource can be retrieved, false otherwise.
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
                    return this.FluentMethodGroup.ResourceGetDescription.SupportsGetByResourceGroup ||
                    this.FluentMethodGroup.ResourceGetDescription.SupportsGetBySubscription ||
                    this.FluentMethodGroup.ResourceGetDescription.SupportsGetByParameterizedParent;
                }
            }
        }

        /// <summary>
        /// Return description of method to retrieve Non-Groupable TopLevel Resource.
        /// </summary>
        public StandardFluentMethod GetMethod
        {
            get
            {
                if (!this.SupportsGetting)
                {
                    return null;
                }
                else if (this.FluentMethodGroup.ResourceGetDescription.SupportsGetByResourceGroup)
                {
                    return this.FluentMethodGroup.ResourceGetDescription.GetByResourceGroupMethod;
                }
                else if (this.FluentMethodGroup.ResourceGetDescription.SupportsGetBySubscription)
                {
                    return this.FluentMethodGroup.ResourceGetDescription.GetBySubscriptionMethod;
                }
                else
                {
                    return this.FluentMethodGroup.ResourceGetDescription.GetByParameterizedParentMethod;
                }
            }
        }

        /// <summary>
        /// All the imports needed in the Non-Groupable TopLevel Resource Java interface.
        /// </summary>
        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    "com.microsoft.azure.arm.model.HasInner",
                    $"{InnerModel.Package}.{InnerModel.Name}", // import "T" in HasInner<T>
                };

                if (this.IsCreatableOrUpdatable || this.SupportsRefreshing)
                {
                    // extending from CreatableUpdatableImpl, IndexableRefreshableImpl requires model
                    // interface to extends from Indexable, so import it.
                    //
                    imports.Add($"com.microsoft.azure.arm.model.Indexable");
                }

                if (this.SupportsCreating)
                {
                    imports.AddRange(this.FluentMethodGroup.ResourceCreateDescription.ImportsForModelInterface);
                }

                if (this.SupportsUpdating)
                {
                    imports.AddRange(this.FluentMethodGroup.ResourceUpdateDescription.ImportsForModelInterface);
                }

                if (this.SupportsRefreshing)
                {
                    imports.Add("com.microsoft.azure.arm.model.Refreshable");
                }

                imports.Add("com.microsoft.azure.arm.resources.models.HasManager");
                imports.Add($"{this.package}.implementation.{this.FluentMethodGroup.ManagerName}");

                imports.AddRange(this.ImportsForInterface);

                return imports;
            }
        }

        /// <summary>
        /// The interfaces that the Non-Groupable TopLevel Resource Interface extends from.
        /// </summary>
        public string ExtendsFrom
        {
            get
            {
                List<string> extends = new List<string>
                {
                    $"HasInner<{this.InnerModel.Name}>",
                };

                if (this.IsCreatableOrUpdatable || this.SupportsRefreshing)
                {
                    // extending from CreatableUpdatableImpl, IndexableRefreshableImpl requires model
                    // interface to extends from Indexable
                    //
                    extends.Add($"Indexable");
                }

                if (this.SupportsUpdating)
                {
                    extends.Add($"Updatable<{this.JavaInterfaceName}.Update>");
                }

                if (this.SupportsRefreshing)
                {
                    extends.Add($"Refreshable<{this.JavaInterfaceName}>");
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
        /// The interfaces from which "Definition" Java nested interface extends from.
        /// 
        /// If a Non-Groupable Toplevel Resource is Creatable then such an interface contains nested interfaces.
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
        /// The interfaces from which "Blank" Java nested interface (representing the first stage) extends from.
        /// </summary>
        public string BlankExtendsFrom
        {
            get
            {
                var requiredDefStages = this.RequiredDefinitionStages;
                if (requiredDefStages.Any())
                {
                    return $" extends {requiredDefStages.First().Name}";
                }
                else
                {
                    return " extends WithCreate";
                }
            }
        }

        /// <summary>
        /// The interfaces from which "WithCreate" Java nested interface extends from.
        /// 
        /// If a Non-Groupable Toplevel Resource is Creatable then such an interface contains nested interfaces.
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
        /// The interfaces from which "Update" Java nested interface extends from.
        /// 
        /// If a Non-Groupable Toplevel Resource is Update then such an interface contains nested interfaces.
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
                       .OrderBy(p => p.Name.ToLowerInvariant());
            }
        }
    }
}
