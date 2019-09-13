package com.azure.autorest.model.javamodel;

import java.util.function.Consumer;

public class JavaBlock implements JavaContext
{
    private JavaFileContents contents;

    public JavaBlock(JavaFileContents contents)
    {
        this.contents = contents;
    }

    public final void Indent(Runnable indentAction)
    {
        contents.Indent(indentAction);
    }

    public final void IncreaseIndent()
    {
        contents.IncreaseIndent();
    }

    public final void DecreaseIndent()
    {
        contents.DecreaseIndent();
    }

    public final void Text(String text)
    {
        contents.Text(text);
    }

    public final void Line(String text, Object... formattedArguments)
    {
        contents.Line(text, formattedArguments);
    }

    public final void Line()
    {
        contents.Line();
    }

    public final void Block(String text, Consumer<JavaBlock> bodyAction)
    {
        contents.Block(text, bodyAction);
    }

    public final void JavadocComment(String text)
    {
        contents.JavadocComment(text);
    }

    public final void JavadocComment(Consumer<JavaJavadocComment> commentAction)
    {
        contents.JavadocComment(commentAction);
    }

    public final void Return(String text)
    {
        contents.Return(text);
    }

    public final void Annotation(String... annotations)
    {
        contents.Annotation(annotations);
    }

    public final void ReturnAnonymousClass(String anonymousClassDeclaration, Consumer<JavaClass> anonymousClassBlock)
    {
        contents.ReturnAnonymousClass(anonymousClassDeclaration, anonymousClassBlock);
    }

    public final JavaIfBlock If(String condition, Consumer<JavaBlock> ifAction)
    {
        contents.If(condition, ifAction);
        return new JavaIfBlock(contents);
    }

    public final void Lambda(String parameterType, String parameterName, Consumer<JavaLambda> body)
    {
        contents.Lambda(parameterType, parameterName, body);
    }

    public final void Lambda(String parameterType, String parameterName, String returnExpression)
    {
        contents.Lambda(parameterType, parameterName, returnExpression);
    }
}