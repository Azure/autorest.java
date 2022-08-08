// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ConvenienceMethod;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ParameterSynthesizedOrigin;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConvenienceMethodTemplate implements IJavaTemplate<ConvenienceMethod, JavaClass> {

    private static final ConvenienceMethodTemplate INSTANCE = new ConvenienceMethodTemplate();

    protected ConvenienceMethodTemplate() {
    }

    public static ConvenienceMethodTemplate getInstance() {
        return INSTANCE;
    }

    public final void write(ConvenienceMethod convenienceMethodObj, JavaClass classBlock) {
        ClientMethod clientMethod = convenienceMethodObj.getClientMethod();
        convenienceMethodObj.getConvenienceMethods().stream()
                .filter(m -> m.getType().name().contains("Async"))
                .forEach(convenienceMethod -> {
                    classBlock.javadocComment(comment -> {
                        ClientMethodTemplate.generateJavadoc(convenienceMethod, comment, convenienceMethod.getProxyMethod(), true);
                    });

                    classBlock.publicMethod(convenienceMethod.getDeclaration(), methodBlock -> {
                        // RequestOptions
                        methodBlock.line("RequestOptions requestOptions = new RequestOptions();");

                        Map<MethodParameter, MethodParameter> parametersMap =
                                findParametersForConvenienceMethod(convenienceMethod, clientMethod);
                        Map<String, String> parameterExpressionsMap = new HashMap<>();
                        for (Map.Entry<MethodParameter, MethodParameter> entry : parametersMap.entrySet()) {
                            MethodParameter parameter = entry.getKey();
                            MethodParameter clientParameter = entry.getValue();

                            if (parameter.getProxyMethodParameter().getOrigin() == ParameterSynthesizedOrigin.CONTEXT) {
                                // Context
                                methodBlock.line(String.format("requestOptions.setContext(%s);", parameter.getName()));
                            } else if (clientParameter != null) {
                                String expression = parameter.getName();
                                if (parameter.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.BODY) {
                                    expression = expressionConvertToBinaryData(parameter.getName(), parameter.getClientMethodParameter().getClientType());
                                }
                                parameterExpressionsMap.put(clientParameter.getName(), expression);
                            } else {
                                switch (parameter.getProxyMethodParameter().getRequestParameterLocation()) {
                                    case HEADER:
                                        methodBlock.line(
                                                String.format("requestOptions.setHeader(%1$s, %2$s);",
                                                        ClassType.String.defaultValueExpression(parameter.getSerializedName()),
                                                        expressionConvertToString(parameter.getName(), parameter.getClientMethodParameter().getClientType())));
                                        break;

                                    case QUERY:
                                        methodBlock.line(
                                                String.format("requestOptions.addQueryParam(%1$s, %2$s);",
                                                        ClassType.String.defaultValueExpression(parameter.getSerializedName()),
                                                        expressionConvertToString(parameter.getName(), parameter.getClientMethodParameter().getClientType())));
                                        break;

                                    case BODY:
                                        methodBlock.line(
                                                String.format("requestOptions.setBody(%s);",
                                                        expressionConvertToBinaryData(parameter.getName(), parameter.getClientMethodParameter().getClientType())));
                                        break;
                                }
                            }
                        }

                        IType baseReturnType = ((GenericType) convenienceMethod.getReturnValue().getType()).getTypeArguments()[0];
                        String methodName = clientMethod.getName().endsWith("Async")
                                ? clientMethod.getName().substring(0, clientMethod.getName().length() - "Async".length())
                                : clientMethod.getName();
                        String invocationExpression = clientMethod.getMethodInputParameters().stream()
                                .map(p -> {
                                    String expression = parameterExpressionsMap.get(p.getName());
                                    if (expression == null) {
                                        expression = p.getName();
                                    }
                                    return expression;
                                })
                                .collect(Collectors.joining(", "));
                        String returnTypeConversionExpression = ClientModelUtil.isClientModel(baseReturnType)
                                ? String.format(".map(r -> r.toObject(%s.class))", baseReturnType)
                                : "";

                        methodBlock.methodReturn(
                                String.format("%1$s(%2$s).map(Response::getValue)%3$s",
                                        methodName,
                                        invocationExpression,
                                        returnTypeConversionExpression));
                    });
                });
    }


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
            return String.format("%s.toString()", name);
        } else {
            return String.format("String.valueOf(%s)", name);
        }
    }

    private static Map<MethodParameter, MethodParameter> findParametersForConvenienceMethod(
            ClientMethod convenienceMethod, ClientMethod clientMethod) {
        Map<MethodParameter, MethodParameter> parameterMap = new LinkedHashMap<>();
        List<MethodParameter> convenienceParameters = getParameters(convenienceMethod, true);
        Map<String, MethodParameter> clientParameters = getParameters(clientMethod, false).stream()
                .collect(Collectors.toMap(MethodParameter::getSerializedName, Function.identity()));
        for (MethodParameter convenienceParameter : convenienceParameters) {
            String name = convenienceParameter.getSerializedName();
            parameterMap.put(convenienceParameter, clientParameters.get(name));
        }
        return parameterMap;
    }

    private static List<MethodParameter> getParameters(ClientMethod clientMethod, boolean useAllParameters) {
        List<ProxyMethodParameter> proxyMethodParameters = useAllParameters ? clientMethod.getProxyMethod().getAllParameters() : clientMethod.getProxyMethod().getParameters();
        Map<String, ProxyMethodParameter> proxyMethodParameterByClientParameterName = proxyMethodParameters.stream()
                .collect(Collectors.toMap(p -> CodeNamer.getEscapedReservedClientMethodParameterName(p.getName()), Function.identity()));
        return clientMethod.getMethodParameters().stream()
                .filter(p -> !p.getIsConstant() && !p.getFromClient())
                .map(p -> new MethodParameter(proxyMethodParameterByClientParameterName.get(p.getName()), p))
                .collect(Collectors.toList());
    }

    private static class MethodParameter {

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
