// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ConvenienceMethod;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ParameterSynthesizedOrigin;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.CodeNamer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

abstract class ConvenienceMethodTemplateBase implements IJavaTemplate<ConvenienceMethod, JavaClass> {

    protected ConvenienceMethodTemplateBase() {
    }

    public final void write(ConvenienceMethod convenienceMethodObj, JavaClass classBlock) {
        if (!isMethodIncluded(convenienceMethodObj)) {
            return;
        }

        ClientMethod protocolMethod = convenienceMethodObj.getProtocolMethod();
        convenienceMethodObj.getConvenienceMethods().stream()
                .filter(this::isMethodIncluded)
                .forEach(convenienceMethod -> {
                    // comments and javadoc
                    classBlock.blockComment("Generated convenience method for " + getMethodName(protocolMethod));

                    classBlock.javadocComment(comment -> {
                        ClientMethodTemplate.generateJavadoc(convenienceMethod, comment, convenienceMethod.getProxyMethod(), true);
                    });

                    // convenience method
                    String methodDeclaration = String.format("%1$s %2$s(%3$s)", convenienceMethod.getReturnValue().getType(), getMethodName(convenienceMethod), convenienceMethod.getParametersDeclaration());
                    classBlock.publicMethod(methodDeclaration, methodBlock -> {
                        // RequestOptions
                        methodBlock.line("RequestOptions requestOptions = new RequestOptions();");

                        // matched parameters from convenience method to protocol method
                        Map<MethodParameter, MethodParameter> parametersMap =
                                findParametersForConvenienceMethod(convenienceMethod, protocolMethod);
                        Map<String, String> parameterExpressionsMap = new HashMap<>();
                        for (Map.Entry<MethodParameter, MethodParameter> entry : parametersMap.entrySet()) {
                            MethodParameter parameter = entry.getKey();
                            MethodParameter protocolParameter = entry.getValue();

                            if (parameter.getProxyMethodParameter().getOrigin() == ParameterSynthesizedOrigin.CONTEXT) {
                                // Context
                                methodBlock.line(String.format("requestOptions.setContext(%s);", parameter.getName()));
                            } else if (protocolParameter != null) {
                                // protocol method parameter exists
                                String expression = expressionConvertToType(parameter.getName(), parameter, protocolParameter);
                                parameterExpressionsMap.put(protocolParameter.getName(), expression);
                            } else {
                                // protocol method parameter not exist, set the parameter via RequestOptions
                                switch (parameter.getProxyMethodParameter().getRequestParameterLocation()) {
                                    case HEADER: {
                                        Consumer<JavaBlock> writeLine = javaBlock -> javaBlock.line(
                                                String.format("requestOptions.setHeader(%1$s, %2$s);",
                                                        ClassType.String.defaultValueExpression(parameter.getSerializedName()),
                                                        expressionConvertToString(parameter.getName(), parameter.getClientMethodParameter().getClientType())));
                                        if (!parameter.getClientMethodParameter().getIsRequired()) {
                                            methodBlock.ifBlock(String.format("%s != null", parameter.getName()), ifBlock -> {
                                                writeLine.accept(ifBlock);
                                            });
                                        } else {
                                            writeLine.accept(methodBlock);
                                        }
                                    }
                                    break;

                                    case QUERY: {
                                        // TODO: array
                                        Consumer<JavaBlock> writeLine = javaBlock -> javaBlock.line(
                                                String.format("requestOptions.addQueryParam(%1$s, %2$s);",
                                                        ClassType.String.defaultValueExpression(parameter.getSerializedName()),
                                                        expressionConvertToString(parameter.getName(), parameter.getClientMethodParameter().getClientType())));
                                        if (!parameter.getClientMethodParameter().getIsRequired()) {
                                            methodBlock.ifBlock(String.format("%s != null", parameter.getName()), ifBlock -> {
                                                writeLine.accept(ifBlock);
                                            });
                                        } else {
                                            writeLine.accept(methodBlock);
                                        }
                                    }
                                    break;

                                    case BODY: {
                                        Consumer<JavaBlock> writeLine = javaBlock -> javaBlock.line(
                                                String.format("requestOptions.setBody(%s);",
                                                        expressionConvertToBinaryData(parameter.getName(), parameter.getClientMethodParameter().getClientType())));
                                        if (!parameter.getClientMethodParameter().getIsRequired()) {
                                            methodBlock.ifBlock(String.format("%s != null", parameter.getName()), ifBlock -> {
                                                writeLine.accept(ifBlock);
                                            });
                                        } else {
                                            writeLine.accept(methodBlock);
                                        }
                                    }
                                    break;
                                }
                            }
                        }

                        // invocation with protocol method parameters and RequestOptions
                        String invocationExpression = protocolMethod.getMethodInputParameters().stream()
                                .map(p -> {
                                    String expression = parameterExpressionsMap.get(p.getName());
                                    if (expression == null) {
                                        expression = p.getName();
                                    }
                                    return expression;
                                })
                                .collect(Collectors.joining(", "));

                        // write the invocation of protocol method, and related type conversion
                        writeInvocationAndConversion(convenienceMethod, protocolMethod, invocationExpression, methodBlock);
                    });
                });
    }

