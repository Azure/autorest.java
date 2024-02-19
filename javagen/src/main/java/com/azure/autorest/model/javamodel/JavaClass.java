// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.javamodel;

import com.azure.core.util.CoreUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.azure.autorest.util.TemplateUtil.GENERATED_JAVADOC_DESC_END_MARKER;
import static com.azure.autorest.util.TemplateUtil.GENERATED_JAVADOC_DESC_START_MARKER;
import static com.azure.autorest.util.TemplateUtil.GENERATED_JAVADOC_TAG_END_MARKER;
import static com.azure.autorest.util.TemplateUtil.GENERATED_JAVADOC_TAG_START_MARKER;

public class JavaClass implements JavaType {
    private JavaFileContents contents;
    private boolean addNewLine;

    public JavaClass(JavaFileContents contents) {
        this.contents = contents;
    }

    private void addExpectedNewLine() {
        if (addNewLine) {
            contents.line();
            addNewLine = false;
        }
    }

    public final void privateMemberVariable(String variableType, String variableName) {
        privateMemberVariable(String.format("%1$s %2$s", variableType, variableName));
    }

    public final void privateMemberVariable(String variableDeclaration) {
        addExpectedNewLine();
        contents.line(String.format("private %1$s;", variableDeclaration));
        addNewLine = true;
    }

    public final void privateFinalMemberVariable(String variableDeclaration) {
        addExpectedNewLine();
        contents.line("private final " + variableDeclaration + ";");
        addNewLine = true;
    }

    public final void privateFinalMemberVariable(String variableType, String variableName) {
        addExpectedNewLine();
        contents.line(String.format("private final %1$s %2$s;", variableType, variableName));
        addNewLine = true;
    }

    public final void privateFinalMemberVariable(String variableType, String variableName, String finalValue) {
        addExpectedNewLine();
        contents.line(String.format("private final %1$s %2$s = %3$s;", variableType, variableName, finalValue));
        addNewLine = true;
    }

    public final void publicStaticFinalVariable(String variableDeclaration) {
        addExpectedNewLine();
        contents.line(String.format("public static final %1$s;", variableDeclaration));
        addNewLine = true;
    }

    public final void privateStaticFinalVariable(String variableDeclaration) {
        addExpectedNewLine();
        contents.line(String.format("private static final %1$s;", variableDeclaration));
        addNewLine = true;
    }

    public final void protectedMemberVariable(String variableType, String variableName) {
        addExpectedNewLine();
        contents.line(String.format("protected %1$s %2$s;", variableType, variableName));
        addNewLine = true;
    }

    /**
     * Adds a variable with the given declaration, visibility, and modifiers.
     * <p>
     * Adding a private constant variable would be:
     * {@code variable(declaration, JavaVisibility.Private, JavaModifier.Static, JavaModifier.Final)}
     *
     * @param variableDeclaration The variable declaration.
     * @param visibility The visibility of the variable.
     * @param modifiers The modifiers of the variable.
     */
    public final void variable(String variableDeclaration, JavaVisibility visibility, JavaModifier... modifiers) {
        addExpectedNewLine();
        String modifier = CoreUtils.isNullOrEmpty(modifiers) ? ""
            : Arrays.stream(modifiers).map(JavaModifier::toString).collect(Collectors.joining(" "));
        contents.line(visibility + " " + modifier + " " + variableDeclaration + ";");
        addNewLine = true;
    }

    public final void constructor(JavaVisibility visibility, String constructorSignature, Consumer<JavaBlock> constructor) {
        addExpectedNewLine();
        contents.constructor(visibility, constructorSignature, constructor);
        addNewLine = true;
    }

    public final void privateConstructor(String constructorSignature, Consumer<JavaBlock> constructor) {
        constructor(JavaVisibility.Private, constructorSignature, constructor);
    }

    public final void publicConstructor(String constructorSignature, Consumer<JavaBlock> constructor) {
        constructor(JavaVisibility.Public, constructorSignature, constructor);
    }

    public final void packagePrivateConstructor(String constructorSignature, Consumer<JavaBlock> constructor) {
        constructor(JavaVisibility.PackagePrivate, constructorSignature, constructor);
    }

    public final void method(JavaVisibility visibility, List<JavaModifier> modifiers, String methodSignature, Consumer<JavaBlock> method) {
        addExpectedNewLine();
        contents.method(visibility, modifiers, methodSignature, method);
        addNewLine = true;
    }

    public final void publicMethod(String methodSignature, Consumer<JavaBlock> method) {
        method(JavaVisibility.Public, null, methodSignature, method);
    }

    public final void packagePrivateMethod(String methodSignature, Consumer<JavaBlock> method) {
        method(JavaVisibility.PackagePrivate, null, methodSignature, method);
    }

    public final void privateMethod(String methodSignature, Consumer<JavaBlock> method) {
        method(JavaVisibility.Private, null, methodSignature, method);
    }

    public final void publicStaticMethod(String methodSignature, Consumer<JavaBlock> method) {
        staticMethod(JavaVisibility.Public, methodSignature, method);
    }

