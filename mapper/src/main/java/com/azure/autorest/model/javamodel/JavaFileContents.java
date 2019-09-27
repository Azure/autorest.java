//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per snippet.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.model.javamodel;

import com.azure.autorest.util.CodeNamer;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class JavaFileContents
{
    private static final String singleIndent = "    ";

    private StringBuilder contents;
    private StringBuilder linePrefix;

    private Integer wordWrapWidth = null;

    private CurrentLineType currentLineType = CurrentLineType.values()[0];
    private enum CurrentLineType
    {
        Empty,
        AfterIf,
        Text;

        public static final int SIZE = java.lang.Integer.SIZE;

        public int getValue()
        {
            return this.ordinal();
        }

        public static CurrentLineType forValue(int value)
        {
            return values()[value];
        }
    }

    public JavaFileContents()
    {
        this(null);
    }

    public JavaFileContents(String fileContents)
    {
        contents = new StringBuilder();
        linePrefix = new StringBuilder();

        if (fileContents != null && !fileContents.isEmpty())
        {
            contents.append(fileContents);
        }
    }

    @Override
    public String toString()
    {
        return contents.toString();
    }

    public final String[] getLines()
    {
        return toString().split(java.util.regex.Pattern.quote("\n"), -1);
    }

    public final void AddToPrefix(String toAdd)
    {
        linePrefix.append(toAdd);
    }

    private void RemoveFromPrefix(String toRemove)
    {
        int toRemoveLength = toRemove.length();
        if (linePrefix.length() <= toRemoveLength)
        {
            linePrefix.setLength(0);
        }
        else
        {
            linePrefix.delete(linePrefix.length() - toRemoveLength, linePrefix.length() - toRemoveLength + toRemoveLength);
        }
    }

    public final void SetWordWrapWidth(Integer wordWrapWidth)
    {
        this.wordWrapWidth = wordWrapWidth;
    }

    private void WithWordWrap(int wordWrapWidth, Runnable action)
    {
        SetWordWrapWidth(wordWrapWidth);
        action.run();
        SetWordWrapWidth(null);
    }

    public final void Indent(Runnable action)
    {
        IncreaseIndent();
        action.run();
        DecreaseIndent();
    }

    public final void IncreaseIndent()
    {
        AddToPrefix(singleIndent);
    }

    public final void DecreaseIndent()
    {
        RemoveFromPrefix(singleIndent);
    }

    private List<String> WordWrap(String line, boolean addPrefix)
    {
        ArrayList<String> lines = new ArrayList<String>();

        if (wordWrapWidth == null)
        {
            lines.add(line);
        }
        else
        {
            // Subtract an extra column from the word wrap width because columns generally are
            // 1 -based instead of 0-based.
            int wordWrapIndexMinusLinePrefixLength = wordWrapWidth.intValue() - (addPrefix ? linePrefix.length() : 0) - 1;
            List<String> wrappedLines = CodeNamer.WordWrap(line, wordWrapIndexMinusLinePrefixLength);
            for (int i = 0; i != wrappedLines.size() - 1; i++)
            {
                lines.add(wrappedLines.get(i) + "\n");
            }

            String lastWrappedLine = wrappedLines.get(wrappedLines.size() - 1);
            if (lastWrappedLine != null && !lastWrappedLine.isEmpty())
            {
                lines.add(lastWrappedLine);
            }
        }

        return lines;
    }

    private void Text(String text, boolean addPrefix)
    {
        ArrayList<String> lines = new ArrayList<String>();

        if (text == null || text.isEmpty())
        {
            lines.add("");
        }
        else
        {
            int lineStartIndex = 0;
            int textLength = text.length();
            while (lineStartIndex < textLength)
            {
                int newLineCharacterIndex = text.indexOf('\n', lineStartIndex);
                if (newLineCharacterIndex == -1)
                {
                    String line = text.substring(lineStartIndex);
                    List<String> wrappedLines = WordWrap(line, addPrefix);
                    lines.addAll(wrappedLines);
                    lineStartIndex = textLength;
                }
                else
                {
                    int nextLineStartIndex = newLineCharacterIndex + 1;
                    String line = text.substring(lineStartIndex, nextLineStartIndex);
                    List<String> wrappedLines = WordWrap(line, addPrefix);
                    lines.addAll(wrappedLines);
                    lineStartIndex = nextLineStartIndex;
                }
            }
        }

        String prefix = addPrefix ? linePrefix.toString() : null;
        for (String line : lines)
        {
            if (addPrefix && prefix != null && !prefix.trim().isEmpty() || (prefix != null && !prefix.isEmpty() && line != null && !line.trim().isEmpty()))
            {
                contents.append(prefix);
            }

            contents.append(line);
        }
    }

    public final void Text(String text)
    {
        if (currentLineType == CurrentLineType.Empty)
        {
            Text(text, true);
        }
        else if (currentLineType == CurrentLineType.Text)
        {
            Text(text, false);
        }
        else if (currentLineType == CurrentLineType.AfterIf)
        {
            Line("", false);
            Text(text, true);
        }
        currentLineType = CurrentLineType.Text;
    }

    private void Line(String text, boolean addPrefix)
    {
        Text(String.format("%1$s%2$s", text, System.lineSeparator()), addPrefix);
        currentLineType = CurrentLineType.Empty;
    }

    public void Line(String text, Object... formattedArguments)
    {
        if (formattedArguments != null && formattedArguments.length > 0)
        {
            text = String.format(text, formattedArguments);
        }

        if (currentLineType == CurrentLineType.Empty)
        {
            Line(text, true);
        }
        else if (currentLineType == CurrentLineType.Text)
        {
            Line(text, false);
        }
        else if (currentLineType == CurrentLineType.AfterIf)
        {
            Line("", false);
            Line(text, true);
        }
        currentLineType = CurrentLineType.Empty;
    }

    public void Line()
    {
        Line("");
    }

    public void Package(String pkg)
    {
        Line("package %s;", pkg);
    }

    public void Block(String text, Consumer<JavaBlock> bodyAction)
    {
        Line("%s {{", text);
        Indent(() ->
                bodyAction.accept(new JavaBlock(this)));
        Line("}}");
    }

    public void Import(String... imports)
    {
        Import(Arrays.asList(imports));
    }

    public void Import(List<String> imports)
    {
        if (imports != null && !imports.isEmpty())
        {
            Set<String> importSet = new TreeSet<>(new JavaImportComparer());
            importSet.addAll(imports);
            for (String toImport : importSet)
            {
                if (toImport != null && !toImport.isEmpty())
                {
                    Line("import %s;", toImport);
                }
            }
            Line();
        }
    }

    public void LineComment(String text)
    {
        LineComment(comment -> comment.Line(text));
    }

    public void LineComment(Consumer<JavaLineComment> commentAction)
    {
        AddToPrefix("// ");
        commentAction.accept(new JavaLineComment(this));
        RemoveFromPrefix("// ");
    }

    public void LineComment(int wordWrapWidth, Consumer<JavaLineComment> commentAction)
    {
        LineComment((comment) -> WithWordWrap(wordWrapWidth, () ->
                commentAction.accept(new JavaLineComment(this))));
    }

    public void BlockComment(String text) {
        BlockComment(comment -> comment.Line(text));
    }

    public void BlockComment(Consumer<JavaLineComment> commentAction)
    {
        Line("/*");
        AddToPrefix(" * ");
        commentAction.accept(new JavaLineComment(this));
        RemoveFromPrefix(" * ");
        Line(" */");
    }

    public void BlockComment(int wordWrapWidth, Consumer<JavaLineComment> commentAction)
    {
        BlockComment((comment) -> WithWordWrap(wordWrapWidth, () ->
                commentAction.accept(new JavaLineComment(this))));
    }

    public void JavadocComment(String text)
    {
        JavadocComment(comment -> comment.Description(text));
    }

    public void JavadocComment(Consumer<JavaJavadocComment> commentAction)
    {
        Line("/**");
        AddToPrefix(" * ");
        commentAction.accept(new JavaJavadocComment(this));
        RemoveFromPrefix(" * ");
        Line(" */");
    }

    public void JavadocComment(int wordWrapWidth, Consumer<JavaJavadocComment> commentAction)
    {
        JavadocComment((comment) -> WithWordWrap(wordWrapWidth, () ->
                commentAction.accept(new JavaJavadocComment(this))));
    }

    public void Return(String text)
    {
        Line("return %s;", text);
    }

    public void ReturnAnonymousClass(String anonymousClassDeclaration, Consumer<JavaClass> anonymousClassBlock)
    {
        Line("return {anonymousClassDeclaration} {{");
        Indent(() -> {
                JavaClass javaClass = new JavaClass(this);
                anonymousClassBlock.accept(javaClass);
        });
        Line("};");
    }

    public void Annotation(String... annotations)
    {
        Annotation(Arrays.asList(annotations));
    }

    public void Annotation(List<String> annotations)
    {
        if (annotations != null && !annotations.isEmpty())
        {
            for (String annotation : annotations)
            {
                if (annotation != null && !annotation.isEmpty())
                {
                    Line("@", annotation);
                }
            }
        }
    }

    private static String ToString(JavaVisibility visiblity)
    {
        String result;
        switch (visiblity) {
            case PackagePrivate:
                result = "";
                break;

            default:
                result = visiblity.toString().toLowerCase() + ' ';
                break;
        }
        return result;
    }

    private static String ToString(List<JavaModifier> modifiers) {
        return modifiers == null ? "" : modifiers.stream().map(modifier -> modifier.toString().toLowerCase() + ' ').collect(Collectors.joining(""));
    }

    public void Class(JavaVisibility visibility, List<JavaModifier> modifiers, String classDeclaration, Consumer<JavaClass> classAction)
    {
        Block("{ToString(visibility)}{ToString(modifiers)}class {classDeclaration}", blockAction -> {
            if (classAction != null)
            {
                JavaClass javaClass = new JavaClass(this);
                classAction.accept(javaClass);
            }
        });
    }

    public void Method(JavaVisibility visibility, List<JavaModifier> modifiers, String methodSignature, Consumer<JavaBlock> method)
    {
        Block("{ToString(visibility)}{ToString(modifiers)}{methodSignature}", method);
    }

    public void Enum(JavaVisibility visibility, String enumName, Consumer<JavaEnum> enumAction)
    {
        Block("{ToString(visibility)}enum {enumName}", block -> {
            if (enumAction != null)
            {
                JavaEnum javaEnum = new JavaEnum(this);
                enumAction.accept(javaEnum);
                javaEnum.AddExpectedNewLineAfterLastValue();
            }
        });
    }

    public void Interface(JavaVisibility visibility, String interfaceSignature, Consumer<JavaInterface> interfaceAction)
    {
        Line("{ToString(visibility)}interface {interfaceSignature} {{");
        Indent(() -> interfaceAction.accept(new JavaInterface(this)));
        Line("}");
    }

    public void If(String condition, Consumer<JavaBlock> ifAction)
    {
        Line("if ({condition}) {{");
        Indent(() ->
                {
                        ifAction.accept(new JavaBlock(this));
            });
        Text("}}");
        currentLineType = CurrentLineType.AfterIf;
    }

    public void Else(Consumer<JavaBlock> elseAction)
    {
        Line(" else {{", false);
        Indent(() ->
                {
                        elseAction.accept(new JavaBlock(this));
            });
        Line("}}");
    }

    public void Lambda(String parameterType, String parameterName, Consumer<JavaLambda> body)
    {
        Text("({parameterType} {parameterName}) -> ");
        try (JavaLambda lambda = new JavaLambda(this))
        {
            body.accept(lambda);
        }
    }

    public void Lambda(String parameterType, String parameterName, String returnExpression)
    {
        Lambda(parameterType, parameterName, lambda ->
                {
                        lambda.Return(returnExpression);
            });
    }
}