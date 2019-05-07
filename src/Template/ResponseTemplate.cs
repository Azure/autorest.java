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
using System.Text.RegularExpressions;

namespace AutoRest.Java
{
    /// <summary>
    /// Writes a ClientResponse to a JavaFile.
    /// </summary>
    public class ResponseTemplate : IJavaTemplate<ClientResponse, JavaFile>
    {
        private static ResponseTemplate _instance = new ResponseTemplate();
        public static ResponseTemplate Instance => _instance;

        private ResponseTemplate()
        {
        }

        public void Write(ClientResponse response, JavaFile javaFile)
        {
            ISet<string> imports = new HashSet<string> { "java.util.Map", "com.azure.core.http.HttpRequest", "com.azure.core.http.HttpHeaders" };
            IType restResponseType = GenericType.RestResponse(response.HeadersType, response.BodyType);
            restResponseType.AddImportsTo(imports, includeImplementationImports: true);

            bool isStreamResponse = response.BodyType.Equals(GenericType.FluxByteBuf);
            if (isStreamResponse)
            {
                imports.Add("java.io.Closeable");
            }

            javaFile.Import(imports);

            string classSignature = isStreamResponse
                ? $"{response.Name} extends {restResponseType} implements Closeable"
                : $"{response.Name} extends {restResponseType}";

            javaFile.JavadocComment(javadoc =>
            {
                javadoc.Description(response.Description);
            });

            javaFile.PublicFinalClass(classSignature, classBlock =>
            {
                classBlock.JavadocComment(javadoc =>
                {
                    javadoc.Description($"Creates an instance of {response.Name}.");
                    javadoc.Param("request", $"the request which resulted in this {response.Name}.");
                    javadoc.Param("statusCode", "the status code of the HTTP response");
                    javadoc.Param("headers", "the deserialized headers of the HTTP response");
                    javadoc.Param("rawHeaders", "the raw headers of the HTTP response");
                    javadoc.Param("value", isStreamResponse ? "the content stream" : "the deserialized value of the HTTP response");
                });
                classBlock.PublicConstructor(
                    $"{response.Name}(HttpRequest request, int statusCode, HttpHeaders rawHeaders, {response.BodyType} value, {response.HeadersType} headers)",
                    ctorBlock => ctorBlock.Line("super(request, statusCode, rawHeaders, value, headers);"));

                if (!response.BodyType.Equals(ClassType.Void))
                {
                    if (response.BodyType.Equals(GenericType.FluxByteBuf))
                    {
                        classBlock.JavadocComment(javadoc => javadoc.Return("the response content stream"));
                    }
                    else
                    {
                        classBlock.JavadocComment(javadoc => javadoc.Return("the deserialized response body"));
                    }


                    classBlock.Annotation("Override");
                    classBlock.PublicMethod($"{response.BodyType} value()", methodBlock => methodBlock.Return("super.value()"));
                }

                if (isStreamResponse)
                {
                    classBlock.JavadocComment(javadoc => javadoc.Description("Disposes of the connection associated with this stream response."));
                    classBlock.Annotation("Override");
                    classBlock.PublicMethod("void close()",
                        methodBlock => methodBlock.Line("value().subscribe(bb -> { }, t -> { }).dispose();"));
                }
            });
        }
    }
}