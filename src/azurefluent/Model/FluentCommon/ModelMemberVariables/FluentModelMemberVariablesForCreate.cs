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
    /// <summary>
    /// Specialized variant of FluentModelMemberVariables for 'Create' method
    /// i.e. Memeber variables in this collection corresponds to 'Create' method parameter.
    /// </summary>
    public class FluentModelMemberVariablesForCreate : FluentModelMemberVariables
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();
        /// <summary>
        /// Holds description of required definition stages.
        /// </summary>
        private List<FluentDefinitionOrUpdateStage> reqDefStages;
        /// <summary>
        /// Holds description of optional definition stages.
        /// </summary>
        private List<FluentDefinitionOrUpdateStage> optDefStages;
        /// <summary>
        /// The properties in the payload parameter of 'Create' method that needs to be skipped
        /// so that we don't generate member variables for them.
        /// </summary>
        private readonly List<string> propertiesOfPayloadToSkip;
        /// <summary>
        /// The name of the java interface representing return value of 'Create' method.
        /// </summary>
        protected readonly string resourceName;

        public FluentModelMemberVariablesForCreate() : base(null)
        {
            this.FluentMethodGroup = null;
            this.reqDefStages = null;
            this.optDefStages = null;
            this.propertiesOfPayloadToSkip = null;
        }

        public FluentModelMemberVariablesForCreate(SegmentFluentMethodGroup fluentMethodGroup, List<string> propertiesOfPayloadToSkip) :
        base(fluentMethodGroup.ResourceCreateDescription.SupportsCreating ? fluentMethodGroup.ResourceCreateDescription.CreateMethod : null)
        {
            this.FluentMethodGroup = fluentMethodGroup;
            this.reqDefStages = null;
            this.optDefStages = null;
            this.propertiesOfPayloadToSkip = propertiesOfPayloadToSkip;
            this.resourceName = fluentMethodGroup.StandardFluentModel.JavaInterfaceName.ToLower();
        }

        public FluentModelMemberVariablesForCreate(SegmentFluentMethodGroup fluentMethodGroup) :
        this(fluentMethodGroup, new List<string>())
        {
        }

        /// <summary>
        /// The fluent method group containing the fluent method for Create, whose parameters are used to
        /// derive the create member variables.
        /// </summary>
        public SegmentFluentMethodGroup FluentMethodGroup { get; private set; }

        #region DisambiguatedMemberVariables operations

        private FluentModelDisambiguatedMemberVariables disambiguatedMemberVariables;
        public virtual void SetDisambiguatedMemberVariables(FluentModelDisambiguatedMemberVariables dMemberVariables)
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
        /// The imports required for the types used in the "Nested Resource Defintion Stage" interfaces.
        /// </summary>
        public virtual HashSet<string> ImportsForInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (!SupportsCreating)
                {
                    return imports;
                }

                // 1. Imports for positional path parameters and memeber variables other than parent ref and composite payload.
                //
                this.NotParentRefNotCompositePayloadButPositionalAndOtherMemberVariables
                    .Select(v => v.FromParameter)
                    .SelectMany(p => Utils.ParameterImportsForInterface(p, package))
                    .ForEach(import =>
                    {
                        imports.Add(import);
                    });
                // 2. Imports for composite payload if there is one.
                //
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
        /// The imports required for the types used in the "Nested Resource Defintion Stage" interfaces implementation.
        /// </summary>
        public virtual HashSet<string> ImportsForImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (!SupportsCreating)
                {
                    return imports;
                }

                // 1. Imports for positional path parameters and memeber variables other than parent ref and composite payload.
                //
                this.NotParentRefNotCompositePayloadButPositionalAndOtherMemberVariables
                    .Select(v => v.FromParameter)
                    .SelectMany(p => Utils.ParameterImportsForImpl(p, package))
                    .ForEach(import =>
                    {
                        imports.Add(import);
                    });
                // 2. Imports for composite payload if there is one.
                //
                FluentModelMemberVariable compositePayloadVariable = this.CompositePayloadVariable;
                if (compositePayloadVariable != null)
                {
                    Utils.ParameterImportsForImpl(compositePayloadVariable.FromParameter, this.package, this.propertiesOfPayloadToSkip)
                        .ForEach(import =>
                        {
                            imports.Add(import);
                        });
                }
                return imports;
            }
        }

        /// <summary>
        /// Create and return "Required Definition Stages" from the member variables.
        /// </summary>
        /// <returns></returns>
        public virtual List<FluentDefinitionOrUpdateStage> RequiredDefinitionStages()
        {
            return this.RequiredDefinitionStages(null);
        }

        /// <summary>
        /// Create and return "Optional Definition Stages" from the member variables.
        /// </summary>
        public virtual List<FluentDefinitionOrUpdateStage> OptionalDefinitionStages()
        {
            return this.OptionalDefinitionStages(null);
        }

        /// <summary>
        /// Create "Required Definition Stages" from the member variables, prepend the provided stages
        /// and return.
        /// </summary>
        /// <returns></returns>
        protected List<FluentDefinitionOrUpdateStage> RequiredDefinitionStages(List<FluentDefinitionOrUpdateStage> initialStages)
        {
            if (this.reqDefStages != null)
            {
                return this.reqDefStages;
            }

            this.reqDefStages = new List<FluentDefinitionOrUpdateStage>();
            if (!this.SupportsCreating)
            {
                return this.reqDefStages;
            }

            var dmvs = this.disambiguatedMemberVariables ?? throw new ArgumentNullException("dMemberVariables");
            FluentDefinitionOrUpdateStage currentStage = null;

            if (initialStages != null)
            {
                this.reqDefStages.AddRange(initialStages);
                currentStage = this.reqDefStages.LastOrDefault();
            }

            // 1. stages for setting create arguments except "create body payload"
            //
            foreach (var memberVariable in this.NotParentRefNotCompositePayloadButPositionalAndOtherMemberVariables)
            {
                string methodName = $"with{memberVariable.FromParameter.Name.ToPascalCase()}";
                string parameterName = memberVariable.VariableName;
                string methodParameterDecl = $"{memberVariable.VariableTypeName} {parameterName}";
                FluentDefinitionOrUpdateStageMethod method = new FluentDefinitionOrUpdateStageMethod(methodName, methodParameterDecl, memberVariable.VariableType)
                {
                    CommentFor = new Dictionary<string, string> {{ parameterName, memberVariable.FromParameter.Documentation }},
                    Body = $"{(dmvs.MemeberVariablesForCreate[memberVariable.VariableName]).VariableAccessor} = {parameterName};"
                };

                string interfaceName = $"With{memberVariable.FromParameter.Name.ToPascalCase()}";
                FluentDefinitionOrUpdateStage nextStage = new FluentDefinitionOrUpdateStage(this.resourceName, interfaceName);
                this.reqDefStages.Add(nextStage);
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

            // 2. stages for setting required properties of "create body composite payload"
            //
            var compositePayloadVariable = this.CompositePayloadVariable;
            if (compositePayloadVariable != null)
            {
                string payloadInnerModelVariableName = compositePayloadVariable.VariableName;

                CompositeTypeJvaf payloadType = (CompositeTypeJvaf)compositePayloadVariable.FromParameter.ClientType;

                var payloadRequiredProperties = payloadType.ComposedProperties
                    .Where(p => !p.IsReadOnly && p.IsRequired)
                    .Where(p => !propertiesOfPayloadToSkip.Contains(p.Name.ToString(), StringComparer.OrdinalIgnoreCase))
                    .OrderBy(p => p.Name.ToLowerInvariant());

                foreach (Property pro in payloadRequiredProperties)
                {
                    string methodName = $"with{pro.Name.ToPascalCase()}";
                    string parameterName = pro.Name;
                    string methodParameterDecl = $"{pro.ModelTypeName} {parameterName}";
                    FluentDefinitionOrUpdateStageMethod method = new FluentDefinitionOrUpdateStageMethod(methodName, methodParameterDecl, pro.ModelType as IModelTypeJv)
                    {
                        CommentFor = new Dictionary<string, string> { { parameterName, pro.Documentation } },
                        Body = $"{(dmvs.MemeberVariablesForCreate[payloadInnerModelVariableName]).VariableAccessor}.{methodName}({parameterName});"
                    };

                    string interfaceName = $"With{pro.Name.ToPascalCase()}";
                    FluentDefinitionOrUpdateStage nextStage = new FluentDefinitionOrUpdateStage(this.resourceName, interfaceName);
                    this.reqDefStages.Add(nextStage);

                    nextStage.Methods.Add(method);
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

            FluentDefinitionOrUpdateStage creatableStage = new FluentDefinitionOrUpdateStage(this.resourceName, "WithCreate");
            if (currentStage != null)
            {
                currentStage.Methods.ForEach(m =>
                {
                    m.NextStage = creatableStage;
                });
            }
            return this.reqDefStages;
        }

        /// <summary>
        /// Create "Optional Definition Stages" from the member variables, prepend the provided stages
        /// and return.
        /// </summary>
        /// <returns></returns>
        protected List<FluentDefinitionOrUpdateStage> OptionalDefinitionStages(List<FluentDefinitionOrUpdateStage> initialStages)
        {
            if (this.optDefStages != null)
            {
                return this.optDefStages;
            }

            this.optDefStages = new List<FluentDefinitionOrUpdateStage>();
            if (!this.SupportsCreating)
            {
                return this.optDefStages;
            }

            var dmvs = this.disambiguatedMemberVariables ?? throw new ArgumentNullException("dMemberVariables");

            var payloadInnerModelVariable = this.CompositePayloadVariable;
            // Stages for setting optional properties of "create body payload"
            //
            if (payloadInnerModelVariable != null)
            {
                string payloadInnerModelVariableName = payloadInnerModelVariable.VariableName;

                CompositeTypeJvaf payloadType = (CompositeTypeJvaf)payloadInnerModelVariable.FromParameter.ClientType;

                var payloadOptinalProperties = payloadType
                    .ComposedProperties
                    .Where(p => !p.IsReadOnly && !p.IsRequired)
                    .Where(p => !propertiesOfPayloadToSkip.Contains(p.Name.ToString(), StringComparer.OrdinalIgnoreCase))
                    .OrderBy(p => p.Name.ToLowerInvariant());

                FluentDefinitionOrUpdateStage creatableStage = new FluentDefinitionOrUpdateStage(this.resourceName, "WithCreate");
                foreach (Property pro in payloadOptinalProperties)
                {
                    string methodName = $"with{pro.Name.ToPascalCase()}";
                    string parameterName = pro.Name;
                    string methodParameterDecl = $"{pro.ModelTypeName} {parameterName}";
                    FluentDefinitionOrUpdateStageMethod method = new FluentDefinitionOrUpdateStageMethod(methodName, methodParameterDecl, pro.ModelType as IModelTypeJv)
                    {
                        CommentFor = new Dictionary<string, string> { { parameterName, pro.Documentation } },
                        Body = $"{(dmvs.MemeberVariablesForCreate[payloadInnerModelVariableName]).VariableAccessor}.{methodName}({parameterName});"
                    };

                    string interfaceName = $"With{pro.Name.ToPascalCase()}";
                    FluentDefinitionOrUpdateStage stage = new FluentDefinitionOrUpdateStage(this.resourceName, interfaceName);
                    this.optDefStages.Add(stage);

                    stage.Methods.Add(method);
                    stage.Methods.ForEach(m =>
                    {
                        m.NextStage = creatableStage;
                    });
                }
            }
            return this.optDefStages;
        }

        /// <summary>
        /// Return true if 'Create' is supported hence this collection contain corresponding memeber
        /// variables, false otherwise.
        /// </summary>
        protected bool SupportsCreating
        {
            get
            {
                return this.FluentMethodGroup.ResourceCreateDescription.SupportsCreating;
            }
        }
    }
}
