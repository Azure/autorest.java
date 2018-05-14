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
    public class ResourceListingDescription : ISupportsGeneralizedView
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        private bool isProcessed;
        private bool supportsListByResourceGroup;
        private bool supportsListBySubscription;
        private bool supportsListByImmediateParent;
        private FluentMethod listByResourceGroupMethod;
        private FluentMethod listBySubscriptionMethod;
        private FluentMethod listByImmediateParentMethod;

        public FluentMethodGroup FluentMethodGroup { get; }

        public ResourceListingDescription(FluentMethodGroup fluentMethodGroup)
        {
            this.FluentMethodGroup = fluentMethodGroup;
        }

        public bool SupportsListByResourceGroup
        {
            get
            {
                this.Process();
                return this.supportsListByResourceGroup;
            }
        }

        public FluentMethod ListByResourceGroupMethod
        {
            get
            {
                this.Process();
                return this.listByResourceGroupMethod;
            }
        }

        public bool SupportsListBySubscription
        {
            get
            {
                this.Process();
                return this.supportsListBySubscription;
            }
        }

        public FluentMethod ListBySubscriptionMethod
        {
            get
            {
                this.Process();
                return this.listBySubscriptionMethod;
            }
        }

        public bool SupportsListByImmediateParent
        {
            get
            {
                this.Process();
                return this.supportsListByImmediateParent;
            }
        }

        public FluentMethod ListByImmediateParentMethod
        {
            get
            {
                this.Process();
                return this.listByImmediateParentMethod;
            }
        }

        public HashSet<string> MethodGroupInterfaceExtendsFrom
        {
            get
            {
                HashSet<string> extendsFrom = new HashSet<string>();
                if (this.SupportsListByResourceGroup)
                {
                    extendsFrom.Add($"SupportsListingByResourceGroup<{this.ListByResourceGroupMethod.ReturnModel.JavaInterfaceName}>");
                }
                if (this.SupportsListBySubscription)
                {
                    extendsFrom.Add($"SupportsListing<{this.ListBySubscriptionMethod.ReturnModel.JavaInterfaceName}>");
                }
                return extendsFrom;
            }
        }

        public HashSet<string> ImportsForMethodGroupInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsListByResourceGroup)
                {
                    imports.Add("com.microsoft.azure.arm.resources.collection.SupportsListingByResourceGroup");
                }
                if (this.SupportsListBySubscription)
                {
                    imports.Add("com.microsoft.azure.arm.collection.SupportsListing");
                }
                if (this.SupportsListByImmediateParent)
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
                if (this.SupportsListByResourceGroup)
                {
                    imports.Add("rx.Observable");
                    imports.Add("rx.functions.Func1");
                    imports.Add("com.microsoft.azure.PagedList");
                    FluentMethod method = this.ListByResourceGroupMethod;
                    if (method.InnerMethod.IsPagingOperation || method.InnerMethod.SimulateAsPagingOperation)
                    {
                        imports.Add("com.microsoft.azure.Page");
                        imports.Add("rx.functions.Func1");
                    }
                    if (!method.InnerMethod.IsPagingOperation && !method.InnerMethod.SimulateAsPagingOperation && method.InnerMethod.ReturnTypeResponseName.StartsWith("List<"))
                    {
                        imports.Add("rx.Observable");
                        imports.Add("java.util.List");
                        imports.Add("rx.functions.Func1");
                    }
                }
                if (this.SupportsListBySubscription)
                {
                    imports.Add("rx.Observable");
                    imports.Add("rx.functions.Func1");
                    imports.Add("com.microsoft.azure.PagedList");
                    FluentMethod method = this.ListBySubscriptionMethod;
                    if (method.InnerMethod.IsPagingOperation || method.InnerMethod.SimulateAsPagingOperation)
                    {
                        imports.Add("com.microsoft.azure.Page");
                        imports.Add("rx.functions.Func1");
                    }
                    if (!method.InnerMethod.IsPagingOperation && !method.InnerMethod.SimulateAsPagingOperation && method.InnerMethod.ReturnTypeResponseName.StartsWith("List<"))
                    {
                        imports.Add("rx.Observable");
                        imports.Add("java.util.List");
                        imports.Add("rx.functions.Func1");
                    }
                }
                if (this.SupportsListByImmediateParent)
                {
                    imports.Add("rx.Observable");
                    imports.Add("rx.functions.Func1");
                    FluentMethod method = this.ListByImmediateParentMethod;
                    if (method.InnerMethod.IsPagingOperation || method.InnerMethod.SimulateAsPagingOperation)
                    {
                        imports.Add("com.microsoft.azure.Page");
                        imports.Add("rx.functions.Func1");
                    }
                    if (!method.InnerMethod.IsPagingOperation && !method.InnerMethod.SimulateAsPagingOperation && method.InnerMethod.ReturnTypeResponseName.StartsWith("List<"))
                    {
                        imports.Add("rx.Observable");
                        imports.Add("java.util.List");
                        imports.Add("rx.functions.Func1");
                    }
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
                if (this.SupportsListByResourceGroup)
                {
                    imports.Add("rx.Observable");
                    imports.Add($"{package}.{this.FluentMethodGroup.StandardFluentModel.JavaInterfaceName}");
                }
                if (this.SupportsListBySubscription)
                {
                    imports.Add("rx.Observable");
                    imports.Add($"{package}.{this.FluentMethodGroup.StandardFluentModel.JavaInterfaceName}");
                }
                if (this.SupportsListByImmediateParent)
                {
                    imports.Add("rx.Observable");
                    imports.Add($"{package}.{this.FluentMethodGroup.StandardFluentModel.JavaInterfaceName}");
                }
                return imports;
            }
        }

        public HashSet<string> ImportsForGeneralizedImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsListByResourceGroup)
                {
                    imports.Add("rx.Observable");
                    imports.Add("rx.functions.Func1");
                    FluentMethod method = this.ListByResourceGroupMethod;
                    if (method.InnerMethod.IsPagingOperation || method.InnerMethod.SimulateAsPagingOperation)
                    {
                        imports.Add("com.microsoft.azure.Page");
                        imports.Add("rx.functions.Func1");
                    }
                    if (!method.InnerMethod.IsPagingOperation && !method.InnerMethod.SimulateAsPagingOperation && method.InnerMethod.ReturnTypeResponseName.StartsWith("List<"))
                    {
                        imports.Add("rx.Observable");
                        imports.Add("java.util.List");
                        imports.Add("rx.functions.Func1");
                    }
                }
                if (this.SupportsListBySubscription)
                {
                    imports.Add("rx.Observable");
                    imports.Add("rx.functions.Func1");
                    FluentMethod method = this.ListBySubscriptionMethod;
                    if (method.InnerMethod.IsPagingOperation || method.InnerMethod.SimulateAsPagingOperation)
                    {
                        imports.Add("com.microsoft.azure.Page");
                        imports.Add("rx.functions.Func1");
                    }
                    if (!method.InnerMethod.IsPagingOperation && !method.InnerMethod.SimulateAsPagingOperation && method.InnerMethod.ReturnTypeResponseName.StartsWith("List<"))
                    {
                        imports.Add("rx.Observable");
                        imports.Add("java.util.List");
                        imports.Add("rx.functions.Func1");
                    }
                }
                if (this.SupportsListByImmediateParent)
                {
                    imports.Add("rx.Observable");
                    imports.Add("rx.functions.Func1");
                    FluentMethod method = this.ListByImmediateParentMethod;
                    if (method.InnerMethod.IsPagingOperation || method.InnerMethod.SimulateAsPagingOperation)
                    {
                        imports.Add("com.microsoft.azure.Page");
                        imports.Add("rx.functions.Func1");
                    }
                    if (!method.InnerMethod.IsPagingOperation && !method.InnerMethod.SimulateAsPagingOperation && method.InnerMethod.ReturnTypeResponseName.StartsWith("List<"))
                    {
                        imports.Add("rx.Observable");
                        imports.Add("java.util.List");
                        imports.Add("rx.functions.Func1");
                    }
                }
                return imports;
            }
        }

        public IEnumerable<string> GeneralizedMethodDecls
        {
            get
            {
                if (this.SupportsListByResourceGroup)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    //
                    yield return $"Observable<{modelInterfaceName}> {this.ListByResourceGroupMethod.Name}Async(string resourceGroupName);";
                }
                if (this.SupportsListBySubscription)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    //
                    yield return $"Observable<{modelInterfaceName}> {this.ListBySubscriptionMethod.Name}Async();";
                }
                if (this.SupportsListByImmediateParent)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    FluentMethod method = this.ListByImmediateParentMethod;
                    string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;
                    //
                    yield return $"Observable<{modelInterfaceName}> {method.Name}Async({parameterDecl});";
                }
            }
        }

        public IEnumerable<string> GeneralizedMethodImpls
        {
            get
            {
                if (this.SupportsListByResourceGroup)
                {
                    yield return this.ListByResourceGroupRxAsyncMethodGeneralizedImplementation;
                }
                if (this.SupportsListBySubscription)
                {
                    yield return this.ListBySubscriptionRxAsyncMethodGeneralizedImplementation;
                }
                if (this.SupportsListByImmediateParent)
                {
                    yield return this.ListByImmediateParentRxAsyncMethodGeneralizedImplementation;
                }
            }
        }

        private string ListByResourceGroupRxAsyncMethodGeneralizedImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                //
                if (this.SupportsListByResourceGroup)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInnerName = standardModel.InnerModelName;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    string innerClientName = this.FluentMethodGroup.InnerMethodGroupTypeName;
                    //
                    FluentMethod method = this.ListByResourceGroupMethod;
                    string innerReturnTypeName = method.InnerReturnType.ClassName;
                    //
                    if (!method.InnerMethod.IsPagingOperation)
                    {
                        //
                        methodBuilder.AppendLine($"@Override");
                        methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> {method.Name}Async(String resourceGroupName) {{");
                        methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                        methodBuilder.AppendLine($"    return client.{method.Name}Async(resourceGroupName)");
                        if (method.InnerMethod.SimulateAsPagingOperation)
                        {
                            methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{innerReturnTypeName}>, Observable<{innerReturnTypeName}>>() {{");
                            methodBuilder.AppendLine($"        @Override");
                            methodBuilder.AppendLine($"        public Observable<{innerReturnTypeName}> call(Page<{innerReturnTypeName}> innerPage) {{");
                            methodBuilder.AppendLine($"            return Observable.from(innerPage.items());");
                            methodBuilder.AppendLine($"        }}");
                            methodBuilder.AppendLine($"    }})");
                        }
                        string flatMap = this.FluentMethodGroup.ModelMapper.GetFlatMapToStandardModelFor(innerReturnTypeName, true);
                        if (flatMap != null)
                        {
                            methodBuilder.AppendLine($"{flatMap}");
                        }
                        methodBuilder.AppendLine($"    .map(new Func1<{modelInnerName}, {modelInterfaceName}>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} inner) {{");
                        methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.GeneralizedMethodName}(inner);");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }});");
                        methodBuilder.AppendLine($"}}");
                    }
                    else
                    {
                        string nextPageMethodName = $"{method.Name}NextInnerPageAsync";

                        methodBuilder.AppendLine($"private Observable<Page<{innerReturnTypeName}>> {nextPageMethodName}(String nextLink) {{");
                        methodBuilder.AppendLine($"    if (nextLink == null) {{");
                        methodBuilder.AppendLine($"        Observable.empty();");
                        methodBuilder.AppendLine($"    }}");
                        methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                        methodBuilder.AppendLine($"    return client.{method.Name}NextAsync(nextLink)");
                        methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{innerReturnTypeName}>, Observable<Page<{innerReturnTypeName}>>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Observable<Page<{innerReturnTypeName}>> call(Page<{innerReturnTypeName}> page) {{");
                        methodBuilder.AppendLine($"            return Observable.just(page).concatWith({nextPageMethodName}(page.nextPageLink()));");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }});");
                        methodBuilder.AppendLine($"}}");

                        methodBuilder.AppendLine($"@Override");
                        methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> {method.Name}Async(String resourceGroupName) {{");
                        methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                        methodBuilder.AppendLine($"    return client.{method.Name}Async(resourceGroupName)");
                        methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{innerReturnTypeName}>, Observable<Page<{innerReturnTypeName}>>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Observable<Page<{innerReturnTypeName}>> call(Page<{innerReturnTypeName}> page) {{");
                        methodBuilder.AppendLine($"            return {nextPageMethodName}(page.nextPageLink());");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }})");
                        methodBuilder.AppendLine($"    .flatMapIterable(new Func1<Page<{innerReturnTypeName}>, Iterable<{innerReturnTypeName}>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Iterable<{innerReturnTypeName}> call(Page<{innerReturnTypeName}> page) {{");
                        methodBuilder.AppendLine($"            return page.items();");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"   }})");
                        string flatMap = this.FluentMethodGroup.ModelMapper.GetFlatMapToStandardModelFor(innerReturnTypeName, true);
                        if (flatMap != null)
                        {
                            methodBuilder.AppendLine($"{flatMap}");
                        }
                        methodBuilder.AppendLine($"    .map(new Func1<{modelInnerName}, {modelInterfaceName}>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} inner) {{");
                        methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.GeneralizedMethodName}(inner);");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"   }});");
                        methodBuilder.AppendLine($"}}");
                    }
                }
                return methodBuilder.ToString();
            }
        }

        private string ListBySubscriptionRxAsyncMethodGeneralizedImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                if (this.SupportsListBySubscription)
                {
                    //
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInnerName = standardModel.InnerModelName;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    string innerClientName = this.FluentMethodGroup.InnerMethodGroupTypeName;
                    //
                    FluentMethod method = this.ListBySubscriptionMethod;
                    string innerReturnTypeName = method.InnerReturnType.ClassName;
                    //
                    if (!method.InnerMethod.IsPagingOperation)
                    {
                        FluentModel returnModel = method.ReturnModel;
                        //
                        methodBuilder.AppendLine($"@Override");
                        methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> {method.Name}Async() {{");
                        methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                        methodBuilder.AppendLine($"    return client.{method.Name}Async()");
                        if (method.InnerMethod.SimulateAsPagingOperation)
                        {
                            methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{innerReturnTypeName}>, Observable<{innerReturnTypeName}>>() {{");
                            methodBuilder.AppendLine($"        @Override");
                            methodBuilder.AppendLine($"        public Observable<{innerReturnTypeName}> call(Page<{innerReturnTypeName}> innerPage) {{");
                            methodBuilder.AppendLine($"            return Observable.from(innerPage.items());");
                            methodBuilder.AppendLine($"        }}");
                            methodBuilder.AppendLine($"    }})");
                        }
                        string flatMap = this.FluentMethodGroup.ModelMapper.GetFlatMapToStandardModelFor(innerReturnTypeName, true);
                        if (flatMap != null)
                        {
                            methodBuilder.AppendLine($"{flatMap}");
                        }
                        methodBuilder.AppendLine($"    .map(new Func1<{modelInnerName}, {modelInterfaceName}>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} inner) {{");
                        methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.GeneralizedMethodName}(inner);");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }});");
                        methodBuilder.AppendLine($"}}");
                    }
                    else
                    {
                        string nextPageMethodName = $"{method.Name}NextInnerPageAsync";

                        methodBuilder.AppendLine($"private Observable<Page<{innerReturnTypeName}>> {nextPageMethodName}(String nextLink) {{");
                        methodBuilder.AppendLine($"    if (nextLink == null) {{");
                        methodBuilder.AppendLine($"        Observable.empty();");
                        methodBuilder.AppendLine($"    }}");
                        methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                        methodBuilder.AppendLine($"    return client.{method.Name}NextAsync(nextLink)");
                        methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{innerReturnTypeName}>, Observable<Page<{innerReturnTypeName}>>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Observable<Page<{innerReturnTypeName}>> call(Page<{innerReturnTypeName}> page) {{");
                        methodBuilder.AppendLine($"            return Observable.just(page).concatWith({nextPageMethodName}(page.nextPageLink()));");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }});");
                        methodBuilder.AppendLine($"}}");

                        methodBuilder.AppendLine($"@Override");
                        methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> {method.Name}Async() {{");
                        methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                        methodBuilder.AppendLine($"    return client.{method.Name}Async()");
                        methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{innerReturnTypeName}>, Observable<Page<{innerReturnTypeName}>>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Observable<Page<{innerReturnTypeName}>> call(Page<{innerReturnTypeName}> page) {{");
                        methodBuilder.AppendLine($"            return {nextPageMethodName}(page.nextPageLink());");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }})");
                        methodBuilder.AppendLine($"    .flatMapIterable(new Func1<Page<{innerReturnTypeName}>, Iterable<{innerReturnTypeName}>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Iterable<{innerReturnTypeName}> call(Page<{innerReturnTypeName}> page) {{");
                        methodBuilder.AppendLine($"            return page.items();");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"   }})");
                        string flatMap = this.FluentMethodGroup.ModelMapper.GetFlatMapToStandardModelFor(innerReturnTypeName, true);
                        if (flatMap != null)
                        {
                            methodBuilder.AppendLine($"{flatMap}");
                        }
                        methodBuilder.AppendLine($"    .map(new Func1<{modelInnerName}, {modelInterfaceName}>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} inner) {{");
                        methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.GeneralizedMethodName}(inner);");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"   }});");
                        methodBuilder.AppendLine($"}}");
                    }
                }
                return methodBuilder.ToString();
            }
        }

        private string ListByImmediateParentRxAsyncMethodGeneralizedImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                if (this.SupportsListByImmediateParent)
                {
                    //
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInnerName = standardModel.InnerModelName;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    string innerClientName = this.FluentMethodGroup.InnerMethodGroupTypeName;
                    //
                    FluentMethod method = this.ListByImmediateParentMethod;
                    if (!method.InnerMethod.IsPagingOperation)
                    {
                        FluentModel returnModel = method.ReturnModel;
                        string returnInnerModelName = returnModel.InnerModel.ClassName;
                        //
                        string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;

                        methodBuilder.AppendLine($"@Override");
                        methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> {method.Name}Async({parameterDecl}) {{");
                        methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                        methodBuilder.AppendLine($"    return client.{method.Name}Async({method.InnerMethodInvocationParameters})");
                        if (method.InnerMethod.SimulateAsPagingOperation)
                        {
                            methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{returnInnerModelName}>, Observable<{returnInnerModelName}>>() {{");
                            methodBuilder.AppendLine($"        @Override");
                            methodBuilder.AppendLine($"        public Observable<{returnInnerModelName}> call(Page<{returnInnerModelName}> innerPage) {{");
                            methodBuilder.AppendLine($"            return Observable.from(innerPage.items());");
                            methodBuilder.AppendLine($"        }}");
                            methodBuilder.AppendLine($"    }})");
                        }
                        else if (method.InnerMethod.ReturnTypeResponseName.StartsWith("List<"))
                        {
                            methodBuilder.AppendLine($"    .flatMap(new Func1<List<{returnInnerModelName}>, Observable<{returnInnerModelName}>>() {{");
                            methodBuilder.AppendLine($"        @Override");
                            methodBuilder.AppendLine($"        public Observable<{returnInnerModelName}> call(List<{returnInnerModelName}> innerList) {{");
                            methodBuilder.AppendLine($"            return Observable.from(innerList);");
                            methodBuilder.AppendLine($"        }}");
                            methodBuilder.AppendLine($"    }})");
                        }
                        methodBuilder.AppendLine($"    .map(new Func1<{modelInnerName}, {modelInterfaceName}>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} inner) {{");
                        //
                        // TODO (anuchan): handle the case where the return type of the list by immediate parent is different from the standard model derived
                        // for nested method group. This means having a getInnerAsync method in the nested method group that makes additional  apiCall to 
                        // transform items in the list to standard inner model then we wrap then.
                        //
                        methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.GeneralizedMethodName}(inner);");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }});");
                        methodBuilder.AppendLine($"}}");
                    }
                    else
                    {
                        string nextPageMethodName = $"{method.Name}NextInnerPageAsync";

                        methodBuilder.AppendLine($"private Observable<Page<{modelInnerName}>> {nextPageMethodName}(String nextLink) {{");
                        methodBuilder.AppendLine($"    if (nextLink == null) {{");
                        methodBuilder.AppendLine($"        Observable.empty();");
                        methodBuilder.AppendLine($"    }}");
                        methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                        methodBuilder.AppendLine($"    return client.{method.Name}NextAsync(nextLink)");
                        methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{modelInnerName}>, Observable<Page<{modelInnerName}>>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Observable<Page<{modelInnerName}>> call(Page<{modelInnerName}> page) {{");
                        methodBuilder.AppendLine($"            return Observable.just(page).concatWith({nextPageMethodName}(page.nextPageLink()));");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }});");
                        methodBuilder.AppendLine($"}}");

                        string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;

                        methodBuilder.AppendLine($"@Override");
                        methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> {method.Name}Async({parameterDecl}) {{");
                        methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                        methodBuilder.AppendLine($"    return client.{method.Name}Async({InnerMethodInvocationParameter(method.InnerMethod)})");
                        methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{modelInnerName}>, Observable<Page<{modelInnerName}>>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Observable<Page<{modelInnerName}>> call(Page<{modelInnerName}> page) {{");
                        methodBuilder.AppendLine($"            return {nextPageMethodName}(page.nextPageLink());");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }})");
                        methodBuilder.AppendLine($"    .flatMapIterable(new Func1<Page<{modelInnerName}>, Iterable<{modelInnerName}>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Iterable<{modelInnerName}> call(Page<{modelInnerName}> page) {{");
                        methodBuilder.AppendLine($"            return page.items();");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"   }})");
                        methodBuilder.AppendLine($"    .map(new Func1<{modelInnerName}, {modelInterfaceName}>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} inner) {{");
                        methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.GeneralizedMethodName}(inner);");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"   }});");
                        methodBuilder.AppendLine($"}}");
                    }
                }
                return methodBuilder.ToString();
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
                this.CheckListByResourceGroupSupport();
                this.CheckListBySubscriptionSupport();
                this.CheckListByImmediateParentSupport();
            }
        }

        /// <summary>
        /// Checks can support "SupportsListByResourceGroup" interface
        /// </summary>
        private void CheckListByResourceGroupSupport()
        {
            if (this.FluentMethodGroup.Level == 0)
            {
                foreach (MethodJvaf innerMethod in FluentMethodGroup.InnerMethods.Where(method => method.HttpMethod == HttpMethod.Get))
                {
                    var armUri = new ARMUri(innerMethod);
                    Segment lastSegment = armUri.LastOrDefault();
                    if (lastSegment != null && lastSegment is TerminalSegment)
                    {
                        TerminalSegment terminalSegment = (TerminalSegment)lastSegment;
                        var requiredParameters = RequiredParametersOfMethod(innerMethod);
                        if (terminalSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase) && requiredParameters.Count() == 1)
                        {
                            var subscriptionSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("subscriptions"));
                            var resourceGroupSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("resourceGroups"));
                            if (subscriptionSegment != null && resourceGroupSegment != null)
                            {
                                var singleParameter = requiredParameters.First();
                                if (resourceGroupSegment.Parameter.SerializedName.EqualsIgnoreCase(singleParameter.SerializedName))
                                {
                                    if (innerMethod.ReturnTypeResponseName.StartsWith("PagedList<")
                                        || innerMethod.ReturnTypeResponseName.StartsWith("List<"))
                                    {
                                        this.supportsListByResourceGroup = true;
                                        this.listByResourceGroupMethod = new FluentMethod(true, innerMethod, this.FluentMethodGroup);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                this.supportsListByResourceGroup = false;
                this.listByResourceGroupMethod = null;
            }
        }

        /// <summary>
        /// Checks can support "SupportsListBySubscription" interface
        /// </summary>
        private void CheckListBySubscriptionSupport()
        {
            if (this.FluentMethodGroup.Level == 0)
            {
                foreach (MethodJvaf innerMethod in FluentMethodGroup.InnerMethods.Where(method => method.HttpMethod == HttpMethod.Get))
                {
                    var armUri = new ARMUri(innerMethod);
                    Segment lastSegment = armUri.LastOrDefault();
                    if (lastSegment != null && lastSegment is TerminalSegment)
                    {
                        TerminalSegment terminalSegment = (TerminalSegment)lastSegment;
                        var requiredParameters = RequiredParametersOfMethod(innerMethod);
                        if (terminalSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase) && requiredParameters.Count() == 0)
                        {
                            var subscriptionSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("subscriptions"));
                            if (subscriptionSegment != null)
                            {
                                if (innerMethod.ReturnTypeResponseName.StartsWith("PagedList<") 
                                    || innerMethod.ReturnTypeResponseName.StartsWith("List<"))
                                {
                                    this.supportsListBySubscription = true;
                                    this.listBySubscriptionMethod = new FluentMethod(true, innerMethod, this.FluentMethodGroup);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                this.supportsListBySubscription = false;
                this.listBySubscriptionMethod = null;
            }
        }

        /// <summary>
        /// Check can support list by immediate parent.
        /// </summary>
        private void CheckListByImmediateParentSupport()
        {
            if (this.FluentMethodGroup.Level > 0)
            {
                foreach (MethodJvaf innerMethod in FluentMethodGroup.InnerMethods.Where(method => method.HttpMethod == HttpMethod.Get))
                {
                    FluentMethodGroup parentMethodGroup = this.FluentMethodGroup.ParentFluentMethodGroup;
                    if (parentMethodGroup != null)
                    {
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is TerminalSegment)
                        {
                            TerminalSegment terminalSegment = (TerminalSegment)lastSegment;
                            if (terminalSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase))
                            {
                                Segment secondLastSegment = armUri.SkipLast(1).LastOrDefault();
                                if (secondLastSegment != null && secondLastSegment is ParentSegment)
                                {
                                    ParentSegment parentSegment = (ParentSegment)secondLastSegment;
                                    if (parentSegment.Name.EqualsIgnoreCase(parentMethodGroup.LocalNameInPascalCase))
                                    {
                                        if (innerMethod.ReturnTypeResponseName.StartsWith("PagedList<")
                                            || innerMethod.ReturnTypeResponseName.StartsWith("List<"))
                                        {
                                            this.supportsListByImmediateParent = true;
                                            this.listByImmediateParentMethod = new FluentMethod(true, innerMethod, this.FluentMethodGroup);
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
                this.supportsListByImmediateParent = false;
                this.listByImmediateParentMethod = null;
            }
        }

        public string ListBySubscriptionSyncMethodImplementation(string convertToPagedListMethodName, string innerClientName, string standardModelInnerName, string stnadardModelInterfaceName)
        {
            StringBuilder methodBuilder = new StringBuilder();
            if (this.SupportsListBySubscription)
            {
                FluentMethod method = this.ListBySubscriptionMethod;
                string innerReturnTypeName = method.InnerReturnType.ClassName;
                // TODO: Check return type is "PagedList" then "converter.convert"
                //       If return type is "List" create a Page, then PagedList from it then "converter.convert"
                //
                methodBuilder.AppendLine("@Override");
                methodBuilder.AppendLine($"public PagedList<{stnadardModelInterfaceName}> list() {{");
                methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                if (innerReturnTypeName.EqualsIgnoreCase(standardModelInnerName))
                {
                    methodBuilder.AppendLine($"    return {convertToPagedListMethodName}(client.{method.Name}());");
                }
                else
                {
                    string convertor = this.FluentMethodGroup.ModelMapper.GetPagedListConvertor(innerReturnTypeName, $"client.{method.Name}()", false);
                    methodBuilder.AppendLine(convertor); 
                }
                methodBuilder.AppendLine($"}}");
            }
            return methodBuilder.ToString();
        }

        public string ListBySubscriptionAsyncMethodImplementation()
        {
            StringBuilder methodBuilder = new StringBuilder();
            if (this.SupportsListBySubscription)
            {
                //
                StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                string stdandardModelInnerName = standardModel.InnerModelName;
                string modelInterfaceName = standardModel.JavaInterfaceName;
                string innerClientName = this.FluentMethodGroup.InnerMethodGroupTypeName;
                //
                FluentMethod method = this.ListBySubscriptionMethod;
                string innerReturnTypeName = method.InnerReturnType.ClassName;
                //
                if (!method.InnerMethod.IsPagingOperation)
                {
                    FluentModel returnModel = method.ReturnModel;
                    //
                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> listAsync() {{");
                    methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{method.Name}Async()");
                    if (method.InnerMethod.SimulateAsPagingOperation)
                    {
                        methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{innerReturnTypeName}>, Observable<{innerReturnTypeName}>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Observable<{innerReturnTypeName}> call(Page<{innerReturnTypeName}> innerPage) {{");
                        methodBuilder.AppendLine($"            return Observable.from(innerPage.items());");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }})");
                    }
                    string flatMap = this.FluentMethodGroup.ModelMapper.GetFlatMapToStandardModelFor(innerReturnTypeName, false);
                    if (flatMap != null)
                    {
                        methodBuilder.AppendLine($"{flatMap}");
                    }
                    methodBuilder.AppendLine($"    .map(new Func1<{stdandardModelInnerName}, {modelInterfaceName}>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public {modelInterfaceName} call({stdandardModelInnerName} inner) {{");
                    methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.MethodName}(inner);");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }});");
                    methodBuilder.AppendLine($"}}");
                }
                else
                {
                    string nextPageMethodName = $"listNextInnerPageAsync";

                    methodBuilder.AppendLine($"private Observable<Page<{innerReturnTypeName}>> {nextPageMethodName}(String nextLink) {{");
                    methodBuilder.AppendLine($"    if (nextLink == null) {{");
                    methodBuilder.AppendLine($"        Observable.empty();");
                    methodBuilder.AppendLine($"    }}");
                    methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{method.Name}NextAsync(nextLink)");
                    methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{innerReturnTypeName}>, Observable<Page<{innerReturnTypeName}>>>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Observable<Page<{innerReturnTypeName}>> call(Page<{innerReturnTypeName}> page) {{");
                    methodBuilder.AppendLine($"            return Observable.just(page).concatWith({nextPageMethodName}(page.nextPageLink()));");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }});");
                    methodBuilder.AppendLine($"}}");

                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> listAsync() {{");
                    methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{method.Name}Async()");
                    methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{innerReturnTypeName}>, Observable<Page<{innerReturnTypeName}>>>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Observable<Page<{innerReturnTypeName}>> call(Page<{innerReturnTypeName}> page) {{");
                    methodBuilder.AppendLine($"            return {nextPageMethodName}(page.nextPageLink());");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }})");
                    methodBuilder.AppendLine($"    .flatMapIterable(new Func1<Page<{innerReturnTypeName}>, Iterable<{innerReturnTypeName}>>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Iterable<{innerReturnTypeName}> call(Page<{innerReturnTypeName}> page) {{");
                    methodBuilder.AppendLine($"            return page.items();");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"   }})");
                    string flatMap = this.FluentMethodGroup.ModelMapper.GetFlatMapToStandardModelFor(innerReturnTypeName, false);
                    if (flatMap != null)
                    {
                        methodBuilder.AppendLine($"{flatMap}");
                    }
                    methodBuilder.AppendLine($"    .map(new Func1<{stdandardModelInnerName}, {modelInterfaceName}>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public {modelInterfaceName} call({stdandardModelInnerName} inner) {{");
                    methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.MethodName}(inner);");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"   }});");
                    methodBuilder.AppendLine($"}}");
                }
            }
            return methodBuilder.ToString();
        }


        public string ListByResourceGroupSyncMethodImplementation(string convertToPagedListMethodName, string innerClientName, string standardModelInnerName, string standardModelInterfaceName)
        {
            StringBuilder methodBuilder = new StringBuilder();
            if (this.SupportsListByResourceGroup)
            {
                FluentMethod method = this.ListByResourceGroupMethod;
                string innerReturnTypeName = method.InnerReturnType.ClassName;
                //
                methodBuilder.AppendLine("@Override");
                methodBuilder.AppendLine($"public PagedList<{standardModelInterfaceName}> listByResourceGroup(String resourceGroupName) {{");
                methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                if (innerReturnTypeName.EqualsIgnoreCase(standardModelInnerName))
                {
                    methodBuilder.AppendLine($"    return {convertToPagedListMethodName}(client.{method.Name}(resourceGroupName));");
                }
                else
                {
                    string convertor = this.FluentMethodGroup.ModelMapper.GetPagedListConvertor(innerReturnTypeName, $"client.{method.Name} (resourceGroupName)", false);
                    methodBuilder.AppendLine(convertor);
                }
                methodBuilder.AppendLine($"}}");
            }
            return methodBuilder.ToString();
        }

        public string ListByResourceGroupAsyncMethodImplementation()
        {
            StringBuilder methodBuilder = new StringBuilder();
            if (this.SupportsListByResourceGroup)
            {
                //
                StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                string modelInnerName = standardModel.InnerModelName;
                string modelInterfaceName = standardModel.JavaInterfaceName;
                string innerClientName = this.FluentMethodGroup.InnerMethodGroupTypeName;
                //
                FluentMethod method = this.ListByResourceGroupMethod;
                string innerReturnTypeName = method.InnerReturnType.ClassName;
                //
                if (!method.InnerMethod.IsPagingOperation)
                {
                    //
                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> listByResourceGroupAsync(String resourceGroupName) {{");
                    methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{method.Name}Async(resourceGroupName)");
                    if (method.InnerMethod.SimulateAsPagingOperation)
                    {
                        methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{innerReturnTypeName}>, Observable<{innerReturnTypeName}>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Observable<{innerReturnTypeName}> call(Page<{innerReturnTypeName}> innerPage) {{");
                        methodBuilder.AppendLine($"            return Observable.from(innerPage.items());");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }})");
                    }
                    string flatMap = this.FluentMethodGroup.ModelMapper.GetFlatMapToStandardModelFor(innerReturnTypeName, false);
                    if (flatMap != null)
                    {
                        methodBuilder.AppendLine($"{flatMap}");
                    }
                    methodBuilder.AppendLine($"    .map(new Func1<{modelInnerName}, {modelInterfaceName}>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} inner) {{");
                    methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.MethodName}(inner);");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }});");
                    methodBuilder.AppendLine($"}}");
                }
                else
                {
                    string nextPageMethodName = $"listByResourceGroupNextInnerPageAsync";

                    methodBuilder.AppendLine($"private Observable<Page<{innerReturnTypeName}>> {nextPageMethodName}(String nextLink) {{");
                    methodBuilder.AppendLine($"    if (nextLink == null) {{");
                    methodBuilder.AppendLine($"        Observable.empty();");
                    methodBuilder.AppendLine($"    }}");
                    methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{method.Name}NextAsync(nextLink)");
                    methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{innerReturnTypeName}>, Observable<Page<{innerReturnTypeName}>>>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Observable<Page<{innerReturnTypeName}>> call(Page<{innerReturnTypeName}> page) {{");
                    methodBuilder.AppendLine($"            return Observable.just(page).concatWith({nextPageMethodName}(page.nextPageLink()));");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }});");
                    methodBuilder.AppendLine($"}}");

                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> listByResourceGroupAsync(String resourceGroupName) {{");
                    methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{method.Name}Async(resourceGroupName)");
                    methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{innerReturnTypeName}>, Observable<Page<{innerReturnTypeName}>>>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Observable<Page<{innerReturnTypeName}>> call(Page<{innerReturnTypeName}> page) {{");
                    methodBuilder.AppendLine($"            return {nextPageMethodName}(page.nextPageLink());");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }})");
                    methodBuilder.AppendLine($"    .flatMapIterable(new Func1<Page<{innerReturnTypeName}>, Iterable<{innerReturnTypeName}>>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Iterable<{innerReturnTypeName}> call(Page<{innerReturnTypeName}> page) {{");
                    methodBuilder.AppendLine($"            return page.items();");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"   }})");
                    string flatMap = this.FluentMethodGroup.ModelMapper.GetFlatMapToStandardModelFor(innerReturnTypeName, false);
                    if (flatMap != null)
                    {
                        methodBuilder.AppendLine($"{flatMap}");
                    }
                    methodBuilder.AppendLine($"    .map(new Func1<{modelInnerName}, {modelInterfaceName}>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} inner) {{");
                    methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.MethodName}(inner);");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"   }});");
                    methodBuilder.AppendLine($"}}");
                }
            }
            return methodBuilder.ToString();
        }

        public string ListByImmediateParentAsyncMethodImplementation()
        {
            StringBuilder methodBuilder = new StringBuilder();
            if (this.SupportsListByImmediateParent)
            {
                //
                StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                string modelInnerName = standardModel.InnerModelName;
                string modelInterfaceName = standardModel.JavaInterfaceName;
                string innerClientName = this.FluentMethodGroup.InnerMethodGroupTypeName;
                string parentMethodGroupLocalSingularName = this.FluentMethodGroup.ParentFluentMethodGroup.LocalSingularNameInPascalCase;
                //
                FluentMethod method = this.ListByImmediateParentMethod;
                if (!method.InnerMethod.IsPagingOperation)
                {
                    FluentModel returnModel = method.ReturnModel;
                    string returnInnerModelName = returnModel.InnerModel.ClassName;
                    //
                    string methodName = $"listBy{parentMethodGroupLocalSingularName}Async";
                    string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;

                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> {methodName}({parameterDecl}) {{");
                    methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{method.Name}Async({method.InnerMethodInvocationParameters})");
                    if (method.InnerMethod.SimulateAsPagingOperation)
                    {
                        methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{returnInnerModelName}>, Observable<{returnInnerModelName}>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Observable<{returnInnerModelName}> call(Page<{returnInnerModelName}> innerPage) {{");
                        methodBuilder.AppendLine($"            return Observable.from(innerPage.items());");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }})");
                    }
                    else if (method.InnerMethod.ReturnTypeResponseName.StartsWith("List<"))
                    {
                        methodBuilder.AppendLine($"    .flatMap(new Func1<List<{returnInnerModelName}>, Observable<{returnInnerModelName}>>() {{");
                        methodBuilder.AppendLine($"        @Override");
                        methodBuilder.AppendLine($"        public Observable<{returnInnerModelName}> call(List<{returnInnerModelName}> innerList) {{");
                        methodBuilder.AppendLine($"            return Observable.from(innerList);");
                        methodBuilder.AppendLine($"        }}");
                        methodBuilder.AppendLine($"    }})");
                    }
                    methodBuilder.AppendLine($"    .map(new Func1<{modelInnerName}, {modelInterfaceName}>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} inner) {{");
                    //
                    // TODO (anuchan): handle the case where the return type of the list by immediate parent is different from the standard model derived
                    // for nested method group. This means having a getInnerAsync method in the nested method group that makes additional  apiCall to 
                    // transform items in the list to standard inner model then we wrap then.
                    //
                    methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.MethodName}(inner);");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }});");
                    methodBuilder.AppendLine($"}}");
                }
                else
                {
                    string nextPageMethodName = $"listBy{parentMethodGroupLocalSingularName}NextInnerPageAsync";

                    methodBuilder.AppendLine($"private Observable<Page<{modelInnerName}>> {nextPageMethodName}(String nextLink) {{");
                    methodBuilder.AppendLine($"    if (nextLink == null) {{");
                    methodBuilder.AppendLine($"        Observable.empty();");
                    methodBuilder.AppendLine($"    }}");
                    methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{method.Name}NextAsync(nextLink)");
                    methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{modelInnerName}>, Observable<Page<{modelInnerName}>>>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Observable<Page<{modelInnerName}>> call(Page<{modelInnerName}> page) {{");
                    methodBuilder.AppendLine($"            return Observable.just(page).concatWith({nextPageMethodName}(page.nextPageLink()));");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }});");
                    methodBuilder.AppendLine($"}}");

                    var methodName = $"listBy{parentMethodGroupLocalSingularName}Async";
                    string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;

                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> {methodName}({parameterDecl}) {{");
                    methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{method.Name}Async({InnerMethodInvocationParameter(method.InnerMethod)})");
                    methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{modelInnerName}>, Observable<Page<{modelInnerName}>>>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Observable<Page<{modelInnerName}>> call(Page<{modelInnerName}> page) {{");
                    methodBuilder.AppendLine($"            return {nextPageMethodName}(page.nextPageLink());");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }})");
                    methodBuilder.AppendLine($"    .flatMapIterable(new Func1<Page<{modelInnerName}>, Iterable<{modelInnerName}>>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Iterable<{modelInnerName}> call(Page<{modelInnerName}> page) {{");
                    methodBuilder.AppendLine($"            return page.items();");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"   }})");
                    methodBuilder.AppendLine($"    .map(new Func1<{modelInnerName}, {modelInterfaceName}>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public {modelInterfaceName} call({modelInnerName} inner) {{");
                    methodBuilder.AppendLine($"            return {standardModel.WrapExistingModelFunc.MethodName}(inner);");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"   }});");
                    methodBuilder.AppendLine($"}}");
                }
            }
            return methodBuilder.ToString();
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

        private static IEnumerable<ParameterJv> RequiredParametersOfMethod(MethodJvaf method)
        {
            return method.LocalParameters.Where(parameter => parameter.IsRequired && !parameter.IsConstant);
        }
    }
}