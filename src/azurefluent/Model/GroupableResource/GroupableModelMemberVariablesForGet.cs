// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class GroupableModelMemberVariablesForGet : FluentModelMemberVariablesForGet
    {
        public GroupableModelMemberVariablesForGet() : base()
        {
        }

        public GroupableModelMemberVariablesForGet(SegmentFluentMethodGroup fluentMethodGroup) : base(fluentMethodGroup, 
            fluentMethodGroup.ResourceGetDescription.SupportsGetByResourceGroup ? 
                fluentMethodGroup.ResourceGetDescription.GetByResourceGroupMethod : null)
        {
        }
    }
}
