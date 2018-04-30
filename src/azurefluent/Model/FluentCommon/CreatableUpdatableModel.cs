// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public abstract class CreatableUpdatableModel
    {
        public const string ResetCreateUpdateParametersMethodName = "resetCreateUpdateParameters";

        protected readonly string package = Settings.Instance.Namespace.ToLower();

        private readonly FluentModelMemberVariablesForCreate cVariables;
        private readonly FluentModelMemberVariablesForUpdate uVariables;
        private readonly FluentModelMemberVariablesForGet gVariable;
        private readonly FluentModel fluentModel;

        protected CreatableUpdatableModel(FluentMethodGroup fluentMethodGroup,
            FluentModelMemberVariablesForCreate cVariables,
            FluentModelMemberVariablesForUpdate uVariables,
            FluentModelMemberVariablesForGet gVariable, 
            FluentModel fluentModel)
        {
            this.FluentMethodGroup = fluentMethodGroup;
            //
            this.cVariables = cVariables;
            this.uVariables = uVariables;
            this.gVariable = gVariable;
            //
            this.fluentModel = fluentModel;
            //
            this.DisambiguatedMemberVariables = new FluentModelDisambiguatedMemberVariables()
                .WithCreateMemberVariable(this.cVariables)
                .WithUpdateMemberVariable(this.uVariables)
                .WithGetMemberVariable(this.gVariable)
                .Disambiguate();
            //
            this.cVariables.SetDisambiguatedMemberVariables(this.DisambiguatedMemberVariables);
            this.uVariables.SetDisambiguatedMemberVariables(this.DisambiguatedMemberVariables);
        }

        public string JavaInterfaceName
        {
            get
            {
                return this.fluentModel.JavaInterfaceName;
            }
        }

        public CompositeTypeJvaf InnerModel
        {
            get
            {
                return this.fluentModel.InnerModel;
            }
        }

        public string InnerModelName
        {
            get
            {
                return this.InnerModel.Name;
            }
        }

        public FluentModelDisambiguatedMemberVariables DisambiguatedMemberVariables
        {
            get; private set;
        }

        public FluentMethodGroup FluentMethodGroup
        {
            get; private set;
        }

        protected abstract IEnumerable<Property> LocalProperties { get; }

        private ModelLocalProperties modelLocalProperties;

        public ModelLocalProperties ModelLocalProperties
        {
            get
            {
                if (modelLocalProperties == null)
                {
                    this.modelLocalProperties = new ModelLocalProperties(this.LocalProperties, 
                        this.FluentMethodGroup.FluentMethodGroups, true);
                }
                return this.modelLocalProperties;
            }
        }

        public abstract bool SupportsCreating { get; }

        public abstract bool SupportsGetting { get; }

        protected abstract bool UpdateSupported { get; }

        public bool SupportsUpdating
        {
            get
            {
                if (this.UpdateSupported)
                {
                    return this.cVariables.IsCompatibleWith(this.uVariables);
                }
                else
                {
                    return false;
                }
            }
        }

        public bool SupportsRefreshing
        {
            get
            {
                if (this.SupportsGetting)
                {
                    bool supportCreating = this.SupportsCreating;
                    bool supportsUpdating = this.SupportsUpdating;

                    if (supportCreating)
                    {
                        return this.cVariables.IsCompatibleWith(this.gVariable);
                    }
                    else if (supportsUpdating)
                    {
                        return this.uVariables.IsCompatibleWith(this.gVariable);
                    }
                    else
                    {
                        return true;
                    }
                }
                return false;
            }
        }

        public bool IsCreatableOrUpdatable
        {
            get
            {
                return (this.SupportsCreating || this.SupportsUpdating);
            }
        }

        public HashSet<string> ImportsForImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                imports.AddRange(this.UpdateImportsForImpl);
                imports.AddRange(this.CreateImportsForImpl);
                imports.AddRange(this.ModelLocalProperties.ImportsForModelImpl);
                //
                if (this.RequireUpdateResultToInnerModelMapping ||
                    this.RequireCreateResultToInnerModelMapping ||
                    this.RequirePayloadReset)
                {
                    imports.Add("rx.functions.Func1");
                }
                //
                return imports;
            }
        }

        public HashSet<string> ImportsForInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                imports.AddRange(this.UpdateImportsForInterface);
                imports.AddRange(this.CreateImportsForInterface);
                imports.AddRange(this.ModelLocalProperties.ImportsForModelInterface);
                return imports;
            }
        }

        public bool HasArmId
        {
            get
            {
                return this.HasPropertyWithName("id");
            }
        }

        public bool HasPropertyWithName(string propertyName)
        {
            return this.InnerModel
                .ComposedProperties
                .Any(p => p.SerializedName.EqualsIgnoreCase(propertyName));
        }

        private HashSet<string> CreateImportsForInterface
        {
            get
            {
                if (this.SupportsCreating)
                {
                    return this.cVariables.ImportsForInterface;
                }
                else
                {
                    return new HashSet<string>();
                }
            }
        }

        private HashSet<string> CreateImportsForImpl
        {
            get
            {
                if (this.SupportsCreating)
                {
                    return this.cVariables.ImportsForImpl;
                }
                else
                {
                    return new HashSet<string>();
                }
            }
        }

        private HashSet<string> UpdateImportsForInterface
        {
            get
            {
                if (this.SupportsUpdating)
                {
                    return this.uVariables.ImportsForInterface;
                }
                else
                {
                    return new HashSet<string>();
                }
            }
        }

        private HashSet<string> UpdateImportsForImpl
        {
            get
            {
                if (this.SupportsUpdating)
                {
                    return this.uVariables.ImportsForImpl;
                }
                else
                {
                    return new HashSet<string>();
                }
            }
        }

        public List<FluentDefinitionOrUpdateStage> RequiredDefinitionStages
        {
            get
            {
                if (!this.SupportsCreating)
                {
                    return new List<FluentDefinitionOrUpdateStage>();
                }
                else
                {
                    return this.cVariables.RequiredDefinitionStages();
                }
            }
        }

        public List<FluentDefinitionOrUpdateStage> OptionalDefinitionStages
        {
            get
            {
                if (!this.SupportsCreating)
                {
                    return new List<FluentDefinitionOrUpdateStage>();
                }
                else
                {
                    return this.cVariables.OptionalDefinitionStages();
                }
            }
        }

        public List<FluentDefinitionOrUpdateStage> UpdateStages
        {
            get
            {
                if (!this.SupportsUpdating)
                {
                    return new List<FluentDefinitionOrUpdateStage>();
                }
                else
                {
                    return this.uVariables.UpdateStages();
                }
            }
        }

        /// <summary>
        /// Returns the methods used to set the nested resource properties applicable only during resource creation time.
        /// </summary>
        public IEnumerable<FluentDefinitionOrUpdateStageMethod> CreateOnlyWither
        {
            get
            {
                return this.RequiredDefinitionStages
                    .Union(this.OptionalDefinitionStages)
                    .SelectMany(s => s.Methods)
                    .Except(this.UpdateStages.SelectMany(r => r.Methods), FluentDefinitionOrUpdateStageMethod.EqualityComparer());
            }
        }

        /// <summary>
        /// Returns the methods used to set the nested resource properties applicable only during resource update time.
        /// </summary>
        public IEnumerable<FluentDefinitionOrUpdateStageMethod> UpdateOnlyWithers
        {
            get
            {
                return this.UpdateStages
                     .SelectMany(s => s.Methods)
                     .Except(this.RequiredDefinitionStages.Union(this.OptionalDefinitionStages).SelectMany(r => r.Methods), FluentDefinitionOrUpdateStageMethod.EqualityComparer());
            }
        }

        /// <summary>
        /// Returns the methods used to set the nested resource properties applicable for both resource creation and update time.
        /// </summary>
        public IEnumerable<FluentDefinitionOrUpdateStageMethod> CreateAndUpdateWithers
        {
            get
            {
                var defMethods = this.RequiredDefinitionStages
                    .Union(this.OptionalDefinitionStages)
                    .SelectMany(s => s.Methods);

                var updateMethods = this.UpdateStages
                    .SelectMany(u => u.Methods);

                var comparer = FluentDefinitionOrUpdateStageMethod.EqualityComparer();
                foreach (var defMethod in defMethods)
                {
                    foreach (var updateMethod in updateMethods)
                    {
                        if (comparer.Equals(defMethod, updateMethod))
                        {
                            if (defMethod.Body.Equals(updateMethod.Body))
                            {
                                yield return defMethod; // or updateMethod
                            }
                            else
                            {
                                FluentDefinitionOrUpdateStageMethod mergedMethod = new FluentDefinitionOrUpdateStageMethod(defMethod.Name,
                                    defMethod.ParameterDeclaration,
                                    defMethod.ParameterTypesKey);

                                string mergedBody = "if (isInCreateMode()) {" + "\n" +
                                                   $"    {defMethod.Body}" + "\n" +
                                                    "} else {" + "\n" +
                                                   $"    {updateMethod.Body}" + "\n" +
                                                    "}";

                                mergedMethod.Body = mergedBody;
                                yield return mergedMethod;
                            }
                        }
                    }
                }
            }
        }

        public bool RequirePayloadReset
        {
            get
            {
                return this.DisambiguatedMemberVariables
                    .MemberVariables
                    .Select(m => m.VariableInitialize)
                    .Where(d => !string.IsNullOrEmpty(d))
                    .Any();
            }
        }

        public string ResetRquestPayloadVariablesMethodImplementation
        {
            get
            {
                var payloadMemberVariableInits = this.DisambiguatedMemberVariables
                    .MemberVariables
                    .Select(m => m.VariableInitialize)
                    .Where(d => !string.IsNullOrEmpty(d));

                if (payloadMemberVariableInits.Any())
                {
                    StringBuilder methodBuilder = new StringBuilder();

                    methodBuilder.AppendLine($"private void {ResetCreateUpdateParametersMethodName}() {{");
                    foreach (var varInit in payloadMemberVariableInits)
                    {
                        methodBuilder.AppendLine($"    {varInit}");
                    }
                    methodBuilder.AppendLine($"}}");
                    return methodBuilder.ToString();
                }
                else
                {
                    return String.Empty;
                }
            }
        }

        public bool RequireUpdateResultToInnerModelMapping
        {
            get
            {
                if (!this.SupportsUpdating)
                {
                    return false;
                }
                FluentMethod updateMethod = this.FluentMethodGroup.ResourceUpdateDescription.UpdateMethod;
                string updateReturnTypeName = updateMethod.ReturnModel.InnerModel.Name;
                return !updateReturnTypeName.Equals(this.InnerModelName);
            }
        }

        public bool RequireCreateResultToInnerModelMapping
        {
            get
            {
                if (!this.SupportsCreating)
                {
                    return false;
                }
                FluentMethod createMethod = this.FluentMethodGroup.ResourceCreateDescription.CreateMethod;
                string createReturnTypeName = createMethod.ReturnModel.InnerModel.Name;
                return !createReturnTypeName.Equals(this.InnerModelName);
            }
        }

        public string CreateResourceAsyncNOPMethodImplementation(string createdResourceInterfaceName, string innerMethodGroupTypeName)
        {
            StringBuilder methodBuilder = new StringBuilder();
            methodBuilder.AppendLine("@Override");
            methodBuilder.AppendLine($"public Observable<{createdResourceInterfaceName}> createResourceAsync() {{");
            methodBuilder.AppendLine($"    {innerMethodGroupTypeName} client = this.manager().inner().{this.FluentMethodGroup.InnerMethodGroup.Name}();");
            methodBuilder.AppendLine("    return null; // NOP createResourceAsync implementation as create is not supported");
            methodBuilder.AppendLine("}");
            return methodBuilder.ToString();
        }

        public string CreateResourceAsyncMethodImplementation(FluentMethod createMethod,
            string createMethodParameters,
            string createdResourceInterfaceName,
            string innerMethodGroupTypeName,
            string preCreateStatements = "")
        {
            StringBuilder methodBuilder = new StringBuilder();
            methodBuilder.AppendLine("@Override");
            methodBuilder.AppendLine($"public Observable<{createdResourceInterfaceName}> createResourceAsync() {{");
            methodBuilder.AppendLine($"    {innerMethodGroupTypeName} client = this.manager().inner().{this.FluentMethodGroup.InnerMethodGroup.Name}();");
            if (!this.SupportsCreating)
            {
                methodBuilder.AppendLine("    return null; // NOP createResourceAsync implementation as create is not supported");
            }
            else
            {
                if (!string.IsNullOrEmpty(preCreateStatements))
                {
                    methodBuilder.AppendLine(preCreateStatements);
                }
                //
                if (this.RequireCreateResultToInnerModelMapping)
                {
                    string createReturnTypeName = createMethod.ReturnModel.InnerModel.Name;

                    methodBuilder.AppendLine($"return client.{createMethod.InnerMethod.Name}Async({createMethodParameters})");
                    methodBuilder.AppendLine($"        .flatMap(new Func1<{createReturnTypeName}, Observable<{InnerModelName}>>() {{");
                    methodBuilder.AppendLine($"           @Override");
                    methodBuilder.AppendLine($"           public Observable<{InnerModelName}> call({createReturnTypeName} resource) {{");
                    if (this.RequirePayloadReset)
                    {
                        methodBuilder.AppendLine($"               {CreatableUpdatableModel.ResetCreateUpdateParametersMethodName}(); ");
                    }
                    methodBuilder.AppendLine($"               return getInnerAsync(); ");
                    methodBuilder.AppendLine($"           }} ");
                    methodBuilder.AppendLine($"        }})");
                    methodBuilder.AppendLine($"        .map(innerToFluentMap(this));");
                }
                else
                {
                    string createReturnTypeName = createMethod.ReturnModel.InnerModel.Name;

                    methodBuilder.AppendLine($"    return client.{createMethod.InnerMethod.Name}Async({createMethodParameters})");
                    if (this.RequirePayloadReset)
                    {
                        methodBuilder.AppendLine($"        .map(new Func1<{InnerModelName}, {InnerModelName}>() {{");
                        methodBuilder.AppendLine($"           @Override");
                        methodBuilder.AppendLine($"           public {createReturnTypeName} call({createReturnTypeName} resource) {{");
                        methodBuilder.AppendLine($"               {CreatableUpdatableModel.ResetCreateUpdateParametersMethodName}(); ");
                        methodBuilder.AppendLine($"               return resource; ");
                        methodBuilder.AppendLine($"           }} ");
                        methodBuilder.AppendLine($"        }})");
                    }
                    methodBuilder.AppendLine($"        .map(innerToFluentMap(this));");
                }
            }
            methodBuilder.AppendLine("}");
            return methodBuilder.ToString();
        }

        public string UpdateResourceAsyncNOPMethodImplementation(string updatedResourceInterfaceName, string innerMethodGroupTypeName)
        {
            StringBuilder methodBuilder = new StringBuilder();
            methodBuilder.AppendLine("@Override");
            methodBuilder.AppendLine($"public Observable<{updatedResourceInterfaceName}> updateResourceAsync() {{");
            methodBuilder.AppendLine($"    {innerMethodGroupTypeName} client = this.manager().inner().{this.FluentMethodGroup.InnerMethodGroup.Name}();");
            methodBuilder.AppendLine("    return null; // NOP updateResourceAsync implementation as update is not supported");
            methodBuilder.AppendLine("}");
            return methodBuilder.ToString();
        }

        public string UpdateResourceAsyncMethodImplementation(FluentMethod updateMethod,
            string updateMethodParameters,
            string updatedResourceInterfaceName,
            string innerMethodGroupTypeName)
        {
            StringBuilder methodBuilder = new StringBuilder();
            methodBuilder.AppendLine("@Override");
            methodBuilder.AppendLine($"public Observable<{updatedResourceInterfaceName}> updateResourceAsync() {{");
            methodBuilder.AppendLine($"    {innerMethodGroupTypeName} client = this.manager().inner().{this.FluentMethodGroup.InnerMethodGroup.Name}();");
            if (!this.SupportsUpdating)
            {
                methodBuilder.AppendLine("    return null; // NOP updateResourceAsync implementation as update is not supported");
            }
            else
            {
                if (this.RequireUpdateResultToInnerModelMapping)
                {
                    string updateReturnTypeName = updateMethod.ReturnModel.InnerModel.Name;

                    methodBuilder.AppendLine($"return client.{updateMethod.InnerMethod.Name}Async({updateMethodParameters})");
                    methodBuilder.AppendLine($"        .flatMap(new Func1<{updateReturnTypeName}, Observable<{InnerModelName}>>() {{");
                    methodBuilder.AppendLine($"           @Override");
                    methodBuilder.AppendLine($"           public Observable<{InnerModelName}> call({updateReturnTypeName} r) {{");
                    if (this.RequirePayloadReset)
                    {
                        methodBuilder.AppendLine($"               {CreatableUpdatableModel.ResetCreateUpdateParametersMethodName}(); ");
                    }
                    methodBuilder.AppendLine($"               return getInnerAsync(); ");
                    methodBuilder.AppendLine($"           }} ");
                    methodBuilder.AppendLine($"        }})");
                    methodBuilder.AppendLine($"        .map(innerToFluentMap(this));");
                }
                else
                {
                    string updateReturnTypeName = updateMethod.ReturnModel.InnerModel.Name;

                    methodBuilder.AppendLine($"    return client.{updateMethod.InnerMethod.Name}Async({updateMethodParameters})");
                    if (this.RequirePayloadReset)
                    {
                        methodBuilder.AppendLine($"        .map(new Func1<{InnerModelName}, {InnerModelName}>() {{");
                        methodBuilder.AppendLine($"           @Override");
                        methodBuilder.AppendLine($"           public {updateReturnTypeName} call({updateReturnTypeName} resource) {{");
                        methodBuilder.AppendLine($"               {CreatableUpdatableModel.ResetCreateUpdateParametersMethodName}(); ");
                        methodBuilder.AppendLine($"               return resource; ");
                        methodBuilder.AppendLine($"           }} ");
                        methodBuilder.AppendLine($"        }})");
                    }
                    methodBuilder.AppendLine($"        .map(innerToFluentMap(this));");
                }
            }
            methodBuilder.AppendLine("}");
            return methodBuilder.ToString();
        }

        public string GetInnerAsyncNOPMethodImplementation(string innerMethodGroupTypeName)
        {
            StringBuilder methodBuilder = new StringBuilder();
            methodBuilder.AppendLine("@Override");
            methodBuilder.AppendLine($"protected Observable<{InnerModelName}> getInnerAsync() {{");
            methodBuilder.AppendLine($"    {innerMethodGroupTypeName} client = this.manager().inner().{this.FluentMethodGroup.InnerMethodGroup.Name}();");
            if (!this.SupportsGetting)
            {
                methodBuilder.AppendLine("    return null; // NOP getInnerAsync implementation as get is not supported");
            }
            methodBuilder.AppendLine("}");
            return methodBuilder.ToString();
        }

        public string GetInnerAsyncMethodImplementation(FluentMethod getMethod,
            string getMethodParameters,
            string innerMethodGroupTypeName)
        {
            StringBuilder methodBuilder = new StringBuilder();
            methodBuilder.AppendLine("@Override");
            methodBuilder.AppendLine($"protected Observable<{InnerModelName}> getInnerAsync() {{");
            methodBuilder.AppendLine($"    {innerMethodGroupTypeName} client = this.manager().inner().{this.FluentMethodGroup.InnerMethodGroup.Name}();");
            if (!this.SupportsGetting)
            {
                methodBuilder.AppendLine("    return null; // NOP getInnerAsync implementation as get is not supported");
            }
            else
            {
                methodBuilder.AppendLine($"    return client.{getMethod.InnerMethod.Name}Async({getMethodParameters});");
            }
            methodBuilder.AppendLine("}");
            return methodBuilder.ToString();
        }

        public string IsInCreateModeMethodImplementation
        {
            get
            {
                if (this.IsCreatableOrUpdatable)
                {
                    StringBuilder methodsBuilder = new StringBuilder();
                    methodsBuilder.AppendLine("@Override");
                    methodsBuilder.AppendLine("public boolean isInCreateMode() {");
                    methodsBuilder.AppendLine("    return this.inner().id() == null;");
                    methodsBuilder.AppendLine("}");
                    return methodsBuilder.ToString();
                }
                else
                {
                    return String.Empty;
                }
            }
        }

        public static IEqualityComparer<T> EqualityComparer<T>() where T : CreatableUpdatableModel
        {
            return new CreatableUpdatableModelComparerBasedOnJvaInterfaceName<T>();
        }
    }

    class CreatableUpdatableModelComparerBasedOnJvaInterfaceName<T> : IEqualityComparer<T> where T : CreatableUpdatableModel
    {
        public bool Equals(T x, T y)
        {
            return x.JavaInterfaceName.EqualsIgnoreCase(y.JavaInterfaceName);
        }

        public int GetHashCode(T obj)
        {
            return obj.JavaInterfaceName.GetHashCode();
        }
    }
}
