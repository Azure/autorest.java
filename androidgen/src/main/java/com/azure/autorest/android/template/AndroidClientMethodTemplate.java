package com.azure.autorest.android.template;

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
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.MethodPageDetails;
import com.azure.autorest.model.clientmodel.MethodTransformationDetail;
import com.azure.autorest.model.clientmodel.ParameterMapping;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaIfBlock;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.template.ClientMethodTemplate;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.CoreUtils;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Writes a ClientMethod to a JavaType block.
 */
public class AndroidClientMethodTemplate extends ClientMethodTemplate {
    private static AndroidClientMethodTemplate _instance = new AndroidClientMethodTemplate();

    protected AndroidClientMethodTemplate() {
    }

    public static AndroidClientMethodTemplate getInstance() {
        return _instance;
    }

    private static void addValidations(JavaBlock function,
                                       List<String> nullValueCheckExpressions,
                                       Map<String, String> validateExpressions,
                                       boolean isAsync,
                                       BiFunction<String, JavaBlock, Void> onError,
                                       JavaSettings settings) {
        if (settings.shouldClientSideValidations()) {
            for (String expressionToCheck : nullValueCheckExpressions) {
                JavaIfBlock nullCheck = function.ifBlock(expressionToCheck + " == null", ifBlock ->
                        onError.apply(String.format("new IllegalArgumentException(\"Parameter %s is required and "
                                + "cannot be null.\")", expressionToCheck), ifBlock));
                if (validateExpressions.containsKey(expressionToCheck)) {
                    nullCheck.elseBlock(elseBlock -> {
                        if (isAsync) {
                            List<String> exceptions = new ArrayList<>();
                            exceptions.add("Exception");
                            elseBlock.tryCatch(
                                    tryBlock -> {
                                        tryBlock.line(validateExpressions.get(expressionToCheck) + ";");
                                    },
                                    exceptions,
                                    "ex",
                                    catchBlock -> {
                                        onError.apply("ex", catchBlock);
                                    },
                                    null);
                        } else {
                            elseBlock.line(validateExpressions.get(expressionToCheck) + ";");
                        }
                    });
                }
            }
            for (Map.Entry<String, String> validateExpression : validateExpressions.entrySet()) {
                if (!nullValueCheckExpressions.contains(validateExpression.getKey())) {
                    function.ifBlock(validateExpression.getKey() + " != null", ifBlock -> {
                        if (isAsync) {
                            List<String> exceptions = new ArrayList<>();
                            exceptions.add("Exception");
                            ifBlock.tryCatch(
                                    tryBlock -> {
                                        ifBlock.line(validateExpression.getValue() + ";");
                                    },
                                    exceptions,
                                    "ex",
                                    catchBlock -> {
                                        onError.apply("ex", catchBlock);
                                    },
                                    null);
                        } else {
                            ifBlock.line(validateExpression.getValue() + ";");
                        }
                    });
                }
            }
        }
    }

    private static void addOptionalVariables(JavaBlock function, ClientMethod clientMethod, List<ProxyMethodParameter> proxyMethodAndConstantParameters, JavaSettings settings) {
        if (clientMethod.getOnlyRequiredParameters()) {
            addOptionalAndConstantVariables(function, clientMethod, proxyMethodAndConstantParameters, settings, true, false, false);
        }
    }

    private static void addOptionalAndConstantVariables(JavaBlock function,
                                                        ClientMethod clientMethod,
                                                        List<ProxyMethodParameter> proxyMethodAndConstantParameters,
                                                        JavaSettings settings) {
        addOptionalAndConstantVariables(function,
                clientMethod,
                proxyMethodAndConstantParameters,
                settings,
                true,
                true,
                true);
    }

