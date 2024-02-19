// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.javamodel;

import java.util.function.Consumer;

import static com.azure.autorest.util.TemplateUtil.GENERATED_JAVADOC_DESC_END_MARKER;
import static com.azure.autorest.util.TemplateUtil.GENERATED_JAVADOC_DESC_START_MARKER;
import static com.azure.autorest.util.TemplateUtil.GENERATED_JAVADOC_TAG_END_MARKER;
import static com.azure.autorest.util.TemplateUtil.GENERATED_JAVADOC_TAG_START_MARKER;

public class JavaInterface implements JavaType {
    private JavaFileContents contents;
    private boolean addNewLine;

    public JavaInterface(JavaFileContents contents) {
        this.contents = contents;
    }

    private void addExpectedNewLine() {
        if (addNewLine) {
            contents.line();
            addNewLine = false;
        }
    }

    public final void publicMethod(String methodSignature) {
        publicMethod(methodSignature, null);
    }

    public final void publicMethod(String methodSignature, Consumer<JavaBlock> functionBlock) {
        addExpectedNewLine();
        contents.line(methodSignature + ";");

        addNewLine = true;
    }

    public final void javadocComment(Consumer<JavaJavadocComment> commentAction) {
        addExpectedNewLine();
        contents.javadocComment(commentAction);
    }

    @Override
    public void javadocComment(Consumer<JavaJavadocComment> commentDescriptionAction, Consumer<JavaJavadocComment> commentTagsAction, boolean withGeneratedMarker) {
        addExpectedNewLine();
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

    public final void lineComment(String comment) {
        addExpectedNewLine();
        contents.lineComment(comment);
    }

    public final void annotation(String... annotations) {
        addExpectedNewLine();
        contents.annotation(annotations);
    }

    public final void interfaceBlock(String interfaceName, Consumer<JavaInterface> interfaceAction) {
        contents.interfaceBlock(JavaVisibility.PackagePrivate, interfaceName, interfaceAction);
    }
}
