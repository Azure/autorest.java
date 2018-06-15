// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class NestedFluentModelMemberVariablesForGet : FluentModelMemberVariablesForGet
    {
        public NestedFluentModelMemberVariablesForGet() : base()
        {
        }

        public NestedFluentModelMemberVariablesForGet(FluentMethodGroup fluentMethodGroup) : base(fluentMethodGroup, 
            fluentMethodGroup.ResourceGetDescription.SupportsGetByImmediateParent ? 
                fluentMethodGroup.ResourceGetDescription.GetByImmediateParentMethod : null)
        {
        }
    }
}
