package com.azure.autorest.customization.models;

/**
 * Describes a range of text in a file.
 */
public final class Range {
    private Position start;
    private Position end;

    /**
     * Creates an empty range.
     */
    public Range() {
    }

    /**
     * Creates a range from a start position and and end position.
     * @param start the starting position of a range, inclusive
     * @param end the end position of a range, exclusive
     */
    public Range(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the starting position of the range.
     * @return the starting position, inclusive
     */
    public Position getStart() {
        return start;
    }

    /**
     * Sets the starting position of the range.
     * @param start the starting position, inclusive
     */
    public void setStart(Position start) {
        this.start = start;
    }

    /**
     * Gets the ending position of the range.
     * @return the ending position, exclusive
     */
    public Position getEnd() {
        return end;
    }

    /**
     * Sets the ending position of the range.
     * @param end the ending position, exclusive
     */
    public void setEnd(Position end) {
        this.end = end;
    }
}
