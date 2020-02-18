package com.azure.autorest.model.xmlmodel;

public class XmlLineComment {
    private XmlFileContents contents;

    public XmlLineComment(XmlFileContents contents) {
        this.contents = contents;
    }

    public final void line(String text) {
        contents.line(text);
    }
}
