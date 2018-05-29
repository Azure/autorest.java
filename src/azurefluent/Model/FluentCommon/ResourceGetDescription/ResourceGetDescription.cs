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
    /// An implementation of 'IResourceGetDescription' that describes retrieval of an Azure resource.
    /// </summary>
    public class ResourceGetDescription : IResourceGetDescription
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        private bool isProcessed;

        private bool supportsGetBySubscription;
        private bool supportsGetByResourceGroup;
        private bool supportsGetByImmediateParent;
        private bool supportsGetByParameterizedParent;
        private FluentMethod getBySubscriptionMethod;
        private FluentMethod getByResourceGroupMethod;
        private FluentMethod getByImmediateParentMethod;
        private FluentMethod getByParameterizedParentMethod;

        public ResourceGetDescription(IFluentMethodGroup fluentMethodGroup)
        {
            this.FluentMethodGroup = fluentMethodGroup;
        }

        public IFluentMethodGroup FluentMethodGroup { get; }

        public bool SupportsGetBySubscription
        {
            get
            {
                Process();
                return this.supportsGetBySubscription;
            }
        }

        public FluentMethod GetBySubscriptionMethod
        {
            get
            {
                Process();
                return this.getBySubscriptionMethod;
            }
        }

        public bool SupportsGetByResourceGroup
        {
            get
            {
                Process();
                return this.supportsGetByResourceGroup;
            }
        }

        public FluentMethod GetByResourceGroupMethod
        {
            get
            {
                Process();
                return this.getByResourceGroupMethod;
            }
        }

        public bool SupportsGetByImmediateParent
        {
            get
            {
                Process();
                return this.supportsGetByImmediateParent;
            }
        }

        public FluentMethod GetByImmediateParentMethod
        {
            get
            {
                Process();
                return this.getByImmediateParentMethod;
            }
        }

        public bool SupportsGetByParameterizedParent
        {
            get
            {
                Process();
                return this.supportsGetByParameterizedParent;
            }
        }

        public FluentMethod GetByParameterizedParentMethod
        {
            get
            {
                Process();
                return this.getByParameterizedParentMethod;
            }
        }

        public bool SupportsGetting
        {
            get
            {
                return this.SupportsGetBySubscription
                    || this.SupportsGetByResourceGroup
                    || this.SupportsGetByImmediateParent
                    || this.SupportsGetByParameterizedParent;
            }
        }

        private IGetInnerAsyncFuncFactory getInnerAsyncFuncFactory;
        public IGetInnerAsyncFuncFactory GetInnerAsyncFuncFactory
        {
            get
            {
                if (this.getInnerAsyncFuncFactory == null)
                {
                    this.getInnerAsyncFuncFactory = new GetInnerAsyncFuncFactory(this);
                }
                return this.getInnerAsyncFuncFactory;
            }
        }

        public HashSet<string> MethodGroupInterfaceExtendsFrom
        {
            get
            {
                HashSet<string> extendsFrom = new HashSet<string>();
                if (this.SupportsGetByResourceGroup)
                {
                    extendsFrom.Add($"SupportsGettingByResourceGroup<{this.GetByResourceGroupMethod.ReturnModel.JavaInterfaceName}>");
                }
                return extendsFrom;
            }
        }

        public HashSet<string> ImportsForMethodGroupInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsGetByResourceGroup)
                {
                    imports.Add("com.microsoft.azure.arm.resources.collection.SupportsGettingByResourceGroup");
                    imports.Add("rx.Observable");
                }
                else if (this.SupportsGetByImmediateParent)
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
                if (this.supportsGetByResourceGroup)
                {
                    imports.Add("rx.Observable");
                    imports.Add("rx.functions.Func1");
                }
                else if (this.SupportsGetByImmediateParent)
                {
                    imports.Add("rx.Observable");
                    imports.Add("rx.functions.Func1");
                }
                return imports;
            }
        }

        public HashSet<string> ImportsForMethodGroupWithLocalGetByResourceGroupImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.supportsGetByResourceGroup)
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

        #region ISupportsGeneralizedView

        public HashSet<string> ImportsForGeneralizedInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsGetByResourceGroup)
                {
                    imports.Add("rx.Observable");
                }
                else if (this.SupportsGetByImmediateParent)
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
                if (this.supportsGetByResourceGroup)
                {
                    imports.Add("rx.Observable");
                    imports.Add("rx.functions.Func1");
                    imports.Add($"{package}.{this.FluentMethodGroup.StandardFluentModel.JavaInterfaceName}");
                }
                else if (this.SupportsGetByImmediateParent)
                {
                    imports.Add("rx.Observable");
                    imports.Add("rx.functions.Func1");
                    imports.Add($"{package}.{this.FluentMethodGroup.StandardFluentModel.JavaInterfaceName}");
                }
                return imports;
            }
        }

        public IEnumerable<string> GeneralizedMethodDecls
        {
            get
            {
                if (this.SupportsGetByResourceGroup)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    //
                    var method = this.GetByResourceGroupMethod;
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
                    yield return methodsBuilder.ToString();
                }
                if (this.SupportsGetByImmediateParent)
                {
                    yield return GetByImmediateParentMethodDecl;
                }
            }
        }

        public IEnumerable<string> GeneralizedMethodImpls
        {
            get
            {
                if (this.SupportsGetByResourceGroup)
                {
                    yield return this.GetByResourceGroupRxAsyncMethodGeneralizedImplementation;
                }
                if (this.SupportsGetByImmediateParent)
                {
                    yield return this.GetByImmediateParentRxAsyncMethodImplementation(true);
                }
            }
        }

        public string GetByImmediateParentMethodDecl
        {
            get
            {
                if (this.SupportsGetByImmediateParent)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    var method = this.GetByImmediateParentMethod;
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


        private string GetByResourceGroupRxAsyncMethodGeneralizedImplementation
        {
            get
            {
                if (this.SupportsGetByResourceGroup)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    string modelInnerName = standardModel.InnerModelName;
                    //
                    var method = this.GetByResourceGroupMethod;
                    //
                    StringBuilder methodBuilder = new StringBuilder();
                    //
                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> {method.Name}Async(String resourceGroupName, String name) {{");
                    methodBuilder.AppendLine($"    return this.{this.GetInnerAsyncFuncFactory.GetFromResourceGroupAsyncFunc.GeneralizedMethodName}(resourceGroupName, name).map(new Func1<{modelInnerName}, {modelInterfaceName}> () {{");
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
                    return string.Empty;
                }
            }
        }

        public string GetByImmediateParentRxAsyncMethodImplementation(bool isGeneralized)
        {
            if (this.SupportsGetByImmediateParent)
            {
                StringBuilder methodBuilder = new StringBuilder();
                //
                StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                string modelInterfaceName = standardModel.JavaInterfaceName;
                string modelInnerName = standardModel.InnerModelName;
                string innerClientName = this.FluentMethodGroup.InnerMethodGroupTypeName;
                //
                var method = this.GetByImmediateParentMethod;
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

        #endregion

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
                this.CheckGetBySubscriptionSupport();
                this.CheckGetByParameterizedParentSupport();
                this.CheckGetByImmediateParentSupport();
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
                        // such types they will be appear as other methods
                        continue;
                    }
                    else
                    {
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is ParentSegment)
                        {
                            ParentSegment resourceSegment = (ParentSegment)lastSegment;
                            var requiredParameters = RequiredParametersOfMethod(innerMethod);
                            if (resourceSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase) && requiredParameters.Count() == 2)
                            {
                                var resourceGroupSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("resourceGroups"));
                                if (resourceGroupSegment != null)
                                {
                                    bool hasResourceGroupParam = requiredParameters.Any(p => p.SerializedName.EqualsIgnoreCase(resourceGroupSegment.Parameter.SerializedName));
                                    bool hasResourceParm = requiredParameters.Any(p => p.SerializedName.EqualsIgnoreCase(resourceSegment.Parameter.SerializedName));
                                    if (hasResourceGroupParam && hasResourceParm)
                                    {
                                        this.supportsGetByResourceGroup = true;
                                        this.getByResourceGroupMethod = new FluentMethod(true, innerMethod, this.FluentMethodGroup);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                this.supportsGetByResourceGroup = false;
                this.getByResourceGroupMethod = null;
            }
        }

        private void CheckGetBySubscriptionSupport()
        {
            if (this.FluentMethodGroup.Level == 0)
            {
                foreach (MethodJvaf innerMethod in FluentMethodGroup.InnerMethods.Where(method => method.HttpMethod == HttpMethod.Get))
                {
                    bool isResponseCompositeType = innerMethod.ReturnTypeJva.BodyClientType is CompositeTypeJv;
                    if (!isResponseCompositeType)
                    {
                        // In order to be able to implement SupportsGetBySubscription<T> where T is class/interface type, 
                        // we should be able to map respone resource of get to T. If the return type is primitive type 
                        // (e.g. void), sequence type, dict type then mapping cannot be done. Skip get methods returning
                        // such types they will be appear as other methods
                        continue;
                    }
                    else
                    {
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is ParentSegment)
                        {
                            ParentSegment resourceSegment = (ParentSegment)lastSegment;
                            var requiredParameters = RequiredParametersOfMethod(innerMethod);
                            if (resourceSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase) && requiredParameters.Count() == 1)
                            {
                                var subscriptionSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("subscriptions"));
                                if (subscriptionSegment != null)
                                {
                                    bool hasResourceParm = requiredParameters.Any(p => p.SerializedName.EqualsIgnoreCase(resourceSegment.Parameter.SerializedName));
                                    if (hasResourceParm)
                                    {
                                        this.supportsGetBySubscription = true;
                                        this.getBySubscriptionMethod = new FluentMethod(true, innerMethod, this.FluentMethodGroup);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                this.supportsGetBySubscription = false;
                this.getBySubscriptionMethod = null;
            }
        }

        private void CheckGetByParameterizedParentSupport()
        {
            if (this.FluentMethodGroup.Level == 0)
            {
                foreach (MethodJvaf innerMethod in FluentMethodGroup.InnerMethods.Where(method => method.HttpMethod == HttpMethod.Get))
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
                            var requiredParameters = RequiredParametersOfMethod(innerMethod);
                            if (resourceSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase))
                            {
                                var subscriptionSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("subscriptions"));
                                var resourceGroupSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("resourceGroups"));

                                if (subscriptionSegment == null && resourceGroupSegment == null)
                                {
                                    this.supportsGetByParameterizedParent = true;
                                    this.getByParameterizedParentMethod = new FluentMethod(true, innerMethod, this.FluentMethodGroup);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                this.supportsGetByParameterizedParent = false;
                this.getByParameterizedParentMethod = null;
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
                                            this.supportsGetByImmediateParent = true;
                                            this.getByImmediateParentMethod = new FluentMethod(true, innerMethod, this.FluentMethodGroup);
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
                this.supportsGetByImmediateParent = false;
                this.getByImmediateParentMethod = null;
            }
        }

        public string GetInnerMethodImplementation(bool applyOverride)
        {
            return this.GetInnerAsyncFuncFactory.GetFromResourceGroupAsyncFunc.MethodImpl(applyOverride);
        }

        public IEnumerable<string> GetByResourceGroupSyncAsyncImplementation
        {
            get
            {
                if (this.SupportsGetByResourceGroup)
                {
                    string methodImpl = this.GetByResourceGroupSyncImplementation;
                    if (!string.IsNullOrEmpty(methodImpl))
                    {
                        yield return methodImpl;
                    }

                    methodImpl = this.GetByResourceGroupRxAsyncMethodImplementation;
                    if (!string.IsNullOrEmpty(methodImpl))
                    {
                        yield return methodImpl;
                    }

                    methodImpl = this.GetByResourceGroupFutureAsyncMethodImplementation;
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

        public string GetByImmediateParentMethodImplementation
        {
            get 
            {
                StringBuilder methodBuilder = new StringBuilder();
                if (this.SupportsGetByImmediateParent)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    string modelInnerName = standardModel.InnerModelName;
                    string innerClientName = this.FluentMethodGroup.InnerMethodGroupTypeName;
                    string parentMethodGroupLocalSingularName = this.FluentMethodGroup.ParentFluentMethodGroup.LocalSingularNameInPascalCase;
                    //
                    FluentMethod method = this.GetByImmediateParentMethod;
                    //
                    string methodName = $"getBy{parentMethodGroupLocalSingularName}Async";
                    string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;

                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> {methodName}({parameterDecl}) {{");
                    methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{method.Name}Async({method.InnerMethodInvocationParameters})");
                    methodBuilder.AppendLine($"    .map(new Func1<{modelInnerName}, {modelInterfaceName}>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} inner) {{");
                    methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.MethodName}(inner);");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"   }});");
                    methodBuilder.AppendLine($"}}");
                }
                return methodBuilder.ToString();
            }
        }

        private string GetByResourceGroupSyncImplementation
        {
            get
            {
                if (this.SupportsGetByResourceGroup)
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

        private string GetByResourceGroupFutureAsyncMethodImplementation
        {
            get
            {
                if (this.SupportsGetByResourceGroup)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    // Obs<T> getByResourceGroupAsync(String resourceGroupName, String name)
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

        private string GetByResourceGroupRxAsyncMethodImplementation
        {
            get
            {
                if (this.SupportsGetByResourceGroup)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    string modelInnerName = standardModel.InnerModelName;
                    // ServiceFuture<EventSubscription> getByResourceGroupAsync(String resourceGroupName, String name, ServiceCallback<EventSubscription> callback)
                    //
                    StringBuilder methodBuilder = new StringBuilder();
                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> getByResourceGroupAsync(String resourceGroupName, String name) {{");
                    methodBuilder.AppendLine($"    return this.{this.GetInnerAsyncFuncFactory.GetFromResourceGroupAsyncFunc.MethodName}(resourceGroupName, name).map(new Func1<{modelInnerName}, {modelInterfaceName}> () {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} innerT) {{");
                    methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.MethodName}(innerT);");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }});");
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

        private static IEnumerable<ParameterJv> RequiredParametersOfMethod(MethodJvaf method)
        {
            return method.LocalParameters.Where(parameter => parameter.IsRequired && !parameter.IsConstant);
        }

    }
}
