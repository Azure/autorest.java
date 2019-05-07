// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Core.Utilities.Collections;
using Newtonsoft.Json.Linq;
using AutoRest.Java.Model;

namespace AutoRest.Java
{
    /// <summary>
    /// Writes a Proxy to a JavaClass block.
    /// </summary>
    public class ProxyTemplate : IJavaTemplate<Proxy, JavaClass>
    {
        private static ProxyTemplate _instance = new ProxyTemplate();
        public static ProxyTemplate Instance => _instance;

        private ProxyTemplate()
        {
        }

        public void Write(Proxy restAPI, JavaClass classBlock)
        {
            var settings = JavaSettings.Instance;
            if (restAPI != null)
            {
                classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, comment =>
                {
                    comment.Description($"The interface defining all the services for {restAPI.ClientTypeName} to be used by the proxy service to perform REST calls.");
                });
                classBlock.Annotation($"Host(\"{restAPI.BaseURL}\")");
                classBlock.Interface(JavaVisibility.Private, restAPI.Name, interfaceBlock =>
                {
                    foreach (ProxyMethod restAPIMethod in restAPI.Methods)
                    {
                        if (restAPIMethod.RequestContentType == "multipart/form-data" || restAPIMethod.RequestContentType == "application/x-www-form-urlencoded")
                        {
                            interfaceBlock.LineComment($"@Multipart not supported by {ClassType.RestProxy.Name}");
                        }

                        if (restAPIMethod.IsPagingNextOperation)
                        {
                            interfaceBlock.Annotation("GET(\"{nextUrl}\")");
                        }
                        else
                        {
                            interfaceBlock.Annotation($"{restAPIMethod.HttpMethod.ToString().ToUpperInvariant()}(\"{restAPIMethod.UrlPath}\")");
                        }

                        if (restAPIMethod.ResponseExpectedStatusCodes.Any())
                        {
                            interfaceBlock.Annotation($"ExpectedResponses({{{string.Join(", ", restAPIMethod.ResponseExpectedStatusCodes.Select(statusCode => statusCode.ToString("D")))}}})");
                        }

                        if (restAPIMethod.ReturnValueWireType != null)
                        {
                            interfaceBlock.Annotation($"ReturnValueWireType({restAPIMethod.ReturnValueWireType}.class)");
                        }

                        if (restAPIMethod.UnexpectedResponseExceptionType != null)
                        {
                            interfaceBlock.Annotation($"UnexpectedResponseExceptionType({restAPIMethod.UnexpectedResponseExceptionType}.class)");
                        }

                        List<string> parameterDeclarationList = new List<string>();
                        if (restAPIMethod.IsResumable)
                        {
                            interfaceBlock.Annotation($"ResumeOperation");
                        }

                        foreach (ProxyMethodParameter parameter in restAPIMethod.Parameters)
                        {
                            StringBuilder parameterDeclarationBuilder = new StringBuilder();

                            switch (parameter.RequestParameterLocation)
                            {
                                case RequestParameterLocation.Host:
                                case RequestParameterLocation.Path:
                                case RequestParameterLocation.Query:
                                case RequestParameterLocation.Header:
                                    parameterDeclarationBuilder.Append($"@{parameter.RequestParameterLocation}Param(");
                                    if ((parameter.RequestParameterLocation == RequestParameterLocation.Path || parameter.RequestParameterLocation == RequestParameterLocation.Query) && settings.IsAzureOrFluent && parameter.AlreadyEncoded)
                                    {
                                        parameterDeclarationBuilder.Append($"value = \"{parameter.RequestParameterName}\", encoded = true");
                                    }
                                    else if (parameter.RequestParameterLocation == RequestParameterLocation.Header && !string.IsNullOrEmpty(parameter.HeaderCollectionPrefix))
                                    {
                                        parameterDeclarationBuilder.Append($"\"{parameter.HeaderCollectionPrefix}\"");
                                    }
                                    else
                                    {
                                        parameterDeclarationBuilder.Append($"\"{parameter.RequestParameterName}\"");
                                    }
                                    parameterDeclarationBuilder.Append(") ");

                                    break;

                                case RequestParameterLocation.Body:
                                    parameterDeclarationBuilder.Append($"@BodyParam(\"{restAPIMethod.RequestContentType}\") ");
                                    break;

                                case RequestParameterLocation.FormData:
                                    parameterDeclarationBuilder.Append($"@FormParam(\"{parameter.RequestParameterName}\") ");
                                    break;

                                default:
                                    if (!restAPIMethod.IsResumable && parameter.WireType != ClassType.Context)
                                    {
                                        throw new ArgumentException("Unrecognized RequestParameterLocation value: " + parameter.RequestParameterLocation);
                                    }

                                    break;
                            }

                            parameterDeclarationBuilder.Append(parameter.WireType + " " + parameter.Name);
                            parameterDeclarationList.Add(parameterDeclarationBuilder.ToString());
                        }

                        string parameterDeclarations = string.Join(", ", parameterDeclarationList);
                        IType restAPIMethodReturnValueClientType = restAPIMethod.ReturnType.ClientType;
                        interfaceBlock.PublicMethod($"{restAPIMethodReturnValueClientType} {restAPIMethod.Name}({parameterDeclarations})");
                    }
                });
            }
        }
    }
}