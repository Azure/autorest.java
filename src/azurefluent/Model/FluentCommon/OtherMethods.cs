// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class OtherMethods : List<FluentMethod>
    {
        private readonly FluentMethodGroup fluentMethodGroup;
        private readonly string package = Settings.Instance.Namespace.ToLower();

        public OtherMethods(FluentMethodGroup fluentMethodGroup, HashSet<string> standardMethods)
        {
            this.fluentMethodGroup = fluentMethodGroup;
            this.AddRange(this.fluentMethodGroup.InnerMethods
                .Where(im => !standardMethods.Contains(im.Name.ToLowerInvariant()))
                .Select(im => new FluentMethod(false, im, this.fluentMethodGroup))
                .ToList());
        }

        public HashSet<string> ImportsForInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.Any(m => m.InnerMethod.HttpMethod == HttpMethod.Delete))
                {
                    imports.Add("rx.Completable");
                }
                if (this.OtherFluentModels.Where(m => m is PrimtiveFluentModel).Any())
                {
                    imports.Add("rx.Completable");
                }
                if (this.OtherFluentModels.Where(m => !(m is PrimtiveFluentModel)).Any())
                {
                    imports.Add("rx.Observable");
                }
                return imports;
            }
        }

        public HashSet<string> ImportsForImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.OtherFluentModels.Where(model => model is PrimtiveFluentModel).Any())
                {
                    imports.Add("rx.Completable");
                }
                if (this.OtherFluentModels.Where(model => !(model is PrimtiveFluentModel)).Any())
                {
                    imports.Add("rx.functions.Func1");
                    imports.Add("rx.Observable");
                }
                if (this.Any(method => method.InnerMethod.IsPagingOperation || method.InnerMethod.SimulateAsPagingOperation))
                {
                    imports.Add("rx.Observable");
                    imports.Add("com.microsoft.azure.Page");
                    imports.Add("rx.functions.Func1");
                }

                if (this.Any(method => !method.InnerMethod.IsPagingOperation && !method.InnerMethod.SimulateAsPagingOperation && method.InnerMethod.ReturnTypeResponseName.StartsWith("List<")))
                {
                    imports.Add("rx.Observable");
                    imports.Add("java.util.List");
                    imports.Add("rx.functions.Func1");
                }
                this.OtherFluentModels.ForEach(model =>
                {
                    if (!(model is PrimtiveFluentModel))
                    {
                        imports.Add($"{this.package}.{model.JavaInterfaceName}");
                    }
                });
                return imports;
            }
        }

        public IEnumerable<string> MethodsImplementation
        {
            get
            {
                string innerClientName = this.fluentMethodGroup.InnerMethodGroupTypeName;
                StringBuilder methodsBuilder = new StringBuilder();
                foreach (FluentMethod otherMethod in this)
                {
                    methodsBuilder.Clear();
                    if (otherMethod.InnerMethod.HttpMethod == AutoRest.Core.Model.HttpMethod.Delete)
                    {
                        methodsBuilder.AppendLine("@Override");
                        methodsBuilder.AppendLine($"public Completable {otherMethod.Name}Async({otherMethod.InnerMethod.MethodRequiredParameterDeclaration}) {{");
                        methodsBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                        methodsBuilder.AppendLine($"    return client.{otherMethod.Name}Async({InnerMethodInvocationParameter(otherMethod.InnerMethod)}).toCompletable();");
                        methodsBuilder.AppendLine($"}}");
                    }
                    else
                    {
                        FluentModel returnModel = otherMethod.ReturnModel;
                        if (returnModel is PrimtiveFluentModel)
                        {
                            methodsBuilder.AppendLine($"@Override");
                            methodsBuilder.AppendLine($"public Completable { otherMethod.Name}Async({otherMethod.InnerMethod.MethodRequiredParameterDeclaration}) {{");
                            methodsBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                            methodsBuilder.AppendLine($"    return client.{otherMethod.Name}Async({InnerMethodInvocationParameter(otherMethod.InnerMethod)}).toCompletable();");
                            methodsBuilder.AppendLine($"}}");
                        }
                        else
                        {
                            if (!otherMethod.InnerMethod.IsPagingOperation)
                            {
                                string rxReturnType = $"Observable<{returnModel.JavaInterfaceName}>";
                                methodsBuilder.AppendLine("@Override");
                                methodsBuilder.AppendLine($"public {rxReturnType} {otherMethod.Name}Async({otherMethod.InnerMethod.MethodRequiredParameterDeclaration}) {{");
                                methodsBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                                methodsBuilder.AppendLine($"    return client.{otherMethod.Name}Async({InnerMethodInvocationParameter(otherMethod.InnerMethod)})");
                                if (otherMethod.InnerMethod.SimulateAsPagingOperation)
                                {
                                    methodsBuilder.AppendLine($"    .flatMap(new Func1<Page<{returnModel.InnerModel.ClassName}>, Observable<{returnModel.InnerModel.ClassName}>>() {{");
                                    methodsBuilder.AppendLine($"        @Override");
                                    methodsBuilder.AppendLine($"        public Observable<{returnModel.InnerModel.ClassName}> call(Page<{returnModel.InnerModel.ClassName}> innerPage) {{");
                                    methodsBuilder.AppendLine($"            return Observable.from(innerPage.items());");
                                    methodsBuilder.AppendLine($"        }}");
                                    methodsBuilder.AppendLine($"    }})");
                                }
                                else if (otherMethod.InnerMethod.ReturnTypeResponseName.StartsWith("List<"))
                                {
                                    methodsBuilder.AppendLine($"    .flatMap(new Func1<List<{returnModel.InnerModel.ClassName}>, Observable<{returnModel.InnerModel.ClassName}>>() {{");
                                    methodsBuilder.AppendLine($"        @Override");
                                    methodsBuilder.AppendLine($"        public Observable<{returnModel.InnerModel.ClassName}> call(List<{returnModel.InnerModel.ClassName}> innerList) {{");
                                    methodsBuilder.AppendLine($"            return Observable.from(innerList);");
                                    methodsBuilder.AppendLine($"        }}");
                                    methodsBuilder.AppendLine($"    }})");
                                }
                                methodsBuilder.AppendLine($"    .map(new Func1<{returnModel.InnerModel.ClassName}, {returnModel.JavaInterfaceName}>() {{");
                                methodsBuilder.AppendLine($"        @Override");
                                methodsBuilder.AppendLine($"        public {returnModel.JavaInterfaceName} call({returnModel.InnerModel.ClassName} inner) {{");
                                methodsBuilder.AppendLine($"            return new {returnModel.JavaInterfaceName}Impl(inner, manager());");
                                methodsBuilder.AppendLine($"        }}");
                                methodsBuilder.AppendLine($"    }});");
                                methodsBuilder.AppendLine($"}}");
                            }
                            else
                            {
                                string nextPageMethodName = $"{otherMethod.Name}NextInnerPageAsync";
                                string rxPagedReturnType = $"Observable<Page<{returnModel.InnerModel.ClassName}>>";

                                methodsBuilder.AppendLine($"private {rxPagedReturnType} {nextPageMethodName}(String nextLink) {{");
                                methodsBuilder.AppendLine($"    if (nextLink == null) {{");
                                methodsBuilder.AppendLine($"        Observable.empty();");
                                methodsBuilder.AppendLine($"    }}");
                                methodsBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                                methodsBuilder.AppendLine($"    return client.{otherMethod.Name}NextAsync(nextLink)");
                                methodsBuilder.AppendLine($"    .flatMap(new Func1<Page<{returnModel.InnerModel.ClassName}>, Observable<Page<{returnModel.InnerModel.ClassName}>>>() {{");
                                methodsBuilder.AppendLine($"        @Override");
                                methodsBuilder.AppendLine($"        public Observable<Page<{returnModel.InnerModel.ClassName}>> call(Page<{returnModel.InnerModel.ClassName}> page) {{");
                                methodsBuilder.AppendLine($"            return Observable.just(page).concatWith({nextPageMethodName}(page.nextPageLink()));");
                                methodsBuilder.AppendLine($"        }}");
                                methodsBuilder.AppendLine($"    }});");
                                methodsBuilder.AppendLine($"}}");

                                string rxReturnType = $"Observable<{returnModel.JavaInterfaceName}>";
                                methodsBuilder.AppendLine($"@Override");
                                methodsBuilder.AppendLine($"public {rxReturnType} {otherMethod.Name}Async({otherMethod.InnerMethod.MethodRequiredParameterDeclaration}) {{");
                                methodsBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                                methodsBuilder.AppendLine($"    return client.{otherMethod.Name}Async({InnerMethodInvocationParameter(otherMethod.InnerMethod)})");
                                methodsBuilder.AppendLine($"    .flatMap(new Func1<Page<{returnModel.InnerModel.ClassName}>, Observable<Page<{returnModel.InnerModel.ClassName}>>>() {{");
                                methodsBuilder.AppendLine($"        @Override");
                                methodsBuilder.AppendLine($"        public Observable<Page<{returnModel.InnerModel.ClassName}>> call(Page<{returnModel.InnerModel.ClassName}> page) {{");
                                methodsBuilder.AppendLine($"            return {nextPageMethodName}(page.nextPageLink());");
                                methodsBuilder.AppendLine($"        }}");
                                methodsBuilder.AppendLine($"    }})");
                                methodsBuilder.AppendLine($"    .flatMapIterable(new Func1<Page<{returnModel.InnerModel.ClassName}>, Iterable<{returnModel.InnerModel.ClassName}>>() {{");
                                methodsBuilder.AppendLine($"        @Override");
                                methodsBuilder.AppendLine($"        public Iterable<{returnModel.InnerModel.ClassName}> call(Page<{returnModel.InnerModel.ClassName}> page) {{");
                                methodsBuilder.AppendLine($"            return page.items();");
                                methodsBuilder.AppendLine($"        }}");
                                methodsBuilder.AppendLine($"   }})");
                                methodsBuilder.AppendLine($"    .map(new Func1<{returnModel.InnerModel.ClassName}, {returnModel.JavaInterfaceName}>() {{");
                                methodsBuilder.AppendLine($"        @Override");
                                methodsBuilder.AppendLine($"        public {returnModel.JavaInterfaceName} call({returnModel.InnerModel.ClassName} inner) {{");
                                methodsBuilder.AppendLine($"            return new {returnModel.JavaInterfaceName}Impl(inner, manager());");
                                methodsBuilder.AppendLine($"        }}");
                                methodsBuilder.AppendLine($"   }});");
                                methodsBuilder.AppendLine($"}}");
                            }
                        }
                    }
                    yield return methodsBuilder.ToString();
                }
            }
        }

        public IEnumerable<FluentModel> OtherFluentModels
        {
            get
            {
                return this.Select(om => om.ReturnModel);
            }
        }

        private static string InnerMethodInvocationParameter(MethodJvaf innerMethod)
        {
            List<string> invoke = new List<string>();
            foreach (var parameter in innerMethod.LocalParameters.Where(p => !p.IsConstant && p.IsRequired))
            {
                invoke.Add(parameter.Name);
            }

            return string.Join(", ", invoke);
        }
    }
}