    /**
     * Whether the convenience method should be included.
     *
     * @param method the convenience method.
     * @return Whether include the convenience method.
     */
    protected abstract boolean isMethodIncluded(ClientMethod method);

    /**
     * Whether the convenience/protocol method should be included.
     *
     * @param method the convenience/protocol method.
     * @return Whether include the convenience/protocol method.
     */
    protected abstract boolean isMethodIncluded(ConvenienceMethod method);

    protected boolean isMethodAsync(ClientMethod method) {
        return method.getType().name().contains("Async");
    }

    protected boolean isMethodVisible(ClientMethod method) {
        return method.getMethodVisibility() == JavaVisibility.Public
                && !method.isImplementationOnly();
    }

    protected String getMethodName(ClientMethod method) {
        if (isMethodAsync(method)) {
            return method.getName().endsWith("Async")
                    ? method.getName().substring(0, method.getName().length() - "Async".length())
                    : method.getName();
        } else {
            return method.getName();
        }
    }

    /**
     * Write the code of the method invocation of client method, and the conversion of parameters and return value.
     *
     * @param convenienceMethod the convenience method.
     * @param protocolMethod the protocol method.
     * @param invocationExpression the prepared expression of invocation on client method.
     * @param methodBlock the code block.
     */
    protected abstract void writeInvocationAndConversion(
            ClientMethod convenienceMethod, ClientMethod protocolMethod,
            String invocationExpression,
            JavaBlock methodBlock);

    private static String expressionConvertToBinaryData(String name, IType type) {
        if (type == ClassType.BinaryData) {
            return name;
        } else {
            return String.format("BinaryData.fromObject(%s)", name);
        }
    }

    private static String expressionConvertToString(String name, IType type) {
        if (type == ClassType.String) {
            return name;
        } else if (type instanceof EnumType) {
            // enum
            EnumType enumType = (EnumType) type;
            if (enumType.getElementType() == ClassType.String) {
                return String.format("%s.toString()", name);
            } else {
                return String.format("String.valueOf(%1$s.%2$s())", name, enumType.getToJsonMethodName());
            }
        } else {
            // primitive
            return String.format("String.valueOf(%s)", name);
        }
    }

    private static String expressionConvertToType(String name, MethodParameter convenienceParameter, MethodParameter clientParameter) {
        if (convenienceParameter.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.BODY) {
            return expressionConvertToBinaryData(name, convenienceParameter.getClientMethodParameter().getClientType());
        } else {
            // TODO: array of enum to array of string
            if (convenienceParameter.getClientMethodParameter().getClientType() instanceof EnumType) {
                return expressionConvertToString(name, convenienceParameter.getClientMethodParameter().getClientType());
            } else {
                return name;
            }
        }
    }

    private static Map<MethodParameter, MethodParameter> findParametersForConvenienceMethod(
            ClientMethod convenienceMethod, ClientMethod protocolMethod) {
        Map<MethodParameter, MethodParameter> parameterMap = new LinkedHashMap<>();
        List<MethodParameter> convenienceParameters = getParameters(convenienceMethod, true);
        Map<String, MethodParameter> clientParameters = getParameters(protocolMethod, false).stream()
                .collect(Collectors.toMap(MethodParameter::getSerializedName, Function.identity()));
        for (MethodParameter convenienceParameter : convenienceParameters) {
            String name = convenienceParameter.getSerializedName();
            parameterMap.put(convenienceParameter, clientParameters.get(name));
        }
        return parameterMap;
    }

    private static List<MethodParameter> getParameters(ClientMethod method, boolean useAllParameters) {
        List<ProxyMethodParameter> proxyMethodParameters = useAllParameters ? method.getProxyMethod().getAllParameters() : method.getProxyMethod().getParameters();
        Map<String, ProxyMethodParameter> proxyMethodParameterByClientParameterName = proxyMethodParameters.stream()
                .collect(Collectors.toMap(p -> CodeNamer.getEscapedReservedClientMethodParameterName(p.getName()), Function.identity()));
        return method.getMethodInputParameters().stream()
                .filter(p -> !p.getIsConstant() && !p.getFromClient())
                .map(p -> new MethodParameter(proxyMethodParameterByClientParameterName.get(p.getName()), p))
                .collect(Collectors.toList());
    }

    protected static class MethodParameter {

        private final ProxyMethodParameter proxyMethodParameter;
        private final ClientMethodParameter clientMethodParameter;

        public MethodParameter(ProxyMethodParameter proxyMethodParameter, ClientMethodParameter clientMethodParameter) {
            this.proxyMethodParameter = proxyMethodParameter;
            this.clientMethodParameter = clientMethodParameter;
        }

        public ProxyMethodParameter getProxyMethodParameter() {
            return proxyMethodParameter;
        }

        public ClientMethodParameter getClientMethodParameter() {
            return clientMethodParameter;
        }

        public String getName() {
            return this.getClientMethodParameter().getName();
        }

        public String getSerializedName() {
            if (this.getProxyMethodParameter() == null) {
                return null;
            } else {
                String name = this.getProxyMethodParameter().getRequestParameterName();
                if (name == null && this.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.BODY) {
                    name = "__internal_request_BODY";
                }
                return name;
            }
        }
    }
}
