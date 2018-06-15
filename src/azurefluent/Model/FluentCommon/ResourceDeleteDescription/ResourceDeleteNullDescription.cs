// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// A null object implementation of IResourceDeleteDescription interface.
    /// </summary>
    public class ResourceDeleteNullDescription : IResourceDeleteDescription
    {
        public static ResourceDeleteNullDescription Instance { get; } = new ResourceDeleteNullDescription();

        private ResourceDeleteNullDescription() { }

        public StandardFluentMethod DeleteBySubcriptionMethod => null;

        public StandardFluentMethod DeleteByImmediateParentMethod => null;

        public StandardFluentMethod DeleteByResourceGroupMethod => null;

        public IEnumerable<string> GeneralizedMethodDecls => Utils.EmptyStringSet;

        public IEnumerable<string> GeneralizedMethodImpls => Utils.EmptyStringSet;

        public HashSet<string> ImportsForGeneralizedImpl => Utils.EmptyStringSet;

        public HashSet<string> ImportsForGeneralizedInterface => Utils.EmptyStringSet;

        public HashSet<string> ImportsForMethodGroupImpl => Utils.EmptyStringSet;

        public HashSet<string> ImportsForMethodGroupInterface => Utils.EmptyStringSet;

        public HashSet<string> ImportsForMethodGroupWithLocalDeleteByResourceGroupImpl => Utils.EmptyStringSet;

        public HashSet<string> MethodGroupInterfaceExtendsFrom => Utils.EmptyStringSet;

        public bool SupportsDeleteByImmediateParent => false;

        public bool SupportsDeleteByResourceGroup => false;

        public bool SupportsDeleteBySubscription => false;

        public string DeleteByImmediateParentMethodDecl => string.Empty;

        public string DeleteByImmediateParentRxAsyncMethodImplementation => string.Empty;

        public IEnumerable<string> BatchDeleteAyncAndSyncMethodImplementations() => Utils.EmptyStringList;

        public IEnumerable<string> DeleteByResourceGroupSyncAsyncImplementation() => Utils.EmptyStringList;
    }
}
