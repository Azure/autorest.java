// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per snippet.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.model.xmlmodel;

import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class XmlFileContents {
    private String singleIndent = "    ";

    private StringBuilder contents;
    private StringBuilder linePrefix;

    private Integer wordWrapWidth = null;

    private CurrentLineType currentLineType = CurrentLineType.values()[0];

    public XmlFileContents() {
        this(null);
    }

    public XmlFileContents(String fileContents) {
        this(fileContents, null);
    }

    public XmlFileContents(String fileContents, XmlFile.Options options) {
        if (options != null) {
            if (options.getIndent() > 0) {
                char[] chars = new char[options.getIndent()];
                Arrays.fill(chars, ' ');
                singleIndent = String.valueOf(chars);
            }
        }

        contents = new StringBuilder();
        linePrefix = new StringBuilder();

        if (fileContents != null && !fileContents.isEmpty()) {
            contents.append(fileContents);
        }
    }

    @Override
    public String toString() {
        return contents.toString();
    }

    public final String[] getLines() {
        return toString().split(java.util.regex.Pattern.quote("\n"), -1);
    }

    public final void addToPrefix(String toAdd) {
        linePrefix.append(toAdd);
    }

    private void removeFromPrefix(String toRemove) {
        int toRemoveLength = toRemove.length();
        if (linePrefix.length() <= toRemoveLength) {
            linePrefix.setLength(0);
        } else {
            linePrefix.delete(linePrefix.length() - toRemoveLength, linePrefix.length() - toRemoveLength + toRemoveLength);
        }
    }

    public final void setWordWrapWidth(Integer wordWrapWidth) {
        this.wordWrapWidth = wordWrapWidth;
    }

    private void withWordWrap(int wordWrapWidth, Runnable action) {
        setWordWrapWidth(wordWrapWidth);
        action.run();
        setWordWrapWidth(null);
    }

    public final void indent(Runnable action) {
        increaseIndent();
        action.run();
        decreaseIndent();
    }

    public final void increaseIndent() {
        addToPrefix(singleIndent);
    }

    public final void decreaseIndent() {
        removeFromPrefix(singleIndent);
    }

    private List<String> wordWrap(String line, boolean addPrefix) {
        ArrayList<String> lines = new ArrayList<String>();

        if (wordWrapWidth == null) {
            lines.add(line);
        } else {
            // Subtract an extra column from the word wrap width because columns generally are
            // 1 -based instead of 0-based.
            int wordWrapIndexMinusLinePrefixLength = wordWrapWidth.intValue() - (addPrefix ? linePrefix.length() : 0) - 1;
            List<String> wrappedLines = CodeNamer.wordWrap(line, wordWrapIndexMinusLinePrefixLength);
            for (int i = 0; i != wrappedLines.size() - 1; i++) {
                lines.add(wrappedLines.get(i) + "\n");
            }

            String lastWrappedLine = wrappedLines.get(wrappedLines.size() - 1);
            if (lastWrappedLine != null && !lastWrappedLine.isEmpty()) {
                lines.add(lastWrappedLine);
            }
        }

        return lines;
    }

    private void text(String text, boolean addPrefix) {
        ArrayList<String> lines = new ArrayList<String>();

        if (text == null || text.isEmpty()) {
            lines.add("");
        } else {
            int lineStartIndex = 0;
            int textLength = text.length();
            while (lineStartIndex < textLength) {
                int newLineCharacterIndex = text.indexOf('\n', lineStartIndex);
                if (newLineCharacterIndex == -1) {
                    String line = text.substring(lineStartIndex);
                    List<String> wrappedLines = wordWrap(line, addPrefix);
                    lines.addAll(wrappedLines);
                    lineStartIndex = textLength;
                } else {
                    int nextLineStartIndex = newLineCharacterIndex + 1;
                    String line = text.substring(lineStartIndex, nextLineStartIndex);
                    List<String> wrappedLines = wordWrap(line, addPrefix);
                    lines.addAll(wrappedLines);
                    lineStartIndex = nextLineStartIndex;
                }
            }
        }

        String prefix = addPrefix ? linePrefix.toString() : null;
        for (String line : lines) {
            if (addPrefix && prefix != null && !prefix.trim().isEmpty() || (prefix != null && !prefix.isEmpty() && line != null && !line.trim().isEmpty())) {
                contents.append(prefix);
            }

            contents.append(line);
        }
    }

    public final void text(String text) {
        if (currentLineType == CurrentLineType.Empty) {
            text(text, true);
        } else if (currentLineType == CurrentLineType.Text) {
            text(text, false);
        } else if (currentLineType == CurrentLineType.AfterIf) {
            line("", false);
            text(text, true);
        }
        currentLineType = CurrentLineType.Text;
    }

    private void line(String text, boolean addPrefix) {
        text(String.format("%1$s%2$s", text, System.lineSeparator()), addPrefix);
        currentLineType = CurrentLineType.Empty;
    }

    public void line(String text, Object... formattedArguments) {
        if (formattedArguments != null && formattedArguments.length > 0) {
            text = String.format(text, formattedArguments);
        }

        if (currentLineType == CurrentLineType.Empty) {
            line(text, true);
        } else if (currentLineType == CurrentLineType.Text) {
            line(text, false);
        } else if (currentLineType == CurrentLineType.AfterIf) {
            line("", false);
            line(text, true);
        }
        currentLineType = CurrentLineType.Empty;
    }

    public void line() {
        line("");
    }

    public void tag(String tag, String value) {
        line("<%s>%s</%s>", tag, value, tag);
    }

    public void block(String text, Consumer<XmlBlock> bodyAction) {
        line("<%s>", text);
        indent(() ->
                bodyAction.accept(new XmlBlock(this)));
        line("</%s>", text);
    }

    public void block(String text, Map<String, String> annotations, Consumer<XmlBlock> bodyAction) {
        if (annotations != null && annotations.size() > 0) {
            String append = annotations.entrySet().stream()
                    .map(entry -> String.format("%s=\"%s\"", entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining(" "));
            line("<%s %s>", text, append);
        } else {
            line("<%s>", text);
        }
        indent(() ->
                bodyAction.accept(new XmlBlock(this)));
        line("</%s>", text);
    }

    public void blockComment(String text) {
        blockComment(comment -> comment.line(text));
    }

    public void blockComment(Consumer<XmlLineComment> commentAction) {
        line("<!--");
        commentAction.accept(new XmlLineComment(this));
        line(" -->");
    }

    public void blockComment(int wordWrapWidth, Consumer<XmlLineComment> commentAction) {
        blockComment((comment) -> withWordWrap(wordWrapWidth, () ->
                commentAction.accept(new XmlLineComment(this))));
    }

    private enum CurrentLineType {
        Empty,
        AfterIf,
        Text;

        public static final int SIZE = Integer.SIZE;

        public static CurrentLineType forValue(int value) {
            return values()[value];
        }

        public int getValue() {
            return this.ordinal();
        }
    }
}
