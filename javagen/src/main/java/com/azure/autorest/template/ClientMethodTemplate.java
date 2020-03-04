package com.azure.autorest.template;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.ParameterMapping;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MethodTransformationDetail;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaIfBlock;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.util.CodeNamer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Writes a ClientMethod to a JavaType block.
 */
public class ClientMethodTemplate implements IJavaTemplate<ClientMethod, JavaType> {
    private static ClientMethodTemplate _instance = new ClientMethodTemplate();

    protected ClientMethodTemplate() {
    }

    public static ClientMethodTemplate getInstance() {
        return _instance;
    }

    private static void AddValidations(JavaBlock function, List<String> expressionsToCheck, Map<String, String> validateExpressions, JavaSettings settings) {
        if (settings.shouldClientSideValidations()) {
            for (String expressionToCheck : expressionsToCheck) {
                JavaIfBlock nullCheck = function.ifBlock(expressionToCheck + " == null", ifBlock ->
                        ifBlock.line("throw new IllegalArgumentException(\"Parameter %s is required and cannot be null.\");", expressionToCheck));
                if (validateExpressions.containsKey(expressionToCheck)) {
                    nullCheck.elseBlock(elseBlock ->
                            elseBlock.line(validateExpressions.get(expressionToCheck) + ";"));
                }
            }
            for (Map.Entry<String, String> validateExpression : validateExpressions.entrySet()) {
                if (!expressionsToCheck.contains(validateExpression.getKey())) {
                    function.ifBlock(validateExpression.getKey() + " != null", ifBlock ->
                            ifBlock.line(validateExpression.getValue() + ";"));
                }
            }
        }
    }

    private static void AddOptionalVariables(JavaBlock function, ClientMethod clientMethod, List<ProxyMethodParameter> proxyMethodAndConstantParameters, JavaSettings settings) {
        if (clientMethod.getOnlyRequiredParameters()) {
            AddOptionalAndConstantVariables(function, clientMethod, proxyMethodAndConstantParameters, settings, true, false);
        }
    }

    private static void AddOptionalAndConstantVariables(JavaBlock function, ClientMethod clientMethod, List<ProxyMethodParameter> proxyMethodAndConstantParameters, JavaSettings settings) {
        AddOptionalAndConstantVariables(function, clientMethod, proxyMethodAndConstantParameters, settings, true, true);
    }

