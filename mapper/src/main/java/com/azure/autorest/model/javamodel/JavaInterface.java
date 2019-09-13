package com.azure.autorest.model.javamodel;

import java.util.function.Consumer;

public class JavaInterface implements JavaType
{
    private JavaFileContents contents;
    private boolean addNewLine;

    public JavaInterface(JavaFileContents contents)
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

    public final void PublicMethod(String methodSignature)
    {
        PublicMethod(methodSignature, null);
    }

    public final void PublicMethod(String methodSignature, Consumer<JavaBlock> functionBlock)
    {
        AddExpectedNewLine();
        contents.Line(methodSignature + ";");

        addNewLine = true;
    }

    public final void JavadocComment(Consumer<JavaJavadocComment> commentAction)
    {
        AddExpectedNewLine();
        contents.JavadocComment(commentAction);
    }

    public final void LineComment(String comment)
    {
        AddExpectedNewLine();
        contents.LineComment(comment);
    }

    public final void Annotation(String... annotations)
    {
        AddExpectedNewLine();
        contents.Annotation(annotations);
    }
}