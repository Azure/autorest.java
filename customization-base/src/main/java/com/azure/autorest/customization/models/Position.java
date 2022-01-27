// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.models;

/**
 * A cursor position in a text document.
 */
public final class Position {
    private int line;
    private int character;

    /**
     * Creates an empty position.
     */
    public Position() {
    }

    /**
     * Creates a position in a text document.
     * @param line the line of the position
     * @param character the character in the line of the position
     */
    public Position(int line, int character) {
        this.line = line;
        this.character = character;
    }

    /**
     * Gets the line number of the position.
     * @return the line number
     */
    public int getLine() {
        return line;
    }

    /**
     * Sets the line number of the position.
     * @param line the line number
     */
    public void setLine(int line) {
        this.line = line;
    }

    /**
     * Gets the character position in the line.
     * @return the character position
     */
    public int getCharacter() {
        return character;
    }

    /**
     * Sets the character position in the line.
     * @param character the character position
     */
    public void setCharacter(int character) {
        this.character = character;
    }
}
