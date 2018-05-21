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
        string ListByImmediateParentMethodGeneralizedDecl { get; }

        string ListByImmediateParentAsyncMethodImplementation();
        string ListByResourceGroupAsyncMethodImplementation();
        string ListByResourceGroupSyncMethodImplementation(string convertToPagedListMethodName, string innerClientName, string standardModelInnerName, string standardModelInterfaceName);
        string ListBySubscriptionAsyncMethodImplementation();
        string ListBySubscriptionSyncMethodImplementation(string convertToPagedListMethodName, string innerClientName, string standardModelInnerName, string stnadardModelInterfaceName);
        string ListByImmediateParentAsyncMethodGeneralizedImplementation { get;  }
    }
}