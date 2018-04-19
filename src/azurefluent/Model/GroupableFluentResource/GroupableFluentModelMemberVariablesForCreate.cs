// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class GroupableFluentModelMemberVariablesForCreate : FluentModelMemberVariablesForCreate
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        public GroupableFluentModelMemberVariablesForCreate() : base()
        {
        }

        public GroupableFluentModelMemberVariablesForCreate(FluentMethodGroup fluentMethodGroup) : base (fluentMethodGroup, ARMTrackedResourceProperties)
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
