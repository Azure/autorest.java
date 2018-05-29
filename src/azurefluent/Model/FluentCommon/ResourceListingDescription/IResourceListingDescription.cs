// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public interface IResourceListingDescription : ISupportsGeneralizedView
    {
        HashSet<string> ImportsForMethodGroupImpl { get; }
        HashSet<string> ImportsForMethodGroupInterface { get; }
        FluentMethod ListByImmediateParentMethod { get; }
        FluentMethod ListByResourceGroupMethod { get; }
        FluentMethod ListBySubscriptionMethod { get; }
        HashSet<string> MethodGroupInterfaceExtendsFrom { get; }
        bool SupportsListByImmediateParent { get; }
        bool SupportsListByResourceGroup { get; }
        bool SupportsListBySubscription { get; }
        string ListByImmediateParentMethodDecl { get; }
        string ListByResourceGroupRxAsyncMethodImplementation(bool isGeneralized);
        string ListByResourceGroupSyncMethodImplementation(string convertToPagedListMethodName);
        string ListBySubscriptionRxAsyncMethodImplementation(bool isGeneralized);
        string ListBySubscriptionSyncMethodImplementation(string convertToPagedListMethodName);
        string ListByImmediateParentRxAsyncMethodImplementation(bool isGeneralized);
    }
}