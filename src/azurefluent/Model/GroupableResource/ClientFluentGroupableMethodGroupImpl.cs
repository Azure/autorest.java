// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The model used by the template to generate Java class (aka Groupable Resource Method Group Implementation) that implements "Groupable Resource Method Group Interface".
    /// Groupable Resource: Represents an Azure resource that appear immediately under Resource Group and is a Tracked Resource [see Utils.IsTrackedResource(param)]
    /// Groupable Resource Method Group: Represents a container that defines operations on Groupable Resource [e.g. StorageAccountsImpl].
    /// 
    /// A java class of this type inheris from
    /// https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/arm/collection/implementation/GroupableResourcesImpl.java
    /// </summary>
    public class ClientFluentGroupableMethodGroupImpl : ClientFluentMethodGroupImpl
    {
        public ClientFluentGroupableMethodGroupImpl(IFluentMethodGroup methodGroup) : base(methodGroup)
        {
        }

        /// <summary>
        /// The imports to be imported by the Groupable Resource Method Group Impl.
        /// </summary>
        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    //
                    $"com.microsoft.azure.arm.resources.collection.implementation.GroupableResourcesCoreImpl",
                    $"{this.package}.{JvaInterfaceName}",
                    $"{this.package}.{Model.JavaInterfaceName}",
                    $"rx.Observable",
                    $"rx.Completable"
                };
                imports.AddRange(this.Interface.ResourceDeleteDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.ResourceListingDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.ResourceGetDescription.ImportsForMethodGroupImpl);
                //
                imports.AddRange(this.Interface.OtherMethods.ImportsForImpl);
                //
                imports.AddRange(this.Interface.ImportsForImpl);
                //
                imports.AddRange(base.ImportsForGeneralizedMethodImpls);
                return imports;
            }
        }

        /// <summary>
        /// The name of the base class from which Groupable Resource Method Group Impl extends.
        /// </summary>
        public string ExtendsFrom
        {
            get
            {
                return $" extends GroupableResourcesCoreImpl<{this.Model.JavaInterfaceName}, {this.Model.JavaClassName}, {this.Model.RawModelName}, {InnerClientName}, {ManagerTypeName}> ";
            }
        }

        /// <summary>
        /// The name of the interface that Groupable Resource Method Group implements.
        /// </summary>
        public string Implements
        {
            get
            {
                return $" implements {this.JvaInterfaceName}";
            }
        }

        /// <summary>
        /// Implementation of Groupable Resource Method Group Constructor.
        /// </summary>
        private string CtrImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();

                methodBuilder.AppendLine($"protected {this.JavaClassName}({this.ManagerTypeName} manager) {{");
                methodBuilder.AppendLine($"    super(manager.inner().{this.InnerClientAccessorName}(), manager);");
                methodBuilder.AppendLine($"}}");

                return methodBuilder.ToString();
            }
        }


        /// <summary>
        /// Returns a list of string, with each entry contains the definition of method in Groupable Resource Method Group Implementation.
        /// </summary>
        public IEnumerable<string> JavaMethods
        {
            get
            {
                yield return this.CtrImplementation;
                yield return this.InnerGetMethodImplementation;
                yield return this.InnerDeleteMethodImplementation;
                foreach (string batchDeleteMethod in this.BatchDeleteAsyncSyncMethodImplementations)
                {
                    yield return batchDeleteMethod;
                }
                yield return this.ListByResourceGroupSyncMethodImplementation;
                yield return this.ListByResourceGroupAsyncMethodImplementation;
                yield return this.ListBySubscriptionSyncMethodImplementation;
                yield return this.ListBySubscriptionAsyncMethodImplementation;
                yield return this.DefineMethodImplementation;
                foreach (string impl in this.Interface.OtherMethods.MethodImpls)
                {
                    yield return impl;
                }
                yield return this.WrapExistingModelImplementation;
                yield return this.WrapNewModelImplementation;
                foreach (string impl in base.GeneralizedMethodImpls)
                {
                    yield return impl;
                }
            }
        }

        /// <summary>
        /// Implementation of getInnerAsync(params) method.
        /// protected abstract  Observable<InnerT> GroupableResourcesCoreImpl::getInnerAsync(resourceGroupName, name).
        /// </summary>
        private string InnerGetMethodImplementation
        {
            get
            {
                return this.Interface.ResourceGetDescription.GetInnerAsyncFuncFactory.GetFromResourceGroupAsyncFunc.MethodImpl(true);
            }
        }

        /// <summary>
        /// Implementation of deleteInnerAsync(params) method.
        /// protected abstract Completable GroupableResourcesCoreImpl::deleteInnerAsync(resourceGroupName, name).
        /// </summary>
        private string InnerDeleteMethodImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                //
                methodBuilder.AppendLine("@Override");
                methodBuilder.AppendLine($"protected Completable deleteInnerAsync(String resourceGroupName, String name) {{");
                methodBuilder.AppendLine($"    {this.InnerClientName} client = this.inner();");
                if (this.Interface.ResourceDeleteDescription.SupportsDeleteByResourceGroup)
                {
                    StandardFluentMethod method = this.Interface.ResourceDeleteDescription.DeleteByResourceGroupMethod;
                    methodBuilder.AppendLine($"    return client.{method.Name}Async(resourceGroupName, name).toCompletable();");
                }
                else
                {
                    methodBuilder.AppendLine($"    return Completable.error(new Throwable(\"Delete by RG not supported for this resource\")); // NOP Delete by RG not supported") ;
                }
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
        }

        /// <summary>
        /// Implementation of SupportsBatchDeletion interface.
        /// https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/arm/collection/SupportsBatchDeletion.java
        /// </summary>
        IEnumerable<string> BatchDeleteAsyncSyncMethodImplementations
        {
            get
            {
                return this.Interface.ResourceDeleteDescription.BatchDeleteAyncAndSyncMethodImplementations();
            }
        }

        /// <summary>
        /// Implementation of sync methods in SupportsListingByResourceGroup interface.
        /// https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/arm/collection/SupportsListingByResourceGroup.java
        /// </summary>
        private string ListByResourceGroupSyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListByResourceGroupSyncMethodImplementation("this.wrapList");
            }
        }

        /// <summary>
        /// Implementation of async methods in SupportsListingByResourceGroup interface.
        /// https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/arm/collection/SupportsListingByResourceGroup.java
        /// </summary>
        private string ListByResourceGroupAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription.ListByResourceGroupRxAsyncMethodImplementation(false);
            }
        }

        /// <summary>
        /// Implementation of sync methods in SupportsListing interface.
        /// https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/collection/InnerSupportsListing.java
        /// </summary>
        private string ListBySubscriptionSyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListBySubscriptionSyncMethodImplementation("this.wrapList");
            }
        }

        /// <summary>
        /// Implementation of async methods in SupportsListing interface.
        /// https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/collection/InnerSupportsListing.java
        /// </summary>
        private string ListBySubscriptionAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription.ListBySubscriptionRxAsyncMethodImplementation(false);
            }
        }

        /// <summary>
        /// Implementation of SupportsCreating interface.
        /// https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/collection/SupportsCreating.java
        /// </summary>
        private string DefineMethodImplementation
        {
            get
            {
                return this.Interface.ResourceCreateDescription.DefineFunc.MethodImpl;
            }
        }

        /// <summary>
        /// Implementation of wrapModel(InnerT) method.
        /// protected abstract ImplT ReadableWrappersImpl::wrapModel(InnerT inner)
        /// </summary>
        private string WrapExistingModelImplementation
        {
            get
            {
                return this.Model.WrapExistingModelFunc.MethodImpl(true);
            }
        }

        /// <summary>
        /// Implementation of wrapModel(String) method.
        /// protected abstract ImplT CreatableWrappersImpl::wrapModel(String name)
        /// </summary>
        private string WrapNewModelImplementation
        {
            get
            {
                return this.Interface.ResourceCreateDescription.WrapNewModelFunc.MethodImpl(true);
            }
        }
    }
}
