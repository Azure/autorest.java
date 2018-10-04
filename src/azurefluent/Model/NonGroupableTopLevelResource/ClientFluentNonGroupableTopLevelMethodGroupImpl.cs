// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The model used by the template to generate Java class (aka Non-Groupable Toplevel Resource Method Group Implementation) that implements "Groupable Resource Method Group Interface".
    /// Non-Groupable TopLevel Resource: Represents an Azure resource that is at level 0 and is Not a Tracked Resource [see Utils.IsTrackedResource(param)]
    /// Non-Groupable TopLevel Resource Method Group: Represents a container that defines operations on Groupable Resource [e.g. StorageAccountsImpl].
    /// 
    /// A java class of this type inheris from
    /// https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/WrapperImpl.java
    /// </summary>
    public class ClientFluentNonGroupableTopLevelMethodGroupImpl : ClientFluentMethodGroupImpl
    {
        public ClientFluentNonGroupableTopLevelMethodGroupImpl(IFluentMethodGroup methodGroup) : base(methodGroup)
        {
        }

        /// <summary>
        /// The imports to be imported by the Non-Groupable Toplevel Resource Method Group Impl.
        /// </summary>
        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    "com.microsoft.azure.arm.model.implementation.WrapperImpl",
                    $"{this.package}.{this.JvaInterfaceName}",
                };
                imports.AddRange(this.Interface.ResourceCreateDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.ResourceDeleteDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.ResourceGetDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.ResourceListingDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.ResourceGetDescription.ImportsForMethodGroupWithLocalGetByResourceGroupImpl);
                imports.AddRange(this.Interface.ResourceDeleteDescription.ImportsForMethodGroupWithLocalDeleteByResourceGroupImpl);
                imports.AddRange(this.Interface.OtherMethods.ImportsForImpl);
                //
                if (this.Interface.ResourceListingDescription.SupportsListByResourceGroup)
                {
                    imports.Add("com.microsoft.azure.arm.utils.PagedListConverter");
                    imports.Add($"{this.package}.{this.Model.JavaInterfaceName}");
                }
                //
                if (this.Interface.ResourceListingDescription.SupportsListBySubscription)
                {
                    imports.Add("com.microsoft.azure.arm.utils.PagedListConverter");
                    imports.Add($"{this.package}.{this.Model.JavaInterfaceName}");
                }
                //
                imports.AddRange(this.Interface.ImportsForImpl);
                imports.AddRange(base.ImportsForGeneralizedMethodImpls);
                return imports;
            }
        }

        /// <summary>
        /// The name of the base class from which Non-Groupable Toplevel Resource Method Group Impl extends.
        /// </summary>
        public string ExtendsFrom
        {
            get
            {
                return $" extends WrapperImpl<{this.InnerMethodGroupName}>";
            }
        }

        /// <summary>
        /// The name of the interface that Non-Groupable Toplevel Resource Method Group implements.
        /// </summary>
        public string Implements
        {
            get
            {
                return $" implements {this.JvaInterfaceName}";
            }
        }

        /// <summary>
        /// Retrun a list of string, each string containing declaration of a memeber variable.
        /// </summary>
        public IEnumerable<string> DeclareMemberVariables
        {
            get
            {
                if (this.Interface.ResourceListingDescription.SupportsListByResourceGroup ||
                    this.Interface.ResourceListingDescription.SupportsListBySubscription)
                {
                    yield return $"private PagedListConverter<{this.Model.RawModelName}, {this.Model.JavaInterfaceName}> converter;";
                }
                yield return this.DeclareManagerVariable;
            }
        }

        /// <summary>
        /// Returns a list of string, with each entry contains the definition of method in Non-Groupable Toplevel Resource Method Group Implementation.
        /// </summary>
        public IEnumerable<string> JavaMethods
        {
            get
            {
                yield return this.CtrImplementation;
                yield return this.ManagerGetterImplementation;
                yield return this.DefineMethodImplementation;
                yield return this.WrapExistingModelImplementation;
                yield return this.WrapNewModelImplementation;
                foreach (string impl in this.Interface.OtherMethods.MethodImpls)
                {
                    yield return impl;
                }
                yield return this.ListBySubscriptionSyncMethodImplementation;
                yield return this.ListBySubscriptionAsyncMethodImplementation;
                yield return this.ListByResourceGroupSyncMethodImplementation;
                yield return this.ListByResourceGroupAsyncMethodImplementation;
                yield return this.GetInnerAsyncMethodImplementation;
                foreach (string impl in this.GetByResourceGroupSyncAsyncImplementation)
                {
                    yield return impl;
                }
                foreach (string impl in this.BatchDeleteAyncAndSyncMethodImplementations)
                {
                    yield return impl;
                }
                foreach (string impl in this.DeleteByResourceGroupSyncAsyncImplementation)
                {
                    yield return impl;
                }
                foreach (string impl in base.GeneralizedMethodImpls)
                {
                    yield return impl;
                }
            }
        }

        /// <summary>
        /// Implementation of Non-Groupable Toplevel Resource Method Group Constructor.
        /// </summary>
        private string CtrImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"{this.JavaClassName}({ManagerTypeName} manager) {{");
                methodBuilder.AppendLine($"    super(manager.inner().{this.InnerClientAccessorName}());"); // WrapperImpl(inner)
                methodBuilder.AppendLine($"    this.manager = manager;");
                if (this.Interface.ResourceListingDescription.SupportsListByResourceGroup ||
                    this.Interface.ResourceListingDescription.SupportsListBySubscription)
                {
                    methodBuilder.AppendLine($"    this.converter = new PagedListConverter<{this.Model.RawModelName}, {this.Model.JavaInterfaceName}>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Observable<{this.Model.JavaInterfaceName}> typeConvertAsync({this.Model.RawModelName} inner) {{");
                    methodBuilder.AppendLine($"            return Observable.just(({this.Model.JavaInterfaceName}) wrapModel(inner));");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }};");
                }
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
        }

        /// <summary>
        /// Implementation of SupportsCreating interface.
        /// https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/collection/SupportsCreating.java
        /// </summary>
        public string DefineMethodImplementation
        {
            get
            {
                return this.Interface.ResourceCreateDescription.DefineFunc.MethodImpl;
            }
        }

        /// <summary>
        /// Implementation of wrapModel(innerT inner) method.
        /// </summary>
        public string WrapExistingModelImplementation
        {
            get
            {
                return this.Model.WrapExistingModelFunc.MethodImpl(false);
            }
        }

        /// <summary>
        /// Implementation of wrapModel(String name) method.
        /// </summary>
        private string WrapNewModelImplementation
        {
            get
            {
                return this.Interface.ResourceCreateDescription.WrapNewModelFunc.MethodImpl(false);
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
                    .ListByResourceGroupSyncMethodImplementation("converter.convert");
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
                    .ListBySubscriptionSyncMethodImplementation("converter.convert");
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
        /// Implementation of getInnerAsync(params) method.
        /// </summary>
        private string GetInnerAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceGetDescription.GetInnerAsyncFuncFactory.GetFromResourceGroupAsyncFunc.MethodImpl(false);
            }
        }

        /// <summary>
        /// Implementation of async methods in SupportsGettingByResourceGroup interface.
        /// https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/resources/collection/SupportsGettingByResourceGroup.java
        /// </summary>
        private IEnumerable<string> GetByResourceGroupSyncAsyncImplementation
        {
            get
            {
                return this.Interface.ResourceGetDescription.GetByResourceGroupSyncAsyncImplementation;
            }
        }

        /// <summary>
        /// Implementation of SupportsBatchDeletion interface.
        /// https://github.com/Azure/azure-sdk-for-java/blob/master/archive/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/arm/collection/SupportsBatchDeletion.java
        /// </summary>
        private IEnumerable<string> BatchDeleteAyncAndSyncMethodImplementations
        {
            get
            {
                return this.Interface.ResourceDeleteDescription.BatchDeleteAyncAndSyncMethodImplementations();
            }
        }

        /// <summary>
        /// Implementation of SupportsDeletingByResourceGroup interface.
        /// https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/resources/collection/SupportsDeletingByResourceGroup.java
        /// </summary>
        public IEnumerable<string> DeleteByResourceGroupSyncAsyncImplementation
        {
            get
            {
                return this.Interface.ResourceDeleteDescription.DeleteByResourceGroupSyncAsyncImplementation();
            }
        }
    }
}
