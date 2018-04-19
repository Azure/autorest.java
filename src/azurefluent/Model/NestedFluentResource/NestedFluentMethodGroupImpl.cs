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
    public class NestedFluentMethodGroupImpl
    {
        private readonly NestedFluentModelImpl fluentModelImpl;
        private readonly FluentMethodGroup Interface;
        private readonly string package = Settings.Instance.Namespace.ToLower();

        public NestedFluentMethodGroupImpl(NestedFluentModelImpl fluentModelImpl)
        {
            this.fluentModelImpl = fluentModelImpl;
            this.Interface = fluentModelImpl.Interface.FluentMethodGroup;
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

                if (this.Interface.ResourceGetDescription.SupportsGetByImmediateParent 
                    || this.Interface.ResourceListingDescription.SupportsListByImmediateParent)
                {
                    imports.Add($"{this.package}.{this.fluentModelImpl.Interface.JavaInterfaceName}");
                }
                //
                string managerTypeName = this.fluentModelImpl.Interface.FluentMethodGroup.ManagerTypeName;
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

        public string DeclareManagerVariable
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
                yield return this.WrapModelImplementation;
                foreach (string impl in this.Interface.OtherMethods.MethodsImplementation)
                {
                    yield return impl;
                }
                yield return this.ListByImmediateParentMethodImplementation;
                yield return this.GetByImmediateParentMethodImplementation;
                yield return this.DeleteByImmediateParentMethodImplementation;
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
                    methodBuilder.AppendLine($"public {nestedFluentMethodGroup.JavaInterfaceName} {nestedFluentMethodGroup.LocalNameInCamelCase}() {{");
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
                // methodBuilder.AppendLine($"{this.JvaClassName}({this.Interface.InnerMethodGroup.MethodGroupImplType} inner) {{");
                methodBuilder.AppendLine($"{this.JvaClassName}({managerTypeName} manager) {{");
                methodBuilder.AppendLine($"    super(manager.inner().{this.Interface.InnerMethodGroupAccessorName}());"); // WrapperImpl(inner)
                methodBuilder.AppendLine($"    this.manager = manager;");
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
        }

        private string DefineMethodImplementation
        {
            get
            {
                if (this.SupportsCreating)
                {
                    StringBuilder methodBuilder = new StringBuilder();
                    methodBuilder.AppendLine("@Override");
                    methodBuilder.AppendLine($"public {this.fluentModelImpl.JvaClassName} define(String name) {{");
                    methodBuilder.AppendLine($"    return {this.fluentModelImpl.CtrInvocationFromWrapNewInnerModel}");

                    methodBuilder.AppendLine($"}}");
                    return methodBuilder.ToString();
                }
                else
                {
                    return String.Empty;
                }
            }
        }

        private string WrapModelImplementation
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

        private string ListByImmediateParentMethodImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                if (this.Interface.ResourceListingDescription.SupportsListByImmediateParent)
                {
                    FluentMethod method = this.Interface.ResourceListingDescription.ListByImmediateParentMethod;
                    if (!method.InnerMethod.IsPagingOperation)
                    {
                        FluentModel returnModel = method.ReturnModel;
                        //
                        string methodName = $"listBy{this.Interface.ParentFluentMethodGroup.LocalSingularNameInPascalCase}Async";
                        string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;

                        methodBuilder.AppendLine($"@Override");
                        methodBuilder.AppendLine($"public Observable<{this.fluentModelImpl.Interface.JavaInterfaceName}> {methodName}({parameterDecl}) {{");
                        methodBuilder.AppendLine($"    {this.Interface.InnerMethodGroupTypeName} client = this.inner();");
                        methodBuilder.AppendLine($"    return client.{method.Name}Async({InnerMethodInvocationParameter(method.InnerMethod)})");
                        if (method.InnerMethod.SimulateAsPagingOperation)
                        {
                            // TODO: handle when return types are diff.
                            //
                            methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{returnModel.InnerModel.ClassName}>, Observable<{returnModel.InnerModel.ClassName}>>() {{");
                            methodBuilder.AppendLine($"        @Override");
                            methodBuilder.AppendLine($"        public Observable<{returnModel.InnerModel.ClassName}> call(Page<{returnModel.InnerModel.ClassName}> innerPage) {{");
                            methodBuilder.AppendLine($"            return Observable.from(innerPage.items());");
                            methodBuilder.AppendLine($"        }}");
                            methodBuilder.AppendLine($"    }})");
                        }
                        else if (method.InnerMethod.ReturnTypeResponseName.StartsWith("List"))
                        {
                            methodBuilder.AppendLine($"    .flatMap(new Func1<List<{returnModel.InnerModel.ClassName}>, Observable<{returnModel.InnerModel.ClassName}>>() {{");
                            methodBuilder.AppendLine($"        @Override");
                            methodBuilder.AppendLine($"        public Observable<{returnModel.InnerModel.ClassName}> call(List<{returnModel.InnerModel.ClassName}> innerList) {{");
                            methodBuilder.AppendLine($"            return Observable.from(innerList);");
                            methodBuilder.AppendLine($"        }}");
                            methodBuilder.AppendLine($"    }})");
                        }
                        methodBuilder.AppendLine($"    .map(new Func1<{this.fluentModelImpl.Interface.InnerModel.ClassName}, {this.fluentModelImpl.Interface.JavaInterfaceName}>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public {this.fluentModelImpl.Interface.JavaInterfaceName} call({this.fluentModelImpl.Interface.InnerModel.ClassName} inner) {{");
                        methodBuilder.AppendLine($"            return wrapModel(inner);");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }});");
                        methodBuilder.AppendLine($"}}");
                    }
                    else
                    {
                        string nextPageMethodName = $"listBy{this.Interface.ParentFluentMethodGroup.LocalSingularNameInPascalCase}NextInnerPageAsync";

                        methodBuilder.AppendLine($"private Observable<Page<{this.fluentModelImpl.Interface.InnerModel.ClassName}>> {nextPageMethodName}(String nextLink) {{");
                        methodBuilder.AppendLine($"    if (nextLink == null) {{");
                        methodBuilder.AppendLine($"        Observable.empty();");
                        methodBuilder.AppendLine($"    }}");
                        methodBuilder.AppendLine($"    {this.Interface.InnerMethodGroupTypeName} client = this.inner();");
                        methodBuilder.AppendLine($"    return client.{method.Name}NextAsync(nextLink)");
                        methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{this.fluentModelImpl.Interface.InnerModel.ClassName}>, Observable<Page<{this.fluentModelImpl.Interface.InnerModel.ClassName}>>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Observable<Page<{this.fluentModelImpl.Interface.InnerModel.ClassName}>> call(Page<{this.fluentModelImpl.Interface.InnerModel.ClassName}> page) {{");
                        methodBuilder.AppendLine($"            return Observable.just(page).concatWith({nextPageMethodName}(page.nextPageLink()));");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }});");
                        methodBuilder.AppendLine($"}}");

                        var methodName = $"listBy{this.Interface.ParentFluentMethodGroup.LocalSingularNameInPascalCase}Async";
                        string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;

                        methodBuilder.AppendLine($"@Override");
                        methodBuilder.AppendLine($"public Observable<{this.fluentModelImpl.Interface.JavaInterfaceName}> {methodName}({parameterDecl}) {{");
                        methodBuilder.AppendLine($"    {this.Interface.InnerMethodGroupTypeName} client = this.inner();");
                        methodBuilder.AppendLine($"    return client.{method.Name}Async({InnerMethodInvocationParameter(method.InnerMethod)})");
                        methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{this.fluentModelImpl.Interface.InnerModel.ClassName}>, Observable<Page<{this.fluentModelImpl.Interface.InnerModel.ClassName}>>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Observable<Page<{this.fluentModelImpl.Interface.InnerModel.ClassName}>> call(Page<{this.fluentModelImpl.Interface.InnerModel.ClassName}> page) {{");
                        methodBuilder.AppendLine($"            return {nextPageMethodName}(page.nextPageLink());");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }})");
                        methodBuilder.AppendLine($"    .flatMapIterable(new Func1<Page<{this.fluentModelImpl.Interface.InnerModel.ClassName}>, Iterable<{this.fluentModelImpl.Interface.InnerModel.ClassName}>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Iterable<{this.fluentModelImpl.Interface.InnerModel.ClassName}> call(Page<{this.fluentModelImpl.Interface.InnerModel.ClassName}> page) {{");
                        methodBuilder.AppendLine($"            return page.items();");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"   }})");
                        methodBuilder.AppendLine($"    .map(new Func1<{this.fluentModelImpl.Interface.InnerModel.ClassName}, {this.fluentModelImpl.Interface.JavaInterfaceName}>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public {this.fluentModelImpl.Interface.JavaInterfaceName} call({this.fluentModelImpl.Interface.InnerModel.ClassName} inner) {{");
                        methodBuilder.AppendLine($"            return wrapModel(inner);");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"   }});");
                        methodBuilder.AppendLine($"}}");
                    }
                }
                return methodBuilder.ToString();
            }
        }


        private string GetByImmediateParentMethodImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                if (this.Interface.ResourceGetDescription.SupportsGetByImmediateParent)
                {
                    FluentMethod method = this.Interface.ResourceGetDescription.GetByImmediateParentMethod;
                    FluentModel returnModel = method.ReturnModel;
                    //
                    string methodName = $"getBy{this.Interface.ParentFluentMethodGroup.LocalSingularNameInPascalCase}Async";
                    string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;

                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Observable<{this.fluentModelImpl.Interface.JavaInterfaceName}> {methodName}({parameterDecl}) {{");
                    methodBuilder.AppendLine($"    {this.Interface.InnerMethodGroupTypeName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{method.Name}Async({InnerMethodInvocationParameter(method.InnerMethod)})");
                    methodBuilder.AppendLine($"    .map(new Func1<{this.fluentModelImpl.Interface.InnerModel.ClassName}, {this.fluentModelImpl.Interface.JavaInterfaceName}>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public {this.fluentModelImpl.Interface.JavaInterfaceName} call({this.fluentModelImpl.Interface.InnerModel.ClassName} inner) {{");
                    methodBuilder.AppendLine($"            return wrapModel(inner);");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"   }});");
                    methodBuilder.AppendLine($"}}");
                }
                return methodBuilder.ToString();
            }
        }

        private string DeleteByImmediateParentMethodImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                if (this.Interface.ResourceDeleteDescription.SupportsDeleteByImmediateParent)
                {
                    FluentMethod method = this.Interface.ResourceDeleteDescription.DeleteByImmediateParentMethod;
                    FluentModel returnModel = method.ReturnModel;
                    //
                    string methodName = $"deleteBy{ this.Interface.ParentFluentMethodGroup.LocalSingularNameInPascalCase}Async";
                    string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;

                    methodBuilder.AppendLine("@Override");
                    methodBuilder.AppendLine($"public Completable {methodName}({parameterDecl}) {{");
                    methodBuilder.AppendLine($"    {this.Interface.InnerMethodGroupTypeName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{method.Name}Async({InnerMethodInvocationParameter(method.InnerMethod)}).toCompletable();");
                    methodBuilder.AppendLine($"}}");
                }
                return methodBuilder.ToString();
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
