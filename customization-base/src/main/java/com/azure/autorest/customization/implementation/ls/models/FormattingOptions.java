// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

public class FormattingOptions {
    private int tabSize;
    private boolean insertSpaces;
    private boolean trimTrailingWhitespace;

    public int getTabSize() {
        return tabSize;
    }

    public void setTabSize(int tabSize) {
        this.tabSize = tabSize;
    }

    public boolean isInsertSpaces() {
        return insertSpaces;
    }

    public void setInsertSpaces(boolean insertSpaces) {
        this.insertSpaces = insertSpaces;
    }

    public boolean isTrimTrailingWhitespace() {
        return trimTrailingWhitespace;
    }

    public void setTrimTrailingWhitespace(boolean trimTrailingWhitespace) {
        this.trimTrailingWhitespace = trimTrailingWhitespace;
    }
}
