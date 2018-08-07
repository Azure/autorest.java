// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type representing get description of standard model (of a method group) under parent scope.
    /// </summary>
    public class GetByImmediateParentDescription
    {
        protected readonly string package = Settings.Instance.Namespace.ToLower();

        protected readonly IFluentMethodGroup FluentMethodGroup;
        private readonly IGetInnerAsyncFuncFactory getInnerAsyncFuncFactory;

        public GetByImmediateParentDescription(IFluentMethodGroup fluentMethodGroup, IGetInnerAsyncFuncFactory getInnerAsyncFuncFactory)
        {
            this.FluentMethodGroup = fluentMethodGroup;
            this.getInnerAsyncFuncFactory = getInnerAsyncFuncFactory;
        }

        private bool supportsGet;
        public bool SupportsGet
        {
            get
            {
                this.Process();
                return this.supportsGet;
            }
        }

        private StandardFluentMethod getMethod;
        public StandardFluentMethod GetMethod
        {
            get
            {
                this.Process();
                return this.getMethod;
            }
        }

        public HashSet<string> MethodGroupInterfaceExtendsFrom
        {
            get
            {
                return Utils.EmptyStringSet;
            }
        }

        public HashSet<string> ImportsForMethodGroupInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsGet)
                {
                    imports.Add("rx.Observable");
                }
                return imports;
            }
        }

        public HashSet<string> ImportsForMethodGroupImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsGet)
                {
                    imports.Add("rx.Observable");
                    imports.Add("rx.functions.Func1");
                }
                return imports;
            }
        }

        public HashSet<string> ImportsForGeneralizedInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsGet)
                {
                    imports.Add("rx.Observable");
                }
                return imports;
            }
        }

        public HashSet<string> ImportsForGeneralizedImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsGet)
                {
                    imports.Add("rx.Observable");
                    imports.Add("rx.functions.Func1");
                    imports.Add($"{package}.{this.FluentMethodGroup.StandardFluentModel.JavaInterfaceName}");
                }
                return imports;
            }
        }

        public string GeneralizedMethodDecl
        {
            get
            {
                if (this.SupportsGet)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    var method = this.GetMethod;
                    string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;
                    //
                    var innerMethod = method.InnerMethod;
                    //
                    StringBuilder methodsBuilder = new StringBuilder();
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
                    methodsBuilder.AppendLine($"Observable<{modelInterfaceName}> {method.Name}Async({parameterDecl});");
                    return methodsBuilder.ToString();
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        public string GeneralizedMethodImpl
        {
            get
            {
                if (this.SupportsGet)
                {
                    return GetRxAsyncMethodImplementation(true);
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        public HashSet<string> ImportsForMethodGroupWithLocalGetMethodImpl
        {
            get
            {
                return Utils.EmptyStringSet;
            }
        }

        public IEnumerable<string> GetSyncAsyncMethodImplementations
        {
            get
            {
                if (this.SupportsGet)
                {
                    string methodImpl = this.GetSyncImplementation;
                    if (!string.IsNullOrEmpty(methodImpl))
                    {
                        yield return methodImpl;
                    }
                    methodImpl = this.GetRxAsyncMethodImplementation(false);
                    if (!string.IsNullOrEmpty(methodImpl))
                    {
                        yield return methodImpl;
                    }
                    methodImpl = this.GetFutureAsyncMethodImplementation;
                    if (!string.IsNullOrEmpty(methodImpl))
                    {
                        yield return methodImpl;
                    }
                }
                else
                {
                    yield break;
                }
            }
        }

        public string GetRxAsyncMethodImplementation(bool isGeneralized)
        {
            if (this.SupportsGet)
            {
                StringBuilder methodBuilder = new StringBuilder();
                //
                StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                string modelInterfaceName = standardModel.JavaInterfaceName;
                string modelInnerName = standardModel.RawModelName;
                string innerClientName = this.FluentMethodGroup.InnerMethodGroupTypeName;
                //
                var method = this.GetMethod;
                string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;
                string wrapExistingMethodName;
                if (isGeneralized)
                {
                    wrapExistingMethodName = standardModel.WrapExistingModelFunc.GeneralizedMethodName;
                }
                else
                {
                    wrapExistingMethodName = standardModel.WrapExistingModelFunc.MethodName;
                }
                methodBuilder.AppendLine($"@Override");
                methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> {method.Name}Async({parameterDecl}) {{");
                methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                methodBuilder.AppendLine($"    return client.{method.Name}Async({method.InnerMethodInvocationParameters})");
                methodBuilder.AppendLine($"    .map(new Func1<{modelInnerName}, {modelInterfaceName}>() {{");
                methodBuilder.AppendLine($"        @Override");
                methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} inner) {{");
                methodBuilder.AppendLine($"            return {wrapExistingMethodName}(inner);");
                methodBuilder.AppendLine($"        }}");
                methodBuilder.AppendLine($"   }});");
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
            else
            {
                return string.Empty;
            }
        }

        private bool isProcessed;
        private void Process()
        {
            if (this.isProcessed)
            {
                return;
            }
            else
            {
                this.isProcessed = true;
                this.CheckGetByImmediateParentSupport();
            }
        }

        private void CheckGetByImmediateParentSupport()
        {
            if (this.FluentMethodGroup.Level > 0)
            {
                foreach (MethodJvaf innerMethod in FluentMethodGroup.InnerMethods.Where(method => method.HttpMethod == HttpMethod.Get))
                {
                    IFluentMethodGroup parentMethodGroup = this.FluentMethodGroup.ParentFluentMethodGroup;
                    if (parentMethodGroup != null)
                    {
                        bool isResponseCompositeType = innerMethod.ReturnTypeJva.BodyClientType is CompositeTypeJv;
                        if (!isResponseCompositeType)
                        {
                            // In order to be able to map response to standard model T where T is class/interface type
                            // it need to be composite type. If the return type is primitive type (e.g. void), sequence type
                            // dict type then mapping cannot be done. Skip get methods returning such types they will be appear
                            // as other methods
                            continue;
                        }
                        else
                        {
                            var armUri = new ARMUri(innerMethod);
                            Segment lastSegment = armUri.LastOrDefault();
                            if (lastSegment != null && lastSegment is ParentSegment)
                            {
                                ParentSegment resourceSegment = (ParentSegment)lastSegment;
                                if (resourceSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase))
                                {
                                    Segment secondLastSegment = armUri.SkipLast(1).LastOrDefault();
                                    if (secondLastSegment != null && secondLastSegment is ParentSegment)
                                    {
                                        ParentSegment parentSegment = (ParentSegment)secondLastSegment;
                                        if (parentSegment.Name.EqualsIgnoreCase(parentMethodGroup.LocalNameInPascalCase))
                                        {
                                            if (StandardFluentMethod.CanWrap(innerMethod))
                                            {
                                                this.supportsGet = true;
                                                this.getMethod = new StandardFluentMethod(innerMethod, this.FluentMethodGroup);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                this.supportsGet = false;
                this.getMethod = null;
            }
        }

        private string GetSyncImplementation
        {
            get
            {
                // No synchronous version of get by immediate parent is exposed
                //
                return string.Empty;
            }
        }

        private string GetFutureAsyncMethodImplementation
        {
            get
            {
                // Np async 'Future' version of get by immediate parent is exposed
                //
                return string.Empty;
            }
        }
    }
}
