package com.azure.autorest.model.javamodel;

public class JavaLineComment {
    private JavaFileContents contents;

    public JavaLineComment(JavaFileContents contents) {
        this.contents = contents;
    }

    public final void line(String text) {
        contents.line(text);
    }
}