    private static void AddOptionalAndConstantVariables(JavaBlock function, ClientMethod clientMethod, List<ProxyMethodParameter> proxyMethodAndConstantParameters, JavaSettings settings,
                                                        boolean addOptional, boolean addConstant) {
        for (ProxyMethodParameter parameter : proxyMethodAndConstantParameters) {
            IType parameterWireType = parameter.getWireType();
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

            if (!parameter.getFromClient() && !alwaysNull && ((addOptional && clientMethod.getOnlyRequiredParameters() && !parameter.getIsRequired()) || (addConstant && parameter.getIsConstant()))) {
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
            // Use a mutable internal variable, leave the original name for effectively final variable
            String outParameterName = conditionalAssignment
                    ? transformation.getOutParameter().getName() + "Internal"
                    : transformation.getOutParameter().getName();
            if (conditionalAssignment) {
                function.line("%s %s = null;",
                        transformation.getOutParameter().getClientType(),
                        outParameterName);
                function.line("if (%s) {", nullCheck);
                function.increaseIndent();
            }

            IType transformationOutputParameterModelType = transformation.getOutParameter().getClientType();
            boolean generatedCompositeType = false;
            if (transformationOutputParameterModelType instanceof ClassType) {
                generatedCompositeType = ((ClassType) transformationOutputParameterModelType).getPackage().startsWith(settings.getPackage());
            }
            if (generatedCompositeType && transformation.getParameterMappings().stream().anyMatch(m -> m.getOutputParameterProperty() != null && !m.getOutputParameterProperty().isEmpty())) {
                String transformationOutputParameterModelCompositeTypeName = transformationOutputParameterModelType.toString();
//                if (settings.isFluent() && transformationOutputParameterModelCompositeTypeName != null && !transformationOutputParameterModelCompositeTypeName.isEmpty() && transformationOutputParameterModelType.getIsInnerModelType()) {
//                    transformationOutputParameterModelCompositeTypeName += "Inner";
//                }

                function.line("%s%s = new %s();",
                        !conditionalAssignment ? transformation.getOutParameter().getClientType() + " " : "",
                        outParameterName,
                        transformationOutputParameterModelCompositeTypeName);
            }

            for (ParameterMapping mapping : transformation.getParameterMappings()) {
                String inputPath;
                if (mapping.getInputParameterProperty() != null) {
                    inputPath = String.format("%s.%s()", mapping.getInputParameter().getName(),
                            CodeNamer.getModelNamer().modelPropertyGetterName(mapping.getInputParameterProperty()));
                } else {
                    inputPath = mapping.getInputParameter().getName();
                }

                if (clientMethod.getOnlyRequiredParameters() && !mapping.getInputParameter().getIsRequired()) {
                    inputPath = "null";
                }

                String getMapping;
                if (mapping.getOutputParameterProperty() != null) {
                    getMapping = String.format(".%s(%s)", CodeNamer.getModelNamer().modelPropertySetterName(mapping.getOutputParameterProperty()), inputPath);
                } else {
                    getMapping = String.format(" = %s", inputPath);
                }

                function.line("%s%s%s;",
                        !conditionalAssignment && !generatedCompositeType ? transformation.getOutParameter().getClientType() + " " : "",
                        outParameterName,
                        getMapping);
            }

            if (conditionalAssignment) {
                function.decreaseIndent();
                function.line("}");
                function.line(String.format("%s %s = %s;",
                        transformation.getOutParameter().getClientType(),
                        transformation.getOutParameter().getName(),
                        outParameterName));
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
                                expression = String.format("Base64Util.encodeToString(%s)", parameterName);
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
                        function.line("%s %s = %s;", parameterWireTypeName, parameterWireName, expression);
                        addedConversion = true;
                    }
                }

                if (settings.shouldGenerateXmlSerialization() &&
                        parameterClientType instanceof ListType &&
                        (parameterLocation == RequestParameterLocation.Body /*|| parameterLocation == RequestParameterLocation.FormData*/)) {
                    function.line("%s %s = new %s(%s);",
                            parameter.getWireType(),
                            parameterWireName,
                            parameter.getWireType(),
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
        //IType restAPIMethodReturnBodyClientType = restAPIMethod.getReturnType().getClientType();

        //MethodPageDetails pageDetails = clientMethod.getMethodPageDetails();

        //boolean isFluentDelete = settings.isFluent() && restAPIMethod.getName().equalsIgnoreCase("Delete") && clientMethod.getMethodRequiredParameters().size() == 2;
        generateJavadoc(clientMethod, typeBlock, restAPIMethod);

        switch (clientMethod.getType()) {
            case PagingSync:
                typeBlock.annotation("ServiceMethod(returns = ReturnType.COLLECTION)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                    AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                    function.methodReturn(String.format("new PagedIterable<>(%s(%s))", clientMethod.getSimpleAsyncMethodName(), clientMethod.getArgumentList()));
                });
                break;

            case PagingAsync:
//                typeBlock.javadocComment(comment ->
                typeBlock.annotation("ServiceMethod(returns = ReturnType.COLLECTION)");
                if (clientMethod.getMethodPageDetails().nonNullNextLink()) {
                    typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                        AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                        function.line("return new PagedFlux<>(");
                        function.indent(() -> {
                            function.line("() -> %s(%s),",
                                    clientMethod.getProxyMethod().getPagingAsyncSinglePageMethodName(),
                                    clientMethod.getArgumentList());
                            function.line("nextLink -> %s(%s));",
                                    clientMethod.getMethodPageDetails().getNextMethod().getProxyMethod().getPagingAsyncSinglePageMethodName(),
                                    clientMethod.getMethodPageDetails().getNextMethod().getArgumentList());
                        });
                    });
                } else {
                    typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                        AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                        function.line("return new PagedFlux<>(");
                        function.indent(() -> {
                            function.line("() -> %s(%s));",
                                    clientMethod.getProxyMethod().getPagingAsyncSinglePageMethodName(),
                                    clientMethod.getArgumentList());
                        });
                    });
                }
                break;
            case PagingAsyncSinglePage:
                generatePagedAsyncSinglePage(clientMethod, typeBlock, restAPIMethod, settings);
                break;

