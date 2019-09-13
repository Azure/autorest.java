package com.azure.autorest.model.javamodel;

import java.util.function.Consumer;

public interface JavaContext
{
    void JavadocComment(Consumer<JavaJavadocComment> commentAction);

    void Annotation(String... annotations);
}