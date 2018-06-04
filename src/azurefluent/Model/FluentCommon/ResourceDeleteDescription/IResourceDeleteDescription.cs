// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Describes deletion of resource instance that the 'Standard Model' of fluent method group represents.
    /// </summary>
    public interface IResourceDeleteDescription : ISupportsGeneralizedView
    {
        /// <summary>
        /// Gets the method representing apiCall to delete the resource in subscription scope.
        /// null will be returned if resource cannot be deleted in subscription level.
        /// </summary>
        StandardFluentMethod DeleteBySubcriptionMethod { get; }
        /// <summary>
        /// Gets the method representing apiCall to delete the resource it's parent scope.
        /// null will be returned if resource cannot be deleted in parent scope.
        /// </summary>
        StandardFluentMethod DeleteByImmediateParentMethod { get; }
        /// <summary>
        /// Gets the method representing apiCall to delete the resource in resource group scope.
        /// null will be returned if resource cannot be deleted in resource group scope.
        /// </summary>
        StandardFluentMethod DeleteByResourceGroupMethod { get; }
        /// <summary>
        /// Imports needed by a method group implementation when implementing standard methods.
        /// In order to use this imports MethodGroup implmentation extends from GRoupableResourcesImpl.
        /// </summary>
        HashSet<string> ImportsForMethodGroupImpl { get; }
        /// <summary>
        /// Imports needed by a method group interface when declaring standard methods.
        /// </summary>
        HashSet<string> ImportsForMethodGroupInterface { get; }
        /// <summary>
        /// Imports needed by a method group implementation when implementing standard methods.
        /// In order to use this imports MethodGroup implmentation shouldn't extends from GRoupableResourcesImpl.
        /// </summary>
        HashSet<string> ImportsForMethodGroupWithLocalDeleteByResourceGroupImpl { get; }
        /// <summary>
        /// The types that method group (which supports deletion of it's 'Standard Model') extends from.
        /// </summary>
        HashSet<string> MethodGroupInterfaceExtendsFrom { get; }
        /// <summary>
        /// true if the resource can logically exists under a parent and can be deleted in parent scope.
        /// </summary>
        bool SupportsDeleteByImmediateParent { get; }
        /// <summary>
        /// true if the resource can logically exists under a resource group and can be deleted in resource group scope.
        /// </summary>
        bool SupportsDeleteByResourceGroup { get; }
        /// <summary>
        /// true if the resource can logically exists under a subscription and can be deleted in subscription scope.
        /// </summary>
        bool SupportsDeleteBySubscription { get; }
        /// <summary>
        /// Implementation for batch deletion of the resource.
        /// </summary>
        /// <returns></returns>
        IEnumerable<string> BatchDeleteAyncAndSyncMethodImplementations();
        /// <summary>
        /// Gets the declaration of method to delete the resource in parent scope.
        /// </summary>
        string DeleteByImmediateParentMethodDecl { get; }
        /// <summary>
        /// Gets the implementation of method to delete the resource in parent scope.
        /// </summary>
        string DeleteByImmediateParentRxAsyncMethodImplementation { get; }
        /// <summary>
        /// The list of various method implementations for deleting resource instance in resource group.
        /// </summary>
        IEnumerable<string> DeleteByResourceGroupSyncAsyncImplementation();
    }
}