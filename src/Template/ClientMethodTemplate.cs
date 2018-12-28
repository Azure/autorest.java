// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Core.Utilities.Collections;
using Newtonsoft.Json.Linq;
using AutoRest.Java.Model;
using System.Text.RegularExpressions;

namespace AutoRest.Java
{
    public class ClientMethodTemplate : IJavaTemplate<ClientMethod, JavaType>
    {
        private JavaSettings settings;
        private TemplateFactory factory;

        public ClientMethodTemplate(TemplateFactory factory)
        {
            this.factory = factory;
            this.settings = factory.Settings;
        }

        public void Write(ClientMethod clientMethod, JavaType typeBlock)
        {
            ProxyMethod restAPIMethod = clientMethod.RestAPIMethod;

            var restAPIMethodReturnBodyClientType = restAPIMethod.ReturnType.ClientType;

            MethodParameter serviceCallbackParameter = new MethodParameter(
                description: "the async ServiceCallback to handle successful and failed responses.",
                isFinal: false,
                wireType: GenericType.ServiceCallback(restAPIMethodReturnBodyClientType),
                name: "serviceCallback",
                isRequired: true,
                isConstant: false,
                fromClient: false,
                defaultValue: null,
                // GetClientMethodParameterAnnotations() is provided false for isRequired so
                // that this parameter won't get marked as NonNull.
                annotations: Enumerable.Empty<ClassType>());

            MethodPageDetails pageDetails = clientMethod.MethodPageDetails;

            GenericType serviceFutureReturnType = GenericType.ServiceFuture(restAPIMethodReturnBodyClientType);
            
            bool isFluentDelete = settings.IsFluent && restAPIMethod.Name.EqualsIgnoreCase("Delete") && clientMethod.MethodRequiredParameters.Count() == 2;

            switch (clientMethod.Type)
            {
                case ClientMethodType.PagingSync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (MethodParameter parameter in clientMethod.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                        if (!string.IsNullOrEmpty(clientMethod.ParametersDeclaration))
                        {
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                        }
                        if (restAPIMethod.UnexpectedResponseExceptionType != null)
                        {
                            comment.Throws(restAPIMethod.UnexpectedResponseExceptionType.ToString(), "thrown if the request is rejected by server");
                        }
                        comment.Throws("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
                        comment.Return(clientMethod.ReturnValue.Description);
                    });
                    typeBlock.PublicMethod(clientMethod.Declaration, function =>
                    {
                        function.Line($"{pageDetails.PageType} response = {clientMethod.PagingAsyncSinglePageMethodName}({clientMethod.ArgumentList}).blockingGet();");
                        function.ReturnAnonymousClass($"new {clientMethod.ReturnValue.Type}(response)", anonymousClass =>
                        {
                            anonymousClass.Annotation("Override");
                            anonymousClass.PublicMethod($"{pageDetails.PageType} nextPage(String {pageDetails.NextLinkParameterName})", subFunction =>
                            {
                                if (restAPIMethod.IsPagingOperation && !restAPIMethod.AutoRestMethod.InputParameterTransformation.IsNullOrEmpty() && !pageDetails.NextMethod.InputParameterTransformation.IsNullOrEmpty())
                                {
                                    if (pageDetails.NextGroupParameterTypeName != clientMethod.GroupedParameterTypeName && (!clientMethod.OnlyRequiredParameters || clientMethod.GroupedParameter.IsRequired))
                                    {
                                        string nextGroupTypeCamelCaseName = pageDetails.NextGroupParameterTypeName.ToCamelCase();
                                        string groupedTypeCamelCaseName = clientMethod.GroupedParameterTypeName.ToCamelCase();

                                        string nextGroupTypeCodeName = CodeNamer.Instance.GetTypeName(pageDetails.NextGroupParameterTypeName) + (settings.IsFluent ? "Inner" : "");

                                        if (!clientMethod.GroupedParameter.IsRequired)
                                        {
                                            subFunction.Line($"{nextGroupTypeCodeName} {nextGroupTypeCamelCaseName} = null;");
                                            subFunction.Line($"if ({groupedTypeCamelCaseName} != null) {{");
                                            subFunction.IncreaseIndent();
                                            subFunction.Line($"{nextGroupTypeCamelCaseName} = new {nextGroupTypeCodeName}();");
                                        }
                                        else
                                        {
                                            subFunction.Line($"{nextGroupTypeCodeName} {nextGroupTypeCamelCaseName} = new {nextGroupTypeCodeName}();");
                                        }

                                        foreach (ParameterJv outputParameter in pageDetails.NextMethod.InputParameterTransformation.Select(transformation => transformation.OutputParameter))
                                        {
                                            string outputParameterName;
                                            if (!outputParameter.IsClientProperty)
                                            {
                                                outputParameterName = outputParameter.Name;
                                            }
                                            else
                                            {
                                                string caller = (outputParameter.Method != null && outputParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                                                string clientPropertyName = outputParameter.ClientProperty?.Name?.ToString();
                                                if (!string.IsNullOrEmpty(clientPropertyName))
                                                {
                                                    CodeNamer codeNamer = CodeNamer.Instance;
                                                    clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                                                }
                                                outputParameterName = $"{caller}.{clientPropertyName}()";
                                            }
                                            subFunction.Line($"{nextGroupTypeCamelCaseName}.with{outputParameterName.ToPascalCase()}({groupedTypeCamelCaseName}.{outputParameterName.ToCamelCase()}());");
                                        }

                                        if (!clientMethod.GroupedParameter.IsRequired)
                                        {
                                            subFunction.DecreaseIndent();
                                            subFunction.Line("}");
                                        }
                                    }
                                }

                                subFunction.Return($"{pageDetails.NextMethodInvocation + "SinglePageAsync"}({pageDetails.NextMethodParameterInvocation}).blockingGet()");
                            });
                        });
                    });
                    break;

                case ClientMethodType.PagingAsync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (MethodParameter parameter in clientMethod.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                        if (!string.IsNullOrEmpty(clientMethod.ParametersDeclaration))
                        {
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                        }
                        comment.Return(clientMethod.ReturnValue.Description);
                    });
                    typeBlock.PublicMethod(clientMethod.Declaration, function =>
                    {
                        function.Line($"return {clientMethod.PagingAsyncSinglePageMethodName}({clientMethod.ArgumentList})");
                        function.Indent(() =>
                        {
                            function.Line(".toObservable()");
                            function.Text($".concatMap(");
                            function.Lambda(pageDetails.PageType.ToString(), "page", lambda =>
                            {
                                lambda.Line($"String {pageDetails.NextLinkVariableName} = page.nextPageLink();");
                                lambda.If($"{pageDetails.NextLinkVariableName} == null", ifBlock =>
                                {
                                    ifBlock.Return("Observable.just(page)");
                                });

                                if (clientMethod.RestAPIMethod.IsPagingOperation && !clientMethod.AutoRestMethod.InputParameterTransformation.IsNullOrEmpty() && !pageDetails.NextMethod.InputParameterTransformation.IsNullOrEmpty())
                                {
                                    if (pageDetails.NextGroupParameterTypeName != clientMethod.GroupedParameterTypeName && (!clientMethod.OnlyRequiredParameters || clientMethod.GroupedParameter.IsRequired))
                                    {
                                        string nextGroupTypeCamelCaseName = pageDetails.NextGroupParameterTypeName.ToCamelCase();
                                        string groupedTypeCamelCaseName = clientMethod.GroupedParameterTypeName.ToCamelCase();

                                        string nextGroupTypeCodeName = CodeNamer.Instance.GetTypeName(pageDetails.NextGroupParameterTypeName) + (settings.IsFluent ? "Inner" : "");

                                        if (!clientMethod.GroupedParameter.IsRequired)
                                        {
                                            lambda.Line($"{nextGroupTypeCodeName} {nextGroupTypeCamelCaseName} = null;");
                                            lambda.Line($"if ({groupedTypeCamelCaseName} != null) {{");
                                            lambda.IncreaseIndent();
                                            lambda.Line($"{nextGroupTypeCamelCaseName} = new {nextGroupTypeCodeName}();");
                                        }
                                        else
                                        {
                                            lambda.Line($"{nextGroupTypeCodeName} {nextGroupTypeCamelCaseName} = new {nextGroupTypeCodeName}();");
                                        }

                                        foreach (ParameterJv outputParameter in pageDetails.NextMethod.InputParameterTransformation.Select(transformation => transformation.OutputParameter))
                                        {
                                            string outputParameterName;
                                            if (!outputParameter.IsClientProperty)
                                            {
                                                outputParameterName = outputParameter.Name;
                                            }
                                            else
                                            {
                                                string caller = (outputParameter.Method != null && outputParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                                                string clientPropertyName = outputParameter.ClientProperty?.Name?.ToString();
                                                if (!string.IsNullOrEmpty(clientPropertyName))
                                                {
                                                    CodeNamer codeNamer = CodeNamer.Instance;
                                                    clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                                                }
                                                outputParameterName = $"{caller}.{clientPropertyName}()";
                                            }
                                            lambda.Line($"{nextGroupTypeCamelCaseName}.with{outputParameterName.ToPascalCase()}({groupedTypeCamelCaseName}.{outputParameterName.ToCamelCase()}());");
                                        }

                                        if (!clientMethod.GroupedParameter.IsRequired)
                                        {
                                            lambda.DecreaseIndent();
                                            lambda.Line("}");
                                        }
                                    }
                                }

                                lambda.Return($"Observable.just(page).concatWith({pageDetails.NextMethodInvocation}Async({pageDetails.NextMethodParameterInvocation}))");
                            });
                            function.Line(");");
                        });
                    });
                    break;

                case ClientMethodType.PagingAsyncSinglePage:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (MethodParameter parameter in clientMethod.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                        if (!string.IsNullOrEmpty(clientMethod.ParametersDeclaration))
                        {
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                        }
                        comment.Return(clientMethod.ReturnValue.Description);
                    });
                    typeBlock.PublicMethod(clientMethod.Declaration, function =>
                    {
                        AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions);
                        AddValidations(function, clientMethod.ExpressionsToValidate);
                        AddOptionalAndConstantVariables(function, clientMethod, clientMethod.MethodParameters, settings);
                        ApplyParameterTransformations(function, clientMethod, settings);
                        ConvertClientTypesToWireTypes(function, restAPIMethod.Parameters, clientMethod.ClientReference, settings);

                        if (restAPIMethod.IsPagingNextOperation)
                        {
                            string methodUrl = restAPIMethod.UrlPath;
                            Regex regex = new Regex("{\\w+}");

                            string substitutedMethodUrl = regex.Replace(methodUrl, "%s");

                            IEnumerable<Parameter> retrofitParameters = restAPIMethod.AutoRestMethod.LogicalParameters.Where(p => p.Location != ParameterLocation.None);
                            StringBuilder builder = new StringBuilder($"String.format(\"{substitutedMethodUrl}\"");
                            foreach (Match match in regex.Matches(methodUrl))
                            {
                                string serializedNameWithBrackets = match.Value;
                                string serializedName = serializedNameWithBrackets.Substring(1, serializedNameWithBrackets.Length - 2);
                                Parameter parameter = retrofitParameters.First(p => p.SerializedName == serializedName);

                                string parameterName;
                                if (!parameter.IsClientProperty)
                                {
                                    parameterName = parameter.Name;
                                }
                                else
                                {
                                    string caller = (parameter.Method != null && parameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                                    string clientPropertyName = parameter.ClientProperty?.Name?.ToString();
                                    if (!string.IsNullOrEmpty(clientPropertyName))
                                    {
                                        CodeNamer codeNamer = CodeNamer.Instance;
                                        clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                                    }
                                    parameterName = $"{caller}.{clientPropertyName}()";
                                }

                                IModelTypeJv parameterModelType = (IModelTypeJv) parameter.ModelType;
                                if (parameterModelType != null && !parameter.IsNullable())
                                {
                                    if (parameterModelType is PrimaryTypeJv parameterModelPrimaryType)
                                    {
                                        PrimaryTypeJv nonNullableParameterModelPrimaryType = DependencyInjection.New<PrimaryTypeJv>(parameterModelPrimaryType.KnownPrimaryType);
                                        nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                        nonNullableParameterModelPrimaryType.IsNullable = false;

                                        parameterModelType = nonNullableParameterModelPrimaryType;
                                    }
                                }

                                IModelTypeJv parameterClientType = parameterModelType.ClientType;

                                IModelTypeJv parameterWireType;
                                if (parameterModelType.IsPrimaryType(KnownPrimaryType.Stream))
                                {
                                    parameterWireType = parameterClientType;
                                }
                                else if (!parameterModelType.IsPrimaryType(KnownPrimaryType.Base64Url) &&
                                    parameter.Location != ParameterLocation.Body &&
                                    parameter.Location != ParameterLocation.FormData &&
                                    ((parameterClientType is PrimaryTypeJv primaryType && primaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterClientType is SequenceTypeJv))
                                {
                                    parameterWireType = DependencyInjection.New<PrimaryTypeJv>(KnownPrimaryType.String);
                                }
                                else
                                {
                                    parameterWireType = parameterModelType;
                                }

                                string parameterWireName = !parameterClientType.StructurallyEquals(parameterWireType) ? $"{parameterName.ToCamelCase()}Converted" : parameterName;
                                builder.Append(", " + parameterWireName);
                            }
                            builder.Append(")");

                            function.Line($"String nextUrl = {builder.ToString()};");
                        }

                        IType returnValueTypeArgumentType = ((GenericType)restAPIMethod.ReturnType).TypeArguments.Single();

                        string restAPIMethodArgumentList = restAPIMethod.GetArgumentList(settings);

                        function.Line($"return service.{restAPIMethod.Name}({restAPIMethodArgumentList})");
                        function.Indent(() =>
                        {
                            function.Text(".map(");
                            function.Lambda(returnValueTypeArgumentType.ToString(), "res", "res.body()");
                            function.Line(");");
                        });
                    });
                    break;

                case ClientMethodType.SimulatedPagingSync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (MethodParameter parameter in clientMethod.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                        comment.Return(clientMethod.ReturnValue.Description);
                    });
                    typeBlock.PublicMethod(clientMethod.Declaration, function =>
                    {
                        function.Line($"{pageDetails.PageImplType} page = new {pageDetails.PageImplType}<>();");
                        function.Line($"page.setItems({clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).single().items());");
                        function.Line("page.setNextPageLink(null);");
                        function.ReturnAnonymousClass($"new {clientMethod.ReturnValue.Type}(page)", anonymousClass =>
                        {
                            anonymousClass.Annotation("Override");
                            anonymousClass.PublicMethod($"{pageDetails.PageType} nextPage(String nextPageLink)", subFunction =>
                            {
                                subFunction.Return("null");
                            });
                        });
                    });
                    break;

                case ClientMethodType.SimulatedPagingAsync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (MethodParameter parameter in clientMethod.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                        if (clientMethod.RequiredNullableParameterExpressions.Any() || clientMethod.ExpressionsToValidate.Any())
                        {
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                        }
                        comment.Return(clientMethod.ReturnValue.Description);
                    });
                    typeBlock.PublicMethod(clientMethod.Declaration, function =>
                    {
                        AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions);
                        AddValidations(function, clientMethod.ExpressionsToValidate);
                        AddOptionalAndConstantVariables(function, clientMethod, clientMethod.MethodParameters, settings);
                        ApplyParameterTransformations(function, clientMethod, settings);
                        ConvertClientTypesToWireTypes(function, restAPIMethod.Parameters, clientMethod.ClientReference, settings);

                        IType returnValueTypeArgumentType = ((GenericType)restAPIMethod.ReturnType).TypeArguments.Single();
                        string restAPIMethodArgumentList = restAPIMethod.GetArgumentList(settings);
                        function.Line($"return service.{clientMethod.RestAPIMethod.Name}({restAPIMethodArgumentList})");
                        function.Indent(() =>
                        {
                            function.Text(".map(");
                            function.Lambda(returnValueTypeArgumentType.ToString(), "res", "res.body()");
                            function.Line(")");
                            function.Line(".toObservable();");
                        });
                    });
                    break;

