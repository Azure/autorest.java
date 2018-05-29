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
    /// Specialized variant of FluentModelMemberVariables for create method.
    /// </summary>
    public class FluentModelMemberVariablesForCreate : FluentModelMemberVariables
    {
        private List<FluentDefinitionOrUpdateStage> reqDefStages;
        private List<FluentDefinitionOrUpdateStage> optDefStages;
        private FluentModelDisambiguatedMemberVariables disambiguatedMemberVariables;
        private List<string> propertiesOfPayloadToSkip;

        private readonly string package = Settings.Instance.Namespace.ToLower();
        protected readonly string resourceName;

        public FluentModelMemberVariablesForCreate() : base(null)
        {
            this.FluentMethodGroup = null;
            this.reqDefStages = null;
            this.optDefStages = null;
            this.propertiesOfPayloadToSkip = null;
        }

        public FluentModelMemberVariablesForCreate(FluentMethodGroup fluentMethodGroup, List<string> propertiesOfPayloadToSkip) :
        base(fluentMethodGroup.ResourceCreateDescription.SupportsCreating ? fluentMethodGroup.ResourceCreateDescription.CreateMethod : null)
        {
            this.FluentMethodGroup = fluentMethodGroup;
            this.reqDefStages = null;
            this.optDefStages = null;
            this.propertiesOfPayloadToSkip = propertiesOfPayloadToSkip;
            this.resourceName = fluentMethodGroup.StandardFluentModel.JavaInterfaceName.ToLower();
        }

        public FluentModelMemberVariablesForCreate(FluentMethodGroup fluentMethodGroup) :
        this(fluentMethodGroup, new List<string>())
        {
        }

        public FluentMethodGroup FluentMethodGroup { get; private set; }

        public virtual void SetDisambiguatedMemberVariables(FluentModelDisambiguatedMemberVariables dMemberVariables)
        {
            this.disambiguatedMemberVariables = dMemberVariables;
        }

        public FluentModelDisambiguatedMemberVariables DisambiguatedMemberVariables
        {
            get
            {
                return this.disambiguatedMemberVariables;
            }
        }

        /// <summary>
        /// The imports required for the types used in the nested resource defintion interfaces.
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

                this.PositionalPathAndNotPayloadInnerMemberVariables
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
        /// The imports required for the types used in the nested resource defintion interface implementation
        /// other definition specific types.
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
                this.PositionalPathAndNotPayloadInnerMemberVariables
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

        public virtual List<FluentDefinitionOrUpdateStage> RequiredDefinitionStages()
        {
            return this.RequiredDefinitionStages(null);
        }

        public virtual List<FluentDefinitionOrUpdateStage> OptionalDefinitionStages()
        {
            return this.OptionalDefinitionStages(null);
        }

        /// <summary>
        /// Derive and return required definition stages from the create member variables.
        /// </summary>
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
            foreach (var memberVariable in this.PositionalPathAndNotPayloadInnerMemberVariables)
            {
                string methodName = $"with{memberVariable.FromParameter.Name.ToPascalCase()}";
                string parameterName = memberVariable.VariableName;
                string methodParameterDecl = $"{memberVariable.VariableTypeName} {parameterName}";
                FluentDefinitionOrUpdateStageMethod method = new FluentDefinitionOrUpdateStageMethod(methodName, methodParameterDecl, memberVariable.VariableType)
                {
                    CommentFor = parameterName,
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

            // 2. stages for setting required properties of "create body payload"
            //
            var payloadInnerModelVariable = this.PayloadInnerModelVariable;
            if (payloadInnerModelVariable != null)
            {
                string payloadInnerModelVariableName = payloadInnerModelVariable.VariableName;

                CompositeTypeJvaf payloadType = (CompositeTypeJvaf)payloadInnerModelVariable.FromParameter.ClientType;

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
                        CommentFor = parameterName,
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
        /// Derive and return optional definition stages from the create member variables.
        /// </summary>
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

            var payloadInnerModelVariable = this.PayloadInnerModelVariable;
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
                        CommentFor = parameterName,
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


        protected bool SupportsCreating
        {
            get
            {
                return this.FluentMethodGroup.ResourceCreateDescription.SupportsCreating;
            }
        }
    }
}
