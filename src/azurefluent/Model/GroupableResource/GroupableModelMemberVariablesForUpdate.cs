// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The specialized variant of GroupableModelMemberVariablesForUpdate holding member variables corrosponding to 'Groupable Resource Update' method parameters.
    /// Groupable Resource: Represents an Azure resource that appear immediately under Resource Group and is a Tracked Resource [see Utils.IsTrackedResource(param)]
    /// 
    /// These member variable descriptions are used to
    ///     1. Derive nested interfaces of 'Groupable Resource Interface' describing stages of resource update we call them 'Update Stage Nested Interfaces'.
    ///     2. Derive  member variables of a Java implementation class (aka Groupable Resource Implementation) that implements 'Groupable Resource Interface' and it's nested interfaces.
    /// 
    /// 'Update Stage Nested Interface' contains methods that takes values for corrosponding member variables.
    ///   
    /// 'Groupable Resource Implementation' extends from:
    ///     https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/resources/collection/implementation/GroupableResourcesCoreImpl.java
    ///  The inner type of 'Groupable Resource' (e.g. VirtualMachineInner) extends from 'com.microsoft.azure.Resource' and thus requires id, type, name, location and tags properties.
    ///     https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-client-runtime/src/main/java/com/microsoft/azure/Resource.java
    /// </summary>
    public class GroupableModelMemberVariablesForUpdate : FluentModelMemberVariablesForUpdate
    {
        /// <summary>
        /// Creates FluentModelMemberVariablesForUpdate holding member variables corrosponding to 'Update' method parameters.
        /// </summary>
        public GroupableModelMemberVariablesForUpdate() : base()
        {
        }

        /// <summary>
        /// Creates FluentModelMemberVariablesForUpdate holding member variables corrosponding to 'Update' method parameters.
        /// </summary>
        /// <param name="fluentMethodGroup">The method group that the 'Create' method belongs to</param>
        public GroupableModelMemberVariablesForUpdate(SegmentFluentMethodGroup fluentMethodGroup) : 
            base (fluentMethodGroup, ARMTrackedResourceProperties)
        {
        }

        /// <summary>
        /// The name of the properties of ARMTrackedResource.
        /// Definition of TrackedResource: https://github.com/Azure/azure-rest-api-specs/blob/64266364a9517d82448d09622b70ff753a9fbaa9/specification/common-types/resource-management/v1/types.json#L45
        /// </summary>
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
