// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The model used by the template to generate Java class (aka Nested Resource Method Group Implementation) that implements "Nested Resource Method Group Interface".
    /// Nested Resource: Represents an Azure resource whose level > 0 and hence has immediate parent resource other than resource group.
    /// Nested Resource Method Group: Represents a container that defines operations on Nested Resource [e.g. StorageAccountsImpl].
    /// 
    /// A java class of this type inheris from
    /// https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/WrapperImpl.java
    /// </summary>
    public class ClientFluentNestedMethodGroupImpl : ClientFluentMethodGroupImpl
    {
        public ClientFluentNestedMethodGroupImpl(IFluentMethodGroup methodGroup) : base(methodGroup)
        {
        }

        /// <summary>
        /// The imports to be imported by the Nested Toplevel Resource Method Group Impl.
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
                imports.AddRange(this.Interface.OtherMethods.ImportsForImpl);

                if (this.Interface.ResourceGetDescription.SupportsGetByImmediateParent 
                    || this.Interface.ResourceListingDescription.SupportsListByImmediateParent)
                {
                    imports.Add($"{this.package}.{this.Model.JavaInterfaceName}");
                }
                //
                imports.AddRange(base.ImportsForGeneralizedMethodImpls);
                return imports;
            }
        }

        /// <summary>
        /// The name of the base class from which Nested Toplevel Resource Method Group Impl extends.
        /// </summary>
        public string ExtendsFrom
        {
            get
            {
                return $" extends WrapperImpl<{this.InnerMethodGroupName}>";
            }
        }

        /// <summary>
        /// The name of the interface that Nested Resource Method Group implements.
        /// </summary>
        public string Implements
        {
            get
            {
                return $" implements {this.Interface.JavaInterfaceName}";
            }
        }

        /// <summary>
        /// Returns a list of string, with each entry contains the definition of method in Nested Resource Method Group Implementation.
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
                yield return this.ListByImmediateParentMethodImplementation;
                yield return this.GetByImmediateParentMethodImplementation;
                yield return this.DeleteByImmediateParentMethodImplementation;
                foreach (string impl in base.GeneralizedMethodImpls)
                {
                    yield return impl;
                }
            }
        }

        /// <summary>
        /// Implementation of Nested Resource Method Group Constructor.
        /// </summary>
        private string CtrImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"{this.JavaClassName}({this.ManagerTypeName} manager) {{");
                methodBuilder.AppendLine($"    super(manager.inner().{this.InnerClientAccessorName}());"); // WrapperImpl(inner)
                methodBuilder.AppendLine($"    this.manager = manager;");
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
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
        /// Implementation of wrapModel(innerT inner) method.
        /// </summary>
        private string WrapExistingModelImplementation
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
        /// Implementation of async method that list Nested Resources under it's parent.
        /// </summary>
        private string ListByImmediateParentMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription.ListByImmediateParentRxAsyncMethodImplementation(false);
            }
        }

        /// <summary>
        /// Implementation of async method that retrieve a Nested Resource under it's parent.
        /// </summary>
        private string GetByImmediateParentMethodImplementation
        {
            get
            {
                return this.Interface.ResourceGetDescription.GetByImmediateParentRxAsyncMethodImplementation(false);
            }
        }

        /// <summary>
        /// Implementation of async method that deletes a Nested Resource under it's parent.
        /// </summary>
        private string DeleteByImmediateParentMethodImplementation
        {
            get
            {
                return this.Interface.ResourceDeleteDescription.DeleteByImmediateParentRxAsyncMethodImplementation;
            }
        }
    }
}
