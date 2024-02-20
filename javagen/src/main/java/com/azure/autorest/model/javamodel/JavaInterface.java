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
    public final void javadocComment(int wordWrapWidth, Consumer<JavaJavadocComment> commentDescriptionAction, Consumer<JavaJavadocComment> commentTagsAction, boolean withGeneratedMarker) {
        javadocComment(commentDescriptionAction, commentTagsAction, withGeneratedMarker, true, wordWrapWidth);
    }

    @Override
    public void javadocComment(Consumer<JavaJavadocComment> commentDescriptionAction, Consumer<JavaJavadocComment> commentTagsAction, boolean withGeneratedMarker) {
        javadocComment(commentDescriptionAction, commentTagsAction, withGeneratedMarker, false, 0);
    }

    /**
     * Helper method to add a javadoc comment to the contents.
     * @param commentDescriptionAction
     * @param commentTagsAction
     * @param withGeneratedMarker
     * @param withWordWrap
     * @param wordWrapWidth
     */
    private void javadocComment(Consumer<JavaJavadocComment> commentDescriptionAction, Consumer<JavaJavadocComment> commentTagsAction, boolean withGeneratedMarker, boolean withWordWrap, int wordWrapWidth) {
        addExpectedNewLine();
        // javadoc comment description
        contents.javadocCommentStart();
        if (withGeneratedMarker) {
            contents.line(GENERATED_JAVADOC_DESC_START_MARKER);
        }
        Consumer<JavaJavadocComment> nonNullCommentDescriptionAction = commentDescriptionAction == null ? c -> {} : commentDescriptionAction;
        if (withWordWrap) {
            contents.withWordWrap(wordWrapWidth, () -> nonNullCommentDescriptionAction.accept(new JavaJavadocComment(contents)));
        } else {
            nonNullCommentDescriptionAction.accept(new JavaJavadocComment(contents));
        }
        if (withGeneratedMarker) {
            contents.line(GENERATED_JAVADOC_DESC_END_MARKER);
        }

        // javadoc comment tags
        if (withGeneratedMarker) {
            contents.line(GENERATED_JAVADOC_TAG_START_MARKER);
        }
        Consumer<JavaJavadocComment> nonNullCommentTagsAction = commentTagsAction == null ? c -> {} : commentTagsAction;
        if (withWordWrap) {
            contents.withWordWrap(wordWrapWidth, () -> nonNullCommentTagsAction.accept(new JavaJavadocComment(contents)));
        } else {
            nonNullCommentTagsAction.accept(new JavaJavadocComment(contents));
        }
        if (withGeneratedMarker) {
            contents.line(GENERATED_JAVADOC_TAG_END_MARKER);
        }
        contents.javadocCommentEnd();
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
