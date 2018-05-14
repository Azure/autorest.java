// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ResourceGetDescription
    {
        private bool isProcessed;

        private bool supportsGetBySubscription;
        private bool supportsGetByResourceGroup;
        private bool supportsGetByImmediateParent;
        private bool supportsGetByParameterizedParent;
        private FluentMethod getBySubscriptionMethod;
        private FluentMethod getByResourceGroupMethod;
        private FluentMethod getByImmediateParentMethod;
        private FluentMethod getByParameterizedParentMethod;

        public ResourceGetDescription(FluentMethodGroup fluentMethodGroup)
        {
            this.FluentMethodGroup = fluentMethodGroup;
        }

        public FluentMethodGroup FluentMethodGroup { get; }

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

        private GetInnerAsyncFunc getInnerAsyncFunc;
        public GetInnerAsyncFunc GetInnerAsyncFunc
        {
            get
            {
                if (this.getInnerAsyncFunc == null)
                {
                    this.getInnerAsyncFunc = new GetInnerAsyncFunc(this);
                }
                return this.getInnerAsyncFunc;
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
                    FluentMethodGroup parentMethodGroup = this.FluentMethodGroup.ParentFluentMethodGroup;
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
            return GetInnerAsyncFunc.MethodImpl(applyOverride);
        }

        public IEnumerable<string> GetByResourceGroupSyncAsyncImplementation()
        {
            if (this.SupportsGetByResourceGroup)
            {
                StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                string modelInterfaceName = standardModel.JavaInterfaceName;
                string modelInnerName = standardModel.InnerModelName;
                //
                StringBuilder methodBuilder = new StringBuilder();

                // T getByResourceGroup(String resourceGroupName, String name)
                //
                methodBuilder.Clear();
                methodBuilder.AppendLine($"@Override");
                methodBuilder.AppendLine($"public {modelInterfaceName} getByResourceGroup(String resourceGroupName, String name) {{");
                methodBuilder.AppendLine($"    return getByResourceGroupAsync(resourceGroupName, name).toBlocking().last();");
                methodBuilder.AppendLine($"}}");
                yield return methodBuilder.ToString();

                // Obs<T> getByResourceGroupAsync(String resourceGroupName, String name)
                //
                methodBuilder.Clear();
                methodBuilder.AppendLine($"@Override");
                methodBuilder.AppendLine($"public ServiceFuture<{modelInterfaceName}> getByResourceGroupAsync(String resourceGroupName, String name, ServiceCallback<{modelInterfaceName}> callback) {{");
                methodBuilder.AppendLine($"    return ServiceFuture.fromBody(getByResourceGroupAsync(resourceGroupName, name), callback);");
                methodBuilder.AppendLine($"}}");
                yield return methodBuilder.ToString();

                // ServiceFuture<EventSubscription> getByResourceGroupAsync(String resourceGroupName, String name, ServiceCallback<EventSubscription> callback)
                //
                methodBuilder.Clear();
                methodBuilder.AppendLine($"@Override");
                methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> getByResourceGroupAsync(String resourceGroupName, String name) {{");
                methodBuilder.AppendLine($"    return this.{this.GetInnerAsyncFunc.MethodName}(resourceGroupName, name).map(new Func1<{modelInnerName}, {modelInterfaceName}> () {{");
                methodBuilder.AppendLine($"        @Override");
                methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} innerT) {{");
                methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.MethodName}(innerT);");
                methodBuilder.AppendLine($"        }}");
                methodBuilder.AppendLine($"    }});");
                methodBuilder.AppendLine($"}}");
                yield return methodBuilder.ToString();
            }
            else
            {
                yield break;
            }
        }

        public string GetByImmediateParentMethodImplementation()
        {
            StringBuilder methodBuilder = new StringBuilder();
            if (this.SupportsGetByImmediateParent)
            {
                //
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

        private static IEnumerable<ParameterJv> RequiredParametersOfMethod(MethodJvaf method)
        {
            return method.LocalParameters.Where(parameter => parameter.IsRequired && !parameter.IsConstant);
        }
    }
}
