package com.azure.autorest.postprocessor.ls.models;

public class TextEdit {
    private Range range;
    private String newText;

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public String getNewText() {
        return newText;
    }

    public void setNewText(String newText) {
        this.newText = newText;
    }
}
