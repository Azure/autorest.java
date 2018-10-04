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
    /// Specialized variant of FluentModelMemberVariables for create method of a nested resource.
    /// </summary>
    public class NestedModelMemberVariablesForCreate : FluentModelMemberVariablesForCreate
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        public NestedModelMemberVariablesForCreate() : base()
        {
        }

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

            // 1. first stage to set the ancestors (parents)
            //
            List<FluentDefinitionOrUpdateStage> initialStages = new List<FluentDefinitionOrUpdateStage>()
            {
                FirstDefintionStage(this.ParentRefMemberVariables)
            };
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
        /// Given the ancestors of this 
        /// </summary>
        /// <param name="parentRefMemberVariables"></param>
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
