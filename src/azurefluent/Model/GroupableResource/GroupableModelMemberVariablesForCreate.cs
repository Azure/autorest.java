// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Java.Model;
using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The specialized variant of FluentModelMemberVariablesForCreate holding member variables corrosponding to 'Groupable Resource Create' method parameters.
    /// Groupable Resource: Represents an Azure resource that appear immediately under Resource Group and is a Tracked Resource [see Utils.IsTrackedResource(param)]
    /// 
    /// These member variable descriptions are used to
    ///     1. Derive nested interfaces of 'Groupable Resource Interface' describing stages of resource definition/create we call them 'Definition Stage Nested Interfaces'.
    ///     2. Derive  member variables of a Java implementation class (aka Groupable Resource Implementation) that implements 'Groupable Resource Interface' and it's nested interfaces.
    /// 
    /// 'Definition Stage Nested Interfaces' contains methods that takes values for corrosponding member variables and can be categorized into two.
    ///     a. Those takes required parameters: 'Required Definition Stage Nested Interfaces'
    ///     b. Those takes optional parameters: 'Optional Definition Stage Nested Interfaces'
    ///   
    /// 'Groupable Resource Implementation' extends from:
    ///     https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/resources/collection/implementation/GroupableResourcesCoreImpl.java
    ///  The inner type of 'Groupable Resource' (e.g. VirtualMachineInner) extends from 'com.microsoft.azure.Resource' and thus requires id, type, name, location and tags properties.
    ///     https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-client-runtime/src/main/java/com/microsoft/azure/Resource.java
    /// </summary>
    public class GroupableModelMemberVariablesForCreate : FluentModelMemberVariablesForCreate
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        /// <summary>
        /// Creates GroupableModelMemberVariablesForCreate holding member variables corrosponding to 'Create' method parameters.
        /// </summary>
        public GroupableModelMemberVariablesForCreate() : base()
        {
        }

        /// <summary>
        /// Creates GroupableModelMemberVariablesForCreate holding member variables corrosponding to 'Create' method parameters.
        /// </summary>
        /// <param name="fluentMethodGroup">The method group that the 'Create' method belongs to</param>
        public GroupableModelMemberVariablesForCreate(SegmentFluentMethodGroup fluentMethodGroup) : base (fluentMethodGroup, ARMTrackedResourceProperties)
        {
        }


        public override void SetDisambiguatedMemberVariables(FluentModelDisambiguatedMemberVariables dMemberVariables)
        {
            base.SetDisambiguatedMemberVariables(dMemberVariables);
        }

        /// <summary>
        /// Returns the imports required by the types used in 'Definition Stage Nested Interfaces'.
        /// </summary>
        public override HashSet<string> ImportsForInterface
        {
            get
            {
                return base.ImportsForInterface;
            }
        }

        /// <summary>
        /// Returns the imports required by a type that implements 'Definition Stage Nested Interfaces'.
        /// </summary>
        public override HashSet<string> ImportsForImpl
        {
            get
            {
                return base.ImportsForImpl;
            }
        }

        /// <summary>
        /// Checks the given parameter is required so that it should be considered while building 'Required Definition Stage
        /// Nested Interfaces'.
        /// </summary>
        /// <param name="parameter">the parameter</param>
        /// <returns></returns>
        protected override bool IsRequiredParamter(ParameterJv parameter)
        {
            return parameter != null
                && !string.IsNullOrWhiteSpace(parameter.Name)
                && (parameter.IsRequired || parameter.Location == Core.Model.ParameterLocation.Body);
        }

        /// <summary>
        /// Returns list of descriptions of 'Required Definition Stage Nested Interfaces'.
        /// </summary>
        public override List<FluentDefinitionOrUpdateStage> RequiredDefinitionStages()
        {
            return base.RequiredDefinitionStages(null);
        }

        /// <summary>
        /// Returns list of descriptions of 'Optional Definition Stage Nested Interfaces'.
        /// </summary>
        public override List<FluentDefinitionOrUpdateStage> OptionalDefinitionStages()
        {
            return base.OptionalDefinitionStages(null);
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
