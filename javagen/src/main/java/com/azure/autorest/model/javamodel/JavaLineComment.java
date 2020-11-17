package com.azure.autorest.model.javamodel;

import com.azure.autorest.util.CodeNamer;

public class JavaLineComment {
    private JavaFileContents contents;

    public JavaLineComment(JavaFileContents contents) {
        this.contents = contents;
    }

    public final void line(String text) {
        contents.line(CodeNamer.escapeComment(text));
    }
}
