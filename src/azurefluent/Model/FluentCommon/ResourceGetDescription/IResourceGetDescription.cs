// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Describes retrival of resource instance that the standard model of fluent method group represents.
    /// </summary>
    public interface IResourceGetDescription : ISupportsGeneralizedView
    {
        /// <summary>
        /// Gets the method representing apiCall to retrieve the resource in parent scope.
        /// null will be returned if resource cannot be retrieved in parent scope.
        /// </summary>
        FluentMethod GetByImmediateParentMethod { get; }
        /// <summary>
        /// Gets the method representing apiCall to retrieve the resource in parameterized parent scope.
        /// null will be returned if resource cannot be retrieved in parameterized parent scope.
        /// </summary>
        FluentMethod GetByParameterizedParentMethod { get; }
        /// <summary>
        /// Gets the method representing apiCall to retrieve the resource in resource group scope.
        /// null will be returned if resource cannot be retrieved in resource group scope.
        /// </summary>
        FluentMethod GetByResourceGroupMethod { get; }
        /// <summary>
        /// Gets the method representing apiCall to retrieve the resource in subscription scope.
        /// null will be returned if resource cannot be retrieved in sibscription scope.
        /// </summary>
        FluentMethod GetBySubscriptionMethod { get; }
        /// <summary>
        /// A factory to get access to "getInnerAsync" method description that retrieve the resource.
        /// Factory will be still returned if the resource cannot be retrieved, in this case
        /// GetInnerAsyncFuncFactory.IsGetInnerSupported will be false.
        /// </summary>
        IGetInnerAsyncFuncFactory GetInnerAsyncFuncFactory { get; }
        /// <summary>
        /// The imports needed for an implementation of method group inorder to support retrival
        /// of it's standard model.
        /// </summary>
        HashSet<string> ImportsForMethodGroupImpl { get; }
        /// <summary>
        /// The imports needed for an method group interface inorder to support retrival
        /// of it's standard model.
        /// </summary>
        HashSet<string> ImportsForMethodGroupInterface { get; }
        /// <summary>
        /// The imports needed for an method group interface inorder to support SupportsGetBySubscription implementation 
        /// locally (i.e. there is no default implementation in the base type).
        /// </summary>
        HashSet<string> ImportsForMethodGroupWithLocalGetByResourceGroupImpl { get; }
        /// <summary>
        /// The collection of types that method group should extends in order to support various standard fluent get operations.
        /// </summary>
        HashSet<string> MethodGroupInterfaceExtendsFrom { get; }
        /// <summary>
        /// true if the resource can logically exists under a parent and can be retrived in parent scope.
        /// </summary>
        bool SupportsGetByImmediateParent { get; }
        /// <summary>
        /// true if the resource can logically exists under a parent and can be retrived in parameterized parent scope.
        /// </summary>
        bool SupportsGetByParameterizedParent { get; }
        /// <summary>
        /// true if the resource can logically exists under a resource group and can be retrieved in resource group scope.
        /// </summary>
        bool SupportsGetByResourceGroup { get; }
        /// <summary>
        /// true if the resource can logically exists under a resource group and can be retrieved in subscription scope.
        /// </summary>
        bool SupportsGetBySubscription { get; }
        /// <summary>
        /// true if resource can be retrieved from Azure.
        /// </summary>
        bool SupportsGetting { get; }
        /// <summary>
        /// Implementation for method to retrieve the resource in parent scope, null will be returned if such retrival is not supported.
        /// </summary>
        string GetByImmediateParentMethodImplementation { get; }
        /// <summary>
        /// Implementation for various methods to retrieve the resource in resource group scope, null will be returned if such retrival is not supported.
        /// </summary>
        IEnumerable<string> GetByResourceGroupSyncAsyncImplementation { get; }
        /// <summary>
        /// Gets the implementation of getInnerAsync method either in protected form (if override param is true) or in private form.
        /// </summary>
        /// <param name="applyOverride"></param>
        /// <returns></returns>
        string GetInnerMethodImplementation(bool applyOverride);
        /// <summary>
        /// Gets the declaration of method to retrieve the resource in parent scope. ull will be returned if such retrival is not supported.
        /// </summary>
        string GetByImmediateParentMethodDecl {get;}
        /// <summary>
        /// Implementation for method to retrieve the resource in parent scope, null will be returned if such retrival is not supported.
        /// </summary>
        string GetByImmediateParentRxAsyncMethodImplementation(bool isGeneralized);
    }
}