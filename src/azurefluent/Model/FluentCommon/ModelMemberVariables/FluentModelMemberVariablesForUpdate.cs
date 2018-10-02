﻿// Copyright (c) Microsoft Corporation. All rights reserved.
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
    /// <summary>
    /// Specialized variant of FluentModelMemberVariables for 'Update' method
    /// i.e. Memeber variables in this collection corresponds to 'Update' method parameter.
    /// </summary>
    public class FluentModelMemberVariablesForUpdate : FluentModelMemberVariables
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();
        /// <summary>
        /// Holds description of update definition stages.
        /// </summary>
        private List<FluentDefinitionOrUpdateStage> updateStages;
        /// <summary>
        /// The properties in the payload parameter of 'Update' method that needs to be skipped
        /// so that we don't generate member variables for them.
        /// </summary>
        private List<string> propertiesOfPayloadToSkip;
        /// <summary>
        /// The name of the java interface representing return value of 'Update' method.
        /// </summary>
        protected readonly string resourceName;

        public FluentModelMemberVariablesForUpdate() : base(null)
        {
            this.FluentMethodGroup = null;
            this.updateStages = null;
            this.propertiesOfPayloadToSkip = null;
        }

        public FluentModelMemberVariablesForUpdate(SegmentFluentMethodGroup fluentMethodGroup, List<string> propertiesOfPayloadToSkip) :
            base(fluentMethodGroup.ResourceUpdateDescription.SupportsUpdating ? fluentMethodGroup.ResourceUpdateDescription.UpdateMethod : null)
        {
            this.FluentMethodGroup = fluentMethodGroup;
            this.updateStages = null;
            this.propertiesOfPayloadToSkip = propertiesOfPayloadToSkip;
            this.resourceName = fluentMethodGroup.StandardFluentModel.JavaInterfaceName.ToLower();
        }

        public FluentModelMemberVariablesForUpdate(SegmentFluentMethodGroup fluentMethodGroup) : this(fluentMethodGroup, new List<string>())
        {
        }

        #region DisambiguatedMemberVariables operations

        private FluentModelDisambiguatedMemberVariables disambiguatedMemberVariables;
        public void SetDisambiguatedMemberVariables(FluentModelDisambiguatedMemberVariables dMemberVariables)
        {
            this.disambiguatedMemberVariables = dMemberVariables;
        }

        protected FluentModelDisambiguatedMemberVariables DisambiguatedMemberVariables
        {
            get
            {
                return this.disambiguatedMemberVariables;
            }
        }

        #endregion

        /// <summary>
        /// The fluent method group containing the fluent method for Update, whose parameters are used to
        /// derive the update member variables.
        /// </summary>
        public SegmentFluentMethodGroup FluentMethodGroup { get; private set; }


        /// <summary>
        /// The imports required for the types used in the "Nested Resource Update Stage" interfaces.
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

                this.NotParentRefNotPositionalPathAndNotCompositePayloadMemberVariables
                    .Select(v => v.FromParameter)
                    .SelectMany(p => Utils.ParameterImportsForInterface(p, package))
                    .ForEach(import =>
                    {
                        imports.Add(import);
                    });

                FluentModelMemberVariable compositePayloadVariable = this.CompositePayloadVariable;
                if (compositePayloadVariable != null)
                {
                    Utils.ParameterImportsForInterface(compositePayloadVariable.FromParameter, this.package, this.propertiesOfPayloadToSkip)
                        .ForEach(import =>
                        {
                            if (!import.EndsWith(compositePayloadVariable.VariableTypeName))
                            {
                                imports.Add(import);
                            }
                        });
                }
                return imports;
            }
        }

        /// <summary>
        /// The imports required for the types used in the "Nested Resource Update Stage" interfaces implementation.
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

                this.NotParentRefNotPositionalPathAndNotCompositePayloadMemberVariables
                    .Select(v => v.FromParameter)
                    .SelectMany(p => Utils.ParameterImportsForImpl(p, package))
                    .ForEach(import =>
                    {
                        imports.Add(import);
                    });

                if (this.CompositePayloadVariable != null)
                {
                    Utils.ParameterImportsForImpl(this.CompositePayloadVariable.FromParameter, this.package, this.propertiesOfPayloadToSkip)
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

        /// <summary>
        /// Create and return "Update Stages" from the member variables.
        /// </summary>
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
            IEnumerable<FluentModelMemberVariable> nonExpandableUpdatableMemberVariables = this.NotParentRefNotPositionalPathAndNotCompositePayloadMemberVariables;
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

            var payloadInnerModelVariable = this.CompositePayloadVariable;
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

        /// <summary>
        /// Return true if 'Update' is supported hence this collection contain corresponding memeber
        /// variables, false otherwise.
        /// </summary>
        private bool SupportsUpdating
        {
            get
            {
                return this.FluentMethodGroup.ResourceUpdateDescription.SupportsUpdating;
            }
        }
    }
}
