package com.azure.autorest.model.javamodel;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class JavaEnum
{
    private JavaFileContents contents;
    private boolean previouslyAddedValue;
    private boolean addNewLine;

    public JavaEnum(JavaFileContents contents)
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

    private void AddExpectedCommaAndNewLine()
    {
        if (previouslyAddedValue)
        {
            contents.Line(",");
            previouslyAddedValue = false;
        }

        AddExpectedNewLine();
    }

    private void AddExpectedSemicolonAndNewLine()
    {
        if (previouslyAddedValue)
        {
            contents.Line(";");
            previouslyAddedValue = false;
        }

        AddExpectedNewLine();
    }

    public final void AddExpectedNewLineAfterLastValue()
    {
        if (previouslyAddedValue)
        {
            contents.Line();
            previouslyAddedValue = false;
            addNewLine = false;
        }
    }

    public final void Value(String name, String value)
    {
        AddExpectedCommaAndNewLine();
        contents.JavadocComment(String.format("Enum value %1$s.", value));
        contents.Text(String.format("%1$s(\"%2$s\")", name, value));
        previouslyAddedValue = true;
        addNewLine = true;
    }

    public final void PrivateFinalMemberVariable(String variableType, String variableName)
    {
        AddExpectedSemicolonAndNewLine();
        contents.Line(String.format("private final %1$s %2$s;", variableType, variableName));
        addNewLine = true;
    }

    public final void Constructor(String constructorSignature, Consumer<JavaBlock> constructor)
    {
        AddExpectedSemicolonAndNewLine();
        contents.Block(String.format("%1$s", constructorSignature), constructor);
        previouslyAddedValue = false;
        addNewLine = true;
    }

    public final void Method(JavaVisibility visibility, List<JavaModifier> modifiers, String methodSignature, Consumer<JavaBlock> method)
    {
        AddExpectedSemicolonAndNewLine();
        contents.Method(visibility, modifiers, methodSignature, method);
        previouslyAddedValue = false;
        addNewLine = true;
    }

    public final void PublicMethod(String methodSignature, Consumer<JavaBlock> method)
    {
        Method(JavaVisibility.Public, null, methodSignature, method);
    }

    public final void PublicStaticMethod(String methodSignature, Consumer<JavaBlock> method)
    {
        Method(JavaVisibility.Public, Collections.singletonList(JavaModifier.Static), methodSignature, method);
    }

    public final void JavadocComment(String description)
    {
        AddExpectedSemicolonAndNewLine();
        contents.JavadocComment(description);
    }

    public final void JavadocComment(Consumer<JavaJavadocComment> commentAction)
    {
        AddExpectedSemicolonAndNewLine();
        contents.JavadocComment(commentAction);
    }

    public final void Annotation(String... annotations)
    {
        AddExpectedSemicolonAndNewLine();
        contents.Annotation(annotations);
    }
}