                case ClientMethodType.LongRunningSync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (MethodParameter parameter in clientMethod.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                        if (clientMethod.RequiredNullableParameterExpressions.Any())
                        {
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                        }
                        if (restAPIMethod.UnexpectedResponseExceptionType != null)
                        {
                            comment.Throws(restAPIMethod.UnexpectedResponseExceptionType.ToString(), "thrown if the request is rejected by server");
                        }
                        comment.Throws("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
                        comment.Return(clientMethod.ReturnValue.Description);
                    });
                    typeBlock.PublicMethod(clientMethod.Declaration, function =>
                    {
                        if (clientMethod.ReturnValue.Type == PrimitiveType.Void)
                        {
                            function.Line($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).blockingLast();");
                        }
                        else
                        {
                            function.Return($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).blockingLast().result()");
                        }
                    });
                    break;

                case ClientMethodType.LongRunningAsyncServiceCallback:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (MethodParameter parameter in clientMethod.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                        comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                        comment.Return(clientMethod.ReturnValue.Description);
                    });
                    typeBlock.PublicMethod(clientMethod.Declaration, function =>
                    {
                        function.Return($"ServiceFutureUtil.fromLRO({clientMethod.SimpleAsyncMethodName}({string.Join(", ", clientMethod.Parameters.SkipLast(1).Select(parameter => parameter.Name))}), {serviceCallbackParameter.Name})");
                    });
                    break;

