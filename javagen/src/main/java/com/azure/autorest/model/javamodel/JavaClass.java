package com.azure.autorest.model.javamodel;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

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
        method(JavaVisibility.Public, Arrays.asList(JavaModifier.Static), methodSignature, method);
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
        addExpectedNewLine();
        contents.classBlock(JavaVisibility.Private, Arrays.asList(JavaModifier.Static, JavaModifier.Final), classSignature, classBlock);
        addNewLine = true;
    }

    public final void blockComment(String description) {
        addExpectedNewLine();
        contents.blockComment(description);
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

    public final void annotation(String... annotations) {
        addExpectedNewLine();
        contents.annotation(annotations);
    }
}
