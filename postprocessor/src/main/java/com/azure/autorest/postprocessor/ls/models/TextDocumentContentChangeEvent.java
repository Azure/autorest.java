package com.azure.autorest.postprocessor.ls.models;

public class TextDocumentContentChangeEvent {
    private Range range;
    private String text;

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
