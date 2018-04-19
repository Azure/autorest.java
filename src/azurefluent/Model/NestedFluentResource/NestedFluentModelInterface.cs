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
    /// <summary>
    /// Represents interface-metadata model that can generate a model interface
    /// that represents standard model of a nested method group.
    /// </summary>
    public class NestedFluentModelInterface : CreatableUpdatableModel
    {
        private readonly FluentModel rawFluentModel;
        private readonly string package = Settings.Instance.Namespace.ToLower();

        private NestedFluentModelImpl impl;

        public NestedFluentModelInterface(FluentModel rawFluentModel, FluentMethodGroup fluentMethodGroup) : 
            base(fluentMethodGroup, 
                new NestedFluentModelMemberVariablesForCreate(fluentMethodGroup), 
                new NestedFluentModelMemberVariablesForUpdate(fluentMethodGroup), 
                new NestedFluentModelMemberVariablesForGet(fluentMethodGroup), 
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

        public NestedFluentModelImpl Impl
        {
            get
            {
                if (impl == null)
                {
                    this.impl = new NestedFluentModelImpl(this);
                }
                return this.impl;
            }
        }

        public override bool SupportsCreating
        {
            get
            {
                return this.FluentMethodGroup.ResourceCreateDescription.SupportsCreating
                    && this.FluentMethodGroup.ResourceCreateDescription.CreateType == CreateType.AsNestedChild;
            }
        }

        protected override bool UpdateSupported
        {
            get
            {
                return this.FluentMethodGroup.ResourceUpdateDescription.SupportsUpdating
                    && this.FluentMethodGroup.ResourceUpdateDescription.UpdateType == UpdateType.AsNestedChild;
            }
        }

        public override bool SupportsGetting
        {
            get
            {
                return this.FluentMethodGroup.ResourceGetDescription.SupportsGetByImmediateParent;
            }
        }

        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    "com.microsoft.azure.management.resources.fluentcore.model.HasInner",
                    $"{InnerModel.Package}.{InnerModel.Name}", // import "T" in HasInner<T>
                };
                if (this.IsCreatableOrUpdatable || this.SupportsRefreshing)
                {
                    // extending from CreatableUpdatableImpl, IndexableRefreshableImpl requires model
                    // interface to implement Indexable hence import indexable
                    //
                    imports.Add("com.microsoft.azure.management.resources.fluentcore.model.Indexable");
                }

                if (this.SupportsRefreshing)
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
                }

                imports.Add("com.microsoft.azure.management.resources.fluentcore.arm.models.HasManager");
                imports.Add($"{this.package}.implementation.{this.FluentMethodGroup.ManagerTypeName}");

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
                    // interface to implement Indexable
                    //
                    extends.Add("Indexable");
                }

                if (this.SupportsRefreshing)
                {
                    extends.Add($"Refreshable<{this.JavaInterfaceName}>");
                }

                if (this.SupportsUpdating)
                {
                    extends.Add($"Updatable<{this.JavaInterfaceName}.Update>");
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

        public CompositeTypeJvaf InnerModel
        {
            get
            {
                return this.rawFluentModel.InnerModel;
            }
        }

        public override IEnumerable<Property> LocalProperties
        {
            get
            {
                CompositeTypeJvaf innerModel = this.InnerModel;
                return innerModel.ComposedProperties
                       .OrderBy(p => p.Name.ToLowerInvariant());
            }
        }

        public static IEqualityComparer<NestedFluentModelInterface> EqualityComparer()
        {
            return new NFMComparerBasedOnJvaInterfaceName();
        }
    }

    class NFMComparerBasedOnJvaInterfaceName : IEqualityComparer<NestedFluentModelInterface>
    {
        public bool Equals(NestedFluentModelInterface x, NestedFluentModelInterface y)
        {
            return x.JavaInterfaceName.EqualsIgnoreCase(y.JavaInterfaceName);
        }

        public int GetHashCode(NestedFluentModelInterface obj)
        {
            return obj.JavaInterfaceName.GetHashCode();
        }
    }
}
