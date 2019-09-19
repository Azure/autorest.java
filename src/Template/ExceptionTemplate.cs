// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Extensions;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using AutoRest.Java.Model;
using System.Text.RegularExpressions;

namespace AutoRest.Java
{
    /// <summary>
    /// Writes a ClientException to a JavaFile.
    /// </summary>
    public class ExceptionTemplate : IJavaTemplate<ClientException, JavaFile>
    {
        private static ExceptionTemplate _instance = new ExceptionTemplate();
        public static ExceptionTemplate Instance => _instance;

        private ExceptionTemplate()
        {
        }

        public void Write(ClientException exception, JavaFile javaFile)
        {
            javaFile.Import("com.azure.core.exception.HttpResponseException",
                            "com.azure.core.http.HttpResponse");
            javaFile.JavadocComment((comment) =>
            {
                comment.Description($"Exception thrown for an invalid response with {exception.ErrorName} information.");
            });
            javaFile.PublicFinalClass($"{exception.Name} extends HttpResponseException", (classBlock) =>
            {
                classBlock.JavadocComment((comment) =>
                {
                    comment.Description($"Initializes a new instance of the {exception.Name} class.");
                    comment.Param("message", "the exception message or the response content if a message is not available");
                    comment.Param("response", "the HTTP response");
                });
                classBlock.PublicConstructor($"{exception.Name}(String message, HttpResponse response)", (constructorBlock) =>
                {
                    constructorBlock.Line("super(message, response);");
                });

                classBlock.JavadocComment((comment) =>
                {
                    comment.Description($"Initializes a new instance of the {exception.Name} class.");
                    comment.Param("message", "the exception message or the response content if a message is not available");
                    comment.Param("response", "the HTTP response");
                    comment.Param("value", "the deserialized response value");
                });
                classBlock.PublicConstructor($"{exception.Name}(String message, HttpResponse response, {exception.ErrorName} value)", (constructorBlock) =>
                {
                    constructorBlock.Line("super(message, response, value);");
                });

                classBlock.Annotation("Override");
                classBlock.PublicMethod($"{exception.ErrorName} getValue()", (methodBlock) =>
                {
                    methodBlock.Return($"({exception.ErrorName}) super.getValue()");
                });
            });
        }
    }
}