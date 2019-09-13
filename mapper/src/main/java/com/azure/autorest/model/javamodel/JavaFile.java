package com.azure.autorest.model.javamodel;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class JavaFile implements JavaContext
{
    private String package_Keyword;
    private int packageWithPeriodLength;

    public JavaFile(String filePath)
    {
        this(filePath, null);
    }

    public JavaFile(String filePath, String fileContents)
    {
        FilePath = filePath;
        Contents = new JavaFileContents(fileContents);
    }

    private String FilePath;
    public final String getFilePath()
    {
        return FilePath;
    }

    private JavaFileContents Contents;
    public final JavaFileContents getContents()
    {
        return Contents;
    }

    public final void Text(String text)
    {
        getContents().Text(text);
    }

    public final void Line(String text)
    {
        getContents().Line(text);
    }

    public final void Line()
    {
        getContents().Line();
    }

    public final void Indent(Runnable indentAction)
    {
        getContents().Indent(indentAction);
    }

    public final void PublicFinalClass(String classDeclaration, Consumer<JavaClass> classAction)
    {
        PublicClass(Arrays.asList(JavaModifier.Final), classDeclaration, classAction);
    }

    public final void PublicClass(List<JavaModifier> modifiers, String classDeclaration, Consumer<JavaClass> classAction)
    {
        Class(JavaVisibility.Public, modifiers, classDeclaration, classAction);
    }

    public final void Class(JavaVisibility visibility, List<JavaModifier> modifiers, String classDeclaration, Consumer<JavaClass> classAction)
    {
        getContents().Class(visibility, modifiers, classDeclaration, classAction);
    }

    public final void Package(String package_Keyword)
    {
        this.package_Keyword = package_Keyword;
        if (package_Keyword == null || package_Keyword.isEmpty())
        {
            packageWithPeriodLength = 0;
        }
        else
        {
            packageWithPeriodLength = package_Keyword.length();
            if (!package_Keyword.endsWith("."))
            {
                ++packageWithPeriodLength;
            }
        }
        getContents().Package(package_Keyword);
    }

    public final void Import(String... imports)
    {
        getContents().Import(imports);
    }

    public final void Import(List<String> imports)
    {
        if (package_Keyword != null && !package_Keyword.isEmpty())
        {
            // Only import paths that don't start with this file's package, or if they do start
            // with this file's package, then they must exist within a subpackage.
            imports = imports.stream()
                    .filter(import_Keyword -> !import_Keyword.startsWith(package_Keyword)
                            || import_Keyword.indexOf('.', packageWithPeriodLength) != -1)
                    .collect(Collectors.toList());
        }
        getContents().Import(imports);
    }

    public final void JavadocComment(Consumer<JavaJavadocComment> commentAction)
    {
        getContents().JavadocComment(commentAction);
    }

    public final void JavadocComment(int wordWrapWidth, Consumer<JavaJavadocComment> commentAction)
    {
        getContents().JavadocComment(wordWrapWidth, commentAction);
    }

    public final void LineComment(int wordWrapWidth, Consumer<JavaLineComment> commentAction)
    {
        getContents().LineComment(wordWrapWidth, commentAction);
    }

    public final void Annotation(String... annotations)
    {
        getContents().Annotation(annotations);
    }

    public final void PublicEnum(String enumName, Consumer<JavaEnum> enumAction)
    {
        Enum(JavaVisibility.Public, enumName, enumAction);
    }

    public final void Enum(JavaVisibility visibility, String enumName, Consumer<JavaEnum> enumAction)
    {
        getContents().Enum(visibility, enumName, enumAction);
    }

    public final void PublicInterface(String interfaceName, Consumer<JavaInterface> interfaceAction)
    {
        Interface(JavaVisibility.Public, interfaceName, interfaceAction);
    }

    public final void Interface(JavaVisibility visibility, String interfaceName, Consumer<JavaInterface> interfaceAction)
    {
        getContents().Interface(visibility, interfaceName, interfaceAction);
    }
}