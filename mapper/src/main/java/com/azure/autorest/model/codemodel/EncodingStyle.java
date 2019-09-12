package com.azure.autorest.model.codemodel;

public enum EncodingStyle {
    MATRIX("matrix"),
    LABEL("label"),
    SIMPLE("simple"),
    FORM("form"),
    SPACE_DELIMITED("spaceDelimited"),
    PIPE_DELIMITED("pipeDelimited"),
    DEEP_OBJECT("deepObject");

    private final String style;

    private EncodingStyle(String style) {
        this.style = style;
    }
}