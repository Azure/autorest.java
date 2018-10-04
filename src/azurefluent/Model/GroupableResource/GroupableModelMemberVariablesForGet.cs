// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The specialized variant of GroupableModelMemberVariablesForGet holding member variables corrosponding to 'Groupable Resource Get' method parameters.
    /// Groupable Resource: Represents an Azure resource that appear immediately under Resource Group and is a Tracked Resource [see Utils.IsTrackedResource(param)]
    /// 
    /// 'Groupable Resource' types extends from:
    ///     https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/resources/collection/implementation/GroupableResourcesCoreImpl.java
    ///  The inner type of such a type extends from 'com.microsoft.azure.Resource' and thus requires id, type, name, location and tags properties.
    ///     https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-client-runtime/src/main/java/com/microsoft/azure/Resource.java
    /// </summary>
    public class GroupableModelMemberVariablesForGet : FluentModelMemberVariablesForGet
    {
        /// <summary>
        /// Creates GroupableModelMemberVariablesForGet holding member variables corrosponding to 'Get' method parameters.
        /// </summary>
        public GroupableModelMemberVariablesForGet() : base()
        {
        }

        /// <summary>
        /// Creates GroupableModelMemberVariablesForGet holding member variables corrosponding to 'Get' method parameters.
        /// </summary>
        /// <param name="fluentMethodGroup">The method group that the 'Get' method belongs to</param>
        public GroupableModelMemberVariablesForGet(SegmentFluentMethodGroup fluentMethodGroup) : base(fluentMethodGroup, 
            fluentMethodGroup.ResourceGetDescription.SupportsGetByResourceGroup ? 
                fluentMethodGroup.ResourceGetDescription.GetByResourceGroupMethod : null)
        {
        }
    }
}
