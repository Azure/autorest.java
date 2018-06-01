// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// An implementation of 'IResourceGetDescription' that describes retrieval of an Azure resource.
    /// </summary>
    public class ResourceGetDescription : IResourceGetDescription
    {
        private readonly GetByResourceGroupDescription getByResourceGroup;
        private readonly GetBySubscriptionDescription getBySubscription;
        private readonly GetByImmediateParentDescription getByImmediateParent;
        private readonly GetByParameterizedParentDescription getByParameterizedParent;

        public ResourceGetDescription(IFluentMethodGroup fluentMethodGroup)
        {
            this.FluentMethodGroup = fluentMethodGroup;
            this.getByResourceGroup = new GetByResourceGroupDescription(fluentMethodGroup, this.GetInnerAsyncFuncFactory);
            this.getBySubscription = new GetBySubscriptionDescription(fluentMethodGroup, this.GetInnerAsyncFuncFactory);
            this.getByImmediateParent = new GetByImmediateParentDescription(fluentMethodGroup, this.GetInnerAsyncFuncFactory);
            this.getByParameterizedParent = new GetByParameterizedParentDescription(fluentMethodGroup, this.GetInnerAsyncFuncFactory);
        }

        public IFluentMethodGroup FluentMethodGroup { get; }

        public bool SupportsGetBySubscription
        {
            get
            {
                return this.getBySubscription.SupportsGet;
            }
        }

        public FluentMethod GetBySubscriptionMethod
        {
            get
            {
                return this.getBySubscription.GetMethod;
            }
        }

        public bool SupportsGetByResourceGroup
        {
            get
            {
                return this.getByResourceGroup.SupportsGet;
            }
        }

        public FluentMethod GetByResourceGroupMethod
        {
            get
            {
                return this.getByResourceGroup.GetMethod;
            }
        }

        public bool SupportsGetByImmediateParent
        {
            get
            {
                return this.getByImmediateParent.SupportsGet;
            }
        }

        public FluentMethod GetByImmediateParentMethod
        {
            get
            {
                return this.getByImmediateParent.GetMethod;
            }
        }

        public bool SupportsGetByParameterizedParent
        {
            get
            {
                return this.getByParameterizedParent.SupportsGet;
            }
        }

        public FluentMethod GetByParameterizedParentMethod
        {
            get
            {
                return this.getByParameterizedParent.GetMethod;
            }
        }

        public bool SupportsGetting
        {
            get
            {
                return this.getBySubscription.SupportsGet
                    || this.getByResourceGroup.SupportsGet
                    || this.getByImmediateParent.SupportsGet
                    || this.getByParameterizedParent.SupportsGet;
            }
        }

        private IGetInnerAsyncFuncFactory getInnerAsyncFuncFactory;
        public IGetInnerAsyncFuncFactory GetInnerAsyncFuncFactory
        {
            get
            {
                if (this.getInnerAsyncFuncFactory == null)
                {
                    this.getInnerAsyncFuncFactory = new GetInnerAsyncFuncFactory(this);
                }
                return this.getInnerAsyncFuncFactory;
            }
        }

        public HashSet<string> MethodGroupInterfaceExtendsFrom
        {
            get
            {
                HashSet<string> extendsFrom = new HashSet<string>();
                //
                extendsFrom.AddRange(this.getByResourceGroup.MethodGroupInterfaceExtendsFrom);
                extendsFrom.AddRange(this.getBySubscription.MethodGroupInterfaceExtendsFrom);
                extendsFrom.AddRange(this.getByImmediateParent.MethodGroupInterfaceExtendsFrom);
                extendsFrom.AddRange(this.getByParameterizedParent.MethodGroupInterfaceExtendsFrom);
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
                imports.AddRange(this.getByResourceGroup.ImportsForMethodGroupInterface);
                imports.AddRange(this.getBySubscription.ImportsForMethodGroupInterface);
                imports.AddRange(this.getByImmediateParent.ImportsForMethodGroupInterface);
                imports.AddRange(this.getByParameterizedParent.ImportsForMethodGroupInterface);
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
                imports.AddRange(this.getByResourceGroup.ImportsForMethodGroupImpl);
                imports.AddRange(this.getBySubscription.ImportsForMethodGroupImpl);
                imports.AddRange(this.getByImmediateParent.ImportsForMethodGroupImpl);
                imports.AddRange(this.getByParameterizedParent.ImportsForMethodGroupImpl);
                //
                return imports;
            }
        }

        public HashSet<string> ImportsForMethodGroupWithLocalGetByResourceGroupImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                //
                imports.AddRange(this.getByResourceGroup.ImportsForMethodGroupWithLocalGetMethodImpl);
                //
                return imports;
            }
        }

        #region ISupportsGeneralizedView

        public HashSet<string> ImportsForGeneralizedInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                //
                imports.AddRange(this.getByResourceGroup.ImportsForGeneralizedInterface);
                imports.AddRange(this.getBySubscription.ImportsForGeneralizedInterface);
                imports.AddRange(this.getByImmediateParent.ImportsForGeneralizedInterface);
                imports.AddRange(this.getByParameterizedParent.ImportsForGeneralizedInterface);
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
                imports.AddRange(this.getByResourceGroup.ImportsForGeneralizedImpl);
                imports.AddRange(this.getBySubscription.ImportsForGeneralizedImpl);
                imports.AddRange(this.getByImmediateParent.ImportsForGeneralizedImpl);
                imports.AddRange(this.getByParameterizedParent.ImportsForGeneralizedImpl);
                //
                return imports;
            }
        }

        public IEnumerable<string> GeneralizedMethodDecls
        {
            get
            {
                string methodDecl = this.getByResourceGroup.GeneralizedMethodDecl;
                if (!string.IsNullOrEmpty(methodDecl))
                {
                    yield return methodDecl;
                }
                methodDecl = this.getBySubscription.GeneralizedMethodDecl;
                if (!string.IsNullOrEmpty(methodDecl))
                {
                    yield return methodDecl;
                }
                methodDecl = this.getByImmediateParent.GeneralizedMethodDecl;
                if (!string.IsNullOrEmpty(methodDecl))
                {
                    yield return methodDecl;
                }
                methodDecl = this.getByParameterizedParent.GeneralizedMethodDecl;
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
                string methodImpl = this.getByResourceGroup.GeneralizedMethodImpl;
                if (!string.IsNullOrEmpty(methodImpl))
                {
                    yield return methodImpl;
                }
                methodImpl = this.getBySubscription.GeneralizedMethodImpl;
                if (!string.IsNullOrEmpty(methodImpl))
                {
                    yield return methodImpl;
                }
                methodImpl = this.getByImmediateParent.GeneralizedMethodImpl;
                if (!string.IsNullOrEmpty(methodImpl))
                {
                    yield return methodImpl;
                }
                methodImpl = this.getByParameterizedParent.GeneralizedMethodImpl;
                if (!string.IsNullOrEmpty(methodImpl))
                {
                    yield return methodImpl;
                }
                //
            }
        }

        public string GetByImmediateParentMethodDecl
        {
            get
            {
                return this.getByImmediateParent.GeneralizedMethodDecl;
            }
        }

        public string GetByImmediateParentRxAsyncMethodImplementation(bool isGeneralized)
        {
            return this.getByImmediateParent.GetRxAsyncMethodImplementation(isGeneralized);
        }

        #endregion

        public IEnumerable<string> GetByResourceGroupSyncAsyncImplementation
        {
            get
            {
                foreach(string methodImpl in this.getByResourceGroup.GetSyncAsyncMethodImplementations)
                {
                    yield return methodImpl;
                }
            }
        }
    }
}