    /**
     * Adds a static method with a declared visibility to the class.
     *
     * @param visibility The visibility of the method.
     * @param methodSignature The method signature.
     * @param method The logic to generate the method.
     */
    public final void staticMethod(JavaVisibility visibility, String methodSignature, Consumer<JavaBlock> method) {
        Objects.requireNonNull(visibility, "'visibility' cannot be null.");
        method(visibility, Collections.singletonList(JavaModifier.Static), methodSignature, method);
    }

    public final void interfaceBlock(JavaVisibility visibility, String interfaceSignature, Consumer<JavaInterface> interfaceBlock) {
        addExpectedNewLine();
        contents.interfaceBlock(visibility, interfaceSignature, interfaceBlock);
        addNewLine = true;
    }

    public final void publicInterface(String interfaceSignature, Consumer<JavaInterface> interfaceBlock) {
        interfaceBlock(JavaVisibility.Public, interfaceSignature, interfaceBlock);
    }

    public final void privateStaticFinalClass(String classSignature, Consumer<JavaClass> classBlock) {
        staticFinalClass(JavaVisibility.Private, classSignature, classBlock);
    }

    public final void staticFinalClass(JavaVisibility visibility, String classSignature, Consumer<JavaClass> classBlock) {
        addExpectedNewLine();
        contents.classBlock(visibility, Arrays.asList(JavaModifier.Static, JavaModifier.Final), classSignature,
            classBlock);
        addNewLine = true;
    }

    public final void blockComment(String description) {
        addExpectedNewLine();
        contents.blockComment(description);
    }

    public final void lineComment(String description) {
        addExpectedNewLine();
        contents.lineComment(description);
    }

    public final void blockComment(Consumer<JavaLineComment> commentAction) {
        addExpectedNewLine();
        contents.blockComment(commentAction);
    }

    public final void blockComment(int wordWrapWidth, Consumer<JavaLineComment> commentAction) {
        addExpectedNewLine();
        contents.blockComment(wordWrapWidth, commentAction);
    }

    public final void javadocComment(String description) {
        addExpectedNewLine();
        contents.javadocComment(description);
    }

    public final void javadocComment(Consumer<JavaJavadocComment> commentAction) {
        addExpectedNewLine();
        contents.javadocComment(commentAction);
    }

    public final void javadocComment(int wordWrapWidth, Consumer<JavaJavadocComment> commentAction) {
        addExpectedNewLine();
        contents.javadocComment(wordWrapWidth, commentAction);
    }

    public final void javadocComment(int wordWrapWidth, Consumer<JavaJavadocComment> commentDescriptionAction, Consumer<JavaJavadocComment> commentTagAction, boolean withGeneratedMarker) {
        javadocComment(commentDescriptionAction, commentTagAction, withGeneratedMarker, true, wordWrapWidth);
    }

    @Override
    public final void javadocComment(Consumer<JavaJavadocComment> commentDescriptionAction, Consumer<JavaJavadocComment> commentTagAction, boolean withGeneratedMarker) {
        javadocComment(commentDescriptionAction, commentTagAction, withGeneratedMarker, false, 0);
    }

    /**
     * Helper method to add a javadoc comment to the contents.
     * @param commentDescriptionAction
     * @param commentTagAction
     * @param withGeneratedMarker
     * @param withWordWrap
     * @param wordWrapWidth
     */
    private void javadocComment(Consumer<JavaJavadocComment> commentDescriptionAction, Consumer<JavaJavadocComment> commentTagAction, boolean withGeneratedMarker, boolean withWordWrap, int wordWrapWidth) {
        addExpectedNewLine();
        if (commentDescriptionAction != null) {
            contents.javadocCommentStart();
            if (withGeneratedMarker) {
                contents.line(GENERATED_JAVADOC_DESC_START_MARKER);
            }
            if (withWordWrap) {
                contents.withWordWrap(wordWrapWidth, () -> commentDescriptionAction.accept(new JavaJavadocComment(contents)));
            } else {
                commentDescriptionAction.accept(new JavaJavadocComment(contents));
            }
            if (withGeneratedMarker) {
                contents.line(GENERATED_JAVADOC_DESC_END_MARKER);
            }
            if (commentTagAction == null) { // if no tags action, then end the javadoc comment
                contents.javadocCommentEnd();
            } else { // otherwise, a new line will be added and the tags action will start
                contents.line();
            }
        }
        if (commentTagAction != null) {
            if (commentDescriptionAction == null) {// if no description action, then start the javadoc comment
                contents.javadocCommentStart();
            }
            if (withGeneratedMarker) {
                contents.line(GENERATED_JAVADOC_TAG_START_MARKER);
            }
            if (withWordWrap) {
                contents.withWordWrap(wordWrapWidth, () -> commentTagAction.accept(new JavaJavadocComment(contents)));
            } else {
                commentTagAction.accept(new JavaJavadocComment(contents));
            }
            if (withGeneratedMarker) {
                contents.line(GENERATED_JAVADOC_TAG_END_MARKER);
            }
            contents.javadocCommentEnd();
        }
    }

    public final void annotation(String... annotations) {
        addExpectedNewLine();
        contents.annotation(annotations);
    }

    public final void staticBlock(Consumer<JavaBlock> codeBlock) {
        addExpectedNewLine();
        contents.block("static", codeBlock);
    }
}
