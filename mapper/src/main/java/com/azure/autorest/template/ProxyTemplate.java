package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.RequestParameterLocation;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.CodeNamer;

import java.util.*;
import java.util.stream.Collectors;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 Writes a Proxy to a JavaClass block.
*/
public class ProxyTemplate implements IJavaTemplate<Proxy, JavaClass>
{
    private static ProxyTemplate _instance = new ProxyTemplate();
    public static ProxyTemplate getInstance()
    {
        return _instance;
    }

    private ProxyTemplate()
    {
    }

    public final void Write(Proxy restAPI, JavaClass classBlock)
    {
        JavaSettings settings = JavaSettings.getInstance();
        if (restAPI != null)
        {
            classBlock.JavadocComment(settings.getMaximumJavadocCommentWidth(), comment ->
            {
                    comment.Description(String.format("The interface defining all the services for %1$s to be used by the proxy service to perform REST calls.", restAPI.getClientTypeName()));
            });
            classBlock.Annotation(String.format("Host(\"%1$s\")", restAPI.getBaseURL()));
            classBlock.Annotation(String.format("ServiceInterface(name = \"%1$s\")", restAPI.getClientTypeName()));
            classBlock.Interface(JavaVisibility.Private, restAPI.getName(), interfaceBlock ->
            {
                    for (ProxyMethod restAPIMethod : restAPI.getMethods())
                    {
                        if (restAPIMethod.getRequestContentType().equals("multipart/form-data") || restAPIMethod.getRequestContentType().equals("application/x-www-form-urlencoded"))
                        {
                            interfaceBlock.LineComment(String.format("@Multipart not supported by %1$s", ClassType.RestProxy.getName()));
                        }

                        if (restAPIMethod.getIsPagingNextOperation())
                        {
                            interfaceBlock.Annotation("Get(\"{nextUrl}\")");
                        }
                        else
                        {
                            interfaceBlock.Annotation(String.format("%1$s(\"%2$s\")", CodeNamer.toPascalCase(restAPIMethod.getHttpMethod().toString()), restAPIMethod.getUrlPath()));
                        }

                        if (!restAPIMethod.getResponseExpectedStatusCodes().isEmpty())
                        {
                            interfaceBlock.Annotation(String.format("ExpectedResponses({%1$s})", restAPIMethod.getResponseExpectedStatusCodes().stream().map(statusCode -> String.format("%d", statusCode)).collect(Collectors.joining(", "))));
                        }

                        if (restAPIMethod.getReturnValueWireType() != null)
                        {
                            interfaceBlock.Annotation(String.format("ReturnValueWireType(%1$s.class)", restAPIMethod.getReturnValueWireType()));
                        }

                        if (restAPIMethod.getUnexpectedResponseExceptionType() != null)
                        {
                            interfaceBlock.Annotation(String.format("UnexpectedResponseExceptionType(%1$s.class)", restAPIMethod.getUnexpectedResponseExceptionType()));
                        }

                        ArrayList<String> parameterDeclarationList = new ArrayList<String>();
                        if (restAPIMethod.getIsResumable())
                        {
                            interfaceBlock.Annotation(String.format("ResumeOperation"));
                        }

                        for (ProxyMethodParameter parameter : restAPIMethod.getParameters())
                        {
                            StringBuilder parameterDeclarationBuilder = new StringBuilder();

                            switch (parameter.getRequestParameterLocation())
                            {
                                case Host:
                                case Path:
                                case Query:
                                case Header:
                                    parameterDeclarationBuilder.append(String.format("@%1$sParam(", parameter.getRequestParameterLocation()));
                                    if ((parameter.getRequestParameterLocation() == RequestParameterLocation.Path || parameter.getRequestParameterLocation() == RequestParameterLocation.Query) && settings.getIsAzureOrFluent() && parameter.getAlreadyEncoded())
                                    {
                                        parameterDeclarationBuilder.append(String.format("value = \"%1$s\", encoded = true", parameter.getRequestParameterName()));
                                    }
                                    else if (parameter.getRequestParameterLocation() == RequestParameterLocation.Header && parameter.getHeaderCollectionPrefix() != null && !parameter.getHeaderCollectionPrefix().isEmpty())
                                    {
                                        parameterDeclarationBuilder.append(String.format("\"%1$s\"", parameter.getHeaderCollectionPrefix()));
                                    }
                                    else
                                    {
                                        parameterDeclarationBuilder.append(String.format("\"%1$s\"", parameter.getRequestParameterName()));
                                    }
                                    parameterDeclarationBuilder.append(") ");

                                    break;

                                case Body:
                                    parameterDeclarationBuilder.append(String.format("@BodyParam(\"%1$s\") ", restAPIMethod.getRequestContentType()));
                                    break;

                                case FormData:
                                    parameterDeclarationBuilder.append(String.format("@FormParam(\"%1$s\") ", parameter.getRequestParameterName()));
                                    break;

                                default:
                                    if (!restAPIMethod.getIsResumable() && parameter.getWireType() != ClassType.Context)
                                    {
                                        throw new IllegalArgumentException("Unrecognized RequestParameterLocation value: " + parameter.getRequestParameterLocation());
                                    }

                                    break;
                            }

                            parameterDeclarationBuilder.append(parameter.getWireType() + " " + parameter.getName());
                            parameterDeclarationList.add(parameterDeclarationBuilder.toString());
                        }

                        String parameterDeclarations = String.join(", ", parameterDeclarationList);
                        IType restAPIMethodReturnValueClientType = restAPIMethod.getReturnType().getClientType();
                        interfaceBlock.PublicMethod(String.format("%1$s %2$s(%3$s)", restAPIMethodReturnValueClientType, restAPIMethod.getName(), parameterDeclarations));
                    }
            });
        }
    }
}