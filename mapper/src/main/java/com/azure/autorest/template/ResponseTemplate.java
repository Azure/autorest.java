package com.azure.autorest.template;


// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientResponse;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.javamodel.JavaFile;

import java.util.HashSet;
import java.util.Set;

/**
 Writes a ClientResponse to a JavaFile.
*/
public class ResponseTemplate implements IJavaTemplate<ClientResponse, JavaFile>
{
    private static ResponseTemplate _instance = new ResponseTemplate();
    public static ResponseTemplate getInstance()
    {
        return _instance;
    }

    private ResponseTemplate()
    {
    }

    public final void Write(ClientResponse response, JavaFile javaFile)
    {
        Set<String> imports = new HashSet<String> () {{
            add("com.azure.core.http.HttpRequest");
            add("com.azure.core.http.HttpHeaders");
        }};
        IType restResponseType = GenericType.RestResponse(response.getHeadersType(), response.getBodyType());
        restResponseType.AddImportsTo(imports, true);

        boolean isStreamResponse = response.getBodyType().equals(GenericType.FluxByteBuffer);
        if (isStreamResponse)
        {
            imports.add("java.io.Closeable");
        }

        javaFile.Import(imports);

        String classSignature = isStreamResponse ? String.format("%1$s extends %2$s implements Closeable", response.getName(), restResponseType) : String.format("%1$s extends %2$s", response.getName(), restResponseType);

        javaFile.JavadocComment(javadoc ->
        {
                javadoc.Description(response.getDescription());
        });

        javaFile.PublicFinalClass(classSignature, classBlock ->
        {
                classBlock.JavadocComment(javadoc ->
                {
                    javadoc.Description(String.format("Creates an instance of %1$s.", response.getName()));
                    javadoc.Param("request", String.format("the request which resulted in this %1$s.", response.getName()));
                    javadoc.Param("statusCode", "the status code of the HTTP response");
                    javadoc.Param("rawHeaders", "the raw headers of the HTTP response");
                    javadoc.Param("value", isStreamResponse ? "the content stream" : "the deserialized value of the HTTP response");
                    javadoc.Param("headers", "the deserialized headers of the HTTP response");
                });
                classBlock.PublicConstructor(String.format("%1$s(HttpRequest request, int statusCode, HttpHeaders rawHeaders, %2$s value, %3$s headers)", response.getName(), response.getBodyType(), response.getHeadersType()), ctorBlock -> ctorBlock.Line("super(request, statusCode, rawHeaders, value, headers);"));

                if (!response.getBodyType().equals(ClassType.Void))
                {
                    if (response.getBodyType().equals(GenericType.FluxByteBuffer))
                    {
                        classBlock.JavadocComment(javadoc -> javadoc.Return("the response content stream"));
                    }
                    else
                    {
                        classBlock.JavadocComment(javadoc -> javadoc.Return("the deserialized response body"));
                    }


                    classBlock.Annotation("Override");
                    classBlock.PublicMethod(String.format("%1$s value()", response.getBodyType()), methodBlock -> methodBlock.Return("super.value()"));
                }

                if (isStreamResponse)
                {
                    classBlock.JavadocComment(javadoc -> javadoc.Description("Disposes of the connection associated with this stream response."));
                    classBlock.Annotation("Override");
                    classBlock.PublicMethod("void close()", methodBlock -> methodBlock.Line("value().subscribe(bb -> { }, t -> { }).dispose();"));
                }
        });
    }
}