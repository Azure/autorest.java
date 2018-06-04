// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Utilities;
using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The base type for list description of standard model under a resource type scope (e.g. for scope are resource group, subscription, parent resource).
    /// </summary>
    public abstract class ListDescriptionBase
    {
        protected readonly string package = Settings.Instance.Namespace.ToLower();

        protected readonly FluentMethodGroup FluentMethodGroup;

        /// <summary>
        /// Creates ListDescriptionBase.
        /// </summary>
        /// <param name="fluentMethodGroup">The method group containing the standard model whose listing this type describes</param>
        protected ListDescriptionBase(FluentMethodGroup fluentMethodGroup)
        {
            this.FluentMethodGroup = fluentMethodGroup;
        }

        /// <summary>
        /// Provide implementation of async list method.
        /// </summary>
        /// <param name="method">the method representing list apiCall (that wraps inner list method)</param>
        /// <param name="fluentMethodName">the name for the async list method</param>
        /// <param name="parameterDecl">the parameter declaration part of the async list method</param>
        /// <param name="parameterInvoke">the parameter invocation string for invoking inner list method</param>
        /// <param name="isGeneralized">true for implementation in generalized form, false for normal form</param>
        /// <returns>list method implementation</returns>
        protected string ListRxAsyncMethodImplementation(StandardFluentMethod method, string fluentMethodName, string parameterDecl, string parameterInvoke, bool isGeneralized)
        {
            StringBuilder methodBuilder = new StringBuilder();
            //
            StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
            string stdandardModelInnerName = standardModel.InnerModelName;
            string modelInterfaceName = standardModel.JavaInterfaceName;
            string innerClientName = this.FluentMethodGroup.InnerMethodGroupTypeName;
            //
            string methodReturnInnerModelName = method.InnerReturnType.ClassName;
            //
            if (!method.InnerMethod.IsPagingOperation)
            {
                methodBuilder.AppendLine($"@Override");
                methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> {fluentMethodName}Async({parameterDecl}) {{");
                methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                methodBuilder.AppendLine($"    return client.{method.Name}Async({parameterInvoke})");
                if (method.InnerMethod.SimulateAsPagingOperation)
                {
                    methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{methodReturnInnerModelName}>, Observable<{methodReturnInnerModelName}>>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Observable<{methodReturnInnerModelName}> call(Page<{methodReturnInnerModelName}> innerPage) {{");
                    methodBuilder.AppendLine($"            return Observable.from(innerPage.items());");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }})");
                }
                else if (method.InnerMethod.ReturnTypeResponseName.StartsWith("List<"))
                {
                    methodBuilder.AppendLine($"    .flatMap(new Func1<List<{methodReturnInnerModelName}>, Observable<{methodReturnInnerModelName}>>() {{");
                    methodBuilder.AppendLine($"        @Override");
                    methodBuilder.AppendLine($"        public Observable<{methodReturnInnerModelName}> call(List<{methodReturnInnerModelName}> innerList) {{");
                    methodBuilder.AppendLine($"            return Observable.from(innerList);");
                    methodBuilder.AppendLine($"        }}");
                    methodBuilder.AppendLine($"    }})");
                }
                string flatMap = this.FluentMethodGroup.ModelMapper.GetFlatMapToStandardModelFor(methodReturnInnerModelName, isGeneralized);
                if (!string.IsNullOrEmpty(flatMap))
                {
                    methodBuilder.AppendLine($"{flatMap}");
                }
                string wrapExistingModelName;
                if (isGeneralized)
                {
                    wrapExistingModelName = standardModel.WrapExistingModelFunc.GeneralizedMethodName;
                }
                else
                {
                    wrapExistingModelName = standardModel.WrapExistingModelFunc.MethodName;
                }
                methodBuilder.AppendLine($"    .map(new Func1<{stdandardModelInnerName}, {modelInterfaceName}>() {{");
                methodBuilder.AppendLine($"        @Override");
                methodBuilder.AppendLine($"        public {modelInterfaceName} call({stdandardModelInnerName} inner) {{");
                methodBuilder.AppendLine($"            return {wrapExistingModelName}(inner);");
                methodBuilder.AppendLine($"        }}");
                methodBuilder.AppendLine($"    }});");
                methodBuilder.AppendLine($"}}");
            }
            else
            {
                string nextPageMethodName = $"{fluentMethodName}NextInnerPageAsync";

                methodBuilder.AppendLine($"private Observable<Page<{methodReturnInnerModelName}>> {nextPageMethodName}(String nextLink) {{");
                methodBuilder.AppendLine($"    if (nextLink == null) {{");
                methodBuilder.AppendLine($"        Observable.empty();");
                methodBuilder.AppendLine($"    }}");
                methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                methodBuilder.AppendLine($"    return client.{method.Name}NextAsync(nextLink)");
                methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{methodReturnInnerModelName}>, Observable<Page<{methodReturnInnerModelName}>>>() {{");
                methodBuilder.AppendLine($"        @Override");
                methodBuilder.AppendLine($"        public Observable<Page<{methodReturnInnerModelName}>> call(Page<{methodReturnInnerModelName}> page) {{");
                methodBuilder.AppendLine($"            return Observable.just(page).concatWith({nextPageMethodName}(page.nextPageLink()));");
                methodBuilder.AppendLine($"        }}");
                methodBuilder.AppendLine($"    }});");
                methodBuilder.AppendLine($"}}");

                methodBuilder.AppendLine($"@Override");
                methodBuilder.AppendLine($"public Observable<{modelInterfaceName}> {fluentMethodName}Async({parameterDecl}) {{");
                methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                methodBuilder.AppendLine($"    return client.{method.Name}Async({parameterInvoke})");
                methodBuilder.AppendLine($"    .flatMap(new Func1<Page<{methodReturnInnerModelName}>, Observable<Page<{methodReturnInnerModelName}>>>() {{");
                methodBuilder.AppendLine($"        @Override");
                methodBuilder.AppendLine($"        public Observable<Page<{methodReturnInnerModelName}>> call(Page<{methodReturnInnerModelName}> page) {{");
                methodBuilder.AppendLine($"            return {nextPageMethodName}(page.nextPageLink());");
                methodBuilder.AppendLine($"        }}");
                methodBuilder.AppendLine($"    }})");
                methodBuilder.AppendLine($"    .flatMapIterable(new Func1<Page<{methodReturnInnerModelName}>, Iterable<{methodReturnInnerModelName}>>() {{");
                methodBuilder.AppendLine($"        @Override");
                methodBuilder.AppendLine($"        public Iterable<{methodReturnInnerModelName}> call(Page<{methodReturnInnerModelName}> page) {{");
                methodBuilder.AppendLine($"            return page.items();");
                methodBuilder.AppendLine($"        }}");
                methodBuilder.AppendLine($"    }})");
                string flatMap = this.FluentMethodGroup.ModelMapper.GetFlatMapToStandardModelFor(methodReturnInnerModelName, isGeneralized);
                if (flatMap != null)
                {
                    methodBuilder.AppendLine($"{flatMap}");
                }
                string wrapExistingModelName;
                if (isGeneralized)
                {
                    wrapExistingModelName = standardModel.WrapExistingModelFunc.GeneralizedMethodName;
                }
                else
                {
                    wrapExistingModelName = standardModel.WrapExistingModelFunc.MethodName;
                }
                methodBuilder.AppendLine($"    .map(new Func1<{stdandardModelInnerName}, {modelInterfaceName}>() {{");
                methodBuilder.AppendLine($"        @Override");
                methodBuilder.AppendLine($"        public {modelInterfaceName} call({stdandardModelInnerName} inner) {{");
                methodBuilder.AppendLine($"            return {wrapExistingModelName}(inner);");
                methodBuilder.AppendLine($"        }}");
                methodBuilder.AppendLine($"    }});");
                methodBuilder.AppendLine($"}}");
            }
            return methodBuilder.ToString();
        }

        /// <summary>
        /// Provide implementation of sync list method.
        /// </summary>
        /// <param name="convertToPagedListMethodName">the method to use for converting inner paged list to fluent page list</param>
        /// <param name="method">the method representing list apiCall (that wraps inner list method)</param>
        /// <param name="fluentMethodName">the name for the sync list method</param>
        /// <param name="parameterDecl">the parameter declaration part of the sync list method</param>
        /// <param name="parameterInvoke">the parameter invocation string for invoking inner list method</param>
        /// <param name="isGeneralized">true for implementation in generalized form, false for normal form</param>
        /// <returns>list method implementation</returns>
        protected string ListSyncMethodImplementation(string convertToPagedListMethodName, StandardFluentMethod method, string fluentMethodName, string parameterDecl, string parameterInvoke, bool isGeneralized)
        {
            StringBuilder methodBuilder = new StringBuilder();
            //
            StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
            string standardModelInnerName = standardModel.InnerModelName;
            string standardModelInterfaceName = standardModel.JavaInterfaceName;
            //
            string innerReturnTypeName = method.InnerReturnType.ClassName;
            string innerClientName = this.FluentMethodGroup.InnerMethodGroupTypeName;
            // TODO: Check return type is "PagedList" then "converter.convert"
            //       If return type is "List" create a Page, then PagedList from it then "converter.convert"
            //
            methodBuilder.AppendLine("@Override");
            methodBuilder.AppendLine($"public PagedList<{standardModelInterfaceName}> {fluentMethodName}({parameterDecl}) {{");
            methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
            if (innerReturnTypeName.EqualsIgnoreCase(standardModelInnerName))
            {
                methodBuilder.AppendLine($"    return {convertToPagedListMethodName}(client.{method.Name}({parameterInvoke}));");
            }
            else
            {
                string convertor = this.FluentMethodGroup.ModelMapper.GetPagedListConvertor(innerReturnTypeName, $"client.{method.Name}({parameterInvoke})", isGeneralized);
                methodBuilder.AppendLine(convertor);
            }
            methodBuilder.AppendLine($"}}");
            //
            return methodBuilder.ToString();
        }

        /// <summary>
        /// True if resource listing is supported, false otherwise.
        /// </summary>
        public abstract bool SupportsListing { get; }

        /// <summary>
        /// The method to list the resource, null will be returned if the listing is not supported.
        /// </summary>
        public abstract StandardFluentMethod ListMethod { get; }

        /// <summary>
        /// The types that method group interface should extends from inorder to support resource listing in normal form.
        /// </summary>
        public abstract HashSet<string> MethodGroupInterfaceExtendsFrom { get; }

        /// <summary>
        /// The imports needed for method group interface when implementing listing in normal form.
        /// </summary>
        public abstract HashSet<string> ImportsForMethodGroupInterface { get; }

        /// <summary>
        /// The imports needed for method group implementation when implementing listing in normal form.
        /// </summary>
        public abstract HashSet<string> ImportsForMethodGroupImpl { get; }

        /// <summary>
        /// The imports needed for method group interface when it implements listing in generalized form.
        /// </summary>
        public abstract HashSet<string> ImportsForGeneralizedInterface { get; }

        /// <summary>
        /// The imports needed for method group implementation when implementing listing in generalized form.
        /// </summary>
        public abstract HashSet<string> ImportsForGeneralizedImpl { get; }

        /// <summary>
        /// Declaration of the listing method in generalized form.
        /// </summary>
        public abstract string GeneralizedMethodDecl { get; }

        /// <summary>
        /// Implementation of the listing method in generalized form.
        /// </summary>
        public abstract string GeneralizedMethodImpl { get; }

        /// <summary>
        /// Provide the implementation of async list method, in generalized or normal form
        /// </summary>
        /// <param name="isGeneralized">true if the listing impl has to be in generalized form, false for norml form</param>
        /// <returns>list implementation</returns>
        public abstract string ListRxAsyncMethodImplementation(bool isGeneralized);

        /// <summary>
        /// Provide the implementation of sync list method.
        /// </summary>
        /// <param name="convertToPagedListMethodName">the method to use for converting inner paged list to fluent page list</param>
        /// <returns></returns>
        public abstract string ListSyncMethodImplementation(string convertToPagedListMethodName);
    }
}
