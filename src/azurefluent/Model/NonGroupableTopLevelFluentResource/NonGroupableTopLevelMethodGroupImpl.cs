// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class NonGroupableTopLevelMethodGroupImpl : FluentMethodGroupImpl
    {
        public NonGroupableTopLevelMethodGroupImpl(IFluentModel fluentModel) : base(fluentModel)
        {
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
                    yield return $"private PagedListConverter<{this.Model.InnerModelName}, {this.Model.JavaInterfaceName}> converter;";
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
            }
        }

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
                    methodBuilder.AppendLine($"    this.converter = new PagedListConverter<{this.Model.InnerModelName}, {this.Model.JavaInterfaceName}>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Observable<{this.Model.JavaInterfaceName}> typeConvertAsync({this.Model.InnerModelName} inner) {{");
                    methodBuilder.AppendLine($"            return Observable.just(({this.Model.JavaInterfaceName}) wrapModel(inner));");
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
                return this.Interface.ResourceCreateDescription.DefineFunc.MethodImpl;
            }
        }

        public string WrapExistingModelImplementation
        {
            get
            {
                return this.Model.WrapExistingModelFunc.MethodImpl(false);
            }
        }

        private string WrapNewModelImplementation
        {
            get
            {
                return this.Interface.ResourceCreateDescription.WrapNewModelMethodImplementation(false);
            }
        }

        private string ListByResourceGroupSyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListByResourceGroupSyncMethodImplementation("converter.convert", this.InnerClientName, this.Model.InnerModelName, this.Model.JavaInterfaceName);
            }
        }

        private string ListByResourceGroupAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription.ListByResourceGroupAsyncMethodImplementation();
            }
        }

        private string ListBySubscriptionSyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListBySubscriptionSyncMethodImplementation("converter.convert", this.InnerClientName, this.Model.InnerModelName, this.Model.JavaInterfaceName);
            }
        }

        private string ListBySubscriptionAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription.ListBySubscriptionAsyncMethodImplementation();
            }
        }

        private string GetInnerAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceGetDescription.GetInnerMethodImplementation(false);
            }
        }

        private IEnumerable<string> GetByResourceGroupSyncAsyncImplementation
        {
            get
            {
                return this.Interface.ResourceGetDescription.GetByResourceGroupSyncAsyncImplementation;
            }
        }

        private IEnumerable<string> BatchDeleteAyncAndSyncMethodImplementations
        {
            get
            {
                return this.Interface.ResourceDeleteDescription.BatchDeleteAyncAndSyncMethodImplementations();
            }
        }

        public IEnumerable<string> DeleteByResourceGroupSyncAsyncImplementation
        {
            get
            {
                return this.Interface.ResourceDeleteDescription.DeleteByResourceGroupSyncAsyncImplementation();
            }
        }
    }
}
