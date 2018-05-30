// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ListDescriptionBase
    {
        protected readonly string package = Settings.Instance.Namespace.ToLower();

        protected readonly FluentMethodGroup FluentMethodGroup;

        protected ListDescriptionBase(FluentMethodGroup fluentMethodGroup)
        {
            this.FluentMethodGroup = fluentMethodGroup;
        }

        protected string ListRxAsyncMethodImplementation(FluentMethod method, string fluentMethodName, string parameterDecl, string parameterInvoke, bool isGeneralized)
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

        protected string ListSyncMethodImplementation(string convertToPagedListMethodName, FluentMethod method, string fluentMethodName, string parameterDecl, string parameterInvoke, bool isGeneralized)
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

        protected static IEnumerable<ParameterJv> RequiredParametersOfMethod(MethodJvaf method)
        {
            return method.LocalParameters.Where(parameter => parameter.IsRequired && !parameter.IsConstant);
        }
    }
}
