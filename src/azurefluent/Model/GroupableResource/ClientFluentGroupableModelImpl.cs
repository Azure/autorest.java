// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Utilities;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The model used by the template to generate Java class (aka Groupable Resource Implementation) that implements "Groupable Resource Interface" and
    /// it's "Nested Defintion & Update Stage Interfaces".
    /// 
    /// Groupable Resource: Represents an Azure resource that appear immediately under Resource Group and is a Tracked Resource [see Utils.IsTrackedResource(param)].
    /// An interface representing Groupable Resource is known as "Groupable Resource Interface".
    /// 
    /// A Groupable Resource Implementation extends from:
    ///     https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/resources/models/implementation/GroupableResourceCoreImpl.java
    /// 
    /// </summary>
    public class ClientFluentGroupableModelImpl : IFluentModel
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        /// <summary>
        /// Creates ClientFluentGroupableModelImpl.
        /// </summary>
        /// <param name="mInterface">model describing "Groupable Resource Interface"</param>
        public ClientFluentGroupableModelImpl(ClientFluentGroupableModelInterface mInterface)
        {
            this.Interface = mInterface;
        }

        /// <summary>
        /// Gets the model describing "Groupable Resource Interface" whose implementation this model describes.
        /// </summary>
        public ClientFluentGroupableModelInterface Interface
        {
            get; private set;
        }

        /// <summary>
        /// Gets the imports to be imported in Groupable Resource Implementation.
        /// </summary>
        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    "com.microsoft.azure.arm.resources.models.implementation.GroupableResourceCoreImpl",
                    $"{this.package}.{this.Interface.JavaInterfaceName}",
                    "rx.Observable"
                };
                imports.AddRange(this.Interface.ImportsForImpl);
                return imports;
            }
        }

        /// <summary>
        /// Gets the class that Groupable Resource Implementation extends from.
        /// </summary>
        public string ExtendsFrom
        {
            get
            {
                return $" extends GroupableResourceCoreImpl<{this.Interface.JavaInterfaceName}, " +
                    $"{this.Interface.InnerModelName}, " +
                    $"{this.JavaClassName}, " +
                    $"{this.Interface.FluentMethodGroup.ManagerName}>";
            }
        }

        /// <summary>
        /// Gets comma list of interfaces that Groupable Resource Implementation implements.
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
        /// Gets a list of string, each one represents declaration of member variable in Groupable Resource Implementation.
        /// e.g: private StorageAccountCreateParameters createParameters;
        /// </summary>
        public IEnumerable<string> DeclareMemberVariables
        {
            get
            {

                return this.Interface.DisambiguatedMemberVariables
                    .MemberVariables
                    .Where(v => !(v is FluentModelParentRefMemberVariable))
                    .Select(m => m.VariableDeclaration);
            }
        }

        /// <summary>
        /// Gets a list of string, each one represents initialization of member variable in Groupable Resource Implementation.
        /// e.g: this.createParameters = new StorageAccountCreateParameters();
        /// </summary>
        public IEnumerable<string> InitMemberVariables
        {
            get
            {
                return this.Interface.DisambiguatedMemberVariables.InitMemberVariables;
            }
        }

        /// <summary>
        /// Gets Groupable Resource Implementation Constructor.
        /// </summary>
        public string CtrImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"{this.JavaClassName}(String name, {this.InnerModelName} inner, {this.Interface.FluentMethodGroup.ManagerName} manager) {{");
                methodBuilder.AppendLine($"    super(name, inner, manager);");
                foreach(string initvariable in InitMemberVariables)
                {
                    methodBuilder.AppendLine($"    {initvariable}");
                }
                methodBuilder.AppendLine($"}}");
                return methodBuilder.ToString();
            }
        }

        /// <summary>
        /// Gets a list of string, each string contains defintion of a method in Groupable Resource Implementation.
        /// </summary>
        public IEnumerable<string> JavaMethods
        {
            get
            {
                yield return this.CtrImplementation;
                foreach (string method in CreatableUpdatableAbstractMethodsImplementation)
                {
                    yield return method;
                }
                yield return this.Interface.IsInCreateModeMethodImplementation;
                yield return this.Interface.ResetRquestPayloadVariablesMethodImplementation;
            }
        }

        /// <summary>
        /// Groupable Resource Implementation inherits chain has following types in the hierarchy.
        /// Groupable Resource Impl -> GroupableResourceCoreImpl -> ResourceImpl -> CreatableUpdatableImpl -> IndexableRefreshableWrapperImpl.
        /// 
        ///   https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/resources/models/implementation/GroupableResourceCoreImpl.java
        ///   https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/resources/models/implementation/ResourceImpl.java
        ///   https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/CreatableUpdatableImpl.java
        ///   https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/IndexableRefreshableWrapperImpl.java
        /// 
        /// This method returns implementation of abstract methods in the base classes.
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
                    //
                    List<string> params1 = new List<string>()
                    {
                        "this.resourceGroupName()",
                        "this.name()"
                    };
                    //
                    IEnumerable<string> params2 = this.Interface.DisambiguatedMemberVariables.MemeberVariablesForCreate
                        .Values
                        .OrderBy(v => v.IndexInMethod)
                        .Where(v => !(v is FluentModelParentRefMemberVariable))
                        .Select(v => v.VariableAccessor);
                    //
                    IEnumerable<string> createMethodParameters = params1.Union(params2);
                    //
                    var createMethodParametersCombined = string.Join(", ", createMethodParameters);
                    //
                    return this.Interface.CreateResourceAsyncMethodImplementation(createMethod,
                        createMethodParametersCombined,
                        this.Interface.JavaInterfaceName,
                        Interface.FluentMethodGroup.InnerMethodGroup.MethodGroupImplType, 
                        SetLocationAndTagsProperties);
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
                    //
                    List<string> params1 = new List<string>()
                    {
                        "this.resourceGroupName()",
                        "this.name()"
                    };
                    //
                    IEnumerable<string> params2 = this.Interface.DisambiguatedMemberVariables.MemeberVariablesForUpdate
                        .Values
                        .OrderBy(v => v.IndexInMethod)
                        .Where(v => !(v is FluentModelParentRefMemberVariable))
                        .Select(v => v.VariableAccessor);
                    //
                    IEnumerable<string> updateMethodParameters = params1.Union(params2);
                    //
                    var updateMethodParametersCombined = string.Join(", ", updateMethodParameters);

                    return this.Interface.UpdateResourceAsyncMethodImplementation(updateMethod,
                        updateMethodParametersCombined,
                        this.Interface.JavaInterfaceName,
                        Interface.FluentMethodGroup.InnerMethodGroup.MethodGroupImplType);
                }
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
                    StandardFluentMethod getMethod = this.Interface.FluentMethodGroup.ResourceGetDescription.GetByResourceGroupMethod;
                    var getMethodParametersCombined = "this.resourceGroupName(), this.name()";
                    return this.Interface.GetInnerAsyncMethodImplementation(getMethod,
                        getMethodParametersCombined,
                        Interface.FluentMethodGroup.InnerMethodGroup.MethodGroupImplType);
                }
            }
        }

        /// <summary>
        /// There are 'Groupable Resource' with payload parameter of it's Create Method different from the 'Inner Groupable Resource'.
        /// An example is storage account - payload parameter is of type 'StorageAccountCreateParameter' and inner is of type 'StorageAccountInner'.
        /// The two defintion stages 'WithRegion' and 'WithTags' sets region and tags in inner type. In cases where inner and payload are different
        /// we need to copy these values from inner instance to payload instance. SetLocationAndTagsProperties returns statements to perform these
        /// copy over if applicable.
        /// </summary>
        private string SetLocationAndTagsProperties
        {
            get
            {
                StringBuilder setProperties = new StringBuilder();
                if (this.Interface.SupportsCreating)
                {
                    var createPayloadParameter = this.Interface.DisambiguatedMemberVariables
                        .MemeberVariablesForCreate.Values
                        .Where(p => p.VariableName.Equals(FluentModelDisambiguatedMemberVariables.CreateParameterVariableName)
                            || p.VariableName.Equals(FluentModelDisambiguatedMemberVariables.CreateOrUpdateParameterVariableName))
                        .FirstOrDefault();
                    //
                    if (createPayloadParameter != null && createPayloadParameter.VariableType is CompositeTypeJvaf)
                    {
                        var variableType = (CompositeTypeJvaf)createPayloadParameter.VariableType;
                        var locationProperty = variableType.ComposedProperties.FirstOrDefault(p => p.Name.EqualsIgnoreCase("location"));
                        if (locationProperty != null && !locationProperty.IsReadOnly)
                        {
                            string setLocationStatement = $"    {createPayloadParameter.VariableAccessor}.withLocation(inner().location());";
                            setProperties.AppendLine(setLocationStatement);
                        }
                        var tagsProperty = variableType.ComposedProperties.FirstOrDefault(p => p.Name.EqualsIgnoreCase("tags"));
                        if (tagsProperty != null && !tagsProperty.IsReadOnly)
                        {
                            string setTagsStatement = $"    {createPayloadParameter.VariableAccessor}.withTags(inner().getTags());";
                            setProperties.AppendLine(setTagsStatement);
                        }
                    }
                }
                return setProperties.ToString();
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
