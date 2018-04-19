// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ReadOnlyFluentModelInterface
    {
        private readonly FluentModel rawFluentModel;
        private ReadOnlyFluentModelImpl impl;

        public ReadOnlyFluentModelInterface(FluentModel rawFluentModel, string managerTypeName)
        {
            this.rawFluentModel = rawFluentModel;
            this.ManagerTypeName = managerTypeName;
        }

        public string JavaInterfaceName
        {
            get
            {
                return this.rawFluentModel.JavaInterfaceName;
            }
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


        public HashSet<string> LocalPropertiesImports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                string thisPackage = this.Package;
                foreach (PropertyJvaf property in this.LocalProperties)
                {
                    var propertyImports = Utils.PropertyImports(property, InnerModel.Package);
                    imports.AddRange(propertyImports);
                }
                return imports;
            }
        }

        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    $"com.microsoft.azure.management.resources.fluentcore.model.HasInner",
                    $"com.microsoft.azure.management.resources.fluentcore.arm.models.HasManager",
                    $"{this.Package}.implementation.{this.ManagerTypeName}", // import "T" in HasManager<T>
                    $"{this.Package}.implementation.{InnerModel.Name}", // import "T" in HasInner<T>
                };
                imports.AddRange(LocalPropertiesImports);
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

        public CompositeTypeJvaf InnerModel
        {
            get
            {
                return this.rawFluentModel.InnerModel;
            }
        }

        public string Package
        {
            get
            {
                if (InnerModel.Package.EndsWith(".implementation"))
                {
                    return InnerModel.Package.Substring(0, InnerModel.Package.Length - 15);
                }
                else
                {
                    return InnerModel.Package;
                }
            }
        }

        /// <summary>
        /// The properties exposed by the readonly model interface.
        /// </summary>
        public IEnumerable<Property> LocalProperties
        {
            get
            {
                CompositeTypeJvaf innerModel = this.InnerModel;
                return innerModel.ComposedProperties
                       .OrderBy(p => p.Name.ToLowerInvariant());
            }
        }
    }
}
