// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ResourceListingNullDescription : IResourceListingDescription
    {
        public static ResourceListingNullDescription Instance { get; } = new ResourceListingNullDescription();

        private ResourceListingNullDescription() { }

        public HashSet<string> ImportsForMethodGroupImpl => Utils.EmptyStringSet;

        public HashSet<string> ImportsForMethodGroupInterface => Utils.EmptyStringSet;

        public FluentMethod ListByImmediateParentMethod => null;

        public FluentMethod ListByResourceGroupMethod => null;

        public FluentMethod ListBySubscriptionMethod => null;

        public HashSet<string> MethodGroupInterfaceExtendsFrom => Utils.EmptyStringSet;

        public bool SupportsListByImmediateParent => false;

        public bool SupportsListByResourceGroup => false;

        public bool SupportsListBySubscription => false;

        public HashSet<string> ImportsForGeneralizedInterface => Utils.EmptyStringSet;

        public HashSet<string> ImportsForGeneralizedImpl => Utils.EmptyStringSet;

        public IEnumerable<string> GeneralizedMethodDecls => Utils.EmptyStringList;

        public IEnumerable<string> GeneralizedMethodImpls => Utils.EmptyStringList;

        public string ListByImmediateParentMethodGeneralizedDecl => string.Empty;

        public string ListByImmediateParentAsyncMethodGeneralizedImplementation => string.Empty;

        public string ListByImmediateParentAsyncMethodImplementation() => string.Empty;

        public string ListByResourceGroupAsyncMethodImplementation() => string.Empty;

        public string ListByResourceGroupSyncMethodImplementation(string convertToPagedListMethodName, string innerClientName, string standardModelInnerName, string standardModelInterfaceName) => string.Empty;

        public string ListBySubscriptionAsyncMethodImplementation() => string.Empty;

        public string ListBySubscriptionSyncMethodImplementation(string convertToPagedListMethodName, string innerClientName, string standardModelInnerName, string stnadardModelInterfaceName) => string.Empty;
    }
}
