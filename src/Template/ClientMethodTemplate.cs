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
    /// <summary>
    /// Writes a ClientMethod to a JavaType block.
    /// </summary>
    public class ClientMethodTemplate : IJavaTemplate<ClientMethod, JavaType>
    {
        private static ClientMethodTemplate _instance = new ClientMethodTemplate();
        public static ClientMethodTemplate Instance => _instance;

        private ClientMethodTemplate()
        {
        }

        public void Write(ClientMethod clientMethod, JavaType typeBlock)
        {
            var settings = JavaSettings.Instance;

            ProxyMethod restAPIMethod = clientMethod.ProxyMethod;
            var restAPIMethodReturnBodyClientType = restAPIMethod.ReturnType.ClientType;

            MethodPageDetails pageDetails = clientMethod.MethodPageDetails;
            
            
            bool isFluentDelete = settings.IsFluent && restAPIMethod.Name.EqualsIgnoreCase("Delete") && clientMethod.MethodRequiredParameters.Count() == 2;

            switch (clientMethod.Type)
            {
                case ClientMethodType.PagingSync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (ClientMethodParameter parameter in clientMethod.Parameters)
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
                        function.Line($"{pageDetails.PageType} response = {clientMethod.PagingAsyncSinglePageMethodName}({clientMethod.ArgumentList}).block();");
                        function.ReturnAnonymousClass($"new {clientMethod.ReturnValue.Type}(response)", anonymousClass =>
                        {
                            anonymousClass.Annotation("Override");
                            anonymousClass.PublicMethod($"{pageDetails.PageType} nextPage(String {pageDetails.NextLinkParameterName})", subFunction =>
                            {
                                if (!clientMethod.MethodTransformationDetails.IsNullOrEmpty() && !pageDetails.NextMethod.InputParameterTransformation.IsNullOrEmpty())
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

                                subFunction.Return($"{pageDetails.NextMethodInvocation + "SinglePageAsync"}({pageDetails.NextMethodParameterInvocation}).block()");
                            });
                        });
                    });
                    break;

                case ClientMethodType.PagingAsync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (ClientMethodParameter parameter in clientMethod.Parameters)
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
                            function.Line(".repeat(1)");
                            function.Text($".concatMap(");
                            function.Lambda(pageDetails.PageType.ToString(), "page", lambda =>
                            {
                                lambda.Line($"String {pageDetails.NextLinkVariableName} = page.nextPageLink();");
                                lambda.If($"{pageDetails.NextLinkVariableName} == null", ifBlock =>
                                {
                                    ifBlock.Return("Flux.just(page)");
                                });

                                if (!clientMethod.MethodTransformationDetails.IsNullOrEmpty() && !pageDetails.NextMethod.InputParameterTransformation.IsNullOrEmpty())
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

                                lambda.Return($"Flux.just(page).concatWith({pageDetails.NextMethodInvocation}Async({pageDetails.NextMethodParameterInvocation}))");
                            });
                            function.Line(");");
                        });
                    });
                    break;

                case ClientMethodType.PagingAsyncSinglePage:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (ClientMethodParameter parameter in clientMethod.Parameters)
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
                        AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions, settings);
                        AddValidations(function, clientMethod.ExpressionsToValidate, settings);
                        AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.Parameters, settings);
                        ApplyParameterTransformations(function, clientMethod, settings);
                        ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.Parameters, clientMethod.ClientReference, settings);

                        if (pageDetails.IsNextMethod)
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

                        string restAPIMethodArgumentList = String.Join(", ", clientMethod.GetProxyMethodArguments(settings));

                        function.Line($"return service.{restAPIMethod.Name}({restAPIMethodArgumentList})");
                        function.Indent(() =>
                        {
                            function.Text(".map(");
                            function.Lambda(returnValueTypeArgumentType.ToString(), "res", "res.value()");
                            function.Line(");");
                        });
                    });
                    break;

                case ClientMethodType.SimulatedPagingSync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (ClientMethodParameter parameter in clientMethod.Parameters)
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
                        foreach (ClientMethodParameter parameter in clientMethod.Parameters)
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
                        AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions, settings);
                        AddValidations(function, clientMethod.ExpressionsToValidate, settings);
                        AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.Parameters, settings);
                        ApplyParameterTransformations(function, clientMethod, settings);
                        ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.Parameters, clientMethod.ClientReference, settings);

                        IType returnValueTypeArgumentType = ((GenericType)restAPIMethod.ReturnType).TypeArguments.Single();
                        string restAPIMethodArgumentList = String.Join(", ", clientMethod.GetProxyMethodArguments(settings));
                        function.Line($"return service.{clientMethod.ProxyMethod.Name}({restAPIMethodArgumentList})");
                        function.Indent(() =>
                        {
                            function.Text(".map(");
                            function.Lambda(returnValueTypeArgumentType.ToString(), "res", "res.value()");
                            function.Line(")");
                            function.Line(".repeat(1);");
                        });
                    });
                    break;

                case ClientMethodType.LongRunningSync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (ClientMethodParameter parameter in clientMethod.Parameters)
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
                            function.Line($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).blockLast();");
                        }
                        else
                        {
                            function.Return($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).blockLast().result()");
                        }
                    });
                    break;


                case ClientMethodType.Resumable:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (ClientMethodParameter parameter in clientMethod.Parameters)
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
                        AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions, settings);
                        function.Return($"service.{restAPIMethod.Name}({parameter.Name})");
                    });
                    break;

                case ClientMethodType.LongRunningAsync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (ClientMethodParameter parameter in clientMethod.Parameters)
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
                        AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions, settings);
                        AddValidations(function, clientMethod.ExpressionsToValidate, settings);
                        AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.Parameters, settings);
                        ApplyParameterTransformations(function, clientMethod, settings);
                        ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.Parameters, clientMethod.ClientReference, settings);
                        string restAPIMethodArgumentList = String.Join(", ", clientMethod.GetProxyMethodArguments(settings));
                        function.Return($"service.{restAPIMethod.Name}({restAPIMethodArgumentList})");
                    });
                    break;

                case ClientMethodType.SimpleSync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (ClientMethodParameter parameter in clientMethod.Parameters)
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
                            function.Return($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).block()");
                        }
                        else if (isFluentDelete)
                        {
                            function.Line($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).block();");
                        }
                        else
                        {
                            function.Line($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).block();");
                        }
                    });
                    break;


                case ClientMethodType.SimpleAsyncRestResponse:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (ClientMethodParameter parameter in clientMethod.Parameters)
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
                        AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions, settings);
                        AddValidations(function, clientMethod.ExpressionsToValidate, settings);
                        AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.Parameters, settings);
                        ApplyParameterTransformations(function, clientMethod, settings);
                        ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.Parameters, clientMethod.ClientReference, settings);
                        string restAPIMethodArgumentList = String.Join(", ", clientMethod.GetProxyMethodArguments(settings));
                        function.Return($"service.{restAPIMethod.Name}({restAPIMethodArgumentList})");
                    });
                    break;

                case ClientMethodType.SimpleAsync:
                    typeBlock.JavadocComment(comment =>
                    {
                        comment.Description(clientMethod.Description);
                        foreach (ClientMethodParameter parameter in clientMethod.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                        if (!string.IsNullOrEmpty(clientMethod.ParametersDeclaration))
                        {
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                        }
                        comment.Return(clientMethod.ReturnValue.Description);
                    });
                    typeBlock.PublicMethod(clientMethod.Declaration, (Action<JavaBlock>)(function =>
                    {
                        function.Line($"return {clientMethod.ProxyMethod.SimpleAsyncRestResponseMethodName}({clientMethod.ArgumentList})");
                        function.Indent((Action)(() =>
                        {
                            GenericType restAPIMethodClientReturnType = (GenericType)restAPIMethod.ReturnType.ClientType;
                            IType returnValueTypeArgumentClientType = restAPIMethodClientReturnType.TypeArguments.Single();
                            if (!GenericType.Mono(ClassType.Void).Equals(clientMethod.ReturnValue.Type) &&
                                !GenericType.Flux(ClassType.Void).Equals(clientMethod.ReturnValue.Type))
                            {
                                function.Text($".flatMap(");
                                function.Lambda(returnValueTypeArgumentClientType.ToString(), "res", "Mono.just(res.value())");
                                function.Line(");");
                            }
                            else
                            {
                                function.Text($".flatMap(");
                                function.Lambda(returnValueTypeArgumentClientType.ToString(), "res", "Mono.empty()");
                                function.Line(");");
                            }
                        }));
                    }));
                    break;

                default:
                    throw new ArgumentException($"There is no method implementation for {nameof(ClientMethodType)}.{clientMethod.Type}.");
            }
        }

        private static void AddNullChecks(JavaBlock function, IEnumerable<string> expressionsToCheck, JavaSettings settings)
        {
            if (settings.ClientSideValidations) {
                foreach (string expressionToCheck in expressionsToCheck)
                {
                    function.If($"{expressionToCheck} == null", ifBlock =>
                    {
                        ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {expressionToCheck} is required and cannot be null.\");");
                    });
                }
            }
        }

        private static void AddValidations(JavaBlock function, IEnumerable<string> expressionsToValidate, JavaSettings settings)
        {
            if (settings.ClientSideValidations) {
                foreach (string expressionToValidate in expressionsToValidate)
                {
                    function.Line($"Validator.validate({expressionToValidate});");
                }
            }
        }

        private static void AddOptionalAndConstantVariables(JavaBlock function, ClientMethod clientMethod, IEnumerable<ProxyMethodParameter> proxyMethodAndConstantParameters, JavaSettings settings)
        {
            foreach (var parameter in proxyMethodAndConstantParameters)
            {
                IType parameterWireType = parameter.WireType;;
                if (parameter.IsNullable)
                {
                    parameterWireType = parameterWireType.AsNullable();
                }
                IType parameterClientType = parameter.ClientType;

                if (parameterWireType != ClassType.Base64Url &&
                    parameter.RequestParameterLocation != RequestParameterLocation.Body &&
                    parameter.RequestParameterLocation != RequestParameterLocation.FormData &&
                    (parameterClientType is ArrayType || parameterClientType is ListType))
                {
                    parameterWireType = ClassType.String;
                }
                bool alwaysNull = parameterWireType != parameterClientType && clientMethod.OnlyRequiredParameters && !parameter.IsRequired;

                if (!parameter.FromClient && !alwaysNull && ((clientMethod.OnlyRequiredParameters && !parameter.IsRequired) || parameter.IsConstant))
                {
                    string defaultValue = parameterClientType.DefaultValueExpression(parameter.DefaultValue);
                    function.Line($"final {parameterClientType} {parameter.ParameterReference} = {defaultValue ?? "null"};");
                }
            }
        }

        private static void ApplyParameterTransformations(JavaBlock function, ClientMethod clientMethod, JavaSettings settings)
        {
            foreach (MethodTransformationDetail transformation in clientMethod.MethodTransformationDetails)
            {
                string nullCheck = string.Join(" || ", transformation.ParameterMappings.Where(m => !m.InputParameter.IsRequired)
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
                bool conditionalAssignment = !string.IsNullOrEmpty(nullCheck) && !transformation.OutParameterIsRequired && !clientMethod.OnlyRequiredParameters;
                if (conditionalAssignment)
                {
                    function.Line("{0} {1} = null;",
                        transformation.OutParameterType.ClientType,
                        transformation.OutParameterName);
                    function.Line($"if ({nullCheck}) {{");
                    function.IncreaseIndent();
                }

                ClassType transformationOutputParameterModelClassType = transformation.OutParameterType as ClassType;
                bool generatedCompositeType = false;
                if (transformationOutputParameterModelClassType != null)
                {
                    generatedCompositeType = transformationOutputParameterModelClassType.Package.StartsWith(settings.Package);
                }
                if (generatedCompositeType && transformation.ParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty)))
                {
                    string transformationOutputParameterModelCompositeTypeName = transformationOutputParameterModelClassType.ToString();
                    if (settings.IsFluent && !string.IsNullOrEmpty(transformationOutputParameterModelCompositeTypeName) && transformationOutputParameterModelClassType.IsInnerModelType)
                    {
                        transformationOutputParameterModelCompositeTypeName += "Inner";
                    }

                    function.Line("{0}{1} = new {2}();",
                        !conditionalAssignment ? transformation.OutParameterType.ClientType + " " : "",
                        transformation.OutParameterName,
                        transformationOutputParameterModelCompositeTypeName);
                }

                foreach (ParameterMapping mapping in transformation.ParameterMappings)
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
                        !conditionalAssignment && !generatedCompositeType ? transformation.OutParameterType.ClientType + " " : "",
                        transformation.OutParameterName,
                        getMapping);
                }

                if (conditionalAssignment)
                {
                    function.DecreaseIndent();
                    function.Line("}");
                }
            }
        }

        private static void ConvertClientTypesToWireTypes(JavaBlock function, ClientMethod clientMethod, IEnumerable<ProxyMethodParameter> autoRestMethodRetrofitParameters, string methodClientReference, JavaSettings settings)
        {
            foreach (ProxyMethodParameter parameter in autoRestMethodRetrofitParameters)
            {
                IType parameterWireType = parameter.WireType;;
                if (parameter.IsNullable)
                {
                    parameterWireType = parameterWireType.AsNullable();
                }
                IType parameterClientType = parameter.ClientType;

                if (parameterWireType != ClassType.Base64Url &&
                    parameter.RequestParameterLocation != RequestParameterLocation.Body &&
                    parameter.RequestParameterLocation != RequestParameterLocation.FormData &&
                    (parameterClientType is ArrayType || parameterClientType is ListType))
                {
                    parameterWireType = ClassType.String;
                }

                if (parameterWireType != parameterClientType)
                {
                    string parameterName = parameter.ParameterReference;
                    string parameterWireName = parameter.ParameterReferenceConverted;

                    bool addedConversion = false;
                    bool alwaysNull = clientMethod.OnlyRequiredParameters && !parameter.IsRequired;
                    
                    RequestParameterLocation parameterLocation = parameter.RequestParameterLocation;
                    if (parameterLocation != RequestParameterLocation.Body &&
                        parameterLocation != RequestParameterLocation.FormData &&
                        (parameterClientType is ArrayType || parameterClientType is ListType))
                    {
                        string parameterWireTypeName = parameterWireType.ToString();

                        if (parameterClientType == ArrayType.ByteArray)
                        {
                            if (parameterWireType == ClassType.String)
                            {
                                string expression;
                                if (alwaysNull)
                                {
                                    expression = "null";
                                }
                                else
                                {
                                    expression = $"Base64Util.encodeToString({parameterName})";
                                }
                                function.Line($"{parameterWireTypeName} {parameterWireName} = {expression};");
                            }
                            else
                            {
                                string expression;
                                if (alwaysNull)
                                {
                                    expression = "null";
                                }
                                else
                                {
                                    expression = $"Base64Url.encode({parameterName})";
                                }
                                function.Line($"{parameterWireTypeName} {parameterWireName} = {expression};");
                            }
                            addedConversion = true;
                        }
                        else if (parameterClientType is ListType)
                        {
                            string expression;
                            if (alwaysNull)
                            {
                                expression = "null";
                            }
                            else
                            {
                                expression = $"{methodClientReference}.serializerAdapter().serializeList({parameterName}, CollectionFormat.{parameter.CollectionFormat.ToString().ToUpperInvariant()})";
                            }
                            function.Line($"{parameterWireTypeName} {parameterWireName} = {expression};");
                            addedConversion = true;
                        }
                    }

                    if (settings.ShouldGenerateXmlSerialization && 
                        parameterClientType is ListType && 
                        (parameterLocation == RequestParameterLocation.Body || parameterLocation == RequestParameterLocation.FormData))
                    {
                        function.Line("{0} {1} = new {0}({2});",
                            parameter.WireType,
                            parameterWireName,
                            alwaysNull? "null": parameterName);
                        addedConversion = true;
                    }

                    if (!addedConversion)
                    {
                        function.Line(parameter.ConvertFromClientType(parameterName, parameterWireName,
                            clientMethod.OnlyRequiredParameters && !parameter.IsRequired,
                            parameter.IsConstant || alwaysNull));
                    }
                }
            }
        }
    }
}