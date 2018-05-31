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
        private readonly ListByResourceGroupDescription listByResourceGroupDescription;
        private readonly ListBySubscriptionDescription listBySubscriptionDescription;
        private readonly ListByImmediateParentDescription listByImmediateParentDescription;

        public ResourceListingDescription(FluentMethodGroup fluentMethodGroup)
        {
            this.listByResourceGroupDescription = new ListByResourceGroupDescription(fluentMethodGroup);
            this.listBySubscriptionDescription = new ListBySubscriptionDescription(fluentMethodGroup);
            this.listByImmediateParentDescription = new ListByImmediateParentDescription(fluentMethodGroup);
        }

        public bool SupportsListByResourceGroup
        {
            get
            {
                return this.listByResourceGroupDescription.SupportsListing;
            }
        }

        public FluentMethod ListByResourceGroupMethod
        {
            get
            {
                return this.listByResourceGroupDescription.ListMethod;
            }
        }

        public bool SupportsListBySubscription
        {
            get
            {
                return this.listBySubscriptionDescription.SupportsListing;
            }
        }

        public FluentMethod ListBySubscriptionMethod
        {
            get
            {
                return this.listBySubscriptionDescription.ListMethod;
            }
        }

        public bool SupportsListByImmediateParent
        {
            get
            {
                return this.listByImmediateParentDescription.SupportsListing;
            }
        }

        public FluentMethod ListByImmediateParentMethod
        {
            get
            {
                return this.listByImmediateParentDescription.ListMethod;
            }
        }

        public HashSet<string> MethodGroupInterfaceExtendsFrom
        {
            get
            {
                HashSet<string> extendsFrom = new HashSet<string>();
                //
                extendsFrom.AddRange(this.listByResourceGroupDescription.MethodGroupInterfaceExtendsFrom);
                extendsFrom.AddRange(this.listBySubscriptionDescription.MethodGroupInterfaceExtendsFrom);
                extendsFrom.AddRange(this.listByImmediateParentDescription.MethodGroupInterfaceExtendsFrom);
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
                imports.AddRange(this.listByResourceGroupDescription.ImportsForMethodGroupInterface);
                imports.AddRange(this.listBySubscriptionDescription.ImportsForMethodGroupInterface);
                imports.AddRange(this.listByImmediateParentDescription.ImportsForMethodGroupInterface);
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
                imports.AddRange(this.listByResourceGroupDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.listBySubscriptionDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.listByImmediateParentDescription.ImportsForMethodGroupImpl);
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
                imports.AddRange(this.listByResourceGroupDescription.ImportsForGeneralizedInterface);
                imports.AddRange(this.listBySubscriptionDescription.ImportsForGeneralizedInterface);
                imports.AddRange(this.listByImmediateParentDescription.ImportsForGeneralizedInterface);
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
                imports.AddRange(this.listByResourceGroupDescription.ImportsForGeneralizedImpl);
                imports.AddRange(this.listBySubscriptionDescription.ImportsForGeneralizedImpl);
                imports.AddRange(this.listByImmediateParentDescription.ImportsForGeneralizedImpl);
                //
                return imports;
            }
        }

        public IEnumerable<string> GeneralizedMethodDecls
        {
            get
            {
                string methodDecl = this.listByResourceGroupDescription.GeneralizedMethodDecl;
                if (!string.IsNullOrEmpty(methodDecl))
                {
                    yield return methodDecl;
                }
                methodDecl = this.listBySubscriptionDescription.GeneralizedMethodDecl;
                if (!string.IsNullOrEmpty(methodDecl))
                {
                    yield return methodDecl;
                }
                methodDecl = this.listByImmediateParentDescription.GeneralizedMethodDecl;
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
                string methodImpl = this.listByResourceGroupDescription.GeneralizedMethodImpl;
                if (!string.IsNullOrEmpty(methodImpl))
                {
                    yield return methodImpl;
                }
                methodImpl = this.listBySubscriptionDescription.GeneralizedMethodImpl;
                if (!string.IsNullOrEmpty(methodImpl))
                {
                    yield return methodImpl;
                }
                methodImpl = this.listByImmediateParentDescription.GeneralizedMethodImpl;
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
                return this.listByImmediateParentDescription.GeneralizedMethodDecl;
            }
        }

        public string ListByResourceGroupRxAsyncMethodImplementation(bool isGeneralized)
        {
            return this.listByResourceGroupDescription.ListByRxAsyncMethodImplementation(isGeneralized);
        }

        public string ListByResourceGroupSyncMethodImplementation(string convertToPagedListMethodName)
        {
            return this.listByResourceGroupDescription.ListBySyncMethodImplementation(convertToPagedListMethodName);
        }

        public string ListBySubscriptionRxAsyncMethodImplementation(bool isGeneralized)
        {
            return this.listBySubscriptionDescription.ListByRxAsyncMethodImplementation(isGeneralized);
        }


        public string ListBySubscriptionSyncMethodImplementation(string convertToPagedListMethodName)
        {
            return this.listBySubscriptionDescription.ListBySyncMethodImplementation(convertToPagedListMethodName);
        }

        public string ListByImmediateParentRxAsyncMethodImplementation(bool isGeneralized)
        {
            return this.listByImmediateParentDescription.ListByRxAsyncMethodImplementation(isGeneralized);
        }
    }
}