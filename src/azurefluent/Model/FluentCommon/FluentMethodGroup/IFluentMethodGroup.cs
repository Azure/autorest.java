// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type describing a "Fluent Method Group" from which we generate Java interface and implementation.
    /// [e.g. VirtualMachines, VirtualMachineImpl]
    /// </summary>
    public interface IFluentMethodGroup
    {
        /// <summary>
        /// The name of the manager [e.g.: ComputeManager] exposing accessor [e.g. computeManager.virtualMachines()] to this "Fluent Method Group".
        /// </summary>
        string ManagerName { get; }
        /// <summary>
        /// List of all "Fluent Method Group"s.
        /// </summary>
        SegmentFluentMethodGroups FluentMethodGroups { get; }
        /// <summary>
        /// The category of this "Fluent Method Group".
        /// </summary>
        MethodGroupType Type { get; }
        /// <summary>
        /// The name of the java interface for this "Fluent Method Group" [e.g. VirtualMachines].
        /// </summary>
        string JavaInterfaceName { get; }
        /// <summary>
        /// The singular name of the java interface for this "Fluent Method Group" [e.g. VirtualMachine].
        /// </summary>
        string SingularJavaInterfaceName { get; }
        /// <summary>
        /// The local name of this "Fluent Method Group" (which is same as the Segment in the ARM Uri that this method group represents).
        /// </summary>
        string LocalNameInPascalCase { get; }
        /// <summary>
        /// The local name of this "Fluent Method Group" in singular form.
        /// </summary>
        string LocalSingularNameInPascalCase { get; }
        /// <summary>
        /// The Standard Model of this "Fluent Method Group" [e.g. Standard model for the "Fluent Method Group" 'VirtualMachines' is 'VirtualMachine'].
        /// </summary>
        StandardModel StandardFluentModel { get; }
        /// <summary>
        /// The autorest provided "Operation Group" that this "Fluent Method Group" uses to perform apiCalls [e.g. VirtualMachinesInner].
        /// </summary>
        MethodGroupJvaf InnerMethodGroup { get; }
        /// <summary>
        /// The type name of the autorest provided "Operation Group" [e.g. VirtualMachinesInner].
        /// </summary>
        string InnerMethodGroupTypeName { get; }
        /// <summary>
        /// The name of the "Operation Group" accessor [e.g. virtualMachines()] in ClientImpl [e.g. ComputeManagementClientImpl].
        /// </summary>
        string InnerMethodGroupAccessorName { get; }
        /// <summary>
        /// The imports needed for this "Fluent Method Group" Java implementation class.
        /// </summary>
        HashSet<string> ImportsForImpl { get; }
        /// <summary>
        /// The imports needed for this "Fluent Method Group" Java interface.
        /// </summary>
        HashSet<string> ImportsForInterface { get; }
        /// <summary>
        /// The comma seperated list of types that the Java interface representing this "Fluent Method Group" extends from.
        /// </summary>
        string ExtendsFrom { get; }
        /// <summary>
        /// Describes how to create a new instance of standard model of this "Fluent Method Group".
        /// </summary>
        IResourceCreateDescription ResourceCreateDescription { get; }
        /// <summary>
        /// Describes how to update the an existing instance of standard model of this "Fluent Method Group".
        /// </summary>
        IResourceUpdateDescription ResourceUpdateDescription { get; }
        /// <summary>
        /// Describes how to fetch an existing instance of standard model of this "Fluent Method Group".
        /// </summary>
        IResourceGetDescription ResourceGetDescription { get; }
        /// <summary>
        /// Describes how to list the instances of stanadard model of this "Fluent Method Group".
        /// </summary>
        IResourceListingDescription ResourceListingDescription { get; }
        /// <summary>
        /// Describes how to delete an instance of standard model of this "Fluent Method Group".
        /// </summary>
        IResourceDeleteDescription ResourceDeleteDescription { get; }
        /// <summary>
        /// The generalized output of other sibling (having the same inner method group) method groups for
        /// which we decided to make this fluent method group a proxy.
        /// </summary>
        IReadOnlyList<GeneralizedOutput> GeneralizedOutputs { get; }
        /// <summary>
        /// The collection containing generalized declarations of methods that list, get and
        /// delete a child resource in parent's context.
        /// </summary>
        IEnumerable<string> ListGetDeleteByParentMethodDecls { get; }
        /// <summary>
        /// The non-standard methods in this "Fluent Method Group".
        /// </summary>
        IOtherMethods OtherMethods { get; }
    }
}
