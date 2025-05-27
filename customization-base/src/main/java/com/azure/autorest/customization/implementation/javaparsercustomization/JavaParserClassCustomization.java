// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.javaparsercustomization;

import com.azure.autorest.customization.ClassCustomization;
import com.azure.autorest.customization.ConstantCustomization;
import com.azure.autorest.customization.ConstructorCustomization;
import com.azure.autorest.customization.Editor;
import com.azure.autorest.customization.JavadocCustomization;
import com.azure.autorest.customization.MethodCustomization;
import com.azure.autorest.customization.PropertyCustomization;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.util.List;
import java.util.function.Consumer;

/**
 * The class level customization for an AutoRest generated class.
 */
public final class JavaParserClassCustomization extends JavaParserCodeCustomization implements ClassCustomization {
    private static final String UNSUPPORTED_MESSAGE
        = "JavaParser-based ClassCustomization only supports the 'customizeAst' method.";
    private final String className;

    JavaParserClassCustomization(Editor editor, String packageName, String className) {
        super(editor, packageName, className);

        this.className = className;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public JavaParserClassCustomization addImports(String... imports) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public JavaParserClassCustomization addStaticBlock(String staticCodeBlock) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public JavaParserClassCustomization addStaticBlock(String staticCodeBlock, List<String> importsToAdd) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public MethodCustomization getMethod(String methodNameOrSignature) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public ConstructorCustomization getConstructor(String constructorNameOrSignature) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public PropertyCustomization getProperty(String propertyName) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public ConstantCustomization getConstant(String constantName) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public JavadocCustomization<?> getJavadoc() {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public ConstructorCustomization addConstructor(String constructor) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public ConstructorCustomization addConstructor(String constructor, List<String> importsToAdd) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public MethodCustomization addMethod(String method) {
        return addMethod(method, null);
    }

    @Override
    public MethodCustomization addMethod(String method, List<String> importsToAdd) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public JavaParserClassCustomization removeMethod(String methodNameOrSignature) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public JavaParserClassCustomization rename(String newName) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public JavaParserClassCustomization setModifier(int modifiers) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public JavaParserClassCustomization addAnnotation(String annotation) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public JavaParserClassCustomization removeAnnotation(String annotation) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public JavaParserClassCustomization renameEnumMember(String enumMemberName, String newName) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public JavaParserClassCustomization customizeAst(Consumer<CompilationUnit> astCustomization) {
        CompilationUnit astToEdit = StaticJavaParser.parse(editor.getFileContent(fileName));
        astCustomization.accept(astToEdit);
        editor.replaceFile(fileName, astToEdit.toString());

        return this;
    }

    public JavaParserClassCustomization refreshSymbol() {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }
}
