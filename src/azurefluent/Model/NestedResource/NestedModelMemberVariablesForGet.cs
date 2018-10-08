// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The specialized variant of NestedModelMemberVariablesForGet holding member variables corrosponding to 'Nested Resource Get' method parameters.
    /// Nested Resource: Represents an Azure resource whose level > 0 and hence has immediate parent resource other than resource group.
    ///
    /// 'Nested Resource Implementation' extends from one of the following depending on it's capability:
    ///     If Creatable/Updatable: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/CreatableUpdatableImpl.java
    ///     If Retrievable: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/IndexableRefreshableWrapperImpl.java
    ///     Otherwise: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/WrapperImpl.java
    /// </summary>
    public class NestedModelMemberVariablesForGet : FluentModelMemberVariablesForGet
    {
        /// <summary>
        /// Creates NestedModelMemberVariablesForGet holding member variables corrosponding to 'Get' method parameters.
        /// </summary>
        public NestedModelMemberVariablesForGet() : base()
        {
        }

        /// <summary>
        /// Creates NestedModelMemberVariablesForGet holding member variables corrosponding to 'Get' method parameters.
        /// </summary>
        /// <param name="fluentMethodGroup">The method group that the 'Get' method belongs to</param>
        public NestedModelMemberVariablesForGet(SegmentFluentMethodGroup fluentMethodGroup) : base(fluentMethodGroup, 
            fluentMethodGroup.ResourceGetDescription.SupportsGetByImmediateParent ? 
                fluentMethodGroup.ResourceGetDescription.GetByImmediateParentMethod : null)
        {
        }
    }
}
