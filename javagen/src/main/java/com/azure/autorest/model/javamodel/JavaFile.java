package com.azure.autorest.model.javamodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class JavaFile implements JavaContext {
    private String package_Keyword;
    private int packageWithPeriodLength;
    private String FilePath;
    private JavaFileContents Contents;

    public JavaFile(String filePath) {
        this(filePath, null);
    }

    public JavaFile(String filePath, String fileContents) {
        FilePath = filePath;
        Contents = new JavaFileContents(fileContents);
    }

    public final String getFilePath() {
        return FilePath;
    }

    public final JavaFileContents getContents() {
        return Contents;
    }

    public final void text(String text) {
        getContents().text(text);
    }

    public final void line(String text) {
        getContents().line(text);
    }

    public final void line() {
        getContents().line();
    }

    public final void indent(Runnable indentAction) {
        getContents().indent(indentAction);
    }

    public final void publicFinalClass(String classDeclaration, Consumer<JavaClass> classAction) {
        publicClass(Arrays.asList(JavaModifier.Final), classDeclaration, classAction);
    }

    public final void publicClass(List<JavaModifier> modifiers, String classDeclaration, Consumer<JavaClass> classAction) {
        classBlock(JavaVisibility.Public, modifiers, classDeclaration, classAction);
    }

    public final void classBlock(JavaVisibility visibility, List<JavaModifier> modifiers, String classDeclaration, Consumer<JavaClass> classAction) {
        getContents().classBlock(visibility, modifiers, classDeclaration, classAction);
    }

    public final void declarePackage(String package_Keyword) {
        this.package_Keyword = package_Keyword;
        if (package_Keyword == null || package_Keyword.isEmpty()) {
            packageWithPeriodLength = 0;
        } else {
            packageWithPeriodLength = package_Keyword.length();
            if (!package_Keyword.endsWith(".")) {
                ++packageWithPeriodLength;
            }
        }
        getContents().declarePackage(package_Keyword);
    }

    public final void declareImport(String... imports) {
        declareImport(Arrays.asList(imports));
    }

    public final void declareImport(Set<String> imports) {
        declareImport(new ArrayList<>(imports));
    }

    public final void declareImport(List<String> imports) {
        if (package_Keyword != null && !package_Keyword.isEmpty()) {
            // Only import paths that don't start with this file's package, or if they do start
            // with this file's package, then they must exist within a subpackage.
            imports = imports.stream()
                    .filter(import_Keyword -> !import_Keyword.startsWith(package_Keyword)
                            || import_Keyword.indexOf('.', packageWithPeriodLength) != -1)
                    .collect(Collectors.toList());
        }
        getContents().declareImport(imports);
    }

    public final void javadocComment(Consumer<JavaJavadocComment> commentAction) {
        getContents().javadocComment(commentAction);
    }

    public final void javadocComment(int wordWrapWidth, Consumer<JavaJavadocComment> commentAction) {
        getContents().javadocComment(wordWrapWidth, commentAction);
    }

    public final void lineComment(int wordWrapWidth, Consumer<JavaLineComment> commentAction) {
        getContents().lineComment(wordWrapWidth, commentAction);
    }

    public final void annotation(String... annotations) {
        getContents().annotation(annotations);
    }

    public final void publicEnum(String enumName, Consumer<JavaEnum> enumAction) {
        enumBlock(JavaVisibility.Public, enumName, enumAction);
    }

    public final void enumBlock(JavaVisibility visibility, String enumName, Consumer<JavaEnum> enumAction) {
        getContents().enumBlock(visibility, enumName, enumAction);
    }

    public final void publicInterface(String interfaceName, Consumer<JavaInterface> interfaceAction) {
        interfaceBlock(JavaVisibility.Public, interfaceName, interfaceAction);
    }

    public final void interfaceBlock(JavaVisibility visibility, String interfaceName, Consumer<JavaInterface> interfaceAction) {
        getContents().interfaceBlock(visibility, interfaceName, interfaceAction);
    }
}