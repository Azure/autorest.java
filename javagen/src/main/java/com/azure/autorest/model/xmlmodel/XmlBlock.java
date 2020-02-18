package com.azure.autorest.model.xmlmodel;

import java.util.function.Consumer;

public class XmlBlock {
    private XmlFileContents contents;

    public XmlBlock(XmlFileContents contents) {
        this.contents = contents;
    }

    public final void indent(Runnable indentAction) {
        contents.indent(indentAction);
    }

    public final void increaseIndent() {
        contents.increaseIndent();
    }

    public final void decreaseIndent() {
        contents.decreaseIndent();
    }

    public final void text(String text) {
        contents.text(text);
    }

    public final void line(String text, Object... formattedArguments) {
        contents.line(text, formattedArguments);
    }

    public final void line() {
        contents.line();
    }

    public final void tag(String tag, String value) {
        contents.tag(tag, value);
    }

    public final void block(String text, Consumer<XmlBlock> bodyAction) {
        contents.block(text, bodyAction);
    }
}
