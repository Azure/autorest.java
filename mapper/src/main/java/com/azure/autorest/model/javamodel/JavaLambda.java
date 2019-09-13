package com.azure.autorest.model.javamodel;

import java.io.*;
import java.util.function.Consumer;

public class JavaLambda implements Closeable
{
    private JavaFileContents contents;
    private boolean isFirstStatement;
    private boolean needsClosingCurlyBracket;

    public JavaLambda(JavaFileContents contents)
    {
        this.contents = contents;
        isFirstStatement = true;
        needsClosingCurlyBracket = false;
    }

    private void NonReturnStatement()
    {
        if (isFirstStatement)
        {
            isFirstStatement = false;

            contents.Line("{");
            contents.IncreaseIndent();
            needsClosingCurlyBracket = true;
        }
    }

    public final void close()
    {
        if (needsClosingCurlyBracket)
        {
            contents.DecreaseIndent();
            contents.Text("}");
        }
    }

    public final void Line(String text)
    {
        NonReturnStatement();
        contents.Line(text);
    }

    public final void IncreaseIndent()
    {
        contents.IncreaseIndent();
    }

    public final void DecreaseIndent()
    {
        contents.DecreaseIndent();
    }

    public final JavaIfBlock If(String condition, Consumer<JavaBlock> ifAction)
    {
        NonReturnStatement();
        contents.If(condition, ifAction);
        return new JavaIfBlock(contents);
    }

    public final void Return(String text)
    {
        if (isFirstStatement)
        {
            contents.Text(text);
        }
        else
        {
            contents.Return(text);
        }
    }
}