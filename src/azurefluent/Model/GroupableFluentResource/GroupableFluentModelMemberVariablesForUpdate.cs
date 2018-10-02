// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class GroupableFluentModelMemberVariablesForUpdate : FluentModelMemberVariablesForUpdate
    {
        public GroupableFluentModelMemberVariablesForUpdate() : base()
        {
        }

        public GroupableFluentModelMemberVariablesForUpdate(SegmentFluentMethodGroup fluentMethodGroup) : 
            base (fluentMethodGroup, ARMTrackedResourceProperties)
        {
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