                // TODO: Simulated paging
//            case SimulatedPagingSync:
//                typeBlock.annotation("ServiceMethod(returns = ReturnType.COLLECTION)");
//                typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
//                    function.line("%s page = new %s<>();", pageDetails.getPageImplType(), pageDetails.getPageImplType());
//                    function.line("page.setItems(%s(%s).single().items());", clientMethod.getSimpleAsyncMethodName(), clientMethod.getArgumentList());
//                    function.line("page.setNextPageLink(null);");
//                    function.returnAnonymousClass(String.format("new %s(page)", clientMethod.getReturnValue().getType()), anonymousClass -> {
//                        anonymousClass.annotation("Override");
//                        anonymousClass.publicMethod("{pageDetails.PageType} nextPage(String nextPageLink)", subFunction -> {
//                            subFunction.methodReturn("null");
//                        });
//                    });
//                });
//                break;
//
//            case SimulatedPagingAsync:
//                typeBlock.annotation("ServiceMethod(returns = ReturnType.COLLECTION)");
//                typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
//                    AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), settings);
//                    AddValidations(function, clientMethod.getValidateExpressions(), settings);
//                    AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
//                    ApplyParameterTransformations(function, clientMethod, settings);
//                    ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);
//
//                    IType returnValueTypeArgumentType = ((GenericType) restAPIMethod.getReturnType()).getTypeArguments()[0];
//                    String restAPIMethodArgumentList = String.join(", ", clientMethod.getProxyMethodArguments(settings));
//                    function.line("return service.%s(%s)", clientMethod.getProxyMethod().getName(), restAPIMethodArgumentList);
//                    function.indent(() -> {
//                        function.text(".map(");
//                        function.lambda(returnValueTypeArgumentType.toString(), "res", "res.value()");
//                        function.line(")");
//                        function.line(".repeat(1);");
//                    });
//                });
//                break;

            case LongRunningAsync:
                generateLongRunningAsync(clientMethod, typeBlock, restAPIMethod, settings);
                break;

