package com.azure.autorest.template;


// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.model.clientmodel.ClientException;
import com.azure.autorest.model.javamodel.JavaFile;

/**
 Writes a ClientException to a JavaFile.
*/
public class ExceptionTemplate implements IJavaTemplate<ClientException, JavaFile>
{
    private static ExceptionTemplate _instance = new ExceptionTemplate();
    public static ExceptionTemplate getInstance()
    {
        return _instance;
    }

    private ExceptionTemplate()
    {
    }

    public final void Write(ClientException exception, JavaFile javaFile)
    {
        javaFile.Import("com.azure.core.exception.HttpResponseException", "com.azure.core.http.HttpResponse");
        javaFile.JavadocComment((comment) ->
        {
                comment.Description(String.format("Exception thrown for an invalid response with %1$s information.", exception.getErrorName()));
        });
        javaFile.PublicFinalClass(String.format("%1$s extends HttpResponseException", exception.getName()), (classBlock) ->
        {
                classBlock.JavadocComment((comment) ->
                {
                    comment.Description(String.format("Initializes a new instance of the %1$s class.", exception.getName()));
                    comment.Param("message", "the exception message or the response content if a message is not available");
                    comment.Param("response", "the HTTP response");
                });
                classBlock.PublicConstructor(String.format("%1$s(String message, HttpResponse response)", exception.getName()), (constructorBlock) ->
                {
                    constructorBlock.Line("super(message, response);");
                });

                classBlock.JavadocComment((comment) ->
                {
                    comment.Description(String.format("Initializes a new instance of the %1$s class.", exception.getName()));
                    comment.Param("message", "the exception message or the response content if a message is not available");
                    comment.Param("response", "the HTTP response");
                    comment.Param("value", "the deserialized response value");
                });
                classBlock.PublicConstructor(String.format("%1$s(String message, HttpResponse response, %2$s value)", exception.getName(), exception.getErrorName()), (constructorBlock) ->
                {
                    constructorBlock.Line("super(message, response, value);");
                });

                classBlock.Annotation("Override");
                classBlock.PublicMethod(String.format("%1$s value()", exception.getErrorName()), (methodBlock) ->
                {
                    methodBlock.Return(String.format("(%1$s) super.value()", exception.getErrorName()));
                });
        });
    }
}