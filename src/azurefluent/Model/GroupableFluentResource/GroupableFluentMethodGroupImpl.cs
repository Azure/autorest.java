// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class GroupableFluentMethodGroupImpl
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        private readonly GroupableFluentModelImpl fluentModelImpl;
        private readonly FluentMethodGroup Interface;

        public GroupableFluentMethodGroupImpl(GroupableFluentModelImpl fluentModelImpl)
        {
            this.fluentModelImpl = fluentModelImpl;
            this.Interface = fluentModelImpl.Interface.FluentMethodGroup;
        }

        public string JvaClassName
        {
            get
            {
                return $"{this.Interface.JavaInterfaceName}Impl";
            }
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
                foreach (string wrapMethod in this.Interface.NonStandardInnerToStandardInnerWrappingMethodImplementations)
                {
                    yield return wrapMethod;
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
                    $"com.microsoft.azure.management.resources.fluentcore.arm.collection.implementation.GroupableResourcesImpl",
                    $"{this.package}.{MethodGroupInterfaceName}",
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
                return imports;
            }
        }

        public string ExtendsFrom
        {
            get
            {
                return $" extends GroupableResourcesImpl<{GroupableModelInterfaceName}, {GroupableModelImplName}, {GroupableModelInnerName}, {InnerClientName}, {ManagerTypeName}> ";
            }
        }

        public string Implements
        {
            get
            {
                return $" implements {MethodGroupInterfaceName}";
            }
        }

        private string CtrImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();

                methodBuilder.AppendLine($"protected {this.MethodGroupImplName}({this.ManagerTypeName} manager) {{");
                methodBuilder.AppendLine($"    super(manager.inner().{this.InnerClientAccessorName}(), manager);");
                methodBuilder.AppendLine($"}}");

                return methodBuilder.ToString();
            }
        }

        private IEnumerable<string> ChildMethodGroupAccessors
        {
            get
            {
                foreach (var nestedFluentMethodGroup in this.Interface.ChildFluentMethodGroups)
                {
                    StringBuilder methodBuilder = new StringBuilder();

                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public {nestedFluentMethodGroup.JavaInterfaceName} {nestedFluentMethodGroup.AccessorMethodName}() {{");
                    methodBuilder.AppendLine($"    {nestedFluentMethodGroup.JavaInterfaceName} accessor = this.manager().{nestedFluentMethodGroup.JavaInterfaceName.ToCamelCase()}();");
                    methodBuilder.AppendLine($"    return accessor;");
                    methodBuilder.AppendLine($"}}");

                    yield return methodBuilder.ToString();
                }
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
                    .ListByResourceGroupSyncMethodImplementation("this.wrapList", this.InnerClientName, GroupableModelInterfaceName);
            }
        }

        private string ListByResourceGroupAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListByResourceGroupAsyncMethodImplementation(this.InnerClientName, this.GroupableModelInterfaceName);
            }
        }

        private string ListBySubscriptionSyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListBySubscriptionSyncMethodImplementation("this.wrapList", this.InnerClientName, this.GroupableModelInterfaceName);
            }
        }

        private string ListBySubscriptionAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListBySubscriptionAsyncMethodImplementation(this.InnerClientName, this.GroupableModelInterfaceName);
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
                    methodBuilder.AppendLine($"public {this.fluentModelImpl.JvaClassName} define(String name) {{");
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

        private string MethodGroupImplName
        {
            get
            {
                return $"{MethodGroupInterfaceName}Impl";
            }
        }

        private string MethodGroupInterfaceName
        {
            get
            {
                return this.Interface.JavaInterfaceName;
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

        private string InnerClientName
        {
            get
            {
                return this.Interface.InnerMethodGroupTypeName;
            }
        }

        private string InnerClientAccessorName
        {
            get
            {
                return this.Interface.InnerMethodGroupAccessorName;
            }
        }

        private string ManagerTypeName
        {
            get
            {
                return this.fluentModelImpl.Interface.FluentMethodGroup.ManagerTypeName;
            }
        }
    }
}
