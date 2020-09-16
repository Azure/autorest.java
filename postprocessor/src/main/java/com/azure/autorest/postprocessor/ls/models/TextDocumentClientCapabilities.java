package com.azure.autorest.postprocessor.ls.models;

public class TextDocumentClientCapabilities {
    private RenameClientCapabilities rename;

    public RenameClientCapabilities getRename() {
        return rename;
    }

    public void setRename(RenameClientCapabilities rename) {
        this.rename = rename;
    }
}
