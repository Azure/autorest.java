// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The specialized variant of NestedModelMemberVariablesForUpdate holding member variables corrosponding to 'Nested Resource Update' method parameters.
    /// Nested Resource: Represents an Azure resource whose level > 0 and hence has immediate parent resource other than resource group.
    /// 
    /// These member variable descriptions are used to
    ///     1. Derive nested interfaces of 'Nested Resource Interface' describing stages of resource update we call them 'Update Stage Nested Interfaces'.
    ///     2. Derive  member variables of a Java implementation class (aka Nested Resource Implementation) that implements 'Nested Resource Interface' and it's nested interfaces.
    /// 
    /// 'Definition Stage Nested Interfaces' contains methods that takes values for corrosponding member variables and can be categorized into two.
    ///     a. Those takes required parameters: 'Required Definition Stage Nested Interfaces'
    ///     b. Those takes optional parameters: 'Optional Definition Stage Nested Interfaces'
    ///   
    /// 'Nested Resource Implementation' extends from one of the following depending on it's capability:
    ///     If Creatable/Updatable: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/CreatableUpdatableImpl.java
    ///     If Retrievable: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/IndexableRefreshableWrapperImpl.java
    ///     Otherwise: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/WrapperImpl.java
    /// </summary>
    public class NestedModelMemberVariablesForUpdate : FluentModelMemberVariablesForUpdate
    {
        /// <summary>
        /// Creates NestedModelMemberVariablesForUpdate holding member variables corrosponding to 'Update' method parameters.
        /// </summary>
        public NestedModelMemberVariablesForUpdate() : base()
        {
        }

        /// <summary>
        /// Creates NestedModelMemberVariablesForUpdate holding member variables corrosponding to 'Update' method parameters.
        /// </summary>
        /// <param name="fluentMethodGroup">The method group that the 'Update' method belongs to</param>
        public NestedModelMemberVariablesForUpdate(SegmentFluentMethodGroup fluentMethodGroup) : 
            base (fluentMethodGroup)
        {
        }
    }
}
