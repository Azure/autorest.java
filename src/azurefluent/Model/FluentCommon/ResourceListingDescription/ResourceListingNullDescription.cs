// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// A null object implementation of IResourceListingDescription interface.
    /// </summary>
    public class ResourceListingNullDescription : IResourceListingDescription
    {
        public static ResourceListingNullDescription Instance { get; } = new ResourceListingNullDescription();

        private ResourceListingNullDescription() { }

        public HashSet<string> ImportsForMethodGroupImpl => Utils.EmptyStringSet;

        public HashSet<string> ImportsForMethodGroupInterface => Utils.EmptyStringSet;

        public StandardFluentMethod ListByImmediateParentMethod => null;

        public StandardFluentMethod ListByResourceGroupMethod => null;

        public StandardFluentMethod ListBySubscriptionMethod => null;

        public HashSet<string> MethodGroupInterfaceExtendsFrom => Utils.EmptyStringSet;

        public bool SupportsListByImmediateParent => false;

        public bool SupportsListByResourceGroup => false;

        public bool SupportsListBySubscription => false;

        public HashSet<string> ImportsForGeneralizedInterface => Utils.EmptyStringSet;

        public HashSet<string> ImportsForGeneralizedImpl => Utils.EmptyStringSet;

        public IEnumerable<string> GeneralizedMethodDecls => Utils.EmptyStringList;

        public IEnumerable<string> GeneralizedMethodImpls => Utils.EmptyStringList;

        public string ListByImmediateParentMethodDecl => string.Empty;

        public string ListByImmediateParentRxAsyncMethodImplementation(bool isGeneralized) => string.Empty;

        public string ListByResourceGroupRxAsyncMethodImplementation(bool isGeneralized) => string.Empty;

        public string ListByResourceGroupSyncMethodImplementation(string convertToPagedListMethodName) => string.Empty;

        public string ListBySubscriptionRxAsyncMethodImplementation(bool isGeneralized) => string.Empty;

        public string ListBySubscriptionSyncMethodImplementation(string convertToPagedListMethodName) => string.Empty;
    }
}
