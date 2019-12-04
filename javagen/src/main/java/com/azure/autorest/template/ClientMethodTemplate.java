package com.azure.autorest.template;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MethodPageDetails;
import com.azure.autorest.model.clientmodel.MethodTransformationDetail;
import com.azure.autorest.model.clientmodel.ParameterMapping;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.util.CodeNamer;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Writes a ClientMethod to a JavaType block.
 */
public class ClientMethodTemplate implements IJavaTemplate<ClientMethod, JavaType> {
    private static ClientMethodTemplate _instance = new ClientMethodTemplate();

    private ClientMethodTemplate() {
    }

    public static ClientMethodTemplate getInstance() {
        return _instance;
    }

    private static void AddNullChecks(JavaBlock function, List<String> expressionsToCheck, JavaSettings settings) {
        if (settings.shouldClientSideValidations()) {
            for (String expressionToCheck : expressionsToCheck) {
                function.ifBlock(expressionToCheck + " == null", ifBlock ->
                        ifBlock.line("throw new IllegalArgumentException(\"Parameter %s is required and cannot be null.\");", expressionToCheck));
            }
        }
    }

    private static void AddValidations(JavaBlock function, List<String> expressionsToValidate, JavaSettings settings) {
        if (settings.shouldClientSideValidations()) {
            for (String expressionToValidate : expressionsToValidate) {
                function.line("Validator.validate(%s);", expressionToValidate);
            }
        }
    }

    private static void AddOptionalAndConstantVariables(JavaBlock function, ClientMethod clientMethod, List<ProxyMethodParameter> proxyMethodAndConstantParameters, JavaSettings settings) {
        for (ProxyMethodParameter parameter : proxyMethodAndConstantParameters) {
            IType parameterWireType = parameter.getWireType();
            ;
            if (parameter.getIsNullable()) {
                parameterWireType = parameterWireType.asNullable();
            }
            IType parameterClientType = parameter.getClientType();

            if (parameterWireType != ClassType.Base64Url &&
                    parameter.getRequestParameterLocation() != RequestParameterLocation.Body &&
                    //parameter.getRequestParameterLocation() != RequestParameterLocation.FormData &&
                    (parameterClientType instanceof ArrayType || parameterClientType instanceof ListType)) {
                parameterWireType = ClassType.String;
            }
            boolean alwaysNull = parameterWireType != parameterClientType && clientMethod.getOnlyRequiredParameters() && !parameter.getIsRequired();

            if (!parameter.getFromClient() && !alwaysNull && ((clientMethod.getOnlyRequiredParameters() && !parameter.getIsRequired()) || parameter.getIsConstant())) {
                String defaultValue = parameterClientType.defaultValueExpression(parameter.getDefaultValue());
                function.line("final %s %s = %s;", parameterClientType, parameter.getParameterReference(), defaultValue == null ? "null" : defaultValue);
            }
        }
    }

