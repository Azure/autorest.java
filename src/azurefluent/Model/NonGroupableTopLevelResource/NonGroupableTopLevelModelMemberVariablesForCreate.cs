// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The specialized variant of FluentModelMemberVariablesForCreate holding member variables corrosponding to 'Non-Groupable TopLevel Resource Create' method parameters.
    /// Non-Groupable TopLevel Resource: Represents an Azure resource that is at level 0 and is Not a Tracked Resource [see Utils.IsTrackedResource(param)]
    /// 
    /// These member variable descriptions are used to
    ///     1. Derive nested interfaces of 'Non-Groupable TopLevel Resource Interface' describing stages of resource definition/create we call them 'Definition Stage Nested Interfaces'.
    ///     2. Derive  member variables of a Java implementation class (aka Non-Groupable TopLevel Resource Implementation) that implements 'Non-Groupable TopLevel Resource Interface' and it's nested interfaces.
    /// 
    /// 'Definition Stage Nested Interfaces' contains methods that takes values for corrosponding member variables and can be categorized into two.
    ///     a. Those takes required parameters: 'Required Definition Stage Nested Interfaces'
    ///     b. Those takes optional parameters: 'Optional Definition Stage Nested Interfaces'
    ///   
    /// 'Non-Groupable TopLevel Resource Implementation' extends from one of the following depending on it's capability:
    ///     If Creatable/Updatable: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/CreatableUpdatableImpl.java
    ///     If Retrievable: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/IndexableRefreshableWrapperImpl.java
    ///     Otherwise: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/WrapperImpl.java
    /// </summary>
    public class NonGroupableTopLevelModelMemberVariablesForCreate : FluentModelMemberVariablesForCreate
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        /// <summary>
        /// Creates NonGroupableTopLevelModelMemberVariablesForCreate holding member variables corrosponding to 'Create' method parameters.
        /// </summary>
        public NonGroupableTopLevelModelMemberVariablesForCreate() : base()
        {
        }

        /// <summary>
        /// Creates NonGroupableTopLevelModelMemberVariablesForCreate holding member variables corrosponding to 'Create' method parameters.
        /// </summary>
        /// <param name="fluentMethodGroup">The method group that the 'Create' method belongs to</param>
        public NonGroupableTopLevelModelMemberVariablesForCreate(SegmentFluentMethodGroup fluentMethodGroup) : base(fluentMethodGroup)
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
            List<FluentDefinitionOrUpdateStage> initialStages = FirstDefintionStages(this.ParentRefMemberVariables);
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
        /// Prepare and return the initial defintion stages that exposes methods to specify the ancestors of the 'Non-Groupable TopLevel Resource'.
        /// </summary>
        /// <param name="parentRefMemberVariables">the member vaiables that corrosonds to ancestors of the resource</param>
        /// <returns></returns>
        private List<FluentDefinitionOrUpdateStage> FirstDefintionStages(IOrderedEnumerable<FluentModelParentRefMemberVariable> parentRefMemberVariables)
        {
            var dmvs = this.DisambiguatedMemberVariables ?? throw new ArgumentNullException("dMemberVariables");

            List<FluentDefinitionOrUpdateStage> initalStages = new List<FluentDefinitionOrUpdateStage>();
            FluentDefinitionOrUpdateStage currentStage = null;
            foreach (FluentModelParentRefMemberVariable memberVariable in parentRefMemberVariables)
            {
                if (memberVariable.ParentRefName.EqualsIgnoreCase(this.FluentMethodGroup.LocalNameInPascalCase))
                {
                    // Exclude self from parent ref member variables
                    continue;
                }
                else
                {
                    string methodName = $"with{memberVariable.FromParameter.Name.ToPascalCase()}";
                    string parameterName = memberVariable.VariableName;
                    string methodParameterDecl = $"{memberVariable.VariableTypeName} {parameterName}";
                    FluentDefinitionOrUpdateStageMethod method = new FluentDefinitionOrUpdateStageMethod(methodName, methodParameterDecl, memberVariable.VariableType)
                    {
                        CommentFor = new Dictionary<string, string> { { parameterName, memberVariable.FromParameter.Documentation } },
                        Body = $"{(dmvs.MemeberVariablesForCreate[memberVariable.VariableName]).VariableAccessor} = {parameterName};"
                    };

                    string interfaceName = $"With{memberVariable.FromParameter.Name.ToPascalCase()}";
                    FluentDefinitionOrUpdateStage nextStage = new FluentDefinitionOrUpdateStage(this.resourceName, interfaceName);
                    //
                    initalStages.Add(nextStage);
                    //
                    nextStage.Methods.Add(method);
                    //
                    if (currentStage != null)
                    {
                        currentStage.Methods.ForEach(m =>
                        {
                            m.NextStage = nextStage;
                        });
                    }
                    currentStage = nextStage;
                }
            }
            return initalStages;
        }
    }
}