                case ClientMethodType.Resumable:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (MethodParameter parameter in clientMethod.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                        if (clientMethod.RequiredNullableParameterExpressions.Any() || clientMethod.ExpressionsToValidate.Any())
                        {
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                        }
                        comment.Return(clientMethod.ReturnValue.Description);
                    });
                    typeBlock.PublicMethod(clientMethod.Declaration, function =>
                    {
                        var parameter = restAPIMethod.Parameters.First();
                        AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions);
                        function.Return($"service.{restAPIMethod.Name}({parameter.Name})");
                    });
                    break;

                case ClientMethodType.LongRunningAsync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (MethodParameter parameter in clientMethod.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                        if (clientMethod.RequiredNullableParameterExpressions.Any() || clientMethod.ExpressionsToValidate.Any())
                        {
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                        }
                        comment.Return(clientMethod.ReturnValue.Description);
                    });
                    typeBlock.PublicMethod(clientMethod.Declaration, function =>
                    {
                        AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions);
                        AddValidations(function, clientMethod.ExpressionsToValidate);
                        AddOptionalAndConstantVariables(function, clientMethod, clientMethod.MethodParameters, settings);
                        ApplyParameterTransformations(function, clientMethod, settings);
                        ConvertClientTypesToWireTypes(function, restAPIMethod.Parameters, clientMethod.ClientReference, settings);
                        string restAPIMethodArgumentList = restAPIMethod.GetArgumentList(settings);
                        function.Return($"service.{restAPIMethod.Name}({restAPIMethodArgumentList})");
                    });
                    break;

                case ClientMethodType.SimpleSync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (MethodParameter parameter in clientMethod.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                        if (!string.IsNullOrEmpty(clientMethod.ParametersDeclaration))
                        {
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                        }
                        if (restAPIMethod.UnexpectedResponseExceptionType != null)
                        {
                            comment.Throws(restAPIMethod.UnexpectedResponseExceptionType.ToString(), "thrown if the request is rejected by server");
                        }
                        comment.Throws("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
                        comment.Return(clientMethod.ReturnValue.Description);
                    });
                    typeBlock.PublicMethod(clientMethod.Declaration, function =>
                    {
                        if (clientMethod.ReturnValue.Type != PrimitiveType.Void)
                        {
                            function.Return($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).blockingGet()");
                        }
                        else if (isFluentDelete)
                        {
                            function.Line($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).blockingGet();");
                        }
                        else
                        {
                            function.Line($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).blockingAwait();");
                        }
                    });
                    break;

                case ClientMethodType.SimpleAsyncServiceCallback:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (MethodParameter parameter in clientMethod.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                        comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                        comment.Return(clientMethod.ReturnValue.Description);
                    });
                    typeBlock.PublicMethod(clientMethod.Declaration, function =>
                    {
                        function.Return($"ServiceFuture.fromBody({clientMethod.Name}({string.Join(", ", clientMethod.Parameters.SkipLast(1).Select(parameter => parameter.Name))}), {serviceCallbackParameter.Name})");
                    });
                    break;

                case ClientMethodType.SimpleAsyncRestResponse:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (MethodParameter parameter in clientMethod.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                        if (!string.IsNullOrEmpty(clientMethod.ParametersDeclaration))
                        {
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                        }
                        comment.Return(clientMethod.ReturnValue.Description);
                    });
                    typeBlock.PublicMethod(clientMethod.Declaration, function =>
                    {
                        AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions);
                        AddValidations(function, clientMethod.ExpressionsToValidate);
                        AddOptionalAndConstantVariables(function, clientMethod, clientMethod.MethodParameters, settings);
                        ApplyParameterTransformations(function, clientMethod, settings);
                        ConvertClientTypesToWireTypes(function, restAPIMethod.Parameters, clientMethod.ClientReference, settings);
                        string restAPIMethodArgumentList = restAPIMethod.GetArgumentList(settings);
                        function.Return($"service.{restAPIMethod.Name}({restAPIMethodArgumentList})");
                    });
                    break;

                case ClientMethodType.SimpleAsync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (MethodParameter parameter in clientMethod.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                        if (!string.IsNullOrEmpty(clientMethod.ParametersDeclaration))
                        {
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                        }
                        comment.Return(clientMethod.ReturnValue.Description);
                    });
                    typeBlock.PublicMethod(clientMethod.Declaration, function =>
                    {
                        function.Line($"return {clientMethod.RestAPIMethod.SimpleAsyncRestResponseMethodName}({clientMethod.ArgumentList})");
                        function.Indent(() =>
                        {
                            GenericType restAPIMethodClientReturnType = (GenericType)restAPIMethod.ReturnType.ClientType;
                            IType returnValueTypeArgumentClientType = restAPIMethodClientReturnType.TypeArguments.Single();
                            if (restAPIMethodReturnBodyClientType != PrimitiveType.Void &&
                                !GenericType.Single(ClassType.VoidResponse).Equals(restAPIMethodReturnBodyClientType) &&
                                restAPIMethod.AutoRestMethod.ReturnType.Body != null)
                            {
                                function.Text($".flatMapMaybe(");
                                function.Lambda(returnValueTypeArgumentClientType.ToString(), "res", "res.body() == null ? Maybe.empty() : Maybe.just(res.body())");
                                function.Line(");");
                            }
                            else if (isFluentDelete)
                            {
                                function.Text($".flatMapMaybe(");
                                function.Lambda(returnValueTypeArgumentClientType.ToString(), "res", "Maybe.empty()");
                                function.Line(");");
                            }
                            else
                            {
                                function.Line(".toCompletable();");
                            }
                        });
                    });
                    break;

                default:
                    throw new ArgumentException($"There is no method implementation for {nameof(ClientMethodType)}.{clientMethod.Type}.");
            }
        }

        

        private static void AddNullChecks(JavaBlock function, IEnumerable<string> expressionsToCheck)
        {
            foreach (string expressionToCheck in expressionsToCheck)
            {
                function.If($"{expressionToCheck} == null", ifBlock =>
                {
                    ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {expressionToCheck} is required and cannot be null.\");");
                });
            }
        }

        private static void AddValidations(JavaBlock function, IEnumerable<string> expressionsToValidate)
        {
            foreach (string expressionToValidate in expressionsToValidate)
            {
                function.Line($"Validator.validate({expressionToValidate});");
            }
        }

        private static void AddOptionalAndConstantVariables(JavaBlock function, ClientMethod clientMethod, IEnumerable<MethodParameter> autoRestClientMethodAndConstantParameters, JavaSettings settings)
        {
            foreach (MethodParameter parameter in autoRestClientMethodAndConstantParameters)
            {
                if ((clientMethod.OnlyRequiredParameters && !parameter.IsRequired) || parameter.IsConstant)
                {
                    IType parameterClientType = parameter.ClientType.ClientType;
                    string defaultValue = parameterClientType.DefaultValueExpression(parameter.DefaultValue);
                    function.Line($"final {parameterClientType} {parameter.Name} = {defaultValue ?? "null"};");
                }
            }
        }

        private static void ApplyParameterTransformations(JavaBlock function, ClientMethod clientMethod, JavaSettings settings)
        {
            MethodJv autoRestMethod = clientMethod.AutoRestMethod;

            foreach (ParameterTransformation transformation in autoRestMethod.InputParameterTransformation)
            {
                Parameter transformationOutputParameter = transformation.OutputParameter;
                IModelTypeJv transformationOutputParameterModelType = (IModelTypeJv) transformationOutputParameter.ModelType;
                if (transformationOutputParameterModelType != null && !transformationOutputParameter.IsNullable() && transformationOutputParameterModelType is PrimaryTypeJv transformationOutputParameterModelPrimaryType)
                {
                    PrimaryTypeJv transformationOutputParameterModelNonNullablePrimaryType = DependencyInjection.New<PrimaryTypeJv>(transformationOutputParameterModelPrimaryType.KnownPrimaryType);
                    transformationOutputParameterModelNonNullablePrimaryType.Format = transformationOutputParameterModelPrimaryType.Format;
                    transformationOutputParameterModelNonNullablePrimaryType.IsNullable = false;

                    transformationOutputParameterModelType = transformationOutputParameterModelNonNullablePrimaryType;
                }
                IModelTypeJv transformationOutputParameterClientType = transformationOutputParameterModelType.ClientType;

                string outParamName;
                if (!transformationOutputParameter.IsClientProperty)
                {
                    outParamName = transformationOutputParameter.Name;
                }
                else
                {
                    string caller = (transformationOutputParameter.Method != null && transformationOutputParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                    string clientPropertyName = transformationOutputParameter.ClientProperty?.Name?.ToString();
                    if (!string.IsNullOrEmpty(clientPropertyName))
                    {
                        CodeNamer codeNamer = CodeNamer.Instance;
                        clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                    }
                    outParamName = $"{caller}.{clientPropertyName}()";
                }
                while (autoRestMethod.Parameters.Any((Parameter parameter) =>
                {
                    string parameterName;
                    if (!parameter.IsClientProperty)
                    {
                        parameterName = parameter.Name;
                    }
                    else
                    {
                        string caller = (parameter.Method != null && parameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                        string clientPropertyName = parameter.ClientProperty?.Name?.ToString();
                        if (!string.IsNullOrEmpty(clientPropertyName))
                        {
                            CodeNamer codeNamer = CodeNamer.Instance;
                            clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                        }
                        parameterName = $"{caller}.{clientPropertyName}()";
                    }
                    return parameterName == outParamName;
                }))
                {
                    outParamName += "1";
                }

                transformationOutputParameter.Name = outParamName;

                string transformationOutputParameterClientParameterVariantTypeName = transformationOutputParameterClientType.ClientType.ModelTypeName;

                IEnumerable<ParameterMapping> transformationParameterMappings = transformation.ParameterMappings;
                string nullCheck = string.Join(" || ", transformationParameterMappings.Where(m => !m.InputParameter.IsRequired)
                    .Select((ParameterMapping m) =>
                    {
                        Parameter parameter = m.InputParameter;

                        string parameterName;
                        if (!parameter.IsClientProperty)
                        {
                            parameterName = parameter.Name;
                        }
                        else
                        {
                            string caller = (parameter.Method != null && parameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                            string clientPropertyName = parameter.ClientProperty?.Name?.ToString();
                            if (!string.IsNullOrEmpty(clientPropertyName))
                            {
                                CodeNamer codeNamer = CodeNamer.Instance;
                                clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                            }
                            parameterName = $"{caller}.{clientPropertyName}()";
                        }

                        return parameterName + " != null";
                    }));
                bool conditionalAssignment = !string.IsNullOrEmpty(nullCheck) && !transformationOutputParameter.IsRequired && !clientMethod.OnlyRequiredParameters;
                if (conditionalAssignment)
                {
                    function.Line("{0} {1} = null;",
                        transformationOutputParameterClientParameterVariantTypeName,
                        outParamName);
                    function.Line($"if ({nullCheck}) {{");
                    function.IncreaseIndent();
                }

                CompositeTypeJv transformationOutputParameterModelCompositeType = transformationOutputParameterModelType as CompositeTypeJv;
                if (transformationOutputParameterModelCompositeType != null && transformationParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty)))
                {
                    string transformationOutputParameterModelCompositeTypeName = transformationOutputParameterModelCompositeType.Name.ToString();
                    if (settings.IsFluent && !string.IsNullOrEmpty(transformationOutputParameterModelCompositeTypeName) && transformationOutputParameterModelCompositeType.IsInnerModel)
                    {
                        transformationOutputParameterModelCompositeTypeName += "Inner";
                    }

                    function.Line("{0}{1} = new {2}();",
                        !conditionalAssignment ? transformationOutputParameterClientParameterVariantTypeName + " " : "",
                        outParamName,
                        transformationOutputParameterModelCompositeTypeName);
                }

                foreach (ParameterMapping mapping in transformationParameterMappings)
                {
                    string inputPath;
                    if (!mapping.InputParameter.IsClientProperty)
                    {
                        inputPath = mapping.InputParameter.Name;
                    }
                    else
                    {
                        string caller = (mapping.InputParameter.Method != null && mapping.InputParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                        string clientPropertyName = mapping.InputParameter.ClientProperty?.Name?.ToString();
                        if (!string.IsNullOrEmpty(clientPropertyName))
                        {
                            CodeNamer codeNamer = CodeNamer.Instance;
                            clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                        }
                        inputPath = $"{caller}.{clientPropertyName}()";
                    }

                    if (mapping.InputParameterProperty != null)
                    {
                        inputPath += "." + CodeNamer.Instance.CamelCase(mapping.InputParameterProperty) + "()";
                    }
                    if (clientMethod.OnlyRequiredParameters && !mapping.InputParameter.IsRequired)
                    {
                        inputPath = "null";
                    }

                    string getMapping;
                    if (mapping.OutputParameterProperty != null)
                    {
                        getMapping = $".with{CodeNamer.Instance.PascalCase(mapping.OutputParameterProperty)}({inputPath})";
                    }
                    else
                    {
                        getMapping = $" = {inputPath}";
                    }

                    function.Line("{0}{1}{2};",
                        !conditionalAssignment && transformationOutputParameterModelCompositeType == null ? transformationOutputParameterClientParameterVariantTypeName + " " : "",
                        outParamName,
                        getMapping);
                }

                if (conditionalAssignment)
                {
                    function.DecreaseIndent();
                    function.Line("}");
                }
            }
        }

        private static void ConvertClientTypesToWireTypes(JavaBlock function, IEnumerable<ProxyMethodParameter> autoRestMethodRetrofitParameters, string methodClientReference, JavaSettings settings)
        {
            foreach (ProxyMethodParameter parameter in autoRestMethodRetrofitParameters)
            {
                IType parameterWireType = parameter.Type;;
                if (parameter.IsNullable)
                {
                    parameterWireType = parameterWireType.AsNullable();
                }
                IType parameterClientType = parameterWireType.ClientType;

                if (parameterClientType != ClassType.Base64Url &&
                    parameter.RequestParameterLocation != RequestParameterLocation.Body &&
                    parameter.RequestParameterLocation != RequestParameterLocation.FormData &&
                    (parameterClientType == ArrayType.ByteArray) || parameterClientType is ListType)
                {
                    parameterWireType = ClassType.String;
                }

                if (parameterWireType != parameterClientType)
                {
                    string parameterName = parameter.ParameterReference;
                    string parameterWireName = parameter.ParameterReferenceConverted;

                    bool addedConversion = false;
                    RequestParameterLocation parameterLocation = parameter.RequestParameterLocation;
                    if (parameterLocation != RequestParameterLocation.Body &&
                        parameterLocation != RequestParameterLocation.FormData &&
                        (parameter.Type is ArrayType || parameter.Type is ListType))
                    {
                        string parameterWireTypeName = ((IModelTypeJv)parameterWireType).ModelTypeName;

                        if (parameterClientType == ArrayType.ByteArray)
                        {
                            if (parameterWireType == ClassType.String)
                            {
                                function.Line($"{parameterWireTypeName} {parameterWireName} = Base64Util.encodeToString({parameterName});");
                            }
                            else
                            {
                                function.Line($"{parameterWireTypeName} {parameterWireName} = Base64Url.encode({parameterName});");
                            }
                            addedConversion = true;
                        }
                        else if (parameterClientType is ListType)
                        {
                            function.Line("{0} {1} = {2}.serializerAdapter().serializeList({3}, CollectionFormat.{4});",
                                parameterWireTypeName,
                                parameterWireName,
                                methodClientReference,
                                parameterName,
                                parameter.CollectionFormat.ToString().ToUpperInvariant());
                            addedConversion = true;
                        }
                    }

                    if (!addedConversion)
                    {
                        function.Line(parameter.ConvertFromClientType(parameterName, parameterWireName));
                    }
                }
            }
        }
    }
}