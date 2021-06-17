package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.javamodel.JavaInterface;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.http.ContentType;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * Writes a Proxy to a JavaClass block.
 */
public class ProxyTemplate implements IJavaTemplate<Proxy, JavaClass> {
    private static ProxyTemplate _instance = new ProxyTemplate();

    protected ProxyTemplate() {
    }

    public static ProxyTemplate getInstance() {
        return _instance;
    }

    public final void write(Proxy restAPI, JavaClass classBlock) {
        JavaSettings settings = JavaSettings.getInstance();
        if (restAPI != null) {
            classBlock.javadocComment(settings.getMaximumJavadocCommentWidth(), comment ->
            {
                comment.description(String.format("The interface defining all the services for %1$s to be used by the proxy service to perform REST calls.", restAPI.getClientTypeName()));
            });
            classBlock.annotation(String.format("Host(\"%1$s\")", restAPI.getBaseURL()));
            classBlock.annotation(String.format("ServiceInterface(name = \"%1$s\")", serviceInterfaceWithLengthLimit(restAPI.getClientTypeName())));

            JavaVisibility visibility = JavaVisibility.Private;
            if (settings.isServiceInterfaceAsPublic()) {
                visibility = JavaVisibility.Public;
            }

            classBlock.interfaceBlock(visibility, restAPI.getName(), interfaceBlock ->
            {
                for (ProxyMethod restAPIMethod : restAPI.getMethods()) {
                    if (restAPIMethod.getRequestContentType().equals("multipart/form-data") || restAPIMethod.getRequestContentType().equals("application/x-www-form-urlencoded")) {
                        interfaceBlock.lineComment(String.format("@Multipart not supported by %1$s", ClassType.RestProxy.getName()));
                    }

                    writeProxyMethodHeaders(restAPIMethod, interfaceBlock);

                    interfaceBlock.annotation(String.format("%1$s(\"%2$s\")", CodeNamer.toPascalCase(restAPIMethod.getHttpMethod().toString().toLowerCase()), restAPIMethod.getUrlPath()));

                    if (!settings.isLowLevelClient()) {
                        if (!restAPIMethod.getResponseExpectedStatusCodes().isEmpty()) {
                            interfaceBlock.annotation(String.format("ExpectedResponses({%1$s})", restAPIMethod.getResponseExpectedStatusCodes().stream().map(statusCode -> String.format("%s", statusCode.code())).collect(Collectors.joining(", "))));
                        }

                        if (restAPIMethod.getReturnValueWireType() != null) {
                            interfaceBlock.annotation(String.format("ReturnValueWireType(%1$s.class)",
                                    restAPIMethod.getReturnValueWireType()));
                        }

                        if (restAPIMethod.getUnexpectedResponseExceptionTypes() != null) {
                            writeUnexpectedExceptions(restAPIMethod, interfaceBlock);
                        }

                        if (restAPIMethod.getUnexpectedResponseExceptionType() != null) {
                            writeSingleUnexpectedException(restAPIMethod, interfaceBlock);
                        }
                    }

                    ArrayList<String> parameterDeclarationList = new ArrayList<String>();
                    if (restAPIMethod.isResumable()) {
                        interfaceBlock.annotation(String.format("ResumeOperation"));
                    }

                    Set<String> usedParameterNames = new HashSet<>();

                    for (ProxyMethodParameter parameter : restAPIMethod.getParameters()) {
                        StringBuilder parameterDeclarationBuilder = new StringBuilder();

                        switch (parameter.getRequestParameterLocation()) {
                            case Uri:
                            case Path:
                            case Query:
                            case Header:
                                parameterDeclarationBuilder.append(String.format("@%1$sParam(", CodeNamer.toPascalCase(parameter.getRequestParameterLocation().toString())));
                                if ((parameter.getRequestParameterLocation() == RequestParameterLocation.Path || parameter.getRequestParameterLocation() == RequestParameterLocation.Query) && parameter.getAlreadyEncoded()) {
                                    parameterDeclarationBuilder.append(String.format("value = \"%1$s\", encoded = true", parameter.getRequestParameterName()));
                                } else if (parameter.getRequestParameterLocation() == RequestParameterLocation.Header && parameter.getHeaderCollectionPrefix() != null && !parameter.getHeaderCollectionPrefix().isEmpty()) {
                                    parameterDeclarationBuilder.append(String.format("\"%1$s\"", parameter.getHeaderCollectionPrefix()));
                                } else {
                                    parameterDeclarationBuilder.append(String.format("\"%1$s\"", parameter.getRequestParameterName()));
                                }
                                parameterDeclarationBuilder.append(") ");

                                break;

                            case Body:
                                if (ContentType.APPLICATION_X_WWW_FORM_URLENCODED.equals(restAPIMethod.getRequestContentType())) {
                                    parameterDeclarationBuilder.append(String.format("@FormParam(\"%1$s\") ",
                                            parameter.getRequestParameterName()));
                                    break;
                                }
                                parameterDeclarationBuilder.append(String.format("@BodyParam(\"%1$s\") ", restAPIMethod.getRequestContentType()));
                                break;

                           // case FormData:
                           //     parameterDeclarationBuilder.append(String.format("@FormParam(\"%1$s\") ", parameter.getRequestParameterName()));
                           //     break;

                            case None:
                                break;

                            default:
                                if (!restAPIMethod.isResumable() && parameter.getWireType() != ClassType.Context) {
                                    throw new IllegalArgumentException("Unrecognized RequestParameterLocation value: " + parameter.getRequestParameterLocation());
                                }

                                break;
                        }

                        // avoid name conflict
                        String parameterName = parameter.getName();
                        if (usedParameterNames.contains(parameterName)) {
                            parameterName = parameterName + "Param";
                        }
                        usedParameterNames.add(parameterName);

                        parameterDeclarationBuilder.append(parameter.getWireType() + " " + parameterName);
                        parameterDeclarationList.add(parameterDeclarationBuilder.toString());
                    }

                    writeProxyMethodSignature(parameterDeclarationList, restAPIMethod, interfaceBlock);
                }
            });
        }
    }

