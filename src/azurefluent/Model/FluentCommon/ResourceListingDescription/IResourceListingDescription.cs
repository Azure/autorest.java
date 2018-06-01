// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Describes retrival (listing) of resource instances that the standard model of a fluent method group represents.
    /// </summary>
    public interface IResourceListingDescription : ISupportsGeneralizedView
    {
        /// <summary>
        /// The imports needed for an implementation of method group inorder to support listing
        /// of it's standard model.
        /// </summary>
        HashSet<string> ImportsForMethodGroupImpl { get; }
        /// <summary>
        /// The imports needed for an method group interface inorder to support listing
        /// of it's standard model.
        /// </summary>
        HashSet<string> ImportsForMethodGroupInterface { get; }
        /// <summary>
        /// Gets the method representing apiCall to list the resources in parent scope.
        /// null will be returned if resource cannot be list in parent scope.
        /// </summary>
        FluentMethod ListByImmediateParentMethod { get; }
        /// <summary>
        /// Gets the method representing apiCall to list the resources in resource group scope.
        /// null will be returned if resource cannot be list in resource group scope.
        /// </summary>
        FluentMethod ListByResourceGroupMethod { get; }
        /// <summary>
        /// Gets the method representing apiCall to list the resources in subscription scope.
        /// null will be returned if resource cannot be list in subscription scope.
        /// </summary>
        FluentMethod ListBySubscriptionMethod { get; }
        /// <summary>
        /// The collection of types that method group should extends in order to support various standard fluent list operations.
        /// </summary>
        HashSet<string> MethodGroupInterfaceExtendsFrom { get; }
        /// <summary>
        /// true if the resource can logically exists under a parent and can be list in parent scope.
        /// </summary>
        bool SupportsListByImmediateParent { get; }
        /// <summary>
        /// true if the resource can logically exists under a resource group and can be list in resource group scope.
        /// </summary>
        bool SupportsListByResourceGroup { get; }
        /// <summary>
        /// true if the resource can logically exists under a resource group and can be list in subscription scope.
        /// </summary>
        bool SupportsListBySubscription { get; }
        /// <summary>
        /// Gets the declaration of method to list the resource in parent scope. Null will be returned if such listing is not supported.
        /// </summary>
        string ListByImmediateParentMethodDecl { get; }
        /// <summary>
        /// Implementation for async method to list the resource in resource group scope, null will be returned if such listing is not supported.
        /// </summary>
        string ListByResourceGroupRxAsyncMethodImplementation(bool isGeneralized);
        /// <summary>
        /// Implementation for sync method to list the resource in resource group scope, null will be returned if such listing is not supported.
        /// </summary>
        /// <param name="convertToPagedListMethodName">the method to use for converting inner paged list to fluent page list</param>
        /// <returns></returns>
        string ListByResourceGroupSyncMethodImplementation(string convertToPagedListMethodName);
        /// <summary>
        /// Implementation for async method to list the resource in subscription scope, null will be returned if such listing is not supported.
        /// </summary>
        string ListBySubscriptionRxAsyncMethodImplementation(bool isGeneralized);
        /// <summary>
        /// Implementation for sync method to list the resource in subscription scope, null will be returned if such listing is not supported.
        /// </summary>
        /// <param name="convertToPagedListMethodName">the method to use for converting inner paged list to fluent page list</param>
        /// <returns></returns>
        string ListBySubscriptionSyncMethodImplementation(string convertToPagedListMethodName);
        /// <summary>
        /// Implementation for async method to list the resource in parent scope, null will be returned if such listing is not supported.
        /// </summary>
        string ListByImmediateParentRxAsyncMethodImplementation(bool isGeneralized);
    }
}