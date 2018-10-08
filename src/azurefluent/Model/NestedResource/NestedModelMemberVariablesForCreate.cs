// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The specialized variant of FluentModelMemberVariablesForCreate holding member variables corrosponding to 'Nested Resource Create' method parameters.
    /// Nested Resource: Represents an Azure resource whose level > 0 and hence has immediate parent resource other than resource group.
    /// 
    /// These member variable descriptions are used to
    ///     1. Derive nested interfaces of 'Nested Resource Interface' describing stages of resource definition/create we call them 'Definition Stage Nested Interfaces'.
    ///     2. Derive  member variables of a Java implementation class (aka Nested Resource Implementation) that implements 'Nested Resource Interface' and it's nested interfaces.
    /// 
    /// 'Definition Stage Nested Interfaces' contains methods that takes values for corrosponding member variables and can be categorized into two.
    ///     a. Those takes required parameters: 'Required Definition Stage Nested Interfaces'
    ///     b. Those takes optional parameters: 'Optional Definition Stage Nested Interfaces'
    ///   
    /// 'Nested Resource Implementation' extends from one of the following depending on it's capability:
    ///     If Creatable/Updatable: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/CreatableUpdatableImpl.java
    ///     If Retrievable: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/IndexableRefreshableWrapperImpl.java
    ///     Otherwise: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/WrapperImpl.java
    /// </summary>
    public class NestedModelMemberVariablesForCreate : FluentModelMemberVariablesForCreate
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        /// <summary>
        /// Creates NestedModelMemberVariablesForCreate holding member variables corrosponding to 'Create' method parameters.
        /// </summary>
        public NestedModelMemberVariablesForCreate() : base()
        {
        }

        /// <summary>
        /// Creates NestedModelMemberVariablesForCreate holding member variables corrosponding to 'Create' method parameters.
        /// </summary>
        /// <param name="fluentMethodGroup">The method group that the 'Create' method belongs to</param>
        public NestedModelMemberVariablesForCreate(SegmentFluentMethodGroup fluentMethodGroup) : base (fluentMethodGroup)
        {
        }

        public override void SetDisambiguatedMemberVariables(FluentModelDisambiguatedMemberVariables dMemberVariables)
        {
            base.SetDisambiguatedMemberVariables(dMemberVariables);
        }

        /// <summary>
        /// Returns the imports required by the types used in 'Definition Stage Nested Interfaces'.
        /// </summary>
        public override HashSet<string> ImportsForInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (!this.SupportsCreating)
                {
                    return imports;
                }
                this.ParentRefMemberVariables
                    .Select(v => v.FromParameter)
                    .SelectMany(p => Utils.ParameterImportsForInterface(p, package))
                    .ForEach(import =>
                    {
                        imports.Add(import);
                    });
                imports.AddRange(base.ImportsForInterface);
                return imports;
            }
        }

        /// <summary>
        /// Returns the imports required by a type that implements 'Definition Stage Nested Interfaces'.
        /// </summary>
        public override HashSet<string> ImportsForImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (!this.SupportsCreating)
                {
                    return imports;
                }
                this.ParentRefMemberVariables
                    .Select(v => v.FromParameter)
                    .SelectMany(p => Utils.ParameterImportsForImpl(p, package))
                    .ForEach(import =>
                    {
                        imports.Add(import);
                    });
                imports.AddRange(base.ImportsForImpl);
                return imports;
            }
        }

        /// <summary>
        /// Returns list of descriptions of 'Required Definition Stage Nested Interfaces'.
        /// </summary>
        public override List<FluentDefinitionOrUpdateStage> RequiredDefinitionStages()
        {
            if (!this.SupportsCreating)
            {
                return base.RequiredDefinitionStages(null);
            }

            // First stage to set the ancestors (parents)
            //
            List<FluentDefinitionOrUpdateStage> initialStages = new List<FluentDefinitionOrUpdateStage>()
            {
                FirstDefintionStage(this.ParentRefMemberVariables)
            };
            // Combine first stages with remaining stages
            return base.RequiredDefinitionStages(initialStages);
        }

        /// <summary>
        /// Returns list of descriptions of 'Optional Definition Stage Nested Interfaces'.
        /// </summary>
        public override List<FluentDefinitionOrUpdateStage> OptionalDefinitionStages()
        {
            return base.OptionalDefinitionStages(null);
        }

        /// <summary>
        /// Prepare and return the initial defintion stages that exposes methods to specify the ancestors of the 'Nested Resource'.
        /// </summary>
        /// <param name="parentRefMemberVariables">the member vaiables that corrosonds to ancestors of the resource</param>
        /// <returns></returns>
        private FluentDefinitionOrUpdateStage FirstDefintionStage(IOrderedEnumerable<FluentModelParentRefMemberVariable> parentRefMemberVariables)
        {
            var pVariables = parentRefMemberVariables
                .Where(pref => !pref.ParentRefName.Equals(this.FluentMethodGroup.LocalNameInPascalCase, StringComparison.OrdinalIgnoreCase));

            string ancestorWitherSuffix = FluentMethodGroup.ParentFluentMethodGroup.SingularJavaInterfaceName;
            FluentDefinitionOrUpdateStage stage = new FluentDefinitionOrUpdateStage(this.resourceName, $"With{ancestorWitherSuffix}");

            List<IModelTypeJv> paramTypes = new List<IModelTypeJv>();
            List<string> declarations = new List<string>();
            StringBuilder setParentRefLocalParams = new StringBuilder();

            var commentFor = new Dictionary<string, string>();
            foreach (var parentRefVar in pVariables)
            {
                commentFor.Add(parentRefVar.VariableName, parentRefVar.FromParameter.Documentation);
                declarations.Add($"{parentRefVar.VariableTypeName} {parentRefVar.VariableName}");
                paramTypes.Add(parentRefVar.VariableType);
                setParentRefLocalParams.AppendLine($"{parentRefVar.VariableAccessor} = {parentRefVar.VariableName};");
            }

            string methodName = $"withExisting{ancestorWitherSuffix}";
            string methodParameterDecl = string.Join(", ", declarations);

            stage.Methods.Add(new FluentDefinitionOrUpdateStageMethod(methodName, methodParameterDecl, paramTypes)
            {
                CommentFor = commentFor,
                Body = setParentRefLocalParams.ToString()
            });
            return stage;
        }
    }
}
