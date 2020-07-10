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
        boolean addContextParameter = settings.getAddContextParameter() && !(settings.isContextClientMethodParameter() && contextInParameters(clientMethod));
        boolean mergeContextParameter = settings.getAddContextParameter() && (settings.isContextClientMethodParameter() && contextInParameters(clientMethod));
        boolean isLroPagination = GenericType.Mono(GenericType.Response(GenericType.FluxByteBuffer)).equals(restAPIMethod.getReturnType().getClientType());
        String endOfLine = addContextParameter ? "" : ";";

        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        String restAPIMethodArgumentList = String.join(", ", clientMethod.getProxyMethodArguments(settings));
        String serviceMethodCall = String.format("service.%s(%s)", restAPIMethod.getName(), restAPIMethodArgumentList);
        if (clientMethod.getMethodPageDetails().nonNullNextLink()) {
            typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
                AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                ApplyParameterTransformations(function, clientMethod, settings);
                ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);
                if (mergeContextParameter) {
                    function.line(String.format("context = %s.mergeContext(context);", clientMethod.getClientReference()));
                }
                if (addContextParameter) {
                    if (!isLroPagination) {
                        function.line(String.format("return FluxUtil.withContext(context -> %s)",
                                serviceMethodCall));
                    } else {
                        function.line("return FluxUtil.withContext(context -> {");
                        function.indent(() -> {
                            function.line(String.format("%s mono = %s.cache();",
                                    clientMethod.getProxyMethod().getReturnType().toString(),
                                    serviceMethodCall));

                            IType classType = clientMethod.getMethodPageDetails().getLroIntermediateType();
                            function.line(String.format("return Mono.zip(mono, %s.<%s, %s>getLroResultAsync(mono, %s.getHttpPipeline(), %s.class, %s.class).last().flatMap(%s::getLroFinalResultOrError));",
                                    clientMethod.getClientReference(), classType.toString(), classType.toString(), clientMethod.getClientReference(), classType.toString(), classType.toString(), clientMethod.getClientReference()));
                        });
                        function.line("})");
                    }
                } else {
                    if (!isLroPagination) {
                        function.line(String.format("return %s",
                                serviceMethodCall));
                    } else {
                        function.line(String.format("%s mono = %s.cache();",
                                clientMethod.getProxyMethod().getReturnType().toString(),
                                serviceMethodCall));

                        IType classType = clientMethod.getMethodPageDetails().getLroIntermediateType();
                        function.line(String.format("return Mono.zip(mono, %s.<%s, %s>getLroResultAsync(mono, %s.getHttpPipeline(), %s.class, %s.class).last().flatMap(%s::getLroFinalResultOrError))",
                                clientMethod.getClientReference(), classType.toString(), classType.toString(), clientMethod.getClientReference(), classType.toString(), classType.toString(), clientMethod.getClientReference()));
                    }
                }
                function.indent(() -> {
                    if (addContextParameter) {
                        function.line(String.format(".<%s>map(res -> new PagedResponseBase<>(",
                                returnTypeWithoutMono(clientMethod.getReturnValue().getType())));
                    } else {
                        function.line(".map(res -> new PagedResponseBase<>(");
                    }
                    function.indent(() -> {
                        if (!isLroPagination) {
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
                        } else {
                            function.line("res.getT1().getRequest(),");
                            function.line("res.getT1().getStatusCode(),");
                            function.line("res.getT1().getHeaders(),");
                            function.line("res.getT2().%s(),", CodeNamer.getModelNamer().modelPropertyGetterName(clientMethod.getMethodPageDetails().getItemName()));
                            function.line("res.getT2().%s(),", CodeNamer.getModelNamer().modelPropertyGetterName(clientMethod.getMethodPageDetails().getNextLinkName()));
                            IType responseType = ((GenericType) clientMethod.getProxyMethod().getReturnType()).getTypeArguments()[0];
                            if (responseType instanceof ClassType) {
                                function.line(String.format("res.getT2().getDeserializedHeaders()))%s", endOfLine));
                            } else {
                                function.line(String.format("null))%s", endOfLine));
                            }
                        }
                    });
                    if (addContextParameter) {
                        function.line(String.format(".subscriberContext(context -> context.putAll(FluxUtil.toReactorContext(%s.getContext())));", clientMethod.getClientReference()));
                    }
                });
            });
        } else {
            typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
                AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
                AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
                ApplyParameterTransformations(function, clientMethod, settings);
                ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);
                if (mergeContextParameter) {
                    function.line(String.format("context = %s.mergeContext(context);", clientMethod.getClientReference()));
                }
                if (addContextParameter) {
                    if (!isLroPagination) {
                        function.line(String.format("return FluxUtil.withContext(context -> %s)",
                                serviceMethodCall));
                    } else {
                        function.line("return FluxUtil.withContext(context -> {");
                        function.indent(() -> {
                            function.line(String.format("%s mono = %s.cache();",
                                    clientMethod.getProxyMethod().getReturnType().toString(),
                                    serviceMethodCall));

                            IType classType = clientMethod.getMethodPageDetails().getLroIntermediateType();
                            function.line(String.format("return Mono.zip(mono, %s.<%s, %s>getLroResultAsync(mono, %s.getHttpPipeline(), %s.class, %s.class).last().flatMap(%s::getLroFinalResultOrError));",
                                    clientMethod.getClientReference(), classType.toString(), classType.toString(), clientMethod.getClientReference(), classType.toString(), classType.toString(), clientMethod.getClientReference()));
                        });
                        function.line("})");
                    }
                } else {
                    if (!isLroPagination) {
                        function.line(String.format("return %s",
                                serviceMethodCall));
                    } else {
                        function.line(String.format("%s mono = %s.cache();",
                                clientMethod.getProxyMethod().getReturnType().toString(),
                                serviceMethodCall));

                        IType classType = clientMethod.getMethodPageDetails().getLroIntermediateType();
                        function.line(String.format("return Mono.zip(mono, %s.<%s, %s>getLroResultAsync(mono, %s.getHttpPipeline(), %s.class, %s.class).last().flatMap(%s::getLroFinalResultOrError))",
                                clientMethod.getClientReference(), classType.toString(), classType.toString(), clientMethod.getClientReference(), classType.toString(), classType.toString(), clientMethod.getClientReference()));
                    }
                }
                function.indent(() -> {
                    if (addContextParameter) {
                        function.line(String.format(".<%s>map(res -> new PagedResponseBase<>(",
                                returnTypeWithoutMono(clientMethod.getReturnValue().getType())));
                    } else {
                        function.line(".map(res -> new PagedResponseBase<>(");
                    }
                    function.indent(() -> {
                        if (!isLroPagination) {
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
                        } else {
                            function.line("res.getT1().getRequest(),");
                            function.line("res.getT1().getStatusCode(),");
                            function.line("res.getT1().getHeaders(),");
                            function.line("res.getT2().%s(),", CodeNamer.getModelNamer().modelPropertyGetterName(clientMethod.getMethodPageDetails().getItemName()));
                            function.line("null,");
                            IType responseType = ((GenericType) clientMethod.getProxyMethod().getReturnType()).getTypeArguments()[0];
                            if (responseType instanceof ClassType) {
                                function.line(String.format("res.getT2().getDeserializedHeaders()))%s", endOfLine));
                            } else {
                                function.line(String.format("null))%s", endOfLine));
                            }
                        }
                    });
                    if (addContextParameter) {
                        function.line(String.format(".subscriberContext(context -> context.putAll(FluxUtil.toReactorContext(%s.getContext())));", clientMethod.getClientReference()));
                    }
                });
            });
        }
    }

    @Override
    protected void generateSimpleAsyncRestResponse(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        boolean addContextParameter = settings.getAddContextParameter() && !(settings.isContextClientMethodParameter() && contextInParameters(clientMethod));
        boolean mergeContextParameter = settings.getAddContextParameter() && (settings.isContextClientMethodParameter() && contextInParameters(clientMethod));

        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
            AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
            AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            ApplyParameterTransformations(function, clientMethod, settings);
            ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);
            String restAPIMethodArgumentList = String.join(", ", clientMethod.getProxyMethodArguments(settings));
            String serviceMethodCall = String.format("service.%s(%s)", restAPIMethod.getName(), restAPIMethodArgumentList);
            if (mergeContextParameter) {
                function.line(String.format("context = %s.mergeContext(context);", clientMethod.getClientReference()));
            }
            if (addContextParameter) {
                function.line(String.format("return FluxUtil.withContext(context -> %s)",
                        serviceMethodCall));
                function.indent(() -> {
                    function.line(String.format(".subscriberContext(context -> context.putAll(FluxUtil.toReactorContext(%s.getContext())));", clientMethod.getClientReference()));
                });
            } else {
                function.methodReturn(serviceMethodCall);
            }
        });
    }

    @Override
    protected void generateLongRunningAsync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
            AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            function.line("return %s(%s)", "begin" + CodeNamer.toPascalCase(restAPIMethod.getSimpleAsyncMethodName()), clientMethod.getArgumentList());
            function.indent(() -> {
                function.line(".last()");
                function.line(String.format(".flatMap(%s::getLroFinalResultOrError);", clientMethod.getClientReference()));
            });
        });
    }

    protected void generateLongRunningBeginAsync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
            IType classType = ((GenericType) clientMethod.getReturnValue().getType().getClientType()).getTypeArguments()[1];

            AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            function.line("%s mono = %s(%s);", clientMethod.getProxyMethod().getReturnType().toString(), clientMethod.getProxyMethod().getSimpleAsyncRestResponseMethodName(), clientMethod.getArgumentList());
            if (classType instanceof GenericType) {
                function.line("return %s.<%s, %s>getLroResultAsync(mono, %s.getHttpPipeline(), new TypeReference<%s>() {}.getType(), new TypeReference<%s>() {}.getType());", clientMethod.getClientReference(), classType.toString(), classType.toString(), clientMethod.getClientReference(), classType.toString(), classType.toString());
            } else {
                function.line("return %s.<%s, %s>getLroResultAsync(mono, %s.getHttpPipeline(), %s.class, %s.class);", clientMethod.getClientReference(), classType.toString(), classType.toString(), clientMethod.getClientReference(), classType.toString(), classType.toString());
            }
        });
    }

    protected void generateLongRunningBeginSync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
            AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            function.line("return %s(%s)", "begin" + CodeNamer.toPascalCase(restAPIMethod.getSimpleAsyncMethodName()), clientMethod.getArgumentList());
            function.indent((() -> {
                function.text(".getSyncPoller();");
            }));
        });
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
