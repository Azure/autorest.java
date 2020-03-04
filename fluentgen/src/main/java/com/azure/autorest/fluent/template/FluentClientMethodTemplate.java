/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.template.ClientMethodTemplate;
import com.azure.autorest.util.CodeNamer;

public class FluentClientMethodTemplate extends ClientMethodTemplate {

    private static FluentClientMethodTemplate _instance = new FluentClientMethodTemplate();

    public static FluentClientMethodTemplate getInstance() {
        return _instance;
    }

    @Override
    protected void generatePagedAsyncSinglePage(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        boolean addContextParameter = settings.getAddContextParameter();
        String endOfLine = addContextParameter ? "" : ";";
        String caller = getClientParameterCaller(restAPIMethod);

        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        String restAPIMethodArgumentList = String.join(", ", clientMethod.getProxyMethodArguments(settings));
        String serviceMethodCall = String.format("service.%s(%s)", restAPIMethod.getName(), restAPIMethodArgumentList);
        if (clientMethod.getMethodPageDetails().nonNullNextLink()) {
            typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
                AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                ApplyParameterTransformations(function, clientMethod, settings);
                ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);
                if (addContextParameter) {
                    function.line(String.format("return FluxUtil.withContext(context -> %s)",
                            serviceMethodCall));
                } else {
                    function.line(String.format("return %s",
                            serviceMethodCall));
                }
                function.indent(() -> {
                    if (addContextParameter) {
                        function.line(String.format(".<%s>map(res -> new PagedResponseBase<>(",
                                returnTypeWithoutMono(clientMethod.getReturnValue().getType())));
                    } else {
                        function.line(".map(res -> new PagedResponseBase<>(");
                    }
                    function.indent(() -> {
                        function.line("res.getRequest(),");
                        function.line("res.getStatusCode(),");
                        function.line("res.getHeaders(),");
                        function.line("res.getValue().%s(),", CodeNamer.getModelNamer().modelPropertyGetterName(clientMethod.getMethodPageDetails().getItemName()));
                        function.line("res.getValue().%s(),", CodeNamer.getModelNamer().modelPropertyGetterName(clientMethod.getMethodPageDetails().getNextLinkName()));
                        IType responseType = ((GenericType) clientMethod.getProxyMethod().getReturnType()).getTypeArguments()[0];
                        if (responseType instanceof ClassType) {
                            function.line(String.format("res.getDeserializedHeaders()))%s", endOfLine));
                        } else {
                            function.line(String.format("null))%s", endOfLine));
                        }
                    });
                    if (addContextParameter) {
                        function.line(String.format(".subscriberContext(context -> context.putAll(FluxUtil.toReactorContext(%sgetContext())));", caller));
                    }
                });
            });
        } else {
            typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                if (addContextParameter) {
                    function.line(String.format("return FluxUtil.withContext(context -> %s)",
                            serviceMethodCall));
                } else {
                    function.line(String.format("return %s",
                            serviceMethodCall));
                }
                function.indent(() -> {
                    if (addContextParameter) {
                        function.line(String.format(".<%s>map(res -> new PagedResponseBase<>(",
                                returnTypeWithoutMono(clientMethod.getReturnValue().getType())));
                    } else {
                        function.line(".map(res -> new PagedResponseBase<>(");
                    }
                    function.indent(() -> {
                        function.line("res.getRequest(),");
                        function.line("res.getStatusCode(),");
                        function.line("res.getHeaders(),");
                        function.line("res.getValue().%s(),", CodeNamer.getModelNamer().modelPropertyGetterName(clientMethod.getMethodPageDetails().getItemName()));
                        function.line("null,");
                        IType responseType = ((GenericType) clientMethod.getProxyMethod().getReturnType()).getTypeArguments()[0];
                        if (responseType instanceof ClassType) {
                            function.line(String.format("res.getDeserializedHeaders()))%s", endOfLine));
                        } else {
                            function.line(String.format("null))%s", endOfLine));
                        }
                    });
                    if (addContextParameter) {
                        function.line(String.format(".subscriberContext(context -> context.putAll(FluxUtil.toReactorContext(%sgetContext())));", caller));
                    }
                });
            });
        }
    }

    @Override
    protected void generateSimpleAsyncRestResponse(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        String caller = getClientParameterCaller(restAPIMethod);

        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
            AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
            AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            ApplyParameterTransformations(function, clientMethod, settings);
            ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);
            String restAPIMethodArgumentList = String.join(", ", clientMethod.getProxyMethodArguments(settings));
            String serviceMethodCall = String.format("service.%s(%s)", restAPIMethod.getName(), restAPIMethodArgumentList);
            if (settings.getAddContextParameter()) {
                function.line(String.format("return FluxUtil.withContext(context -> %s)",
                        serviceMethodCall));
                function.indent(() -> {
                    function.line(String.format(".subscriberContext(context -> context.putAll(FluxUtil.toReactorContext(%sgetContext())));", caller));
                });
            } else {
                function.methodReturn(serviceMethodCall);
            }
        });
    }

    @Override
    protected void generateLongRunningAsync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        String caller = getClientParameterCaller(restAPIMethod);

        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
            IType classType = ((GenericType) clientMethod.getReturnValue().getType().getClientType()).getTypeArguments()[0];

            AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            function.line("%s mono = %s(%s);", clientMethod.getProxyMethod().getReturnType().toString(), clientMethod.getProxyMethod().getSimpleAsyncRestResponseMethodName(), clientMethod.getArgumentList());
            if (classType instanceof GenericType) {
                function.line("return %s<%s, %s>getLroResultAsync(mono, %sgetHttpPipeline(), new TypeReference<%s>() {}.getType(), new TypeReference<%s>() {}.getType())", caller, classType.toString(), classType.toString(), caller, classType.toString(), classType.toString());
            } else {
                function.line("return %s<%s, %s>getLroResultAsync(mono, %sgetHttpPipeline(), %s.class, %s.class)", caller, classType.toString(), classType.toString(), caller, classType.toString(), classType.toString());
            }
            function.indent(() -> {
                function.line(".last()");
                function.line(".flatMap(AsyncPollResponse::getFinalResult);");
            });
        });
    }

    private static String getClientParameterCaller(ProxyMethod restAPIMethod) {
        // hack, 'host' is usually there, use it to determine whether the ClientMethod is in operation group or in client.
        return restAPIMethod.getParameters().stream()
                .filter(p -> p.getName().equalsIgnoreCase("host"))
                .findAny()
                .filter(p -> p.getParameterReference().contains("this.getHost()"))
                .isPresent() ? "this." : "this.client.";
    }

    private static IType returnTypeWithoutMono(IType returnType) {
        // need e.g. PagedResponse<T>
        IType returnTypeWithoutMono = returnType;
        if (returnType instanceof GenericType && ((GenericType) returnType).getName().equals("Mono")) {
            returnTypeWithoutMono = ((GenericType) returnType).getTypeArguments()[0];
        }
        return returnTypeWithoutMono;
    }
}
