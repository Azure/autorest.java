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
    public class NonGroupableTopLevelMethodGroupImpl
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        private readonly NonGroupableTopLevelFluentModelImpl fluentModelImpl;
        private readonly FluentMethodGroup Interface;

        public NonGroupableTopLevelMethodGroupImpl(NonGroupableTopLevelFluentModelImpl fluentModelImpl)
        {
            this.fluentModelImpl = fluentModelImpl;
            this.Interface = fluentModelImpl.Interface.FluentMethodGroup;
        }

        private string JavaInterfaceName
        {
            get
            {
                return this.fluentModelImpl.Interface.JavaInterfaceName;
            }
        }

        private string ModelInnerName
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

        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    "com.microsoft.azure.management.resources.fluentcore.model.implementation.WrapperImpl",
                    $"{this.package}.{this.Interface.JavaInterfaceName}",
                };
                imports.AddRange(this.Interface.ResourceCreateDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.ResourceDeleteDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.ResourceGetDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.ResourceListingDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.OtherMethods.ImportsForImpl);
                //
                if (this.Interface.ResourceListingDescription.SupportsListByResourceGroup)
                {
                    imports.Add("com.microsoft.azure.management.resources.fluentcore.utils.PagedListConverter");
                    imports.Add($"{this.package}.{this.fluentModelImpl.Interface.JavaInterfaceName}");
                }
                //
                if (this.Interface.ResourceListingDescription.SupportsListBySubscription)
                {
                    imports.Add($"{this.package}.{this.fluentModelImpl.Interface.JavaInterfaceName}");
                    imports.Add("com.microsoft.azure.management.resources.fluentcore.utils.PagedListConverter");
                }
                //
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
                return $" extends WrapperImpl<{this.Interface.InnerMethodGroup.MethodGroupImplType}>";
            }
        }

        public string Implements
        {
            get
            {
                return $" implements {this.Interface.JavaInterfaceName}";
            }
        }

        public string JvaClassName
        {
            get
            {
                return $"{this.Interface.JavaInterfaceName}Impl";
            }
        }

        public IEnumerable<string> DeclareMemberVariables
        {
            get
            {
                if (this.Interface.ResourceListingDescription.SupportsListByResourceGroup || 
                    this.Interface.ResourceListingDescription.SupportsListBySubscription)
                {
                    yield return $"private PagedListConverter<{this.ModelInnerName}, {this.JavaInterfaceName}> converter;";
                }
                yield return DeclareManagerVariable;
            }
        }

        private string DeclareManagerVariable
        {
            get
            {
                string managerTypeName = this.fluentModelImpl.Interface.FluentMethodGroup.ManagerTypeName;
                return $"private final {managerTypeName} manager;";
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

        public IEnumerable<string> ChildMethodGroupAccessors
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

        private string ManagerGetterImplementation
        {
            get
            {
                string managerTypeName = this.fluentModelImpl.Interface.FluentMethodGroup.ManagerTypeName;
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"public {managerTypeName} manager() {{");
                methodBuilder.AppendLine($"    return this.manager;");
                methodBuilder.AppendLine($"}}");
                return methodBuilder.ToString();
            }
        }

        private string CtrImplementation
        {
            get
            {
                string managerTypeName = this.fluentModelImpl.Interface.FluentMethodGroup.ManagerTypeName;

                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"{this.JvaClassName}({managerTypeName} manager) {{");
                methodBuilder.AppendLine($"    super(manager.inner().{this.Interface.InnerMethodGroupAccessorName}());"); // WrapperImpl(inner)
                methodBuilder.AppendLine($"    this.manager = manager;");
                if (this.Interface.ResourceListingDescription.SupportsListByResourceGroup || 
                    this.Interface.ResourceListingDescription.SupportsListBySubscription)
                {
                    methodBuilder.AppendLine($"    this.converter = new PagedListConverter<{ModelInnerName}, {JavaInterfaceName}>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Observable<{JavaInterfaceName}> typeConvertAsync({ModelInnerName} inner) {{");
                    methodBuilder.AppendLine($"            return Observable.just(({JavaInterfaceName}) wrapModel(inner));");
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
                if (this.SupportsCreating)
                {
                    StringBuilder methodBuilder = new StringBuilder();
                    methodBuilder.AppendLine("@Override");
                    methodBuilder.AppendLine($"public {this.fluentModelImpl.JvaClassName} define(String name) {{");
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
                methodBuilder.AppendLine($"private {this.fluentModelImpl.JvaClassName} wrapModel({this.fluentModelImpl.InnerModelTypeName} inner) {{");
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
                methodBuilder.AppendLine($"@Override");
                methodBuilder.AppendLine($"protected {this.fluentModelImpl.JvaClassName} wrapModel(String name) {{");
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
                    .ListByResourceGroupSyncMethodImplementation("converter.convert", this.InnerClientName, this.JavaInterfaceName);
            }
        }

        private string ListByResourceGroupAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListByResourceGroupAsyncMethodImplementation(this.InnerClientName, this.JavaInterfaceName);
            }
        }

        private string ListBySubscriptionSyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListBySubscriptionSyncMethodImplementation("converter.convert", this.InnerClientName, this.JavaInterfaceName);
            }
        }

        private string ListBySubscriptionAsyncMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription
                    .ListBySubscriptionAsyncMethodImplementation(this.InnerClientName, this.JavaInterfaceName);
            }
        }

        private string InnerMethodInvocationParameter(MethodJvaf innerMethod)
        {
            List<string> invoke = new List<string>();
            foreach (var parameter in innerMethod.LocalParameters.Where(p => !p.IsConstant && p.IsRequired))
            {
                invoke.Add(parameter.Name);
            }

            return string.Join(", ", invoke);
        }

        private bool SupportsCreating
        {
            get
            {
                return this.Interface.ResourceCreateDescription.SupportsCreating;
            }
        }
    }

}
