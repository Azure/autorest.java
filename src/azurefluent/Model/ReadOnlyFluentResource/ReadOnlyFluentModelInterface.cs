// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ReadOnlyFluentModelInterface
    {
        protected readonly string package = Settings.Instance.Namespace.ToLower();
        //
        private readonly FluentModel fluentModel;
        private readonly FluentMethodGroups fluentMethodGroups;
        private ReadOnlyFluentModelImpl impl;

        public ReadOnlyFluentModelInterface(FluentModel fluentModel, FluentMethodGroups fluentMethodGroups, string managerTypeName)
        {
            this.fluentModel = fluentModel;
            this.fluentMethodGroups = fluentMethodGroups;
            this.ManagerTypeName = managerTypeName;
        }

        public ReadOnlyFluentModelImpl Impl
        {
            get
            {
                if (impl == null)
                {
                    this.impl = new ReadOnlyFluentModelImpl(this);
                }
                return this.impl;
            }
        }

        public string ManagerTypeName { get; private set; }

        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    $"com.microsoft.azure.arm.model.HasInner",
                    $"com.microsoft.azure.arm.resources.models.HasManager",
                    $"{this.Package}.implementation.{this.ManagerTypeName}", // import "T" in HasManager<T>
                    $"{this.Package}.implementation.{InnerModel.Name}", // import "T" in HasInner<T>
                };
                imports.AddRange(this.ModelLocalProperties.ImportsForModelInterface);
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
                    $"HasManager<{this.ManagerTypeName}>"
                };

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

        public string JavaClassName
        {
            get
            {
                return this.fluentModel.JavaClassName;
            }
        }

        public string JavaInterfaceName
        {
            get
            {
                return this.fluentModel.JavaInterfaceName;
            }
        }

        public string InnerModelName
        {
            get
            {
                return this.fluentModel.InnerModelName;
            }
        }

        public WrapExistingModelFunc WrapExistingModelFunc
        {
            get
            {
                return this.fluentModel.WrapExistingModelFunc;
            }
        }

        public string CtrInvocationForWrappingExistingInnerModel
        {
            get
            {
                return this.fluentModel.CtrInvocationForWrappingExistingInnerModel;
            }
        }

        public CompositeTypeJvaf InnerModel
        {
            get
            {
                return this.fluentModel.InnerModel;
            }
        }

        public string Package
        {
            get
            {
                return package;
            }
        }

        private ModelLocalProperties modelLocalProperties;
        public ModelLocalProperties ModelLocalProperties
        {
            get
            {
                if (modelLocalProperties == null)
                {
                    CompositeTypeJvaf innerModel = this.InnerModel;
                    this.modelLocalProperties = new ModelLocalProperties(innerModel.ComposedProperties.OrderBy(p => p.Name.ToLowerInvariant()), 
                        this.fluentMethodGroups, 
                        false);
                }
                return this.modelLocalProperties;
            }
        }
    }
}
