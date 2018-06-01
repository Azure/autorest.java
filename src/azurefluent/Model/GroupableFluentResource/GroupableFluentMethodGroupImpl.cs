// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class GroupableFluentMethodGroupImpl : FluentMethodGroupImpl
    {
        public GroupableFluentMethodGroupImpl(IFluentMethodGroup methodGroup) : base(methodGroup)
        {
        }

        public IEnumerable<string> JavaMethods
        {
            get
            {
                yield return this.CtrImplementation;
                yield return this.InnerGetMethodImplementation;
                yield return this.InnerDeleteMethodImplementation;
                foreach(string batchDeleteMethod in this.BatchDeleteAsyncSyncMethodImplementations)
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

        public string ExtendsFrom
        {
            get
            {
                return $" extends GroupableResourcesCoreImpl<{this.Model.JavaInterfaceName}, {this.Model.JavaClassName}, {this.Model.InnerModelName}, {InnerClientName}, {ManagerTypeName}> ";
            }
        }

        public string Implements
        {
            get
            {
                return $" implements {this.JvaInterfaceName}";
            }
        }

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

        private string InnerGetMethodImplementation
        {
            get
            {
                return this.Interface.ResourceGetDescription.GetInnerAsyncFuncFactory.GetFromResourceGroupAsyncFunc.MethodImpl(true);
            }
        }

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
                    FluentMethod method = this.Interface.ResourceDeleteDescription.DeleteByResourceGroupMethod;
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

        IEnumerable<string> BatchDeleteAsyncSyncMethodImplementations
        {
            get
            {
                return this.Interface.ResourceDeleteDescription.BatchDeleteAyncAndSyncMethodImplementations();
            }
        }

        private string ListByResourceGroupSyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListByResourceGroupSyncMethodImplementation("this.wrapList");
            }
        }

        private string ListByResourceGroupAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription.ListByResourceGroupRxAsyncMethodImplementation(false);
            }
        }

        private string ListBySubscriptionSyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListBySubscriptionSyncMethodImplementation("this.wrapList");
            }
        }

        private string ListBySubscriptionAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription.ListBySubscriptionRxAsyncMethodImplementation(false);
            }
        }

        private string DefineMethodImplementation
        {
            get
            {
                return this.Interface.ResourceCreateDescription.DefineFunc.MethodImpl;
            }
        }

        private string WrapExistingModelImplementation
        {
            get
            {
                return this.Model.WrapExistingModelFunc.MethodImpl(true);
            }
        }

        private string WrapNewModelImplementation
        {
            get
            {
                return this.Interface.ResourceCreateDescription.WrapNewModelFunc.MethodImpl(true);
            }
        }
    }
}
