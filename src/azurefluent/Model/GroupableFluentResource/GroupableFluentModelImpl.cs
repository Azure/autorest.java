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
    /// <summary>
    /// The impl-metadata model that can generate a groupable model implementation.
    /// </summary>
    public class GroupableFluentModelImpl : IFluentModel
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        public GroupableFluentModelImpl(GroupableFluentModelInterface mInterface)
        {
            this.Interface = mInterface;
        }

        public GroupableFluentModelInterface Interface
        {
            get; private set;
        }

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
                yield return this.CtrImplementation;
                foreach (string method in CreatableUpdatableAbstractMethodsImplementation)
                {
                    yield return method;
                }
                yield return this.Interface.IsInCreateModeMethodImplementation;
                yield return this.Interface.ResetRquestPayloadVariablesMethodImplementation;
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
                    //
                    List<string> params1 = new List<string>()
                    {
                        "this.resourceGroupName()",
                        "this.name()"
                    };
                    //
                    IEnumerable<string> params2 = this.Interface.DisambiguatedMemberVariables.MemeberVariablesForCreate
                        .Values
                        .OrderBy(v => v.Index)
                        .Where(v => !(v is FluentModelParentRefMemberVariable))
                        .Select(v => v.VariableAccessor);
                    //
                    IEnumerable<string> createMethodParameters = params1.Union(params2);
                    //
                    var createMethodParametersCombined = String.Join(", ", createMethodParameters);
                    //
                    return this.Interface.CreateResourceAsyncMethodImplementation(createMethod,
                        createMethodParametersCombined,
                        this.Interface.JavaInterfaceName,
                        Interface.FluentMethodGroup.InnerMethodGroup.MethodGroupImplType, 
                        SetLocationAndTagsProperties);
                }
            }
        }

        private string SetLocationAndTagsProperties
        {
            get
            {
                StringBuilder setProperties = new StringBuilder();
                if (this.Interface.SupportsCreating)
                {
                    var createPayloadParameter = this.Interface.DisambiguatedMemberVariables.MemeberVariablesForCreate
                        .Values
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
                    //
                    List<string> params1 = new List<string>()
                    {
                        "this.resourceGroupName()",
                        "this.name()"
                    };
                    //
                    IEnumerable<string> params2 = this.Interface.DisambiguatedMemberVariables.MemeberVariablesForUpdate
                        .Values
                        .OrderBy(v => v.Index)
                        .Where(v => !(v is FluentModelParentRefMemberVariable))
                        .Select(v => v.VariableAccessor);
                    //
                    IEnumerable<string> updateMethodParameters = params1.Union(params2);
                    //
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
                    FluentMethod getMethod = this.Interface.FluentMethodGroup.ResourceGetDescription.GetByResourceGroupMethod;
                    var getMethodParametersCombined = "this.resourceGroupName(), this.name()";
                    return this.Interface.GetInnerAsyncMethodImplementation(getMethod,
                        getMethodParametersCombined,
                        Interface.FluentMethodGroup.InnerMethodGroup.MethodGroupImplType);
                }
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
                return $" new {this.JavaClassName}(inner.name(), inner, manager());";
            }
        }


        public string CtrInvocationForWrappingNewInnerModel
        {
            get
            {
                return $"new {this.JavaClassName}(name, new {this.InnerModelName}(), this.manager());";
            }
        }

        public ModelLocalProperties ModelLocalProperties
        {
            get
            {
                return this.Interface.ModelLocalProperties;
            }
        }

        #endregion
    }
}
