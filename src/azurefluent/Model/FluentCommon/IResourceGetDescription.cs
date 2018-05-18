// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public interface IResourceGetDescription : ISupportsGeneralizedView
    {
        FluentMethod GetByImmediateParentMethod { get; }
        FluentMethod GetByParameterizedParentMethod { get; }
        FluentMethod GetByResourceGroupMethod { get; }
        FluentMethod GetBySubscriptionMethod { get; }
        IGetInnerAsyncFunc GetInnerAsyncFunc { get; }
        HashSet<string> ImportsForMethodGroupImpl { get; }
        HashSet<string> ImportsForMethodGroupInterface { get; }
        HashSet<string> ImportsForMethodGroupWithLocalGetByResourceGroupImpl { get; }
        HashSet<string> MethodGroupInterfaceExtendsFrom { get; }
        bool SupportsGetByImmediateParent { get; }
        bool SupportsGetByParameterizedParent { get; }
        bool SupportsGetByResourceGroup { get; }
        bool SupportsGetBySubscription { get; }
        bool SupportsGetting { get; }

        string GetByImmediateParentMethodImplementation { get; }
        IEnumerable<string> GetByResourceGroupSyncAsyncImplementation { get; }
        string GetInnerMethodImplementation(bool applyOverride);
    }
}