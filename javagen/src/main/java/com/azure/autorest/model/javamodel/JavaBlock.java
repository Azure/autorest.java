// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.javamodel;

import java.util.function.Consumer;

import static com.azure.autorest.util.TemplateUtil.GENERATED_JAVADOC_DESC_END_MARKER;
import static com.azure.autorest.util.TemplateUtil.GENERATED_JAVADOC_DESC_START_MARKER;
import static com.azure.autorest.util.TemplateUtil.GENERATED_JAVADOC_TAG_END_MARKER;
import static com.azure.autorest.util.TemplateUtil.GENERATED_JAVADOC_TAG_START_MARKER;

public class JavaBlock implements JavaContext {
    private JavaFileContents contents;

    public JavaBlock(JavaFileContents contents) {
        this.contents = contents;
    }

    public final void indent(Runnable indentAction) {
        contents.indent(indentAction);
    }

    public final void increaseIndent() {
        contents.increaseIndent();
    }

    public final void decreaseIndent() {
        contents.decreaseIndent();
    }

    public final void text(String text) {
        contents.text(text);
    }

    public final void line(String text, Object... formattedArguments) {
        contents.line(text, formattedArguments);
    }

    public final void line() {
        contents.line();
    }

    public final void block(String text, Consumer<JavaBlock> bodyAction) {
        contents.block(text, bodyAction);
    }

    public final void javadocComment(String text) {
        contents.javadocComment(text);
    }

    public final void javadocComment(Consumer<JavaJavadocComment> commentAction) {
        contents.javadocComment(commentAction);
    }

    @Override
    public final void javadocComment(Consumer<JavaJavadocComment> commentDescriptionAction, Consumer<JavaJavadocComment> commentTagsAction, boolean withGeneratedMarker) {
        if (commentDescriptionAction != null) {
            contents.javadocCommentStart();
            if (withGeneratedMarker) {
                contents.line(GENERATED_JAVADOC_DESC_START_MARKER);
            }
            commentDescriptionAction.accept(new JavaJavadocComment(contents));

            if (withGeneratedMarker) {
                contents.line(GENERATED_JAVADOC_DESC_END_MARKER);
            }

            if (commentTagsAction == null) { // if no tags action, then end the javadoc comment
                contents.javadocCommentEnd();
            } else { // otherwise, a new line will be added and the tags action will start
                contents.line();
            }
        }
        if (commentTagsAction != null) {
            if (commentDescriptionAction == null) {// if no description action, then start the javadoc comment
                contents.javadocCommentStart();
            }
            if (withGeneratedMarker) {
                contents.line(GENERATED_JAVADOC_TAG_START_MARKER);
            }
            commentTagsAction.accept(new JavaJavadocComment(contents));

            if (withGeneratedMarker) {
                contents.line(GENERATED_JAVADOC_TAG_END_MARKER);
            }
            contents.javadocCommentEnd();
        }
    }

    public final void methodReturn(String text) {
        contents.methodReturn(text);
    }

    public final void annotation(String... annotations) {
        contents.annotation(annotations);
    }

    public final void returnAnonymousClass(String anonymousClassDeclaration, Consumer<JavaClass> anonymousClassBlock) {
        contents.returnAnonymousClass(anonymousClassDeclaration, anonymousClassBlock);
    }

    public final void anonymousClass(String anonymousClassDeclaration, String instanceName, Consumer<JavaClass> anonymousClassBlock) {
        contents.anonymousClass(anonymousClassDeclaration, instanceName, anonymousClassBlock);
    }

    public final JavaIfBlock ifBlock(String condition, Consumer<JavaBlock> ifAction) {
        contents.ifBlock(condition, ifAction);
        return new JavaIfBlock(contents);
    }

    public final JavaTryBlock tryBlock(Consumer<JavaBlock> ifAction) {
        contents.tryBlock(ifAction);
        return new JavaTryBlock(contents);
    }

    public final JavaTryBlock tryBlock(String resource, Consumer<JavaBlock> ifAction) {
        contents.tryBlock(resource, ifAction);
        return new JavaTryBlock(contents);
    }

    public final void lambda(String parameterType, String parameterName, Consumer<JavaLambda> body) {
        contents.lambda(parameterType, parameterName, body);
    }

    public final void lambda(String parameterType, String parameterName, String returnExpression) {
        contents.lambda(parameterType, parameterName, returnExpression);
    }
}