    private static void addOptionalAndConstantVariables(JavaBlock function,
                                                          ClientMethod clientMethod,
                                                          List<ProxyMethodParameter> proxyMethodAndConstantParameters,
                                                          JavaSettings settings,
                                                          boolean addOptional,
                                                          boolean addConstant,
                                                          boolean ignoreParameterNeedConvert) {
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
            boolean alwaysNull = ignoreParameterNeedConvert &&
                    parameterWireType != parameterClientType &&
                    clientMethod.getOnlyRequiredParameters() &&
                    !parameter.getIsRequired();

            if (!parameter.getFromClient()
                    && !alwaysNull
                    && ((addOptional && clientMethod.getOnlyRequiredParameters() && !parameter.getIsRequired())
                        || (addConstant && parameter.getIsConstant()))) {
                String defaultValue
                        = parameterClientType.defaultValueExpression(parameter.getDefaultValue());
                function.line("final %s %s = %s;",
                        parameterClientType,
                        parameter.getParameterReference(),
                        defaultValue == null ? "null" : defaultValue);
            }
        }
    }

    private static void applyParameterTransformations(JavaBlock function, ClientMethod clientMethod, JavaSettings settings) {
        for (MethodTransformationDetail transformation : clientMethod.getMethodTransformationDetails()) {
            String nullCheck = transformation.getParameterMappings()
                    .stream()
                    .filter(m -> !m.getInputParameter().getIsRequired())
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

            boolean conditionalAssignment
                    = nullCheck != null &&
                        !nullCheck.isEmpty() &&
                        !transformation.getOutParameter().getIsRequired() &&
                        !clientMethod.getOnlyRequiredParameters();

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

            IType transformationOutputParameterModelType
                    = transformation.getOutParameter().getClientType();
            boolean generatedCompositeType = false;
            if (transformationOutputParameterModelType instanceof ClassType) {
                generatedCompositeType
                        = ((ClassType) transformationOutputParameterModelType).getPackage().startsWith(settings.getPackage());
            }
            if (generatedCompositeType &&
                    transformation.getParameterMappings()
                            .stream()
                            .anyMatch(m -> m.getOutputParameterProperty() != null && !m.getOutputParameterProperty().isEmpty())) {
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
                    getMapping
                            = String.format(".%s(%s)",
                                CodeNamer
                                        .getModelNamer()
                                        .modelPropertySetterName(mapping.getOutputParameterProperty()), inputPath);
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

    private static void convertClientTypesToWireTypes(JavaBlock function,
                                                      ClientMethod clientMethod,
                                                      List<ProxyMethodParameter> autoRestMethodRetrofitParameters,
                                                      String clientReference,
                                                      BiFunction<String, JavaBlock, Void> onError,
                                                      JavaSettings settings) {
        for (ProxyMethodParameter parameter : autoRestMethodRetrofitParameters) {
            if (parameter.getRequestParameterLocation() == RequestParameterLocation.Body) {
                IType parameterWireType = parameter.getWireType();
                if (parameter.getIsNullable()) {
                    parameterWireType = parameterWireType.asNullable();
                }
                final IType parameterClientType = parameter.getClientType();

                final String parameterName = parameter.getParameterReference();
                String parameterWireName = parameterName;
                if (parameterWireType != parameterClientType &&
                        settings.shouldGenerateXmlSerialization() &&
                        parameterClientType instanceof ListType) {
                    boolean alwaysNull = clientMethod.getOnlyRequiredParameters() && !parameter.getIsRequired();
                    parameterWireName = parameter.getParameterReferenceConverted();
                    function.line("%s %s = new %s(%s);",
                            parameter.getWireType(),
                            parameterWireName,
                            parameter.getWireType(),
                            alwaysNull ? "null" : parameterName);
                }
                String contentType = clientMethod.getProxyMethod().getRequestContentType();
                if (contentType == null || contentType.isEmpty()) {
                    if (parameterWireType == ArrayType.ByteArray || parameterWireType == ClassType.String) {
                        function.line(String.format("final okhttp3.RequestBody okHttp3RequestBody " +
                                "= okhttp3.RequestBody.create(okhttp3.MediaType.get(\"application/octet-stream\"), %s);", parameterWireName));
                    }
                } else {
                    function.line("final okhttp3.RequestBody okHttp3RequestBody;");
                    final List<String> exceptions = new ArrayList<>();
                    exceptions.add("java.io.IOException");
                    String serializeExpression = String.format("%sserializerAdapter.serialize(%s, %sresolveSerializerFormat(\"%s\"))",
                            clientReference,
                            parameterWireName,
                            clientReference,
                            contentType);

                    function.tryCatch(
                            tryBlock -> {
                                tryBlock.line(String.format("okHttp3RequestBody " +
                                        "= RequestBody.create(okhttp3.MediaType.get(\"%s\"), %s);", contentType, serializeExpression));
                            },
                            exceptions,
                            "ioe",
                            catchBlock -> {
                                onError.apply("new RuntimeException(ioe)", catchBlock);
                            },
                            null);
                }
            } else {
                IType parameterWireType = parameter.getWireType();
                if (parameter.getIsNullable()) {
                    parameterWireType = parameterWireType.asNullable();
                }
                final IType parameterClientType = parameter.getClientType();

                if (parameterClientType instanceof ArrayType || parameterClientType instanceof ListType) {
                    if (parameterWireType != ClassType.Base64Url) {
                        // Any "Non-Body" parameters of "Collection Type" will need to converted to
                        // String.
                        parameterWireType = ClassType.String;
                    }
                }

                if (parameterWireType != parameterClientType) {
                    // e.g. contentMd5
                    final String parameterName = parameter.getParameterReference();
                    // e.g. contentMd5Converted
                    final String parameterWireName = parameter.getParameterReferenceConverted();

                    boolean addedConversion = false;
                    boolean alwaysNull = clientMethod.getOnlyRequiredParameters() && !parameter.getIsRequired();

                    if (parameterClientType instanceof ArrayType || parameterClientType instanceof ListType) {
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
                                expression
                                        = String.format("JacksonAdapter.createDefaultSerializerAdapter().serializeList(%s, CollectionFormat.%s)", parameterName, parameter.getCollectionFormat().toString().toUpperCase());
                            }
                            function.line("%s %s = %s;", parameterWireTypeName, parameterWireName, expression);
                            addedConversion = true;
                        }
                    }

                    if (!addedConversion) {
                        function.line(parameter.convertFromClientType(parameterName, parameterWireName,
                                clientMethod.getOnlyRequiredParameters() && !parameter.getIsRequired(),
                                parameter.getIsConstant() || alwaysNull));
                    }
                }
            }
        }
    }

    private static void parameterizedTypeExpression(IType bodyType, StringBuilder stringBuilder) {
        if (bodyType instanceof ListType) {
            ListType listType = ((ListType) bodyType);
            stringBuilder.append("createParameterizedType(");
            stringBuilder.append(String.format("%s.%s.class", listType.getPackage(), listType.getName()));
            stringBuilder.append(", ");
            parameterizedTypeExpression(listType.getElementType(), stringBuilder);
            stringBuilder.append(")");
        } else if (bodyType instanceof MapType) {
            ListType mapType = ((ListType) bodyType);
            stringBuilder.append("createParameterizedType(");
            stringBuilder.append(String.format("%s.%s.class", mapType.getPackage(), mapType.getName()));
            stringBuilder.append(", String, ");
            parameterizedTypeExpression(mapType.getElementType(), stringBuilder);
            stringBuilder.append(")");
        } else {
            stringBuilder.append(String.format("%s.class", bodyType));
        }
    }

    public void write(ClientMethod clientMethod, JavaType typeBlock) {
        JavaSettings settings = JavaSettings.getInstance();

        ProxyMethod restAPIMethod = clientMethod.getProxyMethod();
        generateJavadoc(clientMethod, typeBlock, restAPIMethod);

        clientMethod.getProxyMethod().getRequestContentType();
        switch (clientMethod.getType()) {
            case SimpleAsyncRestResponse:
                // async method
                writeAsyncWithRestResponseMethod(clientMethod, typeBlock, settings, restAPIMethod, false);
                break;

            case SimpleSync:
                // sync method
                writeSyncWithResponseMethod(clientMethod, typeBlock, settings, restAPIMethod, false);
                break;

            case PagingSync:
                writePagingSyncMethod(clientMethod, typeBlock, settings, restAPIMethod);
                break;

            case PagingAsync:
                writePagingAsyncMethod(clientMethod, typeBlock, settings, restAPIMethod);
                break;
            case PagingAsyncSinglePage:
                generatePagedAsyncSinglePage(clientMethod, typeBlock, restAPIMethod, settings);
                break;

            case LongRunningSync:
                // LongRunningAsync not applicable to data-plane android
                break;

            case LongRunningAsync:
                // LongRunningAsync not applicable to data-plane android
                break;

            case LongRunningBeginAsync:
                // LongRunningBeginAsync not applicable to data-plane android
                break;

            case LongRunningBeginSync:
                // LongRunningBeginSync not applicable to data-plane android
                break;

            case Resumable:
                // Resumable not applicable to data-plane android
                break;

            case SimpleAsync:
                writeSimpleAsyncMethod(clientMethod, typeBlock, settings, restAPIMethod);
                break;
        }
    }

    private void writeSimpleAsyncMethod(ClientMethod clientMethod, JavaType typeBlock, JavaSettings settings, ProxyMethod restAPIMethod) {
        typeBlock.publicMethod(clientMethod.getDeclaration(), (function -> {
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

    private void writePagingAsyncMethod(ClientMethod clientMethod, JavaType typeBlock, JavaSettings settings, ProxyMethod restAPIMethod) {
        final ClientMethodParameter callbackParameter = getCallbackParameter(clientMethod);
        final GenericType callbackParameterType = (GenericType) callbackParameter.getClientType();
        final GenericType callbackDataType = (GenericType) callbackParameterType.getTypeArguments()[0];
        final IType elementType = callbackDataType.getTypeArguments()[0];
        if (callbackDataType.equals(GenericType.AndroidPage(elementType))) {
            writeAsyncWithRestResponseMethod(clientMethod, typeBlock, settings, restAPIMethod, true);
            return;
        }

        typeBlock.publicMethod(clientMethod.getDeclaration(),
            function -> {
                String retrieverClassName = AsyncPageRetrieverTemplate.getRetrieverClassName(elementType);
                StringBuilder retrieverConstructionBuilder = new StringBuilder();
                retrieverConstructionBuilder.append(String.format("%1$s retriever = new %1$s(", retrieverClassName));
                boolean hasPreviousParam = false;
                for (ClientMethodParameter clientMethodParameter : clientMethod.getMethodParameters()) {
                    if (clientMethodParameter.equals(callbackParameter)) {
                        continue;
                    }
                    if (hasPreviousParam) {
                        retrieverConstructionBuilder.append(", ");
                    }
                    retrieverConstructionBuilder.append(clientMethodParameter.getName());
                    hasPreviousParam = true;
                }
                if (hasPreviousParam) {
                    retrieverConstructionBuilder.append(", ");
                }
                retrieverConstructionBuilder.append("this);");
                function.line(retrieverConstructionBuilder.toString());
                function.line(String.format("%1$s.onSuccess(new %2$s(retriever), null);", callbackParameter.getName(), callbackDataType));
            });
    }

    private void writePagingSyncMethod(ClientMethod clientMethod, JavaType typeBlock, JavaSettings settings, ProxyMethod restAPIMethod) {
        GenericType methodReturnType = (GenericType) clientMethod.getReturnValue().getType();
        IType elementType = methodReturnType.getTypeArguments()[0];
        if (methodReturnType.equals(GenericType.AndroidHttpResponse(elementType))
            || methodReturnType.equals(GenericType.AndroidPage(elementType))) {
            writeSyncWithResponseMethod(clientMethod, typeBlock, settings, restAPIMethod, true);
            return;
        }

        typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
            String retrieverClassName = methodReturnType.equals(GenericType.AndroidPageResponseCollection(elementType))
                    ? PageResponseRetrieverTemplate.getRetrieverClassName(elementType)
                    : PageRetrieverTemplate.getRetrieverClassName(elementType);
            StringBuilder retrieverConstructionBuilder = new StringBuilder();
            retrieverConstructionBuilder.append(String.format("%1$s retriever = new %1$s(", retrieverClassName));
            boolean hasPreviousParam = false;
            for (ClientMethodParameter clientMethodParameter : clientMethod.getMethodParameters()) {
                if (hasPreviousParam) {
                    retrieverConstructionBuilder.append(", ");
                }
                retrieverConstructionBuilder.append(clientMethodParameter.getName());
                hasPreviousParam = true;
            }
            if (hasPreviousParam) {
                retrieverConstructionBuilder.append(", ");
            }
            retrieverConstructionBuilder.append("this);");
            function.line(retrieverConstructionBuilder.toString());
            function.line(String.format("return new %s(retriever);", methodReturnType));
        });
    }

    private void writeSyncWithResponseMethod(ClientMethod clientMethod,
                                             JavaType typeBlock,
                                             JavaSettings settings,
                                             ProxyMethod restAPIMethod,
                                             boolean isPaging) {
        boolean generateSyncMethodWithOnlyRequiredParams = clientMethod.getOnlyRequiredParameters();
        if (generateSyncMethodWithOnlyRequiredParams) {
            typeBlock.publicMethod(clientMethod.getDeclaration(),
                    function -> {
                        // sync-method-with-only-required-params delegate call to
                        // sync-method-with-required-and-optional-param.
                        //
                        addOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                        IType returnType = clientMethod.getReturnValue().getType();
                        if (returnType == PrimitiveType.Void) {
                            function.line("%sWithRestResponse(%s);",
                                    clientMethod.getName(), clientMethod.getArgumentList());
                        } else {
                            function.line("return %sWithRestResponse(%s).getValue();",
                                    clientMethod.getName(), clientMethod.getArgumentList());
                        }
                    });
        } else {
            // ***WithRestResponse method.
            //
            typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {

                final String clientReferenceDot
                        = clientMethod.getClientReference() != null
                        ? clientMethod.getClientReference() + "."
                        : "";

                addValidations(function,
                        clientMethod.getRequiredNullableParameterExpressions(),
                        clientMethod.getValidateExpressions(),
                        false,
                        (exception, onError) -> {
                            onError.line("throw %s;", exception);
                            return null;
                        },
                        settings);
                addOptionalAndConstantVariables(function,
                        clientMethod,
                        restAPIMethod.getParameters(),
                        settings);
                applyParameterTransformations(function,
                        clientMethod,
                        settings);
                convertClientTypesToWireTypes(function,
                        clientMethod,
                        restAPIMethod.getParameters(),
                        clientReferenceDot,
                        (exception, onError) -> {
                            onError.line(String.format("throw %s;", exception));
                            return null;
                        },
                        settings);
                String retrofitAPIArgsList = String.join(", ",
                        clientMethod.getProxyMethodArguments(settings));
                String retrofitAPICall = String.format("service.%s(%s)",
                        restAPIMethod.getName(),
                        retrofitAPIArgsList);

                function.line(String.format("final retrofit2.Response<ResponseBody> response = %sexecuteRetrofitCall(%s);",
                        clientReferenceDot,
                        retrofitAPICall));

                function.ifBlock("response.isSuccessful()", responseSuccessBlock -> {
                    List<HttpResponseStatus> successStatusCodes = clientMethod.getProxyMethod().getResponseExpectedStatusCodes();
                    String successCodeExpression = successStatusCodes
                            .stream()
                            .map(statusCode -> String.format("response.code() == %d", statusCode.code()))
                            .collect(Collectors.joining(" || "));

                    responseSuccessBlock.ifBlock(successCodeExpression, succeededCodeBlock -> {
                        writeSyncSuccessWithRestResponseBlock(clientMethod, clientReferenceDot, succeededCodeBlock, isPaging);
                    }).elseBlock(errorCodeBlock -> {
                        errorCodeBlock.line("final String strContent = %sreadAsString(response.body());", clientReferenceDot);
                        ClassType exceptionType = clientMethod.getProxyMethod().getUnexpectedResponseExceptionType();
                        String exceptionCreateExpression = String.format("new %s(strContent, response.raw())", exceptionType.getName());
                        errorCodeBlock.line(String.format("throw %s;", exceptionCreateExpression));
                    });
                }).elseBlock(responseFailedBlock -> {
                    responseFailedBlock.line("final String strContent = %sreadAsString(response.errorBody());", clientReferenceDot);
                    responseFailedBlock.line("throw new HttpResponseException(strContent, response.raw());");
                });
            });
        }
    }

    private void writeSyncSuccessWithRestResponseBlock(ClientMethod clientMethod,
                                                       String clientReferenceDot,
                                                       JavaBlock succeededCodeBlock,
                                                       boolean isPaging) {
        IType bodyType = clientMethod.getProxyMethod().getReturnType(); // responseType.getTypeArguments()[0];
        if (bodyType == PrimitiveType.Void) {
            succeededCodeBlock.line("response.body().close();");
            succeededCodeBlock.methodReturn("new Response<>(response.raw().request(),\n" +
                            "                        response.code(),\n" +
                            "                        response.headers(),\n" +
                            "                        null)");
        } else if (bodyType == ClassType.OkHttp3ResponseBody) {
            succeededCodeBlock.methodReturn("new Response<>(response.raw().request(),\n" +
                    "                        response.code(),\n" +
                    "                        response.headers(),\n" +
                    "                        response.body())");
        } else {
            final String bodyJvaType;
            if (bodyType instanceof ListType || bodyType instanceof MapType) {
                StringBuilder builder = new StringBuilder();
                builder.append(clientReferenceDot);
                parameterizedTypeExpression(bodyType, builder);
                bodyJvaType = builder.toString();
            } else {
                bodyJvaType = String.format("%s.class", bodyType);
            }

            if (isPaging){
                GenericType responseType = (GenericType) clientMethod.getReturnValue().getType();
                GenericType pageType = (GenericType) responseType.getTypeArguments()[0];
                IType elementType = pageType.getTypeArguments()[0];
                List<String> exceptions = new ArrayList<>();
                exceptions.add("Exception");
                succeededCodeBlock.line("final %s decodedResult;", bodyType);
                succeededCodeBlock.tryCatch(tryBlock -> {
                            succeededCodeBlock.line("decodedResult = %sdeserializeContent(response.headers(), response.body(), %s);",
                                    clientReferenceDot,
                                    bodyJvaType);
                        },
                        exceptions,
                        "ex",
                        catchBlock -> {
                            catchBlock.line("final String strContent = %sreadAsString(response.body());", clientReferenceDot);
                            ClassType exceptionType = clientMethod.getProxyMethod().getUnexpectedResponseExceptionType();
                            String exceptionCreateExpression = String.format("new %s(strContent, response.raw())", exceptionType.getName());
                            catchBlock.line(String.format("throw %s;", exceptionCreateExpression));
                        },
                        null);
                ClientMethod nextMethod = clientMethod.getMethodPageDetails().getNextMethod();
                String pageId = (nextMethod == null || nextMethod == clientMethod)
                        ? clientMethod.getMethodPageDetails().getNextLinkName()
                        : "response.raw().request().url().toString()";
                succeededCodeBlock.methodReturn(String.format("new Response<>(response.raw().request(),\n" +
                                "                        response.code(),\n" +
                                "                        response.headers(),\n" +
                                "                        new Page<%1$s>(%2$s, decodedResult.getValue(), decodedResult.getNextLink()))",
                        elementType, pageId));
            }
            else {
                succeededCodeBlock.methodReturn(String.format("new Response<>(response.raw().request(),\n" +
                                "                        response.code(),\n" +
                                "                        response.headers(),\n" +
                                "                        %sdeserializeContent(response.headers(), response.body(), %s))",
                        clientReferenceDot,
                        bodyJvaType));
            }
        }
    }

    private void writeAsyncWithRestResponseMethod(ClientMethod clientMethod,
                                                  JavaType typeBlock,
                                                  JavaSettings settings,
                                                  ProxyMethod restAPIMethod,
                                                  boolean isPaging) {
        boolean generateAsyncMethodWithOnlyRequiredParams = clientMethod.getOnlyRequiredParameters();
        if (generateAsyncMethodWithOnlyRequiredParams) {
            typeBlock.publicMethod(clientMethod.getDeclaration(),
                    function -> {
                        // async-method-with-only-required-params delegate call to
                        // async-method-with-required-and-optional-param.
                        //
                        addOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                        function.line("%s(%s);",
                                clientMethod.getName(), clientMethod.getArgumentList());
                    });
        } else {
            typeBlock.publicMethod(clientMethod.getDeclaration(),
                    function -> {
                        final Optional<ClientMethodParameter> lastParamOpt = clientMethod.getMethodRequiredParameters()
                                .stream()
                                .reduce((current, next) -> next);
                        final ClientMethodParameter lastParam = lastParamOpt.get();
                        final GenericType callbackParameter = (GenericType) lastParam.getWireType();
                        final String callbackParameterName = lastParam.getName();

                        final String clientReferenceDot;
                        if (clientMethod.getClientReference().equals("this")) {
                            // 'this' is not resolvable from callback remove it.
                            clientReferenceDot = "";
                        } else {
                            clientReferenceDot = clientMethod.getClientReference().replace("this.", "") + ".";
                        }

                        addValidations(function,
                                clientMethod.getRequiredNullableParameterExpressions(),
                                clientMethod.getValidateExpressions(),
                                true,
                                (exception, onError) -> {
                                    onError.line("%s.onFailure(%s, null);", callbackParameterName, exception);
                                    onError.line("return;");
                                    return null;
                                },
                                settings);
                        addOptionalAndConstantVariables(function,
                                clientMethod,
                                restAPIMethod.getParameters(),
                                settings);
                        applyParameterTransformations(function,
                                clientMethod,
                                settings);
                        convertClientTypesToWireTypes(function,
                                clientMethod,
                                restAPIMethod.getParameters(),
                                clientReferenceDot,
                                (exception, onError) -> {
                                    onError.line(String.format("%s.onFailure(%s, null);", callbackParameterName, exception));
                                    onError.line("return;");
                                    return null;
                                },
                                settings);
                        String retrofitAPIArgsList = String.join(", ",
                                clientMethod.getProxyMethodArguments(settings));
                        String retrofitAPICall = String.format("service.%s(%s)",
                                restAPIMethod.getName(),
                                retrofitAPIArgsList);

                        function.line("Call<ResponseBody> call = %s;", retrofitAPICall);
                        function.anonymousClass("retrofit2.Callback<ResponseBody>",
                                "retrofitCallback",
                                anonymousCls -> {
                            anonymousCls.annotation("Override");
                            anonymousCls.publicMethod("void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response)",
                                    onResponseBlock -> onResponseBlock.ifBlock("response.isSuccessful()", responseSuccessBlock -> {
                                        List<HttpResponseStatus> successStatusCodes = clientMethod.getProxyMethod().getResponseExpectedStatusCodes();
                                        String successCodeExpression = successStatusCodes
                                                .stream()
                                                .map(statusCode -> String.format("response.code() == %d", statusCode.code()))
                                                .collect(Collectors.joining(" || "));


                                        responseSuccessBlock.ifBlock(successCodeExpression, succeededCodeBlock -> {
                                            writeAsyncSuccessBlock(clientMethod, callbackParameterName, clientReferenceDot, succeededCodeBlock, (JavaClass) typeBlock, isPaging);
                                        }).elseBlock(errorCodeBlock -> {
                                            errorCodeBlock.line("final String strContent = %sreadAsString(response.body());", clientReferenceDot);
                                            ClassType exceptionType = clientMethod.getProxyMethod().getUnexpectedResponseExceptionType();
                                            String exceptionCreateExpression = String.format("new %s(strContent, response.raw())", exceptionType.getName());
                                            errorCodeBlock
                                                    .line(String.format("%s.onFailure(%s, response.raw());",
                                                            callbackParameterName,
                                                            exceptionCreateExpression));
                                        });
                                    }).elseBlock(responseFailureBlock -> {
                                        responseFailureBlock.line("final String strContent = %sreadAsString(response.errorBody());", clientReferenceDot);
                                        responseFailureBlock.line(String.format("%s.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());",
                                                callbackParameterName));
                                    }));

                            anonymousCls.annotation("Override");
                            anonymousCls.publicMethod("void onFailure(Call<ResponseBody> call, Throwable t)",
                                    onFailureBlock -> {
                                        onFailureBlock.line(String.format("%s.onFailure(t, null);", callbackParameterName));
                                    });
                        });
                        function.line("call.enqueue(retrofitCallback);");
                    });
        }
    }

    private void writeAsyncSuccessBlock(ClientMethod clientMethod,
                                        String callbackParameterName,
                                        String clientReferenceDot,
                                        JavaBlock succeededCodeBlock,
                                        JavaClass classBlock,
                                        boolean isPaging) {
        IType bodyType = clientMethod.getProxyMethod().getReturnType(); // callbackParameter.getTypeArguments()[0];
        if (bodyType == PrimitiveType.Void) {
            succeededCodeBlock.line("response.body().close();");
            succeededCodeBlock
                    .line(String.format("%s.onSuccess(null, response.raw());",
                            callbackParameterName));
        } else if (bodyType == ClassType.OkHttp3ResponseBody) {
            succeededCodeBlock
                    .line(String.format("%s.onSuccess(response.body(), response.raw());",
                            callbackParameterName));
        } else {
            final String bodyJvaType;
            if (bodyType instanceof ListType || bodyType instanceof MapType) {
                StringBuilder builder = new StringBuilder();
                builder.append(clientReferenceDot);
                parameterizedTypeExpression(bodyType, builder);
                bodyJvaType = builder.toString();
            } else {
                bodyJvaType = String.format("%s.class", bodyType);
            }

            List<String> exceptions = new ArrayList<>();
            exceptions.add("Exception");
            succeededCodeBlock.line("final %s decodedResult;", bodyType);
            succeededCodeBlock.tryCatch(tryBlock -> {
                        succeededCodeBlock.line("decodedResult = %sdeserializeContent(response.headers(), response.body(), %s);",
                                clientReferenceDot,
                                bodyJvaType);
                    },
                    exceptions,
                    "ex",
                    catchBlock -> {
                        catchBlock
                                .line(String.format("%s.onFailure(ex, response.raw());",
                                        callbackParameterName));
                        catchBlock.line("return;");
                    },
                    null);

            if (isPaging) {
                ClientMethodParameter callbackParameter = getCallbackParameter(clientMethod);
                final GenericType callbackParameterType = (GenericType) callbackParameter.getWireType();
                final GenericType pageType = (GenericType) callbackParameterType.getTypeArguments()[0];
                final IType elementType = pageType.getTypeArguments()[0];
                MethodPageDetails pageDetails = clientMethod.getMethodPageDetails();
                final String pageId = (pageDetails.getNextMethod() == null) ? pageDetails.getNextLinkName() : "response.raw().request().url().toString()";
                succeededCodeBlock
                        .line(String.format("%1$s.onSuccess(new Page<%2$s>(%3$s, decodedResult.getValue(), decodedResult.getNextLink()), response.raw());",
                                callbackParameterName, elementType, pageId));

            }
            else {
                succeededCodeBlock
                        .line(String.format("%s.onSuccess(decodedResult, response.raw());",
                                callbackParameterName));
            }
        }
    }

    public static ClientMethodParameter getCallbackParameter(ClientMethod clientMethod) {
        final Optional<ClientMethodParameter> lastParamOpt = clientMethod.getMethodRequiredParameters()
                .stream()
                .reduce((current, next) -> next);
        return lastParamOpt.get();
    }

    protected void generateJavadoc(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod) {
        typeBlock.javadocComment(comment -> {
            comment.description(clientMethod.getDescription());
            List<ClientMethodParameter> methodParameters = clientMethod.getOnlyRequiredParameters()
                    ? clientMethod.getMethodRequiredParameters()
                    : clientMethod.getMethodParameters();
            for (ClientMethodParameter parameter : methodParameters) {
                comment.param(parameter.getName(), parameterDescriptionOrDefault(parameter));
            }
            if (clientMethod.getParametersDeclaration() != null && !clientMethod.getParametersDeclaration().isEmpty()) {
                comment.methodThrows("IllegalArgumentException", "thrown if parameters fail the validation");
            }
            if (restAPIMethod.getUnexpectedResponseExceptionType() != null) {
                comment.methodThrows(restAPIMethod.getUnexpectedResponseExceptionType().toString(), "thrown if the request is rejected by server");
            }
            if (restAPIMethod.getUnexpectedResponseExceptionTypes() != null) {
                for (Map.Entry<ClassType, List<HttpResponseStatus>> exception : restAPIMethod.getUnexpectedResponseExceptionTypes().entrySet()) {
                    comment.methodThrows(exception.getKey().toString(),
                            String.format("thrown if the request is rejected by server on status code %s",
                                    exception.getValue().stream().map(status -> String.valueOf(status.code())).collect(Collectors.joining(", "))));
                }
            }
            comment.methodThrows("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
            comment.methodReturns(clientMethod.getReturnValue().getDescription());
        });
    }

    protected String parameterDescriptionOrDefault(ClientMethodParameter parameter) {
        String paramJavadoc = parameter.getDescription();
        if (CoreUtils.isNullOrEmpty(paramJavadoc)) {
            paramJavadoc = String.format("The %1$s parameter", parameter.getName());
        }
        return paramJavadoc;
    }

    protected void generatePagedAsyncSinglePage(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {

        if (clientMethod.getMethodPageDetails().nonNullNextLink()) {
            typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
                addOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                applyParameterTransformations(function, clientMethod, settings);
                ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);
                String restAPIMethodArgumentList = String.join(", ", clientMethod.getProxyMethodArguments(settings));
                String serviceMethodCall = String.format("service.%s(%s)", restAPIMethod.getName(), restAPIMethodArgumentList);
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
                AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
                addOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                applyParameterTransformations(function, clientMethod, settings);
                ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);
                String restAPIMethodArgumentList = String.join(", ", clientMethod.getProxyMethodArguments(settings));
                String serviceMethodCall = String.format("service.%s(%s)", restAPIMethod.getName(), restAPIMethodArgumentList);
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

    protected void generateSimpleAsyncRestResponse(ClientMethod clientMethod,
                                                   JavaType typeBlock,
                                                   ProxyMethod restAPIMethod,
                                                   JavaSettings settings) {
        typeBlock.publicMethod(clientMethod.getDeclaration(),
                function -> {
                    AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
                    addOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                    applyParameterTransformations(function, clientMethod, settings);
                    ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);
                    String restAPIMethodArgumentList = String.join(", ", clientMethod.getProxyMethodArguments(settings));
                    String serviceMethodCall = String.format("service.%s(%s)", restAPIMethod.getName(), restAPIMethodArgumentList);
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
        return clientMethod.getParameters().stream().anyMatch(param -> ClassType.Context.equals(param.getClientType()));
    }
}
