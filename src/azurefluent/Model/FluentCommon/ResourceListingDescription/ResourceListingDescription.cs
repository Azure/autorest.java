// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// An implementation of 'IResourceListingDescription' that describes listing of an Azure resource.
    /// </summary>
    public class ResourceListingDescription : IResourceListingDescription
    {
        private readonly ListDescriptionBase listByResourceGroup;
        private readonly ListDescriptionBase listBySubscription;
        private readonly ListDescriptionBase listByImmediateParent;

        public ResourceListingDescription(FluentMethodGroup fluentMethodGroup)
        {
            this.listByResourceGroup = new ListByResourceGroupDescription(fluentMethodGroup);
            this.listBySubscription = new ListBySubscriptionDescription(fluentMethodGroup);
            this.listByImmediateParent = new ListByImmediateParentDescription(fluentMethodGroup);
        }

        public bool SupportsListByResourceGroup
        {
            get
            {
                return this.listByResourceGroup.SupportsListing;
            }
        }

        public FluentMethod ListByResourceGroupMethod
        {
            get
            {
                return this.listByResourceGroup.ListMethod;
            }
        }

        public bool SupportsListBySubscription
        {
            get
            {
                return this.listBySubscription.SupportsListing;
            }
        }

        public FluentMethod ListBySubscriptionMethod
        {
            get
            {
                return this.listBySubscription.ListMethod;
            }
        }

        public bool SupportsListByImmediateParent
        {
            get
            {
                return this.listByImmediateParent.SupportsListing;
            }
        }

        public FluentMethod ListByImmediateParentMethod
        {
            get
            {
                return this.listByImmediateParent.ListMethod;
            }
        }

        public HashSet<string> MethodGroupInterfaceExtendsFrom
        {
            get
            {
                HashSet<string> extendsFrom = new HashSet<string>();
                //
                extendsFrom.AddRange(this.listByResourceGroup.MethodGroupInterfaceExtendsFrom);
                extendsFrom.AddRange(this.listBySubscription.MethodGroupInterfaceExtendsFrom);
                extendsFrom.AddRange(this.listByImmediateParent.MethodGroupInterfaceExtendsFrom);
                //
                return extendsFrom;
            }
        }

        public HashSet<string> ImportsForMethodGroupInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                //
                imports.AddRange(this.listByResourceGroup.ImportsForMethodGroupInterface);
                imports.AddRange(this.listBySubscription.ImportsForMethodGroupInterface);
                imports.AddRange(this.listByImmediateParent.ImportsForMethodGroupInterface);
                //
                return imports;
            }
        }

        public HashSet<string> ImportsForMethodGroupImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                //
                imports.AddRange(this.listByResourceGroup.ImportsForMethodGroupImpl);
                imports.AddRange(this.listBySubscription.ImportsForMethodGroupImpl);
                imports.AddRange(this.listByImmediateParent.ImportsForMethodGroupImpl);
                //
                return imports;
            }
        }

        public HashSet<string> ImportsForGeneralizedInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                //
                imports.AddRange(this.listByResourceGroup.ImportsForGeneralizedInterface);
                imports.AddRange(this.listBySubscription.ImportsForGeneralizedInterface);
                imports.AddRange(this.listByImmediateParent.ImportsForGeneralizedInterface);
                //
                return imports;
            }
        }

        public HashSet<string> ImportsForGeneralizedImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                //
                imports.AddRange(this.listByResourceGroup.ImportsForGeneralizedImpl);
                imports.AddRange(this.listBySubscription.ImportsForGeneralizedImpl);
                imports.AddRange(this.listByImmediateParent.ImportsForGeneralizedImpl);
                //
                return imports;
            }
        }

        public IEnumerable<string> GeneralizedMethodDecls
        {
            get
            {
                string methodDecl = this.listByResourceGroup.GeneralizedMethodDecl;
                if (!string.IsNullOrEmpty(methodDecl))
                {
                    yield return methodDecl;
                }
                methodDecl = this.listBySubscription.GeneralizedMethodDecl;
                if (!string.IsNullOrEmpty(methodDecl))
                {
                    yield return methodDecl;
                }
                methodDecl = this.listByImmediateParent.GeneralizedMethodDecl;
                if (!string.IsNullOrEmpty(methodDecl))
                {
                    yield return methodDecl;
                }
            }
        }

        public IEnumerable<string> GeneralizedMethodImpls
        {
            get
            {
                string methodImpl = this.listByResourceGroup.GeneralizedMethodImpl;
                if (!string.IsNullOrEmpty(methodImpl))
                {
                    yield return methodImpl;
                }
                methodImpl = this.listBySubscription.GeneralizedMethodImpl;
                if (!string.IsNullOrEmpty(methodImpl))
                {
                    yield return methodImpl;
                }
                methodImpl = this.listByImmediateParent.GeneralizedMethodImpl;
                if (!string.IsNullOrEmpty(methodImpl))
                {
                    yield return methodImpl;
                }
            }
        }

        public string ListByImmediateParentMethodDecl
        {
            get
            { 
                return this.listByImmediateParent.GeneralizedMethodDecl;
            }
        }

        public string ListByResourceGroupRxAsyncMethodImplementation(bool isGeneralized)
        {
            return this.listByResourceGroup.ListRxAsyncMethodImplementation(isGeneralized);
        }

        public string ListByResourceGroupSyncMethodImplementation(string convertToPagedListMethodName)
        {
            return this.listByResourceGroup.ListSyncMethodImplementation(convertToPagedListMethodName);
        }

        public string ListBySubscriptionRxAsyncMethodImplementation(bool isGeneralized)
        {
            return this.listBySubscription.ListRxAsyncMethodImplementation(isGeneralized);
        }


        public string ListBySubscriptionSyncMethodImplementation(string convertToPagedListMethodName)
        {
            return this.listBySubscription.ListSyncMethodImplementation(convertToPagedListMethodName);
        }

        public string ListByImmediateParentRxAsyncMethodImplementation(bool isGeneralized)
        {
            return this.listByImmediateParent.ListRxAsyncMethodImplementation(isGeneralized);
        }
    }
}