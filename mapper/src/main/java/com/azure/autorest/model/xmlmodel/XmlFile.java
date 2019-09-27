package com.azure.autorest.model.xmlmodel;

import java.util.Map;
import java.util.function.Consumer;

public class XmlFile
{
    public XmlFile(String filePath)
    {
        this(filePath, null);
    }

    public XmlFile(String filePath, String fileContents)
    {
        FilePath = filePath;
        Contents = new XmlFileContents(fileContents);
    }

    private String FilePath;
    public final String getFilePath()
    {
        return FilePath;
    }

    private XmlFileContents Contents;
    public final XmlFileContents getContents()
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

    public final void Tag(String tag, String value) {
        getContents().Tag(tag, value);
    }

    public final void Indent(Runnable indentAction)
    {
        getContents().Indent(indentAction);
    }

    public void Block(String text, Consumer<XmlBlock> bodyAction)
    {
        getContents().Block(text, bodyAction);
    }

    public void Block(String text, Map<String, String> annotations, Consumer<XmlBlock> bodyAction)
    {
        getContents().Block(text, annotations, bodyAction);
    }

    public void BlockComment(String text) {
        getContents().BlockComment(text);
    }

    public void BlockComment(Consumer<XmlLineComment> commentAction)
    {
        getContents().BlockComment(commentAction);
    }

    public void BlockComment(int wordWrapWidth, Consumer<XmlLineComment> commentAction)
    {
        getContents().BlockComment(wordWrapWidth, commentAction);
    }
}