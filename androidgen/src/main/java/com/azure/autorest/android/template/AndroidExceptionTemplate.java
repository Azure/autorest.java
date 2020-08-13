package com.azure.autorest.android.template;

import com.azure.autorest.model.clientmodel.ClientException;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.ExceptionTemplate;

import java.util.HashSet;
import java.util.Set;

public class AndroidExceptionTemplate extends ExceptionTemplate {
    private static AndroidExceptionTemplate _instance = new AndroidExceptionTemplate();

    protected AndroidExceptionTemplate() {
    }

    public static ExceptionTemplate getInstance() {
        return _instance;
    }

    public void write(ClientException exception, JavaFile javaFile) {
        Set<String> imports = new HashSet<>();
        imports.add("okhttp3.Response");
        exception.getParentType().addImportsTo(imports, false);
        javaFile.declareImport(imports);
        javaFile.javadocComment((comment) ->
        {
            comment.description(String.format("Exception thrown for an invalid response with %1$s information.", exception.getErrorName()));
        });
        javaFile.publicFinalClass(String.format("%1$s extends %2$s", exception.getName(), exception.getParentType().toString()), (classBlock) ->
        {
            classBlock.javadocComment((comment) ->
            {
                comment.description(String.format("Initializes a new instance of the %1$s class.", exception.getName()));
                comment.param("message", "the exception message or the response content if a message is not available");
                comment.param("response", "the HTTP response");
            });
            classBlock.publicConstructor(String.format("%1$s(String message, Response response)", exception.getName()), (constructorBlock) ->
            {
                constructorBlock.line("super(message, response);");
            });

            classBlock.javadocComment((comment) ->
            {
                comment.description(String.format("Initializes a new instance of the %1$s class.", exception.getName()));
                comment.param("message", "the exception message or the response content if a message is not available");
                comment.param("response", "the HTTP response");
                comment.param("value", "the deserialized response value");
            });
            classBlock.publicConstructor(String.format("%1$s(String message, Response response, %2$s value)", exception.getName(), exception.getErrorName()), (constructorBlock) ->
            {
                constructorBlock.line("super(message, response, value);");
            });

            classBlock.annotation("Override");
            classBlock.publicMethod(String.format("%1$s getValue()", exception.getErrorName()), (methodBlock) ->
            {
                methodBlock.methodReturn(String.format("(%1$s) super.getValue()", exception.getErrorName()));
            });
        });
    }
}
