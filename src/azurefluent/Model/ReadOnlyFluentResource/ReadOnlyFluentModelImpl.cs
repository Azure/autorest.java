// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ReadOnlyFluentModelImpl : IFluentModel
    {
        public ReadOnlyFluentModelImpl(ReadOnlyFluentModelInterface mInterface)
        {
            this.Interface = mInterface;
        }

        public ReadOnlyFluentModelInterface Interface
        {
            get; private set;
        }

        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    $"{this.Interface.Package}.{this.Interface.JavaInterfaceName}",     // The readonly model interface
                    "com.microsoft.azure.arm.model.implementation.WrapperImpl"
                };
                imports.AddRange(this.Interface.ModelLocalProperties.ImportsForModelImpl);
                return imports;
            }
        }

        public string ExtendsFrom
        {
            get
            {
                return $" extends WrapperImpl<{this.InnerModelName}>";
            }
        }

        public string Implements
        {
            get
            {
                List<string> implements = new List<string>
                {
                    this.Interface.JavaInterfaceName
                };
                if (implements.Count() > 0)
                {
                    return $" implements {string.Join(", ", implements)}";
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        public string DeclareManagerVariable
        {
            get
            {
                return $"private final {this.Interface.ManagerTypeName} manager;";
            }
        }

        public IEnumerable<string> JavaMethods
        {
            get
            {
                yield return this.CtrImplementation;
                yield return this.ManagerGetterImplementation;
            }
        }

        private string CtrImplementation
        {
            get
            {
                //
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"{this.JavaClassName}({this.InnerModelName} inner, {this.Interface.ManagerTypeName} manager) {{");
                methodBuilder.AppendLine($"    super(inner);"); // WrapperImpl(inner)
                methodBuilder.AppendLine($"    this.manager = manager;");
                methodBuilder.AppendLine($"}}");
                return methodBuilder.ToString();
            }
        }

        /// <summary>
        /// Implement HasManager<T> interface.
        /// </summary>
        private string ManagerGetterImplementation
        {
            get
            {
                string managerTypeName = this.Interface.ManagerTypeName;
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"@Override");
                methodBuilder.AppendLine($"public {managerTypeName} manager() {{");
                methodBuilder.AppendLine($"    return this.manager;");
                methodBuilder.AppendLine($"}}");
                return methodBuilder.ToString();
            }
        }

        #region IFLuentModel

        public ISegmentFluentMethodGroup FluentMethodGroup
        {
            get
            {
                throw new NotSupportedException("Readonly models are shared models and does not belong to any specific method group");
            }
        }

        public string JavaInterfaceName
        {
            get
            {
                return this.Interface.JavaInterfaceName;
            }
        }


        public string JavaClassName
        {
            get
            {
                return this.Interface.JavaClassName;
            }
        }


        public string InnerModelName
        {
            get
            {
                return this.Interface.InnerModelName;
            }
        }

        public ModelLocalProperties ModelLocalProperties
        {
            get
            {
                return this.Interface.ModelLocalProperties;
            }
        }

        public WrapExistingModelFunc WrapExistingModelFunc
        {
            get
            {
                return this.Interface.WrapExistingModelFunc;
            }
        }

        #endregion
    }
}