    private static void ApplyParameterTransformations(JavaBlock function, ClientMethod clientMethod, JavaSettings settings) {
        for (MethodTransformationDetail transformation : clientMethod.getMethodTransformationDetails()) {
            String nullCheck = transformation.getParameterMappings().stream().filter(m -> !m.getInputParameter().getIsRequired())
                    .map((ParameterMapping m) -> {
                        ClientMethodParameter parameter = m.getInputParameter();

                        String parameterName;
                        if (!parameter.getFromClient()) {
                            parameterName = parameter.getName();
                        } else {
                            parameterName = m.getInputParameterProperty();
                        }

                        return parameterName + " != null";
                    }).collect(Collectors.joining(" || "));
            boolean conditionalAssignment = nullCheck != null && !nullCheck.isEmpty() && !transformation.getOutParameter().getIsRequired() && !clientMethod.getOnlyRequiredParameters();
            if (conditionalAssignment) {
                function.line("{0} {1} = null;",
                        transformation.getOutParameter().getClientType(),
                        transformation.getOutParameter().getName());
                function.line("if (%s) {", nullCheck);
                function.increaseIndent();
            }

            ClassType transformationOutputParameterModelClassType = (ClassType) transformation.getOutParameter().getClientType();
            boolean generatedCompositeType = false;
            if (transformationOutputParameterModelClassType != null) {
                generatedCompositeType = transformationOutputParameterModelClassType.getPackage().startsWith(settings.getPackage());
            }
            if (generatedCompositeType && transformation.getParameterMappings().stream().anyMatch(m -> m.getOutputParameterProperty() != null && !m.getOutputParameterProperty().isEmpty())) {
                String transformationOutputParameterModelCompositeTypeName = transformationOutputParameterModelClassType.toString();
                if (settings.isFluent() && transformationOutputParameterModelCompositeTypeName != null && !transformationOutputParameterModelCompositeTypeName.isEmpty() && transformationOutputParameterModelClassType.getIsInnerModelType()) {
                    transformationOutputParameterModelCompositeTypeName += "Inner";
                }

                function.line("{0}{1} = new {2}();",
                        !conditionalAssignment ? transformation.getOutParameter().getClientType() + " " : "",
                        transformation.getOutParameter().getName(),
                        transformationOutputParameterModelCompositeTypeName);
            }

            for (ParameterMapping mapping : transformation.getParameterMappings()) {
                String inputPath;
                if (!mapping.getInputParameter().getFromClient()) {
                    inputPath = mapping.getInputParameter().getName();
                } else {
                    inputPath = mapping.getInputParameterProperty();
                }

                if (clientMethod.getOnlyRequiredParameters() && !mapping.getInputParameter().getIsRequired()) {
                    inputPath = "null";
                }

                String getMapping;
                if (mapping.getOutputParameterProperty() != null) {
                    getMapping = String.format(".%s(%s)", CodeNamer.toCamelCase(mapping.getOutputParameterProperty()), inputPath);
                } else {
                    getMapping = String.format(" = %s", inputPath);
                }

                function.line("{0}{1}{2};",
                        !conditionalAssignment && !generatedCompositeType ? transformation.getOutParameter().getClientType() + " " : "",
                        transformation.getOutParameter().getName(),
                        getMapping);
            }

            if (conditionalAssignment) {
                function.decreaseIndent();
                function.line("}");
            }
        }
    }

