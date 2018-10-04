// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The specialized variant of FluentModelMemberVariablesForUpdate holding member variables corrosponding to 'Non-Groupable TopLevel Resource Update' method parameters.
    /// Non-Groupable TopLevel Resource: Represents an Azure resource that is at level 0 and is Not a Tracked Resource [see Utils.IsTrackedResource(param)]
    /// 
    /// These member variable descriptions are used to
    ///     1. Derive nested interfaces of 'Non-Groupable TopLevel Resource Interface' describing stages of resource update we call them 'Update Stage Nested Interfaces'.
    ///     2. Derive  member variables of a Java implementation class (aka Non-Groupable TopLevel Resource Implementation) that implements 'Non-Groupable TopLevel Resource Interface' and it's nested interfaces.
    /// 
    /// 'Update Stage Nested Interface' contains methods that takes values for corrosponding member variables and can be categorized into two.
    ///     a. Those takes required parameters: 'Required Definition Stage Nested Interfaces'
    ///     b. Those takes optional parameters: 'Optional Definition Stage Nested Interfaces'
    ///   
    /// 'Non-Groupable TopLevel Resource Implementation' extends from one of the following depending on it's capability:
    ///     If Creatable/Updatable: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/CreatableUpdatableImpl.java
    ///     If Retrievable: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/IndexableRefreshableWrapperImpl.java
    ///     Otherwise: https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/model/implementation/WrapperImpl.java
    /// </summary>
    public class NonGroupableTopLevelModelMemberVariablesForUpdate : FluentModelMemberVariablesForUpdate
    {
        public NonGroupableTopLevelModelMemberVariablesForUpdate() : base()
        {
        }

        public NonGroupableTopLevelModelMemberVariablesForUpdate(SegmentFluentMethodGroup fluentMethodGroup) : 
            base (fluentMethodGroup)
        {
        }
    }
}
