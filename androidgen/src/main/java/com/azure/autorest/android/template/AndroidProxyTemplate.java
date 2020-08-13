// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.android.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaInterface;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.ProxyTemplate;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;

/**
 * Writes a Proxy to a JavaClass block.
 */
public class AndroidProxyTemplate extends ProxyTemplate {
    private static AndroidProxyTemplate _instance = new AndroidProxyTemplate();

    protected AndroidProxyTemplate() {
    }

    public static AndroidProxyTemplate getInstance() {
        return _instance;
    }

    public void write(Proxy restAPI, JavaClass classBlock) {
        JavaSettings settings = JavaSettings.getInstance();
        if (restAPI != null) {
            classBlock.javadocComment(settings.getMaximumJavadocCommentWidth(), comment ->
            {
                comment.description(String.format("The interface defining all the services for %1$s to be used by the proxy service to perform REST calls.", restAPI.getClientTypeName()));
            });

            classBlock.interfaceBlock(JavaVisibility.Private, restAPI.getName(), interfaceBlock ->
            {
                for (ProxyMethod restAPIMethod : restAPI.getMethods()) {
                    if (restAPIMethod.getRequestContentType().equals("multipart/form-data") || restAPIMethod.getRequestContentType().equals("application/x-www-form-urlencoded")) {
                        interfaceBlock.lineComment(String.format("@Multipart not supported by %1$s", ClassType.RestProxy.getName()));
                    }

                    writeProxyMethodHeaders(restAPIMethod, interfaceBlock);

                    interfaceBlock.annotation(String.format("%1$s(\"%2$s\")",
                            CodeNamer.toPascalCase(restAPIMethod.getHttpMethod().toString().toUpperCase()), restAPIMethod.getUrlPath()));

                    ArrayList<String> parameterDeclarationList = new ArrayList<String>();
                    for (ProxyMethodParameter parameter : restAPIMethod.getParameters()) {
                        StringBuilder parameterDeclarationBuilder = new StringBuilder();
                        if (parameter.getRequestParameterLocation() == RequestParameterLocation.Uri) {
                            // Host annotation in RestProxy is ServiceClient::baseUrl in Android
                        } else if (parameter.getRequestParameterLocation() == RequestParameterLocation.Path) {
                            parameterDeclarationBuilder
                                    .append("@Path(");
                            if (parameter.getAlreadyEncoded()) {
                                parameterDeclarationBuilder
                                        .append(String.format("value = \"%1$s\", encoded = true", parameter.getRequestParameterName()));
                            } else {
                                parameterDeclarationBuilder
                                        .append(String.format("\"%1$s\"", parameter.getRequestParameterName()));
                            }
                            parameterDeclarationBuilder.append(") ");
                            parameterDeclarationBuilder
                                    .append(parameter.getWireType() + " " + parameter.getName());
                        } else if (parameter.getRequestParameterLocation() == RequestParameterLocation.Query) {
                            parameterDeclarationBuilder
                                    .append("@Query(");
                            if (parameter.getAlreadyEncoded()) {
                                parameterDeclarationBuilder
                                        .append(String.format("value = \"%1$s\", encoded = true", parameter.getRequestParameterName()));
                            } else {
                                parameterDeclarationBuilder
                                        .append(String.format("\"%1$s\"", parameter.getRequestParameterName()));
                            }
                            parameterDeclarationBuilder.append(") ");
                            parameterDeclarationBuilder
                                    .append(parameter.getWireType() + " " + parameter.getName());
                        } else if (parameter.getRequestParameterLocation() == RequestParameterLocation.Header) {
                            parameterDeclarationBuilder
                                    .append("@Header(")
                                    .append(String.format("\"%1$s\"", parameter.getRequestParameterName()))
                                    .append(") ");
                            parameterDeclarationBuilder
                                    .append(parameter.getWireType() + " " + parameter.getName());
                        } else if (parameter.getRequestParameterLocation() == RequestParameterLocation.Body) {
                            parameterDeclarationBuilder
                                    .append("@Body ")
                                    .append("RequestBody" + " " + parameter.getName());
                        } else {
                            throw new IllegalArgumentException("Unrecognized RequestParameterLocation value: " + parameter.getRequestParameterLocation());
                        }
                        if (parameterDeclarationBuilder.length() > 0) {
                            parameterDeclarationList.add(parameterDeclarationBuilder.toString());
                        }
                    }

                    String parameterDeclarations = String.join(", ", parameterDeclarationList);
                    interfaceBlock.publicMethod(String.format("%1$s %2$s(%3$s)", "Call<ResponseBody>",
                            restAPIMethod.getName(),
                            parameterDeclarations));
                }
            });
        }
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