    private static void ConvertClientTypesToWireTypes(JavaBlock function, ClientMethod clientMethod, List<ProxyMethodParameter> autoRestMethodRetrofitParameters, String methodClientReference, JavaSettings settings) {
        for (ProxyMethodParameter parameter : autoRestMethodRetrofitParameters) {
            IType parameterWireType = parameter.getWireType();
            ;
            if (parameter.getIsNullable()) {
                parameterWireType = parameterWireType.asNullable();
            }
            IType parameterClientType = parameter.getClientType();

            if (parameterWireType != ClassType.Base64Url &&
                    parameter.getRequestParameterLocation() != RequestParameterLocation.Body &&
                    //parameter.getRequestParameterLocation() != RequestParameterLocation.FormData &&
                    (parameterClientType instanceof ArrayType || parameterClientType instanceof ListType)) {
                parameterWireType = ClassType.String;
            }

            if (parameterWireType != parameterClientType) {
                String parameterName = parameter.getParameterReference();
                String parameterWireName = parameter.getParameterReferenceConverted();

                boolean addedConversion = false;
                boolean alwaysNull = clientMethod.getOnlyRequiredParameters() && !parameter.getIsRequired();

                RequestParameterLocation parameterLocation = parameter.getRequestParameterLocation();
                if (parameterLocation != RequestParameterLocation.Body &&
                        //parameterLocation != RequestParameterLocation.FormData &&
                        (parameterClientType instanceof ArrayType || parameterClientType instanceof ListType)) {
                    String parameterWireTypeName = parameterWireType.toString();

                    if (parameterClientType == ArrayType.ByteArray) {
                        if (parameterWireType == ClassType.String) {
                            String expression;
                            if (alwaysNull) {
                                expression = "null";
                            } else {
                                expression = String.format("Base64Util.encodeToString()", parameterName);
                            }
                            function.line("%s %s = %s;", parameterWireType, parameterWireName, expression);
                        } else {
                            String expression;
                            if (alwaysNull) {
                                expression = "null";
                            } else {
                                expression = String.format("Base64Url.encode(%s)", parameterName);
                            }
                            function.line("%s %s = %s;", parameterWireTypeName, parameterWireName, expression);
                        }
                        addedConversion = true;
                    } else if (parameterClientType instanceof ListType) {
                        String expression;
                        if (alwaysNull) {
                            expression = "null";
                        } else {
                            expression = String.format("JacksonAdapter.createDefaultSerializerAdapter().serializeList(%s, CollectionFormat.%s)", parameterName, parameter.getCollectionFormat().toString().toUpperCase());
                        }
                        function.line("%s %s = {%s};", parameterWireTypeName, parameterWireName, expression);
                        addedConversion = true;
                    }
                }

                if (settings.shouldGenerateXmlSerialization() &&
                        parameterClientType instanceof ListType &&
                        (parameterLocation == RequestParameterLocation.Body /*|| parameterLocation == RequestParameterLocation.FormData*/)) {
                    function.line("{0} {1} = new {0}({2});",
                            parameter.getWireType(),
                            parameterWireName,
                            alwaysNull ? "null" : parameterName);
                    addedConversion = true;
                }

                if (!addedConversion) {
                    function.line(parameter.convertFromClientType(parameterName, parameterWireName,
                            clientMethod.getOnlyRequiredParameters() && !parameter.getIsRequired(),
                            parameter.getIsConstant() || alwaysNull));
                }
            }
        }
    }

