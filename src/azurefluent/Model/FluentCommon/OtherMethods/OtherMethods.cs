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
    public class OtherMethods : List<OtherMethod>, IOtherMethods
    {
        private readonly FluentMethodGroup fluentMethodGroup;
        private readonly string package = Settings.Instance.Namespace.ToLower();

        public OtherMethods(FluentMethodGroup fluentMethodGroup)
        {
            StandardMethodsInfo standardMethods = fluentMethodGroup.StandardMethodsInfo();
            //
            this.fluentMethodGroup = fluentMethodGroup;
            this.AddRange(this.fluentMethodGroup.InnerMethods
                .Where(innerMethod => !standardMethods.IsStandardInnerMethod(innerMethod) && !standardMethods.IsConfictWithStandardFluentMethod(innerMethod))
                .Select(innerMethod => new OtherMethod(innerMethod, this.fluentMethodGroup))
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
                if (this.OtherFluentModels.Where(m => m is PrimitiveModel).Any())
                {
                    imports.Add("rx.Completable");
                }
                if (this.OtherFluentModels.Where(m => !(m is PrimitiveModel)).Any())
                {
                    imports.Add("rx.Observable");
                }
                this.OtherFluentModels.ForEach(model =>
                {
                    if (model is NonWrappableModel nonWrappableModel)
                    {
                        imports.AddRange(nonWrappableModel.ImportsForInterface);
                    }
                });
                //
                this.ForEach(method =>
                {
                    foreach (var parameter in method.InnerMethod.LocalParameters.Where(p => !p.IsConstant && p.IsRequired))
                    {
                        foreach (string import in parameter.InterfaceImports)
                        {
                            if (import.StartsWith(this.package))
                            {
                                if (import.Contains(".implementation."))
                                {
                                    imports.Add(import);
                                }
                            }
                            else
                            {
                                imports.Add(import);
                            }
                        }
                    }
                });
                return imports;
            }
        }

        public HashSet<string> ImportsForImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.OtherFluentModels.Where(model => model is PrimitiveModel).Any())
                {
                    imports.Add("rx.Completable");
                }
                if (this.OtherFluentModels.Where(model => !(model is PrimitiveModel)).Any())
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
                    if (model is WrappableFluentModel wrappableFluentModel)
                    {
                        imports.Add($"{this.package}.{wrappableFluentModel.JavaInterfaceName}");
                    }
                    else if (model is NonWrappableModel nonWrappableModel)
                    {
                        imports.AddRange(nonWrappableModel.ImportsForImpl);
                    }
                });
                //
                this.ForEach(method =>
                {
                    foreach (var parameter in method.InnerMethod.LocalParameters.Where(p => !p.IsConstant && p.IsRequired))
                    {
                        foreach (string import in parameter.InterfaceImports)
                        {
                            if (import.StartsWith(this.package))
                            {
                                if (!import.Contains(".implementation."))
                                {
                                    imports.Add(import);
                                }
                            }
                            else
                            {
                                imports.Add(import);
                            }
                        }
                    }
                });
                return imports;
            }
        }

        public IEnumerable<string> MethodDecls
        {
            get
            {
                return FilteredMethodDecls(StandardMethodsInfo.Empty);
            }
        }

        public IEnumerable<string> MethodImpls
        {
            get
            {
                return FilteredMethodImpls(StandardMethodsInfo.Empty);
            }
        }

        public IEnumerable<IModel> OtherFluentModels
        {
            get
            {
                return this.Select(om => om.ReturnModel);
            }
        }

        private IEnumerable<string> FilteredMethodDecls(StandardMethodsInfo standardMethodsInfo)
        {
            IEnumerable<OtherMethod> otherMethods = this
                .Where(o => !standardMethodsInfo.IsStandardInnerMethod(o.InnerMethod)
                                && !standardMethodsInfo.IsConfictWithStandardFluentMethod(o.InnerMethod));
            //
            StringBuilder methodsBuilder = new StringBuilder();
            foreach (OtherMethod otherMethod in otherMethods)
            {
                MethodJvaf innerMethod = otherMethod.InnerMethod;
                string methodName = $"{innerMethod.Name.Value}Async";
                //
                string rxReturnType;
                if (otherMethod.ReturnModel is WrappableFluentModel wrappableFluentModel)
                {
                    rxReturnType = $"Observable<{wrappableFluentModel.JavaInterfaceName}>";
                }
                else if (otherMethod.ReturnModel is NonWrappableModel nonWrappableModel)
                {
                    rxReturnType = $"Observable<{nonWrappableModel.RawModelName}>";
                }
                else
                {
                    // otherMethod.ReturnModel is PrimitiveModel
                    rxReturnType = "Completable";
                }
                //
                methodsBuilder.Clear();
                //
                methodsBuilder.AppendLine($"/**");
                if (!string.IsNullOrEmpty(innerMethod.Summary))
                {
                    methodsBuilder.AppendLine($" * {innerMethod.Summary.EscapeXmlComment().Period()}");
                }
                if (!string.IsNullOrEmpty(innerMethod.Description))
                {
                    methodsBuilder.AppendLine($" * {innerMethod.Description.EscapeXmlComment().Period()}");
                }
                methodsBuilder.AppendLine($" *");
                foreach (var param in innerMethod.LocalParameters.Where(p => !p.IsConstant && p.IsRequired))
                {
                    methodsBuilder.AppendLine($" * @param {param.Name} {param.Documentation.Else("the " + param.ModelType.Name + " value").EscapeXmlComment().Trim()}");
                }
                methodsBuilder.AppendLine($" * @throws IllegalArgumentException thrown if parameters fail the validation");
                methodsBuilder.AppendLine($" * @return the observable for the request");
                methodsBuilder.AppendLine($" */");
                if (innerMethod.HttpMethod == AutoRest.Core.Model.HttpMethod.Delete)
                {
                    methodsBuilder.AppendLine($"{rxReturnType} {methodName}({innerMethod.MethodRequiredParameterDeclaration});");
                }
                else if (otherMethod.ReturnModel is PrimitiveModel)
                {
                    methodsBuilder.AppendLine($"{rxReturnType} {methodName}({innerMethod.MethodRequiredParameterDeclaration});");
                }
                else
                {
                    methodsBuilder.AppendLine($"{rxReturnType} {methodName}({innerMethod.MethodRequiredParameterDeclaration});");
                }
                //
                yield return methodsBuilder.ToString();
            }
        }

        private IEnumerable<string> FilteredMethodImpls(StandardMethodsInfo standardMethodsInfo)
        {
            string innerClientName = this.fluentMethodGroup.InnerMethodGroupTypeName;
            //
            IEnumerable<OtherMethod> otherMethods = this
                .Where(o => !standardMethodsInfo.IsStandardInnerMethod(o.InnerMethod)
                    && !standardMethodsInfo.IsConfictWithStandardFluentMethod(o.InnerMethod));
            //
            StringBuilder methodsBuilder = new StringBuilder();
            foreach (OtherMethod otherMethod in otherMethods)
            {
                methodsBuilder.Clear();
                if (otherMethod.InnerMethod.HttpMethod == HttpMethod.Delete)
                {
                    methodsBuilder.AppendLine("@Override");
                    methodsBuilder.AppendLine($"public Completable {otherMethod.Name}Async({otherMethod.InnerMethodRequiredParameterDeclaration}) {{");
                    methodsBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                    methodsBuilder.AppendLine($"    return client.{otherMethod.Name}Async({otherMethod.InnerMethodInvocationParameters}).toCompletable();");
                    methodsBuilder.AppendLine($"}}");
                }
                else
                {
                    IModel returnModel = otherMethod.ReturnModel;
                    if (returnModel is PrimitiveModel)
                    {
                        methodsBuilder.AppendLine($"@Override");
                        methodsBuilder.AppendLine($"public Completable { otherMethod.Name}Async({otherMethod.InnerMethodRequiredParameterDeclaration}) {{");
                        methodsBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                        methodsBuilder.AppendLine($"    return client.{otherMethod.Name}Async({otherMethod.InnerMethodInvocationParameters}).toCompletable();");
                        methodsBuilder.AppendLine($"}}");
                    }
                    else
                    {
                        if (!otherMethod.InnerMethod.IsPagingOperation)
                        {
                            string rxReturnType;
                            string returnModelClassName;
                            string mapForWrappableModel;
                            //
                            if (returnModel is WrappableFluentModel wrappableReturnModel)
                            {
                                returnModelClassName = wrappableReturnModel.InnerModel.ClassName;
                                string returnModelInterfaceName = wrappableReturnModel.JavaInterfaceName;
                                rxReturnType = $"Observable<{returnModelInterfaceName}>";
                                //
                                string ctrInvocationOfReturnModelClass = this.fluentMethodGroup
                                    .FluentMethodGroups
                                    .CtrToCreateModelFromExistingResource($"{wrappableReturnModel.JavaClassName}");
                                //
                                StringBuilder mapBuilder = new StringBuilder();
                                mapBuilder.AppendLine($"    .map(new Func1<{returnModelClassName}, {returnModelInterfaceName}>() {{");
                                mapBuilder.AppendLine($"        @Override");
                                mapBuilder.AppendLine($"        public {returnModelInterfaceName} call({returnModelClassName} inner) {{");
                                mapBuilder.AppendLine($"            return{ctrInvocationOfReturnModelClass}");
                                mapBuilder.AppendLine($"        }}");
                                mapBuilder.AppendLine($"    }});");
                                //
                                mapForWrappableModel = mapBuilder.ToString();
                            }
                            else if (returnModel is NonWrappableModel nonWrappableReturnModel)
                            {
                                returnModelClassName = nonWrappableReturnModel.RawModelName;
                                rxReturnType = $"Observable<{returnModelClassName}>";
                                mapForWrappableModel = null;
                            }
                            else
                            {
                                throw new NotImplementedException();
                            }
                            //
                            methodsBuilder.AppendLine("@Override");
                            methodsBuilder.AppendLine($"public {rxReturnType} {otherMethod.Name}Async({otherMethod.InnerMethodRequiredParameterDeclaration}) {{");
                            methodsBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                            methodsBuilder.AppendLine($"    return client.{otherMethod.Name}Async({otherMethod.InnerMethodInvocationParameters})");
                            if (otherMethod.InnerMethod.SimulateAsPagingOperation)
                            {
                                methodsBuilder.AppendLine($"    .flatMap(new Func1<Page<{returnModelClassName}>, Observable<{returnModelClassName}>>() {{");
                                methodsBuilder.AppendLine($"        @Override");
                                methodsBuilder.AppendLine($"        public Observable<{returnModelClassName}> call(Page<{returnModelClassName}> innerPage) {{");
                                methodsBuilder.AppendLine($"            return Observable.from(innerPage.items());");
                                methodsBuilder.AppendLine($"        }}");
                                methodsBuilder.AppendLine($"    }})");
                            }
                            else if (otherMethod.InnerMethod.ReturnTypeResponseName.StartsWith("List<"))
                            {
                                methodsBuilder.AppendLine($"    .flatMap(new Func1<List<{returnModelClassName}>, Observable<{returnModelClassName}>>() {{");
                                methodsBuilder.AppendLine($"        @Override");
                                methodsBuilder.AppendLine($"        public Observable<{returnModelClassName}> call(List<{returnModelClassName}> innerList) {{");
                                methodsBuilder.AppendLine($"            return Observable.from(innerList);");
                                methodsBuilder.AppendLine($"        }}");
                                methodsBuilder.Append($"    }})");
                            }
                            //
                            if (mapForWrappableModel != null)
                            {
                                methodsBuilder.AppendLine(mapForWrappableModel);
                            }
                            else
                            {
                                methodsBuilder.Append($";");
                            }
                            methodsBuilder.AppendLine($"}}");
                        }
                        else
                        {
                            string rxReturnType;
                            string returnModelClassName;
                            string mapForWrappableModel;
                            if (returnModel is WrappableFluentModel wrappableReturnModel)
                            {
                                returnModelClassName = wrappableReturnModel.InnerModel.ClassName;
                                string returnModelInterfaceName = wrappableReturnModel.JavaInterfaceName;
                                rxReturnType = $"Observable<{returnModelInterfaceName}>";

                                //
                                string ctrInvocationOfReturnModelClass = this.fluentMethodGroup
                                    .FluentMethodGroups
                                    .CtrToCreateModelFromExistingResource($"{wrappableReturnModel.JavaClassName}");
                                //
                                StringBuilder mapBuilder = new StringBuilder();
                                mapBuilder.AppendLine($"    .map(new Func1<{returnModelClassName}, {returnModelInterfaceName}>() {{");
                                mapBuilder.AppendLine($"        @Override");
                                mapBuilder.AppendLine($"        public {returnModelInterfaceName} call({returnModelClassName} inner) {{");
                                mapBuilder.AppendLine($"            return{ctrInvocationOfReturnModelClass}");
                                mapBuilder.AppendLine($"        }}");
                                mapBuilder.AppendLine($"    }});");
                                //
                                mapForWrappableModel = mapBuilder.ToString();
                            }
                            else if (returnModel is NonWrappableModel nonWrappableReturnModel)
                            {
                                returnModelClassName = nonWrappableReturnModel.RawModel.ClassName;
                                rxReturnType = $"Observable<{returnModelClassName}>";
                                mapForWrappableModel = null;
                            }
                            else
                            {
                                throw new NotImplementedException();
                            }
                            //

                            methodsBuilder.AppendLine($"@Override");
                            methodsBuilder.AppendLine($"public {rxReturnType} {otherMethod.Name}Async({otherMethod.InnerMethodRequiredParameterDeclaration}) {{");
                            methodsBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                            methodsBuilder.AppendLine($"    return client.{otherMethod.Name}Async({otherMethod.InnerMethodInvocationParameters})");
                            methodsBuilder.AppendLine($"    .flatMapIterable(new Func1<Page<{returnModelClassName}>, Iterable<{returnModelClassName}>>() {{");
                            methodsBuilder.AppendLine($"        @Override");
                            methodsBuilder.AppendLine($"        public Iterable<{returnModelClassName}> call(Page<{returnModelClassName}> page) {{");
                            methodsBuilder.AppendLine($"            return page.items();");
                            methodsBuilder.AppendLine($"        }}");
                            methodsBuilder.Append($"    }})");
                            //
                            if (mapForWrappableModel != null)
                            {
                                methodsBuilder.AppendLine(mapForWrappableModel);
                            }
                            else
                            {
                                methodsBuilder.AppendLine($";");
                            }
                            methodsBuilder.AppendLine($"}}");
                        }
                    }
                }
                yield return methodsBuilder.ToString();
            }
        }
    }
}