    protected void writeUnexpectedExceptions(ProxyMethod restAPIMethod, JavaInterface interfaceBlock) {
        for (Map.Entry<ClassType, List<HttpResponseStatus>> exception : restAPIMethod.getUnexpectedResponseExceptionTypes().entrySet()) {
            interfaceBlock.annotation(String.format("UnexpectedResponseExceptionType(value = %1$s.class, code = {%2$s})",
                    exception.getKey(), exception.getValue().stream().map(status -> String.valueOf(status.code())).collect(Collectors.joining(", "))));
        }
    }

    protected void writeSingleUnexpectedException(ProxyMethod restAPIMethod, JavaInterface interfaceBlock) {
        interfaceBlock.annotation(String.format("UnexpectedResponseExceptionType(%1$s.class)", restAPIMethod.getUnexpectedResponseExceptionType()));
    }

    protected void writeProxyMethodSignature(java.util.ArrayList<String> parameterDeclarationList, ProxyMethod restAPIMethod, JavaInterface interfaceBlock) {
        String parameterDeclarations = String.join(", ", parameterDeclarationList);
        IType restAPIMethodReturnValueClientType = restAPIMethod.getReturnType().getClientType();
        interfaceBlock.publicMethod(String.format("%1$s %2$s(%3$s)", restAPIMethodReturnValueClientType, restAPIMethod.getName(), parameterDeclarations));
    }

    private static String serviceInterfaceWithLengthLimit(String serviceInterfaceName) {
        final int lengthLimit = 20;

        return serviceInterfaceName.length() > lengthLimit
                ? serviceInterfaceName.substring(0, lengthLimit)
                : serviceInterfaceName;
    }

    /**
     * Extension to write Headers annotation for proxy method.
     *
     * @param restAPIMethod proxy method
     * @param interfaceBlock interface block
     */
    protected void writeProxyMethodHeaders(ProxyMethod restAPIMethod, JavaInterface interfaceBlock) {
    }
}
