// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Java.Model;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class GroupableModelMemberVariablesForCreate : FluentModelMemberVariablesForCreate
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        public GroupableModelMemberVariablesForCreate() : base()
        {
        }

        public GroupableModelMemberVariablesForCreate(SegmentFluentMethodGroup fluentMethodGroup) : base (fluentMethodGroup, ARMTrackedResourceProperties)
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
                return base.ImportsForInterface;
            }
        }

        public override HashSet<string> ImportsForImpl
        {
            get
            {
                return base.ImportsForImpl;
            }
        }

        protected override bool IsRequiredParamter(ParameterJv parameter)
        {
            return parameter != null
                && !string.IsNullOrWhiteSpace(parameter.Name)
                && (parameter.IsRequired || parameter.Location == Core.Model.ParameterLocation.Body);
        }

        public override List<FluentDefinitionOrUpdateStage> RequiredDefinitionStages()
        {
            return base.RequiredDefinitionStages(null);
        }

        public override List<FluentDefinitionOrUpdateStage> OptionalDefinitionStages()
        {
            return base.OptionalDefinitionStages(null);
        }

        private static List<string> ARMTrackedResourceProperties
        {
            get
            {
                return new List<string>
                {
                    "id",
                    "type",
                    "name",
                    "location",
                    "tags"
                };
            }
        }
    }
}
