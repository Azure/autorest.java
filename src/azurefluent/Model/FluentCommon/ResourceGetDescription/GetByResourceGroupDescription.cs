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
    /// Type representing get description of standard model (of a method group) under resource group scope.
    /// </summary>
    public class GetByResourceGroupDescription
    {
        protected readonly string package = Settings.Instance.Namespace.ToLower();

        protected readonly ISegmentFluentMethodGroup FluentMethodGroup;
        private readonly IGetInnerAsyncFuncFactory getInnerAsyncFuncFactory;

        public GetByResourceGroupDescription(ISegmentFluentMethodGroup fluentMethodGroup, IGetInnerAsyncFuncFactory getInnerAsyncFuncFactory)
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
                HashSet<string> extendsFrom = new HashSet<string>();
                if (this.SupportsGet)
                {
                    extendsFrom.Add($"SupportsGettingByResourceGroup<{this.GetMethod.ReturnModel.JavaInterfaceName}>");
                }
                return extendsFrom;
            }
        }

        public HashSet<string> ImportsForMethodGroupInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsGet)
                {
                    imports.Add("com.microsoft.azure.arm.resources.collection.SupportsGettingByResourceGroup");
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
                    //
                    var method = this.GetMethod;
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
                    methodsBuilder.AppendLine($" * @param resourceGroupName resource group name");
                    methodsBuilder.AppendLine($" * @param name resource name");
                    methodsBuilder.AppendLine($" * @throws IllegalArgumentException thrown if parameters fail the validation");
                    methodsBuilder.AppendLine($" * @return the observable for the request");
                    methodsBuilder.AppendLine($" */");
                    methodsBuilder.AppendLine($"Observable<{modelInterfaceName}> {method.Name}Async(String resourceGroupName, String name);");
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
                return this.GetRxAsyncMethodImplementation(true);
            }
        }

        public HashSet<string> ImportsForMethodGroupWithLocalGetMethodImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsGet)
                {
                    // Imports needed when collection supports listByResourceGroup but it is not inheriting from GrouapbleResourcesImpl
                    // hence not getting free implementation for listByResourceGroup methods.
                    //
                    imports.Add("rx.Observable");
                    imports.Add("com.microsoft.rest.ServiceFuture");
                    imports.Add("com.microsoft.rest.ServiceCallback");
                    imports.Add("rx.functions.Func1");
                }
                return imports;
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
                this.CheckGetByResourceGroupSupport();
            }
        }

        private void CheckGetByResourceGroupSupport()
        {
            if (this.FluentMethodGroup.Level == 0)
            {
                foreach (MethodJvaf innerMethod in FluentMethodGroup.InnerMethods.Where(method => method.HttpMethod == HttpMethod.Get))
                {
                    bool isResponseCompositeType = innerMethod.ReturnTypeJva.BodyClientType is CompositeTypeJv;
                    if (!isResponseCompositeType)
                    {
                        // In order to be able to implement SupportsGetByResourceGroup<T> where T is class/interface type, 
                        // we should be able to map respone resource of get to T. If the return type is primitive type 
                        // (e.g. void), sequence type, dict type then mapping cannot be done. Skip get methods returning
                        // such types they will be appear as "OtherMethod"s.
                        continue;
                    }
                    else
                    {
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is ParentSegment)
                        {
                            ParentSegment resourceSegment = (ParentSegment)lastSegment;
                            var requiredParameters = Utils.RequiredParametersOfMethod(innerMethod);
                            if (resourceSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase) && requiredParameters.Count() == 2)
                            {
                                var resourceGroupSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("resourceGroups"));
                                if (resourceGroupSegment != null)
                                {
                                    bool hasResourceGroupParam = requiredParameters.Any(p => p.SerializedName.EqualsIgnoreCase(resourceGroupSegment.Parameter.SerializedName));
                                    bool hasResourceParm = requiredParameters.Any(p => p.SerializedName.EqualsIgnoreCase(resourceSegment.Parameter.SerializedName));
                                    if (hasResourceGroupParam && hasResourceParm)
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
                if (this.SupportsGet)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    // T getByResourceGroup(String resourceGroupName, String name)
                    //
                    StringBuilder methodBuilder = new StringBuilder();
                    //
                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public {modelInterfaceName} getByResourceGroup(String resourceGroupName, String name) {{");
                    methodBuilder.AppendLine($"    return getByResourceGroupAsync(resourceGroupName, name).toBlocking().last();");
                    methodBuilder.AppendLine($"}}");
                    //
                    return methodBuilder.ToString();
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        private string GetFutureAsyncMethodImplementation
        {
            get
            {
                if (this.SupportsGet)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    // ServiceFuture<EventSubscription> getByResourceGroupAsync(String resourceGroupName, String name, ServiceCallback<EventSubscription> callback)
                    //
                    StringBuilder methodBuilder = new StringBuilder();
                    //
                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public ServiceFuture<{modelInterfaceName}> getByResourceGroupAsync(String resourceGroupName, String name, ServiceCallback<{modelInterfaceName}> callback) {{");
                    methodBuilder.AppendLine($"    return ServiceFuture.fromBody(getByResourceGroupAsync(resourceGroupName, name), callback);");
                    methodBuilder.AppendLine($"}}");
                    //
                    return methodBuilder.ToString();
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        public string GetRxAsyncMethodImplementation(bool isGeneralized)
        {
            if (this.SupportsGet)
            {
                if (isGeneralized)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    string modelInnerName = standardModel.RawModelName;
                    //
                    var method = this.GetMethod;
                    //
                    StringBuilder methodBuilder = new StringBuilder();
                    //
                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> {method.Name}Async(String resourceGroupName, String name) {{");
                    methodBuilder.AppendLine($"    return this.{this.getInnerAsyncFuncFactory.GetFromResourceGroupAsyncFunc.GeneralizedMethodName}(resourceGroupName, name).map(new Func1<{modelInnerName}, {modelInterfaceName}> () {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} inner) {{");
                    methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.GeneralizedMethodName}(inner);");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }});");
                    methodBuilder.AppendLine($"}}");
                    //
                    return methodBuilder.ToString();
                }
                else
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    string modelInnerName = standardModel.RawModelName;
                    // Obs<T> getByResourceGroupAsync(String resourceGroupName, String name)
                    //
                    StringBuilder methodBuilder = new StringBuilder();
                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> getByResourceGroupAsync(String resourceGroupName, String name) {{");
                    methodBuilder.AppendLine($"    return this.{this.getInnerAsyncFuncFactory.GetFromResourceGroupAsyncFunc.MethodName}(resourceGroupName, name).map(new Func1<{modelInnerName}, {modelInterfaceName}> () {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} innerT) {{");
                    methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.MethodName}(innerT);");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }});");
                    methodBuilder.AppendLine($"}}");
                    //
                    return methodBuilder.ToString();
                }
            }
            else
            {
                return string.Empty;
            }
        }
    }
}
