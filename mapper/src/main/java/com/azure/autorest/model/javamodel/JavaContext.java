package com.azure.autorest.model.javamodel;

public interface JavaContext
{
    void JavadocComment(Consumer<JavaJavadocComment> commentAction);

    void Annotation(String... annotations);
}