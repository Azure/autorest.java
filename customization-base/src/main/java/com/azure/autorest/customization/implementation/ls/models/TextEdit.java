package com.azure.autorest.customization.implementation.ls.models;

import com.azure.autorest.customization.models.Range;

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
