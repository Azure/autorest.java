// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class NonGroupableTopLevelFluentModelInterface : CreatableUpdatableModel
    {
        private NonGroupableTopLevelFluentModelImpl impl;

        public NonGroupableTopLevelFluentModelInterface(StandardModel standardModel) : 
            base(new NonGroupableTopLevelFluentModelMemberVariablesForCreate(standardModel.FluentMethodGroup), 
                new NonGroupableTopLevelFluentModelMemberVariablesForUpdate(standardModel.FluentMethodGroup), 
                new NonGroupableTopLevelFluentModelMemberVariablesForGet(standardModel.FluentMethodGroup), 
                standardModel)
        {
            if (standardModel.Type != StanardModelType.NonGroupableTopLevel)
            {
                throw new ArgumentException($"Expected 'StanardModelType.NonGroupableTopLevel' received {standardModel.Type}");
            }
        }

        public NonGroupableTopLevelFluentModelImpl Impl
        {
            get
            {
                if (impl == null)
                {
                    this.impl = new NonGroupableTopLevelFluentModelImpl(this);
                }
                return this.impl;
            }
        }

        public override bool SupportsCreating
        {
            get
            {
                return this.FluentMethodGroup.ResourceCreateDescription.SupportsCreating;
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
                    return this.FluentMethodGroup.ResourceUpdateDescription.SupportsUpdating;
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
                    return this.FluentMethodGroup.ResourceGetDescription.SupportsGetByResourceGroup ||
                    this.FluentMethodGroup.ResourceGetDescription.SupportsGetBySubscription ||
                    this.FluentMethodGroup.ResourceGetDescription.SupportsGetByParameterizedParent;
                }
            }
        }

        public FluentMethod GetMethod
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
                    return $" extends {String.Join(", ", extends)}";
                }
                else
                {
                    return String.Empty;
                }
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
                    return "extends WithCreate";
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
