// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type describing a fluent method group.
    /// </summary>
    public interface IFluentMethodGroup
    {
        /// <summary>
        /// The group that holds all method groups.
        /// </summary>
        FluentMethodGroups FluentMethodGroups { get; }
        /// <summary>
        /// The standard model of this method group.
        /// </summary>
        StandardModel StandardFluentModel { get; }
        /// <summary>
        /// The local name of the fluent method group (which is same as the segment in the
        /// ARM URI that this method group represents).
        /// </summary>
        string LocalNameInPascalCase { get; }
        /// <summary>
        /// The name of the java interface for this method group.
        /// </summary>
        string JavaInterfaceName { get; }
        /// <summary>
        /// The fluent manager name (e.g.: ComputeManager).
        /// </summary>
        string ManagerName { get; }
        /// <summary>
        /// The autorest provided operation group that this method group uses to perform apiCalls.
        /// </summary>
        MethodGroupJvaf InnerMethodGroup { get; }
        /// <summary>
        /// The type name of the autorest provided operation group (e.g. VirtualMachinesInner)
        /// </summary>
        string InnerMethodGroupTypeName { get; }
        /// <summary>
        /// The name of the inner collection accessor [e.g. virtualMachines()]
        /// </summary>
        string InnerMethodGroupAccessorName { get; }
        /// <summary>
        /// The inner methods belongs to this fluent method group.
        /// </summary>
        IReadOnlyList<MethodJvaf> InnerMethods { get; }
        /// <summary>
        /// The level of the fluent method group in the ARM Uri.
        /// </summary>
        int Level { get; }
        /// <summary>
        /// The imports needed for method group java implementation class.
        /// </summary>
        HashSet<string> ImportsForImpl { get; }
        /// <summary>
        /// The imports needed for method group java interface.
        /// </summary>
        HashSet<string> ImportsForInterface { get; }
        /// <summary>
        /// The non-standard methods in the fluent method group.
        /// </summary>
        IOtherMethods OtherMethods { get; }
        /// <summary>
        /// Describes how to create a new instance of standard model of this method group.
        /// e.g. If method group is "VirtualMachines" then it's standard model is "VirtualMachine".
        /// </summary>
        IResourceCreateDescription ResourceCreateDescription { get; }
        /// <summary>
        /// Describes how to update the an existing instance of standard model of this method group.
        /// </summary>
        IResourceUpdateDescription ResourceUpdateDescription { get; }
        /// <summary>
        /// Describes how to fetch an existing instance of standard model of this method group.
        /// </summary>
        IResourceGetDescription ResourceGetDescription { get; }
        /// <summary>
        /// Describes how to list the instances of stanadard model of this method group.
        /// </summary>
        IResourceListingDescription ResourceListingDescription { get; }
        /// <summary>
        /// Describes how to delete an instance of standard model of this method group.
        /// </summary>
        IResourceDeleteDescription ResourceDeleteDescription { get; }
        /// <summary>
        /// The parent fluent method group.
        /// </summary>
        IFluentMethodGroup ParentFluentMethodGroup { get; }
        /// <summary>
        /// The singualar form of parent fluent method group interface name.
        /// </summary>
        string SingularJavaInterfaceName { get; }
        /// <summary>
        /// The child fluent method groups.
        /// </summary>
        IReadOnlyList<IFluentMethodGroup> ChildFluentMethodGroups { get; }
        /// <summary>
        /// The accessor name.
        /// </summary>
        string AccessorMethodName { get; set; }
        /// <summary>
        /// The generalized output of other sibling (having the same inner method group) method groups for
        /// which we decided to make this fluent method group a proxy.
        /// </summary>
        IReadOnlyList<GeneralizedOutput> GeneralizedOutputs { get; }
        /// <summary>
        /// The local name of this fluent method group in singualr form.
        /// </summary>
        string LocalSingularNameInPascalCase { get; }
        /// <summary>
        /// mapper to map non-standard model to standard model of this method group.
        /// </summary>
        NonStandardToStanardModelMappingHelper ModelMapper { get; }
    }
}
