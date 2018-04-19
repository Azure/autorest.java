// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class NonGroupableTopLevelFluentModelMemberVariablesForCreate : FluentModelMemberVariablesForCreate
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        public NonGroupableTopLevelFluentModelMemberVariablesForCreate() : base()
        {
        }

        public NonGroupableTopLevelFluentModelMemberVariablesForCreate(FluentMethodGroup fluentMethodGroup) : base(fluentMethodGroup)
        {
        }

        public override void SetDisambiguatedMemberVariables(FluentModelDisambiguatedMemberVariables dMemberVariables)
        {
            base.SetDisambiguatedMemberVariables(dMemberVariables);
        }

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

        public override List<FluentDefinitionOrUpdateStage> RequiredDefinitionStages()
        {
            if (!this.SupportsCreating)
            {
                return base.RequiredDefinitionStages(null);
            }

            // 1. first stage to set the ancestors (parents)
            //
            List<FluentDefinitionOrUpdateStage> initialStages = FirstDefintionStages(this.ParentRefMemberVariables);
            return base.RequiredDefinitionStages(initialStages);
        }

        public override List<FluentDefinitionOrUpdateStage> OptionalDefinitionStages()
        {
            return base.OptionalDefinitionStages(null);
        }

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
                    FluentDefinitionOrUpdateStageMethod method = new FluentDefinitionOrUpdateStageMethod(methodName, methodParameterDecl, memberVariable.VariableTypeName)
                    {
                        CommentFor = parameterName,
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
