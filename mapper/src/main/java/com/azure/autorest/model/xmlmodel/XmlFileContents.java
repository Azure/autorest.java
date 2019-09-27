//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per snippet.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.model.xmlmodel;

import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class XmlFileContents
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

        public static final int SIZE = Integer.SIZE;

        public int getValue()
        {
            return this.ordinal();
        }

        public static CurrentLineType forValue(int value)
        {
            return values()[value];
        }
    }

    public XmlFileContents()
    {
        this(null);
    }

    public XmlFileContents(String fileContents)
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

    public void Tag(String tag, String value) {
        Line("<%s>%s</%s>", tag, value, tag);
    }

    public void Block(String text, Consumer<XmlBlock> bodyAction)
    {
        Line("<%s>", text);
        Indent(() ->
                bodyAction.accept(new XmlBlock(this)));
        Line("</%s>", text);
    }

    public void Block(String text, Map<String, String> annotations, Consumer<XmlBlock> bodyAction)
    {
        if (annotations != null && annotations.size() > 0) {
            String append = annotations.entrySet().stream()
                    .map(entry -> String.format("%s=\"%s\"", entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining(" "));
            Line("<%s %s>", text, append);
        } else {
            Line("<%s>", text);
        }
        Indent(() ->
                bodyAction.accept(new XmlBlock(this)));
        Line("</%s>", text);
    }

    public void BlockComment(String text) {
        BlockComment(comment -> comment.Line(text));
    }

    public void BlockComment(Consumer<XmlLineComment> commentAction)
    {
        Line("<!--");
        commentAction.accept(new XmlLineComment(this));
        Line(" -->");
    }

    public void BlockComment(int wordWrapWidth, Consumer<XmlLineComment> commentAction)
    {
        BlockComment((comment) -> WithWordWrap(wordWrapWidth, () ->
                commentAction.accept(new XmlLineComment(this))));
    }
}