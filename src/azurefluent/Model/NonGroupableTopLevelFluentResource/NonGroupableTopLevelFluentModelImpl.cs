// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class NonGroupableTopLevelFluentModelImpl : IFluentModel
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        public NonGroupableTopLevelFluentModelImpl(NonGroupableTopLevelFluentModelInterface mInterface)
        {
            this.Interface = mInterface;
        }

        public NonGroupableTopLevelFluentModelInterface Interface
        {
            get; private set;
        }

        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    $"{this.package}.{this.Interface.JavaInterfaceName}"
                };
                if (this.Interface.IsCreatableOrUpdatable)
                {
                    imports.Add("com.microsoft.azure.arm.model.implementation.CreatableUpdatableImpl");
                }
                else if (this.IsIndexableRefreshable)
                {
                    imports.Add("com.microsoft.azure.arm.model.implementation.IndexableRefreshableWrapperImpl");
                }
                else
                {
                    imports.Add("com.microsoft.azure.arm.model.implementation.WrapperImpl");
                }

                imports.Add("rx.Observable");
                imports.AddRange(this.Interface.ImportsForImpl);

                return imports;
            }
        }

        public string ExtendsFrom
        {
            get
            {
                if (this.Interface.IsCreatableOrUpdatable)
                {
                    return $" extends CreatableUpdatableImpl<{this.Interface.JavaInterfaceName}, {this.InnerModelName}, {this.JavaClassName}>";
                }
                else if (this.IsIndexableRefreshable)
                {
                    return $" extends IndexableRefreshableWrapperImpl<{this.Interface.JavaInterfaceName}, {this.InnerModelName}>";
                }
                else
                {
                    return $" extends WrapperImpl<{this.InnerModelName}>";
                }
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
                if (this.Interface.SupportsCreating)
                {
                    implements.Add($"{this.Interface.JavaInterfaceName}.Definition");
                }
                if (this.Interface.SupportsUpdating)
                {
                    implements.Add($"{this.Interface.JavaInterfaceName}.Update");
                }
                if (implements.Count() > 0)
                {
                    return $" implements {String.Join(", ", implements)}";
                }
                else
                {
                    return String.Empty;
                }
            }
        }

        public string DeclareManagerVariable
        {
            get
            {
                return $"private final {this.Interface.FluentMethodGroup.ManagerName} manager;";
            }
        }

        public IEnumerable<string> DeclareMemberVariables
        {
            get
            {
                return this.Interface.DisambiguatedMemberVariables.DeclareMemberVariables;
            }
        }

        public IEnumerable<string> JavaMethods
        {
            get
            {
                foreach (string method in CtrImplementations)
                {
                    yield return method;
                }
                yield return this.ManagerGetterImplementation;
                foreach (string method in AbstractMethodsImplementation)
                {
                    yield return method;
                }
                yield return this.Interface.IsInCreateModeMethodImplementation;
                yield return this.Interface.ResetRquestPayloadVariablesMethodImplementation;
            }
        }

        private IEnumerable<string> CtrImplementations
        {
            get
            {
                string managerTypeName = this.Interface.FluentMethodGroup.ManagerName;
                if (this.Interface.IsCreatableOrUpdatable)
                {
                    StringBuilder methodBuilder = new StringBuilder();
                    //
                    // Ctr1 FooImpl(String name): The ctr invoked from 'Collection.define(name)'
                    //
                    methodBuilder.AppendLine($"{this.JavaClassName}(String name, {managerTypeName} manager) {{");
                    methodBuilder.AppendLine($"    super(name, new {this.InnerModelName}());");               // CreatableUpdatableImpl(name, inner)
                    methodBuilder.AppendLine($"    this.manager = manager;");
                    methodBuilder.AppendLine($"    // Set resource name");
                    methodBuilder.AppendLine($"    {MemberVariableAccessorHoldingResourceName} = name;");
                    // init create update member variables
                    //
                    methodBuilder.AppendLine($"    //");
                    foreach (string initMemberVariable in this.InitMemberVariables)
                    {
                        methodBuilder.AppendLine($"    {initMemberVariable}");
                    }
                    methodBuilder.AppendLine($"}}");
                    yield return methodBuilder.ToString();
                    methodBuilder.Clear();

                    //
                    // Ctr2 FooImpl(FooInner inner, Manager manager): The ctr invoked to wrap inner model retrieved from "Collection.Get() and Collection.List()"
                    //
                    methodBuilder.AppendLine($"{this.JavaClassName}({this.InnerModelName} inner, {managerTypeName} manager) {{");
                    methodBuilder.AppendLine($"    super(inner.name(), inner);");       // CreatableUpdatableImpl(name, inner)
                    methodBuilder.AppendLine($"    this.manager = manager;");
                    methodBuilder.AppendLine($"    // Set resource name");
                    methodBuilder.AppendLine($"    {MemberVariableAccessorHoldingResourceName} = inner.name();");
                    // Init member variables
                    methodBuilder.AppendLine($"    // set resource ancestor and positional variables");
                    foreach (string initVariable in InitParentRefAndPosMemberVariablesFromId)
                    {
                        methodBuilder.AppendLine($"    {initVariable}");
                    }
                    methodBuilder.AppendLine($"    // set other parameters for create and update");
                    // init create update member variables
                    foreach (string initMemberVariable in this.InitMemberVariables)
                    {
                        methodBuilder.AppendLine($"    {initMemberVariable}");
                    }
                    //
                    methodBuilder.AppendLine($"}}");
                    //
                    yield return methodBuilder.ToString();
                }
                else if (this.IsIndexableRefreshable)
                {
                    StringBuilder methodBuilder = new StringBuilder();
                    methodBuilder.AppendLine($"{this.JavaClassName}({this.InnerModelName} inner,  {managerTypeName} manager) {{");
                    methodBuilder.AppendLine($"    super(null, inner);"); // IndexableRefreshableWrapperImpl(key, inner)
                    methodBuilder.AppendLine($"    this.manager = manager;");
                    // Init member variables
                    methodBuilder.AppendLine($"    // set resource ancestor and positional variables");
                    foreach (string initVariable in InitParentRefAndPosMemberVariablesFromId)
                    {
                        methodBuilder.AppendLine($"    {initVariable}");
                    }
                    //
                    methodBuilder.AppendLine($"}}");
                    //
                    yield return methodBuilder.ToString();
                }
                else
                {
                    StringBuilder methodBuilder = new StringBuilder();
                    methodBuilder.AppendLine($"{this.JavaClassName}({this.InnerModelName} inner,  {managerTypeName} manager) {{");
                    methodBuilder.AppendLine($"    super(inner);"); // WrapperImpl(inner)
                    methodBuilder.AppendLine($"    this.manager = manager;");
                    methodBuilder.AppendLine($"}}");
                    //
                    yield return methodBuilder.ToString();
                }
            }
        }

        private string MemberVariableAccessorHoldingResourceName
        {
            get
            {
                return this.Interface.DisambiguatedMemberVariables.MemberVariableAccessorHoldingResourceName;
            }
        }

        private IEnumerable<string> InitMemberVariables
        {
            get
            {
                return this.Interface.DisambiguatedMemberVariables.InitMemberVariables;
            }
        }

        private IEnumerable<string> InitParentRefAndPosMemberVariablesFromId
        {
            get
            {
                return this.Interface.DisambiguatedMemberVariables.InitParentRefAndPosMemberVariablesFromId("inner.id()");
            }
        }

        private string ManagerGetterImplementation
        {
            get
            {
                string managerTypeName = this.Interface.FluentMethodGroup.ManagerName;
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"@Override");
                methodBuilder.AppendLine($"public {managerTypeName} manager() {{");
                methodBuilder.AppendLine($"    return this.manager;");
                methodBuilder.AppendLine($"}}");
                return methodBuilder.ToString();
            }
        }

        private IEnumerable<string> AbstractMethodsImplementation
        {
            get
            {
                if (this.Interface.IsCreatableOrUpdatable)
                {
                    foreach (string method in CreatableUpdatableAbstractMethodsImplementation)
                    {
                        yield return method;
                    }
                }
                else if (this.IsIndexableRefreshable)
                {
                    yield return IndexableRefreshableAbstractMethodImplementation;
                }
            }
        }

        private IEnumerable<string> CreatableUpdatableAbstractMethodsImplementation
        {
            get
            {
                yield return this.CreateResourceAsyncMethodImplementation;
                yield return this.UpdateResourceAsyncMethodImplementation;
                yield return this.GetInnerAsyncMethodImplementation;
            }
        }

        private string CreateResourceAsyncMethodImplementation
        {
            get
            {
                if (!this.Interface.SupportsCreating)
                {
                    return this.Interface.CreateResourceAsyncNOPMethodImplementation(this.Interface.JavaInterfaceName,
                        Interface.FluentMethodGroup.InnerMethodGroup.MethodGroupImplType);
                }
                else
                {
                    StandardFluentMethod createMethod = this.Interface.FluentMethodGroup.ResourceCreateDescription.CreateMethod;

                    var createMethodParameters = this.Interface.DisambiguatedMemberVariables.MemeberVariablesForCreate
                                                    .Values
                                                    .OrderBy(v => v.IndexInMethod)
                                                    .Select(v => v.VariableAccessor);
                    var createMethodParametersCombined = string.Join(", ", createMethodParameters);

                    return this.Interface.CreateResourceAsyncMethodImplementation(createMethod,
                        createMethodParametersCombined,
                        this.Interface.JavaInterfaceName,
                        Interface.FluentMethodGroup.InnerMethodGroup.MethodGroupImplType);
                }
            }
        }

        private string UpdateResourceAsyncMethodImplementation
        {
            get
            {
                if (!this.Interface.SupportsUpdating)
                {
                    return this.Interface.UpdateResourceAsyncNOPMethodImplementation(this.Interface.JavaInterfaceName,
                        Interface.FluentMethodGroup.InnerMethodGroup.MethodGroupImplType);
                }
                else
                {
                    StandardFluentMethod updateMethod = this.Interface.FluentMethodGroup.ResourceUpdateDescription.UpdateMethod;

                    var updateMethodParameters = this.Interface.DisambiguatedMemberVariables.MemeberVariablesForUpdate
                                                    .Values
                                                    .OrderBy(v => v.IndexInMethod)
                                                    .Select(v => v.VariableAccessor);
                    var updateMethodParametersCombined = String.Join(", ", updateMethodParameters);

                    return this.Interface.UpdateResourceAsyncMethodImplementation(updateMethod,
                        updateMethodParametersCombined,
                        this.Interface.JavaInterfaceName,
                        Interface.FluentMethodGroup.InnerMethodGroup.MethodGroupImplType);
                }
            }
        }

        private string GetInnerAsyncMethodImplementation
        {
            get
            {
                if (!this.Interface.SupportsGetting)
                {
                    return this.Interface.GetInnerAsyncNOPMethodImplementation(Interface.FluentMethodGroup.InnerMethodGroup.MethodGroupImplType);
                }
                else
                {
                    StandardFluentMethod getMethod = this.Interface.GetMethod;

                    var getMethodParameters = this.Interface.DisambiguatedMemberVariables.MemeberVariablesForGet
                        .OrderBy(v => v.IndexInMethod)
                        .Select(v => v.VariableAccessor);

                    var getMethodParametersCombined = String.Join(", ", getMethodParameters);

                    return this.Interface.GetInnerAsyncMethodImplementation(getMethod,
                        getMethodParametersCombined,
                        Interface.FluentMethodGroup.InnerMethodGroup.MethodGroupImplType);
                }
            }
        }

        private string IndexableRefreshableAbstractMethodImplementation
        {
            get
            {
                return GetInnerAsyncMethodImplementation;
            }
        }

        private bool IsIndexableRefreshable
        {
            get
            {
                return this.Interface.SupportsRefreshing;
            }
        }

        #region IFluentModel

        public IFluentMethodGroup FluentMethodGroup
        {
            get
            {
                return this.Interface.FluentMethodGroup;
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
