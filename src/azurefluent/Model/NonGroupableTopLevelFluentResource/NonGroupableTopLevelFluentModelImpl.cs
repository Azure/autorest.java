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

        public IEnumerable<string> DeclareMemberVariables
        {
            get
            {
                return this.Interface.DisambiguatedMemberVariables
                    .MemberVariables
                    .Select(m => m.VariableDeclaration);
            }
        }

        public IEnumerable<string> InitMemberVariables
        {
            get
            {
                return this.Interface.DisambiguatedMemberVariables
                    .MemberVariables
                    .Select(m => m.VariableInitialize)
                    .Where(d => !string.IsNullOrEmpty(d));
            }
        }

        public IEnumerable<string> InitParentRefAndPosMemberVariablesFromId
        {
            get
            {
                if (this.RequiresIdParsing)
                {
                    var parentVars = this.Interface.DisambiguatedMemberVariables.MemberVariables.OfType<FluentModelParentRefMemberVariable>();
                    foreach (var parentVar in parentVars)
                    {
                        yield return $"{parentVar.VariableAccessor} = IdParsingUtils.getValueFromIdByName(inner.id(), \"{parentVar.ParentRefName}\");";
                    }

                    var posVars = this.Interface.DisambiguatedMemberVariables.MemberVariables.OfType<FluentModelPositionalPathMemberVariable>();
                    foreach (var posVar in posVars)
                    {
                        yield return $"{posVar.VariableAccessor} = IdParsingUtils.getValueFromIdByPosition(inner.id(), {posVar.Position});";
                    }
                }
                else
                {
                    yield break;
                }
            }
        }

        private bool RequiresIdParsing
        {
            get
            {
                return this.Interface.SupportsRefreshing || this.Interface.SupportsUpdating;
            }
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

        private string MemberVariableAccessorHoldingResourceName
        {
            get
            {
                return this.Interface.DisambiguatedMemberVariables
                    .MemberVariables
                    .OfType<FluentModelParentRefMemberVariable>()
                    .OrderBy(v => v.Index)  // TODO: Find a better way to derive resource param
                    .Select(v => v.VariableAccessor)
                    .Last();
            }
        }

        private IEnumerable<string> CtrImplementations
        {
            get
            {
                string managerTypeName = this.Interface.FluentMethodGroup.ManagerTypeName;
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
                    methodBuilder.AppendLine($"    // resource ancestor names");
                    var parentVars = this.Interface.DisambiguatedMemberVariables.MemberVariables.OfType<FluentModelParentRefMemberVariable>();
                    foreach (var parentVar in parentVars)
                    {
                        methodBuilder.AppendLine($"    {parentVar.VariableAccessor} = IdParsingUtils.getValueFromIdByName(inner.id(), \"{parentVar.ParentRefName}\");");
                    }
                    var posVars = this.Interface.DisambiguatedMemberVariables.MemberVariables.OfType<FluentModelPositionalPathMemberVariable>();
                    foreach (var posVar in posVars)
                    {
                        methodBuilder.AppendLine($"    {posVar.VariableAccessor} = IdParsingUtils.getValueFromIdByPosition(inner.id(), {posVar.Position});");
                    }
                    methodBuilder.AppendLine($"    //");
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
                    //
                    var parentVars = this.Interface.DisambiguatedMemberVariables.MemberVariables.OfType<FluentModelParentRefMemberVariable>();
                    foreach (var parentVar in parentVars)
                    {
                        methodBuilder.AppendLine($"    {parentVar.VariableAccessor} = IdParsingUtils.getValueFromIdByName(inner.id(), \"{parentVar.ParentRefName}\");");
                    }
                    var posVars = this.Interface.DisambiguatedMemberVariables.MemberVariables.OfType<FluentModelPositionalPathMemberVariable>();
                    foreach (var posVar in posVars)
                    {
                        methodBuilder.AppendLine($"    {posVar.VariableAccessor} = IdParsingUtils.getValueFromIdByPosition(inner.id(), {posVar.Position});");
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

        public string DeclareManagerVariable
        {
            get
            {
                return $"private final {this.Interface.FluentMethodGroup.ManagerTypeName} manager;";
            }
        }

        private string ManagerGetterImplementation
        {
            get
            {
                string managerTypeName = this.Interface.FluentMethodGroup.ManagerTypeName;
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
                    FluentMethod createMethod = this.Interface.FluentMethodGroup.ResourceCreateDescription.CreateMethod;

                    var createMethodParameters = this.Interface.DisambiguatedMemberVariables.MemeberVariablesForCreate
                                                    .Values
                                                    .OrderBy(v => v.Index)
                                                    .Select(v => v.VariableAccessor);
                    var createMethodParametersCombined = String.Join(", ", createMethodParameters);

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
                    FluentMethod updateMethod = this.Interface.FluentMethodGroup.ResourceUpdateDescription.UpdateMethod;

                    var updateMethodParameters = this.Interface.DisambiguatedMemberVariables.MemeberVariablesForUpdate
                                                    .Values
                                                    .OrderBy(v => v.Index)
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
                    FluentMethod getMethod = this.Interface.GetMethod;

                    var getMethodParameters = this.Interface.DisambiguatedMemberVariables.MemeberVariablesForGet
                        .OrderBy(v => v.Index)
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

        public FluentMethodGroup FluentMethodGroup
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
                return $"{this.JavaInterfaceName}Impl";
            }
        }

        public string InnerModelName
        {
            get
            {
                return this.Interface.InnerModel.ClassName;
            }
        }

        public string CtrInvocationForWrappingExistingInnerModel
        {
            get
            {
                return $" new {this.JavaClassName}(inner, manager());";
            }
        }

        public string CtrInvocationForWrappingNewInnerModel
        {
            get
            {
                if (this.Interface.SupportsCreating)
                {
                    return $"new {this.JavaClassName}(name, this.manager());";
                }
                else
                {
                    return String.Empty;
                }
            }
        }

        #endregion
    }
}
