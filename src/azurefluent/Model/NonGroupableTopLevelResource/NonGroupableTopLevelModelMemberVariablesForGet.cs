// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The specialized variant of FluentModelMemberVariablesForUpdate holding member variables corrosponding to 'Non-Groupable TopLevel Resource Get' method parameters.
    /// Non-Groupable TopLevel Resource: Represents an Azure resource that is at level 0 and is Not a Tracked Resource [see Utils.IsTrackedResource(param)]
    ///
    /// 'Non-Groupable TopLevel Resource Implementation' extends from one of the following depending on it's capability:
    ///     If Creatable/Updatable: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/CreatableUpdatableImpl.java
    ///     If Retrievable: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/IndexableRefreshableWrapperImpl.java
    ///     Otherwise: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/WrapperImpl.java
    /// </summary>
    public class NonGroupableTopLevelModelMemberVariablesForGet : FluentModelMemberVariablesForGet
    {
        public NonGroupableTopLevelModelMemberVariablesForGet() : base()
        {
        }

        public NonGroupableTopLevelModelMemberVariablesForGet(SegmentFluentMethodGroup fluentMethodGroup) : 
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
