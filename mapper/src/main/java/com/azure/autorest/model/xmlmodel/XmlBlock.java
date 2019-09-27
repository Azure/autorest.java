package com.azure.autorest.model.xmlmodel;

import java.util.function.Consumer;

public class XmlBlock
{
    private XmlFileContents contents;

    public XmlBlock(XmlFileContents contents)
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

    public final void Tag(String tag, String value) {
        contents.Tag(tag, value);
    }

    public final void Block(String text, Consumer<XmlBlock> bodyAction)
    {
        contents.Block(text, bodyAction);
    }
}