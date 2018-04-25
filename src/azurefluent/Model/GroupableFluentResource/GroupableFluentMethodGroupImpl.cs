// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class GroupableFluentMethodGroupImpl : FluentMethodGroupImpl
    {
        private readonly GroupableFluentModelImpl fluentModelImpl;

        public GroupableFluentMethodGroupImpl(GroupableFluentModelImpl fluentModelImpl) : base(fluentModelImpl.Interface.FluentMethodGroup)
        {
            this.fluentModelImpl = fluentModelImpl;
        }

        public IEnumerable<string> JavaMethods
        {
            get
            {
                yield return this.CtrImplementation;
                foreach (string childAccessor in ChildMethodGroupAccessors)
                {
                    yield return childAccessor;
                }
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
                foreach (string impl in this.Interface.OtherMethods.MethodsImplementation)
                {
                    yield return impl;
                }
                yield return this.WrapExistingModelImplementation;
                yield return this.WrapNewModelImplementation;
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
                    $"{this.package}.{GroupableModelInterfaceName}",
                    $"rx.Observable",
                    $"rx.Completable"
                };
                imports.AddRange(this.Interface.ResourceDeleteDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.ResourceListingDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.ResourceGetDescription.ImportsForMethodGroupImpl);
                //
                imports.AddRange(this.Interface.OtherMethods.ImportsForImpl);
                foreach (var nestedFluentMethodGroup in this.Interface.ChildFluentMethodGroups)
                {
                    imports.Add($"{this.package}.{nestedFluentMethodGroup.JavaInterfaceName}");
                }
                //
                imports.AddRange(this.Interface.ImportsForImpl);
                return imports;
            }
        }

        public string ExtendsFrom
        {
            get
            {
                return $" extends GroupableResourcesCoreImpl<{GroupableModelInterfaceName}, {GroupableModelImplName}, {GroupableModelInnerName}, {InnerClientName}, {ManagerTypeName}> ";
            }
        }

        public string Implements
        {
            get
            {
                return $" implements {JvaInterfaceName}";
            }
        }

        private string CtrImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();

                methodBuilder.AppendLine($"protected {this.JvaClassName}({this.ManagerTypeName} manager) {{");
                methodBuilder.AppendLine($"    super(manager.inner().{this.InnerClientAccessorName}(), manager);");
                methodBuilder.AppendLine($"}}");

                return methodBuilder.ToString();
            }
        }

        private string InnerGetMethodImplementation
        {
            get
            {
                return this.Interface.ResourceGetDescription.InnerGetMethodImplementation(true, this.InnerClientName, this.GroupableModelInnerName);
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
                return this.Interface.ResourceDeleteDescription.BatchDeleteAyncAndSyncMethodImplementations(this.InnerClientName);
            }
        }

        private string ListByResourceGroupSyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListByResourceGroupSyncMethodImplementation("this.wrapList", this.InnerClientName, this.GroupableModelInnerName, GroupableModelInterfaceName);
            }
        }

        private string ListByResourceGroupAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListByResourceGroupAsyncMethodImplementation(this.InnerClientName, this.GroupableModelInnerName, this.GroupableModelInterfaceName);
            }
        }

        private string ListBySubscriptionSyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListBySubscriptionSyncMethodImplementation("this.wrapList", this.InnerClientName, this.GroupableModelInnerName, this.GroupableModelInterfaceName);
            }
        }

        private string ListBySubscriptionAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListBySubscriptionAsyncMethodImplementation(this.InnerClientName, this.GroupableModelInnerName, this.GroupableModelInterfaceName);
            }
        }

        private string DefineMethodImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                //
                if (this.Interface.ResourceCreateDescription.SupportsCreating)
                {
                    methodBuilder.AppendLine("@Override");
                    methodBuilder.AppendLine($"public {this.GroupableModelImplName} define(String name) {{");
                    methodBuilder.AppendLine($"    return wrapModel(name);");
                    methodBuilder.AppendLine($"}}");
                    return methodBuilder.ToString();
                }
                //
                return methodBuilder.ToString();
            }
        }

        private string WrapExistingModelImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                //
                methodBuilder.AppendLine($"@Override");
                methodBuilder.AppendLine($"protected {this.GroupableModelImplName} wrapModel({this.GroupableModelInnerName} inner) {{");
                methodBuilder.AppendLine($"    return {this.fluentModelImpl.CtrInvocationFromWrapExistingInnerModel}");
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
        }

        private string WrapNewModelImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                //
                methodBuilder.AppendLine($"@Override");
                methodBuilder.AppendLine($"protected {this.GroupableModelImplName} wrapModel(String name) {{");
                methodBuilder.AppendLine($"    return {this.fluentModelImpl.CtrInvocationFromWrapNewInnerModel}");
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
        }

        private string GroupableModelInterfaceName
        {
            get
            {
                return this.fluentModelImpl.Interface.JavaInterfaceName;
            }
        }

        private string GroupableModelImplName
        {
            get
            {
                return this.fluentModelImpl.JvaClassName;
            }
        }

        private string GroupableModelInnerName
        {
            get
            {
                return this.fluentModelImpl.Interface.InnerModel.ClassName;
            }
        }
    }
}
