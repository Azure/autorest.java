// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class FluentModelMemberVariablesForUpdate : FluentModelMemberVariables
    {
        private List<FluentDefinitionOrUpdateStage> updateStages;
        private FluentModelDisambiguatedMemberVariables disambiguatedMemberVariables;
        private List<string> propertiesOfPayloadToSkip;
        protected readonly string resourceName;

        private readonly string package = Settings.Instance.Namespace.ToLower();

        public FluentModelMemberVariablesForUpdate() : base(null)
        {
            this.FluentMethodGroup = null;
            this.updateStages = null;
            this.propertiesOfPayloadToSkip = null;
        }

        public FluentModelMemberVariablesForUpdate(FluentMethodGroup fluentMethodGroup, List<string> propertiesOfPayloadToSkip) :
            base(fluentMethodGroup.ResourceUpdateDescription.SupportsUpdating ? fluentMethodGroup.ResourceUpdateDescription.UpdateMethod : null)
        {
            this.FluentMethodGroup = fluentMethodGroup;
            this.updateStages = null;
            this.propertiesOfPayloadToSkip = propertiesOfPayloadToSkip;
            this.resourceName = fluentMethodGroup.StandardFluentModel.JavaInterfaceName.ToLower();
        }

        public FluentModelMemberVariablesForUpdate(FluentMethodGroup fluentMethodGroup) : this(fluentMethodGroup, new List<string>())
        {
        }

        public void SetDisambiguatedMemberVariables(FluentModelDisambiguatedMemberVariables dMemberVariables)
        {
            this.disambiguatedMemberVariables = dMemberVariables;
        }

        /// <summary>
        /// The fluent method group containing the fluent method for update, whose parameters are used to
        /// derive the update member variables.
        /// </summary>
        public FluentMethodGroup FluentMethodGroup { get; private set; }


        /// <summary>
        /// The imports required for the types used in the nested resource update interfaces.
        /// </summary>
        public virtual HashSet<string> ImportsForInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (!SupportsUpdating)
                {
                    return imports;
                }

                this.NotParentRefNotPositionalPathAndNotPayloadInnerMemberVariables
                    .Select(v => v.FromParameter)
                    .SelectMany(p => Utils.ParameterImportsForInterface(p, package))
                    .ForEach(import =>
                    {
                        imports.Add(import);
                    });

                FluentModelMemberVariable payloadInnerModel = this.PayloadInnerModelVariable;
                if (payloadInnerModel != null)
                {
                    Utils.ParameterImportsForInterface(this.PayloadInnerModelVariable.FromParameter, this.package, this.propertiesOfPayloadToSkip)
                        .ForEach(import =>
                        {
                            if (!import.EndsWith(payloadInnerModel.VariableTypeName))
                            {
                                imports.Add(import);
                            }
                        });
                }
                return imports;
            }
        }

        /// <summary>
        /// The imports required for the types used in the nested resource update interface implementation and
        /// other update specific types.
        /// </summary>
        public virtual HashSet<string> ImportsForImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (!SupportsUpdating)
                {
                    return imports;
                }

                this.NotParentRefNotPositionalPathAndNotPayloadInnerMemberVariables
                    .Select(v => v.FromParameter)
                    .SelectMany(p => Utils.ParameterImportsForImpl(p, package))
                    .ForEach(import =>
                    {
                        imports.Add(import);
                    });

                if (this.PayloadInnerModelVariable != null)
                {
                    Utils.ParameterImportsForImpl(this.PayloadInnerModelVariable.FromParameter, this.package, this.propertiesOfPayloadToSkip)
                        .ForEach(import =>
                        {
                            imports.Add(import);
                        });
                }
                return imports;
            }
        }


        protected override bool IsRequiredParamter(ParameterJv parameter)
        {
            return parameter != null
                && !string.IsNullOrWhiteSpace(parameter.Name)
                && (parameter.IsRequired || parameter.Location == Core.Model.ParameterLocation.Body);
        }

        public virtual List<FluentDefinitionOrUpdateStage> UpdateStages()
        {
            if (this.updateStages != null)
            {
                return this.updateStages;
            }

            this.updateStages = new List<FluentDefinitionOrUpdateStage>();
            if (!this.SupportsUpdating)
            {
                return this.updateStages;
            }

            var dmvs = this.disambiguatedMemberVariables ?? throw new ArgumentNullException("dMemberVariables");

            FluentDefinitionOrUpdateStage updateGrouping = new FluentDefinitionOrUpdateStage(this.resourceName, "Update");

            // During resource update changing parent ref properties and other path properties are not allowed
            //
            IEnumerable<FluentModelMemberVariable> nonExpandableUpdatableMemberVariables = this.NotParentRefNotPositionalPathAndNotPayloadInnerMemberVariables;
            foreach (var memberVariable in nonExpandableUpdatableMemberVariables)
            {
                string methodName = $"with{memberVariable.FromParameter.Name.ToPascalCase()}";
                string parameterName = memberVariable.VariableName;
                string methodParameterDecl = $"{memberVariable.VariableTypeName} {parameterName}";
                FluentDefinitionOrUpdateStageMethod method = new FluentDefinitionOrUpdateStageMethod(methodName, methodParameterDecl, memberVariable.VariableType)
                {
                    CommentFor = new Dictionary<string, string> { { parameterName, memberVariable.FromParameter.Documentation } },
                    Body = $"{(dmvs.MemeberVariablesForUpdate[memberVariable.VariableName]).VariableAccessor} = {parameterName};"
                };

                string interfaceName = $"With{memberVariable.FromParameter.Name.ToPascalCase()}";
                FluentDefinitionOrUpdateStage stage = new FluentDefinitionOrUpdateStage(this.resourceName, interfaceName);
                this.updateStages.Add(stage);

                stage.Methods.Add(method);
                stage.Methods.ForEach(m =>
                {
                    m.NextStage = updateGrouping;
                });
            }

            var payloadInnerModelVariable = this.PayloadInnerModelVariable;
            if (payloadInnerModelVariable != null)
            {
                string payloadInnerModelVariableName = payloadInnerModelVariable.VariableName;

                CompositeTypeJvaf payloadType = (CompositeTypeJvaf)payloadInnerModelVariable.FromParameter.ClientType;

                var payloadOptionalProperties = payloadType
                    .ComposedProperties
                    .Where(p => !p.IsReadOnly && !p.IsRequired)
                    .Where(p => !propertiesOfPayloadToSkip.Contains(p.Name.ToString(), StringComparer.OrdinalIgnoreCase))
                    .OrderBy(p => p.Name.ToLowerInvariant());

                foreach (Property pro in payloadOptionalProperties)
                {

                    string methodName = $"with{pro.Name.ToPascalCase()}";
                    string parameterName = pro.Name;
                    string methodParameterDecl = $"{pro.ModelTypeName} {parameterName}";
                    FluentDefinitionOrUpdateStageMethod method = new FluentDefinitionOrUpdateStageMethod(methodName, methodParameterDecl, pro.ModelType as IModelTypeJv)
                    {
                        CommentFor = new Dictionary<string, string> { { parameterName, pro.Documentation } },
                        Body = $"{(dmvs.MemeberVariablesForUpdate[payloadInnerModelVariableName]).VariableAccessor}.{methodName}({parameterName});"
                    };

                    string interfaceName = $"With{pro.Name.ToPascalCase()}";
                    FluentDefinitionOrUpdateStage stage = new FluentDefinitionOrUpdateStage(this.resourceName, interfaceName);
                    this.updateStages.Add(stage);

                    stage.Methods.Add(method);
                    stage.Methods.ForEach(m =>
                    {
                        m.NextStage = updateGrouping;
                    });
                }
            }
            return this.updateStages;
        }

        private bool SupportsUpdating
        {
            get
            {
                return this.FluentMethodGroup.ResourceUpdateDescription.SupportsUpdating;
            }
        }
    }
}
