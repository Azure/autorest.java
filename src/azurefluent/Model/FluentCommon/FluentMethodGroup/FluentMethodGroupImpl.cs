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

        private readonly StandardModel model;
        protected StandardModel Model
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

        protected FluentMethodGroupImpl(IFluentMethodGroup fluentMethodGroup)
        {
            if (fluentMethodGroup.Type != MethodGroupType.ActionsOrChildAccessorsOnly)
            {
                if (fluentMethodGroup.StandardFluentModel == null)
                {
                    throw new ArgumentNullException($"Fluent method group type is '{fluentMethodGroup.Type}' but standard model is null.");
                }
                this.model = fluentMethodGroup.StandardFluentModel;
            }
            else
            {
                this.model = null;  // i.e. FMG exposes only actions and child accessors & does not wrap a model
            }
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
        protected HashSet<string> ImportsForGeneralizedMethodImpls
        {
            get
            {
                return this.Interface.GeneralizedOutputs.SelectMany(go => go.ImportsForImpl).ToHashSet<string>();
            }
        }

        protected IEnumerable<string> GeneralizedMethodImpls
        {
            get
            {
                //
                IEnumerable<IDefineFunc> defineFuns = this.Interface.GeneralizedOutputs
                    .Select(go => go.DefineFunc)
                    .Where(def => def.IsDefineSupported);
                foreach (IDefineFunc defineFunc in defineFuns)
                {
                    yield return defineFunc.GeneralizedMethodImpl;
                }
                //
                IEnumerable<IWrapNewModelFunc> wrapNewModelFuncs = this.Interface.GeneralizedOutputs
                    .Select(go => go.WrapNewModelFunc)
                    .Where(def => def.IsWrapNewModelSupported);
                foreach (IWrapNewModelFunc wrapNewModelFunc in wrapNewModelFuncs)
                {
                    yield return wrapNewModelFunc.GeneralizedMethodImpl;
                }
                //
                IEnumerable<WrapExistingModelFunc> wraExistingModelFuncs = this.Interface.GeneralizedOutputs
                    .Select(go => go.WrapExistingModelFunc)
                    .Where(func => func != null) // Will be null if there is no standard model.
                    .Distinct(WrapExistingModelFunc.EqualityComparer());
                foreach (WrapExistingModelFunc wrapExisingModelFunc in wraExistingModelFuncs)
                {
                    yield return wrapExisingModelFunc.GeneralizedMethodImpl;
                }
                //
                IEnumerable<IGetInnerAsyncFunc> getInnerAsyncFuncs = this.Interface.GeneralizedOutputs
                    .SelectMany(go => go.GetInnerAsyncFunc);
                foreach (IGetInnerAsyncFunc getInnerAsyncFunc in getInnerAsyncFuncs)
                {
                    yield return getInnerAsyncFunc.GeneralizedMethodImpl;
                }
                //
                IEnumerable<string> methodImpls = this.Interface.GeneralizedOutputs
                    .SelectMany(go => go.MethodImpl);
                foreach (string methodImpl in methodImpls)
                {
                    yield return methodImpl;
                }
            }
        }
    }
}
