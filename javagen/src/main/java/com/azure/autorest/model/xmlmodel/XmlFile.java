package com.azure.autorest.model.xmlmodel;

import java.util.Map;
import java.util.function.Consumer;

public class XmlFile {
    private String FilePath;
    private XmlFileContents Contents;

    public static class Options {
        private int indent = 4;

        public int getIndent() {
            return indent;
        }

        public Options setIndent(int indent) {
            this.indent = indent;
            return this;
        }
    }

    public XmlFile(String filePath) {
        this(filePath, null, null);
    }

    public XmlFile(String filePath, Options options) {
        this(filePath, null, options);
    }

    public XmlFile(String filePath, String fileContents) {
        this(filePath, fileContents, null);
    }

    public XmlFile(String filePath, String fileContents, Options options) {
        FilePath = filePath;
        Contents = new XmlFileContents(fileContents, options);
    }

    public final String getFilePath() {
        return FilePath;
    }

    public final XmlFileContents getContents() {
        return Contents;
    }

    public final void text(String text) {
        getContents().text(text);
    }

    public final void line(String text) {
        getContents().line(text);
    }

    public final void line() {
        getContents().line();
    }

    public final void tag(String tag, String value) {
        getContents().tag(tag, value);
    }

    public final void indent(Runnable indentAction) {
        getContents().indent(indentAction);
    }

    public void block(String text, Consumer<XmlBlock> bodyAction) {
        getContents().block(text, bodyAction);
    }

    public void block(String text, Map<String, String> annotations, Consumer<XmlBlock> bodyAction) {
        getContents().block(text, annotations, bodyAction);
    }

    public void blockComment(String text) {
        getContents().blockComment(text);
    }

    public void blockComment(Consumer<XmlLineComment> commentAction) {
        getContents().blockComment(commentAction);
    }

    public void blockComment(int wordWrapWidth, Consumer<XmlLineComment> commentAction) {
        getContents().blockComment(wordWrapWidth, commentAction);
    }
}
