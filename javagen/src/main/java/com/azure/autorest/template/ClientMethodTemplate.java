// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
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
import com.azure.autorest.util.MethodUtil;
import com.azure.autorest.util.TemplateUtil;
import com.azure.core.util.CoreUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Writes a ClientMethod to a JavaType block.
 */
public class ClientMethodTemplate extends ClientMethodTemplateBase {
    private static final ClientMethodTemplate INSTANCE = new ClientMethodTemplate();

    protected ClientMethodTemplate() {
    }

    public static ClientMethodTemplate getInstance() {
        return INSTANCE;
    }

    protected static void addValidations(JavaBlock function, List<String> expressionsToCheck, Map<String, String> validateExpressions, JavaSettings settings) {
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

    protected static void addOptionalVariables(JavaBlock function, ClientMethod clientMethod, List<ProxyMethodParameter> proxyMethodAndConstantParameters, JavaSettings settings) {
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

    protected static void addOptionalAndConstantVariables(JavaBlock function, ClientMethod clientMethod, List<ProxyMethodParameter> proxyMethodAndConstantParameters, JavaSettings settings) {
        addOptionalAndConstantVariables(function, clientMethod, proxyMethodAndConstantParameters, settings, true, true, true);
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
    protected static void addOptionalAndConstantVariables(JavaBlock function, ClientMethod clientMethod, List<ProxyMethodParameter> proxyMethodAndConstantParameters, JavaSettings settings,
                                                          boolean addOptional, boolean addConstant, boolean ignoreParameterNeedConvert) {
        for (ProxyMethodParameter parameter : proxyMethodAndConstantParameters) {
            IType parameterWireType = parameter.getWireType();
            if (parameter.getIsNullable()) {
                parameterWireType = parameterWireType.asNullable();
            }
            IType parameterClientType = parameter.getClientType();

            if (parameterWireType != ClassType.Base64Url &&
                    parameter.getRequestParameterLocation() != RequestParameterLocation.BODY &&
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

    protected static void applyParameterTransformations(JavaBlock function, ClientMethod clientMethod, JavaSettings settings) {
        for (MethodTransformationDetail transformation : clientMethod.getMethodTransformationDetails()) {
            if (transformation.getParameterMappings().isEmpty()) {
                // the case that this flattened parameter is not original parameter from any other parameters
                ClientMethodParameter outParameter = transformation.getOutParameter();
                if (outParameter.getIsRequired() && outParameter.getClientType() instanceof ClassType) {
                    function.line("%1$s %2$s = new %1$s();",
                            outParameter.getClientType(),
                            outParameter.getName());
                } else {
                    function.line("%1$s %2$s = null;",
                            outParameter.getClientType(),
                            outParameter.getName());
                }
                break;
            }

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
            boolean conditionalAssignment = !nullCheck.isEmpty() && !transformation.getOutParameter().getIsRequired() && !clientMethod.getOnlyRequiredParameters();
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

    protected static void convertClientTypesToWireTypes(JavaBlock function, ClientMethod clientMethod, List<ProxyMethodParameter> autoRestMethodRetrofitParameters, String methodClientReference, JavaSettings settings) {
        for (ProxyMethodParameter parameter : autoRestMethodRetrofitParameters) {
            IType parameterWireType = parameter.getWireType();

            if (parameter.getIsNullable()) {
                parameterWireType = parameterWireType.asNullable();
            }
            IType parameterClientType = parameter.getClientType();

            if (parameterWireType != ClassType.Base64Url &&
                    parameter.getRequestParameterLocation() != RequestParameterLocation.BODY &&
                    //parameter.getRequestParameterLocation() != RequestParameterLocation.FormData &&
                    (parameterClientType instanceof ArrayType || parameterClientType instanceof ListType)) {
                if (parameter.getExplode() == false) {
                    parameterWireType = ClassType.String;
                } else {
                    parameterWireType = new ListType(ClassType.String);
                }
            }

            if (parameterWireType != parameterClientType) {
                String parameterName = parameter.getParameterReference();
                String parameterWireName = parameter.getParameterReferenceConverted();

                boolean addedConversion = false;
                boolean alwaysNull = clientMethod.getOnlyRequiredParameters() && !parameter.getIsRequired();

                RequestParameterLocation parameterLocation = parameter.getRequestParameterLocation();
                if (parameterLocation != RequestParameterLocation.BODY &&
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
                        } else if (!parameter.getExplode()){
                            expression = String.format("JacksonAdapter.createDefaultSerializerAdapter()" +
                                            ".serializeList(%s, CollectionFormat.%s)", parameterName,
                                    parameter.getCollectionFormat().toString().toUpperCase());
                            if (settings.shouldUseIterable()) {
                                expression = String.format("JacksonAdapter.createDefaultSerializerAdapter()" +
                                                ".serializeIterable(%s, CollectionFormat.%s)", parameterName,
                                        parameter.getCollectionFormat().toString().toUpperCase());
                            }
                        } else {
                            expression = String.format("Optional.ofNullable(%s).map(Collection::stream)" +
                                    ".orElseGet(Stream::empty).map((item) -> Objects.toString(item, \"\"))" +
                                    ".collect(Collectors.toList())",
                                    parameterName);
                        }
                        function.line("%s %s = %s;", parameterWireTypeName, parameterWireName, expression);
                        addedConversion = true;
                    }
                }

                if (settings.shouldGenerateXmlSerialization() &&
                        parameterClientType instanceof ListType &&
                        (parameterLocation == RequestParameterLocation.BODY /*|| parameterLocation == RequestParameterLocation.FormData*/)) {
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

    private static boolean addSpecialHeadersToRequestOptions(JavaBlock function, ClientMethod clientMethod) {
        boolean requestOptionsLocal = false;
        if (MethodUtil.isMethodIncludeRepeatableRequestHeaders(clientMethod.getProxyMethod())) {
            requestOptionsLocal = true;
            function.line("RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;");
            function.line(String.format("requestOptionsLocal.setHeader(\"%1$s\", UUID.randomUUID().toString());", MethodUtil.REPEATABILITY_REQUEST_ID_HEADER));
            function.line(String.format("requestOptionsLocal.setHeader(\"%1$s\", DateTimeFormatter.ofPattern(\"EEE, dd MMM yyyy HH:mm:ss z\", Locale.ENGLISH).withZone(ZoneId.of(\"GMT\")).format(OffsetDateTime.now()));", MethodUtil.REPEATABILITY_FIRST_SENT_HEADER));
        }
        return requestOptionsLocal;
    }

    protected static void addSpecialHeadersToLocalVariables(JavaBlock function, ClientMethod clientMethod) {
        if (MethodUtil.isMethodIncludeRepeatableRequestHeaders(clientMethod.getProxyMethod())) {
            function.line(String.format("String %1$s = UUID.randomUUID().toString();", MethodUtil.REPEATABILITY_REQUEST_ID_VARIABLE_NAME));
            function.line(String.format("String %1$s = DateTimeFormatter.ofPattern(\"EEE, dd MMM yyyy HH:mm:ss z\", Locale.ENGLISH).withZone(ZoneId.of(\"GMT\")).format(OffsetDateTime.now());", MethodUtil.REPEATABILITY_FIRST_SENT_VARIABLE_NAME));
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
                generateLongRunningAsync(clientMethod, typeBlock, restAPIMethod, settings);
                break;

            case LongRunningBeginAsync:
                generateLongRunningBeginAsync(clientMethod, typeBlock, restAPIMethod, settings);
                break;

            case LongRunningBeginSync:
                generateLongRunningBeginSync(clientMethod, typeBlock, restAPIMethod, settings);
                break;

            case LongRunningSync:
                generateSyncMethod(clientMethod, typeBlock, restAPIMethod, settings);
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

            case SendRequestAsync:
                generateSendRequestAsync(clientMethod, typeBlock, settings);
                break;
            case SendRequestSync:
                generateSendRequestSync(clientMethod, typeBlock, settings);
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
            addOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            function.methodReturn(String.format("new PagedIterable<>(%s(%s))", clientMethod.getSimpleAsyncMethodName(), clientMethod.getArgumentList()));
        });
    }

    protected void generatePagingAsync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.COLLECTION)");
        if (clientMethod.getMethodPageDetails().nonNullNextLink()) {
            writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), function -> {
                addOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
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
                addOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
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
            addValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
            function.methodReturn(String.format("service.%s(%s)", restAPIMethod.getName(), parameter.getName()));
        });
    }

    protected void generateSimpleAsync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), (function -> {
            addOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
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
            addOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
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
                            elseAction.line("throw LOGGER.logExceptionAsError(new NullPointerException());");
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
        generateJavadocExceptions(clientMethod, commentBlock, useFullClassName);
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

    protected void generatePagedAsyncSinglePage(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");

        writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), function -> {
            addValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
            addOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            applyParameterTransformations(function, clientMethod, settings);
            convertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);

            boolean requestOptionsLocal = false;
            if (settings.isLowLevelClient()) {
                requestOptionsLocal = addSpecialHeadersToRequestOptions(function, clientMethod);
            } else {
                addSpecialHeadersToLocalVariables(function, clientMethod);
            }

            String serviceMethodCall = checkAndReplaceParamNameCollision(clientMethod, restAPIMethod, requestOptionsLocal, settings);
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

    private String checkAndReplaceParamNameCollision(ClientMethod clientMethod, ProxyMethod restAPIMethod, boolean useLocalRequestOptions, JavaSettings settings) {
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
                .map(argVal -> {
                    if (useLocalRequestOptions && "requestOptions".equals(argVal)) {
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
            addValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
            addOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            applyParameterTransformations(function, clientMethod, settings);
            convertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);

            boolean requestOptionsLocal = false;
            if (settings.isLowLevelClient()) {
                requestOptionsLocal = addSpecialHeadersToRequestOptions(function, clientMethod);
            } else {
                addSpecialHeadersToLocalVariables(function, clientMethod);
            }

            String serviceMethodCall = checkAndReplaceParamNameCollision(clientMethod, restAPIMethod, requestOptionsLocal, settings);
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
        String contextParam;
        if (clientMethod.getParameters().stream().anyMatch(p -> p.getClientType().equals(ClassType.Context))) {
            contextParam = "context";
        } else {
            contextParam = "Context.NONE";
        }
        String pollingStrategy = clientMethod.getMethodPollingDetails().getPollingStrategy()
                .replace("{httpPipeline}", clientMethod.getClientReference() + ".getHttpPipeline()")
                .replace("{context}", contextParam)
                .replace("{serializerAdapter}", clientMethod.getClientReference() + ".getSerializerAdapter()")
                .replace("{intermediate-type}", clientMethod.getMethodPollingDetails().getIntermediateType().toString())
                .replace("{final-type}", clientMethod.getMethodPollingDetails().getFinalType().toString());
        typeBlock.annotation("ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)");
        writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), function -> {
            function.line("return PollerFlux.create(Duration.ofSeconds(%s),", clientMethod.getMethodPollingDetails().getPollIntervalInSeconds());
            function.increaseIndent();
            function.line("() -> this.%s(%s),", clientMethod.getProxyMethod().getSimpleAsyncRestResponseMethodName(), clientMethod.getArgumentList());
            function.line(pollingStrategy + ",");
            TemplateUtil.writeLongRunningOperationTypeReference(function, clientMethod);
            function.decreaseIndent();
        });
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
        typeBlock.annotation("ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)");
        writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), function -> {
            function.methodReturn(String.format("this.%s(%s).getSyncPoller()",
                    clientMethod.getName() + "Async", clientMethod.getArgumentList()));
        });
    }

    protected void generateSendRequestAsync(ClientMethod clientMethod, JavaType typeBlock, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), function -> {
            function.methodReturn(String.format("FluxUtil.withContext(context -> %1$s.getHttpPipeline().send(%2$s, context).flatMap(response -> BinaryData.fromFlux(response.getBody()).map(body -> new SimpleResponse<>(response.getRequest(), response.getStatusCode(), response.getHeaders(), body))))",
                    clientMethod.getClientReference(), clientMethod.getArgumentList()));
        });
    }

    protected void generateSendRequestSync(ClientMethod clientMethod, JavaType typeBlock, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), function -> {
            function.methodReturn("this.sendRequestAsync(httpRequest).contextWrite(c -> c.putAll(FluxUtil.toReactorContext(context).readOnly())).block()");
        });
    }
}
