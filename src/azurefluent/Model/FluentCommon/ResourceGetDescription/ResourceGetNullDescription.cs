﻿// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Null object implementation of IResourceGetDescription.
    /// </summary>
    public class ResourceGetNullDescription : IResourceGetDescription
    {
        public static ResourceGetNullDescription Instance { get; } = new ResourceGetNullDescription();

        private ResourceGetNullDescription() { }

        public StandardFluentMethod GetByImmediateParentMethod => null;

        public StandardFluentMethod GetByParameterizedParentMethod => null;

        public StandardFluentMethod GetByResourceGroupMethod => null;

        public StandardFluentMethod GetBySubscriptionMethod => null;

        public IGetInnerAsyncFuncFactory GetInnerAsyncFuncFactory => GetInnerAsyncFuncNullFactory.Instance;

        public HashSet<string> ImportsForMethodGroupImpl => Utils.EmptyStringSet;

        public HashSet<string> ImportsForMethodGroupInterface => Utils.EmptyStringSet;

        public HashSet<string> ImportsForMethodGroupWithLocalGetByResourceGroupImpl => Utils.EmptyStringSet;

        public HashSet<string> MethodGroupInterfaceExtendsFrom => Utils.EmptyStringSet;

        public bool SupportsGetByImmediateParent => false;

        public bool SupportsGetByParameterizedParent => false;

        public bool SupportsGetByResourceGroup => false;

        public bool SupportsGetBySubscription => false;

        public bool SupportsGetting => false;

        public IEnumerable<string> GetByResourceGroupSyncAsyncImplementation => Utils.EmptyStringList;

        public HashSet<string> ImportsForGeneralizedInterface => Utils.EmptyStringSet;

        public HashSet<string> ImportsForGeneralizedImpl => Utils.EmptyStringSet;

        public IEnumerable<string> GeneralizedMethodDecls => Utils.EmptyStringList;

        public IEnumerable<string> GeneralizedMethodImpls => Utils.EmptyStringList;

        public string GetByImmediateParentMethodDecl => string.Empty;

        public string GetByImmediateParentRxAsyncMethodImplementation(bool aaplyOverride) => string.Empty;
    }
}
