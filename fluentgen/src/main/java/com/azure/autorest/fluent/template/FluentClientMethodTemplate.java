package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.template.ClientMethodTemplate;
import com.azure.core.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

public class FluentClientMethodTemplate extends ClientMethodTemplate {

    public void write(ClientMethod clientMethod, JavaType typeBlock) {
        boolean completed = checkHeadMethod(clientMethod, typeBlock);

        if (!completed) {
            super.write(clientMethod, typeBlock);
        }
    }

    private IType elementType(IType type) {
        IType elementType = null;
        while (type instanceof GenericType) {
            GenericType genericType = (GenericType) type;
            if (genericType.getTypeArguments().length == 1) {
                type = genericType.getTypeArguments()[0];
            } else {
                type = null;
                break;
            }
        }
        if (type instanceof ClassType || type instanceof PrimitiveType) {
            elementType = type;
        }
        return elementType;
    }

    private IType substituteElementType(IType type, IType elementType) {
        IType newType = null;
        if (type instanceof PrimitiveType) {
            newType = elementType;
        } else if (type instanceof ClassType) {
            newType = elementType.asNullable();
        } else if (type instanceof GenericType) {
            GenericType genericType = (GenericType) type;
            newType = new GenericType(genericType.getPackage(), genericType.getName(),
                    substituteElementType(genericType.getTypeArguments()[0], elementType));
        }
        return newType;
    }

    private boolean checkHeadMethod(ClientMethod clientMethod, JavaType typeBlock) {
        boolean handle = false;

        ProxyMethod restAPIMethod = clientMethod.getProxyMethod();
        if (restAPIMethod.getHttpMethod() == HttpMethod.HEAD
                && restAPIMethod.getResponseExpectedStatusCodes().size() == 2
                && (clientMethod.getType() == ClientMethodType.SimpleAsync || clientMethod.getType() == ClientMethodType.SimpleSync)) {
            // HEAD method and exactly 2 success status codes
            IType elementType = elementType(restAPIMethod.getReturnType());
            if (PrimitiveType.Void == elementType || ClassType.Void.equals(elementType)) {
                // empty body
                List<Integer> sortedCodes = restAPIMethod.getResponseExpectedStatusCodes().stream().map(HttpResponseStatus::code).sorted().collect(Collectors.toList());
                if (sortedCodes.get(0) / 100 == 2 && sortedCodes.get(1) == 404) {
                    // Status codes 2xx and 404
                    handle = true;

                    final IType booleanType = substituteElementType(clientMethod.getReturnValue().getType(), PrimitiveType.Boolean);
                    final String declaration = String.format("%1$s %2$s(%3$s)", booleanType, clientMethod.getName(), clientMethod.getParametersDeclaration());

                    switch (clientMethod.getType()) {
                        case SimpleSync:
                            typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
                            typeBlock.publicMethod(declaration, function -> {
                                function.methodReturn(String.format("%s(%s).block()", clientMethod.getSimpleAsyncMethodName(), clientMethod.getArgumentList()));
                            });
                            break;

                        case SimpleAsync:
                            // convert status code to true/false
                            typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
                            typeBlock.publicMethod(declaration, (function -> {
                                function.line("return %s(%s)", clientMethod.getProxyMethod().getSimpleAsyncRestResponseMethodName(), clientMethod.getArgumentList());
                                function.indent((() -> {
                                    GenericType restAPIMethodClientReturnType = (GenericType) restAPIMethod.getReturnType().getClientType();
                                    IType returnValueTypeArgumentClientType = restAPIMethodClientReturnType.getTypeArguments()[0];

                                    function.text(".flatMap(");
                                    function.lambda(returnValueTypeArgumentClientType.toString(), "res", "Mono.just(res.getStatusCode() / 100 == 2)");
                                    function.line(");");
                                }));
                            }));
                            break;
                    }
                }
            }
        }
        return handle;
    }
}
