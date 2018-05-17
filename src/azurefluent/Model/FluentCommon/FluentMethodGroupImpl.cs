// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public abstract class FluentMethodGroupImpl
    {
        protected readonly string package = Settings.Instance.Namespace.ToLower();

        private readonly IFluentModel model;
        protected IFluentModel Model
        {
            get
            {
                if (this.model == null)
                {
                    throw new NotSupportedException("MethodGroup does not have a model associated");
                }
                else
                {
                    return this.model;
                }
            }
        }

        public IFluentMethodGroup Interface { get; private set; }

        protected FluentMethodGroupImpl(IFluentModel fluentModel)
        {
            this.model = fluentModel;
            this.Interface = fluentModel.FluentMethodGroup;
        }

        protected FluentMethodGroupImpl(FluentMethodGroup fluentMethodGroup)
        {
            this.model = null;  // i.e. FMG exposes only actions and child accessors & does not wrap a model
            this.Interface = fluentMethodGroup;
        }

        public string DeclareManagerVariable
        {
            get
            {
                return $"private final {this.ManagerTypeName} manager;";
            }
        }

        protected string JvaInterfaceName
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
                return $"{this.JvaInterfaceName}Impl";
            }
        }

        protected string InnerClientName
        {
            get
            {
                return this.Interface.InnerMethodGroupTypeName;
            }
        }

        protected string InnerClientAccessorName
        {
            get
            {
                return this.Interface.InnerMethodGroupAccessorName;
            }
        }

        protected string ManagerTypeName
        {
            get
            {
                return this.Interface.ManagerName;
            }
        }

        protected string InnerMethodGroupName
        {
            get
            {
                return this.Interface.InnerMethodGroup.MethodGroupImplType;
            }
        }

        protected string ManagerGetterImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"public {this.ManagerTypeName} manager() {{");
                methodBuilder.AppendLine($"    return this.manager;");
                methodBuilder.AppendLine($"}}");
                return methodBuilder.ToString();
            }
        }

        protected IEnumerable<string> ChildMethodGroupAccessors
        {
            get
            {
                foreach (var nestedFluentMethodGroup in this.Interface.ChildFluentMethodGroups)
                {
                    StringBuilder methodBuilder = new StringBuilder();

                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public {nestedFluentMethodGroup.JavaInterfaceName} {nestedFluentMethodGroup.AccessorMethodName}() {{");
                    methodBuilder.AppendLine($"    {nestedFluentMethodGroup.JavaInterfaceName} accessor = this.manager().{nestedFluentMethodGroup.JavaInterfaceName.ToCamelCase()}();");
                    methodBuilder.AppendLine($"    return accessor;");
                    methodBuilder.AppendLine($"}}");

                    yield return methodBuilder.ToString();
                }
            }
        }

        protected string InnerMethodInvocationParameter(MethodJvaf innerMethod)
        {
            List<string> invoke = new List<string>();
            foreach (var parameter in innerMethod.LocalParameters.Where(p => !p.IsConstant && p.IsRequired))
            {
                invoke.Add(parameter.Name);
            }

            return string.Join(", ", invoke);
        }
    }
}
