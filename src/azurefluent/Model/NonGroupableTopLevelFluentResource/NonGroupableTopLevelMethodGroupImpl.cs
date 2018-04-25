// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class NonGroupableTopLevelMethodGroupImpl : FluentMethodGroupImpl
    {
        private readonly NonGroupableTopLevelFluentModelImpl fluentModelImpl;

        public NonGroupableTopLevelMethodGroupImpl(NonGroupableTopLevelFluentModelImpl fluentModelImpl) : base(fluentModelImpl.Interface.FluentMethodGroup)
        {
            this.fluentModelImpl = fluentModelImpl;
        }

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
                //
                if (this.Interface.ResourceListingDescription.SupportsListByResourceGroup)
                {
                    imports.Add("com.microsoft.azure.arm.utils.PagedListConverter");
                    imports.Add($"{this.package}.{this.NonGroupableModelInterfaceName}");
                }
                //
                if (this.Interface.ResourceListingDescription.SupportsListBySubscription)
                {
                    imports.Add("com.microsoft.azure.arm.utils.PagedListConverter");
                    imports.Add($"{this.package}.{this.NonGroupableModelInterfaceName}");
                }
                //
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
                return $" extends WrapperImpl<{this.InnerMethodGroupName}>";
            }
        }

        public string Implements
        {
            get
            {
                return $" implements {this.JvaInterfaceName}";
            }
        }

        public IEnumerable<string> DeclareMemberVariables
        {
            get
            {
                if (this.Interface.ResourceListingDescription.SupportsListByResourceGroup || 
                    this.Interface.ResourceListingDescription.SupportsListBySubscription)
                {
                    yield return $"private PagedListConverter<{this.NonGroupableModelInnerName}, {this.NonGroupableModelInterfaceName}> converter;";
                }
                yield return this.DeclareManagerVariable;
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
                yield return this.ManagerGetterImplementation;
                yield return this.DefineMethodImplementation;
                yield return this.WrapExistingModelImplementation;
                yield return this.WrapNewModelImplementation;
                foreach (string impl in this.Interface.OtherMethods.MethodsImplementation)
                {
                    yield return impl;
                }
                yield return this.ListBySubscriptionSyncMethodImplementation;
                yield return this.ListBySubscriptionAsyncMethodImplementation;
                yield return this.ListByResourceGroupSyncMethodImplementation;
                yield return this.ListByResourceGroupAsyncMethodImplementation;
                // TODO implement deletebyRG + batchDelete
                //
            }
        }

        private string CtrImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"{this.JvaClassName}({ManagerTypeName} manager) {{");
                methodBuilder.AppendLine($"    super(manager.inner().{this.InnerClientAccessorName}());"); // WrapperImpl(inner)
                methodBuilder.AppendLine($"    this.manager = manager;");
                if (this.Interface.ResourceListingDescription.SupportsListByResourceGroup || 
                    this.Interface.ResourceListingDescription.SupportsListBySubscription)
                {
                    methodBuilder.AppendLine($"    this.converter = new PagedListConverter<{NonGroupableModelInnerName}, {NonGroupableModelInterfaceName}>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Observable<{NonGroupableModelInterfaceName}> typeConvertAsync({NonGroupableModelInnerName} inner) {{");
                    methodBuilder.AppendLine($"            return Observable.just(({NonGroupableModelInterfaceName}) wrapModel(inner));");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }};");
                }
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
        }

        public string DefineMethodImplementation
        {
            get
            {
                if (this.Interface.ResourceCreateDescription.SupportsCreating)
                {
                    StringBuilder methodBuilder = new StringBuilder();
                    methodBuilder.AppendLine("@Override");
                    methodBuilder.AppendLine($"public {this.NonGroupableModelImplName} define(String name) {{");
                    methodBuilder.AppendLine($"    return wrapModel(name);");
                    methodBuilder.AppendLine($"}}");
                    return methodBuilder.ToString();
                }
                else
                {
                    return String.Empty;
                }
            }
        }

        public string WrapExistingModelImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"private {this.NonGroupableModelImplName} wrapModel({this.fluentModelImpl.InnerModelTypeName} inner) {{");
                methodBuilder.AppendLine($"    return {this.fluentModelImpl.CtrInvocationFromWrapExistingInnerModel}");
                methodBuilder.AppendLine($"}}");
                return methodBuilder.ToString();
            }
        }

        private string WrapNewModelImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                //
                methodBuilder.AppendLine($"private {this.NonGroupableModelImplName} wrapModel(String name) {{");
                methodBuilder.AppendLine($"    return {this.fluentModelImpl.CtrInvocationFromWrapNewInnerModel}");
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
        }

        private string ListByResourceGroupSyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListByResourceGroupSyncMethodImplementation("converter.convert", this.InnerClientName, this.NonGroupableModelInnerName, this.NonGroupableModelInterfaceName);
            }
        }

        private string ListByResourceGroupAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListByResourceGroupAsyncMethodImplementation(this.InnerClientName, this.NonGroupableModelInnerName, this.NonGroupableModelInterfaceName);
            }
        }

        private string ListBySubscriptionSyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListBySubscriptionSyncMethodImplementation("converter.convert", this.InnerClientName, this.NonGroupableModelInterfaceName, this.NonGroupableModelInterfaceName);
            }
        }

        private string ListBySubscriptionAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListBySubscriptionAsyncMethodImplementation(this.InnerClientName, this.NonGroupableModelInnerName, this.NonGroupableModelInterfaceName);
            }
        }

        private string NonGroupableModelInterfaceName
        {
            get
            {
                return this.fluentModelImpl.Interface.JavaInterfaceName;
            }
        }

        private string NonGroupableModelImplName
        {
            get
            {
                return this.fluentModelImpl.JvaClassName;
            }
        }

        private string NonGroupableModelInnerName
        {
            get
            {
                return this.fluentModelImpl.Interface.InnerModel.ClassName;
            }
        }
    }
}
