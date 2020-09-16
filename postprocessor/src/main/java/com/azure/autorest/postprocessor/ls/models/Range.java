package com.azure.autorest.postprocessor.ls.models;

public class Range {
    private Position start;
    private Position end;

    public Position getStart() {
        return start;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public Position getEnd() {
        return end;
    }

    public void setEnd(Position end) {
        this.end = end;
    }
}
