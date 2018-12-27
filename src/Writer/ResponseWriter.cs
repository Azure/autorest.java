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
    public class ResponseWriter : IWriter<ClientResponse, JavaFile>
    {
        private JavaSettings settings;
        private WriterFactory factory;

        public ResponseWriter(WriterFactory factory)
        {
            this.factory = factory;
            this.settings = factory.Settings;
        }

        public void Write(ClientResponse response, JavaFile javaFile)
        {
            ISet<string> imports = new HashSet<string> { "java.util.Map", "com.microsoft.rest.v2.http.HttpRequest" };
            IType restResponseType = GenericType.RestResponse(response.HeadersType, response.BodyType);
            restResponseType.AddImportsTo(imports, includeImplementationImports: true);

            bool isStreamResponse = response.BodyType.Equals(GenericType.FlowableByteBuffer);
            if (isStreamResponse)
            {
                imports.Add("java.io.Closeable");
                imports.Add("io.reactivex.internal.functions.Functions");
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
                    javadoc.Param("statusCode", "the status code of the HTTP response");
                    javadoc.Param("headers", "the deserialized headers of the HTTP response");
                    javadoc.Param("rawHeaders", "the raw headers of the HTTP response");
                    javadoc.Param("body", isStreamResponse ? "the body content stream" : "the deserialized body of the HTTP response");
                });
                classBlock.PublicConstructor(
                    $"{response.Name}(HttpRequest request, int statusCode, {response.HeadersType} headers, Map<String, String> rawHeaders, {response.BodyType} body)",
                    ctorBlock => ctorBlock.Line("super(request, statusCode, headers, rawHeaders, body);"));

                if (!response.HeadersType.Equals(ClassType.Void))
                {
                    classBlock.JavadocComment(javadoc => javadoc.Return("the deserialized response headers"));
                    classBlock.Annotation("Override");
                    classBlock.PublicMethod($"{response.HeadersType} headers()", methodBlock => methodBlock.Return("super.headers()"));
                }

                if (!response.BodyType.Equals(ClassType.Void))
                {
                    if (response.BodyType.Equals(GenericType.FlowableByteBuffer))
                    {
                        classBlock.JavadocComment(javadoc => javadoc.Return("the response content stream"));
                    }
                    else
                    {
                        classBlock.JavadocComment(javadoc => javadoc.Return("the deserialized response body"));
                    }


                    classBlock.Annotation("Override");
                    classBlock.PublicMethod($"{response.BodyType} body()", methodBlock => methodBlock.Return("super.body()"));
                }

                if (isStreamResponse)
                {
                    classBlock.JavadocComment(javadoc => javadoc.Description("Disposes of the connection associated with this stream response."));
                    classBlock.Annotation("Override");
                    classBlock.PublicMethod("void close()",
                        methodBlock => methodBlock.Line("body().subscribe(Functions.emptyConsumer(), Functions.<Throwable>emptyConsumer()).dispose();"));
                }
            });
        }
    }
}