            case Resumable:
                typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                    ProxyMethodParameter parameter = restAPIMethod.getParameters().get(0);
                    AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
                    function.methodReturn(String.format("service.%s(%s)", restAPIMethod.getName(), parameter.getName()));
                });
                break;

            case SimpleSync:
            case LongRunningSync:
                typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                    AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                    if (clientMethod.getReturnValue().getType() == ClassType.InputStream) {
                        function.line("return %s(%s)", clientMethod.getSimpleAsyncMethodName(), clientMethod.getArgumentList());
                        function.indent(() -> {
                            function.line(".map(ByteBufferBackedInputStream::new)");
                            function.line(".collectList()");
                            function.line(".map(list -> new SequenceInputStream(Collections.enumeration(list)))");
                            function.line(".block();");
                        });
                    } else if (clientMethod.getReturnValue().getType() != PrimitiveType.Void) {
                        function.methodReturn(String.format("%s(%s).block()", clientMethod.getSimpleAsyncMethodName(), clientMethod.getArgumentList()));
                    } else {
                        function.line("%s(%s).block();", clientMethod.getSimpleAsyncMethodName(), clientMethod.getArgumentList());
                    }
                });
                break;

            case SimpleAsyncRestResponse:
                generateSimpleAsyncRestResponse(clientMethod, typeBlock, restAPIMethod, settings);
                break;

            case SimpleAsync:
                typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
                typeBlock.publicMethod(clientMethod.getDeclaration(), (function -> {
                    AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                    function.line("return %s(%s)", clientMethod.getProxyMethod().getSimpleAsyncRestResponseMethodName(), clientMethod.getArgumentList());
                    function.indent((() -> {
                        GenericType restAPIMethodClientReturnType = (GenericType) restAPIMethod.getReturnType().getClientType();
                        IType returnValueTypeArgumentClientType = restAPIMethodClientReturnType.getTypeArguments()[0];
                        if (GenericType.Flux(ClassType.ByteBuffer).equals(clientMethod.getReturnValue().getType())) {
                            function.text(".flatMapMany(StreamResponse::getValue);");
                        } else if (!GenericType.Mono(ClassType.Void).equals(clientMethod.getReturnValue().getType()) &&
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

    protected void generateJavadoc(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod) {
        typeBlock.javadocComment(comment -> {
            comment.description(clientMethod.getDescription());
            List<ClientMethodParameter> methodParameters = clientMethod.getOnlyRequiredParameters()
                    ? clientMethod.getMethodRequiredParameters()
                    : clientMethod.getMethodParameters();
            for (ClientMethodParameter parameter : methodParameters) {
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
    }

    protected void generatePagedAsyncSinglePage(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        String restAPIMethodArgumentList = String.join(", ", clientMethod.getProxyMethodArguments(settings));
        String serviceMethodCall = String.format("service.%s(%s)", restAPIMethod.getName(), restAPIMethodArgumentList);
        if (clientMethod.getMethodPageDetails().nonNullNextLink()) {
            typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
                AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                ApplyParameterTransformations(function, clientMethod, settings);
                ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);
                if (settings.getAddContextParameter()) {
                    function.line(String.format("return FluxUtil.withContext(context -> %s)",
                            serviceMethodCall));
                } else {
                    function.line(String.format("return %s",
                            serviceMethodCall));
                }
                function.indent(() -> {
                    function.line(".map(res -> new PagedResponseBase<>(");
                    function.indent(() -> {
                        function.line("res.getRequest(),");
                        function.line("res.getStatusCode(),");
                        function.line("res.getHeaders(),");
                        function.line("res.getValue().%s(),", CodeNamer.getModelNamer().modelPropertyGetterName(clientMethod.getMethodPageDetails().getItemName()));
                        function.line("res.getValue().%s(),", CodeNamer.getModelNamer().modelPropertyGetterName(clientMethod.getMethodPageDetails().getNextLinkName()));
                        IType responseType = ((GenericType) clientMethod.getProxyMethod().getReturnType()).getTypeArguments()[0];
                        if (responseType instanceof ClassType) {
                            function.line("res.getDeserializedHeaders()));");
                        } else {
                            function.line("null));");
                        }
                    });
                });
            });
        } else {
            typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                if (settings.getAddContextParameter()) {
                    function.line(String.format("return FluxUtil.withContext(context -> %s)",
                            serviceMethodCall));
                } else {
                    function.line(String.format("return %s",
                            serviceMethodCall));
                }
                function.indent(() -> {
                    function.line(".map(res -> new PagedResponseBase<>(");
                    function.indent(() -> {
                        function.line("res.getRequest(),");
                        function.line("res.getStatusCode(),");
                        function.line("res.getHeaders(),");
                        function.line("res.getValue().%s(),", CodeNamer.getModelNamer().modelPropertyGetterName(clientMethod.getMethodPageDetails().getItemName()));
                        function.line("null,");
                        IType responseType = ((GenericType) clientMethod.getProxyMethod().getReturnType()).getTypeArguments()[0];
                        if (responseType instanceof ClassType) {
                            function.line("res.getDeserializedHeaders()));");
                        } else {
                            function.line("null));");
                        }
                    });
                });
            });
        }
    }

    protected void generateSimpleAsyncRestResponse(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
            AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
            AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            ApplyParameterTransformations(function, clientMethod, settings);
            ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);
            String restAPIMethodArgumentList = String.join(", ", clientMethod.getProxyMethodArguments(settings));
            String serviceMethodCall = String.format("service.%s(%s)", restAPIMethod.getName(), restAPIMethodArgumentList);
            if (settings.getAddContextParameter()) {
                function.methodReturn(String.format("FluxUtil.withContext(context -> %s)",
                        serviceMethodCall));
            } else {
                function.methodReturn(serviceMethodCall);
            }
        });
    }

    protected void generateLongRunningAsync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
            // hack, 'host' is usually there, use it to determine whether the ClientMethod is in operation group or in client.
            String caller = restAPIMethod.getParameters().stream()
                    .filter(p -> p.getName().equalsIgnoreCase("host"))
                    .findAny()
                    .filter(p -> p.getParameterReference().contains("this.getHost()"))
                    .isPresent() ? "this." : "this.client.";

            IType classType = ((GenericType) clientMethod.getReturnValue().getType().getClientType()).getTypeArguments()[0];

            AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            function.line("%s response = %s(%s);", clientMethod.getProxyMethod().getReturnType().toString(), clientMethod.getProxyMethod().getSimpleAsyncRestResponseMethodName(), clientMethod.getArgumentList());
            if (classType instanceof GenericType) {
                function.line("return %s<%s, %s>getLroResultAsync(response, %sgetHttpPipeline(), new TypeReference<%s>() {}.getType(), new TypeReference<%s>() {}.getType())", caller, classType.toString(), classType.toString(), caller, classType.toString(), classType.toString());
            } else {
                function.line("return %s<%s, %s>getLroResultAsync(response, %sgetHttpPipeline(), %s.class, %s.class)", caller, classType.toString(), classType.toString(), caller, classType.toString(), classType.toString());
            }
            function.indent(() -> {
                function.line(".last()");
                function.line(".flatMap(AsyncPollResponse::getFinalResult);");
            });
        });
    }
}
