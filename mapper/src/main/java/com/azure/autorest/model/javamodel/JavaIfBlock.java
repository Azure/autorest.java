package com.azure.autorest.model.javamodel;

import java.util.function.Consumer;

public class JavaIfBlock
{
    private JavaFileContents contents;

    public JavaIfBlock(JavaFileContents contents)
    {
        this.contents = contents;
    }

    public final void Else(Consumer<JavaBlock> elseAction)
    {
        contents.Else(elseAction);
    }
}