// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class NonGroupableTopLevelFluentModelMemberVariablesForGet : FluentModelMemberVariablesForGet
    {
        public NonGroupableTopLevelFluentModelMemberVariablesForGet() : base()
        {
        }

        public NonGroupableTopLevelFluentModelMemberVariablesForGet(FluentMethodGroup fluentMethodGroup) : 
            base(fluentMethodGroup, 
                fluentMethodGroup.ResourceGetDescription.SupportsGetByResourceGroup ? 
                    fluentMethodGroup.ResourceGetDescription.GetByResourceGroupMethod : 
                        fluentMethodGroup.ResourceGetDescription.SupportsGetBySubscription ? 
                            fluentMethodGroup.ResourceGetDescription.GetBySubscriptionMethod : 
                                fluentMethodGroup.ResourceGetDescription.SupportsGetByParameterizedParent?  
                                    fluentMethodGroup.ResourceGetDescription.GetByParameterizedParentMethod : null)
        {
        }
    }
}
