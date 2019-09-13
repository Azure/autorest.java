package com.azure.autorest.model.javamodel;

public class JavaClass implements JavaType
{
    private JavaFileContents contents;
    private boolean addNewLine;

    public JavaClass(JavaFileContents contents)
    {
        this.contents = contents;
    }

    private void AddExpectedNewLine()
    {
        if (addNewLine)
        {
            contents.Line();
            addNewLine = false;
        }
    }

    public final void PrivateMemberVariable(String variableType, String variableName)
    {
        PrivateMemberVariable(String.format("%1$s %2$s", variableType, variableName));
    }

    public final void PrivateMemberVariable(String variableDeclaration)
    {
        AddExpectedNewLine();
        contents.Line(String.format("private %1$s;", variableDeclaration));
        addNewLine = true;
    }

    public final void PrivateFinalMemberVariable(String variableType, String variableName)
    {
        AddExpectedNewLine();
        contents.Line(String.format("private final %1$s %2$s;", variableType, variableName));
        addNewLine = true;
    }

    public final void PublicStaticFinalVariable(String variableDeclaration)
    {
        AddExpectedNewLine();
        contents.Line(String.format("public static final %1$s;", variableDeclaration));
        addNewLine = true;
    }

    public final void PrivateConstructor(String constructorSignature, Consumer<JavaBlock> constructor)
    {
        AddExpectedNewLine();
        contents.Block(String.format("private %1$s", constructorSignature), constructor);
        addNewLine = true;
    }

    public final void PublicConstructor(String constructorSignature, Consumer<JavaBlock> constructor)
    {
        AddExpectedNewLine();
        contents.Block(String.format("public %1$s", constructorSignature), constructor);
        addNewLine = true;
    }

    public final void PackagePrivateConstructor(String constructorSignature, Consumer<JavaBlock> constructor)
    {
        AddExpectedNewLine();
        contents.Block(String.format("%1$s", constructorSignature), constructor);
        addNewLine = true;
    }

    public final void Method(JavaVisibility visibility, List<JavaModifier> modifiers, String methodSignature, Consumer<JavaBlock> method)
    {
        AddExpectedNewLine();
        contents.Method(visibility, modifiers, methodSignature, method);
        addNewLine = true;
    }

    public final void PublicMethod(String methodSignature, Consumer<JavaBlock> method)
    {
        Method(JavaVisibility.Public, null, methodSignature, method);
    }

    public final void PackagePrivateMethod(String methodSignature, Consumer<JavaBlock> method)
    {
        Method(JavaVisibility.PackagePrivate, null, methodSignature, method);
    }

    public final void PrivateMethod(String methodSignature, Consumer<JavaBlock> method)
    {
        Method(JavaVisibility.Private, null, methodSignature, method);
    }

    public final void PublicStaticMethod(String methodSignature, Consumer<JavaBlock> method)
    {
        Method(JavaVisibility.Public, new AutoRest.Java.JavaModifier[] {JavaModifier.Static}, methodSignature, method);
    }

    public final void Interface(JavaVisibility visibility, String interfaceSignature, Consumer<JavaInterface> interfaceBlock)
    {
        AddExpectedNewLine();
        contents.Interface(visibility, interfaceSignature, interfaceBlock);
        addNewLine = true;
    }

    public final void PublicInterface(String interfaceSignature, Consumer<JavaInterface> interfaceBlock)
    {
        Interface(JavaVisibility.Public, interfaceSignature, interfaceBlock);
    }

    public final void PrivateStaticFinalClass(String classSignature, Consumer<JavaClass> classBlock)
    {
        AddExpectedNewLine();
        contents.Class(JavaVisibility.Private, new AutoRest.Java.JavaModifier[] {JavaModifier.Static, JavaModifier.Final}, classSignature, classBlock);
        addNewLine = true;
    }

    public final void BlockComment(String description)
    {
        AddExpectedNewLine();
        contents.BlockComment(description);
    }

    public final void BlockComment(Consumer<JavaLineComment> commentAction)
    {
        AddExpectedNewLine();
        contents.BlockComment(commentAction);
    }

    public final void BlockComment(int wordWrapWidth, Consumer<JavaLineComment> commentAction)
    {
        AddExpectedNewLine();
        contents.BlockComment(wordWrapWidth, commentAction);
    }

    public final void JavadocComment(String description)
    {
        AddExpectedNewLine();
        contents.JavadocComment(description);
    }

    public final void JavadocComment(Consumer<JavaJavadocComment> commentAction)
    {
        AddExpectedNewLine();
        contents.JavadocComment(commentAction);
    }

    public final void JavadocComment(int wordWrapWidth, Consumer<JavaJavadocComment> commentAction)
    {
        AddExpectedNewLine();
        contents.JavadocComment(wordWrapWidth, commentAction);
    }

    public final void Annotation(String... annotations)
    {
        AddExpectedNewLine();
        contents.Annotation(annotations);
    }
}