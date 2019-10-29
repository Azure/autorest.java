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

    public final void text(String text)
    {
        getContents().text(text);
    }

    public final void line(String text)
    {
        getContents().line(text);
    }

    public final void line()
    {
        getContents().line();
    }

    public final void tag(String tag, String value) {
        getContents().tag(tag, value);
    }

    public final void indent(Runnable indentAction)
    {
        getContents().indent(indentAction);
    }

    public void block(String text, Consumer<XmlBlock> bodyAction)
    {
        getContents().block(text, bodyAction);
    }

    public void block(String text, Map<String, String> annotations, Consumer<XmlBlock> bodyAction)
    {
        getContents().block(text, annotations, bodyAction);
    }

    public void blockComment(String text) {
        getContents().blockComment(text);
    }

    public void blockComment(Consumer<XmlLineComment> commentAction)
    {
        getContents().blockComment(commentAction);
    }

    public void blockComment(int wordWrapWidth, Consumer<XmlLineComment> commentAction)
    {
        getContents().blockComment(wordWrapWidth, commentAction);
    }
}