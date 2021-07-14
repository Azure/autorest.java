package com.azure.autorest.template;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientEnumValue;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.MethodTransformationDetail;
import com.azure.autorest.model.clientmodel.ParameterMapping;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaIfBlock;
import com.azure.autorest.model.javamodel.JavaInterface;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.CoreUtils;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
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

    protected static void AddValidations(JavaBlock function, List<String> expressionsToCheck, Map<String, String> validateExpressions, JavaSettings settings) {
        if (settings.shouldClientSideValidations()) {
            for (String expressionToCheck : expressionsToCheck) {
                JavaIfBlock nullCheck = function.ifBlock(expressionToCheck + " == null", ifBlock ->
                        ifBlock.line("return Mono.error(new IllegalArgumentException(\"Parameter %s is required and "
                            + "cannot be null.\"));", expressionToCheck));
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

    protected static void AddOptionalVariables(JavaBlock function, ClientMethod clientMethod, List<ProxyMethodParameter> proxyMethodAndConstantParameters, JavaSettings settings) {
        if (clientMethod.getOnlyRequiredParameters()) {
            for (ClientMethodParameter parameter : clientMethod.getMethodParameters()) {
                if (!parameter.getIsRequired()) {
                    IType parameterClientType = parameter.getClientType();
                    String defaultValue = parameterClientType.defaultValueExpression(parameter.getDefaultValue());
                    function.line("final %s %s = %s;", parameterClientType, parameter.getName(), defaultValue == null ? "null" : defaultValue);
                }
            }
        }
    }

    protected static void AddOptionalAndConstantVariables(JavaBlock function, ClientMethod clientMethod, List<ProxyMethodParameter> proxyMethodAndConstantParameters, JavaSettings settings) {
        AddOptionalAndConstantVariables(function, clientMethod, proxyMethodAndConstantParameters, settings, true, true, true);
    }

    /**
     * Add optional and constant variables.
     *
     * @param function
     * @param clientMethod
     * @param proxyMethodAndConstantParameters
     * @param settings
     * @param addOptional Whether add optional variables, init to default or null
     * @param addConstant Whether add constant variables, init to default
     * @param ignoreParameterNeedConvert When adding optional/constant variable, ignore those which need conversion from client type to wire type. Let "ConvertClientTypesToWireTypes" handle them.
     */
    protected static void AddOptionalAndConstantVariables(JavaBlock function, ClientMethod clientMethod, List<ProxyMethodParameter> proxyMethodAndConstantParameters, JavaSettings settings,
                                                        boolean addOptional, boolean addConstant, boolean ignoreParameterNeedConvert) {
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
            boolean alwaysNull = ignoreParameterNeedConvert && parameterWireType != parameterClientType && clientMethod.getOnlyRequiredParameters() && !parameter.getIsRequired();

            if (!parameter.getFromClient()
                    && !alwaysNull
                    && ((addOptional && clientMethod.getOnlyRequiredParameters() && !parameter.getIsRequired())
                    || (addConstant && parameter.getIsConstant() && (!settings.isOptionalConstantAsEnum() || parameter.getIsRequired())))) {
                String defaultValue = parameterClientType.defaultValueExpression(parameter.getDefaultValue());
                function.line("final %s %s = %s;", parameterClientType, parameter.getParameterReference(), defaultValue == null ? "null" : defaultValue);
            }
        }
    }

    protected static void ApplyParameterTransformations(JavaBlock function, ClientMethod clientMethod, JavaSettings settings) {
        for (MethodTransformationDetail transformation : clientMethod.getMethodTransformationDetails()) {
            String nullCheck = transformation.getParameterMappings().stream().filter(m -> !m.getInputParameter().getIsRequired())
                    .map((ParameterMapping m) -> {
                        ClientMethodParameter parameter = m.getInputParameter();

                        String parameterName;
                        if (!parameter.getFromClient()) {
                            parameterName = parameter.getName();
                        } else {
                            parameterName = m.getInputParameterProperty().getName();
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

                String name = transformation.getOutParameter().getName();
                if (clientMethod.getParameters().stream().anyMatch(param -> param.getName().equals(transformation.getOutParameter().getName()))) {
                    name = name + "Local";
                }
                function.line(String.format("%s %s = %s;",
                        transformation.getOutParameter().getClientType(),
                        name,
                        outParameterName));
            }
        }
    }

    protected static void ConvertClientTypesToWireTypes(JavaBlock function, ClientMethod clientMethod, List<ProxyMethodParameter> autoRestMethodRetrofitParameters, String methodClientReference, JavaSettings settings) {
        for (ProxyMethodParameter parameter : autoRestMethodRetrofitParameters) {
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
                            expression = String.format("JacksonAdapter.createDefaultSerializerAdapter()" +
                                            ".serializeList(%s, CollectionFormat.%s)", parameterName,
                                    parameter.getCollectionFormat().toString().toUpperCase());
                            if (settings.shouldUseIterable()) {
                                expression = String.format("JacksonAdapter.createDefaultSerializerAdapter()" +
                                                ".serializeIterable(%s, CollectionFormat.%s)", parameterName,
                                        parameter.getCollectionFormat().toString().toUpperCase());
                            }
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

    protected static void writeMethod(JavaType typeBlock, JavaVisibility visibility, String methodSignature, Consumer<JavaBlock> method) {
        if (visibility == JavaVisibility.Public) {
            typeBlock.publicMethod(methodSignature, method);
        } else {
            if (typeBlock instanceof JavaClass) {
                JavaClass classBlock = (JavaClass) typeBlock;
                classBlock.method(visibility, null, methodSignature, method);
            }
        }
    }

    public final void write(ClientMethod clientMethod, JavaType typeBlock) {
        final boolean writingInterface = typeBlock instanceof JavaInterface;
        if (clientMethod.getMethodVisibility() != JavaVisibility.Public && writingInterface) {
            return;
        }

        JavaSettings settings = JavaSettings.getInstance();

        ProxyMethod restAPIMethod = clientMethod.getProxyMethod();
        //IType restAPIMethodReturnBodyClientType = restAPIMethod.getReturnType().getClientType();

        //MethodPageDetails pageDetails = clientMethod.getMethodPageDetails();

        generateJavadoc(clientMethod, typeBlock, restAPIMethod, writingInterface);

        switch (clientMethod.getType()) {
            case PagingSync:
                if (settings.isLowLevelClient()) {
                    generateProtocolPagingSync(clientMethod, typeBlock, restAPIMethod, settings);
                } else {
                    generatePagingSync(clientMethod, typeBlock, restAPIMethod, settings);
                }
                break;
            case PagingAsync:
                if (settings.isLowLevelClient()) {
                    generateProtocolPagingAsync(clientMethod, typeBlock, restAPIMethod, settings);
                } else {
                    generatePagingAsync(clientMethod, typeBlock, restAPIMethod, settings);
                }
                break;
            case PagingAsyncSinglePage:
                if (settings.isLowLevelClient()) {
                    generateProtocolPagingAsyncSinglePage(clientMethod, typeBlock, restAPIMethod, settings);
                } else {
                    generatePagedAsyncSinglePage(clientMethod, typeBlock, restAPIMethod, settings);
                }
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
                if (!settings.isLowLevelClient()) {
                    // TODO: https://github.com/Azure/autorest.java/issues/1046
                    generateLongRunningAsync(clientMethod, typeBlock, restAPIMethod, settings);
                }
                break;

            case LongRunningBeginAsync:
                if (!settings.isLowLevelClient()) {
                    // TODO: https://github.com/Azure/autorest.java/issues/1046
                    generateLongRunningBeginAsync(clientMethod, typeBlock, restAPIMethod, settings);
                }
                break;

            case LongRunningBeginSync:
                if (!settings.isLowLevelClient()) {
                    // TODO: https://github.com/Azure/autorest.java/issues/1046
                    generateLongRunningBeginSync(clientMethod, typeBlock, restAPIMethod, settings);
                }
                break;

            case LongRunningSync:
                if (!settings.isLowLevelClient()) {
                    // TODO: https://github.com/Azure/autorest.java/issues/1046
                    generateSyncMethod(clientMethod, typeBlock, restAPIMethod, settings);
                }
                break;

            case Resumable:
                generateResumable(clientMethod, typeBlock, restAPIMethod, settings);
                break;

            case SimpleSync:
            case SimpleSyncRestResponse:
                generateSyncMethod(clientMethod, typeBlock, restAPIMethod, settings);
                break;

            case SimpleAsyncRestResponse:
                generateSimpleAsyncRestResponse(clientMethod, typeBlock, restAPIMethod, settings);
                break;

            case SimpleAsync:
                generateSimpleAsync(clientMethod, typeBlock, restAPIMethod, settings);
                break;
        }
    }

    protected void generateProtocolPagingSync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        generatePagingSync(clientMethod, typeBlock, restAPIMethod, settings);
    }

    protected void generateProtocolPagingAsync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        generatePagingAsync(clientMethod, typeBlock, restAPIMethod, settings);
    }

    protected void generateProtocolPagingAsyncSinglePage(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        generatePagedAsyncSinglePage(clientMethod, typeBlock, restAPIMethod, settings);
    }

    protected void generatePagingSync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.COLLECTION)");
        typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
            AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            function.methodReturn(String.format("new PagedIterable<>(%s(%s))", clientMethod.getSimpleAsyncMethodName(), clientMethod.getArgumentList()));
        });
    }

    protected void generatePagingAsync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        //                typeBlock.javadocComment(comment ->
        typeBlock.annotation("ServiceMethod(returns = ReturnType.COLLECTION)");
        if (clientMethod.getMethodPageDetails().nonNullNextLink()) {
            writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), function -> {
                AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                function.line("return new PagedFlux<>(");
                function.indent(() -> {
                    function.line("() -> %s(%s),",
                            clientMethod.getProxyMethod().getPagingAsyncSinglePageMethodName(),
                            clientMethod.getArgumentList());
                    function.line("nextLink -> %s(%s));",
                            clientMethod.getMethodPageDetails().getNextMethod().getProxyMethod().getPagingAsyncSinglePageMethodName(),
                            clientMethod.getMethodPageDetails().getNextMethod().getArgumentListWithoutRequestOptions());
                });
            });
        } else {
            writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), function -> {
                AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                function.line("return new PagedFlux<>(");
                function.indent(() -> {
                    function.line("() -> %s(%s));",
                            clientMethod.getProxyMethod().getPagingAsyncSinglePageMethodName(),
                            clientMethod.getArgumentList());
                });
            });
        }
    }

    protected void generateResumable(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
            ProxyMethodParameter parameter = restAPIMethod.getParameters().get(0);
            AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
            function.methodReturn(String.format("service.%s(%s)", restAPIMethod.getName(), parameter.getName()));
        });
    }

    protected void generateSimpleAsync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), (function -> {
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
    }

    protected void generateSyncMethod(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        String asyncMethodName = clientMethod.getSimpleAsyncMethodName();
        if (clientMethod.getType() == ClientMethodType.SimpleSyncRestResponse) {
            asyncMethodName = clientMethod.getSimpleWithResponseAsyncMethodName();
        }
        String effectiveAsyncMethodName = asyncMethodName;
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
            AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            if (clientMethod.getReturnValue().getType() == ClassType.InputStream) {
                function.line("Iterator<ByteBufferBackedInputStream> iterator = %s(%s).map(ByteBufferBackedInputStream::new).toStream().iterator();",
                        effectiveAsyncMethodName, clientMethod.getArgumentList());
                function.anonymousClass("Enumeration<InputStream>", "enumeration", javaBlock -> {
                    javaBlock.annotation("Override");
                    javaBlock.publicMethod("boolean hasMoreElements()", methodBlock -> methodBlock.methodReturn("iterator.hasNext()"));
                    javaBlock.annotation("Override");
                    javaBlock.publicMethod("InputStream nextElement()", methodBlock -> methodBlock.methodReturn("iterator.next()"));
                });
                function.methodReturn("new SequenceInputStream(enumeration)");
            } else if (clientMethod.getReturnValue().getType() != PrimitiveType.Void) {
                IType returnType = clientMethod.getReturnValue().getType();
                if (returnType instanceof PrimitiveType) {
                    function.line("%s value = %s(%s).block();", returnType.asNullable(),
                            effectiveAsyncMethodName, clientMethod.getArgumentList());
                    function.ifBlock("value != null", ifAction -> {
                        ifAction.methodReturn("value");
                    }).elseBlock(elseAction -> {
                        if (settings.shouldClientLogger()) {
                            elseAction.line("throw logger.logExceptionAsError(new NullPointerException());");
                        } else {
                            elseAction.line("throw new NullPointerException();");
                        }
                    });
                } else {
                    function.methodReturn(String.format("%s(%s).block()", effectiveAsyncMethodName, clientMethod.getArgumentList()));
                }
            } else {
                function.line("%s(%s).block();", effectiveAsyncMethodName, clientMethod.getArgumentList());
            }
        });
    }

    /**
     * Generate javadoc for client method.
     *
     * @param clientMethod client method
     * @param typeBlock code block
     * @param restAPIMethod proxy method
     * @param useFullClassName whether to use fully-qualified class name in javadoc
     */
    public static void generateJavadoc(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, boolean useFullClassName) {
        // interface need a fully-qualified exception class name, since exception is usually only included in ProxyMethod
        typeBlock.javadocComment(comment -> {
            if (JavaSettings.getInstance().isLowLevelClient()) {
                generateProtocolMethodJavadoc(clientMethod, comment);
            } else {
                generateJavadoc(clientMethod, comment, restAPIMethod, useFullClassName);
            }
        });
    }

    /**
     * Generate javadoc for client method.
     *
     * @param clientMethod client method
     * @param commentBlock comment block
     * @param restAPIMethod proxy method
     * @param useFullClassName whether to use fully-qualified class name in javadoc
     */
    public static void generateJavadoc(ClientMethod clientMethod, JavaJavadocComment commentBlock, ProxyMethod restAPIMethod, boolean useFullClassName) {
        commentBlock.description(clientMethod.getDescription());
        List<ClientMethodParameter> methodParameters = clientMethod.getMethodInputParameters();
        for (ClientMethodParameter parameter : methodParameters) {
            commentBlock.param(parameter.getName(), parameterDescriptionOrDefault(parameter));
        }
        if (restAPIMethod != null && clientMethod.getParametersDeclaration() != null && !clientMethod.getParametersDeclaration().isEmpty()) {
            commentBlock.methodThrows("IllegalArgumentException", "thrown if parameters fail the validation");
        }
        if (restAPIMethod != null && restAPIMethod.getUnexpectedResponseExceptionType() != null) {
            commentBlock.methodThrows(useFullClassName
                            ? restAPIMethod.getUnexpectedResponseExceptionType().getFullName()
                            : restAPIMethod.getUnexpectedResponseExceptionType().getName(),
                    "thrown if the request is rejected by server");
        }
        if (restAPIMethod != null && restAPIMethod.getUnexpectedResponseExceptionTypes() != null) {
            for (Map.Entry<ClassType, List<HttpResponseStatus>> exception : restAPIMethod.getUnexpectedResponseExceptionTypes().entrySet()) {
                commentBlock.methodThrows(exception.getKey().toString(),
                        String.format("thrown if the request is rejected by server on status code %s",
                                exception.getValue().stream().map(status -> String.valueOf(status.code())).collect(Collectors.joining(", "))));
            }
        }
        commentBlock.methodThrows("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
        commentBlock.methodReturns(clientMethod.getReturnValue().getDescription());
    }

    protected static String parameterDescriptionOrDefault(ClientMethodParameter parameter) {
        String paramJavadoc = parameter.getDescription();
        if (CoreUtils.isNullOrEmpty(paramJavadoc)) {
            paramJavadoc = String.format("The %1$s parameter", parameter.getName());
        }
        return paramJavadoc;
    }

    private static void generateProtocolMethodJavadoc(ClientMethod clientMethod, JavaJavadocComment commentBlock) {
        commentBlock.description(clientMethod.getDescription());

        List<ProxyMethodParameter> optionalQueryParameters = clientMethod.getProxyMethod().getParameters()
                .stream().filter(p -> RequestParameterLocation.Query.equals(p.getRequestParameterLocation()) && !p.getIsRequired())
                .collect(Collectors.toList());
        if (!optionalQueryParameters.isEmpty()) {
            optionalParametersJavadoc("Optional Query Parameters", optionalQueryParameters, commentBlock);
        }

        List<ProxyMethodParameter> optionalHeaderParameters = clientMethod.getProxyMethod().getParameters()
                .stream().filter(p -> RequestParameterLocation.Header.equals(p.getRequestParameterLocation()) && !p.getIsRequired())
                .collect(Collectors.toList());
        if (!optionalHeaderParameters.isEmpty()) {
            optionalParametersJavadoc("Optional Header Parameters", optionalHeaderParameters, commentBlock);
        }

        Set<IType> typesInJavadoc = new HashSet<>();
        clientMethod.getMethodInputParameters()
                .stream().filter(p -> RequestParameterLocation.Body.equals(p.getLocation()))
                .map(ClientMethodParameter::getClientType)
                .findFirst()
                .ifPresent(iType -> requestBodySchemaJavadoc(iType, commentBlock, typesInJavadoc));

        IType responseBodyType;
        if (JavaSettings.getInstance().isLowLevelClient()) {
            responseBodyType = clientMethod.getProxyMethod().getRawResponseBodyType();
        } else {
            responseBodyType = clientMethod.getProxyMethod().getResponseBodyType();
        }
        if (responseBodyType != null && !responseBodyType.equals(PrimitiveType.Void)) {
            responseBodySchemaJavadoc(responseBodyType, commentBlock, typesInJavadoc);
        }

        clientMethod.getProxyMethod().getParameters()
                .stream().filter(p -> p.getIsRequired() && !p.getFromClient() && !p.getIsConstant()
                && p.getRequestParameterLocation() != RequestParameterLocation.Body)
                .forEach(parameter ->
                        commentBlock.param(parameter.getName(), parameterDescriptionOrDefault(parameter)));

        commentBlock.methodReturns("a DynamicRequest where customizations can be made before sent to the service");
    }

    private static void optionalParametersJavadoc(String title, List<ProxyMethodParameter> parameters, JavaJavadocComment commentBlock) {
        commentBlock.line(String.format("<p><strong>%s</strong></p>", title));
        commentBlock.line("<table border=\"1\">");
        commentBlock.line(String.format("    <caption>%s</caption>", title));
        commentBlock.line("    <tr><th>Name</th><th>Type</th><th>Description</th></tr>");
        for (ProxyMethodParameter parameter : parameters) {
            commentBlock.line(String.format("    <tr><td>%s</td><td>%s</td><td>%s</td></tr>",
                    parameter.getName(), CodeNamer.escapeXmlComment(parameter.getClientType().toString()), parameterDescriptionOrDefault(parameter)));
        }
        commentBlock.line("</table>");
    }

    private static void requestBodySchemaJavadoc(IType requestBodyType, JavaJavadocComment commentBlock, Set<IType> typesInJavadoc) {
        if (requestBodyType == null) {
            return;
        }
        commentBlock.line("<p><strong>Request Body Schema</strong></p>");
        commentBlock.line("<pre>{@code");
        bodySchemaJavadoc(requestBodyType, commentBlock, "", null, typesInJavadoc);
        commentBlock.line("}</pre>");
    }

    private static void responseBodySchemaJavadoc(IType responseBodyType, JavaJavadocComment commentBlock, Set<IType> typesInJavadoc) {
        if (responseBodyType == null) {
            return;
        }
        commentBlock.line("<p><strong>Response Body Schema</strong></p>");
        commentBlock.line("<pre>{@code");
        bodySchemaJavadoc(responseBodyType, commentBlock, "", null, typesInJavadoc);
        commentBlock.line("}</pre>");
    }

    private static void bodySchemaJavadoc(IType type, JavaJavadocComment commentBlock, String indent, String name, Set<IType> typesInJavadoc) {
        String nextIndent = indent + "    ";
        if (type instanceof ClassType
                && ((ClassType) type).getPackage().startsWith(JavaSettings.getInstance().getPackage())
                && !typesInJavadoc.contains(type)) {
            typesInJavadoc.add(type);
            ClientModel model = ClientModels.Instance.getModel(((ClassType) type).getName());
            if (name != null) {
                commentBlock.line(indent + name + ": {");
            } else {
                commentBlock.line(indent + "{");
            }
            List<ClientModelProperty> properties = new ArrayList<>();
            traverseProperties(model, properties);
            for (ClientModelProperty property : properties) {
                bodySchemaJavadoc(property.getClientType(), commentBlock, nextIndent, property.getName(), typesInJavadoc);
            }
            commentBlock.line(indent + "}");
        } else if (typesInJavadoc.contains(type)) {
            if (name != null) {
                commentBlock.line(indent + name + ": (recursive schema, see " + name + " above)");
            } else {
                commentBlock.line(indent + "(recursive schema, see above)");
            }
        } else if (type instanceof ListType) {
            if (name != null) {
                commentBlock.line(indent + name + ": [");
            } else {
                commentBlock.line(indent + "[");
            }
            bodySchemaJavadoc(((ListType) type).getElementType(), commentBlock, nextIndent, null, typesInJavadoc);
            commentBlock.line(indent + "]");
        } else if (type instanceof EnumType) {
            String values = ((EnumType) type).getValues().stream()
                    .map(ClientEnumValue::getValue)
                    .collect(Collectors.joining("/"));
            if (name != null) {
                commentBlock.line(indent + name + ": String(" + values + ")");
            } else {
                commentBlock.line(indent + "String(" + values + ")");
            }
        } else if (type instanceof MapType) {
            if (name != null) {
                commentBlock.line(indent + name + ": {");
            } else {
                commentBlock.line(indent + "{");
            }
            bodySchemaJavadoc(((MapType) type).getValueType(), commentBlock, nextIndent, "String", typesInJavadoc);
            commentBlock.line(indent + "}");
        } else {
            if (name != null) {
                commentBlock.line(indent + name + ": " + type.toString());
            } else {
                commentBlock.line(indent + type.toString());
            }
        }
    }

    private static void traverseProperties(ClientModel model, List<ClientModelProperty> properties) {
        if (model.getParentModelName() != null) {
            traverseProperties(ClientModels.Instance.getModel(model.getParentModelName()), properties);
        }
        properties.addAll(model.getProperties());
    }

    private static String parameterDescriptionOrDefault(ProxyMethodParameter parameter) {
        String paramJavadoc = parameter.getDescription();
        if (CoreUtils.isNullOrEmpty(paramJavadoc)) {
            paramJavadoc = String.format("The %1$s parameter", parameter.getName());
        }
        return CodeNamer.escapeXmlComment(paramJavadoc);
    }

    protected void generatePagedAsyncSinglePage(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");

        writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), function -> {
            AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
            AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            ApplyParameterTransformations(function, clientMethod, settings);
            ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);

            String serviceMethodCall = checkAndReplaceParamNameCollision(clientMethod, restAPIMethod, settings);
            if (settings.getAddContextParameter()) {
                if (settings.isContextClientMethodParameter() && contextInParameters(clientMethod)) {
                    function.line(String.format("return %s", serviceMethodCall));
                } else {
                    function.line(String.format("return FluxUtil.withContext(context -> %s)",
                        serviceMethodCall));
                }
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
                    if (settings.isLowLevelClient()) {
                        function.line("getValues(res.getValue(), \"%s\"),", clientMethod.getMethodPageDetails().getRawItemName());
                    } else {
                        function.line("res.getValue().%s(),", CodeNamer.getModelNamer().modelPropertyGetterName(clientMethod.getMethodPageDetails().getItemName()));
                    }
                    if (clientMethod.getMethodPageDetails().nonNullNextLink()) {
                        if (settings.isLowLevelClient()) {
                            function.line("getNextLink(res.getValue(), \"%s\"),", clientMethod.getMethodPageDetails().getRawNextLinkName());
                        } else {
                            function.line("res.getValue().%s(),", CodeNamer.getModelNamer().modelPropertyGetterName(clientMethod.getMethodPageDetails().getNextLinkName()));
                        }
                    } else {
                        function.line("null,");
                    }
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

    private String checkAndReplaceParamNameCollision(ClientMethod clientMethod, ProxyMethod restAPIMethod, JavaSettings settings) {
        List<String> serviceMethodArgs = clientMethod.getProxyMethodArguments(settings)
                .stream()
                .map(argVal -> {
                    if (clientMethod.getParameters().stream().filter(param -> param.getName().equals(argVal))
                            .anyMatch(param -> clientMethod.getMethodTransformationDetails().stream()
                                    .anyMatch(transformation -> param.getName().equals(transformation.getOutParameter().getName())))) {
                        return argVal + "Local";
                    }
                    return argVal;
                })
                .collect(Collectors.toList());
        String restAPIMethodArgumentList = String.join(", ", serviceMethodArgs);
        return String.format("service.%s(%s)", restAPIMethod.getName(), restAPIMethodArgumentList);
    }

    protected void generateSimpleAsyncRestResponse(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), function -> {
            AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
            AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            ApplyParameterTransformations(function, clientMethod, settings);
            ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);

            String serviceMethodCall = checkAndReplaceParamNameCollision(clientMethod, restAPIMethod, settings);
            if (settings.getAddContextParameter()) {
                if (settings.isContextClientMethodParameter() && contextInParameters(clientMethod)) {
                    function.methodReturn(serviceMethodCall);
                } else {
                    function.methodReturn(String.format("FluxUtil.withContext(context -> %s)",
                        serviceMethodCall));
                }
            } else {
                function.methodReturn(serviceMethodCall);
            }
        });
    }

    protected boolean contextInParameters(ClientMethod clientMethod) {
        return clientMethod.getParameters().stream().anyMatch(param -> getContextType().equals(param.getClientType()));
    }

    protected IType getContextType() {
        return ClassType.Context;
    }

    /**
     * Extension to write LRO async client method.
     *
     * @param clientMethod client method
     * @param typeBlock type block
     * @param restAPIMethod proxy method
     * @param settings java settings
     */
    protected void generateLongRunningAsync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {

    }

    /**
     * Extension to write LRO begin async client method.
     *
     * @param clientMethod client method
     * @param typeBlock type block
     * @param restAPIMethod proxy method
     * @param settings java settings
     */
    protected void generateLongRunningBeginAsync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {

    }

    /**
     * Extension to write LRO begin sync client method.
     *
     * @param clientMethod client method
     * @param typeBlock type block
     * @param restAPIMethod proxy method
     * @param settings java settings
     */
    protected void generateLongRunningBeginSync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {

    }
}