    public final void write(ClientMethod clientMethod, JavaType typeBlock) {
        JavaSettings settings = JavaSettings.getInstance();

        ProxyMethod restAPIMethod = clientMethod.getProxyMethod();
        IType restAPIMethodReturnBodyClientType = restAPIMethod.getReturnType().getClientType();

        MethodPageDetails pageDetails = clientMethod.getMethodPageDetails();


        boolean isFluentDelete = settings.isFluent() && restAPIMethod.getName().equalsIgnoreCase("Delete") && clientMethod.getMethodRequiredParameters().size() == 2;

        switch (clientMethod.getType()) {
            case PagingSync:
                typeBlock.javadocComment(comment -> {
                    comment.description(clientMethod.getDescription());
                    for (ClientMethodParameter parameter : clientMethod.getParameters()) {
                        comment.param(parameter.getName(), parameter.getDescription());
                    }
                    if (clientMethod.getParametersDeclaration() != null && !clientMethod.getParametersDeclaration().isEmpty()) {
                        comment.methodThrows("IllegalArgumentException", "thrown if parameters fail the validation");
                    }
                    if (restAPIMethod.getUnexpectedResponseExceptionType() != null) {
                        comment.methodThrows(restAPIMethod.getUnexpectedResponseExceptionType().toString(), "thrown if the request is rejected by server");
                    }
                    comment.methodThrows("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
                    comment.methodReturns(clientMethod.getReturnValue().getDescription());
                });
                typeBlock.annotation("ServiceMethod(returns = ReturnType.COLLECTION)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), function ->
                {
                    function.line(String.format("%1$s response = %2$s(%3$s).block();", pageDetails.getPageType(), clientMethod.getPagingAsyncSinglePageMethodName(), clientMethod.getArgumentList()));
                    function.returnAnonymousClass(String.format("new %1$s(response)", clientMethod.getReturnValue().getType()), anonymousClass ->
                    {
                        anonymousClass.annotation("Override");
                        anonymousClass.publicMethod(String.format("%1$s nextPage(String %2$s)", pageDetails.getPageType(), pageDetails.getNextLinkParameterName()), subFunction ->
                        {
                            if (clientMethod.getMethodTransformationDetails() != null && !clientMethod.getMethodTransformationDetails().isEmpty() && pageDetails.getNextMethod().getMethodTransformationDetails() != null) {
                                if (!pageDetails.getNextGroupParameterTypeName().equals(clientMethod.getGroupedParameterTypeName()) && (!clientMethod.getOnlyRequiredParameters() || clientMethod.getIsGroupedParameterRequired())) {
                                    String nextGroupTypeCamelCaseName = CodeNamer.toCamelCase(pageDetails.getNextGroupParameterTypeName());
                                    String groupedTypeCamelCaseName = CodeNamer.toCamelCase(clientMethod.getGroupedParameterTypeName());

                                    String nextGroupTypeCodeName = CodeNamer.getTypeName(pageDetails.getNextGroupParameterTypeName()) + (settings.isFluent() ? "Inner" : "");

                                    if (!clientMethod.getIsGroupedParameterRequired()) {
                                        subFunction.line(String.format("%1$s %2$s = null;", nextGroupTypeCodeName, nextGroupTypeCamelCaseName));
                                        subFunction.line(String.format("if (%s != null) {", groupedTypeCamelCaseName));
                                        subFunction.increaseIndent();
                                        subFunction.line(String.format("%1$s = new %2$s();", nextGroupTypeCamelCaseName, nextGroupTypeCodeName));
                                    } else {
                                        subFunction.line(String.format("%1$s %2$s = new %3$s();", nextGroupTypeCodeName, nextGroupTypeCamelCaseName, nextGroupTypeCodeName));
                                    }
                                    ClientMethod nextMethod = pageDetails.getNextMethod();
                                    for (ClientMethodParameter outputParameter : nextMethod.getMethodTransformationDetails().stream().map(MethodTransformationDetail::getOutParameter).collect(Collectors.toList())) {
                                        String outputParameterName;
                                        if (!outputParameter.getFromClient()) {
                                            outputParameterName = outputParameter.getName();
                                        } else {
                                            String caller = (pageDetails.nextMethodGroupName() == null || pageDetails.nextMethodGroupName().isEmpty()) ? "this" : "this.client";
                                            String clientPropertyName = outputParameter.getFromClient() ? outputParameter.getName() : null;
                                            if (clientPropertyName != null && !clientPropertyName.isEmpty()) {
                                                clientPropertyName = CodeNamer.toPascalCase(CodeNamer.removeInvalidCharacters(clientPropertyName));
                                            }
                                            outputParameterName = String.format("%1$s.get%2$s()", caller, clientPropertyName);
                                        }
                                        subFunction.line(String.format("%1$s.%2$s(%3$s.%4$s());", nextGroupTypeCamelCaseName, CodeNamer.toCamelCase(outputParameterName), groupedTypeCamelCaseName, CodeNamer.toCamelCase(outputParameterName)));
                                    }
                                    if (!clientMethod.getIsGroupedParameterRequired()) {
                                        subFunction.decreaseIndent();
                                        subFunction.line("}");
                                    }
                                }
                            }
                            subFunction.methodReturn(String.format("%1$s(%2$s).block()", pageDetails.getNextMethodInvocation() + "SinglePageAsync", pageDetails.getNextMethodParameterInvocation()));
                        });
                    });
                });
                break;

            case PagingAsync:
//                typeBlock.javadocComment(comment ->
                typeBlock.annotation("ServiceMethod(returns = ReturnType.COLLECTION)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), function ->
                {
                    function.line(String.format("return %1$s(%2$s)", clientMethod.getPagingAsyncSinglePageMethodName(), clientMethod.getArgumentList()));
                    function.indent(() ->
                    {
                        function.line(".repeat(1)");
                        function.text(".concatMap(");
                        function.lambda(pageDetails.getPageType().toString(), "page", lambda -> {
                            lambda.line(String.format("String %s = page.nextPageLink();", pageDetails.getNextLinkVariableName()));
                            lambda.ifBlock(String.format("%s == null", pageDetails.getNextLinkVariableName()), ifBlock -> {
                                ifBlock.methodReturn("Flux.just(page)");
                            });

                            if (clientMethod.getMethodTransformationDetails() != null && pageDetails.getNextMethod().getMethodTransformationDetails() != null) {
                                if (pageDetails.getNextGroupParameterTypeName() != clientMethod.getGroupedParameterTypeName() && (!clientMethod.getOnlyRequiredParameters() || clientMethod.getIsGroupedParameterRequired())) {
                                    String nextGroupTypeCamelCaseName = CodeNamer.toCamelCase(pageDetails.getNextGroupParameterTypeName());
                                    String groupedTypeCamelCaseName = CodeNamer.toCamelCase(clientMethod.getGroupedParameterTypeName());

                                    String nextGroupTypeCodeName = CodeNamer.getTypeName(pageDetails.getNextGroupParameterTypeName()) + (settings.isFluent() ? "Inner" : "");

                                    if (!clientMethod.getIsGroupedParameterRequired()) {
                                        lambda.line("%s %s = null", nextGroupTypeCodeName, nextGroupTypeCamelCaseName);
                                        lambda.line("if (%s != null) {", groupedTypeCamelCaseName);
                                        lambda.increaseIndent();
                                        lambda.line("%s = new %s();", nextGroupTypeCamelCaseName, nextGroupTypeCodeName);
                                    } else {
                                        lambda.line("%s %s = new %s()", nextGroupTypeCodeName, nextGroupTypeCamelCaseName, nextGroupTypeCodeName);
                                    }

                                    for (ClientMethodParameter outputParameter : pageDetails.getNextMethod().getMethodTransformationDetails().stream().map(td -> td.getOutParameter()).collect(Collectors.toList())) {
                                        String outputParameterName;
                                        if (!outputParameter.getFromClient()) {
                                            outputParameterName = CodeNamer.toCamelCase(outputParameter.getName());
                                        } else {
                                            String caller = (pageDetails.nextMethodGroupName() == null || pageDetails.nextMethodGroupName().isEmpty()) ? "this" : "this.client";
                                            String clientPropertyName = outputParameter.getFromClient() ? outputParameter.getName() : null;
                                            if (clientPropertyName != null && !clientPropertyName.isEmpty()) {
                                                clientPropertyName = CodeNamer.toPascalCase(CodeNamer.removeInvalidCharacters(clientPropertyName));
                                            }
                                            outputParameterName = String.format("%1$s.get%2$s()", caller, clientPropertyName);
                                        }
                                        lambda.line("%s.%s(%s.%s());", nextGroupTypeCamelCaseName, outputParameterName, groupedTypeCamelCaseName, outputParameterName);
                                    }

                                    if (!clientMethod.getIsGroupedParameterRequired()) {
                                        lambda.decreaseIndent();
                                        lambda.line("}");
                                    }
                                }
                            }

                            lambda.lambdaReturn(String.format("Flux.just(page).concatWith(%sAsync(%s))", pageDetails.getNextMethodInvocation(), pageDetails.getNextMethodParameterInvocation()));
                        });
                        function.line(");");
                    });
                });
                break;

            case PagingAsyncSinglePage:
                typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                    AddNullChecks(function, clientMethod.getRequiredNullableParameterExpressions(), settings);
                    AddValidations(function, clientMethod.getExpressionsToValidate(), settings);
                    AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                    ApplyParameterTransformations(function, clientMethod, settings);
                    ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);

                    if (pageDetails.getIsNextMethod()) {
                        String methodUrl = restAPIMethod.getUrlPath();
                        String substitutedMethodUrl = methodUrl.replaceAll("\\{\\w+}", "%s");

                        List<ProxyMethodParameter> retrofitParameters = restAPIMethod.getParameters().stream().filter(p -> p.getRequestParameterLocation() != RequestParameterLocation.None).collect(Collectors.toList());
                        StringBuilder builder = new StringBuilder(String.format("String.format(\"%s\"", substitutedMethodUrl));
                        Pattern pattern = Pattern.compile("\\{\\w+}");
                        Matcher matcher = pattern.matcher(methodUrl);
                        while (matcher.find()) {
                            String serializedNameWithBrackets = matcher.group();
                            String serializedName = serializedNameWithBrackets.substring(1, serializedNameWithBrackets.length() - 2);
                            ProxyMethodParameter parameter = retrofitParameters.stream().filter((p -> p.getRequestParameterName().equals(serializedName))).findFirst().get();

                            String parameterName;
                            if (!parameter.getFromClient()) {
                                parameterName = parameter.getName();
                            } else {
                                parameterName = parameter.getParameterReference();
                            }

                            IType parameterModelType = parameter.getWireType();
                            if (parameterModelType != null && !parameter.getIsNullable()) {
                                if (parameterModelType instanceof ClassType && ((ClassType) parameterModelType).isBoxedType()) {
                                    parameterModelType = PrimitiveType.fromNullableType((ClassType) parameterModelType);
                                }
                            }

                            IType parameterClientType = parameterModelType.getClientType();

                            IType parameterWireType;
                            if (parameterModelType.equals(GenericType.FluxByteBuffer)) {
                                parameterWireType = parameterClientType;
                            } else if (!parameterModelType.equals(ClassType.Base64Url) &&
                                    parameter.getRequestParameterLocation() != RequestParameterLocation.Body &&
                                    //parameter.getRequestParameterLocation() != RequestParameterLocation.FormData &&
                                    (parameterClientType instanceof ArrayType || parameterClientType instanceof ListType)) {
                                parameterWireType = ClassType.String;
                            } else {
                                parameterWireType = parameterModelType;
                            }

                            String parameterWireName = !parameterClientType.toString().equals(parameterWireType.toString()) ? CodeNamer.toCamelCase(parameterName) + "Converted" : parameterName;
                            builder.append(", ").append(parameterWireName);
                        }
                        builder.append(")");

                        function.line("String nextUrl = %s;", builder.toString());
                    }
                });
                break;
            case SimulatedPagingSync:
                typeBlock.annotation("ServiceMethod(returns = ReturnType.COLLECTION)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                    function.line("%s page = new %s<>();", pageDetails.getPageImplType(), pageDetails.getPageImplType());
                    function.line("page.setItems(%s(%s).single().items());", clientMethod.getSimpleAsyncMethodName(), clientMethod.getArgumentList());
                    function.line("page.setNextPageLink(null);");
                    function.returnAnonymousClass(String.format("new %s(page)", clientMethod.getReturnValue().getType()), anonymousClass -> {
                        anonymousClass.annotation("Override");
                        anonymousClass.publicMethod("{pageDetails.PageType} nextPage(String nextPageLink)", subFunction -> {
                            subFunction.methodReturn("null");
                        });
                    });
                });
                break;

            case SimulatedPagingAsync:
                typeBlock.annotation("ServiceMethod(returns = ReturnType.COLLECTION)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                    AddNullChecks(function, clientMethod.getRequiredNullableParameterExpressions(), settings);
                    AddValidations(function, clientMethod.getExpressionsToValidate(), settings);
                    AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                    ApplyParameterTransformations(function, clientMethod, settings);
                    ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);

                    IType returnValueTypeArgumentType = ((GenericType) restAPIMethod.getReturnType()).getTypeArguments()[0];
                    String restAPIMethodArgumentList = String.join(", ", clientMethod.getProxyMethodArguments(settings));
                    function.line("return service.%s(%s)", clientMethod.getProxyMethod().getName(), restAPIMethodArgumentList);
                    function.indent(() -> {
                        function.text(".map(");
                        function.lambda(returnValueTypeArgumentType.toString(), "res", "res.value()");
                        function.line(")");
                        function.line(".repeat(1);");
                    });
                });
                break;

            case LongRunningSync:
                typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                    if (clientMethod.getReturnValue().getType() == PrimitiveType.Void) {
                        function.line("%s(%s).blockLast();", clientMethod.getSimpleAsyncMethodName(), clientMethod.getArgumentList());
                    } else {
                        function.methodReturn(String.format("%s(%s).blockLast().result()", clientMethod.getSimpleAsyncMethodName(), clientMethod.getArgumentList()));
                    }
                });
                break;

            case Resumable:
                typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                    ProxyMethodParameter parameter = restAPIMethod.getParameters().get(0);
                    AddNullChecks(function, clientMethod.getRequiredNullableParameterExpressions(), settings);
                    function.methodReturn(String.format("service.%s(%s)", restAPIMethod.getName(), parameter.getName()));
                });
                break;

            case LongRunningAsync:
                typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                    AddNullChecks(function, clientMethod.getRequiredNullableParameterExpressions(), settings);
                    AddValidations(function, clientMethod.getExpressionsToValidate(), settings);
                    AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                    ApplyParameterTransformations(function, clientMethod, settings);
                    ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);
                    String restAPIMethodArgumentList = String.join(", ", clientMethod.getProxyMethodArguments(settings));
                    function.methodReturn(String.format("service.%s(%s)", restAPIMethod.getName(), restAPIMethodArgumentList));
                });
                break;

            case SimpleSync:
                typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                    if (clientMethod.getReturnValue().getType() != PrimitiveType.Void) {
                        function.methodReturn(String.format("%s(%s).block()", clientMethod.getSimpleAsyncMethodName(), clientMethod.getArgumentList()));
                    } else {
                        function.line("%s(%s).block();", clientMethod.getSimpleAsyncMethodName(), clientMethod.getArgumentList());
                    }
                });
                break;

            case SimpleAsyncRestResponse:
                typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                    AddNullChecks(function, clientMethod.getRequiredNullableParameterExpressions(), settings);
                    AddValidations(function, clientMethod.getExpressionsToValidate(), settings);
                    AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                    ApplyParameterTransformations(function, clientMethod, settings);
                    ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);
                    String restAPIMethodArgumentList = String.join(", ", clientMethod.getProxyMethodArguments(settings));
                    function.methodReturn(String.format("service.%s(%s)", restAPIMethod.getName(), restAPIMethodArgumentList));
                });
                break;

            case SimpleAsync:
                typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), (function -> {
                    function.line("return %s(%s)", clientMethod.getProxyMethod().getSimpleAsyncRestResponseMethodName(), clientMethod.getArgumentList());
                    function.indent((() -> {
                        GenericType restAPIMethodClientReturnType = (GenericType) restAPIMethod.getReturnType().getClientType();
                        IType returnValueTypeArgumentClientType = restAPIMethodClientReturnType.getTypeArguments()[0];
                        if (!GenericType.Mono(ClassType.Void).equals(clientMethod.getReturnValue().getType()) &&
                                !GenericType.Flux(ClassType.Void).equals(clientMethod.getReturnValue().getType())) {
                            function.text(".flatMap(");
                            function.lambda(returnValueTypeArgumentClientType.toString(), "res", lambda -> {
                                lambda.ifBlock("res.getValue() != null", ifAction -> {
                                    ifAction.methodReturn("Mono.just(res.getValue())");
                                }).elseBlock(elseAction -> {
                                    elseAction.methodReturn("Mono.empty()");
                                });
                            });
                            function.line(");");
                        } else {
                            function.text(".flatMap(");
                            function.lambda(returnValueTypeArgumentClientType.toString(), "res", "Mono.empty()");
                            function.line(");");
                        }
                    }));
                }));
                break;
        }
    }
}
