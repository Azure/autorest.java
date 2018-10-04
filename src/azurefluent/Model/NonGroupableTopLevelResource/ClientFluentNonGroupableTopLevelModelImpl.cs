// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The model used by the template to generate Java class (aka Non-Groupable Toplevel Resource Implementation) that implements "Non-Groupable Toplevel Resource Interface" and
    /// it's "Nested Defintion & Update Stage Interfaces".
    /// 
    /// Non Groupable TopLevel Resource: Represents an Azure resource that is at level 0 and is Not a Tracked Resource [see Utils.IsTrackedResource(param)]
    /// An interface representing Non-Groupable Toplevel Resource is known as "Non-Groupable Toplevel Resource Interface".
    /// 
    /// 'Non-Groupable TopLevel Resource Implementation' extends from one of the following depending on it's capability:
    ///     If Creatable/Updatable: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/CreatableUpdatableImpl.java
    ///     If Retrievable: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/IndexableRefreshableWrapperImpl.java
    ///     Otherwise: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/WrapperImpl.java
    /// </summary>
    public class ClientFluentNonGroupableTopLevelModelImpl : IFluentModel
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        /// <summary>
        /// Creates ClientFluentGroupableModelImpl.
        /// </summary>
        /// <param name="mInterface">model describing "Non-Groupable Toplevel Resource Interface"</param>
        public ClientFluentNonGroupableTopLevelModelImpl(ClientFluentNonGroupableTopLevelModelInterface mInterface)
        {
            this.Interface = mInterface;
        }

        /// <summary>
        /// Gets the model describing "Non-Groupable Toplevel Resource Resource Interface" whose implementation this model describes.
        /// </summary>
        public ClientFluentNonGroupableTopLevelModelInterface Interface
        {
            get; private set;
        }

        /// <summary>
        /// Gets the imports to be imported in Non-Groupable Toplevel Resource Implementation.
        /// </summary>
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

        /// <summary>
        /// Gets the class that Non-Groupable Toplevel Resource Implementation extends from.
        /// </summary>
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

        /// <summary>
        /// Gets comma list of interfaces that Non-Groupable Toplevel Resource Resource Implementation implements.
        /// </summary>
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
                    return $" implements {string.Join(", ", implements)}";
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        /// <summary>
        /// Retrun a string containing declaration of manager memeber variable.
        /// </summary>
        public string DeclareManagerVariable
        {
            get
            {
                return $"private final {this.Interface.FluentMethodGroup.ManagerName} manager;";
            }
        }

        /// <summary>
        /// Gets a list of string, each one represents declaration of member variable in Non-Groupable Toplevel Resource Implementation.
        ///  e.g: private FooCreateParameters createParameters;
        /// </summary>
        public IEnumerable<string> DeclareMemberVariables
        {
            get
            {
                return this.Interface.DisambiguatedMemberVariables.DeclareMemberVariables;
            }
        }

        /// <summary>
        /// Gets a list of string, each one represents initialization of member variable in Non-Groupable Toplevel Resource Implementation.
        /// e.g: this.createParameters = new FooCreateParameters();
        /// </summary>
        private IEnumerable<string> InitMemberVariables
        {
            get
            {
                return this.Interface.DisambiguatedMemberVariables.InitMemberVariables;
            }
        }

        /// <summary>
        /// Initialize the member variables corrosponding to ancestors and positional parameters from the resource id.
        /// </summary>
        private IEnumerable<string> InitParentRefAndPosMemberVariablesFromId
        {
            get
            {
                return this.Interface.DisambiguatedMemberVariables.InitParentRefAndPosMemberVariablesFromId("inner.id()");
            }
        }

        /// <summary>
        /// Gets a list of string, each string contains defintion of a method in Non-Groupable Toplevel Resource Implementation.
        /// </summary>
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

        /// <summary>
        /// Returns a list of strings, each string contains implementation of Non-Groupable Toplevel Resource constructor.
        /// </summary>
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

        /// <summary>
        /// Gets the name of the member variable of Non-Groupable Toplevel Resource impl that holds resource name.
        /// </summary>
        private string MemberVariableAccessorHoldingResourceName
        {
            get
            {
                return this.Interface.DisambiguatedMemberVariables.MemberVariableAccessorHoldingResourceName;
            }
        }

        /// <summary>
        /// Gets string containing Java getter that returns manager 
        /// </summary>
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

        /// <summary>
        /// Non-Groupable Toplevel Resource Implementation inherits chain has one of the following types in the hierarchy.
        /// Non-Groupable Toplevel Resource Impl -> CreatableUpdatableImpl -> IndexableRefreshableWrapperImpl
        /// Non-Groupable Toplevel Resource Impl -> IndexableRefreshableWrapperImpl.
        /// Non-Groupable Toplevel Resource Impl -> WrapperImpl.
        /// 
        ///   https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/CreatableUpdatableImpl.java
        ///   https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/IndexableRefreshableWrapperImpl.java
        /// 
        /// This method returns implementation of abstract methods in the base classes.
        /// </summary>
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

        /// <summary>
        /// Gets implementation of abstract methods in CreatableUpdatableImpl
        /// https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/CreatableUpdatableImpl.java
        /// </summary>
        private IEnumerable<string> CreatableUpdatableAbstractMethodsImplementation
        {
            get
            {
                yield return this.CreateResourceAsyncMethodImplementation;
                yield return this.UpdateResourceAsyncMethodImplementation;
                yield return this.GetInnerAsyncMethodImplementation;
            }
        }

        /// <summary>
        /// Gets implementation of CreatableUpdatableImpl::createResourceAsync(params).
        /// https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/CreatableUpdatableImpl.java
        /// </summary>
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

        /// <summary>
        /// Gets implementation of CreatableUpdatableImpl::updateResourceAsync(params).
        /// https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/CreatableUpdatableImpl.java
        /// </summary>
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
                    var updateMethodParametersCombined = string.Join(", ", updateMethodParameters);

                    return this.Interface.UpdateResourceAsyncMethodImplementation(updateMethod,
                        updateMethodParametersCombined,
                        this.Interface.JavaInterfaceName,
                        Interface.FluentMethodGroup.InnerMethodGroup.MethodGroupImplType);
                }
            }
        }

        /// <summary>
        /// Implementation of abstract methods in IndexableRefreshableWrapperImpl
        /// https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/IndexableRefreshableWrapperImpl.java
        /// </summary>
        private string IndexableRefreshableAbstractMethodImplementation
        {
            get
            {
                return GetInnerAsyncMethodImplementation;
            }
        }

        /// <summary>
        /// Gets implementation of IndexableRefreshableWrapperImpl::getInnerAsync(params).
        /// https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/IndexableRefreshableWrapperImpl.java
        /// </summary>
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

                    var getMethodParametersCombined = string.Join(", ", getMethodParameters);

                    return this.Interface.GetInnerAsyncMethodImplementation(getMethod,
                        getMethodParametersCombined,
                        Interface.FluentMethodGroup.InnerMethodGroup.MethodGroupImplType);
                }
            }
        }

        /// <summary>
        /// Checks if the Non-Groupable Toplevel Resource is Retrievable.
        /// </summary>
        private bool IsIndexableRefreshable
        {
            get
            {
                return this.Interface.SupportsRefreshing;
            }
        }

        #region IFluentModel

        public ISegmentFluentMethodGroup FluentMethodGroup
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
